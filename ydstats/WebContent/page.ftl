<#--页面头部-->
<#macro head prefix="英德统计信息网" title="" suffix="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${prefix}${title}${suffix}</title>
<link href="${path}/style.css" rel="stylesheet" type="text/css" />
<#nested/>
</head>
</#macro>
<#--页面尾部-->
<#macro body>
<div class="bg_body"></div>
<div class="box">
<div class="menu">
<ul>
<li><a href="${path}/index.htm" class="menu_cj" title="网站首页">网站首页</a></li>
<li><a href="${path}/tjdt/index.htm" class="menu_cj" title="统计动态">统计动态</a></li>
<li><a href="${path}/tjfx/index.htm" class="menu_cj" title="统计分析">统计分析</a></li>
<li><a href="${path}/tjgg/index.htm" class="menu_cj" title="统计公告">统计公告</a></li>
<li><a href="${path}/tjnb/index.htm" class="menu_cj" title="统计年报">统计年报</a></li>
<li><a href="${path}/tjyb/index.htm" class="menu_cj" title="统计月报">统计月报</a></li>
<li><a href="${path}/tjyd/index.htm" class="menu_cj" title="统计园地">统计园地</a></li>
<li><a href="${path}/zwgk/index.htm" class="menu_cj" title="政务公开">政务公开</a></li>
<li><a href="${path}/pczc/index.htm" class="menu_cj" title="普查之窗">普查之窗</a></li>
</ul>
</div>
</div>
<#nested/>
<table width="1004" border="0" align="center" cellpadding="0" cellspacing="0"  style="margin-top:10px;">
  <tr>
    <td class="f01">&nbsp;</td>
  </tr>
  <tr>
    <td align="center" valign="top" class="f02">版权所有：广东省英德市统计局 本站地址：www.ydstats.gov.cn<br />
      通讯地址：英德市市政府大院 邮政编码：513000 联系电话：0763-2238188</td>
  </tr>
</table>
</body>
</html>
</#macro>
<#--页面侧边导航-->
<#macro list>
  <tr>
    <td width="180"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" class="r_01"><a href="${path}/tjsj/index.htm" class="menu_cj">统计数据</a></td>
      </tr>
      <tr>
        <td class="r_02"><table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
          <tr>
            <td align="left"><a href="${path}/tjnb/index.htm" class="menu_cj02">统计年报</a></td>
          </tr>
          
        </table>
          <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_04">
            <tr>
              <td align="left"><a href="${path}/tjyb/index.htm" class="menu_cj02">统计月报</a></td>
            </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="8"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" class="r_01"><a href="${path}/tjyd/index.htm" class="menu_cj">统计园地</a></td>
      </tr>
      <tr>
        <td class="r_02"><table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
            <tr>
              <td align="left"><a href="${path}/tjwd/index.htm" class="menu_cj02">统计问答</a></td>
            </tr>
          </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
              <tr>
                <td align="left"><a href="${path}/tjbz/index.htm" class="menu_cj02">统计标准</a></td>
              </tr>
            </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
              <tr>
                <td align="left"><a href="${path}/tjzd/index.htm" class="menu_cj02">统计制度</a></td>
              </tr>
            </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_04">
              <tr>
                <td align="left"><a href="${path}/tjfg/index.htm" class="menu_cj02">统计法规</a></td>
              </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="8"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" class="r_01"><a href="${path}//index.htm" class="menu_cj">政务公开</a></td>
      </tr>
      <tr>
        <td class="r_02"><table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
            <tr>
              <td align="left"><a href="${path}/tjzn/index.htm" class="menu_cj02">统计职能</a></td>
            </tr>
          </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
              <tr>
                <td align="left"><a href="${path}/jgsz/index.htm" class="menu_cj02">机构设置</a></td>
              </tr>
            </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_04">
              <tr>
                <td align="left"><a href="${path}/rysz/index.htm" class="menu_cj02">人员设置</a></td>
              </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
     <td height="8"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" class="r_01"><a href="${path}//index.htm" class="menu_cj">普查之窗</a></td>
      </tr>
      <tr>
        <td class="r_02"><table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
            <tr>
              <td align="left"><a href="${path}/rkpc/index.htm" class="menu_cj02">人口普查</a></td>
            </tr>
          </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_03">
              <tr>
                <td align="left"><a href="${path}/nypc/index.htm" class="menu_cj02">农业普查</a></td>
              </tr>
            </table>
            <table width="160" border="0" cellpadding="0" cellspacing="0" class="r_04">
              <tr>
                <td align="left"><a href="${path}/jjpc/index.htm" class="menu_cj02">经济普查</a></td>
              </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
   <td height="8"></td>
  </tr>
</#macro>
<#--后台管理页面头部-->
<#macro mhead title="" prefix="英德统计信息网-" suffix="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${prefix}${title}${suffix}</title>
<link href="common.css" rel="stylesheet" type="text/css"/>
<script src="common.js" type="text/javascript"></script>
<script src="jquery.js" type="text/javascript"></script>
<#nested/>
</#macro>
<#--后台管理页面尾部-->
<#macro mbody>
<body>
<#nested/>
</body>
</html>
</#macro>