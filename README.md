# 2026 신입 Back-End 개발자 코딩 과제 - 간단한 CMS REST API

---

## 구현 내용

### 기술 스택
- Java 25
- Spring Boot 4.0.3
- Spring Security (JWT 인증)
- Spring Data JPA
- H2 Database
- Lombok
- Gradle
- Swagger (SpringDoc OpenAPI 2.8.5)

### 로그인 방식
**JWT(JSON Web Token) 기반 인증**을 사용합니다.
- 회원가입 시 USER 권한으로 자동 등록
- 로그인 시 JWT 토큰 발급
- 이후 모든 API 요청 시 `Authorization: Bearer {token}` 헤더에 토큰 포함
- 토큰 유효기간: 24시간
- Stateless 세션 관리

### 구현된 기능

#### 1. 인증 및 회원가입
- 회원가입 (POST /api/auth/signup)
  - 사용자명 중복 체크
  - 비밀번호 암호화 (BCrypt)
  - 자동으로 USER 권한 부여
- 로그인 (POST /api/auth/login)
  - JWT 토큰 발급

#### 2. 콘텐츠 CRUD
- 콘텐츠 생성 (POST /api/contents)
- 콘텐츠 목록 조회 (GET /api/contents) - **페이징 처리 구현**
- 콘텐츠 상세 조회 (GET /api/contents/{id}) - 조회수 증가
- 콘텐츠 수정 (PUT /api/contents/{id})
- 콘텐츠 삭제 (DELETE /api/contents/{id})

#### 3. 권한 관리
-  Role 기반 접근 제어 (ADMIN, USER)
-  콘텐츠 생성자 본인만 수정/삭제 가능
-  ADMIN은 모든 콘텐츠 수정/삭제 가능

#### 4. 데이터 모델
-  Contents Entity (요구사항 명세대로 구현)
-  User Entity (인증/권한 관리)
-  JPA Auditing (생성자/수정자 자동 설정)

#### 5. 예외 처리
-  전역 예외 핸들러 (@RestControllerAdvice)
-  Custom Exception 클래스들
-  유효성 검증 (Validation)
-  일관된 에러 응답 형식

#### 6. API 문서
-  Swagger UI (SpringDoc OpenAPI)
-  인터랙티브 API 테스트
-  JWT 인증 지원

### 실행 방법

1. **프로젝트 빌드**
```bash
./gradlew clean build
```

2. **애플리케이션 실행**
```bash
./gradlew bootRun
```

3. **접속 URL**
- API Server: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

### 테스트 계정

| 사용자명 | 비밀번호 | 권한 |
|---------|---------|------|
| admin | password123 | ADMIN |
| user1 | password123 | USER |
| user2 | password123 | USER |

### API 문서 확인 방법

#### Swagger UI 사용
1. 브라우저에서 http://localhost:8080/swagger-ui.html 접속
2. 우측 상단 "Authorize" 버튼 클릭
3. 로그인 API로 토큰 발급받기
4. 발급받은 토큰을 "Value" 필드에 입력 (Bearer는 자동으로 추가됨)
5. "Authorize" 버튼 클릭
6. 이제 모든 API를 Swagger UI에서 직접 테스트 가능

### 사용한 AI 및 참고 자료

- **AI 도구**: Cursor AI (Claude Sonnet 4.5), Chat-GPT

- **참고 자료**:
  - Spring Boot 4 공식 문서
  - Spring Security 공식 문서
  - SpringDoc OpenAPI 공식 문서












