<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="database.provider.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hibernate.pojo.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../resources/monitor.ico" rel="shortcut icon" />
<script type="text/javascript" src="../resources/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../resources/sitemanage.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="../zero/masterpage.jsp"%>
	<div class="container">
		<div class="bs-example">
			<form method='post' role='form' action='addSite.action'
				class='form-inline' role='form'>
				<div class='form-group'>
					<label class='sr-only' for='urlinput'>url</label> <input
						id='urlinput' name='url' type='url' class='form-control'
						maxlength='40' placeholder='Enter url'>
				</div>
				<div class='form-group'>
					<label class='sr-only' for='portinput'>port</label> <input
						id='portinput' name='port' class='form-control' maxlength='5'
						placeholder='Enter port'>
				</div>
				<div class='form-group'>
					<label class='sr-only' for='descriptioninput'>描述</label> <input
						id='descriptioninput' name='description' class='form-control'
						maxlength='20' placeholder='Enter Description'>
				</div>
				<button type='submit' class='btn btn-default'>添加</button>
			</form>
		</div>
		<div class="bs-example">
			<table class="table">
				<thead>
					<tr>
						<th>url</th>
						<th>port</th>
						<th>描述</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="sites">
						<tr>
							<td><strong><s:property value="url" /></strong></td>
							<td><strong><s:property value="port" /></strong></td>
							<td><strong><s:property value="description" /></strong></td>
							<td><button id="<s:property value='id'/>" type='button' class='btn btn-danger btn-xs select'>删除</button></td>
						</tr>
					</s:iterator>				
				</tbody>
			</table>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">确认要删除该节点么？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger ensure">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>