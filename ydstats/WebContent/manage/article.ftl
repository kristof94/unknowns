<@page.mhead title="新闻管理">
<link href="../calendar/calendar-system.css" rel="stylesheet" type="text/css" />
<link href="../calendar/calendar-win2k-cold-1.css" rel="stylesheet" type="text/css" />
<script src="../calendar/calendar.js" type="text/javascript"></script>
<script src="../calendar/calendar-setup.js" type="text/javascript"></script>
<script src="../calendar/calendar-cn.js" type="text/javascript"></script>
<script type="text/javascript">
var typeName = {"tjxx":"统计信息","tjdt":"统计动态","tjfx":"统计分析","tjgb":"统计公报","zwgk":"政务公开"};
var typeMap = {"tjxx":new Array("tjdt","tjfx","tjgb"),"zwgk":new Array()};
function updateSlaveType(masterTypeId) {
	var list = typeMap[masterTypeId];
	if(list && list.length>0) {
		$j("#slaveTypeId").empty();
		var temp = $j("<option></option>");
		$j("#slaveTypeId").append(temp.clone().text("选择子分类"));
		for(var i = 0; i<list.length; i++) {
			var l = list[i];
			$j("#slaveTypeId").append(temp.clone().val(l).text(typeName[l]));
		}
	} else {
		$j("#slaveTypeId").html("<option>该分类没有子分类</option>");
	}
}

function lastcheckform() {
if(submitForm()) return true;
else return false;
}
</script>
</@page.mhead>
<@page.mbody>
<#assign typeName={"tjxx":"统计信息","tjdt":"统计动态","tjfx":"统计分析","tjgb":"统计公报","zwgk":"政务公开"}/>
<#assign typeMap={"tjxx":["tjdt","tjfx","tjgb"],"zwgk":[]}/>
<br/>
<form id="from" action="sou.do" method="post" onsubmit="return checkform(this)">
<#if info??><input type="hidden" name="id" value="${info.id}"/></#if>
<table align="center" class="biankuang">
<tr><td colspan="2" class="gray_15"><div class="black"><a href="#" onclick="back(); return false;">[返回]</a>文章管理</div><div class="div_txt yellow" style="display:inline;"> --> 文章信息</div></td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">标题</td><td><input type="text" name="title" c_min_max="1,100" size="100"<#if info??> value="${info.title}"</#if>/></td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">主分类</td><td><select id="masterTypeId" onchange="updateSlaveType(this.value);">
<#list typeMap?keys as k>
<option value='${k}'>${typeName[k]}</option>
</#list>
</select></td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">子分类</td><td><select id="slaveTypeId">
<#list typeMap?keys as k>
<option value='${k}'>${typeName[k]}</option>
</#list>
</select></td></tr>
<tr><td width="10%" align="right" nowrap="nowrap">内容</td><td>
<#import "/webedit/edit.ftl" as editContent/>
<#if info??>
	<@editContent.webedit path=".." formid="from" messagename="content" messagevalue="${info.content!''}" maxchars=1000000 />
<#else>
	<@editContent.webedit path=".." formid="from" messagename="content" maxchars=1000000 />
</#if>
</td></tr>
<tfoot><tr><td align="right" nowrap="nowrap"></td><td><input type="submit" value="提&nbsp;交"/> <input type="reset" value="重&nbsp;置" onclick="clearcontent();"/></td></tr></tfoot>
</table>
</form>
<hr/>
</@page.mbody>