<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- form태그지만, 내부에서는 자바코드로 돌아감 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>

<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

			<!-- form 태그 적용 -->
				<form:form
					class="join-form"
					modelAttribute="userVo"
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
				
					<label class="block-label" for="name">이름</label>
					<form:input path="name"/>
					
					<p style="text-align:left; padding-left:0; color: #f00">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<strong>${errors.getFieldError('name').defaultMessage }</strong>
							</c:if>
						</spring:hasBindErrors>
					</p>
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email"/>
					<input id="btn-check-email" type="button" value="중복체크">
					<img id="img-check-email" src='${pageContext.request.contextPath }/assets/images/check.png' style='width:16px; display: none'/>
					<p style="text-align:left; padding-left:0; color: #f00">
						<form:errors path="email"/>
					</p>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					<p style="text-align:left; padding-left:0; color: #f00">
						<form:errors path="password"/>
					</p>
					
					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여" />
						<form:radiobutton path="gender" value="female" label="남" />
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
	</div>
</body>
</html>
