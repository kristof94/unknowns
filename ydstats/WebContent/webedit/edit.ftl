<#--应用该编辑器的页面第一行必须加上以下doctype
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
-->

<#--
用到的标签名有：wysiwyg、switchEditorCheckbox、e_mediatyperadio

js的postSubmited全局变量控制是否有提交请求正在处理，postSubmited为true表示有提交请求正在处理
在编辑器里按Ctrl+Enter将会调用submitForm函数，在提交前如果自定义了checkForm函数还会调用该函数，如果checkForm函数最后返回false则不提交请求

部分js函数介绍：

bbcode.js
bbcode2html(str)：将str里的bbcode转换成html
html2bbcode(str)：将str里的html转换成bbcode

common.js
setcookie(cookieName, cookieValue, seconds)：保存cookie
getcookie(name)：获取cookie
isUndefined(variable)：判断对象variable是否未定义
mb_strlen(str)：判断字符串str有多少字节
strlen(str)：计算字符串str的字数，换行当一个字处理
trim(str)：去掉字符串str的前后空格

post.js
getMessage()：返回编辑器里的内容并将值传到messagename所代表的标签里，结果是bbcode方式返回
submitForm()：提交表单，如果返回false则表示不能提交表单
AddText(txt)： 在光标所在位置添加内容
checklength()：显示文章内容的字节数
clearcontent()：清空文章内容
-->
 
<#--
macro webedit的参数定义：
path：该工程相对于站点根目录的地址（强烈建议自定义该值）
formid：该编辑器所在的from的id（强烈建议自定义该值）
messagename：保存编辑内容的文本域的name（强烈建议自定义该值）
messagevalue：编辑窗口默认显示的值
minchars：提交时内容要求的最小字节数，默认是1字节
maxchars：提交时内容要求的最大字节数，默认是2000字节
-->
<#macro webedit path="" formid="postform" messagename="content1" messagevalue="" minchars=1 maxchars=2000>
<link rel="stylesheet" type="text/css" href="${path}/webedit/css/style_common.css" />
<link rel="stylesheet" type="text/css" href="${path}/webedit/css/style_float.css" />
<script src="${path}/webedit/js/common.js" type="text/javascript"></script>
<div id="webedit">
<style type="text/css">
#floatwinnojs { position: static; }
#custominfoarea { right: 10px; left: auto; }
.popupmenu_popup, #e_popup_smilies_menu { *margin-top: 22px; }
* #webedit .popupmenu_popup li { clear: both; float: left; }
* #webedit .fontstyle_menu { overflow: hidden; width: 35px; }
* #webedit .fontstyle_menu ul { width: 70px; }
* #webedit .fontname_menu li { float: none; }
</style>

<div id="floatwinnojs">
<div class="float" id="floatlayout_newthread">
<input type="hidden" name="wysiwyg" id="e_mode" value="1" />
<div style="clear:both;">
<div class="floatbox floatbox1" id="editorbox">
<div class="postbox" id="postbox">
<#--菜单内容-->
<div id="e_controls" class="editorrow">
<#--菜单栏-->
<div class="editor">
<a id="e_switcher" class="plugeditor editormode"><input type="checkbox" id="switchEditorCheckbox" name="switchEditorCheckbox" value="0"  onclick="switchEditor(this.checked?0:1)" /><label for="switchEditorCheckbox">源码</label></a>
<div class="editorbtn"><a id="e_cmd_bold" title="粗体"></a>
<a id="e_popup_simple" title="粗体 斜体 下划线"></a>
<a id="e_popup_fontname" title="字体"></a>
<a id="e_popup_fontsize" title="大小"></a>
<a id="e_popup_forecolor" title="颜色"></a>
<a id="e_popup_justify" title="对齐"></a>
<a id="e_cmd_createlink" title="链接"></a>
<a id="e_cmd_email" title="Email"></a>
<a id="e_cmd_insertimage" title="图片"></a>
<a id="e_popup_media" title="多媒体"></a>
<a id="e_cmd_table" title="表格"></a>
<a id="e_cmd_custom1_flash" class="customedit" title="嵌入 Flash 动画"><img src="${path}/webedit/images/common/bb_flash.gif" title="嵌入 Flash 动画" alt="flash" /></a>
<a id="e_cmd_custom1_qq" class="customedit" title="显示 QQ 在线状态，点这个图标可以和他（她）聊天"><img src="${path}/webedit/images/common/bb_qq.gif" title="显示 QQ 在线状态，点这个图标可以和他（她）聊天" alt="qq" /></a>
<a id="e_popup_tools" title="工具"></a>
</div>
</div><#--菜单栏结束-->
<#--子菜单-->
<div class="editortoolbar">
<#--显示斜体、下划线菜单-->
<div class="popupmenu_popup fontstyle_menu" id="e_popup_simple_menu" style="display: none">
<ul unselectable="on">
<li><a id="e_cmd_italic" title="斜体">斜体</a></li>
<li><a id="e_cmd_underline" title="下划线">下划线</a></li>
</ul>
</div>
<#--显示字体菜单-->
<div class="popupmenu_popup fontname_menu" id="e_popup_fontname_menu" style="display: none">
<ul unselectable="on"><li onclick="discuzcode('fontname', '仿宋_GB2312')" style="font-family: 仿宋_GB2312" unselectable="on">仿宋_GB2312</li><li onclick="discuzcode('fontname', '黑体')" style="font-family: 黑体" unselectable="on">黑体</li><li onclick="discuzcode('fontname', '楷体_GB2312')" style="font-family: 楷体_GB2312" unselectable="on">楷体_GB2312</li><li onclick="discuzcode('fontname', '宋体')" style="font-family: 宋体" unselectable="on">宋体</li><li onclick="discuzcode('fontname', '新宋体')" style="font-family: 新宋体" unselectable="on">新宋体</li><li onclick="discuzcode('fontname', '微软雅黑')" style="font-family: 微软雅黑" unselectable="on">微软雅黑</li><li onclick="discuzcode('fontname', 'Trebuchet MS')" style="font-family: Trebuchet MS" unselectable="on">Trebuchet MS</li><li onclick="discuzcode('fontname', 'Tahoma')" style="font-family: Tahoma" unselectable="on">Tahoma</li><li onclick="discuzcode('fontname', 'Arial')" style="font-family: Arial" unselectable="on">Arial</li><li onclick="discuzcode('fontname', 'Impact')" style="font-family: Impact" unselectable="on">Impact</li><li onclick="discuzcode('fontname', 'Verdana')" style="font-family: Verdana" unselectable="on">Verdana</li><li onclick="discuzcode('fontname', 'Times New Roman')" style="font-family: Times New Roman" unselectable="on">Times New Roman</li></ul>
</div>
<#--显示大小菜单-->
<div class="popupmenu_popup fontsize_menu" id="e_popup_fontsize_menu" style="display: none">
<ul unselectable="on"><li onclick="discuzcode('fontsize', 1)" unselectable="on"><font size="1" unselectable="on">1</font></li><li onclick="discuzcode('fontsize', 2)" unselectable="on"><font size="2" unselectable="on">2</font></li><li onclick="discuzcode('fontsize', 3)" unselectable="on"><font size="3" unselectable="on">3</font></li><li onclick="discuzcode('fontsize', 4)" unselectable="on"><font size="4" unselectable="on">4</font></li><li onclick="discuzcode('fontsize', 5)" unselectable="on"><font size="5" unselectable="on">5</font></li><li onclick="discuzcode('fontsize', 6)" unselectable="on"><font size="6" unselectable="on">6</font></li><li onclick="discuzcode('fontsize', 7)" unselectable="on"><font size="7" unselectable="on">7</font></li></ul>
</div>
<#--显示颜色菜单-->
<div class="popupmenu_popup" id="e_popup_forecolor_menu" style="display: none">
<table cellpadding="0" cellspacing="0" border="0" unselectable="on" style="width: auto;"><tr><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Black')" unselectable="on"><div style="background-color: Black" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Sienna')" unselectable="on"><div style="background-color: Sienna" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkOliveGreen')" unselectable="on"><div style="background-color: DarkOliveGreen" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkGreen')" unselectable="on"><div style="background-color: DarkGreen" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkSlateBlue')" unselectable="on"><div style="background-color: DarkSlateBlue" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Navy')" unselectable="on"><div style="background-color: Navy" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Indigo')" unselectable="on"><div style="background-color: Indigo" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkSlateGray')" unselectable="on"><div style="background-color: DarkSlateGray" unselectable="on"></div></td></tr><tr><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkRed')" unselectable="on"><div style="background-color: DarkRed" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkOrange')" unselectable="on"><div style="background-color: DarkOrange" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Olive')" unselectable="on"><div style="background-color: Olive" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Green')" unselectable="on"><div style="background-color: Green" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Teal')" unselectable="on"><div style="background-color: Teal" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Blue')" unselectable="on"><div style="background-color: Blue" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'SlateGray')" unselectable="on"><div style="background-color: SlateGray" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DimGray')" unselectable="on"><div style="background-color: DimGray" unselectable="on"></div></td></tr><tr><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Red')" unselectable="on"><div style="background-color: Red" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'SandyBrown')" unselectable="on"><div style="background-color: SandyBrown" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'YellowGreen')" unselectable="on"><div style="background-color: YellowGreen" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'SeaGreen')" unselectable="on"><div style="background-color: SeaGreen" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'MediumTurquoise')" unselectable="on"><div style="background-color: MediumTurquoise" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'RoyalBlue')" unselectable="on"><div style="background-color: RoyalBlue" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Purple')" unselectable="on"><div style="background-color: Purple" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Gray')" unselectable="on"><div style="background-color: Gray" unselectable="on"></div></td></tr><tr><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Magenta')" unselectable="on"><div style="background-color: Magenta" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Orange')" unselectable="on"><div style="background-color: Orange" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Yellow')" unselectable="on"><div style="background-color: Yellow" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Lime')" unselectable="on"><div style="background-color: Lime" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Cyan')" unselectable="on"><div style="background-color: Cyan" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DeepSkyBlue')" unselectable="on"><div style="background-color: DeepSkyBlue" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'DarkOrchid')" unselectable="on"><div style="background-color: DarkOrchid" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Silver')" unselectable="on"><div style="background-color: Silver" unselectable="on"></div></td></tr><tr><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Pink')" unselectable="on"><div style="background-color: Pink" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Wheat')" unselectable="on"><div style="background-color: Wheat" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'LemonChiffon')" unselectable="on"><div style="background-color: LemonChiffon" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'PaleGreen')" unselectable="on"><div style="background-color: PaleGreen" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'PaleTurquoise')" unselectable="on"><div style="background-color: PaleTurquoise" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'LightBlue')" unselectable="on"><div style="background-color: LightBlue" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'Plum')" unselectable="on"><div style="background-color: Plum" unselectable="on"></div></td><td class="editor_colornormal" onclick="discuzcode('forecolor', 'White')" unselectable="on"><div style="background-color: White" unselectable="on"></div></td></tr><tr></tr></table>
</div>
<#--显示对齐菜单-->
<div class="popupmenu_popup" id="e_popup_justify_menu" style="display: none">
<ul unselectable="on">
<li><a id="e_cmd_justifyleft" title="居左">居左</a></li>
<li><a id="e_cmd_justifycenter" title="居中">居中</a></li>
<li><a id="e_cmd_justifyright" title="居右">居右</a></li>
</ul>
</div>
<#--显示工具菜单-->
<div class="popupmenu_popup" id="e_popup_tools_menu" style="display: none">
<ul unselectable="on">
<a id="e_cmd_removeformat" title="清除文本格式">清除文本格式</a>
<a id="e_cmd_unlink" title="移除链接">移除链接</a>
<a id="e_cmd_undo" title="撤销">撤销</a>
<a id="e_cmd_redo" title="重做">重做</a>
<a id="e_cmd_checklength" title="字数检查">字数检查</a>
<a id="e_cmd_clearcontent" title="清空内容">清空内容</a>
<br />
<a id="e_cmd_saveData" title="保存数据">保存数据</a>
<a id="e_cmd_loadData" title="恢复数据">恢复数据</a>
</ul>
</div>
</div><#--子菜单结束-->
</div><#--菜单内容结束-->
<#--编辑栏-->
<div class="newediter">
<table cellpadding="0" cellspacing="0" border="0" width="100%" style="table-layout:fixed">
<tr><td><textarea class="autosave max" name="${messagename}" id="e_textarea" tabindex="1" class="txt" style="height:700px">${messagevalue}</textarea></td></tr>
</table>
</div><#--编辑栏结束-->
</div>
</div>
</div>
</div>
<#--显示多媒体菜单-->
<div class="popupmenu_popup" id="e_popup_media_menu" style="width: 280px;display: none" unselectable="on">
<input type="hidden" id="e_mediatype" value="ra"/>
<table cellpadding="4" cellspacing="0" border="0">
<tr class="popupmenu_option">
<td nowrap>
请输入多媒体文件的地址:<br />
<input id="e_mediaurl" style="width: 98%" value="" onkeyup="setmediatype('e')" />
</td>
</tr>
<tr class="popupmenu_option">
<td nowrap>
<label style="float: left; width: 32%"><input type="radio" name="e_mediatyperadio" id="e_mediatyperadio_ra" onclick="$('e_mediatype').value = 'ra'" checked="checked"/>RA</label>
<label style="float: left; width: 32%"><input type="radio" name="e_mediatyperadio" id="e_mediatyperadio_wma" onclick="$('e_mediatype').value = 'wma'"/>WMA</label>
<label style="float: left; width: 32%"><input type="radio" name="e_mediatyperadio" id="e_mediatyperadio_mp3" onclick="$('e_mediatype').value = 'mp3'"/>MP3</label>
<label style="float: left; width: 32%"><input type="radio" name="e_mediatyperadio" id="e_mediatyperadio_rm" onclick="$('e_mediatype').value = 'rm'"/>RM/RMVB</label>
<label style="float: left; width: 32%"><input type="radio" name="e_mediatyperadio" id="e_mediatyperadio_wmv" onclick="$('e_mediatype').value = 'wmv'"/>WMV</label>
<label style="float: left; width: 32%"><input type="radio" name="e_mediatyperadio" id="e_mediatyperadio_mov" onclick="$('e_mediatype').value = 'mov'"/>MOV</label>
</td>
</tr>
<tr class="popupmenu_option">
<td nowrap>
<label style="float: left; width: 32%">宽: <input id="e_mediawidth" size="5" value="400" /></label>
<label style="float: left; width: 32%">高: <input id="e_mediaheight" size="5" value="300" /></label>
</td>
</tr>
<tr class="popupmenu_option">
<td align="center" colspan="2"><input type="button" value="提交" onclick="setmediacode('e')"/> &nbsp; <input type="button" onclick="hideMenu()" value="取消" /></td>
</tr>
</table>
</div>
<#--显示图片菜单-->
<div class="popupmenu_popup" id="e_cmd_insertimage_menu" style="width: 280px; display: none" unselectable="on">
<div class="popupmenu_option" unselectable="on" id="insertimage_www_div">
请输入图片地址:<br /><input id="e_cmd_insertimage_param_url" style="width: 98%;" value="" class="txt" type="text" onkeydown="editorMenuEvent_onkeydown(this);" />
<center><input id="e_cmd_insertimage_submit" value="提交" type="button" onclick="insertimagesubmit()" /> &nbsp; <input onclick="hideMenu();" value="取消" type="button" /></center>
</div>
</div>
<#--初始化编辑器-->
<script src="${path}/webedit/js/post.js" type="text/javascript" reload="1"></script>
<script src="${path}/webedit/js/bbcode.js" type="text/javascript" reload="1"></script>
<script type="text/javascript" reload="1">
function initDomObj() {
	try {
		setDomObj($("${formid}"), $("${formid}").${messagename});
	} catch(e) {
		setTimeout("initDomObj()", 1000);
	}
}
initDomObj();

$('floatlayout_newthread').scrollLeft = 600;

var postminchars = #{minchars};
var postmaxchars = #{maxchars};

var editorid = 'e';
var textobj = $(editorid + '_textarea');
//是否所见即所得模式
var wysiwyg = (is_ie || is_moz || (is_opera >= 9)) && parseInt('1') == 1 ? 1 : 0;
//是否允许转换编辑模式
var allowswitcheditor = parseInt('1');
//是否允许转换表情代码
var allowsmilies = parseInt('0');
var editorcss = '${path}/webedit/css/style_common.css';
var TABLEBG = '#FFF';

var thumbwidth = parseInt(400);
var thumbheight = parseInt(300);
var extensions = '';

var fontoptions = new Array("仿宋_GB2312", "黑体", "楷体_GB2312", "宋体", "新宋体", "微软雅黑", "Trebuchet MS", "Tahoma", "Arial", "Impact", "Verdana", "Times New Roman");
var custombbcodes = new Array();
custombbcodes["flash"] = {'prompt' : '请输入 Flash 动画的 URL:'};
custombbcodes["qq"] = {'prompt' : '请输入显示在线状态 QQ 号码:'};

function cedit() {
	try {
		loadData(1);
	} catch(e) {
		setTimeout('cedit()', 1000);
	}
}

function openEditor() {
	try {
		<#if messagevalue=="">
		newEditor(wysiwyg);
		<#else>
		newEditor(wysiwyg, $("${formid}").${messagename}.value);
		</#if>
		if(editbox) {
			editbox.className = 'autosave max';
		}
	} catch(e) {
		setTimeout('openEditor()', 100);
		return;
	}
	if(!$('floatlayout_newthread').scrollLeft) {
		$('floatlayout_newthread').scrollLeft = 600;
	}
	<#--
	if(!getcookie('disableautosave')) {
		clearInterval(autosaveDatai);
		autosaveData(1);
	}
	-->
	if(is_ie >= 5 || is_moz >= 2) {
		window.onbeforeunload = function () {
			try {
				saveData(wysiwyg ? editdoc.body.innerHTML : textobj.value);
			} catch(e) {}
		};
	}
	<#--if messagevalue=="">cedit();</#if-->
}

openEditor();

</script>


</div>
</div>
</#macro>
