<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限警告</title>
</head>
<body>
	您没有该操作的权限，即将返回登录页面
	<script language=javascript>
		var strFullPath = window.document.location.href;
		var strPath = window.document.location.pathname;
		var pos = strFullPath.indexOf(strPath);
		var prePath = strFullPath.substring(0, pos);
		var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
		var indexpath = prePath + postPath +"/zero/index.jsp";
		setTimeout("window.location.href='" + indexpath + "'", 3000) //这里的3000意思是停留3秒
	</script>
</body>
</html>