<%
'#################################################################
'# 程序名称：逍遥留言本 V1.0
'# 授权方式：免费软件
'# 作者：逍遥浪子
'# 演示及技术支持：http://www.buyok.net/xybook
'# 电子邮箱：buyok@buyok.net
'# 发布时间：2005-4-8
'# 声明：本程序由逍遥浪子独立自主开发，保留所有版权。
'# 各种用途均可免费使用、自由传播，但必须保留程序代码中的版权信息。
'#################################################################

if request("keywords")<>"" then
if checktxt(request("keywords"))<>request("keywords") then
	response.write "<script language='javascript'>"
	response.write "alert('您输入的搜索关键中含有非法字符，请检查后重新输入！');"
	response.write "location.href='javascript:history.go(-1)';"							
	response.write "</script>"	
	response.end
end if
end if
%>
<!--#include file="book_conn.asp"-->



<HTML><HEAD>
<TITLE><%=sitename%></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="description" content="<%=sitename%>">
<meta name="keywords" content="<%=sitename%>">
<link rel="stylesheet" href="../font.css" type="text/css">

</HEAD>
<center>
  <!--#include file="book_top1.asp"-->
  <table width="980" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td colspan="2" height="5"></td>
    </tr>
    <tr bgcolor="ECECEC">
      <td colspan="2" height="10"></td>
    </tr>
    <tr>
      <td width="230" bgcolor="ECECEC" height="450" valign="top"><!--#include file="left.asp" --></td>
      <td align="center" valign="top">
        <table width="674" border="0" cellpadding="0" cellspacing="0">
          <tr>            </tr>
          <tr>
            <td><img src="IMAGES/lyb.gif" width="698" height="57"></td>
          </tr>
          <tr>
            <td align="center"><table width="90%"  border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td height="25" valign="bottom">&nbsp; </td>
                </tr>
                <tr>
                  <td>　　亲爱的朋友，首先非常欢迎您深圳市安吉货运有限公司，希望通过网站您对我们有更多的了解，若您需要了解更多的信息，或者对本公司有什么意见、建议，请您告诉我们，我们将对我们的工作予以改善，同时谢谢您对本网站的关心与支持。 若您对我们公司有更多的意见，您可以填写以下的反馈信息表，我们一定会在第一时间解答您的问题。</td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="90%" border=0 cellspacing=0 cellpadding=0 align=center bgcolor="F6F6F8">
                <tr>
                  <td align=center height=25>&nbsp;</td>
                </tr>
                <tr>
                  <td align=center height=25><a href=book_write.asp><img border=0 src=images/write.gif title="我要写留言"></a> &nbsp;&nbsp;&nbsp;&nbsp; <a href=index.asp><img border=0 src=images/read.gif title="留言本首页"></a> </td>
                </tr>
                <tr>
                  <td>
                    <%
set rs=Server.CreateObject("ADODB.RecordSet")
sql="select * from Feedback where online='1' "
keywords=request("keywords")
if keywords<>"" then sql=sql+ " and Comments like '%"&keywords&"%' "
sql=sql + "order by top desc,Postdate desc"
rs.open sql,conn,1
if not (rs.eof and rs.bof) then			'如果有留言时，就显示留言。此行的if与倒数第6行的end if相对应

if pages=0 or pages="" then pages=10		'每页留言条数
rs.pageSize = pages	'每页记录数
allPages = rs.pageCount	'总页数
page = Request("page")	'从浏览器取得当前页
'if是基本的出错处理

If not isNumeric(page) then page=1

if isEmpty(page) or Cint(page) < 1 then
page = 1
elseif Cint(page) >= allPages then
page = allPages 
end if
rs.AbsolutePage = page			'转到某页头部
	Do While Not rs.eof and pages>0
	UserName=rs("UserName")		'用户名
	pic=rs("pic")			'头像
	face=rs("face")			'表情
	Comments=rs("Comments")		'内容
	bad1=split(bad,"/")		'过滤脏话
	for t=0 to ubound(bad1)
	Comments=replace(Comments,bad1(t),"***")
	next
	Replay=rs("Replay")		'回复
	Usermail=rs("Usermail")		'邮件
	url=rs("url")			'主页
	I=I+1				'序号
	temp=RS.RecordCount-(page-1)*rs.pageSize-I+1
	%>
                    <table width="100%" border="0" align="center" cellPadding="3" cellSpacing="1" style="word-break:break-all">
                      <tr>
                        <td vAlign="top" bgColor="#f7f7f7" rowSpan="2" align=center>
                          <table border=0 width=85%>
                            <tr>
                              <td align=center><img src=images/face/pic<%=pic%>.gif border=0></td>
                            </tr>
                            <tr>
                              <td>姓名：<%=UserName%><br>
                        来自：<%=left(rs("ip"),(len(rs("ip"))-2))+"**"%><br>
                        邮件：<a href=mailto:<%=Usermail%>><img src=images/mail.gif border=0 alt="<%=Usermail%>"></a><br>
                        主页：<a href="<%=URL%>" target='_blank'><img src=images/home.gif border=0 alt="<%=URL%>"></a></td>
                            </tr>
                        </table></td>
                        <td bgColor="F6F6F8"><%if rs("top")<>"1" then response.write "[NO."&temp&"]"%>
                            <img border=0 src=images/face/face<%=face%>.gif> 发表于：<%=cstr(rs("Postdate"))%></td>
                      </tr>
                      <tr>
                        <td vAlign="top" bgColor="#ebebeb" onMouseOver="bgColor='#FFffff'" onMouseOut="bgColor='#ebebeb'">
                          <%
	'是否屏蔽留言内容中的html字符
	if html=0 then
	response.write replace(server.htmlencode(Comments),vbCRLF,"<BR>")
	else
	response.write replace(Comments,vbCRLF,"<BR>")
	end if
	%>
                          <br>
                          <br>
                          <%if rs("Replay")<>"" then%>
                          <table cellSpacing="1" cellPadding="3" width="90%" align="center" bgColor="darkgray" border="0">
                            <tr>
                              <td vAlign="top" bgColor="F6F6F8"> <font color=<%=huifucolor%>><%=huifutishi%>：<br>
                                    <%=Replay%></font> </td>
                            </tr>
                          </table>
                          <br>
                          <%end if%>
                        </td>
                      </tr>
                    </table>
                    <table cellSpacing="0" cellPadding="0"  align="center" bgColor="#FFFFFF" border="0">
                      <TR>
                        <TD height=8> </TD>
                      </TR>
                    </TABLE>
                    <%
pages = pages - 1
rs.movenext
if rs.eof then exit do
loop

response.write "<table border=0 align=center><tr><td><form action='index.asp' method='post'>总计留言"&RS.RecordCount&"条 "
if page = 1 then
response.write "<font color=darkgray>首页 前页</font>"
else
response.write "<a href=index.asp?page=1>首页</a> <a href=index.asp?keywords="&keywords&"&page="&page-1&">前页</a>"
end if
if page = allpages then
response.write "<font color=darkgray> 下页 末页</font>"
else
response.write " <a href=index.asp?keywords="&keywords&"&page="&page+1&">下页</a> <a href=index.asp?keywords="&keywords&"&page="&allpages&">末页</a>"
end if
response.write " 第"&page&"页 共"&allpages&"页 &nbsp; 转到第 "
response.write "<select name='page'>"
for i=1 to allpages
response.write "<option value="&i&">"&i&"</option>"
next
response.write "</select> 页 <input type=submit name=go value='Go'><input type=hidden name=keywords value='"&keywords&"'></form></td><td align=right>"
response.write "<form action='index.asp' method='post'><input title='想查找什么内容' type=text name=keywords value='"&keywords&"' size=10 maxlength=20><input type=submit value='搜索' title='给我搜'></form></td></tr></table>"


else
response.write "<table cellSpacing=0 cellPadding=0  align=center bgColor=#FFFFFF border=0><TR><TD height=120 align=center>"
if keywords="" then response.write "暂时没有留言" else response.write "抱歉，没有找到您要查到的内容<br><br><a href='javascript:history.go(-1)'>返回上一页</a>" end if
response.write "</TD></TR></TABLE>"
end if
%>
                  </td>
                </tr>
            </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="1" bgcolor="#CCCCCC"></td>
  </tr>
</table>
<!--#include file="book_down.asp"-->
</center>

</html>