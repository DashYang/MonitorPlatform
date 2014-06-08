<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="hostname" /></title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../resources/css/bootstrap-switch.min.css" rel="stylesheet">
<script type="text/javascript" src="../resources/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/bootstrap-switch.min.js"></script>
<script src="../resources/esl.js"></script>
<script src="../resources/remotelog.js"></script>
<link href="../resources/monitor.ico" rel="shortcut icon" />
</head>
<body>
	<%@ include file="../zero/masterpage.jsp"%>
	<input id="siteid" type="hidden" value="<s:property value="id"/>">

	<div class="container">
		<div class="col-md-2">
			<ul class="nav bs-sidenav" id='myTab'>
				<li><a href="#nowrunning" data-toggle="tab">当前主机运行信息</a></li>
				<li><a href="#historyrunning" data-toggle="tab">历史主机运行信息</a></li>
			</ul>
		</div>
		<div class="col-md-10">
			<div class="tab-content">
				<div class="tab-pane fade in active" id="nowrunning">
					<button type="button" class="btn btn-info btn-xs" disabled="disabled">最后更新时间</button>
					<span id='lasttime' class="label label-default">最后更新时间
						0000-00-00-00 00:00:00</span>
					<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
					<div id="cpuandmem" style="height: 400px">载入中 ...</div>
					<div id="netinfo" style="height: 400px">载入中 ...</div>
					<div id="ibinfo" style="height: 400px">载入中 ...</div>
				</div>
				<div class="tab-pane fade in " id="historyrunning">
					<s:iterator value="logdate" id="logdate" var="var">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#myModal" style="margin: 5px">
							<s:date name="var" format="yyyy-MM-dd" />
						</button>
					</s:iterator>

					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="myModalLabel">载入中</h4>
								</div>
								<div class="modal-body">载入中</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->

				</div>
			</div>
		</div>
</body>
</html>