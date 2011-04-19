<#if !path??><#global path = ".."/></#if><@page.head title=" ${info.title?html}"></@page.head>
<@page.body>
<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px; ">
  <tr>
    <td width="182" align="left" valign="top"><table width="180" border="0" cellspacing="0" cellpadding="0">
<@page.list/>
    </table></td>
    <td width="23">&nbsp;</td>
    <td width="799" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m04">
            <tr>
              <td width="511" class="m01_zi">当前位置 &gt; ${typeName[info.masterTypeId]!""}<#if info.slaveTypeId??> &gt; ${typeName[info.slaveTypeId]!""}</#if></td>
              <td width="290" align="right">&nbsp;</td>
              <td width="11" align="right">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td class="m05"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
          <tr>
            <td height="30">&nbsp;</td>
          </tr>
          <tr>
            <td width="90%" class="title"><strong>${info.title}</strong></td>
            </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td style="line-height:24px;"><p>${info.content}</p></td>
          </tr>
          
        </table></td>
      </tr>
  
    </table></td>
  </tr>
</table>
</@page.body>