<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 
1. 导入 jQuery 库
2. 获取 name = "username" 的节点：username
3. 为 username 添加 change 响应函数
3.1 获取 username 的 value 属性值，去除前后空格且不为空，准备发送 Ajax 请求
3.2 发送 Ajax 请求检验 username 是否可用 
3.3 在服务端直接返回一个 html 片段：<font color="red">用户名已存在</font>
3.4 在客户端浏览器将其直接添加到 #message 的html中
 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		$(":text[name='username']").change(function() {
			var val = $(this).val();
			val = $.trim(val);

			if (val != "") {
				var url = "${pageContext.request.contextPath}/validateUsername";
				var args={"username":val, "time":new Date()};
				
				$("#message").load(url,args);
				
			}

		});
	})
</script>
</head>
<body>
	<form action="" method="post">
		Username：<input type="text" name="username" id="username" /> <br>
		<div id="message"></div>
		<br> <input type="submit" name="submit" id="submit" />
	</form>
</body>
</html>