<#if !path??><#global path = ".."/></#if><@page.head title=" ${info.title?html}"></@page.head>
<@page.body>
<table width="800" height="505" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#000000">
  <tr bgcolor="#6a7f9a">
    <td width="800" height="100" align="center" valign="top" bgcolor="#5f7189"><table width="789" height="490" border="0" bgcolor="#FFFFFF">
      <tr>
        <td width="492" height="26"><span class="STYLE6"><strong>您现在的位置</strong>：<a href="${path}/">首页</a><#if info.masterTypeId??>-&gt;<a href="${path}/${info.masterTypeId}/">${typeName[info.masterTypeId]!""}</a></#if><#if info.slaveTypeId??>-&gt;<a href="${path}/${info.slaveTypeId}/">${typeName[info.slaveTypeId]!""}</a></#if></span></td>
      </tr>
      <tr>
        <td height="35"><div align="center"><p align="center" style="text-align:center;text-indent:24.1pt"><span style="font-family: 黑体; letter-spacing: 1.5pt"><font size="4">${info.title}</font></span></p></div></td>
      </tr>
      <tr>
        <td height="25"><p align="center" style="text-align:center;text-indent:24.1pt">${info.date}</p></td>
      </tr>
      <tr>
        <td height="25">&nbsp;</td>
      </tr>
      <tr>
        <td align="left" valign="top">${info.content!"没东西？！"}</td>
      </tr>
    </table></td>
  </tr>
</table>
</@page.body>