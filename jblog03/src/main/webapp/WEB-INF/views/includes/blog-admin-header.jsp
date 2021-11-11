<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div id="header">
	<h1>${basic.title }</h1>
	<ul class="menu">
		<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
		<li><a href="${pageContext.request.contextPath }/blog/${authUser.id }/admin/basic">블로그관리</a></li>
		<li><a href="${pageContext.request.contextPath }/blog/${authUser.id }/1">블로그메인</a></li>
	</ul>
</div>