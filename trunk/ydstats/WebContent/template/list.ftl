<#if !path??><#global path = ".."/></#if><@page.head title=" ${typeName[masterTypeId]}"></@page.head>
<@page.body>
<table width="800" height="665" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="#000000">
  <tr bgcolor="#6a7f9a">
    <td width="800" height="663" align="center" valign="top" bgcolor="#5f7189"><table width="789" height="658" border="0">
      <tr>
        <td width="171" rowspan="2" align="left" valign="top" bgcolor="#5f7189"><table width="166" height="258" border="0">
          <tr>
            <td height="26" colspan="2" bgcolor="#F0F0F0"><img src="${path}/images/more3.gif" alt="1" width="20" height="20"> <span class="STYLE7"> <a href="${path}/zwgk/">政务公开</a></span></td>
          </tr>
          <tr>
            <td width="17" align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="24" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">统 计 职 能</span></td>
          </tr>
          <tr>
            <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="24" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">人 员 设 置</span></td>
          </tr>
          <tr>
            <td height="25" colspan="2" bgcolor="#F0F0F0"><img src="${path}/images/more3.gif" alt="1" width="20" height="20"> <span class="STYLE7"><a href="${path}/jgjs/">机关建设</a></span></td>
          </tr>
          <tr>
            <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="24" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">机 关 效 能</span></td>
          </tr>
          <tr>
            <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="24" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">记 律 教 育</span></td>
          </tr>
          <tr>
            <td height="25" colspan="2" bgcolor="#F0F0F0"><img src="${path}/images/more3.gif" alt="1" width="20" height="20"> <span class="STYLE7"><a href="${path}/tjpf/">统计普法</a></span></td>
          </tr>
          <tr>
            <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="24" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">统计法及解析</span></td>
          </tr>
          <tr>
            <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="24" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">以 案 释 法</span></td>
          </tr>
          <tr>
            <td align="center" valign="middle" bgcolor="#FFFFFF"><img src="${path}/images/ra.gif" alt="1" width="7" height="7"></td>
            <td height="16" align="center" valign="middle" bgcolor="#FFFFFF"><span class="STYLE6">普 法 方 案</span></td>
          </tr>
        </table></td>
        <td height="27" bgcolor="#F0F0F0"><img src="${path}/images/mid01.gif" width="11" height="9"> <span class="STYLE5">你现在的位置：</span><span class="STYLE6"><a href="${path}/">英德市统计信息网</a>-&gt;${typeName[masterTypeId]}</span></td>
      </tr>
      <tr>
        <td width="608" height="625" align="center" valign="top" bgcolor="#F0F0F0"><table width="603" border="0" bgcolor="#FFFFFF">
          <#list map?keys as key>
          <tr>
            <td height="24" colspan="2" bgcolor="#F0F0F0"><img src="${path}/images/more01.gif" width="15" height="14"> <span class="STYLE7">${typeName[key]}</span></td>
          </tr>
          <#assign smap = map[key]/><#list smap?keys as k>
          <tr>
            <td width="18"><img src="${path}/images/dot.gif" alt="1" width="10" height="10"></td>
            <td width="575" height="23"><a href="${k?replace(".txt", ".htm")}" class="STYLE6">${smap[k]}</a></td>
          </tr>
          </#list>
          <tr>
            <td height="5" colspan="2">&nbsp;</td>
          </tr>
          </#list>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</@page.body>