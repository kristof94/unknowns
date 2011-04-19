<@page.mhead title="新闻列表">
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

function generate(action) {
	$("dataform").action = action;
	$("dataform").submit();
}

function del(id, mid, sid) {
if(window.confirm("删除后将不能恢复，确定要删除该文章吗？")) window.location.href = "del.do?id="+id+"&masterTypeId="+mid+"&slaveTypeId="+sid;
}

<#if masterTypeId??>
$j(function() {
updateSlaveType("${masterTypeId}");
$j("#masterTypeId").val("${masterTypeId}");
<#if slaveTypeId??>$j("#slaveTypeId").val("${slaveTypeId}");</#if>
});
</#if>
</script>
</@page.mhead>
<@page.mbody>
<br/>
<form action="list.do" method="get" id="dataform">
<table align="center" class="biankuang">
<tr><td>
查询：
<select id="masterTypeId" name="masterTypeId" onchange="updateSlaveType(this.value);">
<option value="">无主分类</option>
<#list typeMap?keys as key>
<option value="${key}">${typeName[key]}</option>
</#list>
</select>
<select id="slaveTypeId" name="slaveTypeId">
<option value="">无子分类</option>
</select>
<input type="submit" value="查&nbsp;询"/>
<#--
<input type="button" value="生成列表" onclick="generate('glist.do');"/>
<input type="button" value="批量生成" onclick="generate('ginfo.do');"/>
-->
</td></tr>
<#--
-->
<tr><td>
操作：
<a href="#" onclick="generate('gindex.do'); return false;">生成主页</a>
</td></tr>
<#if sysmsg?exists>
<tr><td align="center">
${sysmsg}
</td></tr>
</#if>
</table>
</form>

<hr>
<table class="biankuang" align="center" border="1">

<#if map?exists && map?size gt 0>
<#list map?keys as key>
<tbody><tr onmouseover="this.style.backgroundColor='#E6EFF6'" onmouseout="this.style.backgroundColor='transparent'">
<td><div class="div_txt"><a href="show.do?id=${key}&masterTypeId=${masterTypeId}&slaveTypeId=${slaveTypeId}">${map[key]}</a></div></td>
<td width="1%" nowrap="nowrap"><a href="preview.do?id=${key}&masterTypeId=${masterTypeId}&slaveTypeId=${slaveTypeId}" target="_blank">预览</a></td>
<#--
<td width="1%" nowrap="nowrap"><a href="ginfo.do?id=${key}&masterTypeId=${masterTypeId}&slaveTypeId=${slaveTypeId}">生成</a></td>
-->
<td width="1%" nowrap="nowrap"><a href="#" onclick="del('${key}', '${masterTypeId}', '${slaveTypeId}'); return false;">删除</a></td>
</tr></tbody>
</#list>
<#else>
<tbody><tr><td colspan="2" align="center">没有符合条件的结果</td></tr></tbody>
</#if>

</table>
<hr/>
</@page.mbody>