# MVVM_WITH_CLEAN_ARCHITECTURE

이것은 MVVM + Clean Architecture의 샘플 앱입니다.      
Wiki api를 사용하여 검색 앱을 구현하였습니다.


크게 Data, Domain, UI, ViewModel 영역으로 나눠져 있습니다.


Data는 Repository를 통해 데이터를 가져오거나, 추가, 수정, 삭제를 할 수 있습니다. Source(interface)를 사용하여 Repository와 retrofit 간의 의존성을 제거하였습니다. 이렇게 만들면 추후 retrofit을 제거하고 다른 저장소나 통신 라이브러리로 변경하는 것이 쉽습니다. 또한, 가져온 데이터를 Domain에 있는 Entity 형식에 맞게 맵핑 해줍니다. 이유는 서버에서 전달하는 데이터 구조는 쉽게 변경 될 수 있으며, 앱에서 사용하는 데이터 구조와도 다를 수 있기 때문입니다.

Domain에는 Entity와 UseCase, Boundary(interface)가 있는데 Boundary는 UseCase와 Data에 있는 Repository 간의 의존성을 제거하기 위해 존재합니다.

UI에서는 View를 그려주며, 유저의 이벤트를 전달 받아 ViewModel을 호출 합니다. 또한 화면 구성에 필요한 데이터를 ViewModel에서 관찰합니다.

ViewModel에서는 Domain에 있는 UseCase를 사용하여 데이터를 요청하고 받은 데이터를 가공하여 관찰하고 있는 UI에 알려(Notification)줍니다.
