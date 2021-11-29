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

var path = "${pageContext.request.contextPath }"; 

var listTemplate = new EJS({
	url: path + "/assets/js/ejs/list-template.ejs"
});

var listAddTemplate = new EJS({
	url: path + "/assets/js/ejs/listadd-template.ejs"
});

$(function(){
	// 카테고리 리스트 
var fetchList = function(){
		
		$.ajax({
			url: path + '/category/api/list?id=${authUser.id }',
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
				
				var html = listTemplate.render(response, path);
				$(".admin-category-main").append(html);
			},
			
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
			
		});
		
	}
	
var addList = $("#add-form-ajax").submit(function(event){
	event.preventDefault();

	var vo = {};
	vo.name = $("#cate-name").val();
	vo.description = $("#description").val();
	vo.blogId = $("#blogId").val();
	
	console.log(vo);
		$.ajax({
			url: path + '/category/api/insert',
			async: true,
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response) {
				if(response.result !== 'success') {
					console.error(response.message);
					return
				}

				// 이건 왜 안됐을까?
	    		// var html = listAddTemplate.render(response, path);
				var no = document.querySelector("tbody.admin-category-main").getElementsByTagName('tr').length;
	    		var html = 		
	    		"<tr value='" + vo.no + "'>" + 
		    		"<td>" + (no+1) + "</td>" + 
		    		"<td>" + vo.name + "</td>" + 
		    		"<td>0</td>" + 
		    		"<td>" + vo.description + "</td>" + 
		    		"<td>" + 
		    			"<a href='" + path + "/blog/" + vo.blogId + "/admin/category/delete/" + vo.no + "'>" + 
		    				"<img src='" + path + "/assets/images/delete.jpg'>" + 
		    			"</a>" + 
		    		"</td>" + 
		    	"</tr>"
		    	
				$(".admin-category-main").append(html);
			},
			
			error: function(xhr, status, error) {
				console.error(status + ":" + error);
			}
		});
	});

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
		      		
					<tbody class="admin-category-main"></tbody> 
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
				
				<form id="add-form-ajax" action="" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
	      			<input type="hidden" name="blogId" id="blogId" value="${authUser.id }" />
			      	<table id="admin-category-add">
			      		<tr>
			      			<td class="category-name">카테고리명</td>
			      			<td><input type="text" id="cate-name" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="category-explanation">설명</td>
			      			<td><input type="text" id=description name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
		      	</form> 
				
				<p>==================================위에는 ajax 추가, 아래는 jstl 추가============================</p>
				
      			<form id="add-form" action="${pageContext.request.contextPath}/blog/${authUser.id }/admin/category/insert" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
	      			<input type="hidden" name="blogid" value="${authUser.id }" />
			      	<table id="admin-category-add">
			      		<tr>
			      			<td class="category-name">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="category-explanation">설명</td>
			      			<td><input type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
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