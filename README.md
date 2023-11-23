# EduLeaf

- DB 세팅
    - application.properties에서 mysql root 사용자에 알맞은 비밀번호를 입력
    - mysql -uroot -p 입력 후, 비밀번호를 입력하여 root 권한으로 로그인
    - create database eduleaf; 입력하여, 데이터 베이스 생성
    - 프로그램 실행
- port : 8080
- endpoint : `/api/test?studentID={백준아이디}`
  - ex) `/api/test?studentID=sangjuntest`