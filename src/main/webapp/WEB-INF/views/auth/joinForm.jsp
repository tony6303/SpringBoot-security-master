<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../layout/header.jsp"%>
<div class="container">
<h1>회원가입 페이지입니다</h1>
	<form action="/join" method="post">
		<input type="text" placeholder="Username" name="username" class="form-control" required/> <br/>
		<input type="password" placeholder="Password" name="password" class="form-control" required/> <br/>
		<input type="email" placeholder="Email" name="Email" class="form-control" required/> <br/>
		<Button class="btn btn-primary">회원가입</Button>
	</form>
	회원가입을 이미 하셨나요? <a href="/loginForm"><button class="btn btn-primary">로그인</button></a>
</div>
	<br/>
	
	
</body>
</html>