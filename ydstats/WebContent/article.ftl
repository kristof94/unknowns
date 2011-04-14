<@page.mhead title="新闻信息">
<link href="calendar/calendar-system.css" rel="stylesheet" type="text/css" />
<link href="calendar/calendar-win2k-cold-1.css" rel="stylesheet" type="text/css" />
<script src="calendar/calendar.js" type="text/javascript"></script>
<script src="calendar/calendar-setup.js" type="text/javascript"></script>
<script src="calendar/calendar-cn.js" type="text/javascript"></script>
<script type="text/javascript">
var typeName = {<#list typeName?keys as key>"${key}":"${typeName[key]}"<#if key_has_next>,</#if></#list>};
var typeMap = {<#list typeMap?keys as key>"${key}":new Array(<#list typeMap[key] as type>"${type}"<#if type_has_next>,</#if></#list>)<#if key_has_next>,</#if></#list>};
function updateSlaveType(masterTypeId) {
	var list = typeMap[masterTypeId];
	if(list && list.length>0) {
		$j("#slaveTypeId").empty();
		var temp = $j("<option></option>");
		$j("#slaveTypeId").append(temp.clone().val("").text("无子分类"));
		for(var i = 0; i<list.length; i++) {
			var l = list[i];
			$j("#slaveTypeId").append(temp.clone().val(l).text(typeName[l]));
		}
	} else {
		$j("#slaveTypeId").html("<option value=''>无子分类</option>");
	}
}

function lastcheckform() {
if(submitForm()) return true;
else return false;
}

function submitinfo() {
	$("from").action = "save.do";
	$("from").target = "_self";
	checkform($("from"));
}

function previewinfo() {
	$("from").action = "preview.do";
	$("from").target = "_blank";
	$("from").submit();
}
</script>
</@page.mhead>
<@page.mbody>
<br/>
<form id="from" action="save.do" method="post">
<#if info??>
<input type="hidden" name="id" value="${info.id}"/>
<input type="hidden" name="masterTypeId" value="${info.masterTypeId!""}"/>
<input type="hidden" name="slaveTypeId" value="${info.slaveTypeId!""}"/>
</#if>
<table align="center" class="biankuang">
<tr><td colspan="2" class="gray_15"><div class="black"><a href="#" onclick="back(); return false;">[返回]</a>文章管理</div><div class="div_txt yellow" style="display:inline;"> --> <#if info??>修改<#else>新增</#if>文章</div></td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">标题</td><td><input type="text" name="title" c_min_max="1,100" size="100"<#if info??> value="${info.title}"</#if>/></td></tr>
<#if info??><tr><td width="10%" align="right" nowrap="nowrap">时间</td><td><input type="text" id="date" name="date" size="20" value="${info.date!""}" readonly="readonly"/>
<img id="f_trigger" src="calendar/calendarIcon.gif" height="16" style="cursor: pointer;" align="absmiddle" alt="点击选择时间" />
<script type="text/javascript">
Calendar.setup({
inputField	:	"date",
ifFormat	:	"%Y-%m-%d %H:%M",
button		:	"f_trigger",
showsTime	:	"true",
timeFormat	:	"24",
align		:	"Br"
});
</script>
</td></tr></#if>
<#if info??>
<tr><td width="10%" align="right" nowrap="nowrap">主分类</td><td>${typeName[info.masterTypeId]!"无主分类"}</td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">子分类</td><td>${typeName[info.slaveTypeId]!"无子分类"}</td></tr>
<#else>
<tr><td width="10%" align="right" nowrap="nowrap">主分类</td><td><select id="masterTypeId" name="masterTypeId" onchange="updateSlaveType(this.value);">
<option value="">无主分类</option>
<#list typeMap?keys as k>
<option value='${k}'>${typeName[k]}</option>
</#list>
</select></td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">子分类</td><td><select id="slaveTypeId" name="slaveTypeId">
<option value="">无子分类</option>
</select></td></tr>
</#if>
<tr><td width="10%" align="right" nowrap="nowrap">内容</td><td>
<#import "/webedit/edit.ftl" as editContent/>
<#if info??>
	<@editContent.webedit path="." formid="from" messagename="content" messagevalue="${info.content!''}" maxchars=1000000 />
<#else>
	<@editContent.webedit path="." formid="from" messagename="content" maxchars=1000000 />
</#if>
</td></tr>
<tfoot><tr><td align="right" nowrap="nowrap"></td><td><input type="button" value="提&nbsp;交" onclick="submitinfo();"/>&nbsp;<input type="button" value="预&nbsp;览" onclick="previewinfo();"/></td></tr></tfoot>
</table>
</form>
<hr/>
</@page.mbody>