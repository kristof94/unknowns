<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function tourl() {
  	var url = document.getElementById("url").value;
  	if(url=="") {
  		alert("请输入地址");
  	} else {
  		document.getElementById("wapiframe").src = "./wtw?url="+encodeURIComponent(url);
  	}
  }
  </script>
</head>
<body>
<input type="text" id="url" size="120" value="http://" />
<input type="button" value="wap地址" onclick="tourl();" />
<hr />
<iframe id="wapiframe" src="wtw" width="100%" height="100%"></iframe>
</body>
</html>