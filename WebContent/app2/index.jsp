<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		var isHasCart = "${sessionScope.cart == null}";
		if (isHasCart == "true") {
			$("#message").hide();
		}

		$("a").click(function() {
			$("#message").show();
			var url = this.href;
			var args = {
				"time" : new Date()
			};

			$.getJSON(url, args, function(data) {
				$("#name").text(data.name);
				$("#totalNumber").text(data.totalNumber);
				$("#totalPrice").text(data.totalPrice);
			});

			return false;
		});

	})
</script>
</head>
<body>
	<div id="message">
		已将 <span id="name"></span>书加入购物车，共 <span id="totalNumber"></span>本，<span
			id="totalPrice"></span>元
	</div>
	<br> Struts2&nbsp;&nbsp;
	<a
		href="${pageContext.request.contextPath }/addToCart?name=Struts2&price=100">加入购物车</a>
	<br> Spring&nbsp;&nbsp;
	<a
		href="${pageContext.request.contextPath }/addToCart?name=Spring&price=200">加入购物车</a>
	<br> Hibernate&nbsp;&nbsp;
	<a
		href="${pageContext.request.contextPath }/addToCart?name=Hibernate&price=300">加入购物车</a>
	<br>



</body>
</html>