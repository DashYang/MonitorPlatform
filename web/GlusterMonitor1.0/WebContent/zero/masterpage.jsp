<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
				<a class="navbar-brand" href="toInfo.action">Gluster Monitor</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="toInfo.action">首页</a></li>
					<li><a href="toUsermanage.action">用户管理</a></li>
					<li><a href="toSitemanage.action">站点管理</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href='toInfo.action'>欢迎用户 <s:property value="#session.USER"/></a></li>
					<li><a href='logout.action'>log out</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</nav>