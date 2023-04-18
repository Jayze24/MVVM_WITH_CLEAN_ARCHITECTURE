# MVVM_WITH_CLEAN_ARCHITECTURE

MVVM + Clean Architecture + Unit test(with coverage check)의 샘플 App 입니다.

### Architecture
- core
  - domain : UseCase (주로 ViewModel에서 사용 됩니다. DATA 레이어에서 받은 데이터를 가공하는 등의 로직이 들어갈 수 있습니다. UseCaseA를 ViewModelA, ViewModelB, ViewModelC... 등 여러 곳에 재사용 하면서 코드 중복을 방지 할 수 있고, ViewModel의 가독성을 개선할 수 있으며, 책임을 분할 할 수 있습니다.)
  - data : Repository (다른 레이어는 데이터 소스에 직접 액세스해서는 안 됩니다. 데이터 레이어의 진입점은 항상 저장소 클래스여야 합니다. 저장소 클래스를 진입점으로 사용하면 아키텍처의 다양한 레이어(네트워크, 로컬데이터)를 독립적으로 확장할 수 있습니다. 데이터 변경 사항을 한 곳에 집중하고, 여러 데이터 소스 간의 충돌을 해결할 수 있습니다.)
    - network, database(미구현) : DataSource (각 데이터 소스 클래스는 파일, 네트워크 소스, 로컬 데이터베이스와 같은 하나의 데이터 소스만 사용해야 합니다. 데이터 소스 클래스는 데이터 작업을 위한 애플리케이션과 시스템 간의 가교 역할을 합니다.)
  - model : Entity (App에서 사용되는 데이터 클래스 정의 및 Entitiy 관련 로직이 들어갈 수 있습니다.)
- feature : UI, ViewModel

### Unit Test
#### 커버리지 확인(둘중에 하나 선택)
`./gradlew debugCoverage` (모듈 별 유닛 테스트 시작 및 커버리지 체크)   
`./gradlew coverAllVariants` (variant 별 유닛 테스트 시작 및 커버리지 체크)
#### 각 모듈 별 커버리지 통합 (${rootDir}/build/reports/jacoco/) 
`./gradlew allDebugCoverage`

![Unit Test coverage 확인](https://user-images.githubusercontent.com/32419237/232682301-d686fcbf-d492-4707-9cde-1297e62be70d.png "Unit Test coverage 확인")
