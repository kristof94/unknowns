<#if !path??><#global path = ".."/></#if><#if slaveTypeId??><#assign typeId = slaveTypeId/><#else><#assign typeId = masterTypeId/></#if><@page.head title=" ${typeName[typeId]}"/>
<@page.body>
<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
  <tr>
    <td width="182" align="left" valign="top"><table width="180" border="0" cellspacing="0" cellpadding="0">
<@page.list/>
    </table></td>
    <td width="23">&nbsp;</td>
    <td width="799" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m04">
          <tr>
            <td width="511" class="m01_zi">当前位置 &gt; ${typeName[masterTypeId]!""}<#if slaveTypeId??> &gt; ${typeName[slaveTypeId]!""}</#if></td>
            <td width="290" align="right">&nbsp;</td>
            <td width="11" align="right">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td class="m05"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#if slaveTypeId??>
<#assign map = getList(masterTypeId, slaveTypeId) /><#list map?keys as k>
          <tr class="news_h">
            <td width="90%" class="news_h"><span class="yellow">·</span><a href="${k?replace(".txt", ".htm")}">${map[k]}</a></td>
            <td width="10%" class="news_h"></td>
          </tr>
</#list>
<#else>
<#assign map = getList(masterTypeId) /><#list map?keys as k>
          <tr class="news_h">
            <td width="90%" class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${path}/${s[0]}/${k?replace(".txt", ".htm")}">${s[1]}</a></td>
            <td width="10%" class="news_h"></td>
          </tr>
</#list>
</#if>
          <tr>
            <td colspan="2">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
  
    </table></td>
  </tr>
</table>
</@page.body>