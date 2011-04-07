<#--页面头部-->
<#macro head title="" prefix="英德统计信息网" suffix="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${prefix}${title}${suffix}</title>
<link rel="stylesheet" href="3.css">
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
<body background="images/niux-bg.gif">
<#nested/>
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