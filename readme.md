# 게시판 만들기

**프레임워크:** Spring, Mysql, MongoDB, Mybatis

게시판의 사용자의 계정은 Mysql에 저장하였고 게시판에 글을 쓰는 내용은 MongoDB에 저장하였습니다. 일반 게시판과 같이 페이징처리가 되어있으며 쓴 글의 내용은 전부 (삭제, 수정, 쓰기)가 가능합니다.  

또한 사용자의 로그인 여부를 관리하는 세션을 두어 관리하고 있습니다. 그렇기에 해당 사용자만이 글을 삭제, 수정이 가능합니다. 

이 프로젝트의 데이터베이스는 모두 회사의 database를 사용하고 있습니다. 

