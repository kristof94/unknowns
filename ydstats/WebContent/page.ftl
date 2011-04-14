<#--页面头部-->
<#macro head title="" prefix="英德统计信息网" suffix="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${prefix}${title}${suffix}</title>
<link rel="stylesheet" href="${path}/3.css">
<style type="text/css">
<!--
BODY {SCROLLBAR-FACE-COLOR: #6a7f9a;
SCROLLBAR-HIGHLIGHT-COLOR: #000000;
SCROLLBAR-SHADOW-COLOR: #339966;
SCROLLBAR-3DLIGHT-COLOR: #cccccc;
SCROLLBAR-ARROW-COLOR: #000000;
SCROLLBAR-TRACK-COLOR: #6a7f9a;
SCROLLBAR-DARKSHADOW-COLOR: #000000}
#Layer1 {
	position:absolute;
	width:766px;
	height:391px;
	z-index:1;
	left: 127px;
	top: 243px;
	background-color: #FFFFFF;
	overflow: auto;
}
#Layer2 {
	position:absolute;
	width:229px;
	height:380px;
	z-index:1;
	left: 138px;
	top: 253px;
}
#Layer3 {
	position:absolute;
	width:216px;
	height:388px;
	z-index:1;
	left: 122px;
	top: 245px;
}
#Layer4 {
	position:absolute;
	width:330px;
	height:388px;
	z-index:2;
	left: 352px;
	top: 245px;
}
#Layer5 {
	position:absolute;
	width:198px;
	height:385px;
	z-index:3;
	left: 695px;
	top: 246px;
}
#Layer6 {
	position:absolute;
	width:772px;
	height:385px;
	z-index:1;
	left: 120px;
	top: 250px;
	background-color: #FFFFFF;
	overflow: auto;
}
.STYLE3 {font-size: 10pt; }
.STYLE4 {font-size: 12pt; }
.STYLE5 {font-size: 11pt; }
.STYLE6 {font-size: 11pt; }
.STYLE7 {font-size: 12pt; font-weight: bold; }
.STYLE9 {font-size: 9pt; }
#slayer {
	position:absolute;
	width:795px;
	height:143px;
	z-index:1;
	left: 115px;
	top: 557px;
}
.STYLE10 {font-size: 18px; }
.xfb {FONT-FAMILY: "黑体"; FONT-SIZE: 18px; FONT-STYLE: normal; FONT-WEIGHT: normal; LINE-HEIGHT: 20px; }
-->
</style>
<#nested/>
</head>
</#macro>
<#--页面尾部-->
<#macro body>
<body background="${path}/images/niux-bg.gif">
<table width="800" border="0" cellspacing="1" cellpadding="1" align="center" bgcolor="#000000">
  <tr bgcolor="#FFFFFF">
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div align="left"><img src="${path}/images/wangbiao.jpg" width="300" height="47"></div></td>
        </tr>
        <tr>
          <td width="32%"><img src="${path}/images/biaotou.jpg" width="800" height="120"></td>
      </tr>
      </table>    </td>
  </tr>
  <tr bgcolor="#6a7f9a">
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td width="495" height="32" class="jnfont6">
            <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td><img src="${path}/images/more01.gif" alt="s" width="15" height="14"> <span class="STYLE3"><a href="${path}/">首页</a></span></td>
                <td><img src="${path}/images/more01.gif" width="15" height="14"> <a href="${path}/tjdt/" class="STYLE3">统计动态</a></td>
                <td><img src="${path}/images/more01.gif" width="15" height="14"> <a href="${path}/tjfx/" class="STYLE3">统计分析</a></td>
                <td><img src="${path}/images/more01.gif" width="15" height="14"> <a href="${path}/wjtz/" class="STYLE3">文件通知</a></td>
                <td><img src="${path}/images/more01.gif" width="15" height="14"> <a href="${path}/tjyb/" class="STYLE3">统计月报</a></td>
                <td><img src="${path}/images/more01.gif" width="15" height="14"> <a href="${path}/tjpf/" class="STYLE3">统计普法</a></td>
              </tr>
            </table>
          </td>
          <td width="51" height="32" class="jnfont6"></td>
          <td width="194" height="32">
            <table width="65%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td width="38%"><img src="${path}/images/niux-home.gif" width="40" height="33"></td>
                <td width="35%"><img src="${path}/images/niux-f.gif" width="40" height="33"></td>
                <td width="27%"><img src="${path}/images/niux-mail.gif" width="40" height="33"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<table width="758" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="800" height="10"></td>
  </tr>
</table>
<#nested/>
<table width="800" border="0" cellspacing="1" cellpadding="1" align="center" bgcolor="#000000">
  <tr bgcolor="#5f7189">
    <td height="3" align="center" valign="middle" bgcolor="#5f7189">&nbsp;</td>
  </tr>
  <tr bgcolor="#5f7189">
    <td height="18" bgcolor="#5f7189"><table width="796" border="0">
      <tr>
        <td><div align="center">本网站由英德统计局开发完成</div></td>
      </tr>
      <tr>
        <td><div align="center">版权所有：广东省英德市统计局<br>
        </div></td>
      </tr>
      <tr>
        <td><div align="center">通讯地址：英德市市政府大院 邮政编码：513000 联系电话：0763-2238188</div></td>
      </tr>
    </table></td>
  </tr>
</table>
<blockquote>&nbsp;</blockquote>
</body>
</html>
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