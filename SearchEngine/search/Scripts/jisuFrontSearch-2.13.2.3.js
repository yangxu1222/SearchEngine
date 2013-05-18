/*
 * Coder: Zhu Liang
 * Date: 2013-2-3 13:09
 * JISU.NET ALL RIGHTS RESERVED.
 */
// Global Variables
var request;
var minpage;
var maxpage;
var startindex;
var hasnext;

// Execute Search Function - Important Entrance
function doSearch(type)
{
	baidu("#result").html("");
	baidu("#paging").html("");

	if(type != "paging")
	{
		baidu("#startindex").val("1");
	}

	request = { startindex:1, query:""};
	DWRUtil.getValues(request);

	var search_field = "";
	if(baidu("#content").prop("checked"))
		search_field = baidu("#content").val();
	if(baidu("#title").prop("checked"))
		search_field = baidu("#title").val();
	
	searchService.getSearchResults(request,search_field,fillPage);
}

function fillPage(data)
{
    var list = data.results;
    var tangram_result = baidu("#result");
    var tangram_paging = baidu("#paging");

    tangram_result.html("");
    tangram_paging.html("");

    if(list.length == 0)
    {
        tangram_result.html("<div><h4>抱歉，极速搜索在收录的结果中暂未找到您需要的信息~</h4></div>");
        return;
	}
    
    baidu.array(list).each(function(index,item){
        var tangram_ele = baidu("<div></div>");
        var tangram_a = baidu("<a></a>");
	    tangram_a.attr("href",item.url);
	    tangram_a.attr("target","_blank");
	    tangram_a.addClass("divURL");
	    tangram_a.html(item.title);
	    tangram_ele.append(tangram_a);

	    var tangram_abstract_div = baidu("<div></div>");
	    tangram_abstract_div.addClass("abstract_div");
	    tangram_abstract_div.html(item.abstractContent);
	    tangram_ele.append(tangram_abstract_div);

	    var tangram_index_datetime_div = baidu("<div></div>");
	    tangram_index_datetime_div.addClass("index_datetime_div");
	    tangram_index_datetime_div.html(item.url.substr(7) + "&nbsp;&nbsp;" + item.indexCreateTime);
	    tangram_ele.append(tangram_index_datetime_div);
	    tangram_result.append(tangram_ele);
	    tangram_result.html(tangram_result.html() + "<br/>");
	});

	minpage = data.minpage;
	maxpage = data.maxpage;
	startindex = data.startindex;
	hasnext = data.hasnext;

	if(minpage != 1)
	{
		var tangram_a = baidu("<a></a>");
		tangram_a.attr("href","javascript:paging('"+((minpage-11)*10+1)+"')");
		tangram_a.html("前10页&lt;&lt;");
	    tangram_paging.append(tangram_a);
		tangram_paging.html(tangram_paging.html() + "&nbsp;&nbsp;");
	}

	for(var j=minpage;j<=maxpage;j++)
	{
		if((j-1)*10+1 != startindex)
		{
			if(j<=76)
			{
				var tangram_a = baidu("<a></a>");
				tangram_a.attr("href","javascript:paging('"+((j-1)*10+1)+"')");
				tangram_a.html("第" + j + "页");
				tangram_paging.append(tangram_a);
				tangram_paging.html(tangram_paging.html() + "&nbsp;&nbsp;");
			}
		}
		else
		{
			tangram_paging.html(tangram_paging.html() + "第" + j + "页");
			tangram_paging.html(tangram_paging.html() + "&nbsp;&nbsp;");
		}
	}
				
	if(hasnext == 1 && maxpage <= 76)
	{
		var tangram_a = baidu("<a></a>");
		tangram_a.attr("href","javascript:paging('" +(maxpage*10+1) + "')");
		tangram_a.html("&gt;&gt;后10页");
		tangram_paging.append(tangram_a);
	}
}
		
function paging(newindex)
{
	baidu("#startindex").val(newindex);
	doSearch("paging");
}

function handlekey(src)
{
	if(baidu("#query").val() == "")
		return;
	var intKey = -1;
	if(window.event)
	{
		intKey = event.keyCode;
		if(intKey == 13)
		{
			if(src == "HomePage")
				SubmitMain("");
			else
				Submit("");
		}
	}
}

function getQueryString(name)
{
	var reg = new RegExp("(^|&)" + name + "=(.*?)(&|$)","i");
	var r = window.location.search.substr(1).match(reg);
	if(r != null)
		return decodeURIComponent(r[2]);
	return null;
}

function Submit(reserve)
{
	//var query = encodeURIComponent(baidu("#query").val());
	doSearch("");
}

function SubmitMain(reserve)
{
	var query = encodeURIComponent(baidu("#query").val());
	
	var url = "http://59.72.63.6/search/detail.jsp?wd=" + query + "&src=HomePage";
	//var url = "http://localhost:8080/search/detail.jsp";
	if(baidu.browser.ie)
		window.location.href(url);
	else
		window.location=url;
}

function OnLoad(type)
{
	var src = getQueryString("src");
	if(src == null)
		return;

	if(src == 'HomePage')
	{
		var query = getQueryString("wd");
		if(query != null)
		{
			baidu("#query").val(query);
		}
		Submit(type);
	}
}
function OnLoadMain()
{
    document.all.query.select();
    baidu("#date").html(baidu.date.format(new Date(),"yyyy-MM-dd"));
    tick();
}
