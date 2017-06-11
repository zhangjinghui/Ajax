# Ajax 笔记

---

## js 与 ajax
1. ajax（Asynchronous JavaScript and XML）：允许浏览器与服务器通信而无须刷新当前页面的技术都被叫做Ajax  
2. ajax技术示例：google suggest、google maps  
3. 不用刷新整个页面便可与服务器通讯的办法  
> + web 传统模型客户端向服务器发送 **请求** ，服务器返回整个页面  
> + ajax 模型，数据在客户端与服务器之间 **独立传输** ，服务器不再返回整个页面  

4. 实现异步传输的对象：**XMLHttpRequest**，该对象是 JavaScript 对象的一个扩展，可以使网页与服务器进行通信。通常把 Ajax 当成 XMLHttpRequest 对象的代名词  
5. ajax 工作原理  
![QQ截图20170610125127.png](https://ooo.0o0.ooo/2017/06/10/593b7adec25c6.png)  
6. ajax 并不是一项新技术，而是几种技术的聚合：  
> + 服务器端语言：具备向浏览器发送特定信息的能力  
> + XML：用于客户端与服务器之间传递信息（也可使用 json ）  
> + XHTML 和 CSS：客户端内容显示  
> + DOM：动态显示和交互  
> + JavaScript：绑定和处理数据  

7. 在 IE7 之前，各浏览器创建 XMLHttpRequest 对象的方式有所不同，但该对象的方法和属性都是相同的  
 + 方法：  
![方法.png](https://ooo.0o0.ooo/2017/06/10/593b7ca67b030.png)
 + 属性：  
![属性.png](https://ooo.0o0.ooo/2017/06/10/593b7ca68e3c1.png)  
8. 发送请求涉及到三个关键部分  
 + **onreadystatechange 事件处理函数**：每次服务器对 readyState 属性改变时，都会触发该事件（由服务器触发）  
 + **open(method,url)**：method 可以取 **get** 或 **post**，前者一般用于获取，传递参数较少（**在url中传递数据**）；后者较少使用（**在content参数中传递数据**）。---为避免浏览器缓存，通常在请求 url 后面加入一个时间戳参数。
 + **send(content)**:如果用 **POST** 请求向服务器发送数据，需要将 **“Content-type”** 的首部设置为 **“application/x-www-form-urlencoded”**.它会告知服务器正在发送数据，并且数据已经符合URL编码了。该方法必须在 **open()** 之后才能调用  

9. **接收响应** 服务器可以修改以下属性  
> + readyState：取值范围0-4，4代表响应完毕
> + status：常用状态码 404-没找到页面、403-禁止访问、500-服务器内部错误、**200-正常**、**304-没有修改**
> + reponseText：当 readyState 为 4 时，该属性才可以使用，代表服务器返回的内容（HTML、XML、普通文本、json）  
> + reponseXML：如果返回的内容是 XML，那么数据将存储在该属性中（返回xml需要设置请求头部 MIME 类型 为text/xml）  

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		// 1. 获取 a 节点，并为其添加 onclick 响应函数
		var a = document.getElementsByTagName("a")[0].onclick = function() {

			// 3. 创建一个 XMLHttpRequest 对象
			var xhr = new XMLHttpRequest();
			
			// 4. 准备发送请求数据 url，并添加时间戳，避免缓存
			var url = this.href + "?time=" + new Date();
			var method = "get";
				
			
			// 5. 调用 XMLHttpRequest 对象的 open()方法
			xhr.open(method, url);
			// 6. 调用 XMLHttpRequest 对象的 send()方法
			xhr.send(null);
			
			// post 测试
// 			xhr.open("post", url);
// 			xhr.setRequestHeader("contentType","application/x-www-form-urlencoded");
// 			xhr.send("name='aaa'");


			// 7. 为 XMLHttpReuqest 对象添加 onreadystatechange 响应函数
			xhr.onreadystatechange = function() {
			// 8. 判断响应是否完成：XMLHttpRequest 对象的 readyState 值为 4 时
				if (xhr.readyState == 4) {
					// 9. 再判断响应是否可用：XMLHttpRequest 对象 status 值为 200 或 304 时
					if (xhr.status == 200 || xhr.status == 304) {
						// 10. 打印响应结果 reponseText
						alert(xhr.responseText);
					}
				}
			}

			// 2. 取消 a 节点的默认行为
			return false;
		}
	}
</script>
</head>
<body>

	<a href="helloajax.txt">Hello Ajax!</a>

</body>
</html>
```
## 服务器三种响应数据类型  
1. **html**  
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>People at Clearleft</title>
<style type="text/css">
@import url("clearleft.css");
</style>
<script type="text/javascript">
	window.onload = function() {
		var a = document.getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			a[i].onclick = function() {
				var url = this.href;
				var method = "get";

				var xhr = new XMLHttpRequest();
				xhr.open(method, url);
				xhr.send(null);

				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200 || xhr.status == 304) {
							document.getElementById("details").innerHTML = xhr.responseText;
						}
					}
				};

				return false;
			}
		}
	}
</script>
</head>
<body>
	<h1>People</h1>
	<ul>
		<li><a href="files/andy.html">Andy</a></li>
		<li><a href="files/richard.html">Richard</a></li>
		<li><a href="files/jeremy.html">Jeremy</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>
```
2. **xml**
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>People at Clearleft</title>
<style type="text/css">
@import url("clearleft.css");
</style>
<script type="text/javascript">
	window.onload = function() {
		var a = document.getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			a[i].onclick = function() {
				var url = this.href;
				var method = "get";

				var xhr = new XMLHttpRequest();
				xhr.open(method, url);
				xhr.send(null);

				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200 || xhr.status == 304) {
							// 1. 结果为 XML 格式，需要使用 responseXML 来获取
							var result = xhr.responseXML;

							// 2. 结果不能直接使用，必须先创建对应节点，再把节点放入 #details 中
							// 目标格式如下：
							/*
								<h2><a href="mailto:andy@clearleft.com">Andy Budd</a></h2>
								<a href="http://andybudd.com/">http://andybudd.com/</a>
							 */
							var nameVal = result.getElementsByTagName("name")[0].firstChild.nodeValue;
							var emailVal = result.getElementsByTagName("email")[0].firstChild.nodeValue;
							var websiteVal = result
									.getElementsByTagName("website")[0].firstChild.nodeValue;

							var aNode = document.createElement("a");
							aNode.appendChild(document.createTextNode(nameVal));
							aNode.href = "mailto:" + emailVal;

							var h2Node = document.createElement("h2");
							h2Node.appendChild(aNode);

							var aNode2 = document.createElement("a");
							aNode2.appendChild(document
									.createTextNode(websiteVal));
							aNode2.href = websiteVal;

							var details = document.getElementById("details");
							details.innerHTML = "";
							details.appendChild(h2Node);
							details.appendChild(aNode2);
						}
					}
				};

				return false;
			}
		}
	}
</script>
</head>
<body>
	<h1>People</h1>
	<ul>
		<li><a href="files/andy.xml">Andy</a></li>
		<li><a href="files/richard.xml">Richard</a></li>
		<li><a href="files/jeremy.xml">Jeremy</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>
```  
3. **json**  
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>People at Clearleft</title>
<style type="text/css">
@import url("clearleft.css");
</style>
<script type="text/javascript">
	window.onload = function() {
		var a = document.getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			a[i].onclick = function() {
				var url = this.href;
				var method = "get";

				var xhr = new XMLHttpRequest();
				xhr.open(method, url);
				xhr.send(null);

				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200 || xhr.status == 304) {
							var result = xhr.responseText;
							var jsonObject = eval("(" + result + ")");

							var nameVal = jsonObject.person.name;
							var emailVal = jsonObject.person.email;
							var websiteVal = jsonObject.person.website;

							var aNode = document.createElement("a");
							aNode.appendChild(document.createTextNode(nameVal));
							aNode.href = "mailto:" + emailVal;

							var h2Node = document.createElement("h2");
							h2Node.appendChild(aNode);

							var aNode2 = document.createElement("a");
							aNode2.appendChild(document
									.createTextNode(websiteVal));
							aNode2.href = websiteVal;

							var details = document.getElementById("details");
							details.innerHTML = "";
							details.appendChild(h2Node);
							details.appendChild(aNode2);
						}
					}
				};

				return false;
			}
		}
	}
</script>
</head>
<body>
	<h1>People</h1>
	<ul>
		<li><a href="files/andy.json">Andy</a></li>
		<li><a href="files/richard.json">Richard</a></li>
		<li><a href="files/jeremy.json">Jeremy</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>
```
4. 三种返回类型的优缺点：  
### HTML:  
> **优点**：  
> + 从服务器端发送的 HTML 代码在浏览器端不需要用 JavaScript 进行解析。 
> + HTML 的可读性好。
> + HTML 代码块与 innerHTML 属性搭配，效率高。
>
> **缺点**：  
> + 若需要通过 AJAX 更新一篇文档的多个部分，HTML 不合适  
> + innerHTML 并非 DOM 标准。  

### XML:  
> **优点**：  
> + XML 是一种通用的数据格式。  
> + 不必把数据强加到已定义好的格式中，而是要为数据自定义合适的标记。  
> + 利用 DOM 可以完全掌控文档。  
>
> **缺点**：  
> + 如果文档来自于服务器（动态生成，没有xml文件头），就必须得保证文档含有正确的首部信息。若文档类型不正确，那么 responseXML 的值将是空的。  
> + 当浏览器接收到长的 XML 文件后， DOM 解析可能会很复杂  

### json:  
> **优点**：
> + json 是 JavaScript **原生格式**,在 js 中处理 json 不需要任何特殊的 api  
> + 作为一种数据传输格式，JSON 与 XML 很相似，但是它更加灵巧。  
> + JSON 不需要从服务器端发送含有特定内容类型的首部信息。  
>
> **缺点**：  
> + 语法过于严谨  
> + 代码不易读（相对于 xml）  
> + eval 函数存在风险  

5. 小结：  
> + **若应用程序不需要与其他应用程序共享数据的时候**, 使用 HTML 片段来返回数据时最简单的  
> + **如果数据需要重用**, JSON 文件是个不错的选择, 其在性能和文件大小方面有优势  
> + **当远程应用程序未知时**, XML 文档是首选, 因为 XML 是 web 服务领域的 “世界语”  

## jQuery 中的 ajax  
1. jQuery 对 ajax 进行了封装  
> + 最底层的封装 $.ajax()  
> + 第二层 **load()**、**$.get()**、**$.post()**  
> + 第三层 **$.getJSON()**、$.getScript()  

2. **load()**  
> + 载入远程 HTML 代码并插入到 DOM 中  
> + load(url,[data],[callback]): url 可以使用 jQuery 的过滤器对返回的 html 进行必要的过滤（不要忘记空格）；data为传递的参数，若不指定，则为get方式，若指定则为post方式；回调函数有三个参数，data代表返回的内容，textStatus 对象、XMLHttpRequest 对象  

3. **$.get()或$.post()**  
> + $.get(url,[data],[callback],[type]):回调函数只有两个参数，data代表返回的内容，textStatus对象  
> + 这两个方法都是全局函数，直接使用 “$.” 的形式调用  

4. 以下三种方式效果相同  
```js
// 1. $.getJSON()
$.getJSON(url,[data],function(data){

})

// 2. $.get()
$.get(url,[data],function(data){

},json)

// 3. $.post()
$.post(url,[data],function(data){

},json)
```
5. 代码示例：  
jquery-html  
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>People at Clearleft</title>
<style type="text/css">
@import url("clearleft.css");
</style>
<script type="text/javascript" src="../scripts/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function() {
		$("a").click(function() {
			// url使用选择器进行过滤（不要忘记空格）
			var url = this.href+" h2 a";
			// 参数必须是 json
			var args = {
				"time" : new Date()
			};
			$("#details").load(url, args);
			return false;
		})
	})
</script>
</head>
<body>
	<h1>People</h1>
	<ul>
		<li><a href="files/andy.html">Andy</a></li>
		<li><a href="files/richard.html">Richard</a></li>
		<li><a href="files/jeremy.html">Jeremy</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>
```
jquery-xml  
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>People at Clearleft</title>
<style type="text/css">
@import url("clearleft.css");
</style>
<script type="text/javascript" src="../scripts/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").click(function(){
			var url = this.href;
			var args = {"time":new Date()};
			$.get(url,args,function(data){
				var nameVal = $(data).find("name").text();
				var emailVal = $(data).find("email").text();
				var websiteVal = $(data).find("website").text();
				
				$("#details").empty().append("<h2><a href='mailto:"+ emailVal +"'>"+ nameVal +"</a></h2>")
							 .append("<a href='"+ websiteVal +"'>"+ websiteVal +"</a>");
			})
			return false;
		})
	})
</script>
</head>
<body>
	<h1>People</h1>
	<ul>
		<li><a href="files/andy.xml">Andy</a></li>
		<li><a href="files/richard.xml">Richard</a></li>
		<li><a href="files/jeremy.xml">Jeremy</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>
```
jquery-json  
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>People at Clearleft</title>
<style type="text/css">
@import url("clearleft.css");
</style>
<script type="text/javascript" src="../scripts/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").click(function(){
			var url = this.href;
			var args = {"time":new Date()};
			$.getJSON(url, args, function(data){
				$("#details").empty().append("<h2><a href='"+ data.person.email +"'>"+ data.person.name +"</a></h2>")
							 .append("<a href='"+ data.person.website +"'>"+ data.person.website +"</a>");
			});
			return false;
		});
	})
</script>
</head>
<body>
	<h1>People</h1>
	<ul>
		<li><a href="files/andy.json">Andy</a></li>
		<li><a href="files/richard.json">Richard</a></li>
		<li><a href="files/jeremy.json">Jeremy</a></li>
	</ul>
	<div id="details"></div>
</body>
</html>
```
## 总结  
1. 使用 XMLHttpRequest 对象实现 ajax 的方式操作复杂，常用的方法时使用 jquery 和 json 代替  
2. ajax 三种传输方式比较  
 + xml：笨重、解析困难。但 xml 是通用的数据交换格式  
 + html：不需要解析就可以直接放入到文档中，若仅更新一部分区域，推荐使用。但传输数据不是很方便，且 html 需要拼接完成  
 + json：小巧，有面向对象的特征，且有许多第三方 jar 包可以把 java 对象或集合转为 JSON 字符串  

## jackson.jar 的使用  
1. 服务器后台手动生成 json，需要严格的格式控制（属性名必须使用双引号等），这时可以借助 jackson 包提供的  **ObjectMapper.writeValueAsString(Object o);** 方法生成符合规定的json 字符串  
```java
package com.zjh.ajax.jackson.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class User {
	private Integer id;
	private String username;

	// 在 getter 方法上添加 @JsonIgnore 可以忽略该属性
	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// json 的属性名是根据 getter 方法名而定的，与属性名无关
	public String getName() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return 12;
	}

	// 不仅可以操作对象，也可以操作集合
	public List getList() {
		return Arrays.asList("aaa", "bbb", "ccc");
	}

	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		// 构造 ObjectMapper 对象
		ObjectMapper mapper = new ObjectMapper();

		// 调用 ObjectMapper 对象的 writeValueAsString()方法，返回 String
		String jsonStr = mapper.writeValueAsString(new User());
		System.out.println(jsonStr);
	}

}
```
