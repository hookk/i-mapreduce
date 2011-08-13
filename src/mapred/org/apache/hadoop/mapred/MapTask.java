



<!DOCTYPE html>
<html>
<head>
 <link rel="icon" type="image/vnd.microsoft.icon" href="http://www.gstatic.com/codesite/ph/images/phosting.ico">
 
 
 <script type="text/javascript">
 
 
 
 
 var codesite_token = "0897d6b737948e7f980e806dd0cf4ead";
 
 
 var CS_env = {"assetHostPath":"http://www.gstatic.com/codesite/ph","projectName":"i-mapreduce","projectHomeUrl":"/p/i-mapreduce","urlPrefix":"p","domainName":null,"relativeBaseUrl":"","profileUrl":["/u/@WRVVSlFSDxJCVgV9/"],"token":"0897d6b737948e7f980e806dd0cf4ead","assetVersionPath":"http://www.gstatic.com/codesite/ph/3282150342600863652","absoluteBaseUrl":"http://code.google.com","loggedInUserEmail":"threewells14@gmail.com"};
 var _gaq = _gaq || [];
 _gaq.push(
 ['siteTracker._setAccount', 'UA-18071-1'],
 ['siteTracker._trackPageview']);
 
 _gaq.push(
 ['projectTracker._setAccount', 'UA-19094636-3'],
 ['projectTracker._trackPageview']);
 
 
 </script>
 
 
 <title>MapTask.java - 
 i-mapreduce -
 
 
 a modified MapReduce Framework for iterative processing - Google Project Hosting
 </title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" >
 
 <meta name="ROBOTS" content="NOARCHIVE">
 
 <link type="text/css" rel="stylesheet" href="http://www.gstatic.com/codesite/ph/3282150342600863652/css/ph_core.css">
 
 <link type="text/css" rel="stylesheet" href="http://www.gstatic.com/codesite/ph/3282150342600863652/css/ph_detail.css" >
 
 
 <link type="text/css" rel="stylesheet" href="http://www.gstatic.com/codesite/ph/3282150342600863652/css/d_sb.css" >
 
 
 
<!--[if IE]>
 <link type="text/css" rel="stylesheet" href="http://www.gstatic.com/codesite/ph/3282150342600863652/css/d_ie.css" >
<![endif]-->
 <style type="text/css">
 .menuIcon.off { background: no-repeat url(http://www.gstatic.com/codesite/ph/images/dropdown_sprite.gif) 0 -42px }
 .menuIcon.on { background: no-repeat url(http://www.gstatic.com/codesite/ph/images/dropdown_sprite.gif) 0 -28px }
 .menuIcon.down { background: no-repeat url(http://www.gstatic.com/codesite/ph/images/dropdown_sprite.gif) 0 0; }
 
 
 
  tr.inline_comment {
 background: #fff;
 vertical-align: top;
 }
 div.draft, div.published {
 padding: .3em;
 border: 1px solid #999; 
 margin-bottom: .1em;
 font-family: arial, sans-serif;
 max-width: 60em;
 }
 div.draft {
 background: #ffa;
 } 
 div.published {
 background: #e5ecf9;
 }
 div.published .body, div.draft .body {
 padding: .5em .1em .1em .1em;
 max-width: 60em;
 white-space: pre-wrap;
 white-space: -moz-pre-wrap;
 white-space: -pre-wrap;
 white-space: -o-pre-wrap;
 word-wrap: break-word;
 font-size: 1em;
 }
 div.draft .actions {
 margin-left: 1em;
 font-size: 90%;
 }
 div.draft form {
 padding: .5em .5em .5em 0;
 }
 div.draft textarea, div.published textarea {
 width: 95%;
 height: 10em;
 font-family: arial, sans-serif;
 margin-bottom: .5em;
 }

 
 .nocursor, .nocursor td, .cursor_hidden, .cursor_hidden td {
 background-color: white;
 height: 2px;
 }
 .cursor, .cursor td {
 background-color: darkblue;
 height: 2px;
 display: '';
 }
 
 
.list {
 border: 1px solid white;
 border-bottom: 0;
}

 
 </style>
</head>
<body class="t4">
<script type="text/javascript">
 (function() {
 var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
 (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(ga);
 })();
</script>
<div class="headbg">

 <div id="gaia">
 

 <span>
 
 
 <b>threewells14@gmail.com</b>
 
 
 | <a href="/u/@WRVVSlFSDxJCVgV9/" id="projects-dropdown" onclick="return false;"
 ><u>My favorites</u> <small>&#9660;</small></a>
 | <a href="/u/@WRVVSlFSDxJCVgV9/" onclick="_CS_click('/gb/ph/profile');"
 title="Profile, Updates, and Settings"
 ><u>Profile</u></a>
 | <a href="https://www.google.com/accounts/Logout?continue=http%3A%2F%2Fcode.google.com%2Fp%2Fi-mapreduce%2Fsource%2Fbrowse%2Ftrunk%2Fsrc%2Fmapred%2Forg%2Fapache%2Fhadoop%2Fmapred%2FMapTask.java" 
 onclick="_CS_click('/gb/ph/signout');"
 ><u>Sign out</u></a>
 
 </span>

 </div>

 <div class="gbh" style="left: 0pt;"></div>
 <div class="gbh" style="right: 0pt;"></div>
 
 
 <div style="height: 1px"></div>
<!--[if lte IE 7]>
<div style="text-align:center;">
Your version of Internet Explorer is not supported. Try a browser that
contributes to open source, such as <a href="http://www.firefox.com">Firefox</a>,
<a href="http://www.google.com/chrome">Google Chrome</a>, or
<a href="http://code.google.com/chrome/chromeframe/">Google Chrome Frame</a>.
</div>
<![endif]-->




 <table style="padding:0px; margin: 0px 0px 10px 0px; width:100%" cellpadding="0" cellspacing="0">
 <tr style="height: 58px;">
 
 <td id="plogo">
 <a href="/p/i-mapreduce/">
 
 <img src="http://www.gstatic.com/codesite/ph/images/defaultlogo.png" alt="Logo">
 
 </a>
 </td>
 
 <td style="padding-left: 0.5em">
 
 <div id="pname">
 <a href="/p/i-mapreduce/">i-mapreduce</a>
 </div>
 
 <div id="psum">
 <a id="project_summary_link" href="/p/i-mapreduce/" >a modified MapReduce Framework for iterative processing</a>
 
 </div>
 
 
 </td>
 <td style="white-space:nowrap;text-align:right; vertical-align:bottom;">
 
 <form action="/hosting/search">
 <input size="30" name="q" value="" type="text">
 
 <input type="submit" name="projectsearch" value="Search projects" >
 </form>
 
 </tr>
 </table>

</div>

 
<div id="mt" class="gtb"> 
 <a href="/p/i-mapreduce/" class="tab ">Project&nbsp;Home</a>
 
 
 
 
 <a href="/p/i-mapreduce/downloads/list" class="tab ">Downloads</a>
 
 
 
 
 
 <a href="/p/i-mapreduce/w/list" class="tab ">Wiki</a>
 
 
 
 
 
 <a href="/p/i-mapreduce/issues/list"
 class="tab ">Issues</a>
 
 
 
 
 
 <a href="/p/i-mapreduce/source/checkout"
 class="tab active">Source</a>
 
 
 <a href="/p/i-mapreduce/admin"
 class="tab inactive">Administer</a>
 
 
 
 
 <div class=gtbc></div>
</div>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0" class="st">
 <tr>
 
 
 
 
 
 
 <td class="subt">
 <div class="st2">
 <div class="isf">
 
 


 <span class="inst1"><a href="/p/i-mapreduce/source/checkout">Checkout</a></span> &nbsp;
 <span class="inst2"><a href="/p/i-mapreduce/source/browse/">Browse</a></span> &nbsp;
 <span class="inst3"><a href="/p/i-mapreduce/source/list">Changes</a></span> &nbsp;
 
 <form action="http://www.google.com/codesearch" method="get" style="display:inline"
 onsubmit="document.getElementById('codesearchq').value = document.getElementById('origq').value + ' package:http://i-mapreduce\\.googlecode\\.com'">
 <input type="hidden" name="q" id="codesearchq" value="">
 <input type="text" maxlength="2048" size="38" id="origq" name="origq" value="" title="Google Code Search" style="font-size:92%">&nbsp;<input type="submit" value="Search Trunk" name="btnG" style="font-size:92%">
 
  &nbsp;
 <a href="/p/i-mapreduce/issues/entry?show=review&former=sourcelist">Request code review</a>
 
 
 </form>
 </div>
</div>

 </td>
 
 
 
 <td align="right" valign="top" class="bevel-right"></td>
 </tr>
</table>


<script type="text/javascript">
 var cancelBubble = false;
 function _go(url) { document.location = url; }
</script>
<div id="maincol"
 
>

 
<!-- IE -->




<div class="expand">
<div id="colcontrol">
<style type="text/css">
 #file_flipper { white-space: nowrap; padding-right: 2em; }
 #file_flipper.hidden { display: none; }
 #file_flipper .pagelink { color: #0000CC; text-decoration: underline; }
 #file_flipper #visiblefiles { padding-left: 0.5em; padding-right: 0.5em; }
</style>
<table id="nav_and_rev" class="list"
 cellpadding="0" cellspacing="0" width="100%">
 <tr>
 
 <td nowrap="nowrap" class="src_crumbs src_nav" width="33%">
 <strong class="src_nav">Source path:&nbsp;</strong>
 <span id="crumb_root">
 
 <a href="/p/i-mapreduce/source/browse/">svn</a>/&nbsp;</span>
 <span id="crumb_links" class="ifClosed"><a href="/p/i-mapreduce/source/browse/trunk/">trunk</a><span class="sp">/&nbsp;</span><a href="/p/i-mapreduce/source/browse/trunk/src/">src</a><span class="sp">/&nbsp;</span><a href="/p/i-mapreduce/source/browse/trunk/src/mapred/">mapred</a><span class="sp">/&nbsp;</span><a href="/p/i-mapreduce/source/browse/trunk/src/mapred/org/">org</a><span class="sp">/&nbsp;</span><a href="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/">apache</a><span class="sp">/&nbsp;</span><a href="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/">hadoop</a><span class="sp">/&nbsp;</span><a href="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/">mapred</a><span class="sp">/&nbsp;</span>MapTask.java</span>
 
 

 </td>
 
 
 <td nowrap="nowrap" width="33%" align="center">
 <a href="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java?edit=1"
 ><img src="http://www.gstatic.com/codesite/ph/images/pencil-y14.png"
 class="edit_icon">Edit file</a>
 </td>
 
 
 <td nowrap="nowrap" width="33%" align="right">
 <table cellpadding="0" cellspacing="0" style="font-size: 100%"><tr>
 
 
 <td class="flipper">
 <ul class="leftside">
 
 <li><a href="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java?r=67" title="Previous">&lsaquo;r67</a></li>
 
 </ul>
 </td>
 
 <td class="flipper"><b>r78</b></td>
 
 </tr></table>
 </td> 
 </tr>
</table>

<div class="fc">
 
 
 
<style type="text/css">
.undermouse span {
 background-image: url(http://www.gstatic.com/codesite/ph/images/comments.gif); }
</style>
<table class="opened" id="review_comment_area"
onmouseout="gutterOut()"><tr>
<td id="nums">
<pre><table width="100%"><tr class="nocursor"><td></td></tr></table></pre>
<pre><table width="100%" id="nums_table_0"><tr id="gr_svn78_1"

 onmouseover="gutterOver(1)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',1);">&nbsp;</span
></td><td id="1"><a href="#1">1</a></td></tr
><tr id="gr_svn78_2"

 onmouseover="gutterOver(2)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',2);">&nbsp;</span
></td><td id="2"><a href="#2">2</a></td></tr
><tr id="gr_svn78_3"

 onmouseover="gutterOver(3)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',3);">&nbsp;</span
></td><td id="3"><a href="#3">3</a></td></tr
><tr id="gr_svn78_4"

 onmouseover="gutterOver(4)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',4);">&nbsp;</span
></td><td id="4"><a href="#4">4</a></td></tr
><tr id="gr_svn78_5"

 onmouseover="gutterOver(5)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',5);">&nbsp;</span
></td><td id="5"><a href="#5">5</a></td></tr
><tr id="gr_svn78_6"

 onmouseover="gutterOver(6)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',6);">&nbsp;</span
></td><td id="6"><a href="#6">6</a></td></tr
><tr id="gr_svn78_7"

 onmouseover="gutterOver(7)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',7);">&nbsp;</span
></td><td id="7"><a href="#7">7</a></td></tr
><tr id="gr_svn78_8"

 onmouseover="gutterOver(8)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',8);">&nbsp;</span
></td><td id="8"><a href="#8">8</a></td></tr
><tr id="gr_svn78_9"

 onmouseover="gutterOver(9)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',9);">&nbsp;</span
></td><td id="9"><a href="#9">9</a></td></tr
><tr id="gr_svn78_10"

 onmouseover="gutterOver(10)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',10);">&nbsp;</span
></td><td id="10"><a href="#10">10</a></td></tr
><tr id="gr_svn78_11"

 onmouseover="gutterOver(11)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',11);">&nbsp;</span
></td><td id="11"><a href="#11">11</a></td></tr
><tr id="gr_svn78_12"

 onmouseover="gutterOver(12)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',12);">&nbsp;</span
></td><td id="12"><a href="#12">12</a></td></tr
><tr id="gr_svn78_13"

 onmouseover="gutterOver(13)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',13);">&nbsp;</span
></td><td id="13"><a href="#13">13</a></td></tr
><tr id="gr_svn78_14"

 onmouseover="gutterOver(14)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',14);">&nbsp;</span
></td><td id="14"><a href="#14">14</a></td></tr
><tr id="gr_svn78_15"

 onmouseover="gutterOver(15)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',15);">&nbsp;</span
></td><td id="15"><a href="#15">15</a></td></tr
><tr id="gr_svn78_16"

 onmouseover="gutterOver(16)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',16);">&nbsp;</span
></td><td id="16"><a href="#16">16</a></td></tr
><tr id="gr_svn78_17"

 onmouseover="gutterOver(17)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',17);">&nbsp;</span
></td><td id="17"><a href="#17">17</a></td></tr
><tr id="gr_svn78_18"

 onmouseover="gutterOver(18)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',18);">&nbsp;</span
></td><td id="18"><a href="#18">18</a></td></tr
><tr id="gr_svn78_19"

 onmouseover="gutterOver(19)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',19);">&nbsp;</span
></td><td id="19"><a href="#19">19</a></td></tr
><tr id="gr_svn78_20"

 onmouseover="gutterOver(20)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',20);">&nbsp;</span
></td><td id="20"><a href="#20">20</a></td></tr
><tr id="gr_svn78_21"

 onmouseover="gutterOver(21)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',21);">&nbsp;</span
></td><td id="21"><a href="#21">21</a></td></tr
><tr id="gr_svn78_22"

 onmouseover="gutterOver(22)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',22);">&nbsp;</span
></td><td id="22"><a href="#22">22</a></td></tr
><tr id="gr_svn78_23"

 onmouseover="gutterOver(23)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',23);">&nbsp;</span
></td><td id="23"><a href="#23">23</a></td></tr
><tr id="gr_svn78_24"

 onmouseover="gutterOver(24)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',24);">&nbsp;</span
></td><td id="24"><a href="#24">24</a></td></tr
><tr id="gr_svn78_25"

 onmouseover="gutterOver(25)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',25);">&nbsp;</span
></td><td id="25"><a href="#25">25</a></td></tr
><tr id="gr_svn78_26"

 onmouseover="gutterOver(26)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',26);">&nbsp;</span
></td><td id="26"><a href="#26">26</a></td></tr
><tr id="gr_svn78_27"

 onmouseover="gutterOver(27)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',27);">&nbsp;</span
></td><td id="27"><a href="#27">27</a></td></tr
><tr id="gr_svn78_28"

 onmouseover="gutterOver(28)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',28);">&nbsp;</span
></td><td id="28"><a href="#28">28</a></td></tr
><tr id="gr_svn78_29"

 onmouseover="gutterOver(29)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',29);">&nbsp;</span
></td><td id="29"><a href="#29">29</a></td></tr
><tr id="gr_svn78_30"

 onmouseover="gutterOver(30)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',30);">&nbsp;</span
></td><td id="30"><a href="#30">30</a></td></tr
><tr id="gr_svn78_31"

 onmouseover="gutterOver(31)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',31);">&nbsp;</span
></td><td id="31"><a href="#31">31</a></td></tr
><tr id="gr_svn78_32"

 onmouseover="gutterOver(32)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',32);">&nbsp;</span
></td><td id="32"><a href="#32">32</a></td></tr
><tr id="gr_svn78_33"

 onmouseover="gutterOver(33)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',33);">&nbsp;</span
></td><td id="33"><a href="#33">33</a></td></tr
><tr id="gr_svn78_34"

 onmouseover="gutterOver(34)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',34);">&nbsp;</span
></td><td id="34"><a href="#34">34</a></td></tr
><tr id="gr_svn78_35"

 onmouseover="gutterOver(35)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',35);">&nbsp;</span
></td><td id="35"><a href="#35">35</a></td></tr
><tr id="gr_svn78_36"

 onmouseover="gutterOver(36)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',36);">&nbsp;</span
></td><td id="36"><a href="#36">36</a></td></tr
><tr id="gr_svn78_37"

 onmouseover="gutterOver(37)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',37);">&nbsp;</span
></td><td id="37"><a href="#37">37</a></td></tr
><tr id="gr_svn78_38"

 onmouseover="gutterOver(38)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',38);">&nbsp;</span
></td><td id="38"><a href="#38">38</a></td></tr
><tr id="gr_svn78_39"

 onmouseover="gutterOver(39)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',39);">&nbsp;</span
></td><td id="39"><a href="#39">39</a></td></tr
><tr id="gr_svn78_40"

 onmouseover="gutterOver(40)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',40);">&nbsp;</span
></td><td id="40"><a href="#40">40</a></td></tr
><tr id="gr_svn78_41"

 onmouseover="gutterOver(41)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',41);">&nbsp;</span
></td><td id="41"><a href="#41">41</a></td></tr
><tr id="gr_svn78_42"

 onmouseover="gutterOver(42)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',42);">&nbsp;</span
></td><td id="42"><a href="#42">42</a></td></tr
><tr id="gr_svn78_43"

 onmouseover="gutterOver(43)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',43);">&nbsp;</span
></td><td id="43"><a href="#43">43</a></td></tr
><tr id="gr_svn78_44"

 onmouseover="gutterOver(44)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',44);">&nbsp;</span
></td><td id="44"><a href="#44">44</a></td></tr
><tr id="gr_svn78_45"

 onmouseover="gutterOver(45)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',45);">&nbsp;</span
></td><td id="45"><a href="#45">45</a></td></tr
><tr id="gr_svn78_46"

 onmouseover="gutterOver(46)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',46);">&nbsp;</span
></td><td id="46"><a href="#46">46</a></td></tr
><tr id="gr_svn78_47"

 onmouseover="gutterOver(47)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',47);">&nbsp;</span
></td><td id="47"><a href="#47">47</a></td></tr
><tr id="gr_svn78_48"

 onmouseover="gutterOver(48)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',48);">&nbsp;</span
></td><td id="48"><a href="#48">48</a></td></tr
><tr id="gr_svn78_49"

 onmouseover="gutterOver(49)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',49);">&nbsp;</span
></td><td id="49"><a href="#49">49</a></td></tr
><tr id="gr_svn78_50"

 onmouseover="gutterOver(50)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',50);">&nbsp;</span
></td><td id="50"><a href="#50">50</a></td></tr
><tr id="gr_svn78_51"

 onmouseover="gutterOver(51)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',51);">&nbsp;</span
></td><td id="51"><a href="#51">51</a></td></tr
><tr id="gr_svn78_52"

 onmouseover="gutterOver(52)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',52);">&nbsp;</span
></td><td id="52"><a href="#52">52</a></td></tr
><tr id="gr_svn78_53"

 onmouseover="gutterOver(53)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',53);">&nbsp;</span
></td><td id="53"><a href="#53">53</a></td></tr
><tr id="gr_svn78_54"

 onmouseover="gutterOver(54)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',54);">&nbsp;</span
></td><td id="54"><a href="#54">54</a></td></tr
><tr id="gr_svn78_55"

 onmouseover="gutterOver(55)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',55);">&nbsp;</span
></td><td id="55"><a href="#55">55</a></td></tr
><tr id="gr_svn78_56"

 onmouseover="gutterOver(56)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',56);">&nbsp;</span
></td><td id="56"><a href="#56">56</a></td></tr
><tr id="gr_svn78_57"

 onmouseover="gutterOver(57)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',57);">&nbsp;</span
></td><td id="57"><a href="#57">57</a></td></tr
><tr id="gr_svn78_58"

 onmouseover="gutterOver(58)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',58);">&nbsp;</span
></td><td id="58"><a href="#58">58</a></td></tr
><tr id="gr_svn78_59"

 onmouseover="gutterOver(59)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',59);">&nbsp;</span
></td><td id="59"><a href="#59">59</a></td></tr
><tr id="gr_svn78_60"

 onmouseover="gutterOver(60)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',60);">&nbsp;</span
></td><td id="60"><a href="#60">60</a></td></tr
><tr id="gr_svn78_61"

 onmouseover="gutterOver(61)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',61);">&nbsp;</span
></td><td id="61"><a href="#61">61</a></td></tr
><tr id="gr_svn78_62"

 onmouseover="gutterOver(62)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',62);">&nbsp;</span
></td><td id="62"><a href="#62">62</a></td></tr
><tr id="gr_svn78_63"

 onmouseover="gutterOver(63)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',63);">&nbsp;</span
></td><td id="63"><a href="#63">63</a></td></tr
><tr id="gr_svn78_64"

 onmouseover="gutterOver(64)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',64);">&nbsp;</span
></td><td id="64"><a href="#64">64</a></td></tr
><tr id="gr_svn78_65"

 onmouseover="gutterOver(65)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',65);">&nbsp;</span
></td><td id="65"><a href="#65">65</a></td></tr
><tr id="gr_svn78_66"

 onmouseover="gutterOver(66)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',66);">&nbsp;</span
></td><td id="66"><a href="#66">66</a></td></tr
><tr id="gr_svn78_67"

 onmouseover="gutterOver(67)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',67);">&nbsp;</span
></td><td id="67"><a href="#67">67</a></td></tr
><tr id="gr_svn78_68"

 onmouseover="gutterOver(68)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',68);">&nbsp;</span
></td><td id="68"><a href="#68">68</a></td></tr
><tr id="gr_svn78_69"

 onmouseover="gutterOver(69)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',69);">&nbsp;</span
></td><td id="69"><a href="#69">69</a></td></tr
><tr id="gr_svn78_70"

 onmouseover="gutterOver(70)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',70);">&nbsp;</span
></td><td id="70"><a href="#70">70</a></td></tr
><tr id="gr_svn78_71"

 onmouseover="gutterOver(71)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',71);">&nbsp;</span
></td><td id="71"><a href="#71">71</a></td></tr
><tr id="gr_svn78_72"

 onmouseover="gutterOver(72)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',72);">&nbsp;</span
></td><td id="72"><a href="#72">72</a></td></tr
><tr id="gr_svn78_73"

 onmouseover="gutterOver(73)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',73);">&nbsp;</span
></td><td id="73"><a href="#73">73</a></td></tr
><tr id="gr_svn78_74"

 onmouseover="gutterOver(74)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',74);">&nbsp;</span
></td><td id="74"><a href="#74">74</a></td></tr
><tr id="gr_svn78_75"

 onmouseover="gutterOver(75)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',75);">&nbsp;</span
></td><td id="75"><a href="#75">75</a></td></tr
><tr id="gr_svn78_76"

 onmouseover="gutterOver(76)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',76);">&nbsp;</span
></td><td id="76"><a href="#76">76</a></td></tr
><tr id="gr_svn78_77"

 onmouseover="gutterOver(77)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',77);">&nbsp;</span
></td><td id="77"><a href="#77">77</a></td></tr
><tr id="gr_svn78_78"

 onmouseover="gutterOver(78)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',78);">&nbsp;</span
></td><td id="78"><a href="#78">78</a></td></tr
><tr id="gr_svn78_79"

 onmouseover="gutterOver(79)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',79);">&nbsp;</span
></td><td id="79"><a href="#79">79</a></td></tr
><tr id="gr_svn78_80"

 onmouseover="gutterOver(80)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',80);">&nbsp;</span
></td><td id="80"><a href="#80">80</a></td></tr
><tr id="gr_svn78_81"

 onmouseover="gutterOver(81)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',81);">&nbsp;</span
></td><td id="81"><a href="#81">81</a></td></tr
><tr id="gr_svn78_82"

 onmouseover="gutterOver(82)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',82);">&nbsp;</span
></td><td id="82"><a href="#82">82</a></td></tr
><tr id="gr_svn78_83"

 onmouseover="gutterOver(83)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',83);">&nbsp;</span
></td><td id="83"><a href="#83">83</a></td></tr
><tr id="gr_svn78_84"

 onmouseover="gutterOver(84)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',84);">&nbsp;</span
></td><td id="84"><a href="#84">84</a></td></tr
><tr id="gr_svn78_85"

 onmouseover="gutterOver(85)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',85);">&nbsp;</span
></td><td id="85"><a href="#85">85</a></td></tr
><tr id="gr_svn78_86"

 onmouseover="gutterOver(86)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',86);">&nbsp;</span
></td><td id="86"><a href="#86">86</a></td></tr
><tr id="gr_svn78_87"

 onmouseover="gutterOver(87)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',87);">&nbsp;</span
></td><td id="87"><a href="#87">87</a></td></tr
><tr id="gr_svn78_88"

 onmouseover="gutterOver(88)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',88);">&nbsp;</span
></td><td id="88"><a href="#88">88</a></td></tr
><tr id="gr_svn78_89"

 onmouseover="gutterOver(89)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',89);">&nbsp;</span
></td><td id="89"><a href="#89">89</a></td></tr
><tr id="gr_svn78_90"

 onmouseover="gutterOver(90)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',90);">&nbsp;</span
></td><td id="90"><a href="#90">90</a></td></tr
><tr id="gr_svn78_91"

 onmouseover="gutterOver(91)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',91);">&nbsp;</span
></td><td id="91"><a href="#91">91</a></td></tr
><tr id="gr_svn78_92"

 onmouseover="gutterOver(92)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',92);">&nbsp;</span
></td><td id="92"><a href="#92">92</a></td></tr
><tr id="gr_svn78_93"

 onmouseover="gutterOver(93)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',93);">&nbsp;</span
></td><td id="93"><a href="#93">93</a></td></tr
><tr id="gr_svn78_94"

 onmouseover="gutterOver(94)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',94);">&nbsp;</span
></td><td id="94"><a href="#94">94</a></td></tr
><tr id="gr_svn78_95"

 onmouseover="gutterOver(95)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',95);">&nbsp;</span
></td><td id="95"><a href="#95">95</a></td></tr
><tr id="gr_svn78_96"

 onmouseover="gutterOver(96)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',96);">&nbsp;</span
></td><td id="96"><a href="#96">96</a></td></tr
><tr id="gr_svn78_97"

 onmouseover="gutterOver(97)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',97);">&nbsp;</span
></td><td id="97"><a href="#97">97</a></td></tr
><tr id="gr_svn78_98"

 onmouseover="gutterOver(98)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',98);">&nbsp;</span
></td><td id="98"><a href="#98">98</a></td></tr
><tr id="gr_svn78_99"

 onmouseover="gutterOver(99)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',99);">&nbsp;</span
></td><td id="99"><a href="#99">99</a></td></tr
><tr id="gr_svn78_100"

 onmouseover="gutterOver(100)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',100);">&nbsp;</span
></td><td id="100"><a href="#100">100</a></td></tr
><tr id="gr_svn78_101"

 onmouseover="gutterOver(101)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',101);">&nbsp;</span
></td><td id="101"><a href="#101">101</a></td></tr
><tr id="gr_svn78_102"

 onmouseover="gutterOver(102)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',102);">&nbsp;</span
></td><td id="102"><a href="#102">102</a></td></tr
><tr id="gr_svn78_103"

 onmouseover="gutterOver(103)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',103);">&nbsp;</span
></td><td id="103"><a href="#103">103</a></td></tr
><tr id="gr_svn78_104"

 onmouseover="gutterOver(104)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',104);">&nbsp;</span
></td><td id="104"><a href="#104">104</a></td></tr
><tr id="gr_svn78_105"

 onmouseover="gutterOver(105)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',105);">&nbsp;</span
></td><td id="105"><a href="#105">105</a></td></tr
><tr id="gr_svn78_106"

 onmouseover="gutterOver(106)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',106);">&nbsp;</span
></td><td id="106"><a href="#106">106</a></td></tr
><tr id="gr_svn78_107"

 onmouseover="gutterOver(107)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',107);">&nbsp;</span
></td><td id="107"><a href="#107">107</a></td></tr
><tr id="gr_svn78_108"

 onmouseover="gutterOver(108)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',108);">&nbsp;</span
></td><td id="108"><a href="#108">108</a></td></tr
><tr id="gr_svn78_109"

 onmouseover="gutterOver(109)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',109);">&nbsp;</span
></td><td id="109"><a href="#109">109</a></td></tr
><tr id="gr_svn78_110"

 onmouseover="gutterOver(110)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',110);">&nbsp;</span
></td><td id="110"><a href="#110">110</a></td></tr
><tr id="gr_svn78_111"

 onmouseover="gutterOver(111)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',111);">&nbsp;</span
></td><td id="111"><a href="#111">111</a></td></tr
><tr id="gr_svn78_112"

 onmouseover="gutterOver(112)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',112);">&nbsp;</span
></td><td id="112"><a href="#112">112</a></td></tr
><tr id="gr_svn78_113"

 onmouseover="gutterOver(113)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',113);">&nbsp;</span
></td><td id="113"><a href="#113">113</a></td></tr
><tr id="gr_svn78_114"

 onmouseover="gutterOver(114)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',114);">&nbsp;</span
></td><td id="114"><a href="#114">114</a></td></tr
><tr id="gr_svn78_115"

 onmouseover="gutterOver(115)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',115);">&nbsp;</span
></td><td id="115"><a href="#115">115</a></td></tr
><tr id="gr_svn78_116"

 onmouseover="gutterOver(116)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',116);">&nbsp;</span
></td><td id="116"><a href="#116">116</a></td></tr
><tr id="gr_svn78_117"

 onmouseover="gutterOver(117)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',117);">&nbsp;</span
></td><td id="117"><a href="#117">117</a></td></tr
><tr id="gr_svn78_118"

 onmouseover="gutterOver(118)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',118);">&nbsp;</span
></td><td id="118"><a href="#118">118</a></td></tr
><tr id="gr_svn78_119"

 onmouseover="gutterOver(119)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',119);">&nbsp;</span
></td><td id="119"><a href="#119">119</a></td></tr
><tr id="gr_svn78_120"

 onmouseover="gutterOver(120)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',120);">&nbsp;</span
></td><td id="120"><a href="#120">120</a></td></tr
><tr id="gr_svn78_121"

 onmouseover="gutterOver(121)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',121);">&nbsp;</span
></td><td id="121"><a href="#121">121</a></td></tr
><tr id="gr_svn78_122"

 onmouseover="gutterOver(122)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',122);">&nbsp;</span
></td><td id="122"><a href="#122">122</a></td></tr
><tr id="gr_svn78_123"

 onmouseover="gutterOver(123)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',123);">&nbsp;</span
></td><td id="123"><a href="#123">123</a></td></tr
><tr id="gr_svn78_124"

 onmouseover="gutterOver(124)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',124);">&nbsp;</span
></td><td id="124"><a href="#124">124</a></td></tr
><tr id="gr_svn78_125"

 onmouseover="gutterOver(125)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',125);">&nbsp;</span
></td><td id="125"><a href="#125">125</a></td></tr
><tr id="gr_svn78_126"

 onmouseover="gutterOver(126)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',126);">&nbsp;</span
></td><td id="126"><a href="#126">126</a></td></tr
><tr id="gr_svn78_127"

 onmouseover="gutterOver(127)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',127);">&nbsp;</span
></td><td id="127"><a href="#127">127</a></td></tr
><tr id="gr_svn78_128"

 onmouseover="gutterOver(128)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',128);">&nbsp;</span
></td><td id="128"><a href="#128">128</a></td></tr
><tr id="gr_svn78_129"

 onmouseover="gutterOver(129)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',129);">&nbsp;</span
></td><td id="129"><a href="#129">129</a></td></tr
><tr id="gr_svn78_130"

 onmouseover="gutterOver(130)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',130);">&nbsp;</span
></td><td id="130"><a href="#130">130</a></td></tr
><tr id="gr_svn78_131"

 onmouseover="gutterOver(131)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',131);">&nbsp;</span
></td><td id="131"><a href="#131">131</a></td></tr
><tr id="gr_svn78_132"

 onmouseover="gutterOver(132)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',132);">&nbsp;</span
></td><td id="132"><a href="#132">132</a></td></tr
><tr id="gr_svn78_133"

 onmouseover="gutterOver(133)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',133);">&nbsp;</span
></td><td id="133"><a href="#133">133</a></td></tr
><tr id="gr_svn78_134"

 onmouseover="gutterOver(134)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',134);">&nbsp;</span
></td><td id="134"><a href="#134">134</a></td></tr
><tr id="gr_svn78_135"

 onmouseover="gutterOver(135)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',135);">&nbsp;</span
></td><td id="135"><a href="#135">135</a></td></tr
><tr id="gr_svn78_136"

 onmouseover="gutterOver(136)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',136);">&nbsp;</span
></td><td id="136"><a href="#136">136</a></td></tr
><tr id="gr_svn78_137"

 onmouseover="gutterOver(137)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',137);">&nbsp;</span
></td><td id="137"><a href="#137">137</a></td></tr
><tr id="gr_svn78_138"

 onmouseover="gutterOver(138)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',138);">&nbsp;</span
></td><td id="138"><a href="#138">138</a></td></tr
><tr id="gr_svn78_139"

 onmouseover="gutterOver(139)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',139);">&nbsp;</span
></td><td id="139"><a href="#139">139</a></td></tr
><tr id="gr_svn78_140"

 onmouseover="gutterOver(140)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',140);">&nbsp;</span
></td><td id="140"><a href="#140">140</a></td></tr
><tr id="gr_svn78_141"

 onmouseover="gutterOver(141)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',141);">&nbsp;</span
></td><td id="141"><a href="#141">141</a></td></tr
><tr id="gr_svn78_142"

 onmouseover="gutterOver(142)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',142);">&nbsp;</span
></td><td id="142"><a href="#142">142</a></td></tr
><tr id="gr_svn78_143"

 onmouseover="gutterOver(143)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',143);">&nbsp;</span
></td><td id="143"><a href="#143">143</a></td></tr
><tr id="gr_svn78_144"

 onmouseover="gutterOver(144)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',144);">&nbsp;</span
></td><td id="144"><a href="#144">144</a></td></tr
><tr id="gr_svn78_145"

 onmouseover="gutterOver(145)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',145);">&nbsp;</span
></td><td id="145"><a href="#145">145</a></td></tr
><tr id="gr_svn78_146"

 onmouseover="gutterOver(146)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',146);">&nbsp;</span
></td><td id="146"><a href="#146">146</a></td></tr
><tr id="gr_svn78_147"

 onmouseover="gutterOver(147)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',147);">&nbsp;</span
></td><td id="147"><a href="#147">147</a></td></tr
><tr id="gr_svn78_148"

 onmouseover="gutterOver(148)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',148);">&nbsp;</span
></td><td id="148"><a href="#148">148</a></td></tr
><tr id="gr_svn78_149"

 onmouseover="gutterOver(149)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',149);">&nbsp;</span
></td><td id="149"><a href="#149">149</a></td></tr
><tr id="gr_svn78_150"

 onmouseover="gutterOver(150)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',150);">&nbsp;</span
></td><td id="150"><a href="#150">150</a></td></tr
><tr id="gr_svn78_151"

 onmouseover="gutterOver(151)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',151);">&nbsp;</span
></td><td id="151"><a href="#151">151</a></td></tr
><tr id="gr_svn78_152"

 onmouseover="gutterOver(152)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',152);">&nbsp;</span
></td><td id="152"><a href="#152">152</a></td></tr
><tr id="gr_svn78_153"

 onmouseover="gutterOver(153)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',153);">&nbsp;</span
></td><td id="153"><a href="#153">153</a></td></tr
><tr id="gr_svn78_154"

 onmouseover="gutterOver(154)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',154);">&nbsp;</span
></td><td id="154"><a href="#154">154</a></td></tr
><tr id="gr_svn78_155"

 onmouseover="gutterOver(155)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',155);">&nbsp;</span
></td><td id="155"><a href="#155">155</a></td></tr
><tr id="gr_svn78_156"

 onmouseover="gutterOver(156)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',156);">&nbsp;</span
></td><td id="156"><a href="#156">156</a></td></tr
><tr id="gr_svn78_157"

 onmouseover="gutterOver(157)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',157);">&nbsp;</span
></td><td id="157"><a href="#157">157</a></td></tr
><tr id="gr_svn78_158"

 onmouseover="gutterOver(158)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',158);">&nbsp;</span
></td><td id="158"><a href="#158">158</a></td></tr
><tr id="gr_svn78_159"

 onmouseover="gutterOver(159)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',159);">&nbsp;</span
></td><td id="159"><a href="#159">159</a></td></tr
><tr id="gr_svn78_160"

 onmouseover="gutterOver(160)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',160);">&nbsp;</span
></td><td id="160"><a href="#160">160</a></td></tr
><tr id="gr_svn78_161"

 onmouseover="gutterOver(161)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',161);">&nbsp;</span
></td><td id="161"><a href="#161">161</a></td></tr
><tr id="gr_svn78_162"

 onmouseover="gutterOver(162)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',162);">&nbsp;</span
></td><td id="162"><a href="#162">162</a></td></tr
><tr id="gr_svn78_163"

 onmouseover="gutterOver(163)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',163);">&nbsp;</span
></td><td id="163"><a href="#163">163</a></td></tr
><tr id="gr_svn78_164"

 onmouseover="gutterOver(164)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',164);">&nbsp;</span
></td><td id="164"><a href="#164">164</a></td></tr
><tr id="gr_svn78_165"

 onmouseover="gutterOver(165)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',165);">&nbsp;</span
></td><td id="165"><a href="#165">165</a></td></tr
><tr id="gr_svn78_166"

 onmouseover="gutterOver(166)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',166);">&nbsp;</span
></td><td id="166"><a href="#166">166</a></td></tr
><tr id="gr_svn78_167"

 onmouseover="gutterOver(167)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',167);">&nbsp;</span
></td><td id="167"><a href="#167">167</a></td></tr
><tr id="gr_svn78_168"

 onmouseover="gutterOver(168)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',168);">&nbsp;</span
></td><td id="168"><a href="#168">168</a></td></tr
><tr id="gr_svn78_169"

 onmouseover="gutterOver(169)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',169);">&nbsp;</span
></td><td id="169"><a href="#169">169</a></td></tr
><tr id="gr_svn78_170"

 onmouseover="gutterOver(170)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',170);">&nbsp;</span
></td><td id="170"><a href="#170">170</a></td></tr
><tr id="gr_svn78_171"

 onmouseover="gutterOver(171)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',171);">&nbsp;</span
></td><td id="171"><a href="#171">171</a></td></tr
><tr id="gr_svn78_172"

 onmouseover="gutterOver(172)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',172);">&nbsp;</span
></td><td id="172"><a href="#172">172</a></td></tr
><tr id="gr_svn78_173"

 onmouseover="gutterOver(173)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',173);">&nbsp;</span
></td><td id="173"><a href="#173">173</a></td></tr
><tr id="gr_svn78_174"

 onmouseover="gutterOver(174)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',174);">&nbsp;</span
></td><td id="174"><a href="#174">174</a></td></tr
><tr id="gr_svn78_175"

 onmouseover="gutterOver(175)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',175);">&nbsp;</span
></td><td id="175"><a href="#175">175</a></td></tr
><tr id="gr_svn78_176"

 onmouseover="gutterOver(176)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',176);">&nbsp;</span
></td><td id="176"><a href="#176">176</a></td></tr
><tr id="gr_svn78_177"

 onmouseover="gutterOver(177)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',177);">&nbsp;</span
></td><td id="177"><a href="#177">177</a></td></tr
><tr id="gr_svn78_178"

 onmouseover="gutterOver(178)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',178);">&nbsp;</span
></td><td id="178"><a href="#178">178</a></td></tr
><tr id="gr_svn78_179"

 onmouseover="gutterOver(179)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',179);">&nbsp;</span
></td><td id="179"><a href="#179">179</a></td></tr
><tr id="gr_svn78_180"

 onmouseover="gutterOver(180)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',180);">&nbsp;</span
></td><td id="180"><a href="#180">180</a></td></tr
><tr id="gr_svn78_181"

 onmouseover="gutterOver(181)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',181);">&nbsp;</span
></td><td id="181"><a href="#181">181</a></td></tr
><tr id="gr_svn78_182"

 onmouseover="gutterOver(182)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',182);">&nbsp;</span
></td><td id="182"><a href="#182">182</a></td></tr
><tr id="gr_svn78_183"

 onmouseover="gutterOver(183)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',183);">&nbsp;</span
></td><td id="183"><a href="#183">183</a></td></tr
><tr id="gr_svn78_184"

 onmouseover="gutterOver(184)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',184);">&nbsp;</span
></td><td id="184"><a href="#184">184</a></td></tr
><tr id="gr_svn78_185"

 onmouseover="gutterOver(185)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',185);">&nbsp;</span
></td><td id="185"><a href="#185">185</a></td></tr
><tr id="gr_svn78_186"

 onmouseover="gutterOver(186)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',186);">&nbsp;</span
></td><td id="186"><a href="#186">186</a></td></tr
><tr id="gr_svn78_187"

 onmouseover="gutterOver(187)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',187);">&nbsp;</span
></td><td id="187"><a href="#187">187</a></td></tr
><tr id="gr_svn78_188"

 onmouseover="gutterOver(188)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',188);">&nbsp;</span
></td><td id="188"><a href="#188">188</a></td></tr
><tr id="gr_svn78_189"

 onmouseover="gutterOver(189)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',189);">&nbsp;</span
></td><td id="189"><a href="#189">189</a></td></tr
><tr id="gr_svn78_190"

 onmouseover="gutterOver(190)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',190);">&nbsp;</span
></td><td id="190"><a href="#190">190</a></td></tr
><tr id="gr_svn78_191"

 onmouseover="gutterOver(191)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',191);">&nbsp;</span
></td><td id="191"><a href="#191">191</a></td></tr
><tr id="gr_svn78_192"

 onmouseover="gutterOver(192)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',192);">&nbsp;</span
></td><td id="192"><a href="#192">192</a></td></tr
><tr id="gr_svn78_193"

 onmouseover="gutterOver(193)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',193);">&nbsp;</span
></td><td id="193"><a href="#193">193</a></td></tr
><tr id="gr_svn78_194"

 onmouseover="gutterOver(194)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',194);">&nbsp;</span
></td><td id="194"><a href="#194">194</a></td></tr
><tr id="gr_svn78_195"

 onmouseover="gutterOver(195)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',195);">&nbsp;</span
></td><td id="195"><a href="#195">195</a></td></tr
><tr id="gr_svn78_196"

 onmouseover="gutterOver(196)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',196);">&nbsp;</span
></td><td id="196"><a href="#196">196</a></td></tr
><tr id="gr_svn78_197"

 onmouseover="gutterOver(197)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',197);">&nbsp;</span
></td><td id="197"><a href="#197">197</a></td></tr
><tr id="gr_svn78_198"

 onmouseover="gutterOver(198)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',198);">&nbsp;</span
></td><td id="198"><a href="#198">198</a></td></tr
><tr id="gr_svn78_199"

 onmouseover="gutterOver(199)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',199);">&nbsp;</span
></td><td id="199"><a href="#199">199</a></td></tr
><tr id="gr_svn78_200"

 onmouseover="gutterOver(200)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',200);">&nbsp;</span
></td><td id="200"><a href="#200">200</a></td></tr
><tr id="gr_svn78_201"

 onmouseover="gutterOver(201)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',201);">&nbsp;</span
></td><td id="201"><a href="#201">201</a></td></tr
><tr id="gr_svn78_202"

 onmouseover="gutterOver(202)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',202);">&nbsp;</span
></td><td id="202"><a href="#202">202</a></td></tr
><tr id="gr_svn78_203"

 onmouseover="gutterOver(203)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',203);">&nbsp;</span
></td><td id="203"><a href="#203">203</a></td></tr
><tr id="gr_svn78_204"

 onmouseover="gutterOver(204)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',204);">&nbsp;</span
></td><td id="204"><a href="#204">204</a></td></tr
><tr id="gr_svn78_205"

 onmouseover="gutterOver(205)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',205);">&nbsp;</span
></td><td id="205"><a href="#205">205</a></td></tr
><tr id="gr_svn78_206"

 onmouseover="gutterOver(206)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',206);">&nbsp;</span
></td><td id="206"><a href="#206">206</a></td></tr
><tr id="gr_svn78_207"

 onmouseover="gutterOver(207)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',207);">&nbsp;</span
></td><td id="207"><a href="#207">207</a></td></tr
><tr id="gr_svn78_208"

 onmouseover="gutterOver(208)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',208);">&nbsp;</span
></td><td id="208"><a href="#208">208</a></td></tr
><tr id="gr_svn78_209"

 onmouseover="gutterOver(209)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',209);">&nbsp;</span
></td><td id="209"><a href="#209">209</a></td></tr
><tr id="gr_svn78_210"

 onmouseover="gutterOver(210)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',210);">&nbsp;</span
></td><td id="210"><a href="#210">210</a></td></tr
><tr id="gr_svn78_211"

 onmouseover="gutterOver(211)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',211);">&nbsp;</span
></td><td id="211"><a href="#211">211</a></td></tr
><tr id="gr_svn78_212"

 onmouseover="gutterOver(212)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',212);">&nbsp;</span
></td><td id="212"><a href="#212">212</a></td></tr
><tr id="gr_svn78_213"

 onmouseover="gutterOver(213)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',213);">&nbsp;</span
></td><td id="213"><a href="#213">213</a></td></tr
><tr id="gr_svn78_214"

 onmouseover="gutterOver(214)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',214);">&nbsp;</span
></td><td id="214"><a href="#214">214</a></td></tr
><tr id="gr_svn78_215"

 onmouseover="gutterOver(215)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',215);">&nbsp;</span
></td><td id="215"><a href="#215">215</a></td></tr
><tr id="gr_svn78_216"

 onmouseover="gutterOver(216)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',216);">&nbsp;</span
></td><td id="216"><a href="#216">216</a></td></tr
><tr id="gr_svn78_217"

 onmouseover="gutterOver(217)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',217);">&nbsp;</span
></td><td id="217"><a href="#217">217</a></td></tr
><tr id="gr_svn78_218"

 onmouseover="gutterOver(218)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',218);">&nbsp;</span
></td><td id="218"><a href="#218">218</a></td></tr
><tr id="gr_svn78_219"

 onmouseover="gutterOver(219)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',219);">&nbsp;</span
></td><td id="219"><a href="#219">219</a></td></tr
><tr id="gr_svn78_220"

 onmouseover="gutterOver(220)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',220);">&nbsp;</span
></td><td id="220"><a href="#220">220</a></td></tr
><tr id="gr_svn78_221"

 onmouseover="gutterOver(221)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',221);">&nbsp;</span
></td><td id="221"><a href="#221">221</a></td></tr
><tr id="gr_svn78_222"

 onmouseover="gutterOver(222)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',222);">&nbsp;</span
></td><td id="222"><a href="#222">222</a></td></tr
><tr id="gr_svn78_223"

 onmouseover="gutterOver(223)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',223);">&nbsp;</span
></td><td id="223"><a href="#223">223</a></td></tr
><tr id="gr_svn78_224"

 onmouseover="gutterOver(224)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',224);">&nbsp;</span
></td><td id="224"><a href="#224">224</a></td></tr
><tr id="gr_svn78_225"

 onmouseover="gutterOver(225)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',225);">&nbsp;</span
></td><td id="225"><a href="#225">225</a></td></tr
><tr id="gr_svn78_226"

 onmouseover="gutterOver(226)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',226);">&nbsp;</span
></td><td id="226"><a href="#226">226</a></td></tr
><tr id="gr_svn78_227"

 onmouseover="gutterOver(227)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',227);">&nbsp;</span
></td><td id="227"><a href="#227">227</a></td></tr
><tr id="gr_svn78_228"

 onmouseover="gutterOver(228)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',228);">&nbsp;</span
></td><td id="228"><a href="#228">228</a></td></tr
><tr id="gr_svn78_229"

 onmouseover="gutterOver(229)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',229);">&nbsp;</span
></td><td id="229"><a href="#229">229</a></td></tr
><tr id="gr_svn78_230"

 onmouseover="gutterOver(230)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',230);">&nbsp;</span
></td><td id="230"><a href="#230">230</a></td></tr
><tr id="gr_svn78_231"

 onmouseover="gutterOver(231)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',231);">&nbsp;</span
></td><td id="231"><a href="#231">231</a></td></tr
><tr id="gr_svn78_232"

 onmouseover="gutterOver(232)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',232);">&nbsp;</span
></td><td id="232"><a href="#232">232</a></td></tr
><tr id="gr_svn78_233"

 onmouseover="gutterOver(233)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',233);">&nbsp;</span
></td><td id="233"><a href="#233">233</a></td></tr
><tr id="gr_svn78_234"

 onmouseover="gutterOver(234)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',234);">&nbsp;</span
></td><td id="234"><a href="#234">234</a></td></tr
><tr id="gr_svn78_235"

 onmouseover="gutterOver(235)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',235);">&nbsp;</span
></td><td id="235"><a href="#235">235</a></td></tr
><tr id="gr_svn78_236"

 onmouseover="gutterOver(236)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',236);">&nbsp;</span
></td><td id="236"><a href="#236">236</a></td></tr
><tr id="gr_svn78_237"

 onmouseover="gutterOver(237)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',237);">&nbsp;</span
></td><td id="237"><a href="#237">237</a></td></tr
><tr id="gr_svn78_238"

 onmouseover="gutterOver(238)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',238);">&nbsp;</span
></td><td id="238"><a href="#238">238</a></td></tr
><tr id="gr_svn78_239"

 onmouseover="gutterOver(239)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',239);">&nbsp;</span
></td><td id="239"><a href="#239">239</a></td></tr
><tr id="gr_svn78_240"

 onmouseover="gutterOver(240)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',240);">&nbsp;</span
></td><td id="240"><a href="#240">240</a></td></tr
><tr id="gr_svn78_241"

 onmouseover="gutterOver(241)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',241);">&nbsp;</span
></td><td id="241"><a href="#241">241</a></td></tr
><tr id="gr_svn78_242"

 onmouseover="gutterOver(242)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',242);">&nbsp;</span
></td><td id="242"><a href="#242">242</a></td></tr
><tr id="gr_svn78_243"

 onmouseover="gutterOver(243)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',243);">&nbsp;</span
></td><td id="243"><a href="#243">243</a></td></tr
><tr id="gr_svn78_244"

 onmouseover="gutterOver(244)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',244);">&nbsp;</span
></td><td id="244"><a href="#244">244</a></td></tr
><tr id="gr_svn78_245"

 onmouseover="gutterOver(245)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',245);">&nbsp;</span
></td><td id="245"><a href="#245">245</a></td></tr
><tr id="gr_svn78_246"

 onmouseover="gutterOver(246)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',246);">&nbsp;</span
></td><td id="246"><a href="#246">246</a></td></tr
><tr id="gr_svn78_247"

 onmouseover="gutterOver(247)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',247);">&nbsp;</span
></td><td id="247"><a href="#247">247</a></td></tr
><tr id="gr_svn78_248"

 onmouseover="gutterOver(248)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',248);">&nbsp;</span
></td><td id="248"><a href="#248">248</a></td></tr
><tr id="gr_svn78_249"

 onmouseover="gutterOver(249)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',249);">&nbsp;</span
></td><td id="249"><a href="#249">249</a></td></tr
><tr id="gr_svn78_250"

 onmouseover="gutterOver(250)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',250);">&nbsp;</span
></td><td id="250"><a href="#250">250</a></td></tr
><tr id="gr_svn78_251"

 onmouseover="gutterOver(251)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',251);">&nbsp;</span
></td><td id="251"><a href="#251">251</a></td></tr
><tr id="gr_svn78_252"

 onmouseover="gutterOver(252)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',252);">&nbsp;</span
></td><td id="252"><a href="#252">252</a></td></tr
><tr id="gr_svn78_253"

 onmouseover="gutterOver(253)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',253);">&nbsp;</span
></td><td id="253"><a href="#253">253</a></td></tr
><tr id="gr_svn78_254"

 onmouseover="gutterOver(254)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',254);">&nbsp;</span
></td><td id="254"><a href="#254">254</a></td></tr
><tr id="gr_svn78_255"

 onmouseover="gutterOver(255)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',255);">&nbsp;</span
></td><td id="255"><a href="#255">255</a></td></tr
><tr id="gr_svn78_256"

 onmouseover="gutterOver(256)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',256);">&nbsp;</span
></td><td id="256"><a href="#256">256</a></td></tr
><tr id="gr_svn78_257"

 onmouseover="gutterOver(257)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',257);">&nbsp;</span
></td><td id="257"><a href="#257">257</a></td></tr
><tr id="gr_svn78_258"

 onmouseover="gutterOver(258)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',258);">&nbsp;</span
></td><td id="258"><a href="#258">258</a></td></tr
><tr id="gr_svn78_259"

 onmouseover="gutterOver(259)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',259);">&nbsp;</span
></td><td id="259"><a href="#259">259</a></td></tr
><tr id="gr_svn78_260"

 onmouseover="gutterOver(260)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',260);">&nbsp;</span
></td><td id="260"><a href="#260">260</a></td></tr
><tr id="gr_svn78_261"

 onmouseover="gutterOver(261)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',261);">&nbsp;</span
></td><td id="261"><a href="#261">261</a></td></tr
><tr id="gr_svn78_262"

 onmouseover="gutterOver(262)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',262);">&nbsp;</span
></td><td id="262"><a href="#262">262</a></td></tr
><tr id="gr_svn78_263"

 onmouseover="gutterOver(263)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',263);">&nbsp;</span
></td><td id="263"><a href="#263">263</a></td></tr
><tr id="gr_svn78_264"

 onmouseover="gutterOver(264)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',264);">&nbsp;</span
></td><td id="264"><a href="#264">264</a></td></tr
><tr id="gr_svn78_265"

 onmouseover="gutterOver(265)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',265);">&nbsp;</span
></td><td id="265"><a href="#265">265</a></td></tr
><tr id="gr_svn78_266"

 onmouseover="gutterOver(266)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',266);">&nbsp;</span
></td><td id="266"><a href="#266">266</a></td></tr
><tr id="gr_svn78_267"

 onmouseover="gutterOver(267)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',267);">&nbsp;</span
></td><td id="267"><a href="#267">267</a></td></tr
><tr id="gr_svn78_268"

 onmouseover="gutterOver(268)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',268);">&nbsp;</span
></td><td id="268"><a href="#268">268</a></td></tr
><tr id="gr_svn78_269"

 onmouseover="gutterOver(269)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',269);">&nbsp;</span
></td><td id="269"><a href="#269">269</a></td></tr
><tr id="gr_svn78_270"

 onmouseover="gutterOver(270)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',270);">&nbsp;</span
></td><td id="270"><a href="#270">270</a></td></tr
><tr id="gr_svn78_271"

 onmouseover="gutterOver(271)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',271);">&nbsp;</span
></td><td id="271"><a href="#271">271</a></td></tr
><tr id="gr_svn78_272"

 onmouseover="gutterOver(272)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',272);">&nbsp;</span
></td><td id="272"><a href="#272">272</a></td></tr
><tr id="gr_svn78_273"

 onmouseover="gutterOver(273)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',273);">&nbsp;</span
></td><td id="273"><a href="#273">273</a></td></tr
><tr id="gr_svn78_274"

 onmouseover="gutterOver(274)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',274);">&nbsp;</span
></td><td id="274"><a href="#274">274</a></td></tr
><tr id="gr_svn78_275"

 onmouseover="gutterOver(275)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',275);">&nbsp;</span
></td><td id="275"><a href="#275">275</a></td></tr
><tr id="gr_svn78_276"

 onmouseover="gutterOver(276)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',276);">&nbsp;</span
></td><td id="276"><a href="#276">276</a></td></tr
><tr id="gr_svn78_277"

 onmouseover="gutterOver(277)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',277);">&nbsp;</span
></td><td id="277"><a href="#277">277</a></td></tr
><tr id="gr_svn78_278"

 onmouseover="gutterOver(278)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',278);">&nbsp;</span
></td><td id="278"><a href="#278">278</a></td></tr
><tr id="gr_svn78_279"

 onmouseover="gutterOver(279)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',279);">&nbsp;</span
></td><td id="279"><a href="#279">279</a></td></tr
><tr id="gr_svn78_280"

 onmouseover="gutterOver(280)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',280);">&nbsp;</span
></td><td id="280"><a href="#280">280</a></td></tr
><tr id="gr_svn78_281"

 onmouseover="gutterOver(281)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',281);">&nbsp;</span
></td><td id="281"><a href="#281">281</a></td></tr
><tr id="gr_svn78_282"

 onmouseover="gutterOver(282)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',282);">&nbsp;</span
></td><td id="282"><a href="#282">282</a></td></tr
><tr id="gr_svn78_283"

 onmouseover="gutterOver(283)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',283);">&nbsp;</span
></td><td id="283"><a href="#283">283</a></td></tr
><tr id="gr_svn78_284"

 onmouseover="gutterOver(284)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',284);">&nbsp;</span
></td><td id="284"><a href="#284">284</a></td></tr
><tr id="gr_svn78_285"

 onmouseover="gutterOver(285)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',285);">&nbsp;</span
></td><td id="285"><a href="#285">285</a></td></tr
><tr id="gr_svn78_286"

 onmouseover="gutterOver(286)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',286);">&nbsp;</span
></td><td id="286"><a href="#286">286</a></td></tr
><tr id="gr_svn78_287"

 onmouseover="gutterOver(287)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',287);">&nbsp;</span
></td><td id="287"><a href="#287">287</a></td></tr
><tr id="gr_svn78_288"

 onmouseover="gutterOver(288)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',288);">&nbsp;</span
></td><td id="288"><a href="#288">288</a></td></tr
><tr id="gr_svn78_289"

 onmouseover="gutterOver(289)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',289);">&nbsp;</span
></td><td id="289"><a href="#289">289</a></td></tr
><tr id="gr_svn78_290"

 onmouseover="gutterOver(290)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',290);">&nbsp;</span
></td><td id="290"><a href="#290">290</a></td></tr
><tr id="gr_svn78_291"

 onmouseover="gutterOver(291)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',291);">&nbsp;</span
></td><td id="291"><a href="#291">291</a></td></tr
><tr id="gr_svn78_292"

 onmouseover="gutterOver(292)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',292);">&nbsp;</span
></td><td id="292"><a href="#292">292</a></td></tr
><tr id="gr_svn78_293"

 onmouseover="gutterOver(293)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',293);">&nbsp;</span
></td><td id="293"><a href="#293">293</a></td></tr
><tr id="gr_svn78_294"

 onmouseover="gutterOver(294)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',294);">&nbsp;</span
></td><td id="294"><a href="#294">294</a></td></tr
><tr id="gr_svn78_295"

 onmouseover="gutterOver(295)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',295);">&nbsp;</span
></td><td id="295"><a href="#295">295</a></td></tr
><tr id="gr_svn78_296"

 onmouseover="gutterOver(296)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',296);">&nbsp;</span
></td><td id="296"><a href="#296">296</a></td></tr
><tr id="gr_svn78_297"

 onmouseover="gutterOver(297)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',297);">&nbsp;</span
></td><td id="297"><a href="#297">297</a></td></tr
><tr id="gr_svn78_298"

 onmouseover="gutterOver(298)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',298);">&nbsp;</span
></td><td id="298"><a href="#298">298</a></td></tr
><tr id="gr_svn78_299"

 onmouseover="gutterOver(299)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',299);">&nbsp;</span
></td><td id="299"><a href="#299">299</a></td></tr
><tr id="gr_svn78_300"

 onmouseover="gutterOver(300)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',300);">&nbsp;</span
></td><td id="300"><a href="#300">300</a></td></tr
><tr id="gr_svn78_301"

 onmouseover="gutterOver(301)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',301);">&nbsp;</span
></td><td id="301"><a href="#301">301</a></td></tr
><tr id="gr_svn78_302"

 onmouseover="gutterOver(302)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',302);">&nbsp;</span
></td><td id="302"><a href="#302">302</a></td></tr
><tr id="gr_svn78_303"

 onmouseover="gutterOver(303)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',303);">&nbsp;</span
></td><td id="303"><a href="#303">303</a></td></tr
><tr id="gr_svn78_304"

 onmouseover="gutterOver(304)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',304);">&nbsp;</span
></td><td id="304"><a href="#304">304</a></td></tr
><tr id="gr_svn78_305"

 onmouseover="gutterOver(305)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',305);">&nbsp;</span
></td><td id="305"><a href="#305">305</a></td></tr
><tr id="gr_svn78_306"

 onmouseover="gutterOver(306)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',306);">&nbsp;</span
></td><td id="306"><a href="#306">306</a></td></tr
><tr id="gr_svn78_307"

 onmouseover="gutterOver(307)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',307);">&nbsp;</span
></td><td id="307"><a href="#307">307</a></td></tr
><tr id="gr_svn78_308"

 onmouseover="gutterOver(308)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',308);">&nbsp;</span
></td><td id="308"><a href="#308">308</a></td></tr
><tr id="gr_svn78_309"

 onmouseover="gutterOver(309)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',309);">&nbsp;</span
></td><td id="309"><a href="#309">309</a></td></tr
><tr id="gr_svn78_310"

 onmouseover="gutterOver(310)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',310);">&nbsp;</span
></td><td id="310"><a href="#310">310</a></td></tr
><tr id="gr_svn78_311"

 onmouseover="gutterOver(311)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',311);">&nbsp;</span
></td><td id="311"><a href="#311">311</a></td></tr
><tr id="gr_svn78_312"

 onmouseover="gutterOver(312)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',312);">&nbsp;</span
></td><td id="312"><a href="#312">312</a></td></tr
><tr id="gr_svn78_313"

 onmouseover="gutterOver(313)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',313);">&nbsp;</span
></td><td id="313"><a href="#313">313</a></td></tr
><tr id="gr_svn78_314"

 onmouseover="gutterOver(314)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',314);">&nbsp;</span
></td><td id="314"><a href="#314">314</a></td></tr
><tr id="gr_svn78_315"

 onmouseover="gutterOver(315)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',315);">&nbsp;</span
></td><td id="315"><a href="#315">315</a></td></tr
><tr id="gr_svn78_316"

 onmouseover="gutterOver(316)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',316);">&nbsp;</span
></td><td id="316"><a href="#316">316</a></td></tr
><tr id="gr_svn78_317"

 onmouseover="gutterOver(317)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',317);">&nbsp;</span
></td><td id="317"><a href="#317">317</a></td></tr
><tr id="gr_svn78_318"

 onmouseover="gutterOver(318)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',318);">&nbsp;</span
></td><td id="318"><a href="#318">318</a></td></tr
><tr id="gr_svn78_319"

 onmouseover="gutterOver(319)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',319);">&nbsp;</span
></td><td id="319"><a href="#319">319</a></td></tr
><tr id="gr_svn78_320"

 onmouseover="gutterOver(320)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',320);">&nbsp;</span
></td><td id="320"><a href="#320">320</a></td></tr
><tr id="gr_svn78_321"

 onmouseover="gutterOver(321)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',321);">&nbsp;</span
></td><td id="321"><a href="#321">321</a></td></tr
><tr id="gr_svn78_322"

 onmouseover="gutterOver(322)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',322);">&nbsp;</span
></td><td id="322"><a href="#322">322</a></td></tr
><tr id="gr_svn78_323"

 onmouseover="gutterOver(323)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',323);">&nbsp;</span
></td><td id="323"><a href="#323">323</a></td></tr
><tr id="gr_svn78_324"

 onmouseover="gutterOver(324)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',324);">&nbsp;</span
></td><td id="324"><a href="#324">324</a></td></tr
><tr id="gr_svn78_325"

 onmouseover="gutterOver(325)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',325);">&nbsp;</span
></td><td id="325"><a href="#325">325</a></td></tr
><tr id="gr_svn78_326"

 onmouseover="gutterOver(326)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',326);">&nbsp;</span
></td><td id="326"><a href="#326">326</a></td></tr
><tr id="gr_svn78_327"

 onmouseover="gutterOver(327)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',327);">&nbsp;</span
></td><td id="327"><a href="#327">327</a></td></tr
><tr id="gr_svn78_328"

 onmouseover="gutterOver(328)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',328);">&nbsp;</span
></td><td id="328"><a href="#328">328</a></td></tr
><tr id="gr_svn78_329"

 onmouseover="gutterOver(329)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',329);">&nbsp;</span
></td><td id="329"><a href="#329">329</a></td></tr
><tr id="gr_svn78_330"

 onmouseover="gutterOver(330)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',330);">&nbsp;</span
></td><td id="330"><a href="#330">330</a></td></tr
><tr id="gr_svn78_331"

 onmouseover="gutterOver(331)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',331);">&nbsp;</span
></td><td id="331"><a href="#331">331</a></td></tr
><tr id="gr_svn78_332"

 onmouseover="gutterOver(332)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',332);">&nbsp;</span
></td><td id="332"><a href="#332">332</a></td></tr
><tr id="gr_svn78_333"

 onmouseover="gutterOver(333)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',333);">&nbsp;</span
></td><td id="333"><a href="#333">333</a></td></tr
><tr id="gr_svn78_334"

 onmouseover="gutterOver(334)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',334);">&nbsp;</span
></td><td id="334"><a href="#334">334</a></td></tr
><tr id="gr_svn78_335"

 onmouseover="gutterOver(335)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',335);">&nbsp;</span
></td><td id="335"><a href="#335">335</a></td></tr
><tr id="gr_svn78_336"

 onmouseover="gutterOver(336)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',336);">&nbsp;</span
></td><td id="336"><a href="#336">336</a></td></tr
><tr id="gr_svn78_337"

 onmouseover="gutterOver(337)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',337);">&nbsp;</span
></td><td id="337"><a href="#337">337</a></td></tr
><tr id="gr_svn78_338"

 onmouseover="gutterOver(338)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',338);">&nbsp;</span
></td><td id="338"><a href="#338">338</a></td></tr
><tr id="gr_svn78_339"

 onmouseover="gutterOver(339)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',339);">&nbsp;</span
></td><td id="339"><a href="#339">339</a></td></tr
><tr id="gr_svn78_340"

 onmouseover="gutterOver(340)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',340);">&nbsp;</span
></td><td id="340"><a href="#340">340</a></td></tr
><tr id="gr_svn78_341"

 onmouseover="gutterOver(341)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',341);">&nbsp;</span
></td><td id="341"><a href="#341">341</a></td></tr
><tr id="gr_svn78_342"

 onmouseover="gutterOver(342)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',342);">&nbsp;</span
></td><td id="342"><a href="#342">342</a></td></tr
><tr id="gr_svn78_343"

 onmouseover="gutterOver(343)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',343);">&nbsp;</span
></td><td id="343"><a href="#343">343</a></td></tr
><tr id="gr_svn78_344"

 onmouseover="gutterOver(344)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',344);">&nbsp;</span
></td><td id="344"><a href="#344">344</a></td></tr
><tr id="gr_svn78_345"

 onmouseover="gutterOver(345)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',345);">&nbsp;</span
></td><td id="345"><a href="#345">345</a></td></tr
><tr id="gr_svn78_346"

 onmouseover="gutterOver(346)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',346);">&nbsp;</span
></td><td id="346"><a href="#346">346</a></td></tr
><tr id="gr_svn78_347"

 onmouseover="gutterOver(347)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',347);">&nbsp;</span
></td><td id="347"><a href="#347">347</a></td></tr
><tr id="gr_svn78_348"

 onmouseover="gutterOver(348)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',348);">&nbsp;</span
></td><td id="348"><a href="#348">348</a></td></tr
><tr id="gr_svn78_349"

 onmouseover="gutterOver(349)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',349);">&nbsp;</span
></td><td id="349"><a href="#349">349</a></td></tr
><tr id="gr_svn78_350"

 onmouseover="gutterOver(350)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',350);">&nbsp;</span
></td><td id="350"><a href="#350">350</a></td></tr
><tr id="gr_svn78_351"

 onmouseover="gutterOver(351)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',351);">&nbsp;</span
></td><td id="351"><a href="#351">351</a></td></tr
><tr id="gr_svn78_352"

 onmouseover="gutterOver(352)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',352);">&nbsp;</span
></td><td id="352"><a href="#352">352</a></td></tr
><tr id="gr_svn78_353"

 onmouseover="gutterOver(353)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',353);">&nbsp;</span
></td><td id="353"><a href="#353">353</a></td></tr
><tr id="gr_svn78_354"

 onmouseover="gutterOver(354)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',354);">&nbsp;</span
></td><td id="354"><a href="#354">354</a></td></tr
><tr id="gr_svn78_355"

 onmouseover="gutterOver(355)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',355);">&nbsp;</span
></td><td id="355"><a href="#355">355</a></td></tr
><tr id="gr_svn78_356"

 onmouseover="gutterOver(356)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',356);">&nbsp;</span
></td><td id="356"><a href="#356">356</a></td></tr
><tr id="gr_svn78_357"

 onmouseover="gutterOver(357)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',357);">&nbsp;</span
></td><td id="357"><a href="#357">357</a></td></tr
><tr id="gr_svn78_358"

 onmouseover="gutterOver(358)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',358);">&nbsp;</span
></td><td id="358"><a href="#358">358</a></td></tr
><tr id="gr_svn78_359"

 onmouseover="gutterOver(359)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',359);">&nbsp;</span
></td><td id="359"><a href="#359">359</a></td></tr
><tr id="gr_svn78_360"

 onmouseover="gutterOver(360)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',360);">&nbsp;</span
></td><td id="360"><a href="#360">360</a></td></tr
><tr id="gr_svn78_361"

 onmouseover="gutterOver(361)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',361);">&nbsp;</span
></td><td id="361"><a href="#361">361</a></td></tr
><tr id="gr_svn78_362"

 onmouseover="gutterOver(362)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',362);">&nbsp;</span
></td><td id="362"><a href="#362">362</a></td></tr
><tr id="gr_svn78_363"

 onmouseover="gutterOver(363)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',363);">&nbsp;</span
></td><td id="363"><a href="#363">363</a></td></tr
><tr id="gr_svn78_364"

 onmouseover="gutterOver(364)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',364);">&nbsp;</span
></td><td id="364"><a href="#364">364</a></td></tr
><tr id="gr_svn78_365"

 onmouseover="gutterOver(365)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',365);">&nbsp;</span
></td><td id="365"><a href="#365">365</a></td></tr
><tr id="gr_svn78_366"

 onmouseover="gutterOver(366)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',366);">&nbsp;</span
></td><td id="366"><a href="#366">366</a></td></tr
><tr id="gr_svn78_367"

 onmouseover="gutterOver(367)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',367);">&nbsp;</span
></td><td id="367"><a href="#367">367</a></td></tr
><tr id="gr_svn78_368"

 onmouseover="gutterOver(368)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',368);">&nbsp;</span
></td><td id="368"><a href="#368">368</a></td></tr
><tr id="gr_svn78_369"

 onmouseover="gutterOver(369)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',369);">&nbsp;</span
></td><td id="369"><a href="#369">369</a></td></tr
><tr id="gr_svn78_370"

 onmouseover="gutterOver(370)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',370);">&nbsp;</span
></td><td id="370"><a href="#370">370</a></td></tr
><tr id="gr_svn78_371"

 onmouseover="gutterOver(371)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',371);">&nbsp;</span
></td><td id="371"><a href="#371">371</a></td></tr
><tr id="gr_svn78_372"

 onmouseover="gutterOver(372)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',372);">&nbsp;</span
></td><td id="372"><a href="#372">372</a></td></tr
><tr id="gr_svn78_373"

 onmouseover="gutterOver(373)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',373);">&nbsp;</span
></td><td id="373"><a href="#373">373</a></td></tr
><tr id="gr_svn78_374"

 onmouseover="gutterOver(374)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',374);">&nbsp;</span
></td><td id="374"><a href="#374">374</a></td></tr
><tr id="gr_svn78_375"

 onmouseover="gutterOver(375)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',375);">&nbsp;</span
></td><td id="375"><a href="#375">375</a></td></tr
><tr id="gr_svn78_376"

 onmouseover="gutterOver(376)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',376);">&nbsp;</span
></td><td id="376"><a href="#376">376</a></td></tr
><tr id="gr_svn78_377"

 onmouseover="gutterOver(377)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',377);">&nbsp;</span
></td><td id="377"><a href="#377">377</a></td></tr
><tr id="gr_svn78_378"

 onmouseover="gutterOver(378)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',378);">&nbsp;</span
></td><td id="378"><a href="#378">378</a></td></tr
><tr id="gr_svn78_379"

 onmouseover="gutterOver(379)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',379);">&nbsp;</span
></td><td id="379"><a href="#379">379</a></td></tr
><tr id="gr_svn78_380"

 onmouseover="gutterOver(380)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',380);">&nbsp;</span
></td><td id="380"><a href="#380">380</a></td></tr
><tr id="gr_svn78_381"

 onmouseover="gutterOver(381)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',381);">&nbsp;</span
></td><td id="381"><a href="#381">381</a></td></tr
><tr id="gr_svn78_382"

 onmouseover="gutterOver(382)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',382);">&nbsp;</span
></td><td id="382"><a href="#382">382</a></td></tr
><tr id="gr_svn78_383"

 onmouseover="gutterOver(383)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',383);">&nbsp;</span
></td><td id="383"><a href="#383">383</a></td></tr
><tr id="gr_svn78_384"

 onmouseover="gutterOver(384)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',384);">&nbsp;</span
></td><td id="384"><a href="#384">384</a></td></tr
><tr id="gr_svn78_385"

 onmouseover="gutterOver(385)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',385);">&nbsp;</span
></td><td id="385"><a href="#385">385</a></td></tr
><tr id="gr_svn78_386"

 onmouseover="gutterOver(386)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',386);">&nbsp;</span
></td><td id="386"><a href="#386">386</a></td></tr
><tr id="gr_svn78_387"

 onmouseover="gutterOver(387)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',387);">&nbsp;</span
></td><td id="387"><a href="#387">387</a></td></tr
><tr id="gr_svn78_388"

 onmouseover="gutterOver(388)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',388);">&nbsp;</span
></td><td id="388"><a href="#388">388</a></td></tr
><tr id="gr_svn78_389"

 onmouseover="gutterOver(389)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',389);">&nbsp;</span
></td><td id="389"><a href="#389">389</a></td></tr
><tr id="gr_svn78_390"

 onmouseover="gutterOver(390)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',390);">&nbsp;</span
></td><td id="390"><a href="#390">390</a></td></tr
><tr id="gr_svn78_391"

 onmouseover="gutterOver(391)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',391);">&nbsp;</span
></td><td id="391"><a href="#391">391</a></td></tr
><tr id="gr_svn78_392"

 onmouseover="gutterOver(392)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',392);">&nbsp;</span
></td><td id="392"><a href="#392">392</a></td></tr
><tr id="gr_svn78_393"

 onmouseover="gutterOver(393)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',393);">&nbsp;</span
></td><td id="393"><a href="#393">393</a></td></tr
><tr id="gr_svn78_394"

 onmouseover="gutterOver(394)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',394);">&nbsp;</span
></td><td id="394"><a href="#394">394</a></td></tr
><tr id="gr_svn78_395"

 onmouseover="gutterOver(395)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',395);">&nbsp;</span
></td><td id="395"><a href="#395">395</a></td></tr
><tr id="gr_svn78_396"

 onmouseover="gutterOver(396)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',396);">&nbsp;</span
></td><td id="396"><a href="#396">396</a></td></tr
><tr id="gr_svn78_397"

 onmouseover="gutterOver(397)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',397);">&nbsp;</span
></td><td id="397"><a href="#397">397</a></td></tr
><tr id="gr_svn78_398"

 onmouseover="gutterOver(398)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',398);">&nbsp;</span
></td><td id="398"><a href="#398">398</a></td></tr
><tr id="gr_svn78_399"

 onmouseover="gutterOver(399)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',399);">&nbsp;</span
></td><td id="399"><a href="#399">399</a></td></tr
><tr id="gr_svn78_400"

 onmouseover="gutterOver(400)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',400);">&nbsp;</span
></td><td id="400"><a href="#400">400</a></td></tr
><tr id="gr_svn78_401"

 onmouseover="gutterOver(401)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',401);">&nbsp;</span
></td><td id="401"><a href="#401">401</a></td></tr
><tr id="gr_svn78_402"

 onmouseover="gutterOver(402)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',402);">&nbsp;</span
></td><td id="402"><a href="#402">402</a></td></tr
><tr id="gr_svn78_403"

 onmouseover="gutterOver(403)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',403);">&nbsp;</span
></td><td id="403"><a href="#403">403</a></td></tr
><tr id="gr_svn78_404"

 onmouseover="gutterOver(404)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',404);">&nbsp;</span
></td><td id="404"><a href="#404">404</a></td></tr
><tr id="gr_svn78_405"

 onmouseover="gutterOver(405)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',405);">&nbsp;</span
></td><td id="405"><a href="#405">405</a></td></tr
><tr id="gr_svn78_406"

 onmouseover="gutterOver(406)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',406);">&nbsp;</span
></td><td id="406"><a href="#406">406</a></td></tr
><tr id="gr_svn78_407"

 onmouseover="gutterOver(407)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',407);">&nbsp;</span
></td><td id="407"><a href="#407">407</a></td></tr
><tr id="gr_svn78_408"

 onmouseover="gutterOver(408)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',408);">&nbsp;</span
></td><td id="408"><a href="#408">408</a></td></tr
><tr id="gr_svn78_409"

 onmouseover="gutterOver(409)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',409);">&nbsp;</span
></td><td id="409"><a href="#409">409</a></td></tr
><tr id="gr_svn78_410"

 onmouseover="gutterOver(410)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',410);">&nbsp;</span
></td><td id="410"><a href="#410">410</a></td></tr
><tr id="gr_svn78_411"

 onmouseover="gutterOver(411)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',411);">&nbsp;</span
></td><td id="411"><a href="#411">411</a></td></tr
><tr id="gr_svn78_412"

 onmouseover="gutterOver(412)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',412);">&nbsp;</span
></td><td id="412"><a href="#412">412</a></td></tr
><tr id="gr_svn78_413"

 onmouseover="gutterOver(413)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',413);">&nbsp;</span
></td><td id="413"><a href="#413">413</a></td></tr
><tr id="gr_svn78_414"

 onmouseover="gutterOver(414)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',414);">&nbsp;</span
></td><td id="414"><a href="#414">414</a></td></tr
><tr id="gr_svn78_415"

 onmouseover="gutterOver(415)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',415);">&nbsp;</span
></td><td id="415"><a href="#415">415</a></td></tr
><tr id="gr_svn78_416"

 onmouseover="gutterOver(416)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',416);">&nbsp;</span
></td><td id="416"><a href="#416">416</a></td></tr
><tr id="gr_svn78_417"

 onmouseover="gutterOver(417)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',417);">&nbsp;</span
></td><td id="417"><a href="#417">417</a></td></tr
><tr id="gr_svn78_418"

 onmouseover="gutterOver(418)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',418);">&nbsp;</span
></td><td id="418"><a href="#418">418</a></td></tr
><tr id="gr_svn78_419"

 onmouseover="gutterOver(419)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',419);">&nbsp;</span
></td><td id="419"><a href="#419">419</a></td></tr
><tr id="gr_svn78_420"

 onmouseover="gutterOver(420)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',420);">&nbsp;</span
></td><td id="420"><a href="#420">420</a></td></tr
><tr id="gr_svn78_421"

 onmouseover="gutterOver(421)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',421);">&nbsp;</span
></td><td id="421"><a href="#421">421</a></td></tr
><tr id="gr_svn78_422"

 onmouseover="gutterOver(422)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',422);">&nbsp;</span
></td><td id="422"><a href="#422">422</a></td></tr
><tr id="gr_svn78_423"

 onmouseover="gutterOver(423)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',423);">&nbsp;</span
></td><td id="423"><a href="#423">423</a></td></tr
><tr id="gr_svn78_424"

 onmouseover="gutterOver(424)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',424);">&nbsp;</span
></td><td id="424"><a href="#424">424</a></td></tr
><tr id="gr_svn78_425"

 onmouseover="gutterOver(425)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',425);">&nbsp;</span
></td><td id="425"><a href="#425">425</a></td></tr
><tr id="gr_svn78_426"

 onmouseover="gutterOver(426)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',426);">&nbsp;</span
></td><td id="426"><a href="#426">426</a></td></tr
><tr id="gr_svn78_427"

 onmouseover="gutterOver(427)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',427);">&nbsp;</span
></td><td id="427"><a href="#427">427</a></td></tr
><tr id="gr_svn78_428"

 onmouseover="gutterOver(428)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',428);">&nbsp;</span
></td><td id="428"><a href="#428">428</a></td></tr
><tr id="gr_svn78_429"

 onmouseover="gutterOver(429)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',429);">&nbsp;</span
></td><td id="429"><a href="#429">429</a></td></tr
><tr id="gr_svn78_430"

 onmouseover="gutterOver(430)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',430);">&nbsp;</span
></td><td id="430"><a href="#430">430</a></td></tr
><tr id="gr_svn78_431"

 onmouseover="gutterOver(431)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',431);">&nbsp;</span
></td><td id="431"><a href="#431">431</a></td></tr
><tr id="gr_svn78_432"

 onmouseover="gutterOver(432)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',432);">&nbsp;</span
></td><td id="432"><a href="#432">432</a></td></tr
><tr id="gr_svn78_433"

 onmouseover="gutterOver(433)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',433);">&nbsp;</span
></td><td id="433"><a href="#433">433</a></td></tr
><tr id="gr_svn78_434"

 onmouseover="gutterOver(434)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',434);">&nbsp;</span
></td><td id="434"><a href="#434">434</a></td></tr
><tr id="gr_svn78_435"

 onmouseover="gutterOver(435)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',435);">&nbsp;</span
></td><td id="435"><a href="#435">435</a></td></tr
><tr id="gr_svn78_436"

 onmouseover="gutterOver(436)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',436);">&nbsp;</span
></td><td id="436"><a href="#436">436</a></td></tr
><tr id="gr_svn78_437"

 onmouseover="gutterOver(437)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',437);">&nbsp;</span
></td><td id="437"><a href="#437">437</a></td></tr
><tr id="gr_svn78_438"

 onmouseover="gutterOver(438)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',438);">&nbsp;</span
></td><td id="438"><a href="#438">438</a></td></tr
><tr id="gr_svn78_439"

 onmouseover="gutterOver(439)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',439);">&nbsp;</span
></td><td id="439"><a href="#439">439</a></td></tr
><tr id="gr_svn78_440"

 onmouseover="gutterOver(440)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',440);">&nbsp;</span
></td><td id="440"><a href="#440">440</a></td></tr
><tr id="gr_svn78_441"

 onmouseover="gutterOver(441)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',441);">&nbsp;</span
></td><td id="441"><a href="#441">441</a></td></tr
><tr id="gr_svn78_442"

 onmouseover="gutterOver(442)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',442);">&nbsp;</span
></td><td id="442"><a href="#442">442</a></td></tr
><tr id="gr_svn78_443"

 onmouseover="gutterOver(443)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',443);">&nbsp;</span
></td><td id="443"><a href="#443">443</a></td></tr
><tr id="gr_svn78_444"

 onmouseover="gutterOver(444)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',444);">&nbsp;</span
></td><td id="444"><a href="#444">444</a></td></tr
><tr id="gr_svn78_445"

 onmouseover="gutterOver(445)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',445);">&nbsp;</span
></td><td id="445"><a href="#445">445</a></td></tr
><tr id="gr_svn78_446"

 onmouseover="gutterOver(446)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',446);">&nbsp;</span
></td><td id="446"><a href="#446">446</a></td></tr
><tr id="gr_svn78_447"

 onmouseover="gutterOver(447)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',447);">&nbsp;</span
></td><td id="447"><a href="#447">447</a></td></tr
><tr id="gr_svn78_448"

 onmouseover="gutterOver(448)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',448);">&nbsp;</span
></td><td id="448"><a href="#448">448</a></td></tr
><tr id="gr_svn78_449"

 onmouseover="gutterOver(449)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',449);">&nbsp;</span
></td><td id="449"><a href="#449">449</a></td></tr
><tr id="gr_svn78_450"

 onmouseover="gutterOver(450)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',450);">&nbsp;</span
></td><td id="450"><a href="#450">450</a></td></tr
><tr id="gr_svn78_451"

 onmouseover="gutterOver(451)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',451);">&nbsp;</span
></td><td id="451"><a href="#451">451</a></td></tr
><tr id="gr_svn78_452"

 onmouseover="gutterOver(452)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',452);">&nbsp;</span
></td><td id="452"><a href="#452">452</a></td></tr
><tr id="gr_svn78_453"

 onmouseover="gutterOver(453)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',453);">&nbsp;</span
></td><td id="453"><a href="#453">453</a></td></tr
><tr id="gr_svn78_454"

 onmouseover="gutterOver(454)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',454);">&nbsp;</span
></td><td id="454"><a href="#454">454</a></td></tr
><tr id="gr_svn78_455"

 onmouseover="gutterOver(455)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',455);">&nbsp;</span
></td><td id="455"><a href="#455">455</a></td></tr
><tr id="gr_svn78_456"

 onmouseover="gutterOver(456)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',456);">&nbsp;</span
></td><td id="456"><a href="#456">456</a></td></tr
><tr id="gr_svn78_457"

 onmouseover="gutterOver(457)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',457);">&nbsp;</span
></td><td id="457"><a href="#457">457</a></td></tr
><tr id="gr_svn78_458"

 onmouseover="gutterOver(458)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',458);">&nbsp;</span
></td><td id="458"><a href="#458">458</a></td></tr
><tr id="gr_svn78_459"

 onmouseover="gutterOver(459)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',459);">&nbsp;</span
></td><td id="459"><a href="#459">459</a></td></tr
><tr id="gr_svn78_460"

 onmouseover="gutterOver(460)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',460);">&nbsp;</span
></td><td id="460"><a href="#460">460</a></td></tr
><tr id="gr_svn78_461"

 onmouseover="gutterOver(461)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',461);">&nbsp;</span
></td><td id="461"><a href="#461">461</a></td></tr
><tr id="gr_svn78_462"

 onmouseover="gutterOver(462)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',462);">&nbsp;</span
></td><td id="462"><a href="#462">462</a></td></tr
><tr id="gr_svn78_463"

 onmouseover="gutterOver(463)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',463);">&nbsp;</span
></td><td id="463"><a href="#463">463</a></td></tr
><tr id="gr_svn78_464"

 onmouseover="gutterOver(464)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',464);">&nbsp;</span
></td><td id="464"><a href="#464">464</a></td></tr
><tr id="gr_svn78_465"

 onmouseover="gutterOver(465)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',465);">&nbsp;</span
></td><td id="465"><a href="#465">465</a></td></tr
><tr id="gr_svn78_466"

 onmouseover="gutterOver(466)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',466);">&nbsp;</span
></td><td id="466"><a href="#466">466</a></td></tr
><tr id="gr_svn78_467"

 onmouseover="gutterOver(467)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',467);">&nbsp;</span
></td><td id="467"><a href="#467">467</a></td></tr
><tr id="gr_svn78_468"

 onmouseover="gutterOver(468)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',468);">&nbsp;</span
></td><td id="468"><a href="#468">468</a></td></tr
><tr id="gr_svn78_469"

 onmouseover="gutterOver(469)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',469);">&nbsp;</span
></td><td id="469"><a href="#469">469</a></td></tr
><tr id="gr_svn78_470"

 onmouseover="gutterOver(470)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',470);">&nbsp;</span
></td><td id="470"><a href="#470">470</a></td></tr
><tr id="gr_svn78_471"

 onmouseover="gutterOver(471)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',471);">&nbsp;</span
></td><td id="471"><a href="#471">471</a></td></tr
><tr id="gr_svn78_472"

 onmouseover="gutterOver(472)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',472);">&nbsp;</span
></td><td id="472"><a href="#472">472</a></td></tr
><tr id="gr_svn78_473"

 onmouseover="gutterOver(473)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',473);">&nbsp;</span
></td><td id="473"><a href="#473">473</a></td></tr
><tr id="gr_svn78_474"

 onmouseover="gutterOver(474)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',474);">&nbsp;</span
></td><td id="474"><a href="#474">474</a></td></tr
><tr id="gr_svn78_475"

 onmouseover="gutterOver(475)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',475);">&nbsp;</span
></td><td id="475"><a href="#475">475</a></td></tr
><tr id="gr_svn78_476"

 onmouseover="gutterOver(476)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',476);">&nbsp;</span
></td><td id="476"><a href="#476">476</a></td></tr
><tr id="gr_svn78_477"

 onmouseover="gutterOver(477)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',477);">&nbsp;</span
></td><td id="477"><a href="#477">477</a></td></tr
><tr id="gr_svn78_478"

 onmouseover="gutterOver(478)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',478);">&nbsp;</span
></td><td id="478"><a href="#478">478</a></td></tr
><tr id="gr_svn78_479"

 onmouseover="gutterOver(479)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',479);">&nbsp;</span
></td><td id="479"><a href="#479">479</a></td></tr
><tr id="gr_svn78_480"

 onmouseover="gutterOver(480)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',480);">&nbsp;</span
></td><td id="480"><a href="#480">480</a></td></tr
><tr id="gr_svn78_481"

 onmouseover="gutterOver(481)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',481);">&nbsp;</span
></td><td id="481"><a href="#481">481</a></td></tr
><tr id="gr_svn78_482"

 onmouseover="gutterOver(482)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',482);">&nbsp;</span
></td><td id="482"><a href="#482">482</a></td></tr
><tr id="gr_svn78_483"

 onmouseover="gutterOver(483)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',483);">&nbsp;</span
></td><td id="483"><a href="#483">483</a></td></tr
><tr id="gr_svn78_484"

 onmouseover="gutterOver(484)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',484);">&nbsp;</span
></td><td id="484"><a href="#484">484</a></td></tr
><tr id="gr_svn78_485"

 onmouseover="gutterOver(485)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',485);">&nbsp;</span
></td><td id="485"><a href="#485">485</a></td></tr
><tr id="gr_svn78_486"

 onmouseover="gutterOver(486)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',486);">&nbsp;</span
></td><td id="486"><a href="#486">486</a></td></tr
><tr id="gr_svn78_487"

 onmouseover="gutterOver(487)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',487);">&nbsp;</span
></td><td id="487"><a href="#487">487</a></td></tr
><tr id="gr_svn78_488"

 onmouseover="gutterOver(488)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',488);">&nbsp;</span
></td><td id="488"><a href="#488">488</a></td></tr
><tr id="gr_svn78_489"

 onmouseover="gutterOver(489)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',489);">&nbsp;</span
></td><td id="489"><a href="#489">489</a></td></tr
><tr id="gr_svn78_490"

 onmouseover="gutterOver(490)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',490);">&nbsp;</span
></td><td id="490"><a href="#490">490</a></td></tr
><tr id="gr_svn78_491"

 onmouseover="gutterOver(491)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',491);">&nbsp;</span
></td><td id="491"><a href="#491">491</a></td></tr
><tr id="gr_svn78_492"

 onmouseover="gutterOver(492)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',492);">&nbsp;</span
></td><td id="492"><a href="#492">492</a></td></tr
><tr id="gr_svn78_493"

 onmouseover="gutterOver(493)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',493);">&nbsp;</span
></td><td id="493"><a href="#493">493</a></td></tr
><tr id="gr_svn78_494"

 onmouseover="gutterOver(494)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',494);">&nbsp;</span
></td><td id="494"><a href="#494">494</a></td></tr
><tr id="gr_svn78_495"

 onmouseover="gutterOver(495)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',495);">&nbsp;</span
></td><td id="495"><a href="#495">495</a></td></tr
><tr id="gr_svn78_496"

 onmouseover="gutterOver(496)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',496);">&nbsp;</span
></td><td id="496"><a href="#496">496</a></td></tr
><tr id="gr_svn78_497"

 onmouseover="gutterOver(497)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',497);">&nbsp;</span
></td><td id="497"><a href="#497">497</a></td></tr
><tr id="gr_svn78_498"

 onmouseover="gutterOver(498)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',498);">&nbsp;</span
></td><td id="498"><a href="#498">498</a></td></tr
><tr id="gr_svn78_499"

 onmouseover="gutterOver(499)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',499);">&nbsp;</span
></td><td id="499"><a href="#499">499</a></td></tr
><tr id="gr_svn78_500"

 onmouseover="gutterOver(500)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',500);">&nbsp;</span
></td><td id="500"><a href="#500">500</a></td></tr
><tr id="gr_svn78_501"

 onmouseover="gutterOver(501)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',501);">&nbsp;</span
></td><td id="501"><a href="#501">501</a></td></tr
><tr id="gr_svn78_502"

 onmouseover="gutterOver(502)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',502);">&nbsp;</span
></td><td id="502"><a href="#502">502</a></td></tr
><tr id="gr_svn78_503"

 onmouseover="gutterOver(503)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',503);">&nbsp;</span
></td><td id="503"><a href="#503">503</a></td></tr
><tr id="gr_svn78_504"

 onmouseover="gutterOver(504)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',504);">&nbsp;</span
></td><td id="504"><a href="#504">504</a></td></tr
><tr id="gr_svn78_505"

 onmouseover="gutterOver(505)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',505);">&nbsp;</span
></td><td id="505"><a href="#505">505</a></td></tr
><tr id="gr_svn78_506"

 onmouseover="gutterOver(506)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',506);">&nbsp;</span
></td><td id="506"><a href="#506">506</a></td></tr
><tr id="gr_svn78_507"

 onmouseover="gutterOver(507)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',507);">&nbsp;</span
></td><td id="507"><a href="#507">507</a></td></tr
><tr id="gr_svn78_508"

 onmouseover="gutterOver(508)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',508);">&nbsp;</span
></td><td id="508"><a href="#508">508</a></td></tr
><tr id="gr_svn78_509"

 onmouseover="gutterOver(509)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',509);">&nbsp;</span
></td><td id="509"><a href="#509">509</a></td></tr
><tr id="gr_svn78_510"

 onmouseover="gutterOver(510)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',510);">&nbsp;</span
></td><td id="510"><a href="#510">510</a></td></tr
><tr id="gr_svn78_511"

 onmouseover="gutterOver(511)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',511);">&nbsp;</span
></td><td id="511"><a href="#511">511</a></td></tr
><tr id="gr_svn78_512"

 onmouseover="gutterOver(512)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',512);">&nbsp;</span
></td><td id="512"><a href="#512">512</a></td></tr
><tr id="gr_svn78_513"

 onmouseover="gutterOver(513)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',513);">&nbsp;</span
></td><td id="513"><a href="#513">513</a></td></tr
><tr id="gr_svn78_514"

 onmouseover="gutterOver(514)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',514);">&nbsp;</span
></td><td id="514"><a href="#514">514</a></td></tr
><tr id="gr_svn78_515"

 onmouseover="gutterOver(515)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',515);">&nbsp;</span
></td><td id="515"><a href="#515">515</a></td></tr
><tr id="gr_svn78_516"

 onmouseover="gutterOver(516)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',516);">&nbsp;</span
></td><td id="516"><a href="#516">516</a></td></tr
><tr id="gr_svn78_517"

 onmouseover="gutterOver(517)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',517);">&nbsp;</span
></td><td id="517"><a href="#517">517</a></td></tr
><tr id="gr_svn78_518"

 onmouseover="gutterOver(518)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',518);">&nbsp;</span
></td><td id="518"><a href="#518">518</a></td></tr
><tr id="gr_svn78_519"

 onmouseover="gutterOver(519)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',519);">&nbsp;</span
></td><td id="519"><a href="#519">519</a></td></tr
><tr id="gr_svn78_520"

 onmouseover="gutterOver(520)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',520);">&nbsp;</span
></td><td id="520"><a href="#520">520</a></td></tr
><tr id="gr_svn78_521"

 onmouseover="gutterOver(521)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',521);">&nbsp;</span
></td><td id="521"><a href="#521">521</a></td></tr
><tr id="gr_svn78_522"

 onmouseover="gutterOver(522)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',522);">&nbsp;</span
></td><td id="522"><a href="#522">522</a></td></tr
><tr id="gr_svn78_523"

 onmouseover="gutterOver(523)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',523);">&nbsp;</span
></td><td id="523"><a href="#523">523</a></td></tr
><tr id="gr_svn78_524"

 onmouseover="gutterOver(524)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',524);">&nbsp;</span
></td><td id="524"><a href="#524">524</a></td></tr
><tr id="gr_svn78_525"

 onmouseover="gutterOver(525)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',525);">&nbsp;</span
></td><td id="525"><a href="#525">525</a></td></tr
><tr id="gr_svn78_526"

 onmouseover="gutterOver(526)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',526);">&nbsp;</span
></td><td id="526"><a href="#526">526</a></td></tr
><tr id="gr_svn78_527"

 onmouseover="gutterOver(527)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',527);">&nbsp;</span
></td><td id="527"><a href="#527">527</a></td></tr
><tr id="gr_svn78_528"

 onmouseover="gutterOver(528)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',528);">&nbsp;</span
></td><td id="528"><a href="#528">528</a></td></tr
><tr id="gr_svn78_529"

 onmouseover="gutterOver(529)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',529);">&nbsp;</span
></td><td id="529"><a href="#529">529</a></td></tr
><tr id="gr_svn78_530"

 onmouseover="gutterOver(530)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',530);">&nbsp;</span
></td><td id="530"><a href="#530">530</a></td></tr
><tr id="gr_svn78_531"

 onmouseover="gutterOver(531)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',531);">&nbsp;</span
></td><td id="531"><a href="#531">531</a></td></tr
><tr id="gr_svn78_532"

 onmouseover="gutterOver(532)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',532);">&nbsp;</span
></td><td id="532"><a href="#532">532</a></td></tr
><tr id="gr_svn78_533"

 onmouseover="gutterOver(533)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',533);">&nbsp;</span
></td><td id="533"><a href="#533">533</a></td></tr
><tr id="gr_svn78_534"

 onmouseover="gutterOver(534)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',534);">&nbsp;</span
></td><td id="534"><a href="#534">534</a></td></tr
><tr id="gr_svn78_535"

 onmouseover="gutterOver(535)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',535);">&nbsp;</span
></td><td id="535"><a href="#535">535</a></td></tr
><tr id="gr_svn78_536"

 onmouseover="gutterOver(536)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',536);">&nbsp;</span
></td><td id="536"><a href="#536">536</a></td></tr
><tr id="gr_svn78_537"

 onmouseover="gutterOver(537)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',537);">&nbsp;</span
></td><td id="537"><a href="#537">537</a></td></tr
><tr id="gr_svn78_538"

 onmouseover="gutterOver(538)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',538);">&nbsp;</span
></td><td id="538"><a href="#538">538</a></td></tr
><tr id="gr_svn78_539"

 onmouseover="gutterOver(539)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',539);">&nbsp;</span
></td><td id="539"><a href="#539">539</a></td></tr
><tr id="gr_svn78_540"

 onmouseover="gutterOver(540)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',540);">&nbsp;</span
></td><td id="540"><a href="#540">540</a></td></tr
><tr id="gr_svn78_541"

 onmouseover="gutterOver(541)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',541);">&nbsp;</span
></td><td id="541"><a href="#541">541</a></td></tr
><tr id="gr_svn78_542"

 onmouseover="gutterOver(542)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',542);">&nbsp;</span
></td><td id="542"><a href="#542">542</a></td></tr
><tr id="gr_svn78_543"

 onmouseover="gutterOver(543)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',543);">&nbsp;</span
></td><td id="543"><a href="#543">543</a></td></tr
><tr id="gr_svn78_544"

 onmouseover="gutterOver(544)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',544);">&nbsp;</span
></td><td id="544"><a href="#544">544</a></td></tr
><tr id="gr_svn78_545"

 onmouseover="gutterOver(545)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',545);">&nbsp;</span
></td><td id="545"><a href="#545">545</a></td></tr
><tr id="gr_svn78_546"

 onmouseover="gutterOver(546)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',546);">&nbsp;</span
></td><td id="546"><a href="#546">546</a></td></tr
><tr id="gr_svn78_547"

 onmouseover="gutterOver(547)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',547);">&nbsp;</span
></td><td id="547"><a href="#547">547</a></td></tr
><tr id="gr_svn78_548"

 onmouseover="gutterOver(548)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',548);">&nbsp;</span
></td><td id="548"><a href="#548">548</a></td></tr
><tr id="gr_svn78_549"

 onmouseover="gutterOver(549)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',549);">&nbsp;</span
></td><td id="549"><a href="#549">549</a></td></tr
><tr id="gr_svn78_550"

 onmouseover="gutterOver(550)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',550);">&nbsp;</span
></td><td id="550"><a href="#550">550</a></td></tr
><tr id="gr_svn78_551"

 onmouseover="gutterOver(551)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',551);">&nbsp;</span
></td><td id="551"><a href="#551">551</a></td></tr
><tr id="gr_svn78_552"

 onmouseover="gutterOver(552)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',552);">&nbsp;</span
></td><td id="552"><a href="#552">552</a></td></tr
><tr id="gr_svn78_553"

 onmouseover="gutterOver(553)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',553);">&nbsp;</span
></td><td id="553"><a href="#553">553</a></td></tr
><tr id="gr_svn78_554"

 onmouseover="gutterOver(554)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',554);">&nbsp;</span
></td><td id="554"><a href="#554">554</a></td></tr
><tr id="gr_svn78_555"

 onmouseover="gutterOver(555)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',555);">&nbsp;</span
></td><td id="555"><a href="#555">555</a></td></tr
><tr id="gr_svn78_556"

 onmouseover="gutterOver(556)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',556);">&nbsp;</span
></td><td id="556"><a href="#556">556</a></td></tr
><tr id="gr_svn78_557"

 onmouseover="gutterOver(557)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',557);">&nbsp;</span
></td><td id="557"><a href="#557">557</a></td></tr
><tr id="gr_svn78_558"

 onmouseover="gutterOver(558)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',558);">&nbsp;</span
></td><td id="558"><a href="#558">558</a></td></tr
><tr id="gr_svn78_559"

 onmouseover="gutterOver(559)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',559);">&nbsp;</span
></td><td id="559"><a href="#559">559</a></td></tr
><tr id="gr_svn78_560"

 onmouseover="gutterOver(560)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',560);">&nbsp;</span
></td><td id="560"><a href="#560">560</a></td></tr
><tr id="gr_svn78_561"

 onmouseover="gutterOver(561)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',561);">&nbsp;</span
></td><td id="561"><a href="#561">561</a></td></tr
><tr id="gr_svn78_562"

 onmouseover="gutterOver(562)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',562);">&nbsp;</span
></td><td id="562"><a href="#562">562</a></td></tr
><tr id="gr_svn78_563"

 onmouseover="gutterOver(563)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',563);">&nbsp;</span
></td><td id="563"><a href="#563">563</a></td></tr
><tr id="gr_svn78_564"

 onmouseover="gutterOver(564)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',564);">&nbsp;</span
></td><td id="564"><a href="#564">564</a></td></tr
><tr id="gr_svn78_565"

 onmouseover="gutterOver(565)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',565);">&nbsp;</span
></td><td id="565"><a href="#565">565</a></td></tr
><tr id="gr_svn78_566"

 onmouseover="gutterOver(566)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',566);">&nbsp;</span
></td><td id="566"><a href="#566">566</a></td></tr
><tr id="gr_svn78_567"

 onmouseover="gutterOver(567)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',567);">&nbsp;</span
></td><td id="567"><a href="#567">567</a></td></tr
><tr id="gr_svn78_568"

 onmouseover="gutterOver(568)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',568);">&nbsp;</span
></td><td id="568"><a href="#568">568</a></td></tr
><tr id="gr_svn78_569"

 onmouseover="gutterOver(569)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',569);">&nbsp;</span
></td><td id="569"><a href="#569">569</a></td></tr
><tr id="gr_svn78_570"

 onmouseover="gutterOver(570)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',570);">&nbsp;</span
></td><td id="570"><a href="#570">570</a></td></tr
><tr id="gr_svn78_571"

 onmouseover="gutterOver(571)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',571);">&nbsp;</span
></td><td id="571"><a href="#571">571</a></td></tr
><tr id="gr_svn78_572"

 onmouseover="gutterOver(572)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',572);">&nbsp;</span
></td><td id="572"><a href="#572">572</a></td></tr
><tr id="gr_svn78_573"

 onmouseover="gutterOver(573)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',573);">&nbsp;</span
></td><td id="573"><a href="#573">573</a></td></tr
><tr id="gr_svn78_574"

 onmouseover="gutterOver(574)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',574);">&nbsp;</span
></td><td id="574"><a href="#574">574</a></td></tr
><tr id="gr_svn78_575"

 onmouseover="gutterOver(575)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',575);">&nbsp;</span
></td><td id="575"><a href="#575">575</a></td></tr
><tr id="gr_svn78_576"

 onmouseover="gutterOver(576)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',576);">&nbsp;</span
></td><td id="576"><a href="#576">576</a></td></tr
><tr id="gr_svn78_577"

 onmouseover="gutterOver(577)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',577);">&nbsp;</span
></td><td id="577"><a href="#577">577</a></td></tr
><tr id="gr_svn78_578"

 onmouseover="gutterOver(578)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',578);">&nbsp;</span
></td><td id="578"><a href="#578">578</a></td></tr
><tr id="gr_svn78_579"

 onmouseover="gutterOver(579)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',579);">&nbsp;</span
></td><td id="579"><a href="#579">579</a></td></tr
><tr id="gr_svn78_580"

 onmouseover="gutterOver(580)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',580);">&nbsp;</span
></td><td id="580"><a href="#580">580</a></td></tr
><tr id="gr_svn78_581"

 onmouseover="gutterOver(581)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',581);">&nbsp;</span
></td><td id="581"><a href="#581">581</a></td></tr
><tr id="gr_svn78_582"

 onmouseover="gutterOver(582)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',582);">&nbsp;</span
></td><td id="582"><a href="#582">582</a></td></tr
><tr id="gr_svn78_583"

 onmouseover="gutterOver(583)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',583);">&nbsp;</span
></td><td id="583"><a href="#583">583</a></td></tr
><tr id="gr_svn78_584"

 onmouseover="gutterOver(584)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',584);">&nbsp;</span
></td><td id="584"><a href="#584">584</a></td></tr
><tr id="gr_svn78_585"

 onmouseover="gutterOver(585)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',585);">&nbsp;</span
></td><td id="585"><a href="#585">585</a></td></tr
><tr id="gr_svn78_586"

 onmouseover="gutterOver(586)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',586);">&nbsp;</span
></td><td id="586"><a href="#586">586</a></td></tr
><tr id="gr_svn78_587"

 onmouseover="gutterOver(587)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',587);">&nbsp;</span
></td><td id="587"><a href="#587">587</a></td></tr
><tr id="gr_svn78_588"

 onmouseover="gutterOver(588)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',588);">&nbsp;</span
></td><td id="588"><a href="#588">588</a></td></tr
><tr id="gr_svn78_589"

 onmouseover="gutterOver(589)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',589);">&nbsp;</span
></td><td id="589"><a href="#589">589</a></td></tr
><tr id="gr_svn78_590"

 onmouseover="gutterOver(590)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',590);">&nbsp;</span
></td><td id="590"><a href="#590">590</a></td></tr
><tr id="gr_svn78_591"

 onmouseover="gutterOver(591)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',591);">&nbsp;</span
></td><td id="591"><a href="#591">591</a></td></tr
><tr id="gr_svn78_592"

 onmouseover="gutterOver(592)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',592);">&nbsp;</span
></td><td id="592"><a href="#592">592</a></td></tr
><tr id="gr_svn78_593"

 onmouseover="gutterOver(593)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',593);">&nbsp;</span
></td><td id="593"><a href="#593">593</a></td></tr
><tr id="gr_svn78_594"

 onmouseover="gutterOver(594)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',594);">&nbsp;</span
></td><td id="594"><a href="#594">594</a></td></tr
><tr id="gr_svn78_595"

 onmouseover="gutterOver(595)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',595);">&nbsp;</span
></td><td id="595"><a href="#595">595</a></td></tr
><tr id="gr_svn78_596"

 onmouseover="gutterOver(596)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',596);">&nbsp;</span
></td><td id="596"><a href="#596">596</a></td></tr
><tr id="gr_svn78_597"

 onmouseover="gutterOver(597)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',597);">&nbsp;</span
></td><td id="597"><a href="#597">597</a></td></tr
><tr id="gr_svn78_598"

 onmouseover="gutterOver(598)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',598);">&nbsp;</span
></td><td id="598"><a href="#598">598</a></td></tr
><tr id="gr_svn78_599"

 onmouseover="gutterOver(599)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',599);">&nbsp;</span
></td><td id="599"><a href="#599">599</a></td></tr
><tr id="gr_svn78_600"

 onmouseover="gutterOver(600)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',600);">&nbsp;</span
></td><td id="600"><a href="#600">600</a></td></tr
><tr id="gr_svn78_601"

 onmouseover="gutterOver(601)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',601);">&nbsp;</span
></td><td id="601"><a href="#601">601</a></td></tr
><tr id="gr_svn78_602"

 onmouseover="gutterOver(602)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',602);">&nbsp;</span
></td><td id="602"><a href="#602">602</a></td></tr
><tr id="gr_svn78_603"

 onmouseover="gutterOver(603)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',603);">&nbsp;</span
></td><td id="603"><a href="#603">603</a></td></tr
><tr id="gr_svn78_604"

 onmouseover="gutterOver(604)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',604);">&nbsp;</span
></td><td id="604"><a href="#604">604</a></td></tr
><tr id="gr_svn78_605"

 onmouseover="gutterOver(605)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',605);">&nbsp;</span
></td><td id="605"><a href="#605">605</a></td></tr
><tr id="gr_svn78_606"

 onmouseover="gutterOver(606)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',606);">&nbsp;</span
></td><td id="606"><a href="#606">606</a></td></tr
><tr id="gr_svn78_607"

 onmouseover="gutterOver(607)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',607);">&nbsp;</span
></td><td id="607"><a href="#607">607</a></td></tr
><tr id="gr_svn78_608"

 onmouseover="gutterOver(608)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',608);">&nbsp;</span
></td><td id="608"><a href="#608">608</a></td></tr
><tr id="gr_svn78_609"

 onmouseover="gutterOver(609)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',609);">&nbsp;</span
></td><td id="609"><a href="#609">609</a></td></tr
><tr id="gr_svn78_610"

 onmouseover="gutterOver(610)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',610);">&nbsp;</span
></td><td id="610"><a href="#610">610</a></td></tr
><tr id="gr_svn78_611"

 onmouseover="gutterOver(611)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',611);">&nbsp;</span
></td><td id="611"><a href="#611">611</a></td></tr
><tr id="gr_svn78_612"

 onmouseover="gutterOver(612)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',612);">&nbsp;</span
></td><td id="612"><a href="#612">612</a></td></tr
><tr id="gr_svn78_613"

 onmouseover="gutterOver(613)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',613);">&nbsp;</span
></td><td id="613"><a href="#613">613</a></td></tr
><tr id="gr_svn78_614"

 onmouseover="gutterOver(614)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',614);">&nbsp;</span
></td><td id="614"><a href="#614">614</a></td></tr
><tr id="gr_svn78_615"

 onmouseover="gutterOver(615)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',615);">&nbsp;</span
></td><td id="615"><a href="#615">615</a></td></tr
><tr id="gr_svn78_616"

 onmouseover="gutterOver(616)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',616);">&nbsp;</span
></td><td id="616"><a href="#616">616</a></td></tr
><tr id="gr_svn78_617"

 onmouseover="gutterOver(617)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',617);">&nbsp;</span
></td><td id="617"><a href="#617">617</a></td></tr
><tr id="gr_svn78_618"

 onmouseover="gutterOver(618)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',618);">&nbsp;</span
></td><td id="618"><a href="#618">618</a></td></tr
><tr id="gr_svn78_619"

 onmouseover="gutterOver(619)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',619);">&nbsp;</span
></td><td id="619"><a href="#619">619</a></td></tr
><tr id="gr_svn78_620"

 onmouseover="gutterOver(620)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',620);">&nbsp;</span
></td><td id="620"><a href="#620">620</a></td></tr
><tr id="gr_svn78_621"

 onmouseover="gutterOver(621)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',621);">&nbsp;</span
></td><td id="621"><a href="#621">621</a></td></tr
><tr id="gr_svn78_622"

 onmouseover="gutterOver(622)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',622);">&nbsp;</span
></td><td id="622"><a href="#622">622</a></td></tr
><tr id="gr_svn78_623"

 onmouseover="gutterOver(623)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',623);">&nbsp;</span
></td><td id="623"><a href="#623">623</a></td></tr
><tr id="gr_svn78_624"

 onmouseover="gutterOver(624)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',624);">&nbsp;</span
></td><td id="624"><a href="#624">624</a></td></tr
><tr id="gr_svn78_625"

 onmouseover="gutterOver(625)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',625);">&nbsp;</span
></td><td id="625"><a href="#625">625</a></td></tr
><tr id="gr_svn78_626"

 onmouseover="gutterOver(626)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',626);">&nbsp;</span
></td><td id="626"><a href="#626">626</a></td></tr
><tr id="gr_svn78_627"

 onmouseover="gutterOver(627)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',627);">&nbsp;</span
></td><td id="627"><a href="#627">627</a></td></tr
><tr id="gr_svn78_628"

 onmouseover="gutterOver(628)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',628);">&nbsp;</span
></td><td id="628"><a href="#628">628</a></td></tr
><tr id="gr_svn78_629"

 onmouseover="gutterOver(629)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',629);">&nbsp;</span
></td><td id="629"><a href="#629">629</a></td></tr
><tr id="gr_svn78_630"

 onmouseover="gutterOver(630)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',630);">&nbsp;</span
></td><td id="630"><a href="#630">630</a></td></tr
><tr id="gr_svn78_631"

 onmouseover="gutterOver(631)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',631);">&nbsp;</span
></td><td id="631"><a href="#631">631</a></td></tr
><tr id="gr_svn78_632"

 onmouseover="gutterOver(632)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',632);">&nbsp;</span
></td><td id="632"><a href="#632">632</a></td></tr
><tr id="gr_svn78_633"

 onmouseover="gutterOver(633)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',633);">&nbsp;</span
></td><td id="633"><a href="#633">633</a></td></tr
><tr id="gr_svn78_634"

 onmouseover="gutterOver(634)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',634);">&nbsp;</span
></td><td id="634"><a href="#634">634</a></td></tr
><tr id="gr_svn78_635"

 onmouseover="gutterOver(635)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',635);">&nbsp;</span
></td><td id="635"><a href="#635">635</a></td></tr
><tr id="gr_svn78_636"

 onmouseover="gutterOver(636)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',636);">&nbsp;</span
></td><td id="636"><a href="#636">636</a></td></tr
><tr id="gr_svn78_637"

 onmouseover="gutterOver(637)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',637);">&nbsp;</span
></td><td id="637"><a href="#637">637</a></td></tr
><tr id="gr_svn78_638"

 onmouseover="gutterOver(638)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',638);">&nbsp;</span
></td><td id="638"><a href="#638">638</a></td></tr
><tr id="gr_svn78_639"

 onmouseover="gutterOver(639)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',639);">&nbsp;</span
></td><td id="639"><a href="#639">639</a></td></tr
><tr id="gr_svn78_640"

 onmouseover="gutterOver(640)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',640);">&nbsp;</span
></td><td id="640"><a href="#640">640</a></td></tr
><tr id="gr_svn78_641"

 onmouseover="gutterOver(641)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',641);">&nbsp;</span
></td><td id="641"><a href="#641">641</a></td></tr
><tr id="gr_svn78_642"

 onmouseover="gutterOver(642)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',642);">&nbsp;</span
></td><td id="642"><a href="#642">642</a></td></tr
><tr id="gr_svn78_643"

 onmouseover="gutterOver(643)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',643);">&nbsp;</span
></td><td id="643"><a href="#643">643</a></td></tr
><tr id="gr_svn78_644"

 onmouseover="gutterOver(644)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',644);">&nbsp;</span
></td><td id="644"><a href="#644">644</a></td></tr
><tr id="gr_svn78_645"

 onmouseover="gutterOver(645)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',645);">&nbsp;</span
></td><td id="645"><a href="#645">645</a></td></tr
><tr id="gr_svn78_646"

 onmouseover="gutterOver(646)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',646);">&nbsp;</span
></td><td id="646"><a href="#646">646</a></td></tr
><tr id="gr_svn78_647"

 onmouseover="gutterOver(647)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',647);">&nbsp;</span
></td><td id="647"><a href="#647">647</a></td></tr
><tr id="gr_svn78_648"

 onmouseover="gutterOver(648)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',648);">&nbsp;</span
></td><td id="648"><a href="#648">648</a></td></tr
><tr id="gr_svn78_649"

 onmouseover="gutterOver(649)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',649);">&nbsp;</span
></td><td id="649"><a href="#649">649</a></td></tr
><tr id="gr_svn78_650"

 onmouseover="gutterOver(650)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',650);">&nbsp;</span
></td><td id="650"><a href="#650">650</a></td></tr
><tr id="gr_svn78_651"

 onmouseover="gutterOver(651)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',651);">&nbsp;</span
></td><td id="651"><a href="#651">651</a></td></tr
><tr id="gr_svn78_652"

 onmouseover="gutterOver(652)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',652);">&nbsp;</span
></td><td id="652"><a href="#652">652</a></td></tr
><tr id="gr_svn78_653"

 onmouseover="gutterOver(653)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',653);">&nbsp;</span
></td><td id="653"><a href="#653">653</a></td></tr
><tr id="gr_svn78_654"

 onmouseover="gutterOver(654)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',654);">&nbsp;</span
></td><td id="654"><a href="#654">654</a></td></tr
><tr id="gr_svn78_655"

 onmouseover="gutterOver(655)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',655);">&nbsp;</span
></td><td id="655"><a href="#655">655</a></td></tr
><tr id="gr_svn78_656"

 onmouseover="gutterOver(656)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',656);">&nbsp;</span
></td><td id="656"><a href="#656">656</a></td></tr
><tr id="gr_svn78_657"

 onmouseover="gutterOver(657)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',657);">&nbsp;</span
></td><td id="657"><a href="#657">657</a></td></tr
><tr id="gr_svn78_658"

 onmouseover="gutterOver(658)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',658);">&nbsp;</span
></td><td id="658"><a href="#658">658</a></td></tr
><tr id="gr_svn78_659"

 onmouseover="gutterOver(659)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',659);">&nbsp;</span
></td><td id="659"><a href="#659">659</a></td></tr
><tr id="gr_svn78_660"

 onmouseover="gutterOver(660)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',660);">&nbsp;</span
></td><td id="660"><a href="#660">660</a></td></tr
><tr id="gr_svn78_661"

 onmouseover="gutterOver(661)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',661);">&nbsp;</span
></td><td id="661"><a href="#661">661</a></td></tr
><tr id="gr_svn78_662"

 onmouseover="gutterOver(662)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',662);">&nbsp;</span
></td><td id="662"><a href="#662">662</a></td></tr
><tr id="gr_svn78_663"

 onmouseover="gutterOver(663)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',663);">&nbsp;</span
></td><td id="663"><a href="#663">663</a></td></tr
><tr id="gr_svn78_664"

 onmouseover="gutterOver(664)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',664);">&nbsp;</span
></td><td id="664"><a href="#664">664</a></td></tr
><tr id="gr_svn78_665"

 onmouseover="gutterOver(665)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',665);">&nbsp;</span
></td><td id="665"><a href="#665">665</a></td></tr
><tr id="gr_svn78_666"

 onmouseover="gutterOver(666)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',666);">&nbsp;</span
></td><td id="666"><a href="#666">666</a></td></tr
><tr id="gr_svn78_667"

 onmouseover="gutterOver(667)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',667);">&nbsp;</span
></td><td id="667"><a href="#667">667</a></td></tr
><tr id="gr_svn78_668"

 onmouseover="gutterOver(668)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',668);">&nbsp;</span
></td><td id="668"><a href="#668">668</a></td></tr
><tr id="gr_svn78_669"

 onmouseover="gutterOver(669)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',669);">&nbsp;</span
></td><td id="669"><a href="#669">669</a></td></tr
><tr id="gr_svn78_670"

 onmouseover="gutterOver(670)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',670);">&nbsp;</span
></td><td id="670"><a href="#670">670</a></td></tr
><tr id="gr_svn78_671"

 onmouseover="gutterOver(671)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',671);">&nbsp;</span
></td><td id="671"><a href="#671">671</a></td></tr
><tr id="gr_svn78_672"

 onmouseover="gutterOver(672)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',672);">&nbsp;</span
></td><td id="672"><a href="#672">672</a></td></tr
><tr id="gr_svn78_673"

 onmouseover="gutterOver(673)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',673);">&nbsp;</span
></td><td id="673"><a href="#673">673</a></td></tr
><tr id="gr_svn78_674"

 onmouseover="gutterOver(674)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',674);">&nbsp;</span
></td><td id="674"><a href="#674">674</a></td></tr
><tr id="gr_svn78_675"

 onmouseover="gutterOver(675)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',675);">&nbsp;</span
></td><td id="675"><a href="#675">675</a></td></tr
><tr id="gr_svn78_676"

 onmouseover="gutterOver(676)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',676);">&nbsp;</span
></td><td id="676"><a href="#676">676</a></td></tr
><tr id="gr_svn78_677"

 onmouseover="gutterOver(677)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',677);">&nbsp;</span
></td><td id="677"><a href="#677">677</a></td></tr
><tr id="gr_svn78_678"

 onmouseover="gutterOver(678)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',678);">&nbsp;</span
></td><td id="678"><a href="#678">678</a></td></tr
><tr id="gr_svn78_679"

 onmouseover="gutterOver(679)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',679);">&nbsp;</span
></td><td id="679"><a href="#679">679</a></td></tr
><tr id="gr_svn78_680"

 onmouseover="gutterOver(680)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',680);">&nbsp;</span
></td><td id="680"><a href="#680">680</a></td></tr
><tr id="gr_svn78_681"

 onmouseover="gutterOver(681)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',681);">&nbsp;</span
></td><td id="681"><a href="#681">681</a></td></tr
><tr id="gr_svn78_682"

 onmouseover="gutterOver(682)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',682);">&nbsp;</span
></td><td id="682"><a href="#682">682</a></td></tr
><tr id="gr_svn78_683"

 onmouseover="gutterOver(683)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',683);">&nbsp;</span
></td><td id="683"><a href="#683">683</a></td></tr
><tr id="gr_svn78_684"

 onmouseover="gutterOver(684)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',684);">&nbsp;</span
></td><td id="684"><a href="#684">684</a></td></tr
><tr id="gr_svn78_685"

 onmouseover="gutterOver(685)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',685);">&nbsp;</span
></td><td id="685"><a href="#685">685</a></td></tr
><tr id="gr_svn78_686"

 onmouseover="gutterOver(686)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',686);">&nbsp;</span
></td><td id="686"><a href="#686">686</a></td></tr
><tr id="gr_svn78_687"

 onmouseover="gutterOver(687)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',687);">&nbsp;</span
></td><td id="687"><a href="#687">687</a></td></tr
><tr id="gr_svn78_688"

 onmouseover="gutterOver(688)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',688);">&nbsp;</span
></td><td id="688"><a href="#688">688</a></td></tr
><tr id="gr_svn78_689"

 onmouseover="gutterOver(689)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',689);">&nbsp;</span
></td><td id="689"><a href="#689">689</a></td></tr
><tr id="gr_svn78_690"

 onmouseover="gutterOver(690)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',690);">&nbsp;</span
></td><td id="690"><a href="#690">690</a></td></tr
><tr id="gr_svn78_691"

 onmouseover="gutterOver(691)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',691);">&nbsp;</span
></td><td id="691"><a href="#691">691</a></td></tr
><tr id="gr_svn78_692"

 onmouseover="gutterOver(692)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',692);">&nbsp;</span
></td><td id="692"><a href="#692">692</a></td></tr
><tr id="gr_svn78_693"

 onmouseover="gutterOver(693)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',693);">&nbsp;</span
></td><td id="693"><a href="#693">693</a></td></tr
><tr id="gr_svn78_694"

 onmouseover="gutterOver(694)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',694);">&nbsp;</span
></td><td id="694"><a href="#694">694</a></td></tr
><tr id="gr_svn78_695"

 onmouseover="gutterOver(695)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',695);">&nbsp;</span
></td><td id="695"><a href="#695">695</a></td></tr
><tr id="gr_svn78_696"

 onmouseover="gutterOver(696)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',696);">&nbsp;</span
></td><td id="696"><a href="#696">696</a></td></tr
><tr id="gr_svn78_697"

 onmouseover="gutterOver(697)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',697);">&nbsp;</span
></td><td id="697"><a href="#697">697</a></td></tr
><tr id="gr_svn78_698"

 onmouseover="gutterOver(698)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',698);">&nbsp;</span
></td><td id="698"><a href="#698">698</a></td></tr
><tr id="gr_svn78_699"

 onmouseover="gutterOver(699)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',699);">&nbsp;</span
></td><td id="699"><a href="#699">699</a></td></tr
><tr id="gr_svn78_700"

 onmouseover="gutterOver(700)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',700);">&nbsp;</span
></td><td id="700"><a href="#700">700</a></td></tr
><tr id="gr_svn78_701"

 onmouseover="gutterOver(701)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',701);">&nbsp;</span
></td><td id="701"><a href="#701">701</a></td></tr
><tr id="gr_svn78_702"

 onmouseover="gutterOver(702)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',702);">&nbsp;</span
></td><td id="702"><a href="#702">702</a></td></tr
><tr id="gr_svn78_703"

 onmouseover="gutterOver(703)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',703);">&nbsp;</span
></td><td id="703"><a href="#703">703</a></td></tr
><tr id="gr_svn78_704"

 onmouseover="gutterOver(704)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',704);">&nbsp;</span
></td><td id="704"><a href="#704">704</a></td></tr
><tr id="gr_svn78_705"

 onmouseover="gutterOver(705)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',705);">&nbsp;</span
></td><td id="705"><a href="#705">705</a></td></tr
><tr id="gr_svn78_706"

 onmouseover="gutterOver(706)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',706);">&nbsp;</span
></td><td id="706"><a href="#706">706</a></td></tr
><tr id="gr_svn78_707"

 onmouseover="gutterOver(707)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',707);">&nbsp;</span
></td><td id="707"><a href="#707">707</a></td></tr
><tr id="gr_svn78_708"

 onmouseover="gutterOver(708)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',708);">&nbsp;</span
></td><td id="708"><a href="#708">708</a></td></tr
><tr id="gr_svn78_709"

 onmouseover="gutterOver(709)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',709);">&nbsp;</span
></td><td id="709"><a href="#709">709</a></td></tr
><tr id="gr_svn78_710"

 onmouseover="gutterOver(710)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',710);">&nbsp;</span
></td><td id="710"><a href="#710">710</a></td></tr
><tr id="gr_svn78_711"

 onmouseover="gutterOver(711)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',711);">&nbsp;</span
></td><td id="711"><a href="#711">711</a></td></tr
><tr id="gr_svn78_712"

 onmouseover="gutterOver(712)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',712);">&nbsp;</span
></td><td id="712"><a href="#712">712</a></td></tr
><tr id="gr_svn78_713"

 onmouseover="gutterOver(713)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',713);">&nbsp;</span
></td><td id="713"><a href="#713">713</a></td></tr
><tr id="gr_svn78_714"

 onmouseover="gutterOver(714)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',714);">&nbsp;</span
></td><td id="714"><a href="#714">714</a></td></tr
><tr id="gr_svn78_715"

 onmouseover="gutterOver(715)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',715);">&nbsp;</span
></td><td id="715"><a href="#715">715</a></td></tr
><tr id="gr_svn78_716"

 onmouseover="gutterOver(716)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',716);">&nbsp;</span
></td><td id="716"><a href="#716">716</a></td></tr
><tr id="gr_svn78_717"

 onmouseover="gutterOver(717)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',717);">&nbsp;</span
></td><td id="717"><a href="#717">717</a></td></tr
><tr id="gr_svn78_718"

 onmouseover="gutterOver(718)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',718);">&nbsp;</span
></td><td id="718"><a href="#718">718</a></td></tr
><tr id="gr_svn78_719"

 onmouseover="gutterOver(719)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',719);">&nbsp;</span
></td><td id="719"><a href="#719">719</a></td></tr
><tr id="gr_svn78_720"

 onmouseover="gutterOver(720)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',720);">&nbsp;</span
></td><td id="720"><a href="#720">720</a></td></tr
><tr id="gr_svn78_721"

 onmouseover="gutterOver(721)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',721);">&nbsp;</span
></td><td id="721"><a href="#721">721</a></td></tr
><tr id="gr_svn78_722"

 onmouseover="gutterOver(722)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',722);">&nbsp;</span
></td><td id="722"><a href="#722">722</a></td></tr
><tr id="gr_svn78_723"

 onmouseover="gutterOver(723)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',723);">&nbsp;</span
></td><td id="723"><a href="#723">723</a></td></tr
><tr id="gr_svn78_724"

 onmouseover="gutterOver(724)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',724);">&nbsp;</span
></td><td id="724"><a href="#724">724</a></td></tr
><tr id="gr_svn78_725"

 onmouseover="gutterOver(725)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',725);">&nbsp;</span
></td><td id="725"><a href="#725">725</a></td></tr
><tr id="gr_svn78_726"

 onmouseover="gutterOver(726)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',726);">&nbsp;</span
></td><td id="726"><a href="#726">726</a></td></tr
><tr id="gr_svn78_727"

 onmouseover="gutterOver(727)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',727);">&nbsp;</span
></td><td id="727"><a href="#727">727</a></td></tr
><tr id="gr_svn78_728"

 onmouseover="gutterOver(728)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',728);">&nbsp;</span
></td><td id="728"><a href="#728">728</a></td></tr
><tr id="gr_svn78_729"

 onmouseover="gutterOver(729)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',729);">&nbsp;</span
></td><td id="729"><a href="#729">729</a></td></tr
><tr id="gr_svn78_730"

 onmouseover="gutterOver(730)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',730);">&nbsp;</span
></td><td id="730"><a href="#730">730</a></td></tr
><tr id="gr_svn78_731"

 onmouseover="gutterOver(731)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',731);">&nbsp;</span
></td><td id="731"><a href="#731">731</a></td></tr
><tr id="gr_svn78_732"

 onmouseover="gutterOver(732)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',732);">&nbsp;</span
></td><td id="732"><a href="#732">732</a></td></tr
><tr id="gr_svn78_733"

 onmouseover="gutterOver(733)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',733);">&nbsp;</span
></td><td id="733"><a href="#733">733</a></td></tr
><tr id="gr_svn78_734"

 onmouseover="gutterOver(734)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',734);">&nbsp;</span
></td><td id="734"><a href="#734">734</a></td></tr
><tr id="gr_svn78_735"

 onmouseover="gutterOver(735)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',735);">&nbsp;</span
></td><td id="735"><a href="#735">735</a></td></tr
><tr id="gr_svn78_736"

 onmouseover="gutterOver(736)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',736);">&nbsp;</span
></td><td id="736"><a href="#736">736</a></td></tr
><tr id="gr_svn78_737"

 onmouseover="gutterOver(737)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',737);">&nbsp;</span
></td><td id="737"><a href="#737">737</a></td></tr
><tr id="gr_svn78_738"

 onmouseover="gutterOver(738)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',738);">&nbsp;</span
></td><td id="738"><a href="#738">738</a></td></tr
><tr id="gr_svn78_739"

 onmouseover="gutterOver(739)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',739);">&nbsp;</span
></td><td id="739"><a href="#739">739</a></td></tr
><tr id="gr_svn78_740"

 onmouseover="gutterOver(740)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',740);">&nbsp;</span
></td><td id="740"><a href="#740">740</a></td></tr
><tr id="gr_svn78_741"

 onmouseover="gutterOver(741)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',741);">&nbsp;</span
></td><td id="741"><a href="#741">741</a></td></tr
><tr id="gr_svn78_742"

 onmouseover="gutterOver(742)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',742);">&nbsp;</span
></td><td id="742"><a href="#742">742</a></td></tr
><tr id="gr_svn78_743"

 onmouseover="gutterOver(743)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',743);">&nbsp;</span
></td><td id="743"><a href="#743">743</a></td></tr
><tr id="gr_svn78_744"

 onmouseover="gutterOver(744)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',744);">&nbsp;</span
></td><td id="744"><a href="#744">744</a></td></tr
><tr id="gr_svn78_745"

 onmouseover="gutterOver(745)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',745);">&nbsp;</span
></td><td id="745"><a href="#745">745</a></td></tr
><tr id="gr_svn78_746"

 onmouseover="gutterOver(746)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',746);">&nbsp;</span
></td><td id="746"><a href="#746">746</a></td></tr
><tr id="gr_svn78_747"

 onmouseover="gutterOver(747)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',747);">&nbsp;</span
></td><td id="747"><a href="#747">747</a></td></tr
><tr id="gr_svn78_748"

 onmouseover="gutterOver(748)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',748);">&nbsp;</span
></td><td id="748"><a href="#748">748</a></td></tr
><tr id="gr_svn78_749"

 onmouseover="gutterOver(749)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',749);">&nbsp;</span
></td><td id="749"><a href="#749">749</a></td></tr
><tr id="gr_svn78_750"

 onmouseover="gutterOver(750)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',750);">&nbsp;</span
></td><td id="750"><a href="#750">750</a></td></tr
><tr id="gr_svn78_751"

 onmouseover="gutterOver(751)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',751);">&nbsp;</span
></td><td id="751"><a href="#751">751</a></td></tr
><tr id="gr_svn78_752"

 onmouseover="gutterOver(752)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',752);">&nbsp;</span
></td><td id="752"><a href="#752">752</a></td></tr
><tr id="gr_svn78_753"

 onmouseover="gutterOver(753)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',753);">&nbsp;</span
></td><td id="753"><a href="#753">753</a></td></tr
><tr id="gr_svn78_754"

 onmouseover="gutterOver(754)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',754);">&nbsp;</span
></td><td id="754"><a href="#754">754</a></td></tr
><tr id="gr_svn78_755"

 onmouseover="gutterOver(755)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',755);">&nbsp;</span
></td><td id="755"><a href="#755">755</a></td></tr
><tr id="gr_svn78_756"

 onmouseover="gutterOver(756)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',756);">&nbsp;</span
></td><td id="756"><a href="#756">756</a></td></tr
><tr id="gr_svn78_757"

 onmouseover="gutterOver(757)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',757);">&nbsp;</span
></td><td id="757"><a href="#757">757</a></td></tr
><tr id="gr_svn78_758"

 onmouseover="gutterOver(758)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',758);">&nbsp;</span
></td><td id="758"><a href="#758">758</a></td></tr
><tr id="gr_svn78_759"

 onmouseover="gutterOver(759)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',759);">&nbsp;</span
></td><td id="759"><a href="#759">759</a></td></tr
><tr id="gr_svn78_760"

 onmouseover="gutterOver(760)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',760);">&nbsp;</span
></td><td id="760"><a href="#760">760</a></td></tr
><tr id="gr_svn78_761"

 onmouseover="gutterOver(761)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',761);">&nbsp;</span
></td><td id="761"><a href="#761">761</a></td></tr
><tr id="gr_svn78_762"

 onmouseover="gutterOver(762)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',762);">&nbsp;</span
></td><td id="762"><a href="#762">762</a></td></tr
><tr id="gr_svn78_763"

 onmouseover="gutterOver(763)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',763);">&nbsp;</span
></td><td id="763"><a href="#763">763</a></td></tr
><tr id="gr_svn78_764"

 onmouseover="gutterOver(764)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',764);">&nbsp;</span
></td><td id="764"><a href="#764">764</a></td></tr
><tr id="gr_svn78_765"

 onmouseover="gutterOver(765)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',765);">&nbsp;</span
></td><td id="765"><a href="#765">765</a></td></tr
><tr id="gr_svn78_766"

 onmouseover="gutterOver(766)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',766);">&nbsp;</span
></td><td id="766"><a href="#766">766</a></td></tr
><tr id="gr_svn78_767"

 onmouseover="gutterOver(767)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',767);">&nbsp;</span
></td><td id="767"><a href="#767">767</a></td></tr
><tr id="gr_svn78_768"

 onmouseover="gutterOver(768)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',768);">&nbsp;</span
></td><td id="768"><a href="#768">768</a></td></tr
><tr id="gr_svn78_769"

 onmouseover="gutterOver(769)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',769);">&nbsp;</span
></td><td id="769"><a href="#769">769</a></td></tr
><tr id="gr_svn78_770"

 onmouseover="gutterOver(770)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',770);">&nbsp;</span
></td><td id="770"><a href="#770">770</a></td></tr
><tr id="gr_svn78_771"

 onmouseover="gutterOver(771)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',771);">&nbsp;</span
></td><td id="771"><a href="#771">771</a></td></tr
><tr id="gr_svn78_772"

 onmouseover="gutterOver(772)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',772);">&nbsp;</span
></td><td id="772"><a href="#772">772</a></td></tr
><tr id="gr_svn78_773"

 onmouseover="gutterOver(773)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',773);">&nbsp;</span
></td><td id="773"><a href="#773">773</a></td></tr
><tr id="gr_svn78_774"

 onmouseover="gutterOver(774)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',774);">&nbsp;</span
></td><td id="774"><a href="#774">774</a></td></tr
><tr id="gr_svn78_775"

 onmouseover="gutterOver(775)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',775);">&nbsp;</span
></td><td id="775"><a href="#775">775</a></td></tr
><tr id="gr_svn78_776"

 onmouseover="gutterOver(776)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',776);">&nbsp;</span
></td><td id="776"><a href="#776">776</a></td></tr
><tr id="gr_svn78_777"

 onmouseover="gutterOver(777)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',777);">&nbsp;</span
></td><td id="777"><a href="#777">777</a></td></tr
><tr id="gr_svn78_778"

 onmouseover="gutterOver(778)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',778);">&nbsp;</span
></td><td id="778"><a href="#778">778</a></td></tr
><tr id="gr_svn78_779"

 onmouseover="gutterOver(779)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',779);">&nbsp;</span
></td><td id="779"><a href="#779">779</a></td></tr
><tr id="gr_svn78_780"

 onmouseover="gutterOver(780)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',780);">&nbsp;</span
></td><td id="780"><a href="#780">780</a></td></tr
><tr id="gr_svn78_781"

 onmouseover="gutterOver(781)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',781);">&nbsp;</span
></td><td id="781"><a href="#781">781</a></td></tr
><tr id="gr_svn78_782"

 onmouseover="gutterOver(782)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',782);">&nbsp;</span
></td><td id="782"><a href="#782">782</a></td></tr
><tr id="gr_svn78_783"

 onmouseover="gutterOver(783)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',783);">&nbsp;</span
></td><td id="783"><a href="#783">783</a></td></tr
><tr id="gr_svn78_784"

 onmouseover="gutterOver(784)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',784);">&nbsp;</span
></td><td id="784"><a href="#784">784</a></td></tr
><tr id="gr_svn78_785"

 onmouseover="gutterOver(785)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',785);">&nbsp;</span
></td><td id="785"><a href="#785">785</a></td></tr
><tr id="gr_svn78_786"

 onmouseover="gutterOver(786)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',786);">&nbsp;</span
></td><td id="786"><a href="#786">786</a></td></tr
><tr id="gr_svn78_787"

 onmouseover="gutterOver(787)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',787);">&nbsp;</span
></td><td id="787"><a href="#787">787</a></td></tr
><tr id="gr_svn78_788"

 onmouseover="gutterOver(788)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',788);">&nbsp;</span
></td><td id="788"><a href="#788">788</a></td></tr
><tr id="gr_svn78_789"

 onmouseover="gutterOver(789)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',789);">&nbsp;</span
></td><td id="789"><a href="#789">789</a></td></tr
><tr id="gr_svn78_790"

 onmouseover="gutterOver(790)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',790);">&nbsp;</span
></td><td id="790"><a href="#790">790</a></td></tr
><tr id="gr_svn78_791"

 onmouseover="gutterOver(791)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',791);">&nbsp;</span
></td><td id="791"><a href="#791">791</a></td></tr
><tr id="gr_svn78_792"

 onmouseover="gutterOver(792)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',792);">&nbsp;</span
></td><td id="792"><a href="#792">792</a></td></tr
><tr id="gr_svn78_793"

 onmouseover="gutterOver(793)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',793);">&nbsp;</span
></td><td id="793"><a href="#793">793</a></td></tr
><tr id="gr_svn78_794"

 onmouseover="gutterOver(794)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',794);">&nbsp;</span
></td><td id="794"><a href="#794">794</a></td></tr
><tr id="gr_svn78_795"

 onmouseover="gutterOver(795)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',795);">&nbsp;</span
></td><td id="795"><a href="#795">795</a></td></tr
><tr id="gr_svn78_796"

 onmouseover="gutterOver(796)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',796);">&nbsp;</span
></td><td id="796"><a href="#796">796</a></td></tr
><tr id="gr_svn78_797"

 onmouseover="gutterOver(797)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',797);">&nbsp;</span
></td><td id="797"><a href="#797">797</a></td></tr
><tr id="gr_svn78_798"

 onmouseover="gutterOver(798)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',798);">&nbsp;</span
></td><td id="798"><a href="#798">798</a></td></tr
><tr id="gr_svn78_799"

 onmouseover="gutterOver(799)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',799);">&nbsp;</span
></td><td id="799"><a href="#799">799</a></td></tr
><tr id="gr_svn78_800"

 onmouseover="gutterOver(800)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',800);">&nbsp;</span
></td><td id="800"><a href="#800">800</a></td></tr
><tr id="gr_svn78_801"

 onmouseover="gutterOver(801)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',801);">&nbsp;</span
></td><td id="801"><a href="#801">801</a></td></tr
><tr id="gr_svn78_802"

 onmouseover="gutterOver(802)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',802);">&nbsp;</span
></td><td id="802"><a href="#802">802</a></td></tr
><tr id="gr_svn78_803"

 onmouseover="gutterOver(803)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',803);">&nbsp;</span
></td><td id="803"><a href="#803">803</a></td></tr
><tr id="gr_svn78_804"

 onmouseover="gutterOver(804)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',804);">&nbsp;</span
></td><td id="804"><a href="#804">804</a></td></tr
><tr id="gr_svn78_805"

 onmouseover="gutterOver(805)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',805);">&nbsp;</span
></td><td id="805"><a href="#805">805</a></td></tr
><tr id="gr_svn78_806"

 onmouseover="gutterOver(806)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',806);">&nbsp;</span
></td><td id="806"><a href="#806">806</a></td></tr
><tr id="gr_svn78_807"

 onmouseover="gutterOver(807)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',807);">&nbsp;</span
></td><td id="807"><a href="#807">807</a></td></tr
><tr id="gr_svn78_808"

 onmouseover="gutterOver(808)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',808);">&nbsp;</span
></td><td id="808"><a href="#808">808</a></td></tr
><tr id="gr_svn78_809"

 onmouseover="gutterOver(809)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',809);">&nbsp;</span
></td><td id="809"><a href="#809">809</a></td></tr
><tr id="gr_svn78_810"

 onmouseover="gutterOver(810)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',810);">&nbsp;</span
></td><td id="810"><a href="#810">810</a></td></tr
><tr id="gr_svn78_811"

 onmouseover="gutterOver(811)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',811);">&nbsp;</span
></td><td id="811"><a href="#811">811</a></td></tr
><tr id="gr_svn78_812"

 onmouseover="gutterOver(812)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',812);">&nbsp;</span
></td><td id="812"><a href="#812">812</a></td></tr
><tr id="gr_svn78_813"

 onmouseover="gutterOver(813)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',813);">&nbsp;</span
></td><td id="813"><a href="#813">813</a></td></tr
><tr id="gr_svn78_814"

 onmouseover="gutterOver(814)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',814);">&nbsp;</span
></td><td id="814"><a href="#814">814</a></td></tr
><tr id="gr_svn78_815"

 onmouseover="gutterOver(815)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',815);">&nbsp;</span
></td><td id="815"><a href="#815">815</a></td></tr
><tr id="gr_svn78_816"

 onmouseover="gutterOver(816)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',816);">&nbsp;</span
></td><td id="816"><a href="#816">816</a></td></tr
><tr id="gr_svn78_817"

 onmouseover="gutterOver(817)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',817);">&nbsp;</span
></td><td id="817"><a href="#817">817</a></td></tr
><tr id="gr_svn78_818"

 onmouseover="gutterOver(818)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',818);">&nbsp;</span
></td><td id="818"><a href="#818">818</a></td></tr
><tr id="gr_svn78_819"

 onmouseover="gutterOver(819)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',819);">&nbsp;</span
></td><td id="819"><a href="#819">819</a></td></tr
><tr id="gr_svn78_820"

 onmouseover="gutterOver(820)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',820);">&nbsp;</span
></td><td id="820"><a href="#820">820</a></td></tr
><tr id="gr_svn78_821"

 onmouseover="gutterOver(821)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',821);">&nbsp;</span
></td><td id="821"><a href="#821">821</a></td></tr
><tr id="gr_svn78_822"

 onmouseover="gutterOver(822)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',822);">&nbsp;</span
></td><td id="822"><a href="#822">822</a></td></tr
><tr id="gr_svn78_823"

 onmouseover="gutterOver(823)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',823);">&nbsp;</span
></td><td id="823"><a href="#823">823</a></td></tr
><tr id="gr_svn78_824"

 onmouseover="gutterOver(824)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',824);">&nbsp;</span
></td><td id="824"><a href="#824">824</a></td></tr
><tr id="gr_svn78_825"

 onmouseover="gutterOver(825)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',825);">&nbsp;</span
></td><td id="825"><a href="#825">825</a></td></tr
><tr id="gr_svn78_826"

 onmouseover="gutterOver(826)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',826);">&nbsp;</span
></td><td id="826"><a href="#826">826</a></td></tr
><tr id="gr_svn78_827"

 onmouseover="gutterOver(827)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',827);">&nbsp;</span
></td><td id="827"><a href="#827">827</a></td></tr
><tr id="gr_svn78_828"

 onmouseover="gutterOver(828)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',828);">&nbsp;</span
></td><td id="828"><a href="#828">828</a></td></tr
><tr id="gr_svn78_829"

 onmouseover="gutterOver(829)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',829);">&nbsp;</span
></td><td id="829"><a href="#829">829</a></td></tr
><tr id="gr_svn78_830"

 onmouseover="gutterOver(830)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',830);">&nbsp;</span
></td><td id="830"><a href="#830">830</a></td></tr
><tr id="gr_svn78_831"

 onmouseover="gutterOver(831)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',831);">&nbsp;</span
></td><td id="831"><a href="#831">831</a></td></tr
><tr id="gr_svn78_832"

 onmouseover="gutterOver(832)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',832);">&nbsp;</span
></td><td id="832"><a href="#832">832</a></td></tr
><tr id="gr_svn78_833"

 onmouseover="gutterOver(833)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',833);">&nbsp;</span
></td><td id="833"><a href="#833">833</a></td></tr
><tr id="gr_svn78_834"

 onmouseover="gutterOver(834)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',834);">&nbsp;</span
></td><td id="834"><a href="#834">834</a></td></tr
><tr id="gr_svn78_835"

 onmouseover="gutterOver(835)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',835);">&nbsp;</span
></td><td id="835"><a href="#835">835</a></td></tr
><tr id="gr_svn78_836"

 onmouseover="gutterOver(836)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',836);">&nbsp;</span
></td><td id="836"><a href="#836">836</a></td></tr
><tr id="gr_svn78_837"

 onmouseover="gutterOver(837)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',837);">&nbsp;</span
></td><td id="837"><a href="#837">837</a></td></tr
><tr id="gr_svn78_838"

 onmouseover="gutterOver(838)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',838);">&nbsp;</span
></td><td id="838"><a href="#838">838</a></td></tr
><tr id="gr_svn78_839"

 onmouseover="gutterOver(839)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',839);">&nbsp;</span
></td><td id="839"><a href="#839">839</a></td></tr
><tr id="gr_svn78_840"

 onmouseover="gutterOver(840)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',840);">&nbsp;</span
></td><td id="840"><a href="#840">840</a></td></tr
><tr id="gr_svn78_841"

 onmouseover="gutterOver(841)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',841);">&nbsp;</span
></td><td id="841"><a href="#841">841</a></td></tr
><tr id="gr_svn78_842"

 onmouseover="gutterOver(842)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',842);">&nbsp;</span
></td><td id="842"><a href="#842">842</a></td></tr
><tr id="gr_svn78_843"

 onmouseover="gutterOver(843)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',843);">&nbsp;</span
></td><td id="843"><a href="#843">843</a></td></tr
><tr id="gr_svn78_844"

 onmouseover="gutterOver(844)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',844);">&nbsp;</span
></td><td id="844"><a href="#844">844</a></td></tr
><tr id="gr_svn78_845"

 onmouseover="gutterOver(845)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',845);">&nbsp;</span
></td><td id="845"><a href="#845">845</a></td></tr
><tr id="gr_svn78_846"

 onmouseover="gutterOver(846)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',846);">&nbsp;</span
></td><td id="846"><a href="#846">846</a></td></tr
><tr id="gr_svn78_847"

 onmouseover="gutterOver(847)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',847);">&nbsp;</span
></td><td id="847"><a href="#847">847</a></td></tr
><tr id="gr_svn78_848"

 onmouseover="gutterOver(848)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',848);">&nbsp;</span
></td><td id="848"><a href="#848">848</a></td></tr
><tr id="gr_svn78_849"

 onmouseover="gutterOver(849)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',849);">&nbsp;</span
></td><td id="849"><a href="#849">849</a></td></tr
><tr id="gr_svn78_850"

 onmouseover="gutterOver(850)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',850);">&nbsp;</span
></td><td id="850"><a href="#850">850</a></td></tr
><tr id="gr_svn78_851"

 onmouseover="gutterOver(851)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',851);">&nbsp;</span
></td><td id="851"><a href="#851">851</a></td></tr
><tr id="gr_svn78_852"

 onmouseover="gutterOver(852)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',852);">&nbsp;</span
></td><td id="852"><a href="#852">852</a></td></tr
><tr id="gr_svn78_853"

 onmouseover="gutterOver(853)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',853);">&nbsp;</span
></td><td id="853"><a href="#853">853</a></td></tr
><tr id="gr_svn78_854"

 onmouseover="gutterOver(854)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',854);">&nbsp;</span
></td><td id="854"><a href="#854">854</a></td></tr
><tr id="gr_svn78_855"

 onmouseover="gutterOver(855)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',855);">&nbsp;</span
></td><td id="855"><a href="#855">855</a></td></tr
><tr id="gr_svn78_856"

 onmouseover="gutterOver(856)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',856);">&nbsp;</span
></td><td id="856"><a href="#856">856</a></td></tr
><tr id="gr_svn78_857"

 onmouseover="gutterOver(857)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',857);">&nbsp;</span
></td><td id="857"><a href="#857">857</a></td></tr
><tr id="gr_svn78_858"

 onmouseover="gutterOver(858)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',858);">&nbsp;</span
></td><td id="858"><a href="#858">858</a></td></tr
><tr id="gr_svn78_859"

 onmouseover="gutterOver(859)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',859);">&nbsp;</span
></td><td id="859"><a href="#859">859</a></td></tr
><tr id="gr_svn78_860"

 onmouseover="gutterOver(860)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',860);">&nbsp;</span
></td><td id="860"><a href="#860">860</a></td></tr
><tr id="gr_svn78_861"

 onmouseover="gutterOver(861)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',861);">&nbsp;</span
></td><td id="861"><a href="#861">861</a></td></tr
><tr id="gr_svn78_862"

 onmouseover="gutterOver(862)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',862);">&nbsp;</span
></td><td id="862"><a href="#862">862</a></td></tr
><tr id="gr_svn78_863"

 onmouseover="gutterOver(863)"

><td><span title="Add comment" onclick="codereviews.startEdit('svn78',863);">&nbsp;</span
></td><td id="863"><a href="#863">863</a></td></tr
></table></pre>
<pre><table width="100%"><tr class="nocursor"><td></td></tr></table></pre>
</td>
<td id="lines">
<pre><table width="100%"><tr class="cursor_stop cursor_hidden"><td></td></tr></table></pre>
<pre class="prettyprint lang-java"><table id="src_table_0"><tr
id=sl_svn78_1

 onmouseover="gutterOver(1)"

><td class="source">/**<br></td></tr
><tr
id=sl_svn78_2

 onmouseover="gutterOver(2)"

><td class="source"> * Licensed to the Apache Software Foundation (ASF) under one<br></td></tr
><tr
id=sl_svn78_3

 onmouseover="gutterOver(3)"

><td class="source"> * or more contributor license agreements.  See the NOTICE file<br></td></tr
><tr
id=sl_svn78_4

 onmouseover="gutterOver(4)"

><td class="source"> * distributed with this work for additional information<br></td></tr
><tr
id=sl_svn78_5

 onmouseover="gutterOver(5)"

><td class="source"> * regarding copyright ownership.  The ASF licenses this file<br></td></tr
><tr
id=sl_svn78_6

 onmouseover="gutterOver(6)"

><td class="source"> * to you under the Apache License, Version 2.0 (the<br></td></tr
><tr
id=sl_svn78_7

 onmouseover="gutterOver(7)"

><td class="source"> * &quot;License&quot;); you may not use this file except in compliance<br></td></tr
><tr
id=sl_svn78_8

 onmouseover="gutterOver(8)"

><td class="source"> * with the License.  You may obtain a copy of the License at<br></td></tr
><tr
id=sl_svn78_9

 onmouseover="gutterOver(9)"

><td class="source"> *<br></td></tr
><tr
id=sl_svn78_10

 onmouseover="gutterOver(10)"

><td class="source"> *     http://www.apache.org/licenses/LICENSE-2.0<br></td></tr
><tr
id=sl_svn78_11

 onmouseover="gutterOver(11)"

><td class="source"> *<br></td></tr
><tr
id=sl_svn78_12

 onmouseover="gutterOver(12)"

><td class="source"> * Unless required by applicable law or agreed to in writing, software<br></td></tr
><tr
id=sl_svn78_13

 onmouseover="gutterOver(13)"

><td class="source"> * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,<br></td></tr
><tr
id=sl_svn78_14

 onmouseover="gutterOver(14)"

><td class="source"> * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br></td></tr
><tr
id=sl_svn78_15

 onmouseover="gutterOver(15)"

><td class="source"> * See the License for the specific language governing permissions and<br></td></tr
><tr
id=sl_svn78_16

 onmouseover="gutterOver(16)"

><td class="source"> * limitations under the License.<br></td></tr
><tr
id=sl_svn78_17

 onmouseover="gutterOver(17)"

><td class="source"> */<br></td></tr
><tr
id=sl_svn78_18

 onmouseover="gutterOver(18)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_19

 onmouseover="gutterOver(19)"

><td class="source">package org.apache.hadoop.mapred;<br></td></tr
><tr
id=sl_svn78_20

 onmouseover="gutterOver(20)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_21

 onmouseover="gutterOver(21)"

><td class="source">import static org.apache.hadoop.mapred.Task.Counter.MAP_INPUT_BYTES;<br></td></tr
><tr
id=sl_svn78_22

 onmouseover="gutterOver(22)"

><td class="source">import static org.apache.hadoop.mapred.Task.Counter.MAP_INPUT_RECORDS;<br></td></tr
><tr
id=sl_svn78_23

 onmouseover="gutterOver(23)"

><td class="source">import static org.apache.hadoop.mapred.Task.Counter.MAP_OUTPUT_RECORDS;<br></td></tr
><tr
id=sl_svn78_24

 onmouseover="gutterOver(24)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_25

 onmouseover="gutterOver(25)"

><td class="source">import java.io.BufferedWriter;<br></td></tr
><tr
id=sl_svn78_26

 onmouseover="gutterOver(26)"

><td class="source">import java.io.DataInput;<br></td></tr
><tr
id=sl_svn78_27

 onmouseover="gutterOver(27)"

><td class="source">import java.io.DataInputStream;<br></td></tr
><tr
id=sl_svn78_28

 onmouseover="gutterOver(28)"

><td class="source">import java.io.DataOutput;<br></td></tr
><tr
id=sl_svn78_29

 onmouseover="gutterOver(29)"

><td class="source">import java.io.DataOutputStream;<br></td></tr
><tr
id=sl_svn78_30

 onmouseover="gutterOver(30)"

><td class="source">import java.io.File;<br></td></tr
><tr
id=sl_svn78_31

 onmouseover="gutterOver(31)"

><td class="source">import java.io.IOException;<br></td></tr
><tr
id=sl_svn78_32

 onmouseover="gutterOver(32)"

><td class="source">import java.io.OutputStreamWriter;<br></td></tr
><tr
id=sl_svn78_33

 onmouseover="gutterOver(33)"

><td class="source">import java.net.URI;<br></td></tr
><tr
id=sl_svn78_34

 onmouseover="gutterOver(34)"

><td class="source">import java.util.HashSet;<br></td></tr
><tr
id=sl_svn78_35

 onmouseover="gutterOver(35)"

><td class="source">import java.util.Set;<br></td></tr
><tr
id=sl_svn78_36

 onmouseover="gutterOver(36)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_37

 onmouseover="gutterOver(37)"

><td class="source">import org.apache.commons.logging.Log;<br></td></tr
><tr
id=sl_svn78_38

 onmouseover="gutterOver(38)"

><td class="source">import org.apache.commons.logging.LogFactory;<br></td></tr
><tr
id=sl_svn78_39

 onmouseover="gutterOver(39)"

><td class="source">import org.apache.hadoop.fs.FSDataInputStream;<br></td></tr
><tr
id=sl_svn78_40

 onmouseover="gutterOver(40)"

><td class="source">import org.apache.hadoop.fs.FSDataOutputStream;<br></td></tr
><tr
id=sl_svn78_41

 onmouseover="gutterOver(41)"

><td class="source">import org.apache.hadoop.fs.FileStatus;<br></td></tr
><tr
id=sl_svn78_42

 onmouseover="gutterOver(42)"

><td class="source">import org.apache.hadoop.fs.FileSystem;<br></td></tr
><tr
id=sl_svn78_43

 onmouseover="gutterOver(43)"

><td class="source">import org.apache.hadoop.fs.LocalFileSystem;<br></td></tr
><tr
id=sl_svn78_44

 onmouseover="gutterOver(44)"

><td class="source">import org.apache.hadoop.fs.Path;<br></td></tr
><tr
id=sl_svn78_45

 onmouseover="gutterOver(45)"

><td class="source">import org.apache.hadoop.io.BytesWritable;<br></td></tr
><tr
id=sl_svn78_46

 onmouseover="gutterOver(46)"

><td class="source">import org.apache.hadoop.io.DataInputBuffer;<br></td></tr
><tr
id=sl_svn78_47

 onmouseover="gutterOver(47)"

><td class="source">import org.apache.hadoop.io.IntWritable;<br></td></tr
><tr
id=sl_svn78_48

 onmouseover="gutterOver(48)"

><td class="source">import org.apache.hadoop.io.Text;<br></td></tr
><tr
id=sl_svn78_49

 onmouseover="gutterOver(49)"

><td class="source">import org.apache.hadoop.io.compress.CompressionCodec;<br></td></tr
><tr
id=sl_svn78_50

 onmouseover="gutterOver(50)"

><td class="source">import org.apache.hadoop.io.compress.DefaultCodec;<br></td></tr
><tr
id=sl_svn78_51

 onmouseover="gutterOver(51)"

><td class="source">import org.apache.hadoop.io.serializer.Deserializer;<br></td></tr
><tr
id=sl_svn78_52

 onmouseover="gutterOver(52)"

><td class="source">import org.apache.hadoop.io.serializer.SerializationFactory;<br></td></tr
><tr
id=sl_svn78_53

 onmouseover="gutterOver(53)"

><td class="source">import org.apache.hadoop.mapred.TaskCompletionEvent.Status;<br></td></tr
><tr
id=sl_svn78_54

 onmouseover="gutterOver(54)"

><td class="source">import org.apache.hadoop.mapred.buffer.BufferUmbilicalProtocol;<br></td></tr
><tr
id=sl_svn78_55

 onmouseover="gutterOver(55)"

><td class="source">import org.apache.hadoop.mapred.buffer.OutputFile;<br></td></tr
><tr
id=sl_svn78_56

 onmouseover="gutterOver(56)"

><td class="source">import org.apache.hadoop.mapred.buffer.OutputFile.Header;<br></td></tr
><tr
id=sl_svn78_57

 onmouseover="gutterOver(57)"

><td class="source">import org.apache.hadoop.mapred.buffer.impl.DataValuesIterator;<br></td></tr
><tr
id=sl_svn78_58

 onmouseover="gutterOver(58)"

><td class="source">import org.apache.hadoop.mapred.buffer.impl.JInputBuffer;<br></td></tr
><tr
id=sl_svn78_59

 onmouseover="gutterOver(59)"

><td class="source">import org.apache.hadoop.mapred.buffer.impl.JOutputBuffer;<br></td></tr
><tr
id=sl_svn78_60

 onmouseover="gutterOver(60)"

><td class="source">import org.apache.hadoop.mapred.buffer.impl.StaticData;<br></td></tr
><tr
id=sl_svn78_61

 onmouseover="gutterOver(61)"

><td class="source">import org.apache.hadoop.mapred.buffer.impl.ValuesIterator;<br></td></tr
><tr
id=sl_svn78_62

 onmouseover="gutterOver(62)"

><td class="source">import org.apache.hadoop.mapred.buffer.net.BufferExchange;<br></td></tr
><tr
id=sl_svn78_63

 onmouseover="gutterOver(63)"

><td class="source">import org.apache.hadoop.mapred.buffer.net.BufferRequest;<br></td></tr
><tr
id=sl_svn78_64

 onmouseover="gutterOver(64)"

><td class="source">import org.apache.hadoop.mapred.buffer.net.BufferExchangeSink;<br></td></tr
><tr
id=sl_svn78_65

 onmouseover="gutterOver(65)"

><td class="source">import org.apache.hadoop.mapred.buffer.net.MapBufferRequest;<br></td></tr
><tr
id=sl_svn78_66

 onmouseover="gutterOver(66)"

><td class="source">import org.apache.hadoop.mapred.buffer.net.ReduceBufferRequest;<br></td></tr
><tr
id=sl_svn78_67

 onmouseover="gutterOver(67)"

><td class="source">import org.apache.hadoop.util.Progress;<br></td></tr
><tr
id=sl_svn78_68

 onmouseover="gutterOver(68)"

><td class="source">import org.apache.hadoop.util.ReflectionUtils;<br></td></tr
><tr
id=sl_svn78_69

 onmouseover="gutterOver(69)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_70

 onmouseover="gutterOver(70)"

><td class="source">/** A Map task. */<br></td></tr
><tr
id=sl_svn78_71

 onmouseover="gutterOver(71)"

><td class="source">public class MapTask extends Task implements InputCollector {<br></td></tr
><tr
id=sl_svn78_72

 onmouseover="gutterOver(72)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_73

 onmouseover="gutterOver(73)"

><td class="source">	private class ReduceOutputFetcher extends Thread {<br></td></tr
><tr
id=sl_svn78_74

 onmouseover="gutterOver(74)"

><td class="source">		private TaskID oneReduceTaskId;<br></td></tr
><tr
id=sl_svn78_75

 onmouseover="gutterOver(75)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_76

 onmouseover="gutterOver(76)"

><td class="source">		private TaskUmbilicalProtocol trackerUmbilical;<br></td></tr
><tr
id=sl_svn78_77

 onmouseover="gutterOver(77)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_78

 onmouseover="gutterOver(78)"

><td class="source">		private BufferUmbilicalProtocol bufferUmbilical;<br></td></tr
><tr
id=sl_svn78_79

 onmouseover="gutterOver(79)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_80

 onmouseover="gutterOver(80)"

><td class="source">		private BufferExchangeSink sink;<br></td></tr
><tr
id=sl_svn78_81

 onmouseover="gutterOver(81)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_82

 onmouseover="gutterOver(82)"

><td class="source">		public ReduceOutputFetcher(TaskUmbilicalProtocol trackerUmbilical, <br></td></tr
><tr
id=sl_svn78_83

 onmouseover="gutterOver(83)"

><td class="source">				BufferUmbilicalProtocol bufferUmbilical, <br></td></tr
><tr
id=sl_svn78_84

 onmouseover="gutterOver(84)"

><td class="source">				BufferExchangeSink sink,<br></td></tr
><tr
id=sl_svn78_85

 onmouseover="gutterOver(85)"

><td class="source">				TaskID reduceTaskId) {<br></td></tr
><tr
id=sl_svn78_86

 onmouseover="gutterOver(86)"

><td class="source">			this.trackerUmbilical = trackerUmbilical;<br></td></tr
><tr
id=sl_svn78_87

 onmouseover="gutterOver(87)"

><td class="source">			this.bufferUmbilical = bufferUmbilical;<br></td></tr
><tr
id=sl_svn78_88

 onmouseover="gutterOver(88)"

><td class="source">			this.sink = sink;<br></td></tr
><tr
id=sl_svn78_89

 onmouseover="gutterOver(89)"

><td class="source">			this.oneReduceTaskId = reduceTaskId;<br></td></tr
><tr
id=sl_svn78_90

 onmouseover="gutterOver(90)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_91

 onmouseover="gutterOver(91)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_92

 onmouseover="gutterOver(92)"

><td class="source">		public void run() {<br></td></tr
><tr
id=sl_svn78_93

 onmouseover="gutterOver(93)"

><td class="source">			Set&lt;TaskID&gt; finishedReduceTasks = new HashSet&lt;TaskID&gt;();<br></td></tr
><tr
id=sl_svn78_94

 onmouseover="gutterOver(94)"

><td class="source">			Set&lt;TaskAttemptID&gt;  reduceTasks = new HashSet&lt;TaskAttemptID&gt;();<br></td></tr
><tr
id=sl_svn78_95

 onmouseover="gutterOver(95)"

><td class="source">			int eid = 0;<br></td></tr
><tr
id=sl_svn78_96

 onmouseover="gutterOver(96)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_97

 onmouseover="gutterOver(97)"

><td class="source">			while (!isInterrupted() &amp;&amp; finishedReduceTasks.size() &lt; getNumberOfInputs()+1) {<br></td></tr
><tr
id=sl_svn78_98

 onmouseover="gutterOver(98)"

><td class="source">				try {<br></td></tr
><tr
id=sl_svn78_99

 onmouseover="gutterOver(99)"

><td class="source">					ReduceTaskCompletionEventsUpdate updates = <br></td></tr
><tr
id=sl_svn78_100

 onmouseover="gutterOver(100)"

><td class="source">						trackerUmbilical.getReduceCompletionEvents(getJobID(), eid, Integer.MAX_VALUE);<br></td></tr
><tr
id=sl_svn78_101

 onmouseover="gutterOver(101)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_102

 onmouseover="gutterOver(102)"

><td class="source">					eid += updates.events.length;<br></td></tr
><tr
id=sl_svn78_103

 onmouseover="gutterOver(103)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_104

 onmouseover="gutterOver(104)"

><td class="source">					//LOG.info(&quot;get reduce task completion events : &quot; + eid);<br></td></tr
><tr
id=sl_svn78_105

 onmouseover="gutterOver(105)"

><td class="source">					// Process the TaskCompletionEvents:<br></td></tr
><tr
id=sl_svn78_106

 onmouseover="gutterOver(106)"

><td class="source">					// 1. Save the SUCCEEDED maps in knownOutputs to fetch the outputs.<br></td></tr
><tr
id=sl_svn78_107

 onmouseover="gutterOver(107)"

><td class="source">					// 2. Save the OBSOLETE/FAILED/KILLED maps in obsoleteOutputs to stop fetching<br></td></tr
><tr
id=sl_svn78_108

 onmouseover="gutterOver(108)"

><td class="source">					//    from those maps.<br></td></tr
><tr
id=sl_svn78_109

 onmouseover="gutterOver(109)"

><td class="source">					// 3. Remove TIPFAILED maps from neededOutputs since we don&#39;t need their<br></td></tr
><tr
id=sl_svn78_110

 onmouseover="gutterOver(110)"

><td class="source">					//    outputs at all.<br></td></tr
><tr
id=sl_svn78_111

 onmouseover="gutterOver(111)"

><td class="source">					for (TaskCompletionEvent event : updates.events) {<br></td></tr
><tr
id=sl_svn78_112

 onmouseover="gutterOver(112)"

><td class="source">						//LOG.info(&quot;event is &quot; + event + &quot; status is &quot; + event.getTaskStatus());<br></td></tr
><tr
id=sl_svn78_113

 onmouseover="gutterOver(113)"

><td class="source">						switch (event.getTaskStatus()) {<br></td></tr
><tr
id=sl_svn78_114

 onmouseover="gutterOver(114)"

><td class="source">						case FAILED:<br></td></tr
><tr
id=sl_svn78_115

 onmouseover="gutterOver(115)"

><td class="source">						case KILLED:<br></td></tr
><tr
id=sl_svn78_116

 onmouseover="gutterOver(116)"

><td class="source">						case OBSOLETE:<br></td></tr
><tr
id=sl_svn78_117

 onmouseover="gutterOver(117)"

><td class="source">						case TIPFAILED:<br></td></tr
><tr
id=sl_svn78_118

 onmouseover="gutterOver(118)"

><td class="source">						{<br></td></tr
><tr
id=sl_svn78_119

 onmouseover="gutterOver(119)"

><td class="source">							TaskAttemptID reduceTaskId = event.getTaskAttemptId();<br></td></tr
><tr
id=sl_svn78_120

 onmouseover="gutterOver(120)"

><td class="source">							if (!reduceTasks.contains(reduceTaskId)) {<br></td></tr
><tr
id=sl_svn78_121

 onmouseover="gutterOver(121)"

><td class="source">								reduceTasks.remove(reduceTaskId);<br></td></tr
><tr
id=sl_svn78_122

 onmouseover="gutterOver(122)"

><td class="source">							}<br></td></tr
><tr
id=sl_svn78_123

 onmouseover="gutterOver(123)"

><td class="source">						}<br></td></tr
><tr
id=sl_svn78_124

 onmouseover="gutterOver(124)"

><td class="source">						case SUCCEEDED:<br></td></tr
><tr
id=sl_svn78_125

 onmouseover="gutterOver(125)"

><td class="source">						{<br></td></tr
><tr
id=sl_svn78_126

 onmouseover="gutterOver(126)"

><td class="source">							TaskAttemptID mapTaskId = event.getTaskAttemptId();<br></td></tr
><tr
id=sl_svn78_127

 onmouseover="gutterOver(127)"

><td class="source">							finishedReduceTasks.add(mapTaskId.getTaskID());<br></td></tr
><tr
id=sl_svn78_128

 onmouseover="gutterOver(128)"

><td class="source">						}<br></td></tr
><tr
id=sl_svn78_129

 onmouseover="gutterOver(129)"

><td class="source">						case RUNNING:<br></td></tr
><tr
id=sl_svn78_130

 onmouseover="gutterOver(130)"

><td class="source">						{<br></td></tr
><tr
id=sl_svn78_131

 onmouseover="gutterOver(131)"

><td class="source">							URI u = URI.create(event.getTaskTrackerHttp());<br></td></tr
><tr
id=sl_svn78_132

 onmouseover="gutterOver(132)"

><td class="source">							String host = u.getHost();<br></td></tr
><tr
id=sl_svn78_133

 onmouseover="gutterOver(133)"

><td class="source">							TaskAttemptID reduceTasktId = event.getTaskAttemptId();<br></td></tr
><tr
id=sl_svn78_134

 onmouseover="gutterOver(134)"

><td class="source">							<br></td></tr
><tr
id=sl_svn78_135

 onmouseover="gutterOver(135)"

><td class="source">							//LOG.info(reduceAttemptId.getTaskID() + &quot; : &quot; + reduceTaskId);<br></td></tr
><tr
id=sl_svn78_136

 onmouseover="gutterOver(136)"

><td class="source">							if(job.getBoolean(&quot;mapred.iterative.mapsync&quot;, false)){<br></td></tr
><tr
id=sl_svn78_137

 onmouseover="gutterOver(137)"

><td class="source">								if (!reduceTasks.contains(reduceTasktId)) {<br></td></tr
><tr
id=sl_svn78_138

 onmouseover="gutterOver(138)"

><td class="source">									BufferExchange.BufferType type = BufferExchange.BufferType.ITERATE;<br></td></tr
><tr
id=sl_svn78_139

 onmouseover="gutterOver(139)"

><td class="source">									<br></td></tr
><tr
id=sl_svn78_140

 onmouseover="gutterOver(140)"

><td class="source">									BufferRequest request = <br></td></tr
><tr
id=sl_svn78_141

 onmouseover="gutterOver(141)"

><td class="source">										new ReduceBufferRequest(host, getTaskID(), sink.getAddress(), type, reduceTasktId.getTaskID());<br></td></tr
><tr
id=sl_svn78_142

 onmouseover="gutterOver(142)"

><td class="source">									try {<br></td></tr
><tr
id=sl_svn78_143

 onmouseover="gutterOver(143)"

><td class="source">										bufferUmbilical.request(request);<br></td></tr
><tr
id=sl_svn78_144

 onmouseover="gutterOver(144)"

><td class="source">										reduceTasks.add(reduceTasktId);<br></td></tr
><tr
id=sl_svn78_145

 onmouseover="gutterOver(145)"

><td class="source">										if (reduceTasks.size() == getNumberOfInputs()) {<br></td></tr
><tr
id=sl_svn78_146

 onmouseover="gutterOver(146)"

><td class="source">											LOG.info(&quot;ReduceTask &quot; + getTaskID() + &quot; has requested all reduce buffers. &quot; + <br></td></tr
><tr
id=sl_svn78_147

 onmouseover="gutterOver(147)"

><td class="source">													reduceTasks.size() + &quot; reduce buffers.&quot;);<br></td></tr
><tr
id=sl_svn78_148

 onmouseover="gutterOver(148)"

><td class="source">										}<br></td></tr
><tr
id=sl_svn78_149

 onmouseover="gutterOver(149)"

><td class="source">									} catch (IOException e) {<br></td></tr
><tr
id=sl_svn78_150

 onmouseover="gutterOver(150)"

><td class="source">										LOG.warn(&quot;BufferUmbilical problem in taking request &quot; + request + &quot;. &quot; + e);<br></td></tr
><tr
id=sl_svn78_151

 onmouseover="gutterOver(151)"

><td class="source">									}<br></td></tr
><tr
id=sl_svn78_152

 onmouseover="gutterOver(152)"

><td class="source">								}<br></td></tr
><tr
id=sl_svn78_153

 onmouseover="gutterOver(153)"

><td class="source">							}else{<br></td></tr
><tr
id=sl_svn78_154

 onmouseover="gutterOver(154)"

><td class="source">								//LOG.info(&quot;I am here for reduce buffer request &quot; + reduceTasktId.getTaskID() + &quot; : &quot; + oneReduceTaskId);<br></td></tr
><tr
id=sl_svn78_155

 onmouseover="gutterOver(155)"

><td class="source">								//wrong<br></td></tr
><tr
id=sl_svn78_156

 onmouseover="gutterOver(156)"

><td class="source">								<br></td></tr
><tr
id=sl_svn78_157

 onmouseover="gutterOver(157)"

><td class="source">								if (reduceTasktId.getTaskID().equals(oneReduceTaskId)) {<br></td></tr
><tr
id=sl_svn78_158

 onmouseover="gutterOver(158)"

><td class="source">									LOG.info(&quot;Map &quot; + getTaskID() + &quot; sending buffer request to reducer &quot; + oneReduceTaskId);<br></td></tr
><tr
id=sl_svn78_159

 onmouseover="gutterOver(159)"

><td class="source">									BufferExchange.BufferType type = BufferExchange.BufferType.ITERATE;<br></td></tr
><tr
id=sl_svn78_160

 onmouseover="gutterOver(160)"

><td class="source">									<br></td></tr
><tr
id=sl_svn78_161

 onmouseover="gutterOver(161)"

><td class="source">									BufferRequest request = <br></td></tr
><tr
id=sl_svn78_162

 onmouseover="gutterOver(162)"

><td class="source">										new ReduceBufferRequest(host, getTaskID(), sink.getAddress(), type, oneReduceTaskId);<br></td></tr
><tr
id=sl_svn78_163

 onmouseover="gutterOver(163)"

><td class="source">									<br></td></tr
><tr
id=sl_svn78_164

 onmouseover="gutterOver(164)"

><td class="source">									try {<br></td></tr
><tr
id=sl_svn78_165

 onmouseover="gutterOver(165)"

><td class="source">										bufferUmbilical.request(request);<br></td></tr
><tr
id=sl_svn78_166

 onmouseover="gutterOver(166)"

><td class="source">										if (event.getTaskStatus() == Status.SUCCEEDED) return;<br></td></tr
><tr
id=sl_svn78_167

 onmouseover="gutterOver(167)"

><td class="source">									} catch (IOException e) {<br></td></tr
><tr
id=sl_svn78_168

 onmouseover="gutterOver(168)"

><td class="source">										LOG.warn(&quot;BufferUmbilical problem sending request &quot; + request + &quot;. &quot; + e);<br></td></tr
><tr
id=sl_svn78_169

 onmouseover="gutterOver(169)"

><td class="source">									}<br></td></tr
><tr
id=sl_svn78_170

 onmouseover="gutterOver(170)"

><td class="source">								}<br></td></tr
><tr
id=sl_svn78_171

 onmouseover="gutterOver(171)"

><td class="source">							}					<br></td></tr
><tr
id=sl_svn78_172

 onmouseover="gutterOver(172)"

><td class="source">						}<br></td></tr
><tr
id=sl_svn78_173

 onmouseover="gutterOver(173)"

><td class="source">						break;<br></td></tr
><tr
id=sl_svn78_174

 onmouseover="gutterOver(174)"

><td class="source">						}<br></td></tr
><tr
id=sl_svn78_175

 onmouseover="gutterOver(175)"

><td class="source">					}<br></td></tr
><tr
id=sl_svn78_176

 onmouseover="gutterOver(176)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_177

 onmouseover="gutterOver(177)"

><td class="source">				catch (IOException e) {<br></td></tr
><tr
id=sl_svn78_178

 onmouseover="gutterOver(178)"

><td class="source">					e.printStackTrace();<br></td></tr
><tr
id=sl_svn78_179

 onmouseover="gutterOver(179)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_180

 onmouseover="gutterOver(180)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_181

 onmouseover="gutterOver(181)"

><td class="source">				try {<br></td></tr
><tr
id=sl_svn78_182

 onmouseover="gutterOver(182)"

><td class="source">					Thread.sleep(1000);<br></td></tr
><tr
id=sl_svn78_183

 onmouseover="gutterOver(183)"

><td class="source">				} catch (InterruptedException e) { }<br></td></tr
><tr
id=sl_svn78_184

 onmouseover="gutterOver(184)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_185

 onmouseover="gutterOver(185)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_186

 onmouseover="gutterOver(186)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_187

 onmouseover="gutterOver(187)"

><td class="source">	/**<br></td></tr
><tr
id=sl_svn78_188

 onmouseover="gutterOver(188)"

><td class="source">	 * The size of each record in the index file for the map-outputs.<br></td></tr
><tr
id=sl_svn78_189

 onmouseover="gutterOver(189)"

><td class="source">	 */<br></td></tr
><tr
id=sl_svn78_190

 onmouseover="gutterOver(190)"

><td class="source">	public static final int MAP_OUTPUT_INDEX_RECORD_LENGTH = 24;<br></td></tr
><tr
id=sl_svn78_191

 onmouseover="gutterOver(191)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_192

 onmouseover="gutterOver(192)"

><td class="source">	protected TrackedRecordReader recordReader = null;<br></td></tr
><tr
id=sl_svn78_193

 onmouseover="gutterOver(193)"

><td class="source">	private JobConf job = null;<br></td></tr
><tr
id=sl_svn78_194

 onmouseover="gutterOver(194)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_195

 onmouseover="gutterOver(195)"

><td class="source">	protected OutputCollector collector = null;	//the original one uses it<br></td></tr
><tr
id=sl_svn78_196

 onmouseover="gutterOver(196)"

><td class="source">	protected JOutputBuffer buffer = null;		//iterative mapreduce uses it<br></td></tr
><tr
id=sl_svn78_197

 onmouseover="gutterOver(197)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_198

 onmouseover="gutterOver(198)"

><td class="source">	private BytesWritable split = new BytesWritable();<br></td></tr
><tr
id=sl_svn78_199

 onmouseover="gutterOver(199)"

><td class="source">	private String splitClass;<br></td></tr
><tr
id=sl_svn78_200

 onmouseover="gutterOver(200)"

><td class="source">	private InputSplit instantiatedSplit = null;<br></td></tr
><tr
id=sl_svn78_201

 onmouseover="gutterOver(201)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_202

 onmouseover="gutterOver(202)"

><td class="source">    protected Class inputKeyClass;<br></td></tr
><tr
id=sl_svn78_203

 onmouseover="gutterOver(203)"

><td class="source">    protected Class inputValClass;<br></td></tr
><tr
id=sl_svn78_204

 onmouseover="gutterOver(204)"

><td class="source">    protected Class outputKeyClass;<br></td></tr
><tr
id=sl_svn78_205

 onmouseover="gutterOver(205)"

><td class="source">    protected Class outputValClass;<br></td></tr
><tr
id=sl_svn78_206

 onmouseover="gutterOver(206)"

><td class="source">    protected Class dataKeyClass;<br></td></tr
><tr
id=sl_svn78_207

 onmouseover="gutterOver(207)"

><td class="source">    protected Class dataValClass;<br></td></tr
><tr
id=sl_svn78_208

 onmouseover="gutterOver(208)"

><td class="source">    <br></td></tr
><tr
id=sl_svn78_209

 onmouseover="gutterOver(209)"

><td class="source">	protected Class&lt;? extends CompressionCodec&gt; mapOutputCodecClass = null;<br></td></tr
><tr
id=sl_svn78_210

 onmouseover="gutterOver(210)"

><td class="source">	protected Class&lt;? extends CompressionCodec&gt; reduceOutputCodecClass = null;<br></td></tr
><tr
id=sl_svn78_211

 onmouseover="gutterOver(211)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_212

 onmouseover="gutterOver(212)"

><td class="source">	protected Deserializer inputKeyDeserializer;<br></td></tr
><tr
id=sl_svn78_213

 onmouseover="gutterOver(213)"

><td class="source">	protected Deserializer inputValDeserializer;<br></td></tr
><tr
id=sl_svn78_214

 onmouseover="gutterOver(214)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_215

 onmouseover="gutterOver(215)"

><td class="source">	protected BufferUmbilicalProtocol bufferUmbilical;<br></td></tr
><tr
id=sl_svn78_216

 onmouseover="gutterOver(216)"

><td class="source">	protected Reporter reporter;<br></td></tr
><tr
id=sl_svn78_217

 onmouseover="gutterOver(217)"

><td class="source">	protected IterativeMapper mapper;<br></td></tr
><tr
id=sl_svn78_218

 onmouseover="gutterOver(218)"

><td class="source">	protected StaticData staticData;<br></td></tr
><tr
id=sl_svn78_219

 onmouseover="gutterOver(219)"

><td class="source">	protected int counter = 0;<br></td></tr
><tr
id=sl_svn78_220

 onmouseover="gutterOver(220)"

><td class="source">	private int iteration = 0;<br></td></tr
><tr
id=sl_svn78_221

 onmouseover="gutterOver(221)"

><td class="source">	private int snapshotInterval = 0;<br></td></tr
><tr
id=sl_svn78_222

 onmouseover="gutterOver(222)"

><td class="source">	private int snapshotIndex = 1;<br></td></tr
><tr
id=sl_svn78_223

 onmouseover="gutterOver(223)"

><td class="source">	private int stopIteration = 0;<br></td></tr
><tr
id=sl_svn78_224

 onmouseover="gutterOver(224)"

><td class="source">	private StaticData.MatchType joinType = null;<br></td></tr
><tr
id=sl_svn78_225

 onmouseover="gutterOver(225)"

><td class="source">	private long maptime = 0;<br></td></tr
><tr
id=sl_svn78_226

 onmouseover="gutterOver(226)"

><td class="source">	private long readtime = 0;<br></td></tr
><tr
id=sl_svn78_227

 onmouseover="gutterOver(227)"

><td class="source">	private FileSystem localfs = null;<br></td></tr
><tr
id=sl_svn78_228

 onmouseover="gutterOver(228)"

><td class="source">	private IFile.Writer cachewriter = null;<br></td></tr
><tr
id=sl_svn78_229

 onmouseover="gutterOver(229)"

><td class="source">    <br></td></tr
><tr
id=sl_svn78_230

 onmouseover="gutterOver(230)"

><td class="source">    protected TaskID predecessorReduceTaskId = null;<br></td></tr
><tr
id=sl_svn78_231

 onmouseover="gutterOver(231)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_232

 onmouseover="gutterOver(232)"

><td class="source">	private static final Log LOG = LogFactory.getLog(MapTask.class.getName());<br></td></tr
><tr
id=sl_svn78_233

 onmouseover="gutterOver(233)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_234

 onmouseover="gutterOver(234)"

><td class="source">	{   // set phase for this task<br></td></tr
><tr
id=sl_svn78_235

 onmouseover="gutterOver(235)"

><td class="source">		setPhase(TaskStatus.Phase.MAP); <br></td></tr
><tr
id=sl_svn78_236

 onmouseover="gutterOver(236)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_237

 onmouseover="gutterOver(237)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_238

 onmouseover="gutterOver(238)"

><td class="source">	public MapTask() {<br></td></tr
><tr
id=sl_svn78_239

 onmouseover="gutterOver(239)"

><td class="source">		super();<br></td></tr
><tr
id=sl_svn78_240

 onmouseover="gutterOver(240)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_241

 onmouseover="gutterOver(241)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_242

 onmouseover="gutterOver(242)"

><td class="source">	public MapTask(String jobFile, TaskAttemptID taskId, <br></td></tr
><tr
id=sl_svn78_243

 onmouseover="gutterOver(243)"

><td class="source">			int partition, String splitClass, BytesWritable split, boolean iterative) {<br></td></tr
><tr
id=sl_svn78_244

 onmouseover="gutterOver(244)"

><td class="source">		super(jobFile, taskId, partition);<br></td></tr
><tr
id=sl_svn78_245

 onmouseover="gutterOver(245)"

><td class="source">		this.splitClass = splitClass;<br></td></tr
><tr
id=sl_svn78_246

 onmouseover="gutterOver(246)"

><td class="source">		this.split = split;<br></td></tr
><tr
id=sl_svn78_247

 onmouseover="gutterOver(247)"

><td class="source">		this.iterative = iterative;<br></td></tr
><tr
id=sl_svn78_248

 onmouseover="gutterOver(248)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_249

 onmouseover="gutterOver(249)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_250

 onmouseover="gutterOver(250)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_251

 onmouseover="gutterOver(251)"

><td class="source">	public boolean isMapTask() {<br></td></tr
><tr
id=sl_svn78_252

 onmouseover="gutterOver(252)"

><td class="source">		return true;<br></td></tr
><tr
id=sl_svn78_253

 onmouseover="gutterOver(253)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_254

 onmouseover="gutterOver(254)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_255

 onmouseover="gutterOver(255)"

><td class="source">	public TaskID predecessorReduceTask(JobConf job) {<br></td></tr
><tr
id=sl_svn78_256

 onmouseover="gutterOver(256)"

><td class="source">		JobID reduceJobId = JobID.forName(job.get(&quot;mapred.job.predecessor&quot;));<br></td></tr
><tr
id=sl_svn78_257

 onmouseover="gutterOver(257)"

><td class="source">		return new TaskID(reduceJobId, false, getTaskID().getTaskID().id);<br></td></tr
><tr
id=sl_svn78_258

 onmouseover="gutterOver(258)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_259

 onmouseover="gutterOver(259)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_260

 onmouseover="gutterOver(260)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_261

 onmouseover="gutterOver(261)"

><td class="source">	public int getNumberOfInputs() { 		<br></td></tr
><tr
id=sl_svn78_262

 onmouseover="gutterOver(262)"

><td class="source">		if(job != null &amp;&amp; job.getBoolean(&quot;mapred.iterative.mapsync&quot;, false)){<br></td></tr
><tr
id=sl_svn78_263

 onmouseover="gutterOver(263)"

><td class="source">			return job.getInt(&quot;mapred.iterative.partitions&quot;, 0);<br></td></tr
><tr
id=sl_svn78_264

 onmouseover="gutterOver(264)"

><td class="source">		}else if(this.isIterative()){<br></td></tr
><tr
id=sl_svn78_265

 onmouseover="gutterOver(265)"

><td class="source">			return 1;<br></td></tr
><tr
id=sl_svn78_266

 onmouseover="gutterOver(266)"

><td class="source">		}else{<br></td></tr
><tr
id=sl_svn78_267

 onmouseover="gutterOver(267)"

><td class="source">			return super.getNumberOfInputs();<br></td></tr
><tr
id=sl_svn78_268

 onmouseover="gutterOver(268)"

><td class="source">		}	<br></td></tr
><tr
id=sl_svn78_269

 onmouseover="gutterOver(269)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_270

 onmouseover="gutterOver(270)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_271

 onmouseover="gutterOver(271)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_272

 onmouseover="gutterOver(272)"

><td class="source">	public void localizeConfiguration(JobConf conf) throws IOException {<br></td></tr
><tr
id=sl_svn78_273

 onmouseover="gutterOver(273)"

><td class="source">		super.localizeConfiguration(conf);<br></td></tr
><tr
id=sl_svn78_274

 onmouseover="gutterOver(274)"

><td class="source">		Path localSplit = new Path(new Path(getJobFile()).getParent(), <br></td></tr
><tr
id=sl_svn78_275

 onmouseover="gutterOver(275)"

><td class="source">				&quot;split.dta&quot;);<br></td></tr
><tr
id=sl_svn78_276

 onmouseover="gutterOver(276)"

><td class="source">		LOG.debug(&quot;Writing local split to &quot; + localSplit);<br></td></tr
><tr
id=sl_svn78_277

 onmouseover="gutterOver(277)"

><td class="source">		DataOutputStream out = FileSystem.getLocal(conf).create(localSplit);<br></td></tr
><tr
id=sl_svn78_278

 onmouseover="gutterOver(278)"

><td class="source">		Text.writeString(out, splitClass);<br></td></tr
><tr
id=sl_svn78_279

 onmouseover="gutterOver(279)"

><td class="source">		split.write(out);<br></td></tr
><tr
id=sl_svn78_280

 onmouseover="gutterOver(280)"

><td class="source">		out.close();<br></td></tr
><tr
id=sl_svn78_281

 onmouseover="gutterOver(281)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_282

 onmouseover="gutterOver(282)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_283

 onmouseover="gutterOver(283)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_284

 onmouseover="gutterOver(284)"

><td class="source">	public TaskRunner createRunner(TaskTracker tracker, TaskTracker.TaskInProgress tip) {<br></td></tr
><tr
id=sl_svn78_285

 onmouseover="gutterOver(285)"

><td class="source">		return new MapTaskRunner(tip, tracker, this.conf);<br></td></tr
><tr
id=sl_svn78_286

 onmouseover="gutterOver(286)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_287

 onmouseover="gutterOver(287)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_288

 onmouseover="gutterOver(288)"

><td class="source">	private void writeObject(java.io.ObjectOutputStream out) throws IOException {<br></td></tr
><tr
id=sl_svn78_289

 onmouseover="gutterOver(289)"

><td class="source">		write(out);<br></td></tr
><tr
id=sl_svn78_290

 onmouseover="gutterOver(290)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_291

 onmouseover="gutterOver(291)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_292

 onmouseover="gutterOver(292)"

><td class="source">	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {<br></td></tr
><tr
id=sl_svn78_293

 onmouseover="gutterOver(293)"

><td class="source">		readFields(in);<br></td></tr
><tr
id=sl_svn78_294

 onmouseover="gutterOver(294)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_295

 onmouseover="gutterOver(295)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_296

 onmouseover="gutterOver(296)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_297

 onmouseover="gutterOver(297)"

><td class="source">	public void write(DataOutput out) throws IOException {<br></td></tr
><tr
id=sl_svn78_298

 onmouseover="gutterOver(298)"

><td class="source">		super.write(out);<br></td></tr
><tr
id=sl_svn78_299

 onmouseover="gutterOver(299)"

><td class="source">		Text.writeString(out, splitClass);<br></td></tr
><tr
id=sl_svn78_300

 onmouseover="gutterOver(300)"

><td class="source">		if (split != null) split.write(out);<br></td></tr
><tr
id=sl_svn78_301

 onmouseover="gutterOver(301)"

><td class="source">		else throw new IOException(&quot;SPLIT IS NULL&quot;);<br></td></tr
><tr
id=sl_svn78_302

 onmouseover="gutterOver(302)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_303

 onmouseover="gutterOver(303)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_304

 onmouseover="gutterOver(304)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_305

 onmouseover="gutterOver(305)"

><td class="source">	public void readFields(DataInput in) throws IOException {<br></td></tr
><tr
id=sl_svn78_306

 onmouseover="gutterOver(306)"

><td class="source">		super.readFields(in);<br></td></tr
><tr
id=sl_svn78_307

 onmouseover="gutterOver(307)"

><td class="source">		splitClass = Text.readString(in);<br></td></tr
><tr
id=sl_svn78_308

 onmouseover="gutterOver(308)"

><td class="source">		split.readFields(in);<br></td></tr
><tr
id=sl_svn78_309

 onmouseover="gutterOver(309)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_310

 onmouseover="gutterOver(310)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_311

 onmouseover="gutterOver(311)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_312

 onmouseover="gutterOver(312)"

><td class="source">	InputSplit getInputSplit() throws UnsupportedOperationException {<br></td></tr
><tr
id=sl_svn78_313

 onmouseover="gutterOver(313)"

><td class="source">		return instantiatedSplit;<br></td></tr
><tr
id=sl_svn78_314

 onmouseover="gutterOver(314)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_315

 onmouseover="gutterOver(315)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_316

 onmouseover="gutterOver(316)"

><td class="source">	/**<br></td></tr
><tr
id=sl_svn78_317

 onmouseover="gutterOver(317)"

><td class="source">	 * This class wraps the user&#39;s record reader to update the counters and progress<br></td></tr
><tr
id=sl_svn78_318

 onmouseover="gutterOver(318)"

><td class="source">	 * as records are read.<br></td></tr
><tr
id=sl_svn78_319

 onmouseover="gutterOver(319)"

><td class="source">	 * @param &lt;K&gt;<br></td></tr
><tr
id=sl_svn78_320

 onmouseover="gutterOver(320)"

><td class="source">	 * @param &lt;V&gt;<br></td></tr
><tr
id=sl_svn78_321

 onmouseover="gutterOver(321)"

><td class="source">	 */<br></td></tr
><tr
id=sl_svn78_322

 onmouseover="gutterOver(322)"

><td class="source">	class TrackedRecordReader&lt;K, V&gt; <br></td></tr
><tr
id=sl_svn78_323

 onmouseover="gutterOver(323)"

><td class="source">	implements RecordReader&lt;K,V&gt; {<br></td></tr
><tr
id=sl_svn78_324

 onmouseover="gutterOver(324)"

><td class="source">		private RecordReader&lt;K,V&gt; rawIn;<br></td></tr
><tr
id=sl_svn78_325

 onmouseover="gutterOver(325)"

><td class="source">		private Counters.Counter inputByteCounter;<br></td></tr
><tr
id=sl_svn78_326

 onmouseover="gutterOver(326)"

><td class="source">		private Counters.Counter inputRecordCounter;<br></td></tr
><tr
id=sl_svn78_327

 onmouseover="gutterOver(327)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_328

 onmouseover="gutterOver(328)"

><td class="source">		TrackedRecordReader(RecordReader&lt;K,V&gt; raw, Counters counters) {<br></td></tr
><tr
id=sl_svn78_329

 onmouseover="gutterOver(329)"

><td class="source">			rawIn = raw;<br></td></tr
><tr
id=sl_svn78_330

 onmouseover="gutterOver(330)"

><td class="source">			inputRecordCounter = counters.findCounter(MAP_INPUT_RECORDS);<br></td></tr
><tr
id=sl_svn78_331

 onmouseover="gutterOver(331)"

><td class="source">			inputByteCounter = counters.findCounter(MAP_INPUT_BYTES);<br></td></tr
><tr
id=sl_svn78_332

 onmouseover="gutterOver(332)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_333

 onmouseover="gutterOver(333)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_334

 onmouseover="gutterOver(334)"

><td class="source">		public K createKey() {<br></td></tr
><tr
id=sl_svn78_335

 onmouseover="gutterOver(335)"

><td class="source">			return rawIn.createKey();<br></td></tr
><tr
id=sl_svn78_336

 onmouseover="gutterOver(336)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_337

 onmouseover="gutterOver(337)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_338

 onmouseover="gutterOver(338)"

><td class="source">		public V createValue() {<br></td></tr
><tr
id=sl_svn78_339

 onmouseover="gutterOver(339)"

><td class="source">			return rawIn.createValue();<br></td></tr
><tr
id=sl_svn78_340

 onmouseover="gutterOver(340)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_341

 onmouseover="gutterOver(341)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_342

 onmouseover="gutterOver(342)"

><td class="source">		public synchronized boolean next(K key, V value)<br></td></tr
><tr
id=sl_svn78_343

 onmouseover="gutterOver(343)"

><td class="source">		throws IOException {<br></td></tr
><tr
id=sl_svn78_344

 onmouseover="gutterOver(344)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_345

 onmouseover="gutterOver(345)"

><td class="source">			setProgress(getProgress());<br></td></tr
><tr
id=sl_svn78_346

 onmouseover="gutterOver(346)"

><td class="source">			long beforePos = getPos();<br></td></tr
><tr
id=sl_svn78_347

 onmouseover="gutterOver(347)"

><td class="source">			boolean ret = rawIn.next(key, value);<br></td></tr
><tr
id=sl_svn78_348

 onmouseover="gutterOver(348)"

><td class="source">			if (ret) {<br></td></tr
><tr
id=sl_svn78_349

 onmouseover="gutterOver(349)"

><td class="source">				inputRecordCounter.increment(1);<br></td></tr
><tr
id=sl_svn78_350

 onmouseover="gutterOver(350)"

><td class="source">				inputByteCounter.increment(Math.abs(getPos() - beforePos));<br></td></tr
><tr
id=sl_svn78_351

 onmouseover="gutterOver(351)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_352

 onmouseover="gutterOver(352)"

><td class="source">			return ret;<br></td></tr
><tr
id=sl_svn78_353

 onmouseover="gutterOver(353)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_354

 onmouseover="gutterOver(354)"

><td class="source">		public long getPos() throws IOException { return rawIn.getPos(); }<br></td></tr
><tr
id=sl_svn78_355

 onmouseover="gutterOver(355)"

><td class="source">		public void close() throws IOException { rawIn.close(); }<br></td></tr
><tr
id=sl_svn78_356

 onmouseover="gutterOver(356)"

><td class="source">		public float getProgress() throws IOException {<br></td></tr
><tr
id=sl_svn78_357

 onmouseover="gutterOver(357)"

><td class="source">			return rawIn.getProgress();<br></td></tr
><tr
id=sl_svn78_358

 onmouseover="gutterOver(358)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_359

 onmouseover="gutterOver(359)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_360

 onmouseover="gutterOver(360)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_361

 onmouseover="gutterOver(361)"

><td class="source">	public void setProgress(float progress) {<br></td></tr
><tr
id=sl_svn78_362

 onmouseover="gutterOver(362)"

><td class="source">		super.setProgress(progress);<br></td></tr
><tr
id=sl_svn78_363

 onmouseover="gutterOver(363)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_364

 onmouseover="gutterOver(364)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_365

 onmouseover="gutterOver(365)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_366

 onmouseover="gutterOver(366)"

><td class="source">	@SuppressWarnings(&quot;unchecked&quot;)<br></td></tr
><tr
id=sl_svn78_367

 onmouseover="gutterOver(367)"

><td class="source">	public void run(final JobConf job, final TaskUmbilicalProtocol umbilical, final BufferUmbilicalProtocol bufferUmbilical)<br></td></tr
><tr
id=sl_svn78_368

 onmouseover="gutterOver(368)"

><td class="source">	throws IOException {<br></td></tr
><tr
id=sl_svn78_369

 onmouseover="gutterOver(369)"

><td class="source">		 reporter = getReporter(umbilical);<br></td></tr
><tr
id=sl_svn78_370

 onmouseover="gutterOver(370)"

><td class="source">		this.job = job;<br></td></tr
><tr
id=sl_svn78_371

 onmouseover="gutterOver(371)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_372

 onmouseover="gutterOver(372)"

><td class="source">		// start thread that will handle communication with parent<br></td></tr
><tr
id=sl_svn78_373

 onmouseover="gutterOver(373)"

><td class="source">	    startCommunicationThread(umbilical);<br></td></tr
><tr
id=sl_svn78_374

 onmouseover="gutterOver(374)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_375

 onmouseover="gutterOver(375)"

><td class="source">		initialize(job, reporter);<br></td></tr
><tr
id=sl_svn78_376

 onmouseover="gutterOver(376)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_377

 onmouseover="gutterOver(377)"

><td class="source">	    // check if it is a cleanupJobTask<br></td></tr
><tr
id=sl_svn78_378

 onmouseover="gutterOver(378)"

><td class="source">	    if (jobCleanup) {<br></td></tr
><tr
id=sl_svn78_379

 onmouseover="gutterOver(379)"

><td class="source">	      runJobCleanupTask(umbilical);<br></td></tr
><tr
id=sl_svn78_380

 onmouseover="gutterOver(380)"

><td class="source">	      return;<br></td></tr
><tr
id=sl_svn78_381

 onmouseover="gutterOver(381)"

><td class="source">	    }<br></td></tr
><tr
id=sl_svn78_382

 onmouseover="gutterOver(382)"

><td class="source">	    if (jobSetup) {<br></td></tr
><tr
id=sl_svn78_383

 onmouseover="gutterOver(383)"

><td class="source">	      runJobSetupTask(umbilical);<br></td></tr
><tr
id=sl_svn78_384

 onmouseover="gutterOver(384)"

><td class="source">	      return;<br></td></tr
><tr
id=sl_svn78_385

 onmouseover="gutterOver(385)"

><td class="source">	    }<br></td></tr
><tr
id=sl_svn78_386

 onmouseover="gutterOver(386)"

><td class="source">	    if (taskCleanup) {<br></td></tr
><tr
id=sl_svn78_387

 onmouseover="gutterOver(387)"

><td class="source">	      runTaskCleanupTask(umbilical);<br></td></tr
><tr
id=sl_svn78_388

 onmouseover="gutterOver(388)"

><td class="source">	      return;<br></td></tr
><tr
id=sl_svn78_389

 onmouseover="gutterOver(389)"

><td class="source">	    }<br></td></tr
><tr
id=sl_svn78_390

 onmouseover="gutterOver(390)"

><td class="source">	    <br></td></tr
><tr
id=sl_svn78_391

 onmouseover="gutterOver(391)"

><td class="source">		int numReduceTasks = conf.getNumReduceTasks();<br></td></tr
><tr
id=sl_svn78_392

 onmouseover="gutterOver(392)"

><td class="source">		LOG.info(&quot;numReduceTasks: &quot; + numReduceTasks);<br></td></tr
><tr
id=sl_svn78_393

 onmouseover="gutterOver(393)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_394

 onmouseover="gutterOver(394)"

><td class="source">		//iterative version<br></td></tr
><tr
id=sl_svn78_395

 onmouseover="gutterOver(395)"

><td class="source">		if(this.iterative){		<br></td></tr
><tr
id=sl_svn78_396

 onmouseover="gutterOver(396)"

><td class="source">			this.bufferUmbilical = bufferUmbilical;<br></td></tr
><tr
id=sl_svn78_397

 onmouseover="gutterOver(397)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_398

 onmouseover="gutterOver(398)"

><td class="source">			Class mapCombiner = job.getClass(&quot;mapred.map.combiner.class&quot;, null);<br></td></tr
><tr
id=sl_svn78_399

 onmouseover="gutterOver(399)"

><td class="source">			if (mapCombiner != null) {<br></td></tr
><tr
id=sl_svn78_400

 onmouseover="gutterOver(400)"

><td class="source">				job.setCombinerClass(mapCombiner);<br></td></tr
><tr
id=sl_svn78_401

 onmouseover="gutterOver(401)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_402

 onmouseover="gutterOver(402)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_403

 onmouseover="gutterOver(403)"

><td class="source">			snapshotInterval = job.getInt(&quot;mapred.iterative.snapshot.interval&quot;, Integer.MAX_VALUE);<br></td></tr
><tr
id=sl_svn78_404

 onmouseover="gutterOver(404)"

><td class="source">			stopIteration = job.getInt(&quot;mapred.iterative.stop.iteration&quot;, Integer.MAX_VALUE);<br></td></tr
><tr
id=sl_svn78_405

 onmouseover="gutterOver(405)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_406

 onmouseover="gutterOver(406)"

><td class="source">			this.outputKeyClass = job.getMapOutputKeyClass();<br></td></tr
><tr
id=sl_svn78_407

 onmouseover="gutterOver(407)"

><td class="source">			this.outputValClass = job.getMapOutputValueClass();<br></td></tr
><tr
id=sl_svn78_408

 onmouseover="gutterOver(408)"

><td class="source">			this.inputKeyClass = job.getInputKeyClass();<br></td></tr
><tr
id=sl_svn78_409

 onmouseover="gutterOver(409)"

><td class="source">			this.inputValClass = job.getInputValueClass();<br></td></tr
><tr
id=sl_svn78_410

 onmouseover="gutterOver(410)"

><td class="source">			this.dataKeyClass = job.getDataKeyClass();<br></td></tr
><tr
id=sl_svn78_411

 onmouseover="gutterOver(411)"

><td class="source">			this.dataValClass = job.getDataValClass();<br></td></tr
><tr
id=sl_svn78_412

 onmouseover="gutterOver(412)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_413

 onmouseover="gutterOver(413)"

><td class="source">			//for sync map test<br></td></tr
><tr
id=sl_svn78_414

 onmouseover="gutterOver(414)"

><td class="source">			localfs = FileSystem.getLocal(job);<br></td></tr
><tr
id=sl_svn78_415

 onmouseover="gutterOver(415)"

><td class="source">			Path localpath = new Path(&quot;/tmp/cache&quot;);<br></td></tr
><tr
id=sl_svn78_416

 onmouseover="gutterOver(416)"

><td class="source">			if(localfs.exists(localpath)){<br></td></tr
><tr
id=sl_svn78_417

 onmouseover="gutterOver(417)"

><td class="source">				localfs.delete(localpath, true);<br></td></tr
><tr
id=sl_svn78_418

 onmouseover="gutterOver(418)"

><td class="source">			};<br></td></tr
><tr
id=sl_svn78_419

 onmouseover="gutterOver(419)"

><td class="source">			cachewriter = new IFile.Writer(conf, localfs, localpath,<br></td></tr
><tr
id=sl_svn78_420

 onmouseover="gutterOver(420)"

><td class="source">					inputKeyClass, inputValClass, null, null);<br></td></tr
><tr
id=sl_svn78_421

 onmouseover="gutterOver(421)"

><td class="source">		    <br></td></tr
><tr
id=sl_svn78_422

 onmouseover="gutterOver(422)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_423

 onmouseover="gutterOver(423)"

><td class="source">			if (conf.getCompressMapOutput()) {<br></td></tr
><tr
id=sl_svn78_424

 onmouseover="gutterOver(424)"

><td class="source">				mapOutputCodecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);<br></td></tr
><tr
id=sl_svn78_425

 onmouseover="gutterOver(425)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_426

 onmouseover="gutterOver(426)"

><td class="source">			if (conf.getCompressReduceOutput()) {<br></td></tr
><tr
id=sl_svn78_427

 onmouseover="gutterOver(427)"

><td class="source">				reduceOutputCodecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);<br></td></tr
><tr
id=sl_svn78_428

 onmouseover="gutterOver(428)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_429

 onmouseover="gutterOver(429)"

><td class="source">			 <br></td></tr
><tr
id=sl_svn78_430

 onmouseover="gutterOver(430)"

><td class="source">			this.buffer = new JOutputBuffer(bufferUmbilical, this, job, <br></td></tr
><tr
id=sl_svn78_431

 onmouseover="gutterOver(431)"

><td class="source">					reporter, getProgress(), false, <br></td></tr
><tr
id=sl_svn78_432

 onmouseover="gutterOver(432)"

><td class="source">					this.outputKeyClass, this.outputValClass, mapOutputCodecClass);<br></td></tr
><tr
id=sl_svn78_433

 onmouseover="gutterOver(433)"

><td class="source">			this.buffer.setIterative(true);<br></td></tr
><tr
id=sl_svn78_434

 onmouseover="gutterOver(434)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_435

 onmouseover="gutterOver(435)"

><td class="source">			mapper = (IterativeMapper) ReflectionUtils.newInstance(job.getMapperClass(), job);<br></td></tr
><tr
id=sl_svn78_436

 onmouseover="gutterOver(436)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_437

 onmouseover="gutterOver(437)"

><td class="source">			//get the predecessor job&#39;s corresponding reduce id<br></td></tr
><tr
id=sl_svn78_438

 onmouseover="gutterOver(438)"

><td class="source">			while(this.predecessorReduceTaskId == null){<br></td></tr
><tr
id=sl_svn78_439

 onmouseover="gutterOver(439)"

><td class="source">				JobID predecessorJobID = JobID.forName(conf.get(&quot;mapred.job.predecessor&quot;));<br></td></tr
><tr
id=sl_svn78_440

 onmouseover="gutterOver(440)"

><td class="source">				this.predecessorReduceTaskId = new TaskID(predecessorJobID, false, this.getTaskID().getTaskID().getId());<br></td></tr
><tr
id=sl_svn78_441

 onmouseover="gutterOver(441)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_442

 onmouseover="gutterOver(442)"

><td class="source">			LOG.info(&quot;local reduce task id extracted &quot; + predecessorReduceTaskId);<br></td></tr
><tr
id=sl_svn78_443

 onmouseover="gutterOver(443)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_444

 onmouseover="gutterOver(444)"

><td class="source">			BufferExchangeSink sink = new BufferExchangeSink(job, this, this); <br></td></tr
><tr
id=sl_svn78_445

 onmouseover="gutterOver(445)"

><td class="source">			sink.open();<br></td></tr
><tr
id=sl_svn78_446

 onmouseover="gutterOver(446)"

><td class="source">			LOG.info(&quot;iterate sink opened&quot;);<br></td></tr
><tr
id=sl_svn78_447

 onmouseover="gutterOver(447)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_448

 onmouseover="gutterOver(448)"

><td class="source">			ReduceOutputFetcher rof = new ReduceOutputFetcher(umbilical, bufferUmbilical, sink, predecessorReduceTaskId);<br></td></tr
><tr
id=sl_svn78_449

 onmouseover="gutterOver(449)"

><td class="source">			rof.setDaemon(true);<br></td></tr
><tr
id=sl_svn78_450

 onmouseover="gutterOver(450)"

><td class="source">			rof.start();<br></td></tr
><tr
id=sl_svn78_451

 onmouseover="gutterOver(451)"

><td class="source">					<br></td></tr
><tr
id=sl_svn78_452

 onmouseover="gutterOver(452)"

><td class="source">			Path[] stateDataPaths = mapper.initStateData();<br></td></tr
><tr
id=sl_svn78_453

 onmouseover="gutterOver(453)"

><td class="source">			Path staticDataPath = mapper.initStaticData();<br></td></tr
><tr
id=sl_svn78_454

 onmouseover="gutterOver(454)"

><td class="source">			String type = job.get(&quot;mapred.iterative.jointype&quot;, &quot;one2one&quot;);<br></td></tr
><tr
id=sl_svn78_455

 onmouseover="gutterOver(455)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_456

 onmouseover="gutterOver(456)"

><td class="source">			if(type.equals(&quot;one2all&quot;)){<br></td></tr
><tr
id=sl_svn78_457

 onmouseover="gutterOver(457)"

><td class="source">				joinType = StaticData.MatchType.ONE2ALL;<br></td></tr
><tr
id=sl_svn78_458

 onmouseover="gutterOver(458)"

><td class="source">			}else {<br></td></tr
><tr
id=sl_svn78_459

 onmouseover="gutterOver(459)"

><td class="source">				joinType = StaticData.MatchType.ONE2ONE;<br></td></tr
><tr
id=sl_svn78_460

 onmouseover="gutterOver(460)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_461

 onmouseover="gutterOver(461)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_462

 onmouseover="gutterOver(462)"

><td class="source">			//if null no static data involved<br></td></tr
><tr
id=sl_svn78_463

 onmouseover="gutterOver(463)"

><td class="source">			if(staticDataPath != null){<br></td></tr
><tr
id=sl_svn78_464

 onmouseover="gutterOver(464)"

><td class="source">				staticData = new StaticData(conf, staticDataPath, joinType, dataKeyClass, dataValClass); <br></td></tr
><tr
id=sl_svn78_465

 onmouseover="gutterOver(465)"

><td class="source">			}else{<br></td></tr
><tr
id=sl_svn78_466

 onmouseover="gutterOver(466)"

><td class="source">				staticData = null;<br></td></tr
><tr
id=sl_svn78_467

 onmouseover="gutterOver(467)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_468

 onmouseover="gutterOver(468)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_469

 onmouseover="gutterOver(469)"

><td class="source">			SerializationFactory serializationFactory = new SerializationFactory(conf);<br></td></tr
><tr
id=sl_svn78_470

 onmouseover="gutterOver(470)"

><td class="source">			inputKeyDeserializer = serializationFactory.getDeserializer(inputKeyClass);<br></td></tr
><tr
id=sl_svn78_471

 onmouseover="gutterOver(471)"

><td class="source">			inputValDeserializer = serializationFactory.getDeserializer(inputValClass);<br></td></tr
><tr
id=sl_svn78_472

 onmouseover="gutterOver(472)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_473

 onmouseover="gutterOver(473)"

><td class="source">			if(stateDataPaths !=  null){<br></td></tr
><tr
id=sl_svn78_474

 onmouseover="gutterOver(474)"

><td class="source">				long mapbegin= System.currentTimeMillis();<br></td></tr
><tr
id=sl_svn78_475

 onmouseover="gutterOver(475)"

><td class="source">				LocalFileSystem localFs = FileSystem.getLocal(job);<br></td></tr
><tr
id=sl_svn78_476

 onmouseover="gutterOver(476)"

><td class="source">			    for(Path stateDataPath : stateDataPaths){<br></td></tr
><tr
id=sl_svn78_477

 onmouseover="gutterOver(477)"

><td class="source">			    	long filelen = localFs.getFileStatus(stateDataPath).getLen();<br></td></tr
><tr
id=sl_svn78_478

 onmouseover="gutterOver(478)"

><td class="source">					FSDataInputStream stateIn = localFs.open(stateDataPath);<br></td></tr
><tr
id=sl_svn78_479

 onmouseover="gutterOver(479)"

><td class="source">					<br></td></tr
><tr
id=sl_svn78_480

 onmouseover="gutterOver(480)"

><td class="source">					//initial mapreduce<br></td></tr
><tr
id=sl_svn78_481

 onmouseover="gutterOver(481)"

><td class="source">					IFile.Reader&lt;Object, Object&gt; initReader = new IFile.Reader(job, stateIn, filelen, null, null);<br></td></tr
><tr
id=sl_svn78_482

 onmouseover="gutterOver(482)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_483

 onmouseover="gutterOver(483)"

><td class="source">					DataInputBuffer key = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_484

 onmouseover="gutterOver(484)"

><td class="source">					DataInputBuffer value = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_485

 onmouseover="gutterOver(485)"

><td class="source">					Object keyObject = null;<br></td></tr
><tr
id=sl_svn78_486

 onmouseover="gutterOver(486)"

><td class="source">					Object valObject = null;<br></td></tr
><tr
id=sl_svn78_487

 onmouseover="gutterOver(487)"

><td class="source">					<br></td></tr
><tr
id=sl_svn78_488

 onmouseover="gutterOver(488)"

><td class="source">					if(staticData != null){<br></td></tr
><tr
id=sl_svn78_489

 onmouseover="gutterOver(489)"

><td class="source">						//have static data, perform join operation before map<br></td></tr
><tr
id=sl_svn78_490

 onmouseover="gutterOver(490)"

><td class="source">						if(joinType == StaticData.MatchType.ONE2ONE){<br></td></tr
><tr
id=sl_svn78_491

 onmouseover="gutterOver(491)"

><td class="source">							while(initReader.next(key, value)){<br></td></tr
><tr
id=sl_svn78_492

 onmouseover="gutterOver(492)"

><td class="source">								inputKeyDeserializer.open(key);<br></td></tr
><tr
id=sl_svn78_493

 onmouseover="gutterOver(493)"

><td class="source">								inputValDeserializer.open(value);<br></td></tr
><tr
id=sl_svn78_494

 onmouseover="gutterOver(494)"

><td class="source">								keyObject = inputKeyDeserializer.deserialize(keyObject);<br></td></tr
><tr
id=sl_svn78_495

 onmouseover="gutterOver(495)"

><td class="source">								valObject = inputValDeserializer.deserialize(valObject);<br></td></tr
><tr
id=sl_svn78_496

 onmouseover="gutterOver(496)"

><td class="source">								<br></td></tr
><tr
id=sl_svn78_497

 onmouseover="gutterOver(497)"

><td class="source">								staticData.next();<br></td></tr
><tr
id=sl_svn78_498

 onmouseover="gutterOver(498)"

><td class="source">								mapper.map(keyObject, valObject, staticData.getKey(), staticData.getValue(), this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_499

 onmouseover="gutterOver(499)"

><td class="source">							}<br></td></tr
><tr
id=sl_svn78_500

 onmouseover="gutterOver(500)"

><td class="source">						}else if(joinType == StaticData.MatchType.ONE2ALL){<br></td></tr
><tr
id=sl_svn78_501

 onmouseover="gutterOver(501)"

><td class="source">							while(initReader.next(key, value)){<br></td></tr
><tr
id=sl_svn78_502

 onmouseover="gutterOver(502)"

><td class="source">								inputKeyDeserializer.open(key);<br></td></tr
><tr
id=sl_svn78_503

 onmouseover="gutterOver(503)"

><td class="source">								inputValDeserializer.open(value);<br></td></tr
><tr
id=sl_svn78_504

 onmouseover="gutterOver(504)"

><td class="source">								keyObject = inputKeyDeserializer.deserialize(keyObject);<br></td></tr
><tr
id=sl_svn78_505

 onmouseover="gutterOver(505)"

><td class="source">								valObject = inputValDeserializer.deserialize(valObject);<br></td></tr
><tr
id=sl_svn78_506

 onmouseover="gutterOver(506)"

><td class="source">								mapper.map(keyObject, valObject, null, null, this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_507

 onmouseover="gutterOver(507)"

><td class="source">							}<br></td></tr
><tr
id=sl_svn78_508

 onmouseover="gutterOver(508)"

><td class="source">						}<br></td></tr
><tr
id=sl_svn78_509

 onmouseover="gutterOver(509)"

><td class="source">					}else{<br></td></tr
><tr
id=sl_svn78_510

 onmouseover="gutterOver(510)"

><td class="source">						// no static data, parse the state data in a single pass<br></td></tr
><tr
id=sl_svn78_511

 onmouseover="gutterOver(511)"

><td class="source">						while(initReader.next(key, value)){<br></td></tr
><tr
id=sl_svn78_512

 onmouseover="gutterOver(512)"

><td class="source">							inputKeyDeserializer.open(key);<br></td></tr
><tr
id=sl_svn78_513

 onmouseover="gutterOver(513)"

><td class="source">							inputValDeserializer.open(value);<br></td></tr
><tr
id=sl_svn78_514

 onmouseover="gutterOver(514)"

><td class="source">							keyObject = inputKeyDeserializer.deserialize(keyObject);<br></td></tr
><tr
id=sl_svn78_515

 onmouseover="gutterOver(515)"

><td class="source">							valObject = inputValDeserializer.deserialize(valObject);<br></td></tr
><tr
id=sl_svn78_516

 onmouseover="gutterOver(516)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_517

 onmouseover="gutterOver(517)"

><td class="source">							mapper.map(keyObject, valObject, null, null, this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_518

 onmouseover="gutterOver(518)"

><td class="source">						}<br></td></tr
><tr
id=sl_svn78_519

 onmouseover="gutterOver(519)"

><td class="source">					}<br></td></tr
><tr
id=sl_svn78_520

 onmouseover="gutterOver(520)"

><td class="source">					<br></td></tr
><tr
id=sl_svn78_521

 onmouseover="gutterOver(521)"

><td class="source">					initReader.close();<br></td></tr
><tr
id=sl_svn78_522

 onmouseover="gutterOver(522)"

><td class="source">					<br></td></tr
><tr
id=sl_svn78_523

 onmouseover="gutterOver(523)"

><td class="source">				    maptime += System.currentTimeMillis() - mapbegin;<br></td></tr
><tr
id=sl_svn78_524

 onmouseover="gutterOver(524)"

><td class="source">					   <br></td></tr
><tr
id=sl_svn78_525

 onmouseover="gutterOver(525)"

><td class="source">				    if(staticData != null &amp;&amp; joinType == StaticData.MatchType.ONE2ALL){<br></td></tr
><tr
id=sl_svn78_526

 onmouseover="gutterOver(526)"

><td class="source">				    	while(staticData.next()){<br></td></tr
><tr
id=sl_svn78_527

 onmouseover="gutterOver(527)"

><td class="source">				    		mapper.map(null, null, staticData.getKey(), staticData.getValue(), this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_528

 onmouseover="gutterOver(528)"

><td class="source">				    	}    	<br></td></tr
><tr
id=sl_svn78_529

 onmouseover="gutterOver(529)"

><td class="source">				    }<br></td></tr
><tr
id=sl_svn78_530

 onmouseover="gutterOver(530)"

><td class="source">			    }<br></td></tr
><tr
id=sl_svn78_531

 onmouseover="gutterOver(531)"

><td class="source">			    this.mapper.iterate();<br></td></tr
><tr
id=sl_svn78_532

 onmouseover="gutterOver(532)"

><td class="source">				LOG.info(&quot;first round finished!&quot;);<br></td></tr
><tr
id=sl_svn78_533

 onmouseover="gutterOver(533)"

><td class="source">				this.buffer.stream(iteration++, true);<br></td></tr
><tr
id=sl_svn78_534

 onmouseover="gutterOver(534)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_535

 onmouseover="gutterOver(535)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_536

 onmouseover="gutterOver(536)"

><td class="source">			synchronized(this){<br></td></tr
><tr
id=sl_svn78_537

 onmouseover="gutterOver(537)"

><td class="source">				try{<br></td></tr
><tr
id=sl_svn78_538

 onmouseover="gutterOver(538)"

><td class="source">					this.wait();										<br></td></tr
><tr
id=sl_svn78_539

 onmouseover="gutterOver(539)"

><td class="source">				} catch (InterruptedException e) {<br></td></tr
><tr
id=sl_svn78_540

 onmouseover="gutterOver(540)"

><td class="source">					// TODO Auto-generated catch block<br></td></tr
><tr
id=sl_svn78_541

 onmouseover="gutterOver(541)"

><td class="source">					e.printStackTrace();<br></td></tr
><tr
id=sl_svn78_542

 onmouseover="gutterOver(542)"

><td class="source">				}finally {<br></td></tr
><tr
id=sl_svn78_543

 onmouseover="gutterOver(543)"

><td class="source">					LOG.info(&quot;we can stop&quot;);<br></td></tr
><tr
id=sl_svn78_544

 onmouseover="gutterOver(544)"

><td class="source">					rof.interrupt();<br></td></tr
><tr
id=sl_svn78_545

 onmouseover="gutterOver(545)"

><td class="source">					rof = null;<br></td></tr
><tr
id=sl_svn78_546

 onmouseover="gutterOver(546)"

><td class="source">					sink.close();<br></td></tr
><tr
id=sl_svn78_547

 onmouseover="gutterOver(547)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_548

 onmouseover="gutterOver(548)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_549

 onmouseover="gutterOver(549)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_550

 onmouseover="gutterOver(550)"

><td class="source">			getProgress().complete();<br></td></tr
><tr
id=sl_svn78_551

 onmouseover="gutterOver(551)"

><td class="source">			mapper.close();<br></td></tr
><tr
id=sl_svn78_552

 onmouseover="gutterOver(552)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_553

 onmouseover="gutterOver(553)"

><td class="source">		else{<br></td></tr
><tr
id=sl_svn78_554

 onmouseover="gutterOver(554)"

><td class="source">			boolean pipeline = job.getBoolean(&quot;mapred.map.pipeline&quot;, false);<br></td></tr
><tr
id=sl_svn78_555

 onmouseover="gutterOver(555)"

><td class="source">			if (numReduceTasks &gt; 0) {<br></td></tr
><tr
id=sl_svn78_556

 onmouseover="gutterOver(556)"

><td class="source">				Class mapCombiner = job.getClass(&quot;mapred.map.combiner.class&quot;, null);<br></td></tr
><tr
id=sl_svn78_557

 onmouseover="gutterOver(557)"

><td class="source">				if (mapCombiner != null) {<br></td></tr
><tr
id=sl_svn78_558

 onmouseover="gutterOver(558)"

><td class="source">					job.setCombinerClass(mapCombiner);<br></td></tr
><tr
id=sl_svn78_559

 onmouseover="gutterOver(559)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_560

 onmouseover="gutterOver(560)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_561

 onmouseover="gutterOver(561)"

><td class="source">				Class keyClass = job.getMapOutputKeyClass();<br></td></tr
><tr
id=sl_svn78_562

 onmouseover="gutterOver(562)"

><td class="source">				Class valClass = job.getMapOutputValueClass();<br></td></tr
><tr
id=sl_svn78_563

 onmouseover="gutterOver(563)"

><td class="source">				Class&lt;? extends CompressionCodec&gt; codecClass = null;<br></td></tr
><tr
id=sl_svn78_564

 onmouseover="gutterOver(564)"

><td class="source">				if (conf.getCompressMapOutput()) {<br></td></tr
><tr
id=sl_svn78_565

 onmouseover="gutterOver(565)"

><td class="source">					codecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);<br></td></tr
><tr
id=sl_svn78_566

 onmouseover="gutterOver(566)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_567

 onmouseover="gutterOver(567)"

><td class="source">				JOutputBuffer buffer = new JOutputBuffer(bufferUmbilical, this, job, <br></td></tr
><tr
id=sl_svn78_568

 onmouseover="gutterOver(568)"

><td class="source">						reporter, getProgress(), pipeline, <br></td></tr
><tr
id=sl_svn78_569

 onmouseover="gutterOver(569)"

><td class="source">						keyClass, valClass, codecClass);<br></td></tr
><tr
id=sl_svn78_570

 onmouseover="gutterOver(570)"

><td class="source">				collector = buffer;<br></td></tr
><tr
id=sl_svn78_571

 onmouseover="gutterOver(571)"

><td class="source">			} else { <br></td></tr
><tr
id=sl_svn78_572

 onmouseover="gutterOver(572)"

><td class="source">				collector = new DirectMapOutputCollector(umbilical, job, reporter);<br></td></tr
><tr
id=sl_svn78_573

 onmouseover="gutterOver(573)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_574

 onmouseover="gutterOver(574)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_575

 onmouseover="gutterOver(575)"

><td class="source">			// reinstantiate the split<br></td></tr
><tr
id=sl_svn78_576

 onmouseover="gutterOver(576)"

><td class="source">			try {<br></td></tr
><tr
id=sl_svn78_577

 onmouseover="gutterOver(577)"

><td class="source">				instantiatedSplit = (InputSplit) <br></td></tr
><tr
id=sl_svn78_578

 onmouseover="gutterOver(578)"

><td class="source">				ReflectionUtils.newInstance(job.getClassByName(splitClass), job);<br></td></tr
><tr
id=sl_svn78_579

 onmouseover="gutterOver(579)"

><td class="source">			} catch (ClassNotFoundException exp) {<br></td></tr
><tr
id=sl_svn78_580

 onmouseover="gutterOver(580)"

><td class="source">				IOException wrap = new IOException(&quot;Split class &quot; + splitClass + <br></td></tr
><tr
id=sl_svn78_581

 onmouseover="gutterOver(581)"

><td class="source">				&quot; not found&quot;);<br></td></tr
><tr
id=sl_svn78_582

 onmouseover="gutterOver(582)"

><td class="source">				wrap.initCause(exp);<br></td></tr
><tr
id=sl_svn78_583

 onmouseover="gutterOver(583)"

><td class="source">				throw wrap;<br></td></tr
><tr
id=sl_svn78_584

 onmouseover="gutterOver(584)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_585

 onmouseover="gutterOver(585)"

><td class="source">			DataInputBuffer splitBuffer = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_586

 onmouseover="gutterOver(586)"

><td class="source">			splitBuffer.reset(split.get(), 0, split.getSize());<br></td></tr
><tr
id=sl_svn78_587

 onmouseover="gutterOver(587)"

><td class="source">			instantiatedSplit.readFields(splitBuffer);<br></td></tr
><tr
id=sl_svn78_588

 onmouseover="gutterOver(588)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_589

 onmouseover="gutterOver(589)"

><td class="source">			// if it is a file split, we can give more details<br></td></tr
><tr
id=sl_svn78_590

 onmouseover="gutterOver(590)"

><td class="source">			if (instantiatedSplit instanceof FileSplit) {<br></td></tr
><tr
id=sl_svn78_591

 onmouseover="gutterOver(591)"

><td class="source">				FileSplit fileSplit = (FileSplit) instantiatedSplit;<br></td></tr
><tr
id=sl_svn78_592

 onmouseover="gutterOver(592)"

><td class="source">				job.set(&quot;map.input.file&quot;, fileSplit.getPath().toString());<br></td></tr
><tr
id=sl_svn78_593

 onmouseover="gutterOver(593)"

><td class="source">				job.setLong(&quot;map.input.start&quot;, fileSplit.getStart());<br></td></tr
><tr
id=sl_svn78_594

 onmouseover="gutterOver(594)"

><td class="source">				job.setLong(&quot;map.input.length&quot;, fileSplit.getLength());<br></td></tr
><tr
id=sl_svn78_595

 onmouseover="gutterOver(595)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_596

 onmouseover="gutterOver(596)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_597

 onmouseover="gutterOver(597)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_598

 onmouseover="gutterOver(598)"

><td class="source">			RecordReader rawIn =                  // open input<br></td></tr
><tr
id=sl_svn78_599

 onmouseover="gutterOver(599)"

><td class="source">				job.getInputFormat().getRecordReader(instantiatedSplit, job, reporter);<br></td></tr
><tr
id=sl_svn78_600

 onmouseover="gutterOver(600)"

><td class="source">			this.recordReader = new TrackedRecordReader(rawIn, getCounters());<br></td></tr
><tr
id=sl_svn78_601

 onmouseover="gutterOver(601)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_602

 onmouseover="gutterOver(602)"

><td class="source">			MapRunnable runner =<br></td></tr
><tr
id=sl_svn78_603

 onmouseover="gutterOver(603)"

><td class="source">				(MapRunnable)ReflectionUtils.newInstance(job.getMapRunnerClass(), job);<br></td></tr
><tr
id=sl_svn78_604

 onmouseover="gutterOver(604)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_605

 onmouseover="gutterOver(605)"

><td class="source">			try {<br></td></tr
><tr
id=sl_svn78_606

 onmouseover="gutterOver(606)"

><td class="source">				long begin = System.currentTimeMillis();<br></td></tr
><tr
id=sl_svn78_607

 onmouseover="gutterOver(607)"

><td class="source">				runner.run(this.recordReader, collector, reporter);      <br></td></tr
><tr
id=sl_svn78_608

 onmouseover="gutterOver(608)"

><td class="source">				getProgress().complete();<br></td></tr
><tr
id=sl_svn78_609

 onmouseover="gutterOver(609)"

><td class="source">				LOG.info(&quot;Map task complete in &quot; + (System.currentTimeMillis() - begin) +&quot; ms&quot;);	<br></td></tr
><tr
id=sl_svn78_610

 onmouseover="gutterOver(610)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_611

 onmouseover="gutterOver(611)"

><td class="source">				if (collector instanceof JOutputBuffer) {<br></td></tr
><tr
id=sl_svn78_612

 onmouseover="gutterOver(612)"

><td class="source">					JOutputBuffer buffer = (JOutputBuffer) collector;<br></td></tr
><tr
id=sl_svn78_613

 onmouseover="gutterOver(613)"

><td class="source">					OutputFile finalOut = buffer.close();<br></td></tr
><tr
id=sl_svn78_614

 onmouseover="gutterOver(614)"

><td class="source">					buffer.free();<br></td></tr
><tr
id=sl_svn78_615

 onmouseover="gutterOver(615)"

><td class="source">					if (finalOut != null) {<br></td></tr
><tr
id=sl_svn78_616

 onmouseover="gutterOver(616)"

><td class="source">						LOG.debug(&quot;Register final output&quot;);<br></td></tr
><tr
id=sl_svn78_617

 onmouseover="gutterOver(617)"

><td class="source">						bufferUmbilical.output(finalOut);<br></td></tr
><tr
id=sl_svn78_618

 onmouseover="gutterOver(618)"

><td class="source">					}<br></td></tr
><tr
id=sl_svn78_619

 onmouseover="gutterOver(619)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_620

 onmouseover="gutterOver(620)"

><td class="source">				else {<br></td></tr
><tr
id=sl_svn78_621

 onmouseover="gutterOver(621)"

><td class="source">					((DirectMapOutputCollector)collector).close();<br></td></tr
><tr
id=sl_svn78_622

 onmouseover="gutterOver(622)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_623

 onmouseover="gutterOver(623)"

><td class="source">			} catch (IOException e) {<br></td></tr
><tr
id=sl_svn78_624

 onmouseover="gutterOver(624)"

><td class="source">				e.printStackTrace();<br></td></tr
><tr
id=sl_svn78_625

 onmouseover="gutterOver(625)"

><td class="source">				throw e;<br></td></tr
><tr
id=sl_svn78_626

 onmouseover="gutterOver(626)"

><td class="source">			} finally {<br></td></tr
><tr
id=sl_svn78_627

 onmouseover="gutterOver(627)"

><td class="source">				//close<br></td></tr
><tr
id=sl_svn78_628

 onmouseover="gutterOver(628)"

><td class="source">				this.recordReader.close();                               // close input<br></td></tr
><tr
id=sl_svn78_629

 onmouseover="gutterOver(629)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_630

 onmouseover="gutterOver(630)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_631

 onmouseover="gutterOver(631)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_632

 onmouseover="gutterOver(632)"

><td class="source">		try {<br></td></tr
><tr
id=sl_svn78_633

 onmouseover="gutterOver(633)"

><td class="source">			Thread.sleep(5000);<br></td></tr
><tr
id=sl_svn78_634

 onmouseover="gutterOver(634)"

><td class="source">		} catch (InterruptedException e) {<br></td></tr
><tr
id=sl_svn78_635

 onmouseover="gutterOver(635)"

><td class="source">			// TODO Auto-generated catch block<br></td></tr
><tr
id=sl_svn78_636

 onmouseover="gutterOver(636)"

><td class="source">			e.printStackTrace();<br></td></tr
><tr
id=sl_svn78_637

 onmouseover="gutterOver(637)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_638

 onmouseover="gutterOver(638)"

><td class="source">		done(umbilical);<br></td></tr
><tr
id=sl_svn78_639

 onmouseover="gutterOver(639)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_640

 onmouseover="gutterOver(640)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_641

 onmouseover="gutterOver(641)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_642

 onmouseover="gutterOver(642)"

><td class="source">	class DirectMapOutputCollector&lt;K, V&gt;<br></td></tr
><tr
id=sl_svn78_643

 onmouseover="gutterOver(643)"

><td class="source">	implements OutputCollector&lt;K, V&gt; {<br></td></tr
><tr
id=sl_svn78_644

 onmouseover="gutterOver(644)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_645

 onmouseover="gutterOver(645)"

><td class="source">		private RecordWriter&lt;K, V&gt; out = null;<br></td></tr
><tr
id=sl_svn78_646

 onmouseover="gutterOver(646)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_647

 onmouseover="gutterOver(647)"

><td class="source">		private Reporter reporter = null;<br></td></tr
><tr
id=sl_svn78_648

 onmouseover="gutterOver(648)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_649

 onmouseover="gutterOver(649)"

><td class="source">		private final Counters.Counter mapOutputRecordCounter;<br></td></tr
><tr
id=sl_svn78_650

 onmouseover="gutterOver(650)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_651

 onmouseover="gutterOver(651)"

><td class="source">		@SuppressWarnings(&quot;unchecked&quot;)<br></td></tr
><tr
id=sl_svn78_652

 onmouseover="gutterOver(652)"

><td class="source">		public DirectMapOutputCollector(TaskUmbilicalProtocol umbilical,<br></td></tr
><tr
id=sl_svn78_653

 onmouseover="gutterOver(653)"

><td class="source">				JobConf job, Reporter reporter) throws IOException {<br></td></tr
><tr
id=sl_svn78_654

 onmouseover="gutterOver(654)"

><td class="source">			this.reporter = reporter;<br></td></tr
><tr
id=sl_svn78_655

 onmouseover="gutterOver(655)"

><td class="source">			String finalName = getOutputName(getPartition());<br></td></tr
><tr
id=sl_svn78_656

 onmouseover="gutterOver(656)"

><td class="source">			FileSystem fs = FileSystem.get(job);<br></td></tr
><tr
id=sl_svn78_657

 onmouseover="gutterOver(657)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_658

 onmouseover="gutterOver(658)"

><td class="source">			out = job.getOutputFormat().getRecordWriter(fs, job, finalName, reporter);<br></td></tr
><tr
id=sl_svn78_659

 onmouseover="gutterOver(659)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_660

 onmouseover="gutterOver(660)"

><td class="source">			Counters counters = getCounters();<br></td></tr
><tr
id=sl_svn78_661

 onmouseover="gutterOver(661)"

><td class="source">			mapOutputRecordCounter = counters.findCounter(MAP_OUTPUT_RECORDS);<br></td></tr
><tr
id=sl_svn78_662

 onmouseover="gutterOver(662)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_663

 onmouseover="gutterOver(663)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_664

 onmouseover="gutterOver(664)"

><td class="source">		public void close() throws IOException {<br></td></tr
><tr
id=sl_svn78_665

 onmouseover="gutterOver(665)"

><td class="source">			if (this.out != null) {<br></td></tr
><tr
id=sl_svn78_666

 onmouseover="gutterOver(666)"

><td class="source">				out.close(this.reporter);<br></td></tr
><tr
id=sl_svn78_667

 onmouseover="gutterOver(667)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_668

 onmouseover="gutterOver(668)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_669

 onmouseover="gutterOver(669)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_670

 onmouseover="gutterOver(670)"

><td class="source">		public void collect(K key, V value) throws IOException {<br></td></tr
><tr
id=sl_svn78_671

 onmouseover="gutterOver(671)"

><td class="source">			reporter.progress();<br></td></tr
><tr
id=sl_svn78_672

 onmouseover="gutterOver(672)"

><td class="source">			out.write(key, value);<br></td></tr
><tr
id=sl_svn78_673

 onmouseover="gutterOver(673)"

><td class="source">			mapOutputRecordCounter.increment(1);<br></td></tr
><tr
id=sl_svn78_674

 onmouseover="gutterOver(674)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_675

 onmouseover="gutterOver(675)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_676

 onmouseover="gutterOver(676)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_677

 onmouseover="gutterOver(677)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_678

 onmouseover="gutterOver(678)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_679

 onmouseover="gutterOver(679)"

><td class="source">	public void close() {<br></td></tr
><tr
id=sl_svn78_680

 onmouseover="gutterOver(680)"

><td class="source">		// TODO Auto-generated method stub<br></td></tr
><tr
id=sl_svn78_681

 onmouseover="gutterOver(681)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_682

 onmouseover="gutterOver(682)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_683

 onmouseover="gutterOver(683)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_684

 onmouseover="gutterOver(684)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_685

 onmouseover="gutterOver(685)"

><td class="source">	public void flush() throws IOException {<br></td></tr
><tr
id=sl_svn78_686

 onmouseover="gutterOver(686)"

><td class="source">		// TODO Auto-generated method stub<br></td></tr
><tr
id=sl_svn78_687

 onmouseover="gutterOver(687)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_688

 onmouseover="gutterOver(688)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_689

 onmouseover="gutterOver(689)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_690

 onmouseover="gutterOver(690)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_691

 onmouseover="gutterOver(691)"

><td class="source">	public void free() {<br></td></tr
><tr
id=sl_svn78_692

 onmouseover="gutterOver(692)"

><td class="source">		// TODO Auto-generated method stub<br></td></tr
><tr
id=sl_svn78_693

 onmouseover="gutterOver(693)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_694

 onmouseover="gutterOver(694)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_695

 onmouseover="gutterOver(695)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_696

 onmouseover="gutterOver(696)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_697

 onmouseover="gutterOver(697)"

><td class="source">	public boolean read(DataInputStream istream, Header header)<br></td></tr
><tr
id=sl_svn78_698

 onmouseover="gutterOver(698)"

><td class="source">			throws IOException {<br></td></tr
><tr
id=sl_svn78_699

 onmouseover="gutterOver(699)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_700

 onmouseover="gutterOver(700)"

><td class="source">		CompressionCodec codec = null;<br></td></tr
><tr
id=sl_svn78_701

 onmouseover="gutterOver(701)"

><td class="source">		Class&lt;? extends CompressionCodec&gt; codecClass = null;<br></td></tr
><tr
id=sl_svn78_702

 onmouseover="gutterOver(702)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_703

 onmouseover="gutterOver(703)"

><td class="source">		if (conf.getCompressMapOutput()) {<br></td></tr
><tr
id=sl_svn78_704

 onmouseover="gutterOver(704)"

><td class="source">			codecClass = conf.getMapOutputCompressorClass(DefaultCodec.class);<br></td></tr
><tr
id=sl_svn78_705

 onmouseover="gutterOver(705)"

><td class="source">			codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);<br></td></tr
><tr
id=sl_svn78_706

 onmouseover="gutterOver(706)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_707

 onmouseover="gutterOver(707)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_708

 onmouseover="gutterOver(708)"

><td class="source">		boolean snapshotGen = false;<br></td></tr
><tr
id=sl_svn78_709

 onmouseover="gutterOver(709)"

><td class="source">		boolean stop = false;<br></td></tr
><tr
id=sl_svn78_710

 onmouseover="gutterOver(710)"

><td class="source">		BufferedWriter writer = null;<br></td></tr
><tr
id=sl_svn78_711

 onmouseover="gutterOver(711)"

><td class="source">		int count = 0;<br></td></tr
><tr
id=sl_svn78_712

 onmouseover="gutterOver(712)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_713

 onmouseover="gutterOver(713)"

><td class="source">		if(iteration &gt;= this.stopIteration){<br></td></tr
><tr
id=sl_svn78_714

 onmouseover="gutterOver(714)"

><td class="source">			stop = true;<br></td></tr
><tr
id=sl_svn78_715

 onmouseover="gutterOver(715)"

><td class="source">			snapshotGen = true;		<br></td></tr
><tr
id=sl_svn78_716

 onmouseover="gutterOver(716)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_717

 onmouseover="gutterOver(717)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_718

 onmouseover="gutterOver(718)"

><td class="source">		if((iteration % snapshotInterval == 0) || stop){<br></td></tr
><tr
id=sl_svn78_719

 onmouseover="gutterOver(719)"

><td class="source">			//perform snapshot <br></td></tr
><tr
id=sl_svn78_720

 onmouseover="gutterOver(720)"

><td class="source">			snapshotGen = true;<br></td></tr
><tr
id=sl_svn78_721

 onmouseover="gutterOver(721)"

><td class="source">			String outputDir = conf.get(&quot;mapred.output.dir&quot;);<br></td></tr
><tr
id=sl_svn78_722

 onmouseover="gutterOver(722)"

><td class="source">			int taskid = getTaskID().getTaskID().getId();<br></td></tr
><tr
id=sl_svn78_723

 onmouseover="gutterOver(723)"

><td class="source">			String snapshot = outputDir + &quot;/snapshot&quot; + snapshotIndex + &quot;/part&quot; + taskid;	<br></td></tr
><tr
id=sl_svn78_724

 onmouseover="gutterOver(724)"

><td class="source">			FileSystem hdfs = FileSystem.get(conf);<br></td></tr
><tr
id=sl_svn78_725

 onmouseover="gutterOver(725)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_726

 onmouseover="gutterOver(726)"

><td class="source">			FSDataOutputStream ostream = hdfs.create(new Path(snapshot), true);<br></td></tr
><tr
id=sl_svn78_727

 onmouseover="gutterOver(727)"

><td class="source">			writer = new BufferedWriter(new OutputStreamWriter(ostream));<br></td></tr
><tr
id=sl_svn78_728

 onmouseover="gutterOver(728)"

><td class="source">			snapshotIndex++;<br></td></tr
><tr
id=sl_svn78_729

 onmouseover="gutterOver(729)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_730

 onmouseover="gutterOver(730)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_731

 onmouseover="gutterOver(731)"

><td class="source">		IFile.Reader reader = new IFile.Reader(conf, istream, header.compressed(), codec, null);<br></td></tr
><tr
id=sl_svn78_732

 onmouseover="gutterOver(732)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_733

 onmouseover="gutterOver(733)"

><td class="source">		DataInputBuffer key = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_734

 onmouseover="gutterOver(734)"

><td class="source">		DataInputBuffer value = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_735

 onmouseover="gutterOver(735)"

><td class="source">		Object keyObject = null;<br></td></tr
><tr
id=sl_svn78_736

 onmouseover="gutterOver(736)"

><td class="source">		Object valObject = null;<br></td></tr
><tr
id=sl_svn78_737

 onmouseover="gutterOver(737)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_738

 onmouseover="gutterOver(738)"

><td class="source">		long mapbegin = System.currentTimeMillis();<br></td></tr
><tr
id=sl_svn78_739

 onmouseover="gutterOver(739)"

><td class="source">		long readbegin = System.currentTimeMillis();<br></td></tr
><tr
id=sl_svn78_740

 onmouseover="gutterOver(740)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_741

 onmouseover="gutterOver(741)"

><td class="source">		boolean mapsync = conf.getBoolean(&quot;mapred.iterative.mapsync&quot;, false);<br></td></tr
><tr
id=sl_svn78_742

 onmouseover="gutterOver(742)"

><td class="source">		int partitions = job.getInt(&quot;mapred.iterative.partitions&quot;, 0);<br></td></tr
><tr
id=sl_svn78_743

 onmouseover="gutterOver(743)"

><td class="source">		while (reader.next(key, value)) {			<br></td></tr
><tr
id=sl_svn78_744

 onmouseover="gutterOver(744)"

><td class="source">			inputKeyDeserializer.open(key);<br></td></tr
><tr
id=sl_svn78_745

 onmouseover="gutterOver(745)"

><td class="source">			inputValDeserializer.open(value);<br></td></tr
><tr
id=sl_svn78_746

 onmouseover="gutterOver(746)"

><td class="source">			keyObject = inputKeyDeserializer.deserialize(keyObject);<br></td></tr
><tr
id=sl_svn78_747

 onmouseover="gutterOver(747)"

><td class="source">			valObject = inputValDeserializer.deserialize(valObject);<br></td></tr
><tr
id=sl_svn78_748

 onmouseover="gutterOver(748)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_749

 onmouseover="gutterOver(749)"

><td class="source">			if(!stop){<br></td></tr
><tr
id=sl_svn78_750

 onmouseover="gutterOver(750)"

><td class="source">				//cache the input state data, use iterateOver to parse them together<br></td></tr
><tr
id=sl_svn78_751

 onmouseover="gutterOver(751)"

><td class="source">				if((joinType == StaticData.MatchType.ONE2ONE) &amp;&amp; mapsync){	<br></td></tr
><tr
id=sl_svn78_752

 onmouseover="gutterOver(752)"

><td class="source">					<br></td></tr
><tr
id=sl_svn78_753

 onmouseover="gutterOver(753)"

><td class="source">					if(((IntWritable)keyObject).get()%partitions == this.getTaskID().id){<br></td></tr
><tr
id=sl_svn78_754

 onmouseover="gutterOver(754)"

><td class="source">						cachewriter.append(keyObject, valObject);<br></td></tr
><tr
id=sl_svn78_755

 onmouseover="gutterOver(755)"

><td class="source">						continue;<br></td></tr
><tr
id=sl_svn78_756

 onmouseover="gutterOver(756)"

><td class="source">					}<br></td></tr
><tr
id=sl_svn78_757

 onmouseover="gutterOver(757)"

><td class="source">					else{<br></td></tr
><tr
id=sl_svn78_758

 onmouseover="gutterOver(758)"

><td class="source">						//the input is not mine, ignore it<br></td></tr
><tr
id=sl_svn78_759

 onmouseover="gutterOver(759)"

><td class="source">						LOG.info(&quot;the input key &quot; + ((IntWritable)keyObject).get() + &quot;is not corresponding to the task&quot;);<br></td></tr
><tr
id=sl_svn78_760

 onmouseover="gutterOver(760)"

><td class="source">						while (reader.next(key, value)){}<br></td></tr
><tr
id=sl_svn78_761

 onmouseover="gutterOver(761)"

><td class="source">						break;<br></td></tr
><tr
id=sl_svn78_762

 onmouseover="gutterOver(762)"

><td class="source">					}<br></td></tr
><tr
id=sl_svn78_763

 onmouseover="gutterOver(763)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_764

 onmouseover="gutterOver(764)"

><td class="source">							<br></td></tr
><tr
id=sl_svn78_765

 onmouseover="gutterOver(765)"

><td class="source">				if(staticData != null){<br></td></tr
><tr
id=sl_svn78_766

 onmouseover="gutterOver(766)"

><td class="source">					if(joinType == StaticData.MatchType.ONE2ONE){<br></td></tr
><tr
id=sl_svn78_767

 onmouseover="gutterOver(767)"

><td class="source">						staticData.next();<br></td></tr
><tr
id=sl_svn78_768

 onmouseover="gutterOver(768)"

><td class="source">						mapper.map(keyObject, valObject, staticData.getKey(), staticData.getValue(), this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_769

 onmouseover="gutterOver(769)"

><td class="source">					}else if(joinType == StaticData.MatchType.ONE2ALL){<br></td></tr
><tr
id=sl_svn78_770

 onmouseover="gutterOver(770)"

><td class="source">						mapper.map(keyObject, valObject, null, null, this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_771

 onmouseover="gutterOver(771)"

><td class="source">					}<br></td></tr
><tr
id=sl_svn78_772

 onmouseover="gutterOver(772)"

><td class="source">				}else{<br></td></tr
><tr
id=sl_svn78_773

 onmouseover="gutterOver(773)"

><td class="source">					mapper.map(keyObject, valObject, null, null, this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_774

 onmouseover="gutterOver(774)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_775

 onmouseover="gutterOver(775)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_776

 onmouseover="gutterOver(776)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_777

 onmouseover="gutterOver(777)"

><td class="source">			if(snapshotGen){<br></td></tr
><tr
id=sl_svn78_778

 onmouseover="gutterOver(778)"

><td class="source">				writer.write(keyObject + &quot;\t&quot; + valObject + &quot;\n&quot;);<br></td></tr
><tr
id=sl_svn78_779

 onmouseover="gutterOver(779)"

><td class="source">				count++;<br></td></tr
><tr
id=sl_svn78_780

 onmouseover="gutterOver(780)"

><td class="source">				if(count % 10000 == 0){<br></td></tr
><tr
id=sl_svn78_781

 onmouseover="gutterOver(781)"

><td class="source">					writer.flush();<br></td></tr
><tr
id=sl_svn78_782

 onmouseover="gutterOver(782)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_783

 onmouseover="gutterOver(783)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_784

 onmouseover="gutterOver(784)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_785

 onmouseover="gutterOver(785)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_786

 onmouseover="gutterOver(786)"

><td class="source">		readtime += System.currentTimeMillis() - readbegin;<br></td></tr
><tr
id=sl_svn78_787

 onmouseover="gutterOver(787)"

><td class="source">		LOG.info(&quot;read use &quot; + readtime + &quot; ms.&quot;);<br></td></tr
><tr
id=sl_svn78_788

 onmouseover="gutterOver(788)"

><td class="source">		maptime += System.currentTimeMillis() - mapbegin;<br></td></tr
><tr
id=sl_svn78_789

 onmouseover="gutterOver(789)"

><td class="source">		LOG.info(&quot;iterative map use &quot; + maptime + &quot; ms.&quot;);<br></td></tr
><tr
id=sl_svn78_790

 onmouseover="gutterOver(790)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_791

 onmouseover="gutterOver(791)"

><td class="source">		if(snapshotGen){<br></td></tr
><tr
id=sl_svn78_792

 onmouseover="gutterOver(792)"

><td class="source">			writer.close();<br></td></tr
><tr
id=sl_svn78_793

 onmouseover="gutterOver(793)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_794

 onmouseover="gutterOver(794)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_795

 onmouseover="gutterOver(795)"

><td class="source">		return true;<br></td></tr
><tr
id=sl_svn78_796

 onmouseover="gutterOver(796)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_797

 onmouseover="gutterOver(797)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_798

 onmouseover="gutterOver(798)"

><td class="source">	public void iterateOver() throws IOException {<br></td></tr
><tr
id=sl_svn78_799

 onmouseover="gutterOver(799)"

><td class="source">		LOG.info(&quot;iterate over data&quot;);<br></td></tr
><tr
id=sl_svn78_800

 onmouseover="gutterOver(800)"

><td class="source">		<br></td></tr
><tr
id=sl_svn78_801

 onmouseover="gutterOver(801)"

><td class="source">		if(joinType == StaticData.MatchType.ONE2ONE){<br></td></tr
><tr
id=sl_svn78_802

 onmouseover="gutterOver(802)"

><td class="source">			cachewriter.close();<br></td></tr
><tr
id=sl_svn78_803

 onmouseover="gutterOver(803)"

><td class="source">			Path localpath = new Path(&quot;/tmp/cache&quot;);<br></td></tr
><tr
id=sl_svn78_804

 onmouseover="gutterOver(804)"

><td class="source">			IFile.Reader reader = new IFile.Reader(conf, localfs, localpath, null, null);<br></td></tr
><tr
id=sl_svn78_805

 onmouseover="gutterOver(805)"

><td class="source">			DataInputBuffer key = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_806

 onmouseover="gutterOver(806)"

><td class="source">			DataInputBuffer value = new DataInputBuffer();<br></td></tr
><tr
id=sl_svn78_807

 onmouseover="gutterOver(807)"

><td class="source">			Object keyObject = null;<br></td></tr
><tr
id=sl_svn78_808

 onmouseover="gutterOver(808)"

><td class="source">			Object valObject = null;<br></td></tr
><tr
id=sl_svn78_809

 onmouseover="gutterOver(809)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_810

 onmouseover="gutterOver(810)"

><td class="source">			if(staticData != null){<br></td></tr
><tr
id=sl_svn78_811

 onmouseover="gutterOver(811)"

><td class="source">				while (reader.next(key, value)) {<br></td></tr
><tr
id=sl_svn78_812

 onmouseover="gutterOver(812)"

><td class="source">					inputKeyDeserializer.open(key);<br></td></tr
><tr
id=sl_svn78_813

 onmouseover="gutterOver(813)"

><td class="source">					inputValDeserializer.open(value);<br></td></tr
><tr
id=sl_svn78_814

 onmouseover="gutterOver(814)"

><td class="source">					keyObject = inputKeyDeserializer.deserialize(keyObject);<br></td></tr
><tr
id=sl_svn78_815

 onmouseover="gutterOver(815)"

><td class="source">					valObject = inputValDeserializer.deserialize(valObject);<br></td></tr
><tr
id=sl_svn78_816

 onmouseover="gutterOver(816)"

><td class="source">					staticData.next();<br></td></tr
><tr
id=sl_svn78_817

 onmouseover="gutterOver(817)"

><td class="source">					mapper.map(keyObject, valObject, staticData.getKey(), staticData.getValue(), this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_818

 onmouseover="gutterOver(818)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_819

 onmouseover="gutterOver(819)"

><td class="source">			}else{<br></td></tr
><tr
id=sl_svn78_820

 onmouseover="gutterOver(820)"

><td class="source">				while (reader.next(key, value)) {<br></td></tr
><tr
id=sl_svn78_821

 onmouseover="gutterOver(821)"

><td class="source">					inputKeyDeserializer.open(key);<br></td></tr
><tr
id=sl_svn78_822

 onmouseover="gutterOver(822)"

><td class="source">					inputValDeserializer.open(value);<br></td></tr
><tr
id=sl_svn78_823

 onmouseover="gutterOver(823)"

><td class="source">					keyObject = inputKeyDeserializer.deserialize(keyObject);<br></td></tr
><tr
id=sl_svn78_824

 onmouseover="gutterOver(824)"

><td class="source">					valObject = inputValDeserializer.deserialize(valObject);<br></td></tr
><tr
id=sl_svn78_825

 onmouseover="gutterOver(825)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_826

 onmouseover="gutterOver(826)"

><td class="source">					mapper.map(keyObject, valObject, null, null, this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_827

 onmouseover="gutterOver(827)"

><td class="source">				}<br></td></tr
><tr
id=sl_svn78_828

 onmouseover="gutterOver(828)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_829

 onmouseover="gutterOver(829)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_830

 onmouseover="gutterOver(830)"

><td class="source">			<br></td></tr
><tr
id=sl_svn78_831

 onmouseover="gutterOver(831)"

><td class="source">			reader.close();<br></td></tr
><tr
id=sl_svn78_832

 onmouseover="gutterOver(832)"

><td class="source">			if(localfs.exists(localpath)){<br></td></tr
><tr
id=sl_svn78_833

 onmouseover="gutterOver(833)"

><td class="source">				localfs.delete(localpath, true);<br></td></tr
><tr
id=sl_svn78_834

 onmouseover="gutterOver(834)"

><td class="source">			};<br></td></tr
><tr
id=sl_svn78_835

 onmouseover="gutterOver(835)"

><td class="source">			cachewriter = new IFile.Writer(conf, localfs, localpath,<br></td></tr
><tr
id=sl_svn78_836

 onmouseover="gutterOver(836)"

><td class="source">					inputKeyClass, inputValClass, null, null);<br></td></tr
><tr
id=sl_svn78_837

 onmouseover="gutterOver(837)"

><td class="source">		}else if(joinType == StaticData.MatchType.ONE2ALL){<br></td></tr
><tr
id=sl_svn78_838

 onmouseover="gutterOver(838)"

><td class="source">			while(staticData.next()){<br></td></tr
><tr
id=sl_svn78_839

 onmouseover="gutterOver(839)"

><td class="source">				mapper.map(null, null, staticData.getKey(), staticData.getValue(), this.buffer, reporter);<br></td></tr
><tr
id=sl_svn78_840

 onmouseover="gutterOver(840)"

><td class="source">			}<br></td></tr
><tr
id=sl_svn78_841

 onmouseover="gutterOver(841)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_842

 onmouseover="gutterOver(842)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_843

 onmouseover="gutterOver(843)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_844

 onmouseover="gutterOver(844)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_845

 onmouseover="gutterOver(845)"

><td class="source">	public void stream() throws IOException {<br></td></tr
><tr
id=sl_svn78_846

 onmouseover="gutterOver(846)"

><td class="source">		LOG.info(iteration + &quot; and stop at &quot; + stopIteration);<br></td></tr
><tr
id=sl_svn78_847

 onmouseover="gutterOver(847)"

><td class="source">		if(iteration &gt;= this.stopIteration){<br></td></tr
><tr
id=sl_svn78_848

 onmouseover="gutterOver(848)"

><td class="source">			synchronized(this){<br></td></tr
><tr
id=sl_svn78_849

 onmouseover="gutterOver(849)"

><td class="source">				this.notifyAll();<br></td></tr
><tr
id=sl_svn78_850

 onmouseover="gutterOver(850)"

><td class="source">			}		<br></td></tr
><tr
id=sl_svn78_851

 onmouseover="gutterOver(851)"

><td class="source">		}else{<br></td></tr
><tr
id=sl_svn78_852

 onmouseover="gutterOver(852)"

><td class="source">			this.mapper.iterate();<br></td></tr
><tr
id=sl_svn78_853

 onmouseover="gutterOver(853)"

><td class="source">			this.buffer.stream(iteration++, true);<br></td></tr
><tr
id=sl_svn78_854

 onmouseover="gutterOver(854)"

><td class="source">		}<br></td></tr
><tr
id=sl_svn78_855

 onmouseover="gutterOver(855)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_856

 onmouseover="gutterOver(856)"

><td class="source">	<br></td></tr
><tr
id=sl_svn78_857

 onmouseover="gutterOver(857)"

><td class="source">	@Override<br></td></tr
><tr
id=sl_svn78_858

 onmouseover="gutterOver(858)"

><td class="source">	public ValuesIterator valuesIterator() throws IOException {<br></td></tr
><tr
id=sl_svn78_859

 onmouseover="gutterOver(859)"

><td class="source">		// TODO Auto-generated method stub<br></td></tr
><tr
id=sl_svn78_860

 onmouseover="gutterOver(860)"

><td class="source">		return null;<br></td></tr
><tr
id=sl_svn78_861

 onmouseover="gutterOver(861)"

><td class="source">	}<br></td></tr
><tr
id=sl_svn78_862

 onmouseover="gutterOver(862)"

><td class="source"><br></td></tr
><tr
id=sl_svn78_863

 onmouseover="gutterOver(863)"

><td class="source">}<br></td></tr
></table></pre>
<pre><table width="100%"><tr class="cursor_stop cursor_hidden"><td></td></tr></table></pre>
</td>
</tr></table>

 
<script type="text/javascript">
 var lineNumUnderMouse = -1;
 
 function gutterOver(num) {
 gutterOut();
 var newTR = document.getElementById('gr_svn78_' + num);
 if (newTR) {
 newTR.className = 'undermouse';
 }
 lineNumUnderMouse = num;
 }
 function gutterOut() {
 if (lineNumUnderMouse != -1) {
 var oldTR = document.getElementById(
 'gr_svn78_' + lineNumUnderMouse);
 if (oldTR) {
 oldTR.className = '';
 }
 lineNumUnderMouse = -1;
 }
 }
 var numsGenState = {table_base_id: 'nums_table_'};
 var srcGenState = {table_base_id: 'src_table_'};
 var alignerRunning = false;
 var startOver = false;
 function setLineNumberHeights() {
 if (alignerRunning) {
 startOver = true;
 return;
 }
 numsGenState.chunk_id = 0;
 numsGenState.table = document.getElementById('nums_table_0');
 numsGenState.row_num = 0;
 if (!numsGenState.table) {
 return; // Silently exit if no file is present.
 }
 srcGenState.chunk_id = 0;
 srcGenState.table = document.getElementById('src_table_0');
 srcGenState.row_num = 0;
 alignerRunning = true;
 continueToSetLineNumberHeights();
 }
 function rowGenerator(genState) {
 if (genState.row_num < genState.table.rows.length) {
 var currentRow = genState.table.rows[genState.row_num];
 genState.row_num++;
 return currentRow;
 }
 var newTable = document.getElementById(
 genState.table_base_id + (genState.chunk_id + 1));
 if (newTable) {
 genState.chunk_id++;
 genState.row_num = 0;
 genState.table = newTable;
 return genState.table.rows[0];
 }
 return null;
 }
 var MAX_ROWS_PER_PASS = 1000;
 function continueToSetLineNumberHeights() {
 var rowsInThisPass = 0;
 var numRow = 1;
 var srcRow = 1;
 while (numRow && srcRow && rowsInThisPass < MAX_ROWS_PER_PASS) {
 numRow = rowGenerator(numsGenState);
 srcRow = rowGenerator(srcGenState);
 rowsInThisPass++;
 if (numRow && srcRow) {
 if (numRow.offsetHeight != srcRow.offsetHeight) {
 numRow.firstChild.style.height = srcRow.offsetHeight + 'px';
 }
 }
 }
 if (rowsInThisPass >= MAX_ROWS_PER_PASS) {
 setTimeout(continueToSetLineNumberHeights, 10);
 } else {
 alignerRunning = false;
 if (startOver) {
 startOver = false;
 setTimeout(setLineNumberHeights, 500);
 }
 }
 }
 function initLineNumberHeights() {
 // Do 2 complete passes, because there can be races
 // between this code and prettify.
 startOver = true;
 setTimeout(setLineNumberHeights, 250);
 window.onresize = setLineNumberHeights;
 }
 initLineNumberHeights();
</script>

 
 
 <div id="log">
 <div style="text-align:right">
 <a class="ifCollapse" href="#" onclick="_toggleMeta('', 'p', 'i-mapreduce', this)">Show details</a>
 <a class="ifExpand" href="#" onclick="_toggleMeta('', 'p', 'i-mapreduce', this)">Hide details</a>
 </div>
 <div class="ifExpand">
 
 
 <div class="pmeta_bubble_bg" style="border:1px solid white">
 <div class="round4"></div>
 <div class="round2"></div>
 <div class="round1"></div>
 <div class="box-inner">
 <div id="changelog">
 <p>Change log</p>
 <div>
 <a href="/p/i-mapreduce/source/detail?spec=svn78&amp;r=77">r77</a>
 by threewells14@gmail.com
 on Jul 25, 2011
 &nbsp; <a href="/p/i-mapreduce/source/diff?spec=svn78&r=77&amp;format=side&amp;path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old_path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old=67">Diff</a>
 </div>
 <pre>[No log message]</pre>
 </div>
 
 
 
 
 
 
 <script type="text/javascript">
 var detail_url = '/p/i-mapreduce/source/detail?r=77&spec=svn78';
 var publish_url = '/p/i-mapreduce/source/detail?r=77&spec=svn78#publish';
 // describe the paths of this revision in javascript.
 var changed_paths = [];
 var changed_urls = [];
 
 changed_paths.push('/trunk/src/mapred/org/apache/hadoop/mapred/JobTracker.java');
 changed_urls.push('/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/JobTracker.java?r\x3d77\x26spec\x3dsvn78');
 
 
 changed_paths.push('/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java');
 changed_urls.push('/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java?r\x3d77\x26spec\x3dsvn78');
 
 var selected_path = '/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java';
 
 
 changed_paths.push('/trunk/src/mapred/org/apache/hadoop/mapred/ReduceTask.java');
 changed_urls.push('/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/ReduceTask.java?r\x3d77\x26spec\x3dsvn78');
 
 
 function getCurrentPageIndex() {
 for (var i = 0; i < changed_paths.length; i++) {
 if (selected_path == changed_paths[i]) {
 return i;
 }
 }
 }
 function getNextPage() {
 var i = getCurrentPageIndex();
 if (i < changed_paths.length - 1) {
 return changed_urls[i + 1];
 }
 return null;
 }
 function getPreviousPage() {
 var i = getCurrentPageIndex();
 if (i > 0) {
 return changed_urls[i - 1];
 }
 return null;
 }
 function gotoNextPage() {
 var page = getNextPage();
 if (!page) {
 page = detail_url;
 }
 window.location = page;
 }
 function gotoPreviousPage() {
 var page = getPreviousPage();
 if (!page) {
 page = detail_url;
 }
 window.location = page;
 }
 function gotoDetailPage() {
 window.location = detail_url;
 }
 function gotoPublishPage() {
 window.location = publish_url;
 }
</script>

 
 <style type="text/css">
 #review_nav {
 border-top: 3px solid white;
 padding-top: 6px;
 margin-top: 1em;
 }
 #review_nav td {
 vertical-align: middle;
 }
 #review_nav select {
 margin: .5em 0;
 }
 </style>
 <div id="review_nav">
 <table><tr><td>Go to:&nbsp;</td><td>
 <select name="files_in_rev" onchange="window.location=this.value">
 
 <option value="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/JobTracker.java?r=77&amp;spec=svn78"
 
 >...he/hadoop/mapred/JobTracker.java</option>
 
 <option value="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java?r=77&amp;spec=svn78"
 selected="selected"
 >...pache/hadoop/mapred/MapTask.java</option>
 
 <option value="/p/i-mapreduce/source/browse/trunk/src/mapred/org/apache/hadoop/mapred/ReduceTask.java?r=77&amp;spec=svn78"
 
 >...he/hadoop/mapred/ReduceTask.java</option>
 
 </select>
 </td></tr></table>
 
 
 <div id="review_instr" class="closed">
 <a class="ifOpened" href="/p/i-mapreduce/source/detail?r=77&spec=svn78#publish">Publish your comments</a>
 <div class="ifClosed">Double click a line to add a comment</div>
 </div>
 
 </div>
 
 
 </div>
 <div class="round1"></div>
 <div class="round2"></div>
 <div class="round4"></div>
 </div>
 <div class="pmeta_bubble_bg" style="border:1px solid white">
 <div class="round4"></div>
 <div class="round2"></div>
 <div class="round1"></div>
 <div class="box-inner">
 <div id="older_bubble">
 <p>Older revisions</p>
 
 
 <div class="closed" style="margin-bottom:3px;" >
 <img class="ifClosed" onclick="_toggleHidden(this)" src="http://www.gstatic.com/codesite/ph/images/plus.gif" >
 <img class="ifOpened" onclick="_toggleHidden(this)" src="http://www.gstatic.com/codesite/ph/images/minus.gif" >
 <a href="/p/i-mapreduce/source/detail?spec=svn78&r=67">r67</a>
 by threewells14@gmail.com
 on Jul 23, 2011
 &nbsp; <a href="/p/i-mapreduce/source/diff?spec=svn78&r=67&amp;format=side&amp;path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old_path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old=65">Diff</a>
 <br>
 <pre class="ifOpened">[No log message]</pre>
 </div>
 
 <div class="closed" style="margin-bottom:3px;" >
 <img class="ifClosed" onclick="_toggleHidden(this)" src="http://www.gstatic.com/codesite/ph/images/plus.gif" >
 <img class="ifOpened" onclick="_toggleHidden(this)" src="http://www.gstatic.com/codesite/ph/images/minus.gif" >
 <a href="/p/i-mapreduce/source/detail?spec=svn78&r=65">r65</a>
 by threewells14@gmail.com
 on Jul 22, 2011
 &nbsp; <a href="/p/i-mapreduce/source/diff?spec=svn78&r=65&amp;format=side&amp;path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old_path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old=10">Diff</a>
 <br>
 <pre class="ifOpened">[No log message]</pre>
 </div>
 
 <div class="closed" style="margin-bottom:3px;" >
 <img class="ifClosed" onclick="_toggleHidden(this)" src="http://www.gstatic.com/codesite/ph/images/plus.gif" >
 <img class="ifOpened" onclick="_toggleHidden(this)" src="http://www.gstatic.com/codesite/ph/images/minus.gif" >
 <a href="/p/i-mapreduce/source/detail?spec=svn78&r=10">r10</a>
 by threewells14@gmail.com
 on Jul 15, 2011
 &nbsp; <a href="/p/i-mapreduce/source/diff?spec=svn78&r=10&amp;format=side&amp;path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old_path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&amp;old=9">Diff</a>
 <br>
 <pre class="ifOpened">[No log message]</pre>
 </div>
 
 
 <a href="/p/i-mapreduce/source/list?path=/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java&start=77">All revisions of this file</a>
 </div>
 </div>
 <div class="round1"></div>
 <div class="round2"></div>
 <div class="round4"></div>
 </div>
 
 <div class="pmeta_bubble_bg" style="border:1px solid white">
 <div class="round4"></div>
 <div class="round2"></div>
 <div class="round1"></div>
 <div class="box-inner">
 <div id="fileinfo_bubble">
 <p>File info</p>
 
 <div>Size: 27960 bytes,
 863 lines</div>
 
 <div><a href="//i-mapreduce.googlecode.com/svn/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java">View raw file</a></div>
 </div>
 
 </div>
 <div class="round1"></div>
 <div class="round2"></div>
 <div class="round4"></div>
 </div>
 </div>
 </div>


</div>

</div>
</div>

<script src="http://www.gstatic.com/codesite/ph/3282150342600863652/js/prettify/prettify.js"></script>
<script type="text/javascript">prettyPrint();</script>


<script src="http://www.gstatic.com/codesite/ph/3282150342600863652/js/source_file_scripts.js"></script>

 <script type="text/javascript" src="https://kibbles.googlecode.com/files/kibbles-1.3.3.comp.js"></script>
 <script type="text/javascript">
 var lastStop = null;
 var initialized = false;
 
 function updateCursor(next, prev) {
 if (prev && prev.element) {
 prev.element.className = 'cursor_stop cursor_hidden';
 }
 if (next && next.element) {
 next.element.className = 'cursor_stop cursor';
 lastStop = next.index;
 }
 }
 
 function pubRevealed(data) {
 updateCursorForCell(data.cellId, 'cursor_stop cursor_hidden');
 if (initialized) {
 reloadCursors();
 }
 }
 
 function draftRevealed(data) {
 updateCursorForCell(data.cellId, 'cursor_stop cursor_hidden');
 if (initialized) {
 reloadCursors();
 }
 }
 
 function draftDestroyed(data) {
 updateCursorForCell(data.cellId, 'nocursor');
 if (initialized) {
 reloadCursors();
 }
 }
 function reloadCursors() {
 kibbles.skipper.reset();
 loadCursors();
 if (lastStop != null) {
 kibbles.skipper.setCurrentStop(lastStop);
 }
 }
 // possibly the simplest way to insert any newly added comments
 // is to update the class of the corresponding cursor row,
 // then refresh the entire list of rows.
 function updateCursorForCell(cellId, className) {
 var cell = document.getElementById(cellId);
 // we have to go two rows back to find the cursor location
 var row = getPreviousElement(cell.parentNode);
 row.className = className;
 }
 // returns the previous element, ignores text nodes.
 function getPreviousElement(e) {
 var element = e.previousSibling;
 if (element.nodeType == 3) {
 element = element.previousSibling;
 }
 if (element && element.tagName) {
 return element;
 }
 }
 function loadCursors() {
 // register our elements with skipper
 var elements = CR_getElements('*', 'cursor_stop');
 var len = elements.length;
 for (var i = 0; i < len; i++) {
 var element = elements[i]; 
 element.className = 'cursor_stop cursor_hidden';
 kibbles.skipper.append(element);
 }
 }
 function toggleComments() {
 CR_toggleCommentDisplay();
 reloadCursors();
 }
 function keysOnLoadHandler() {
 // setup skipper
 kibbles.skipper.addStopListener(
 kibbles.skipper.LISTENER_TYPE.PRE, updateCursor);
 // Set the 'offset' option to return the middle of the client area
 // an option can be a static value, or a callback
 kibbles.skipper.setOption('padding_top', 50);
 // Set the 'offset' option to return the middle of the client area
 // an option can be a static value, or a callback
 kibbles.skipper.setOption('padding_bottom', 100);
 // Register our keys
 kibbles.skipper.addFwdKey("n");
 kibbles.skipper.addRevKey("p");
 kibbles.keys.addKeyPressListener(
 'u', function() { window.location = detail_url; });
 kibbles.keys.addKeyPressListener(
 'r', function() { window.location = detail_url + '#publish'; });
 
 kibbles.keys.addKeyPressListener('j', gotoNextPage);
 kibbles.keys.addKeyPressListener('k', gotoPreviousPage);
 
 
 kibbles.keys.addKeyPressListener('h', toggleComments);
 
 }
 </script>
<script src="http://www.gstatic.com/codesite/ph/3282150342600863652/js/code_review_scripts.js"></script>
<script type="text/javascript">
 function showPublishInstructions() {
 var element = document.getElementById('review_instr');
 if (element) {
 element.className = 'opened';
 }
 }
 var codereviews;
 function revsOnLoadHandler() {
 // register our source container with the commenting code
 var paths = {'svn78': '/trunk/src/mapred/org/apache/hadoop/mapred/MapTask.java'}
 codereviews = CR_controller.setup(
 {"assetHostPath":"http://www.gstatic.com/codesite/ph","projectName":"i-mapreduce","projectHomeUrl":"/p/i-mapreduce","urlPrefix":"p","domainName":null,"relativeBaseUrl":"","profileUrl":["/u/@WRVVSlFSDxJCVgV9/"],"token":"0897d6b737948e7f980e806dd0cf4ead","assetVersionPath":"http://www.gstatic.com/codesite/ph/3282150342600863652","absoluteBaseUrl":"http://code.google.com","loggedInUserEmail":"threewells14@gmail.com"}, '', 'svn78', paths,
 CR_BrowseIntegrationFactory);
 
 // register our source container with the commenting code
 // in this case we're registering the container and the revison
 // associated with the contianer which may be the primary revision
 // or may be a previous revision against which the primary revision
 // of the file is being compared.
 codereviews.registerSourceContainer(document.getElementById('lines'), 'svn78');
 
 codereviews.registerActivityListener(CR_ActivityType.REVEAL_DRAFT_PLATE, showPublishInstructions);
 
 codereviews.registerActivityListener(CR_ActivityType.REVEAL_PUB_PLATE, pubRevealed);
 codereviews.registerActivityListener(CR_ActivityType.REVEAL_DRAFT_PLATE, draftRevealed);
 codereviews.registerActivityListener(CR_ActivityType.DISCARD_DRAFT_COMMENT, draftDestroyed);
 
 
 
 
 
 
 
 var initialized = true;
 reloadCursors();
 }
 window.onload = function() {keysOnLoadHandler(); revsOnLoadHandler();};

</script>
<script type="text/javascript" src="http://www.gstatic.com/codesite/ph/3282150342600863652/js/dit_scripts.js"></script>

 
 
 
 <script type="text/javascript" src="http://www.gstatic.com/codesite/ph/3282150342600863652/js/ph_core.js"></script>
 
 
 
 
 <script type="text/javascript" src="/js/codesite_product_dictionary_ph.pack.04102009.js"></script>
</div> 
<div id="footer" dir="ltr">
 <div class="text">
 &copy;2011 Google -
 <a href="/projecthosting/terms.html">Terms</a> -
 <a href="http://www.google.com/privacy.html">Privacy</a> -
 <a href="/p/support/">Project Hosting Help</a>
 </div>
</div>
 <div class="hostedBy" style="margin-top: -20px;">
 <span style="vertical-align: top;">Powered by <a href="http://code.google.com/projecthosting/">Google Project Hosting</a></span>
 </div>
 
 


 
 </body>
</html>

