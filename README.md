
# 개요

기존 서비스 Jpa 도입을 위한 예제 프로젝트


controller > service에서 dto를 entity에 매핑하기 위한 방법으로는
- ModelMapper
- MapStruct

가 있음


### 두 방법 비교

|**특징**|**ModelMapper**|**MapStruct**|
|---|---|---|
|런타임 / 컴파일 시 변환|런타임에 변환|컴파일 시 변환|
|성능|상대적으로 느림|빠름|
|설정|최소 설정으로 바로 사용 가능|매퍼 인터페이스 및 구현 필요|
|학습 곡선|쉬움|약간의 학습 필요|
|커스터마이징|복잡한 커스터마이징은 코드로 작성해야 함|매핑 규칙을 인터페이스에 선언 가능|


- 간단한 프로젝트에서는 ModelMapper를 추천
- 성능이 중요하고 변환이 많다면 MapStruct가 더 적합

### 스웨거

- URL: http://localhost:8080/swagger-ui.html

### 비고

- Spring Boot 3.x 이상에서는 Jakarta JPA(jakarta.persistence.Entity) 사용
- 이전 버전에서는 **Javax JPA(javax.persistence.Entity)**를 사용
