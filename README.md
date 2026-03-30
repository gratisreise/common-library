# Rebook Common Library

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-blue.svg)](https://gradle.org/)

MSA 환경에서 공통으로 사용되는 라이브러리 모음입니다. API 응답 표준화, 예외 처리, JPA Auditing, Passport 인증 등 마이크로서비스 간 공통 기능을 제공합니다.

## 기술 스택

### Language & Framework
- **Java 17**, **Spring Boot 3.5.10**, **Spring Cloud 2025.1.0**

### Build & Publishing
- **Gradle** (multi-module), **GitHub Packages**

---

## 프로젝트 구조

```
common-library/
├── common-core/          # 핵심 유틸리티 (응답, 예외 처리)
│   └── response/         # BaseResponse, SuccessResponse, ErrorResponse, PageResponse
│   └── exception/        # ErrorCode, BusinessException, GlobalExceptionHandler
├── common-db/            # JPA 공통 엔티티
│   └── BaseEntity, BaseEntityWithId, JpaAuditingConfig
├── common-auth/          # Passport 인증, HMAC 유틸리티
│   └── PassportDecoder, PassportUser, PassportWebConfig, HmacUtil
│   └── Passport.proto    # Protobuf 스키마
└── common-autoconfigure/ # Spring Cloud Auto-configuration
    └── DiscoveryClientAutoConfiguration, FeignAutoConfiguration, JpaAuditingAutoConfiguration
```

---

## 모듈 설명

### common-core

API 응답 표준화 및 전역 예외 처리를 제공합니다.

- `BaseResponse` — 공통 응답 필드 (`success`, `timestamp`)
- `SuccessResponse<T>` / `ErrorResponse` — 성공/에러 응답 래핑
- `PageResponse<T>` — 페이징 응답
- `ErrorCode` (인터페이스) / `CommonError` (공통 에러 코드 Enum)
- `BusinessException` — 비즈니스 예외
- `GlobalExceptionHandler` — `@RestControllerAdvice` 전역 예외 핸들러

### common-db

JPA 공통 엔티티 및 Auditing 설정을 제공합니다.

- `BaseEntity` — `createdAt`, `updatedAt` 필드 자동 관리
- `BaseEntityWithId` — `id` + Auditing 필드
- `JpaAuditingConfig` — `@EnableJpaAuditing` 설정 클래스

### common-auth

Passport 기반 인증 모듈입니다. API Gateway에서 발급한 Passport 토큰을 디코딩하여 사용자 정보를 주입합니다.

- `PassportDecoder` — Passport 토큰 디코딩 (Protobuf)
- `@PassportUser` — 컨트롤러 파라미터에 사용자 정보 주입
- `PassportUserResolver` — `HandlerMethodArgumentResolver` 구현
- `PassportWebConfig` — WebMvc 설정 (Resolver 등록)
- `HmacUtil` — HMAC 서명 유틸리티

### common-autoconfigure

Spring Cloud 관련 Auto-configuration을 제공합니다.

- `DiscoveryClientAutoConfiguration` — Eureka Service Discovery 설정
- `FeignAutoConfiguration` — OpenFeign 클라이언트 설정
- `JpaAuditingAutoConfiguration` — JPA Auditing 자동 설정

---

## 사용법

### 의존성 추가

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/gratisreise/common-library")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation 'com.rebook:common-core:1.0.1'
    implementation 'com.rebook:common-db:1.0.0'
    implementation 'com.rebook:common-auth:1.0.2'
    implementation 'com.rebook:common-autoconfigure:1.0.1'
}
```

### 공통 응답 사용

```java
@GetMapping("/users/{id}")
public SuccessResponse<UserResponse> getUser(@PathVariable Long id) {
    return SuccessResponse.ok(userService.findById(id));
}
```

### 공통 예외 사용

```java
public enum UserError implements ErrorCode {
    USER_NOT_FOUND(404, "USR_001", "사용자를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}

// 비즈니스 로직에서
throw new BusinessException(UserError.USER_NOT_FOUND);
```

### Passport 인증 사용

```java
@GetMapping("/me")
public SuccessResponse<UserResponse> getMyInfo(@PassportUser Passport passport) {
    return SuccessResponse.ok(userService.findById(passport.getUserId()));
}
```

### BaseEntity 상속

```java
@Entity
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
```

---

## 빌드 및 배포

```bash
# 전체 빌드
./gradlew build

# 특정 모듈 빌드
./gradlew :common-core:build

# GitHub Packages 배포 (GITHUB_TOKEN 필요)
./gradlew publish
```
