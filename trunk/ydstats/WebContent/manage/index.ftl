<@page.mhead title="后台管理"/>
<#if admin_login?exists>
<frameset cols="140,10,*" frameborder="no" border="0" framespacing="0" id="frm">
	<frame id="frmleft" name="frmleft" src="left.ftl" scrolling="no"/>
	<frame id="frmcontrol" name="frmcontrol" src="control.html" scrolling="0" noresize marginheight="0" marginwidth="0"/>
	<frame id="frmmain" name="frmmain" src="article.ftl"/>
</frameset>
<#else>
<link rel="stylesheet" href="manage.css" type="text/css"/>
<script type="text/javascript">
var is_doing_login = false;
function $(id) {
	return document.getElementById(id);
}

function login(){
	var loginmsg = $("loginmsg");
	loginmsg.style.display = "none";
	var admin_username = $("admin_username");
	var username = admin_username.value;
	var admin_password = $("admin_password");
	var password = admin_password.value;
	if(username.length<1) {
		loginmsg.innerHTML = "请输入用户名";
		loginmsg.style.display = "block";
		admin_username.focus();
	} else if(password.length<1) {
		var loginmsg = $("loginmsg");
		loginmsg.innerHTML = "请输入密码";
		loginmsg.style.display = "block";
		admin_password.focus();
	} else {
		loginmsg.innerHTML = "正在登录...";
		loginmsg.style.display = "block";
		if(!is_doing_login){
			is_doing_login=true;
			try {
				$j.get("../login.do",{username:username, password:password, t:Math.random()},function(result) {
					is_doing_login=false;
					switch(result) {
					case "0":
						loginmsg.innerHTML = "登录成功，正在验证用户权限...";
						//location.href = "../?t="+Math.random();
						location.href = location.href;
						break;
					case "1":
						loginmsg.innerHTML = "登录失败，用户名或密码错误";
						admin_username.focus();
						break;
					case "2":
						loginmsg.innerHTML = "登录失败，登录时出现意外错误";
						admin_username.focus();
						break;
					default:
						loginmsg.innerHTML = str;
					}
				});
			} catch(e) {
				loginmsg.innerHTML = "登录失败，登录时出现意外错误:"+e;
				is_doing_login=false;
			}
		}
	}
}

function checkey(keycode) {
	if(keycode==13) {
		login();
		return false;
	} else {
		return true;
	}
}
</script>
<body>
<table class="logintb">
<tr>
	<td colspan="2" class="loginbox">
		<p>后台管理</p>
	</td>
</tr>
<tr>
	<td class="login">
		<p>该后台仅提供给管理员使用<br/>游客请止步</p>
	</td>
	<td>
		<p class="loginmsg" id="loginmsg"<#if !sysmsg?exists> style="display:none"</#if>>${sysmsg?default('')}</p>
		<p class="logintitle">用户名:</p>
		<p class="loginform"><input id="admin_username" tabindex="1" type="text" class="txt" onkeydown="return checkey(event.keyCode);" onfocus="this.select();" /></p>
		<p class="logintitle">密　码:</p>
		<p class="loginform"><input id="admin_password" tabindex="2" type="password" class="txt" onkeydown="return checkey(event.keyCode);" onfocus="this.select();" /></p>
		<p class="loginnofloat"><input value="提交" tabindex="3" type="button" class="btn" onclick="login();" /></p>
	</td>
</tr>
</table>
<script type="text/javascript">$("admin_username").focus();</script>
</body>
</#if>
</html>