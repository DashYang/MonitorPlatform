<%@page import="webservice.interfaces.Statedetector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.provider.*"%>
<%@page import="hibernate.pojo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>探针信息</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript" src="../resources/jquery-1.10.2.js"></script>
<script src="../resources/info.js"></script>
<link href="../resources/monitor.ico" rel="shortcut icon" />
</head>
<body>
	<%@ include file="../zero/masterpage.jsp"%>
	<div class="container">
		<div class="bs-example">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>探针</th>
						<th>日志选项</th>
						<th>主机名称</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<%
						SQLinterface sqLinterface = new GmsiteManage();
						ArrayList<Object> sites = sqLinterface.getList();
						if (sites != null) {
							for (Object o : sites) {
								if (o instanceof GmSite) {
									GmSite tmpGmSite = (GmSite) o;
									String address = tmpGmSite.getAddress();
									/*if(flag == true){
										out.println("<tr class='success'>");
										out.println("<td><strong><a href='getInfo.action?id="+ tmpGmSite.getId()+ "'>" + tmpGmSite.getUrl()
												+ ":" + tmpGmSite.getPort() + "/info</strong></td>");
										out.println("<td><strong><a href='getLogs.action?id="+ tmpGmSite.getId()+ "'>" + 
												"主机运行情况" + "</strong></td>");
										out.println("<td><strong>" + tmpGmSite.getDescription()
												+ "</strong></td>");
										out.println("<td><span style='font-size:14px' class='label label-success'>Online</span></td>");
										out.println("</tr>");
									}else{*/
										out.println("<tr id='" + tmpGmSite.getId() + "'class='warning'>");
										out.println("<td><strong id=a" + tmpGmSite.getId() + ">" +  tmpGmSite.getUrl()
												+ ":" + tmpGmSite.getPort() + "/info</strong></td>");
										out.println("<td><strong><a href='getLogs.action?id="+ tmpGmSite.getId()+ "'>" + 
												"主机历史运行情况" + "</strong></td>");
										out.println("<td><strong>" + tmpGmSite.getDescription()
												+ "</strong></td>");
										out.println("<td><span id=l" + tmpGmSite.getId() + " style='font-size:14px' class='label label-warning'>连接中...</span></td>");
										out.println("</tr>");
									//}
								}
							}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>