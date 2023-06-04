# xlwj  效力位阶
# zdjg 制定机关
# sxx 时效性
# sxrq 施行日期
# gbrq 公布日期

from bs4 import BeautifulSoup

# 假设html_content是包含HTML内容的字符串
html_content = """
<body><div id="tip" style="display: none;" class="tips">您的浏览器版本过低，暂不支持，请下载最新版本！</div><div class="logo"><ul><a href="./index.html"><li style="float: left;"><img src="./images/logo1.png"></li></a><li><p>欢迎您访问国家法律法规数据库</p></li><li><i class="iconfont" style="color:#DEDEDE; font-size:28px; line-height: 62px;"></i></li></ul></div><div class="title" id="flName">中华人民共和国青藏高原生态保护法</div><div class="header-box"><div class="header"><div class="header-left"><div class="saoma-box"><div class="saoma">扫码下载</div><img class="erweima" id="codeMa" src="https://wb.flk.npc.gov.cn/flfg/PNG/81f3696e815a4052ba3c614a146530b6.png" alt="erweima"></div><div class="header-left-box flxlwj-detail" style="margin-left: 20px;"><p class="header-content">法律效力位阶：<span id="xlwj">法律</span></p><p class="header-content">制定机关：<span id="zdjg">全国人民代表大会常务委员会</span></p><p class="header-content">时效性：<span id="sxx">尚未生效</span></p></div><div class="header-left-box" style="margin-left:30px;"><p class="header-content">施行日期：<span id="sxrq">2023-09-01</span></p><p class="header-content">公布日期：<span id="gbrq">2023-04-26</span></p></div></div><div class="header-right"><div class="header-right-box" id="wzdiv"><i class="header-icon iconfont"></i> <span id="wz" onclick="changeType(this)" class="header-button">WPS版本</span></div><div class="header-right-box" id="htdiv" style="display: none;"><i class="header-icon iconfont"></i> <span id="ht" onclick="changeType(this)" class="header-button">网页版</span></div><div class="header-right-box" id="gbdiv" style="display: none;"><i class="header-icon iconfont"></i> <span id="gb" onclick="changeType(this)" class="header-button">公报原版</span></div></div></div></div><div class="content1"><div class="er-box1"><table cellpadding="0" cellspacing="0" class="qx-tab"><tbody><tr class="wen-dang"><td colspan="4"><div class="wen-box"><iframe id="viewDoc" name="viewDoc" src="https://wb.flk.npc.gov.cn/flfg/html/f67cb9b4c170ef6e4fb39168f35a21916d6bcdd3.html" width="90%" height="100%"></iframe></div></td></tr></tbody></table></div></div><!-- <div class="fu-b-box">
  <div class="fu-b" id="linkFile">
  </div>
</div> --><div class="xf-big"><div class="xf-box"><div id="downLoadFile" onclick="downLoadFile()" class="xia-z" style="cursor: pointer;"><p>下载</p></div></div></div><div id="enclosure" style="display: none;"><div class="enclosure-title">相关文件</div><div id="enclosureItem"></div></div><div id="relevant"><div class="relevant-title">相关资料</div><div id="relevantItem"><ul><li style="cursor:pointer;"><div><img style="width: 5px;height: 5px;border-radius: 50%;" src="/images/home-point.png"></div></li><li style="cursor:pointer;" class="downloadRelevant" osspath="/flfg/XGZL/ZXL/728843aacb8a4d72bf900f7e38d5d892.docx" filename="中华人民共和国主席令第五号.docx"><div title="中华人民共和国主席令第五号.docx">中华人民共和国主席令第五号.docx</div></li></ul><ul><li style="cursor:pointer;"><div><img style="width: 5px;height: 5px;border-radius: 50%;" src="/images/home-point.png"></div></li><li style="cursor:pointer;" class="downloadRelevant" osspath="/flfg/XGZL/CADSM/cecff777ebe7452093fcba854475213c.docx" filename="关于《中华人民共和国青藏高原生态保护法（草案）》的说明.docx"><div title="关于《中华人民共和国青藏高原生态保护法（草案）》的说明.docx">关于《中华人民共和国青藏高原生态保护法（草案）》的说明.docx</div></li></ul><ul><li style="cursor:pointer;"><div><img style="width: 5px;height: 5px;border-radius: 50%;" src="/images/home-point.png"></div></li><li style="cursor:pointer;" class="downloadRelevant" osspath="/flfg/XGZL/XGQKDHB/ec6fa5bcbe764ba9a56545c1d7418352.docx" filename="全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案）》修改情况的汇报.docx"><div title="全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案）》修改情况的汇报.docx">全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案）》修改情况的汇报.docx</div></li></ul><ul><li style="cursor:pointer;"><div><img style="width: 5px;height: 5px;border-radius: 50%;" src="/images/home-point.png"></div></li><li style="cursor:pointer;" class="downloadRelevant" osspath="/flfg/XGZL/SYJGDBG/88ee6df780924fc8adb6171153fde3e0.docx" filename="全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案）》审议结果的报告.docx"><div title="全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案）》审议结果的报告.docx">全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案）》审议结果的报告.docx</div></li></ul><ul><li style="cursor:pointer;"><div><img style="width: 5px;height: 5px;border-radius: 50%;" src="/images/home-point.png"></div></li><li style="cursor:pointer;" class="downloadRelevant" osspath="/flfg/XGZL/XGYJDBG/e54d3d50f6af4f5980f49695da47205b.docx" filename="全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案三次审议稿）》修改意见的报告.docx"><div title="全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案三次审议稿）》修改意见的报告.docx">全国人民代表大会宪法和法律委员会关于《中华人民共和国青藏高原生态保护法（草案三次审议稿）》修改意见的报告.docx</div></li></ul></div></div><div class="footer-box" style="height: 111px;"><div class="footer"><div class="footer-left"><p class="footer-content">版权所有 © 全国人大常委会办公厅</p><p class="footer-content">联系我们 flk@npc.gov.cn</p><p class="footer-content">建议使用谷歌Chrome65.0、火狐Firefox47.0、IE10及以上版本浏览器</p></div><div class="footer-right" id="explain"><i class="footer-icon iconfont"></i><p class="footer-explain">说明</p></div></div></div><script src="https://hm.baidu.com/hm.js?54434aa6770b6d9fef104d146430b53b"></script><script type="text/javascript" src="./js/jquery.min.js"></script><script type="text/javascript" src="./js/layui/layui.js"></script><script type="text/javascript" src="./js/base64.js"></script><script>var timestamp=Date.parse(new Date);document.write("<script type='text/javascript' src='./js/detail3.js?"+timestamp+"'><\/script>")</script><script type="text/javascript" src="./js/detail3.js?1685711364000"></script><script>$(function(){var e=location.search.replace("?","");e&&doGet(e)});var _hmt=_hmt||[];!function(){var e=document.createElement("script");e.src="https://hm.baidu.com/hm.js?54434aa6770b6d9fef104d146430b53b";var t=document.getElementsByTagName("script")[0];t.parentNode.insertBefore(e,t)}()</script></body>
"""

# 创建BeautifulSoup对象并解析HTML内容
soup = BeautifulSoup(html_content, 'html.parser')

# 使用find方法查找id为"sxrq"的span元素
sxrq = soup.find('span', id='sxrq')
xlwj = soup.find('span', id='xlwj')
zdjg = soup.find('span', id='zdjg')
sxx = soup.find('span', id='sxx')
gbrq = soup.find('span', id='gbrq')

# 提取span元素的文本内容

sxrq = sxrq.get_text()
xlwj = xlwj.get_text()
zdjg = zdjg.get_text()
sxx = sxx.get_text()
gbrq = gbrq.get_text()
# 打印提取到的文本
print(sxrq)
print(sxx)

