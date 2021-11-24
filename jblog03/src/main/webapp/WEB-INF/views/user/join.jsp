<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>JBlog</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	$("#btn-check-id").click(function() {
		var id = $("#id").val();
		if(id == '') {
			return;
		}
		
		console.log(id);
		$.ajax({
			url: "${pageContext.request.contextPath }/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			// 통신 에러 체크
			error: function(xhr, status, e){
				console.log(status, e);
			},
			success: function(response) {
				console.log(response);
				
				if(response.result != "success"){
					console.error(response.message);
					
					return;
				}
				
				// UserController와 상호작용함
				if(response.data) {
					alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
					$("#id").val("").focus();
					return;
				}
				
				$("#btn-check-id").hide();
				$("#img-check-id").show();
			}
		});		
	});	
});

</script>
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
					
					<label class="block-label" for="id">아이디</label>
					<form:input path="id"/>
					<input id="btn-check-id" type="button" value="중복체크">
					<img id="img-check-id" src='${pageContext.request.contextPath }/assets/images/check.png' style='width:16px; display: none'/>
					<p style="text-align:left; padding-left:0; color: #f00">
						<form:errors path="id"/>
					</p>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					<p style="text-align:left; padding-left:0; color: #f00">
						<form:errors path="password"/>
					</p>

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
