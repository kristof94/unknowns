<!--#include file="inc/syscode_article.asp"-->
<%
'请勿改动下面这三行代码
const ChannelID=1
Const ShowRunTime="Yes"
MaxPerPage=20
SkinID=0
PageTitle="首页"
dim rsu
Set rsArticle= Server.CreateObject("ADODB.Recordset")
Set rsPic= Server.CreateObject("ADODB.Recordset")
set rsu= Server.CreateObject("ADODB.Recordset")
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>新大陆国际物流</title>
<link href="sz.css" rel="stylesheet" type="text/css">
<STYLE type=text/css>
BODY {
	FONT-FAMILY: 宋体;
	FONT-SIZE: 9pt;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
TD {
	FONT-FAMILY: 宋体; FONT-SIZE: 9pt
}
.px12 {
	FONT-FAMILY: 宋体; FONT-SIZE: 12px
}
.px13 {
	FONT-FAMILY: 宋体; FONT-SIZE: 13px
}
.px14 {
	FONT-FAMILY: 宋体; FONT-SIZE: 14px
}
.px16 {
	FONT-FAMILY: 宋体; FONT-SIZE: 16px
}
.px18 {
	FONT-FAMILY: 宋体; FONT-SIZE: 18px
}
.px20 {
	FONT-FAMILY: 宋体; FONT-SIZE: 20px
}
.px22 {
	FONT-FAMILY: 宋体; FONT-SIZE: 22px
}
.px24 {
	FONT-FAMILY: 宋体; FONT-SIZE: 24px
}
.px26 {
	FONT-FAMILY: 宋体; FONT-SIZE: 26px
}
.px28 {
	FONT-FAMILY: 宋体; FONT-SIZE: 28px
}
.px30 {
	FONT-FAMILY: 宋体; FONT-SIZE: 30px
}
.lh12 {
	LINE-HEIGHT: 12px
}
.lh14 {
	LINE-HEIGHT: 14px
}
.lh16 {
	LINE-HEIGHT: 16px
}
.lh15 {
	LINE-HEIGHT: 22px
}
.lh18 {
	LINE-HEIGHT: 18px
}
.lh20 {
	LINE-HEIGHT: 20px
}
.lh22 {
	LINE-HEIGHT: 22px
}
.lh24 {
	LINE-HEIGHT: 24px
}
.lh28 {
	LINE-HEIGHT: 28px
}
.lh30 {
	LINE-HEIGHT: 30px
}
.px12-lh20 {
	FONT-FAMILY: 宋体; FONT-SIZE: 12px; LINE-HEIGHT: 20px
}
.px12-lh22 {
	FONT-FAMILY: 宋体; FONT-SIZE: 12px; LINE-HEIGHT: 22px
}
.px14-lh22 {
	FONT-FAMILY: 宋体; FONT-SIZE: 14px; LINE-HEIGHT: 22px
}
.px14-lh24 {
	FONT-FAMILY: 宋体; FONT-SIZE: 14px; LINE-HEIGHT: 24px
}
.px14-lh28 {
	FONT-FAMILY: 宋体; FONT-SIZE: 14px; LINE-HEIGHT: 28px
}
.blue {
	COLOR: #00007f
}
.white {
	COLOR: #fffffe
}
.Gray {
	COLOR: #999999
}
.Gray1 {
	COLOR: #7e7e7e
}
.red {
	COLOR: #ff0000
}
.HEAD {
	COLOR: #fff; FILTER: progid:DXImageTransform.Microsoft.Glow(color=000000,strength=2); FONT-SIZE: 14px; FONT-STYLE: normal; FONT-VARIANT: normal; FONT-WEIGHT: bold; LINE-HEIGHT: normal; WIDTH: 70%
}
.button {
	BACKGROUND: #0069b5; BORDER-TOP: #408fc8 4px solid; COLOR: #fff; FONT-SIZE: 12px; FONT-STYLE: normal; FONT-VARIANT: normal; FONT-WEIGHT: normal; HEIGHT: 25px; LINE-HEIGHT: normal; TEXT-ALIGN: center
}
.nrb {
	BACKGROUND: #f1f1f1; BORDER-BOTTOM: #cccccc 2px solid; BORDER-LEFT: #cccccc 1px solid; BORDER-TOP: #fffffe 5px solid; FONT-SIZE: 12px; HEIGHT: 25px; TEXT-ALIGN: center
}
.M {
	FONT-SIZE: 14px; FONT-STYLE: normal; FONT-VARIANT: normal; FONT-WEIGHT: bold; LINE-HEIGHT: normal
}
INPUT.buttonface {
	FONT-SIZE: 12px
}
FORM {
	MARGIN-BOTTOM: 0px; MARGIN-TOP: 0px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px
}
A:link {
	COLOR: #000000; TEXT-DECORATION: none
}
A:visited {
	COLOR: #000000; TEXT-DECORATION: none
}
A:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A:active {
	COLOR: #ff0000; TEXT-DECORATION: none
}
A.a01:link {
	COLOR: #000099; TEXT-DECORATION: none
}
A.a01:visited {
	COLOR: #000099; TEXT-DECORATION: none
}
A.a01:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a01:active {
	COLOR: #ff0000; TEXT-DECORATION: none
}
A.a02:link {
	COLOR: #fffffe; TEXT-DECORATION: none
}
A.a02:visited {
	COLOR: #fffffe; TEXT-DECORATION: none
}
A.a02:hover {
	COLOR: #fffffe; TEXT-DECORATION: underline
}
A.a02:active {
	COLOR: #fffffe; TEXT-DECORATION: none
}
A.a01:link {
	COLOR: #000099; TEXT-DECORATION: none
}
A.a03:link {
	COLOR: #00007f; TEXT-DECORATION: none
}
A.a03:visited {
	COLOR: #00007f; TEXT-DECORATION: none
}
A.a03:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a03:active {
	COLOR: #ff0000; TEXT-DECORATION: none
}
A.a04:link {
	COLOR: #ff0000; TEXT-DECORATION: none
}
A.a04:visited {
	COLOR: #ff0000; TEXT-DECORATION: none
}
A.a04:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a04:active {
	COLOR: #ff0000; TEXT-DECORATION: none
}
A.a05:link {
	COLOR: #00007f; TEXT-DECORATION: underline
}
A.a05:visited {
	COLOR: #00007f; TEXT-DECORATION: underline
}
A.a05:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a05:active {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a06:link {
	COLOR: #000000; TEXT-DECORATION: underline
}
A.a06:visited {
	COLOR: #000000; TEXT-DECORATION: underline
}
A.a06:hover {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a06:active {
	COLOR: #ff0000; TEXT-DECORATION: underline
}
A.a07:link {
	COLOR: #ff6600; TEXT-DECORATION: underline
}
A.a07:visited {
	COLOR: #ff6600; TEXT-DECORATION: underline
}
A.a07:hover {
	COLOR: #ff6600; TEXT-DECORATION: underline
}
A.a07:active {
	COLOR: #ff6600; TEXT-DECORATION: underline
}
A.path:link {
	COLOR: #fffffe; FILTER: dropshadow(OffX=1, OffY=1,Color=#000000); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path:visited {
	COLOR: #fffffe; FILTER: dropshadow(OffX=1, OffY=1,Color=#000000); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path:active {
	COLOR: #ffccff; FILTER: dropshadow(OffX=1, OffY=1,Color=#000000); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path:hover {
	COLOR: #ffccff; FILTER: dropshadow(OffX=1, OffY=1,Color=#000000); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path1:link {
	COLOR: #000000; FILTER: dropshadow(OffX=1, OffY=1,Color=#FFFFFE); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path1:visited {
	COLOR: #000000; FILTER: dropshadow(OffX=1, OffY=1,Color=#FFFFFE); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path1:active {
	COLOR: #69fdff; FILTER: dropshadow(OffX=1, OffY=1,Color=#000000); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
A.path1:hover {
	COLOR: #69fdff; FILTER: dropshadow(OffX=1, OffY=1,Color=#000000); HEIGHT: 0px; TEXT-DECORATION: none; VERTICAL-ALIGN: middle
}
.letter {
	LETTER-SPACING: 1px; LINE-HEIGHT: 1.5
}
.pic-bk {
	BORDER-BOTTOM: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid
}
.red1 {	COLOR: #ff0000
}
.style25 {
	color: #0000FF;
	font-weight: bold;
}
.style27 {FONT-FAMILY: 宋体; FONT-SIZE: 12px; font-weight: bold; }
</STYLE></head>

<BODY  LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<!--#include file="top.asp" -->
<table width="768" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2"><IMG SRC="images/index_left_24.gif" WIDTH=768 HEIGHT=20 ALT=""></td>
  </tr>
  <tr>
    <td width="168" valign="top" background="images/index_left_30.gif"><!--#include file="xdl_left.asp" --></td>
    <td width="600" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="300" valign="top" background="images/index_left_27.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center"><IMG SRC="images/index_left_26.gif" WIDTH=600 HEIGHT=12 ALT=""></td>
          </tr>
          <tr>
            <td align="center"><img src="xdl/xdl_qywh.gif" width="560" height="31"></td>
          </tr>
          <tr>
            <td><table width="98%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="20">&nbsp;</td>
              </tr>
            </table>              <table width="90%"  border="0" align="center" cellpadding="3" cellspacing="3">
              <tr>
                <td><span class="style25">深圳市新大陆国际物流有限公司</span></td>
              </tr>
              <tr>
                <td valign="top"><span class="px12-lh22">地　址：深圳市南山区蛇口太子路1号新时代广场24L 邮编：518067<br>
电　话：0755—26888885 26888066<br>
传　真：0755—26893112 26893113<br>
电　邮：info@nci-logistics.com<br>
网　址：Http：www.nci-logistics.com</span></td>
              </tr>
              <tr>
                <td><span class="style25">深圳市新大陆集装箱货运有限公司</span></td>
              </tr>
              <tr>
                <td><span class="px12-lh22">地　址：深圳市南山区荔湾路月亮湾花园18-405 邮编：518054<br>
电　话：0755—26453299 26060080<br>
传　真：0755—26391302<br>
电　邮：info@nci-logistics.com</span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2"><IMG SRC="images/index_left_31.gif" WIDTH=768 HEIGHT=36 ALT=""></td>
  </tr>
</table>
<!--#include file="but.asp"-->
</body>
</html>
