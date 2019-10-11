# Android Sunflower Sample

## 1. WorkManager
 - 앱의 종료 여부와 상관없이 수행되어야 하는 작업, 앱의 프로세스 수명과 별도로 살아남기 위한 작업에 하는 것을 추천
 - 예를 들어 이미지를 서버에 업로드 하거나 데이터를 분석하고 이를 데이터베이스에 저장해야 하는 작업에는 WorkManager를 사용하는 것이 좋다

 #### 1.1 WorkManager의 구성
  - WorkManager: 처리해야 하는 작업을 자신의 큐에 넣고 관리한다. 싱글톤 인스턴스로 사용하기 위해 내부에 WorkManager 객체를 반환하는 getInstance()가 있다
  - Work: 추상 클래스로 처리해야 하는 백그라운드 작업의 처리 코드를 이 클래스를 상속받아 doWork() 메서드를 오버라이드 하여 작성하게 된다.
  - WorkRequest: WorkManager를 통해 실제 요청하게 될 개별 작업. WorkRequest는 처리해야 할 작업인 Worker와 작업 반복 여부 및 작업 실행 조건, 제약 사항 등 이 작업을 어떻게 처리할 것인지에 대한 정보가 담겨 있다.
    - OneTimeWorkRequest: 반복하지 않을 작업, 한번만 실행할 작업의 요청을 나타내는 클래스
    - PeriodicWorkRequest: 여러번 실행할 작업의 요청을 나타내는 클래스
  - WorkState

## 2. Navigation
 - Navigation Graph: destination과 이들을 연결하는 actions들을 시각화해서 볼 수 있다.
 - Destination: Navigation을 이용해 이동하는 앱의 특정 화면을 말한다.



--
참고
https://beomseok95.tistory.com/193
https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29

