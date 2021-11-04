<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-admin-header.jsp" />

		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/blog/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath }/blog/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${category }"	var="vo" varStatus="status">	
			      		<tr>
							<td>${vo.no }</td>
							<td>${vo.name }</td>
							<td>카운트해서 전체 게시물 갯수 세면 그만임</td>
							<td>${vo.desc }</td>
							<td>
								<c:if test="${vo.desc eq '미분류'}">
									<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
								</c:if>
								
								<c:if test="${vo.desc ne '미분류'}">
									<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
								</c:if>			
							</td>
						</tr>  	
		      		</c:forEach>
				</table>
      	
      			<form action="${pageContext.request.contextPath }/category/insert" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
	      			${authUser.id }
	      			<input type="hidden" value="${authUser.id }" name="${blogId }"/>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
		      	</form> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-admin-footer.jsp" />
	</div>
</body>
</html>