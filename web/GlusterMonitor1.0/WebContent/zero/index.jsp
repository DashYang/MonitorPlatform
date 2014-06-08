<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GlusterMonitor</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../resources/monitor.ico" rel="shortcut icon" />
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/zero/index.jsp">Gluster Monitor</a>
			</div>
		</div>
	</div>
	</nav>
	<div class="container">
		<form action="login.action" class="well" role="form"
			style="width: 520px; margin: 0px auto;">
			<h2>Welcome</h2>
			<input name='username' type="text" class="form-control"
				placeholder="Username" maxlength='12'> <input
				name='password' type="password" class="form-control"
				placeholder="Password" maxlength='12'>
			<button class="btn btn-lg btn-primary btn-block"
				style="margin-top: 5px;" type="submit">登录</button>
		</form>
	</div>
</body>
</html>