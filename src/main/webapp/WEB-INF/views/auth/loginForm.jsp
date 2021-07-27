<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
<h1>로그인 페이지입니다</h1>
	<form action="/login" method="post">
		<input type="text" placeholder="Username" name="username" class="form-control" autocomplete="off" required/><br/>
		<input type="password" placeholder="Password" name="password" class="form-control" required/><br/>
		<Button class="btn btn-primary">로그인</Button>
	</form><br/>
	회원가입 하지 않으셨나요? <a href="/joinForm"><Button class="btn btn-primary">회원가입</Button></a>
 <a href="/oauth2/authorization/google"><Button class="btn btn-primary">구글 로그인</Button></a>
 <a href="/oauth2/authorization/facebook"><Button class="btn btn-secondary" id="btn-facebook">페이스북 로그인</Button></a>
 <!-- spring yml에 registration정보를 토대로 알아서 쿼리스트링 만들어서 요청주소를 때려준다 -->
 <a href="/oauth2/authorization/naver"><Button class="btn btn-primary">네이버 로그인</Button></a>
 <a href="/oauth2/authorization/kakao"><Button class="btn btn-primary">카카오톡 로그인</Button></a><br/>
 <div id="info" style="color : red;">페이스북 로그인 오류가 발생해서 작동하지 않습니다.</div>
	
	<br/>
	</div>
	
</body>
</html>