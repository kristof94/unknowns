<%
Response.Buffer=True
DB="db/xybook.asp"		'数据库路径和文件名，请修改引号中的文件名
on error resume next		'这一句很关键，不能删除
set conn=server.createobject("adodb.Connection")
connstr="provider=Microsoft.Jet.OLEDB.4.0;Data Source=" & Server.MapPath(DB)
conn.Open connstr

set book=conn.execute("select * from book_setup")
sitename=book("sitename")
admin=book("admin")
password=book("password")
maxlength=cint(book("maxlength"))
view=book("view")
pages=cint(book("pages"))
html=cint(book("html"))
mailyes=cint(book("mailyes"))
book_jianju=cint(book("book_jianju"))
huifutishi=cstr(book("huifutishi"))
huifucolor=cstr(book("huifucolor"))
bad=cstr(book("bad"))
set book=nothing


'检查用户输入的内容中是否含有非法字符
function checktxt(txt)
chrtxt="33|34|35|36|37|38|39|40|41|42|43|44|47|58|59|60|61|62|63|91|92|93|94|96|123|124|125|126|128"
chrtext=split(chrtxt,"|")
for c=0 to ubound(chrtext)
txt=replace(txt,chr(chrtext(c)),"")
next
checktxt=txt
end function

%>