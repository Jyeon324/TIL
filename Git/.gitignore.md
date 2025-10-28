# [Git] .gitignore을 사용하여 특정 파일 및 디렉토리 무시하기

## 1. .gitignore이란?

해당 프로젝트 내에서 필요없다고 느끼는 특정 '파일' 및 '디렉토리 경로'에 대해서 Repository에 올리지 않기 위해서 이 파일들을 무시(ignore)하기 위한 정보를 가지고 있는 파일(.gitignore)을 의미 한다.
-   Git 버전 관리에서 제외할 파일(untracked files)이나 디렉터리 목록을 정의하는 설정 파일.
-   Git에게 "이 파일들은 커밋(commit) 대상에 포함하지 마"라고 알려주는 목록표 역할을 함.

## 2. .gitignore를 왜 사용하는가?

-   **보안:** API 키, DB 비밀번호 등이 담긴 `.env`, `secret.key` 파일이 실수로 깃허브에 올라가는 것을 방지한다. (매우 중요)
-   **용량/효율:** `node_modules/`, `build/` 같이 용량이 매우 크고, 설치/빌드 시 자동으로 생성되는 파일들을 저장소(Repository)에서 제외한다.
-   **협업:** `.DS_Store`(macOS), `Thumbs.db`(Windows) 같은 OS 자동 생성 파일이나, `.idea/`, `.vscode/` 같은 개인 IDE 설정이 커밋되어 다른 팀원에게 영향을 주는 것을 막아준다.

## 3. .gitignore 기본 작성법

-   `#` : 주석 (설명을 적을 때 사용)
    -   `# 이것은 주석입니다.`
-   `파일이름` : 특정 파일을 무시
    -   `secret.key`
-   `디렉터리명/` : 특정 디렉터리(폴더)와 그 안의 모든 내용을 무시
    -   `node_modules/`
    -   `build/`
-   `*` (와일드카드) : 특정 패턴을 가진 파일들을 무시
    -   `*.log` (모든 .log 확장자 파일)
    -   `temp_*` (temp_ 로 시작하는 모든 파일)
-   `!` (예외 처리) : 무시 목록 중 특정 파일만 예외로 포함
    -   `*.log`  (모든 log 파일을 무시하지만,)
    -   `!important.log` (important.log 파일은 예외로 추적함)
-   `/` (루트 디렉터리 기준) : `/`로 시작하면 프로젝트 최상위 폴더 기준으로 경로를 설정
    -   `/logs/` (프로젝트 최상위의 logs 폴더만 무시. 하위 폴더의 logs는 무시 안 함)

## 4. [중요] 이미 커밋(Commit)된 파일 무시하기

-   `.gitignore`는 **아직 Git이 추적(track)하지 않는 파일**에 대해서만 동작한다.
-   실수로 `secret.key`를 커밋한 뒤에 `.gitignore`에 추가해도, Git은 이미 추적 중인 파일을 계속 관리한다.

-   **[해결 방법]**
    1.  Git의 추적 대상에서만 삭제 (로컬 파일은 남겨둠)
        ```bash
        git rm --cached <파일명>
        # 예시: git rm --cached secret.key
        ```
    2.  `.gitignore` 파일에 해당 파일명(`secret.key`)을 추가
    3.  변경 사항을 커밋
        ```bash
        git commit -m "Stop tracking secret.key"
        ```

## 5. 유용한 팁

-   **gitignore.io (Toptal):** [https://www.toptal.com/developers/gitignore](https://www.toptal.com/developers/gitignore)
    -   사용하는 언어, 프레임워크, OS (e.g., `Node`, `React`, `Python`, `macOS`)를 입력하면 표준 `.gitignore` 파일을 자동으로 생성해주는 매우 유용한 사이트.
-   **전역(Global) 설정:** 내 컴퓨터의 모든 프로젝트에 공통으로 적용하고 싶다면 (`.DS_Store` 등) 전역 `.gitignore` 파일을 설정할 수 있다.
    ```bash
    git config --global core.excludesfile ~/.gitignore_global
    ```

## 6. 실무 심화 팁 (Advanced Tips)

### 팁 1: 빈 디렉터리(Empty Directory) 추적하기 (`.gitkeep`)

-   **문제점:** Git은 원칙적으로 '빈 폴더'를 추적(commit)하지 않는다.
-   하지만 `logs/`나 `uploads/`처럼 파일 내용은 무시하되, 폴더 구조 자체는 협업을 위해 필요한 경우가 많다.
-   **해결책:** 빈 폴더 안에 `.gitkeep`이라는 이름의 빈 파일을 하나 생성한다. (`logs/.gitkeep`)
-   **작동 원리:**
    1.  `.gitkeep` 파일이 존재하므로 `logs` 폴더는 더 이상 '빈 폴더'가 아니게 된다.
    2.  Git이 `logs/.gitkeep` 파일을 추적(commit)하게 되고, 결과적으로 `logs`라는 디렉터리 구조가 저장소에 포함된다.
-   **참고:** `.gitkeep`은 Git의 공식 기능이 아니라, 개발자들 사이의 '관례(convention)'이다.

### 팁 2: 예외 처리(`!`)의 함정 (폴더 무시의 우선순위)

-   **상황:** `logs/` 폴더 안의 모든 것은 무시하되, `logs/important.log` 파일 *하나만* 예외로 커밋하고 싶을 때.
-   **잘못된 예시 (작동 안 함):**
    ```gitignore
    logs/
    !logs/important.log
    ```
    -   **이유:** `logs/` 규칙이 적용되면, Git은 `logs` 디렉터리 자체를 "열어보지 않겠다"고 결정한다. 따라서 그 안의 `!important.log` 예외 규칙을 읽을 기회조차 없다.
-   **올바른 예시 (작동 함):**
    ```gitignore
    # logs '폴더' 자체가 아닌, '폴더 안의 모든 내용(*)'을 무시
    logs/*
    # 이제 Git이 logs 폴더 안을 보기 때문에, 예외 규칙이 작동함
    !logs/important.log
    ```

### 팁 3: 이중 별표(`**`)를 사용한 하위 디렉터리 무시

-   `*` (Single Asterisk): 현재 디렉터리 수준에서만 패턴 매칭 (`*.log`)
-   `**` (Double Asterisk): 모든 하위 디렉터리(재귀적)를 포함하여 패턴 매칭
-   **예시:**
    -   `**/*.log` : 프로젝트 내 *어디에 있든* 모든 `.log` 파일을 무시 (e.g., `a.log`, `temp/b.log`, `src/app/c.log` 모두 무시)
    -   `**/node_modules/` : 프로젝트 내 *어디에 있든* 모든 `node_modules` 폴더를 무시 (e.g., `client/node_modules/`, `server/node_modules/` 등)

---

## 7. 예시 .gitignore 파일

```gitignore
# ===================================================
# 1. 보안 (Security)
# ===================================================
# (중요) .env 파일, 개인 키 파일 등 민감 정보
.env
*.key
secrets.json

# ===================================================
# 2. 의존성 및 빌드 파일 (Dependencies & Build)
# ===================================================
# Node.js
node_modules/

# Python 가상 환경
venv/
.venv/

# Build/Distribution output
build/
dist/

# ===================================================
# 3. OS 및 IDE 설정 (System & IDE)
# ===================================================
# macOS
.DS_Store

# Windows
Thumbs.db

# IDE (VSCode, IntelliJ)
.vscode/
.idea/

# ===================================================
# 4. 로그 및 예외 처리 (심화 팁 #1, #2 적용)
# ===================================================
# 'logs/' 폴더 자체를 무시하면 예외 처리가 안 됨.
# 따라서 'logs/' 내부의 모든 것(*)을 무시하도록 설정
logs/*

# [심화 팁 #2 적용]
# 하지만 logs/important.log 파일은 예외로 추적(커밋)함
!logs/important.log

# [심화 팁 #1 적용]
# 'logs'라는 빈 폴더 구조를 유지하기 위한 .gitkeep 파일은 예외로 추적
# (이렇게 하지 않으면 'logs/*' 규칙 때문에 .gitkeep도 무시됨)
!logs/.gitkeep

# ===================================================
# 5. 재귀적 무시 (심화 팁 #3 적용)
# ===================================================
# [심화 팁 #3 적용]
# 프로젝트 내 모든 하위 폴더에 있는 .pyc 파일 (Python 캐시)을 무시
**/*.pyc

# 모든 .tmp 임시 파일 무시
**/*.tmp

# ===================================================
# 6. 기타 로그 파일
# ===================================================
npm-debug.log*
yarn-error.log
```

## 8. 참고 자료
-   [Git 공식 문서 - gitignore](https://git-scm.com/docs/gitignore)

## 9. 마무리

`.gitignore` 파일을 적절히 활용하면 프로젝트의 보안과 효율성을 크게 향상시킬 수 있다. 프로젝트 초기 설정 시 반드시 필요한 파일들을 `.gitignore`에 추가하여 깔끔한 버전 관리를 유지하자.

## 10. 오늘 배운 점 (회고)

-   gitignore 파일은 프로젝트 폴더의 루트 디렉토리에 위치시켜야 한다.
    -   왜? 그래야 Git이 해당 프로젝트에서 무시할 파일 및 디렉토리 목록을 올바르게 인식할 수 있다.
-   gitignore의 중요성을 이해했다. 특히 보안 측면에서 민감한 정보가 깃허브에 올라가는 것을 방지하는 것이 매우 중요하다.
-   보안 측면 이외 프로젝트를 인스톨 하면서 생성되는 같은 정보를 왜 무시해야 하는지 용량 관점에서 이해했다.
-   `.gitignore`의 기본 문법뿐만 아니라, `git rm --cached`를 통해 이미 추적된 파일을 제외하는 법을 배웠다. 많이 하는 실수 중에 하나니까 꼭 기억해두자.
