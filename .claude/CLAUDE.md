# Rebook Common Library

MSA 환경에서 공통으로 사용되는 라이브러리 모음입니다.

## 프로젝트 구조

```
common-library/
├── common-core/          # 핵심 유틸리티 (응답, 예외 처리)
├── common-db/            # JPA BaseEntity, Auditing 설정
├── common-auth/          # Passport 인증, HMAC 유틸리티
└── common-autoconfigure/ # Spring Cloud Auto-configuration
```

## 기술 스택

- **Java**: 17
- **Spring Boot**: 3.5.10
- **Spring Cloud**: 2025.0.0
- **Build**: Gradle (multi-module)
- **Publishing**: GitHub Packages

## 모듈 설명

### common-core
API 응답 표준화 및 예외 처리
- `BaseResponse`, `SuccessResponse`, `ErrorResponse`, `PageResponse`
- `BusinessException`, `ErrorCode`, `GlobalExceptionHandler`

### common-db
JPA 공통 엔티티
- `BaseEntity` (createdAt, updatedAt)
- `BaseEntityWithId` (id + auditing)
- `JpaAuditingConfig`

### common-auth
Passport 기반 인증
- `PassportDecoder`: Passport 토큰 디코딩
- `PassportUser`: 사용자 정보 주입 어노테이션
- `HmacUtil`: HMAC 서명 유틸리티
- Protobuf 사용

### common-autoconfigure
Spring Cloud 자동 설정
- `DiscoveryClientAutoConfiguration`
- `FeignAutoConfiguration`
- `JpaAuditingAutoConfiguration`

## 빌드 및 배포

```bash
# 전체 빌드
./gradlew build

# 특정 모듈 빌드
./gradlew :common-core:build

# 배포 (GITHUB_TOKEN 필요)
./gradlew publish
```

## 코딩 컨벤션

- **Package**: `com.rebook.common.{module}`
- **Lombok**: `@Getter`, `@Builder` 적극 활용
- **의존성**: `compileOnly` 사용 (사용하는 프로젝트에서 의존성 제공)
- **응답**: 모든 API는 `BaseResponse` 상속받은 클래스 사용
- **예외**: `BusinessException` + `ErrorCode` 조합으로 처리

## 의존성 사용법

```groovy
// build.gradle
dependencies {
    implementation 'com.rebook:common-core:1.0.0'
    implementation 'com.rebook:common-db:1.0.0'
    implementation 'com.rebook:common-auth:1.0.0'
    implementation 'com.rebook:common-autoconfigure:1.0.0'
}
```
