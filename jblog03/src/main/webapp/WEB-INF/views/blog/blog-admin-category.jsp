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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});

var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/listitem-template.ejs"
});

// var path = "${pageContext.request.contextPath }"; 

$(function(){
	// 카테고리 리스트 
	var fetchList = function(){
		
		$.ajax({
			url: '${pageContext.request.contextPath }/category/api/list?id=${authUser.id }',
			async: true,
			type: 'get',
			dataType: 'json',
			contentType: 'application/json',
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				// json 타입으로 path를 넘겨줌
				// var html = listTemplate.render(response, path);
				var html = listTemplate.render(response, {path : "${pageContext.request.contextPath }"});
				$(".admin-cat-main").append(html);
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});
		
	}
	
	fetchList();	
});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/blog/${authUser.id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath }/blog/${authUser.id }/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
					<tbody class="admin-cat-main"></tbody> 
				</table>

				<br/>
				<p>==================================위에는 ajax 표, 아래는 jstl 표============================</p>
				<br/>
				
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
							<td>${status.count}</td>
							<td>${vo.name }</td>
							<td>${vo.count }</td>
							<td>${vo.description }</td>
							<td>
								<c:if test="${vo.name eq '미분류'}">
									&nbsp;
								</c:if>
								
								<c:if test="${vo.name ne '미분류'}">
									<a href="${pageContext.request.contextPath}/blog/${authUser.id }/admin/category/delete/${vo.no }">
										<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
									</a>
								</c:if>			
							</td>
						</tr>  	
		      		</c:forEach>
				</table>
				
      			<form id="add-form" action="${pageContext.request.contextPath}/blog/${authUser.id }/admin/category/insert" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
	      			<input type="hidden" name="blogid" value="${authUser.id }" />
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
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>