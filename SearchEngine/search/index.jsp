<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>BLOG搜索-搜索引擎</title>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/searchService.js'></script>

<link rel="stylesheet" href="./Styles/DefaultStyle-min.css" />

<script type="text/javascript"
	src="./Scripts/tangram/tangram-min-2.0.2.1.js" charset="utf-8"></script>
<script type="text/javascript" src="./Scripts/browser_check.js"></script>
<script type="text/javascript"
	src="./Scripts/jisuFrontSearch-2.13.2.3.js"></script>
<script type="text/javascript" src="./Scripts/client_timer-min.js"></script>
<script type="text/javascript" src="./Scripts/baidu_tongji_async.js"></script>
</head>
<body onload="OnLoadMain()" class="main_body">
	<input type="hidden" name="startindex" id="startindex" value="1" />
	<div id="searchbar" style="margin-top: 80px">
		<table align="center" style="width: 725px;">
			<tr align="center">
				<td><img src="./Images/logo.jpg" /></td>
			</tr>
			<tr align="center">
				<td>
					<br />
					<span class="search_box_outer">
					<input class="search_box" type="text" name="query"
						id="query" value="" onkeyup="handlekey('HomePage')" /> 
					</span>
					<input class="search_btn" type="button" value="Search" id="Search"
						onclick="javascript:SubmitMain('')" />
				</td>
			</tr>
		</table>
	</div>
	<br />
	<br />
	<div>
		<table align="center" class="copyright">
			<tr align="center">
				<td><span>Spring+DWR+Lucence搜索引擎
						，无版权，请任意修改，mailto：sky5520@126.com</span></td>
			</tr>
		</table>
	</div>
</body>
</html>
