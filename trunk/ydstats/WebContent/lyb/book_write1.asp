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
%>
<!--#include file="book_conn.asp"-->
<%
if request("send")="ok" then

	username=trim(request.form("username"))
	usermail=trim(request.form("usermail"))

	if username="" or request.form("Comments")="" then
	response.write "<script language='javascript'>"
	response.write "alert('填写资料不完整，请检查后重新输入！');"
	response.write "location.href='javascript:history.go(-1)';"
	response.write "</script>"
	response.end
	end if

	if checktxt(request.form("username"))<>request.form("username") then
	response.write "<script language='javascript'>"
	response.write "alert('您输入的用户名中含有非法字符，请检查后重新输入！');"
	response.write "location.href='javascript:history.go(-1)';"							
	response.write "</script>"	
	response.end
	end if

	if mailyes=0 then		'邮箱为必填时检查邮箱是否合法

	if checktxt(request.form("usermail"))<>request.form("usermail") then
	response.write "<script language='javascript'>"
	response.write "alert('您输入的邮箱中含有非法字符，请检查后重新输入！');"
	response.write "location.href='javascript:history.go(-1)';"							
	response.write "</script>"	
	response.end
	end if

	if Instr(usermail,".")<=0 or Instr(usermail,"@")<=0 or len(usermail)<10 or len(usermail)>50 then
	response.write "<script language='javascript'>"
	response.write "alert('您输入的电子邮件地址格式不正确，请检查后重新输入！');"
	response.write "location.href='javascript:history.go(-1)';"							
	response.write "</script>"	
	response.end
	end if

	end if

	if len(request.form("Comments"))>maxlength then
	response.write "<script language='javascript'>"
	response.write "alert('留言内容太长了，请不要超过"&maxlength&"个字符！');"
	response.write "location.href='javascript:history.go(-1)';"
	response.write "</script>"
	response.end
	end if

	set rs=Server.CreateObject("ADODB.RecordSet")
	sql="select * from Feedback where online='1' order by Postdate desc"
	rs.open sql,conn,1,3

			rs.Addnew
			rs("username")=Request("username")
			rs("comments")=Request("comments")
			rs("usermail")=Request("usermail")
			rs("face")=Request("face")
			rs("pic")=Request("pic")
			rs("url")=Request("url")
			rs("qq")=Request("qq")
			view=cstr(view)
			if view<>"0" then view="1"
			rs("online")=view
			rs("IP")=Request.serverVariables("REMOTE_ADDR")
			rs.Update
		rs.close
		set rs=nothing
	response.write "<script language='javascript'>"
	if view="0" then
	response.write "alert('留言提交成功，留言须经管理员审核才能发布。');"
	else
	response.write "alert('留言提交成功，单击“确定”返回留言列表！');"
	end if
	response.write "location.href='index.asp';"	
	response.write "</script>"
	response.end
end if
%>



<HTML><HEAD>
<TITLE><%=sitename%></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="description" content="<%=sitename%>">
<meta name="keywords" content="<%=sitename%>">
<link rel="stylesheet" href="../font.css" type="text/css">

</HEAD>
<center>
  <!--#include file="book_top1.asp"-->
  <table width="949" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    </tr>
  <tr>
    <td width="233" align="center" valign="top"><!--#include file="link.asp" -->
<!--#include file="left2.asp" --></td>
    <td width="716" valign="top"><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#EAEAEA">
      <tr>
        <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" bgcolor="#FFFFFF"><img src="IMAGES/lyb.gif" width="705" height="41"></td>
          </tr>
        </table>
          <table width="716" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                <tr> </tr>
                <tr>
                  <td valign="top"><table width="716"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="300" valign="top" background="../images/index_left_27.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td height="300" valign="top" background="../images/index_left_27.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td align="center"><img src="../glt/right_top.gif" width="603" height="1"></td>
                                  </tr>
                                  <tr> </tr>
                                  <tr>
                                    <td><table width="98%"  border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                          <td align="center"><table width="90%"  border="0" cellspacing="2" cellpadding="2">
                                              <tr>
                                                <td height="25" valign="bottom"> 　　親愛的朋友，首先非常歡迎您深圳市和运运输实业有限公司，希望通過網站您對我們有更多的瞭解，若您需要瞭解更多的資訊，或者對本公司有什麽意見、建議，請您告訴我們，我們將對我們的工作予以改善，同時謝謝您對本網站的關心與支援。 若您對我們公司有更多的意見，您可以填寫以下的反饋資訊表，我們一定會在第一時間解答您的問題。</td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                              </tr>
                                          </table>
                                          <p>&nbsp;</p></td>
                                        </tr>
                                    </table></td>
                                  </tr>
                                  <tr>
                                    <td><table width="98%" border=0 align=center cellpadding=0 cellspacing=0>
                                        <tr>
                                          <td align=center height=50> <img border=0 src=images/write.gif> &nbsp;&nbsp;&nbsp;&nbsp; <a href=index.asp><img border=0 src=images/read.gif title="我要看留言"></a> </td>
                                        </tr>
                                        <tr>
                                          <td>
                                            <form action=book_write.asp method=post name="book">
                                              <table cellSpacing="1" borderColorDark="#ffffff" cellPadding="4" width="100%" align="center" borderColorLight="#000000" border="0">
                                                <tr bgColor="#F3F3F3">
                                                  <td   width="20%" align=right> 您的姓名：</td>
                                                  <td ><input type=text name="UserName" size="30" maxlength=16>
                                                      <font color="#FF0000">*</font></td>
                                                </tr>
                                                <tr bgColor="#F3F3F3">
                                                  <td   width="20%" align=right>您您的郵箱：</td>
                                                  <td ><input type=text name="UserMail" size="30"  maxlength=50>
                                                      <%if mailyes=0 then%>
                                                      <font color="#FF0000">*</font>
                                                      <%end if%></td>
                                                </tr>
                                                <tr bgColor="#F3F3F3">
                                                  <td   width="20%" align=right>您的網站：</td>
                                                  <td><input type=text value="http://" name="url" size="30"  maxlength=100></td>
                                                </tr>
                                                <tr bgColor="#F3F3F3">
                                                  <td   width="20%" align=right>其他聯繫方式：</td>
                                                  <td><input type=text value="" name="QQ" size="30"  maxlength=100>
                                            （如手机、固定电话、QQ、MSN等）</td>
                                                </tr>
                                                <tr bgColor="#F3F3F3">
                                                  <td   width="20%" align=right>留言内容：<br>
                                                      <font color=red>（<%=maxlength%>字以内）</font></td>
                                                  <td><textarea name="Comments" rows="6" cols="60" style="overflow:auto;"></textarea></td>
                                                </tr>
                                                <tr bgColor="#F3F3F3">
                                                  <td   width="20%" align=right>請選擇表情：</td>
                                                  <td>
                                                    <input type="radio" value="1" name="face" checked>
                                                    <img border=0 src="images/face/face1.gif">
                                                    <input type="radio" value="2" name="face">
                                                    <img border=0 src="images/face/face2.gif">
                                                    <input type="radio" value="3" name="face">
                                                    <img border=0 src="images/face/face3.gif">
                                                    <input type="radio" value="4" name="face">
                                                    <img border=0 src="images/face/face4.gif">
                                                    <input type="radio" value="5" name="face">
                                                    <img border=0 src="images/face/face5.gif">
                                                    <input type="radio" value="6" name="face">
                                                    <img border=0 src="images/face/face6.gif">
                                                    <input type="radio" value="7" name="face">
                                                    <img border=0 src="images/face/face7.gif">
                                                    <input type="radio" value="8" name="face">
                                                    <img border=0 src="images/face/face8.gif">
                                                    <input type="radio" value="9" name="face">
                                                    <img border=0 src="images/face/face9.gif">
                                                    <input type="radio" value="10" name="face">
                                                    <img border=0 src="images/face/face10.gif"> <br>
                                                    <input type="radio" value="11" name="face">
                                                    <img border=0 src="images/face/face11.gif">
                                                    <input type="radio" value="12" name="face">
                                                    <img border=0 src="images/face/face12.gif">
                                                    <input type="radio" value="13" name="face">
                                                    <img border=0 src="images/face/face13.gif">
                                                    <input type="radio" value="14" name="face">
                                                    <img border=0 src="images/face/face14.gif">
                                                    <input type="radio" value="15" name="face">
                                                    <img border=0 src="images/face/face15.gif">
                                                    <input type="radio" value="16" name="face">
                                                    <img border=0 src="images/face/face16.gif">
                                                    <input type="radio" value="17" name="face">
                                                    <img border=0 src="images/face/face17.gif">
                                                    <input type="radio" value="18" name="face">
                                                    <img border=0 src="images/face/face18.gif">
                                                    <input type="radio" value="19" name="face">
                                                    <img border=0 src="images/face/face19.gif">
                                                    <input type="radio" value="20" name="face">
                                                    <img border=0 src="images/face/face20.gif"> </td>
                                                </tr>
                                                <tr bgColor="#F3F3F3">
                                                  <td width="20%" align=right>请选择头像：</td>
                                                  <td>
                                                    <input type="radio" value="1" name="pic" checked>
                                                    <img border=0 src="images/face/pic1.gif" width=60>
                                                    <input type="radio" value="2" name="pic">
                                                    <img border=0 src="images/face/pic2.gif" width=60>
                                                    <input type="radio" value="3" name="pic">
                                                    <img border=0 src="images/face/pic3.gif" width=60>
                                                    <input type="radio" value="4" name="pic">
                                                    <img border=0 src="images/face/pic4.gif" width=60>
                                                    <input type="radio" value="5" name="pic">
                                                    <img border=0 src="images/face/pic5.gif" width=60> <br>
                                                    <input type="radio" value="6" name="pic">
                                                    <img border=0 src="images/face/pic6.gif" width=60>
                                                    <input type="radio" value="7" name="pic">
                                                    <img border=0 src="images/face/pic7.gif" width=60>
                                                    <input type="radio" value="8" name="pic">
                                                    <img border=0 src="images/face/pic8.gif" width=60>
                                                    <input type="radio" value="9" name="pic">
                                                    <img border=0 src="images/face/pic9.gif" width=60>
                                                    <input type="radio" value="10" name="pic">
                                                    <img border=0 src="images/face/pic10.gif" width=60> </td>
                                                </tr>
                                                <tr align="center" bgColor="#F3F3F3">
                                                  <td colSpan="2"><input type="submit" value="提交留言" name="Submit">
                                                      <input type="reset" value="重新填寫" name="Submit2">
                                                      <input type=hidden name=send value=ok></td>
                                                </tr>
                                                <tr>
                                                  <td colSpan="2">&nbsp;</td>
                                                </tr>
                                              </table>
                                            </form>
                                        <tr>
                                          <td>
                                        </table></td>
                                  </tr>
                              </table></td>
                            </tr>
                        </table></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td colspan="2">&nbsp;</td>
                </tr>
          </table></td></tr>
      <tr>
        <td bgcolor="#FFFFFF">&nbsp;</td>
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
</body></center>

</html>