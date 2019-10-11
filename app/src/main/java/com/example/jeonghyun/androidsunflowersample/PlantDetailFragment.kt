package com.example.jeonghyun.androidsunflowersample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jeonghyun.androidsunflowersample.data.Plant
import com.example.jeonghyun.androidsunflowersample.databinding.FragmentPlantDetailBinding
import com.example.jeonghyun.androidsunflowersample.utilities.InjectorUtils
import com.example.jeonghyun.androidsunflowersample.viewmodels.PlantDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class PlantDetailFragment : Fragment() {

    private val args: PlantDetailFragmentArgs by navArgs()
    private lateinit var shareText: String

    private val plantDetailViewModel: PlantDetailViewModel by viewModels{
        InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), args.plantId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater, R.layout.fragment_plant_detail, container, false
        ).apply {
            viewModel = plantDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun add(plant: Plant?) {
                    plant?.let {
                        hideAppBarFab(fab)
                        plantDetailViewModel.addPlantToGarden()
                        Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }

            // scroll change listener begins at Y = 0 when image is fully collapsed
            var isToolbarShown =false
            plantDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener{
                    _, _, scrollY, _, _ ->
                    val shouldShowToolbar = scrollY > toolbar.height

                    if(isToolbarShown != shouldShowToolbar){
                        isToolbarShown = shouldShowToolbar

                        appbar.isActivated = shouldShowToolbar

                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }


            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }
        }

        plantDetailViewModel.plant.observe(this) {
            plant -> shareText = if(plant == null) {
                ""
            } else {
                getString(R.string.share_text_plant, plant.name)
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun createShareIntent(){
        val shareIntent = ShareCompat.IntentBuilder.from(activity)
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .apply{
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                } else {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                }
            }
        startActivity(shareIntent)
    }

    private fun hideAppBarFab(fab: FloatingActionButton){
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    interface Callback {
        fun add(plant: Plant?)
    }
}
