<@page.mhead title="后台管理">
<link id="style_sheet" href="left.css" type="text/css" rel="stylesheet"/>
<script src="jquery.js" type="text/javascript"></script>
</@page.mhead>
<body class="navigatorbar">
<div id="layer1" style="border-right: 0px; border-top: 0px; scrollbar-face-color: #4da1e4; z-index: 1; scrollbar-highlight-color: #ffffff; overflow: auto; border-left: 0px; width: 168px; scrollbar-shadow-color: #4da1e4; scrollbar-arrow-color: #ffffff; border-bottom: 0px; position: absolute; height: 100%; crollbar-base-color: #4da1e4; scrollbar-dark-shadow-color: #4da1e4">
	<table height="100%" cellspacing="1" cellpadding="1" width="100%" bgcolor="#ffffff" border="0">
	<tbody>
    <tr valign="top"><td><table cellspacing="3" cellpadding="0" width="100%" border="0">
		<tbody><tr><td class="dtree">
			<script src="dirtree.js" type="text/javascript"></script>
			<script type="text/javascript">
				var d = new dTree("d");
				d.add(0, -1, '后台管理','','','','');
				d.add(1,0,'文章管理','','','','images/folder.gif','images/folderopen.gif',true);
				d.add(11,1,'文章信息','article.ftl','','','images/page.gif','images/page.gif',true);
				d.add(12,1,'文章列表','list.do','','','images/page.gif','images/page.gif',true);
				document.write(d);
			</script>
		</td></tr></tbody>
	</table></td></tr>
	</tbody>
	</table>
</div>
</body>
</html>