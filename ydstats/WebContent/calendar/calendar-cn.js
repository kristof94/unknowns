Calendar._DN = new Array(
 "星期天",
 "星期一",
 "星期二",
 "星期三",
 "星期四",
 "星期五",
 "星期六",
 "星期天"
);

Calendar._SDN = new Array(
 "Sun",
 "Mon",
 "Tue",
 "Wed",
 "Thu",
 "Fri",
 "Sat",
 "Sun"
);

Calendar._MN = new Array(
 "一月",
 "二月",
 "三月",
 "四月",
 "五月",
 "六月",
 "七月",
 "八月",
 "九月",
 "十月",
 "十一月",
 "十二月"
);

Calendar._SMN = new Array(
 "1月",
 "2月",
 "3月",
 "4月",
 "5月",
 "6月",
 "7月",
 "8月",
 "9月",
 "10月",
 "11月",
 "12月"
);

Calendar._TT = {};
Calendar._TT["INFO"] = "关于..";
Calendar._TT["ABOUT"] ="DHTML Date/Time Selector\n" +
"(c) dynarch.com 2002-2003\n" + // don't translate this this ;-)
"For latest version visit: http://dynarch.com/mishoo/calendar.epl\n" +
"Distributed under GNU LGPL.  See http://gnu.org/licenses/lgpl.html for details." +
"\n\n" +
"language file mender: HuaLide" +
"\n\n" +
"选择日期:\n" +
"- 点击 \xab, \xbb 按钮增减年份\n" +
"- 点击 " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " 按钮增减月份\n" +
"- 在以上位置按住鼠标可以快速选择.";

/*
 * "Date selection:\n" +
 * "- Use the \xab, \xbb buttons to select year\n" +
 * "- Use the " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " buttons to select month\n" +
 * "- Hold mouse button on any of the above buttons for faster selection.";
 */

Calendar._TT["ABOUT_TIME"] = "\n\n" +
"Time selection:\n" +
"- Click on any of the time parts to increase it\n" +
"- or Shift-click to decrease it\n" +
"- or click and drag for faster selection.";

Calendar._TT["PREV_YEAR"] = "上一年(按住鼠标)";
Calendar._TT["PREV_MONTH"] = "上月(按住鼠标)";
Calendar._TT["GO_TODAY"] = "去到今天";
Calendar._TT["NEXT_MONTH"] = "下月(按住鼠标)";
Calendar._TT["NEXT_YEAR"] = "下一年(按住鼠标)";
Calendar._TT["SEL_DATE"] = "选择日期";
Calendar._TT["DRAG_TO_MOVE"] = "拖动";
Calendar._TT["PART_TODAY"] = "(今天)";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["DAY_FIRST"] = "%s显示为第一天";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["WEEKEND"] = "0,6";
Calendar._TT["CLOSE"] = "关闭";
Calendar._TT["TODAY"] = "今天";
Calendar._TT["TIME_PART"] = "Shift-单击、单击、拖动 改变值";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "%Y年%b%e日,%A";
Calendar._TT["WK"] = "周";
Calendar._TT["TIME"] = "时间:";