function $(id) {
	return document.getElementById(id);
}

function back() {
	history.back(-1);
}

/**
 * 用jquery检查表单内容的有效性，使用该函数前需加载jquery.js，如果最后有lastcheckform函数则调用该函数进行最后的检查
 * @param {Form} obj
 * @return {Boolean}
 */
function checkform(frm) {
	if(frm) {
		try {
			var form = $j(frm);
			var checklist = form.find(".c_istrimnotnull");
			for(var i = 0; i<checklist.length; i++) {
				var obj = checklist[i];
				if(obj.value.length==0) {
					alert("请输入必填内容");
					obj.select();
					return false;
				}
			}
			checklist = form.find("[c_min_max]");
			for(var i = 0; i<checklist.length; i++) {
				var obj = checklist[i];
				var c_min_max = obj.getAttribute("c_min_max").split(",");
				var min = parseInt(c_min_max[0]);
				var max = parseInt(c_min_max[1]);
				var len = obj.value.length;
				if(len < min || len > max) {
					alert("限制字数："+min+"到"+max+"字，当前字数："+len+"字");
					obj.select();
					return false;
				}
			}
			checklist = form.find(".c_isposinteger");
			var pattern = /^\d*$/;
			for(var i = 0; i<checklist.length; i++) {
				var obj = checklist[i];
				if(!pattern.test(obj.value)) {
					alert("请输入数字");
					obj.select();
					return false;
				}
			}
			checklist = form.find(".c_isenorint");
			pattern = /^\w*$/;
			for(var i = 0; i<checklist.length; i++) {
				var obj = checklist[i];
				if(!pattern.test(obj.value)) {
					alert("请输入数字或字母");
					obj.select();
					return false;
				}
			}
			checklist = form.find(".c_isascii");
			pattern = /^[\x00-\xff]*$/;
			for(var i = 0; i<checklist.length; i++) {
				var obj = checklist[i];
				if(!pattern.test(obj.value)) {
					alert("请输入数字、字母、半角符号");
					obj.select();
					return false;
				}
			}
			if(typeof lastcheckform == "function") return lastcheckform();
		} catch(e) {
			alert(e.message);
			return confirm("无法验证表单完整性，是否提交表单？");
		}
		return true;
	} else {
		alert("请确定要验证的form");
		return false;
	}
}

function togglecheck(checked) {
	$j(":checkbox").attr("checked", checked);
}

function ischecked() {
	if($j("input:checked[name=id]").length>0) {
		return confirm("删除后将不能恢复，确定要删除所选的数据吗？");
	} else {
		alert("请选择要删除的记录");
		return false;
	}
}