# EduLeaf

## DB 세팅
### MySQL에서 데이터베이스 생성 ( 최초 1회 )
 - `src/main/resources/application.properties`에서 mysql root 사용자에 알맞은 비밀번호를 입력
 - `mysql -uroot -p` 입력 후, 비밀번호를 입력하여 root 권한으로 로그인
 - `create database eduleaf;` 입력하여, 데이터 베이스 생성

### 프로그램 실행 시의 데이터베이스 초기화
 - 이후 프로그램 실행마다, 데이터베이스는 아래의 경로에 존재하는 sql 파일을 통해 초기화됨
   - `src/main/resources/data.sql`
 - **만약 프로그램 반복 실행에도 데이터베이스 내용을 유지하고 싶다면,**
   - `src/main/resources/application.properties`의 주석 내용을 참고하여 설정을 변경해야 함.

## Port
- 8080