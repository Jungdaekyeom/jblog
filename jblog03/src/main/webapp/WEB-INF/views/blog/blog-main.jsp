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

		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />

		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				
				<c:choose>
					<c:when test="${ postMain.title ne null}">
						<h4>${postMain.title }</h4>
						<p>
						${postMain.contents }
						<p>
					</c:when>
					<c:when test="${ postMain.title eq null}">
						<h4>게시글이 비어있습니다.</h4>
						<p>
						게시글을 추가해 주세요.
						<p>					
					</c:when>
				</c:choose>

				</div>
				<ul class="blog-list">
					<c:forEach items="${post }"	var="vo" varStatus="status">		
						<li>
							<a href="${pageContext.request.contextPath }/blog/${id}/${vo.categoryNo }/${vo.no}">${vo.title }</a>
							<c:if test="${authUser.id eq basic.id}">
								<span>
									<a href="${pageContext.request.contextPath }/blog/${authUser.id }/admin/post/delete/${vo.categoryNo }/${vo.no }">
										<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
									</a>
								</span>
							</c:if>
							<span>${vo.regDate }</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${basic.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${category }"	var="vo" varStatus="status">		
				<li><a href="${pageContext.request.contextPath }/blog/${id}/${vo.no}/0">${vo.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>