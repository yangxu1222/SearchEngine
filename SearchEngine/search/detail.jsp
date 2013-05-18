<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BLOG搜索 -搜索引擎</title>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/searchService.js'></script>

<link rel="stylesheet" href="./Styles/DefaultStyle-min.css" />
<link rel="stylesheet" href="./Styles/DefaultAdsStyle-min.css" />

<script type="text/javascript" src="./Scripts/tangram/tangram-min-2.0.2.1.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="./Scripts/jisuFrontSearch-2.13.2.3.js"></script>
<script type="text/javascript" src="./Scripts/baidu_tongji_async.js"></script>
</head>
<body onload="javascript:OnLoad('')" class="result_body">
	<input type="hidden" name="startindex" id="startindex" value="1" />
	<div id="searchbar" style="margin-top: 0px;">
		<table align="left">
			<tr align="left">
				<td>
					<div style="float: left;">
						<a href="./"><img src="Images/small_logo.jpg" /></a>
					</div>
				</td>
			</tr>
			<tr align="center">
				<td><span class="search_box_outer">
						<input class="search_box"  type="text"
						name="query" id="query" value="" onkeyup="handlekey()" />
				</span> <input class="search_btn" type="button" value="Search" id="Search"
					onclick="javascript:Submit('')" /></td>
			</tr>
			<tr align="left">
				<td><input type="radio" name="tn" value="content"
					checked="checked" id="content" /><label for="contents"
					class="top_tn_radio_text">搜索全文</label> <input type="radio"
					name="tn" value="title" id="title" /><label
					for="contents_title" class="top_tn_radio_text">搜索标题</label></td>
			</tr>
		</table>
	</div>
	<br style="clear: both" />
	<br />
	<div style="width: 1050px;">
		<div id="result" style="float: left; margin-left: 3px; width: 552px;"></div>
		<div id="ads_result"
			style="margin-right: 0px; width: 300px; float: right;"></div>
		<br style="clear: both" /> <br /> <br />
		<div id="paging" class="divPaging" style="margin-left: 3px"></div>
		<br /> <br />
	</div>

	<div>
		<table align="center" class="copyright">
		    <tr align="center"><td>Spring+DWR+lucence搜索引擎 ，无版权，请任意修改，mailto：sky5520@126.com</td></tr>
		</table>
	</div>
</body>
</html>
