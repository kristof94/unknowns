<#if !path??><#global path = "."/></#if><@page.head>
<meta name="Keywords" content="英德,统计,信息" />
<meta name="Description" content="广东省英德市统计局对外发布的统计信息网。" />
</@page.head>
<@page.body>
<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:auto; margin-top:10px;">
  <tr>
    <td width="814" align="left" valign="top"><table width="812" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="807"><table width="812" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="400"><script type=text/javascript>
var pic_width=400; //图片宽度
var pic_height=300; //图片高度
var button_pos=4; //按扭位置 1左 2右 3上 4下
var stop_time=6000; //图片停留时间(1000为1秒钟)
var show_text=0; //是否显示文字标签 1显示 0不显示
var txtcolor="000000"; //文字色
var bgcolor="FFFFFF"; //背景色
var imag=new Array();
var link=new Array();
var text=new Array();
 imag[1]="images/new1.jpg";
 link[1]="#";
 text[1]="你好";
 imag[2]="images/new2.jpg";
 link[2]="#";
 text[2]="我好";
  imag[3]="images/new3.jpg";
 link[3]="#";
 text[3]="大家好";
  imag[4]="images/new4.jpg";
 link[4]="#";
 text[4]="非常好";
//可编辑内容结束
var swf_height=show_text==1?pic_height+20:pic_height;
var pics="", links="", texts="";
for(var i=1; i<imag.length; i++){
	pics=pics+("|"+imag[i]);
	links=links+("|"+link[i]);
	texts=texts+("|"+text[i]);
}
pics=pics.substring(1);
links=links.substring(1);
texts=texts.substring(1);
document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cabversion=6,0,0,0" width="'+ pic_width +'" height="'+ swf_height +'">');
document.write('<param name="movie" value="focus.swf">');
document.write('<param name="quality" value="high"><param name="wmode" value="opaque">');
document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&pic_width='+pic_width+'&pic_height='+pic_height+'&show_text='+show_text+'&txtcolor='+txtcolor+'&bgcolor='+bgcolor+'&button_pos='+button_pos+'&stop_time='+stop_time+'">');
document.write('<embed src="focus.swf" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&pic_width='+pic_width+'&pic_height='+pic_height+'&show_text='+show_text+'&txtcolor='+txtcolor+'&bgcolor='+bgcolor+'&button_pos='+button_pos+'&stop_time='+stop_time+'" quality="high" width="'+ pic_width +'" height="'+ swf_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
document.write('</object>');
</script></td>
                <td width="8">&nbsp;</td>
                <td width="405" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="left" valign="top"><table width="404" height="300" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="405" height="28"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m01">
                          <tr>
                            <td width="362" class="m01_zi">统计动态</td>
                            <td width="42"><a href="tjdt/index.htm" class="menu_cj02">更多</a></td>
                          </tr>
                        </table></td>
                      </tr>
                      <tr>
                        <td height="271" align="left" valign="top" class="m02"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("tjdt", "", "Y", 9) /><#list map?keys as k>
                          <tr class="news_h">
                            <td class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${s[0]}/${k?replace(".txt", ".htm")}"><#assign title=s[1]/><#if title?length gt 29>${title?substring(0, 29)}...<#else>${title}</#if></a></td>
                          </tr>
</#list>
                        </table></td>
                      </tr>
                    </table></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="8"></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m04">
                  <tr>
                    <td width="166" align="middle" class="m01_zi"><strong>统计职业道德：</strong></td>
                    <td width="644" align="middle"> <marquee width=644 behavior=scroll scrollamount=5 >忠诚统计　乐于奉献　实事求是　不出假数　依法统计　严守秘密　公正透明　服务社会 </marquee>                      </td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td class="m05"><table width="83%" height="151" border="0" align="center" cellpadding="0" cellspacing="8">
                  <tr>
                    <td height="123"><img src="images/s5.jpg" width="150" height="115" /></td>
                    <td><img src="images/s1.jpg" width="150" height="115" /></td>
                    <td><img src="images/s2.jpg" width="150" height="115" /></td>
                    <td><img src="images/s3.jpg" width="150" height="115" /></td>
                    <td><img src="images/s4.jpg" width="150" height="115" /></td>
                    </tr>
                  <tr>
                    <td height="20" align="center"><a href="#">3.12植树活动</a></td>
                    <td align="center"><a href="#">党日活动</a></td>
                    <td align="center"><a href="#">党日活动</a></td>
                    <td align="center"><a href="#">党日活动</a></td>
                    <td align="center"><a href="#">党日活动</a></td>
                  </tr>
                </table></td>
              </tr>
            
            </table></td>
        </tr>
        <tr>
          <td height="8"></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="263" align="left" valign="top"><table width="266" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="266"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m06">
                    <tr>
                      <td width="" class="m01_zi">统计分析</td>
                      <td width="42"><a href="tjfx/index.htm" class="menu_cj02">更多</a></td>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="190" align="left" valign="top" class="m07"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("tjfx", "", "Y", 6) /><#list map?keys as k>
                    <tr class="news_h">
                      <td class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${s[0]}/${k?replace(".txt", ".htm")}"><#assign title=s[1]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                    </tr>
</#list>
                  </table></td>
                </tr>
              </table></td>
              <td width="7">&nbsp;</td>
              <td width="221"><table width="263" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="263"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m066">
                      <tr>
                        <td >
						<div class="m67">
						<ul>
						<li><a href="#" class="menu_cj02 first" onclick="showtj(true); return false;" id="tjgba">统计公报</a></li>
						<li><a href="#" class="menu_cj02" onclick="showtj(false); return false;" id="tjyba">统计月报</a></li>
						</ul>
						</li>
						</div>
<script type="text/javascript">
function showtj(flag) {
	if(flag) {
		document.getElementById("tjgba").className="menu_cj02 first";
		document.getElementById("tjgbd").style.display="";
		document.getElementById("tj").href="tjgb/index.htm";
		document.getElementById("tjyba").className="menu_cj02";
		document.getElementById("tjybd").style.display="none";
	} else {
		document.getElementById("tjgba").className="menu_cj02";
		document.getElementById("tjgbd").style.display="none";
		document.getElementById("tj").href="tjyb/index.htm";
		document.getElementById("tjyba").className="menu_cj02 first";
		document.getElementById("tjybd").style.display="";
	}
}
</script>
						</td>
                        <td width="42"><a href="tjgb/index.htm" class="menu_cj02" id="tj">更多</a></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="190" align="left" valign="top" class="m07"><div id="tjgbd"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("tjsj", "tjgb", "Y", 6) /><#list map?keys as k>
                      <tr class="news_h">
                        <td class="news_h"><span class="yellow">·</span><a href="tjgb/${k?replace(".txt", ".htm")}"><#assign title=map[k]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                      </tr>
</#list>
                  </table></div>
                  <div id="tjybd" style="display:none;"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("tjsj", "tjyb", "Y", 6) /><#list map?keys as k>
                      <tr class="news_h">
                        <td class="news_h"><span class="yellow">·</span><a href="tjyb/${k?replace(".txt", ".htm")}"><#assign title=map[k]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                      </tr>
</#list>
                  </table></div></td>
                </tr>
              </table></td>
              <td width="7">&nbsp;</td>
              <td width="264" align="left" valign="top"><table width="263" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="263"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m06">
                      <tr>
                        <td width="" class="m01_zi">文件通知</td>
                        <td width="42"><a href="wjtz/index.htm" class="menu_cj02">更多</a></td>
                      </tr>
                  </table></td>
                </tr>
                <tr>
                  <td height="190" align="left" valign="top" class="m07"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("wjtz", "", "Y", 6) /><#list map?keys as k>
                      <tr class="news_h">
                        <td class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${s[0]}/${k?replace(".txt", ".htm")}"><#assign title=s[1]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                      </tr>
</#list>
                  </table></td>
                </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="8"></td>
        </tr>
        <tr>
          <td><img src="images/banner.jpg" width="811" height="75" /></td>
        </tr>
        <tr>
          <td height="8"></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="263" align="left" valign="top"><table width="266" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="266"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m06">
                        <tr>
                          <td width="" class="m01_zi">统计园地</td>
                          <td width="42"><a href="tjyd/index.htm" class="menu_cj02">更多</a></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="190" align="left" valign="top" class="m07"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("tjyd", "", "Y", 6) /><#list map?keys as k>
                      <tr class="news_h">
                        <td class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${s[0]}/${k?replace(".txt", ".htm")}"><#assign title=s[1]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                      </tr>
</#list>
                    </table></td>
                  </tr>
              </table></td>
              <td width="7">&nbsp;</td>
              <td width="221"><table width="263" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="263"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m06">
                        <tr>
                          <td width="" class="m01_zi">政务公开</td>
                          <td width="42"><a href="zwgk/index.htm" class="menu_cj02">更多</a></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="190" align="left" valign="top" class="m07"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("zwgk", "", "Y", 6) /><#list map?keys as k>
                      <tr class="news_h">
                        <td class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${s[0]}/${k?replace(".txt", ".htm")}"><#assign title=s[1]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                      </tr>
</#list>
                    </table></td>
                  </tr>
              </table></td>
              <td width="7">&nbsp;</td>
              <td width="264" align="left" valign="top"><table width="263" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="263"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="m06">
                        <tr>
                          <td width="" class="m01_zi">普查之窗</td>
                          <td width="42"><a href="pczc/index.htm" class="menu_cj02">更多</a></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="190" align="left" valign="top" class="m07"><table width="91%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:5px;">
<#assign map = getList("pczc", "", "Y", 6) /><#list map?keys as k>
                      <tr class="news_h">
                        <td class="news_h"><span class="yellow">·</span><#assign s=map[k]/><a href="${s[0]}/${k?replace(".txt", ".htm")}"><#assign title=s[1]/><#if title?length gt 17>${title?substring(0, 17)}...<#else>${title}</#if></a></td>
                      </tr>
</#list>
                    </table></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
    <td width="10">&nbsp;</td>
    <td width="183" align="right" valign="top"><table width="180" border="0" cellspacing="0" cellpadding="0">
<@page.list/>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="left" class="r_01">友情链接</td>
          </tr>
          <tr>
            <td class="r_02"><table width="147" border="0" align="center" cellspacing="5" style="margin:auto; margin-top:8px; margin-bottom:8px;">
                  <tr>
                    <td><a href="http://www.yingde.gov.cn/" target="_blank"><img src="images/logo01.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.stats.gov.cn/" target="_blank"><img src="images/logo02.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.gdstats.gov.cn/" target="_blank"><img src="images/logo03.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.qystats.gov.cn/" target="_blank"><img src="images/logo04.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.gdqygs.gov.cn/sub/ydhd/" target="_blank"><img src="images/logo05.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.yingde.gov.cn/web/price/index.jsp" target="_blank"><img src="images/logo06.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.ydyzly.cn/" target="_blank"><img src="images/logo07.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://210.76.64.44:7001/bjstat_web/login.do" target="_blank"><img src="images/logo08.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.teayq.cn/" target="_blank"><img src="images/logo09.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
                  <tr>
                    <td><a href="http://www.baojinggong.com.cn/" target="_blank"><img src="images/logo10.jpg" width="141" height="31" border="0" /></a></td>
                  </tr>
              </table></td>
          </tr>
        </table></td>
      </tr>
      
    </table></td>
  </tr>
</table>
</@page.body>