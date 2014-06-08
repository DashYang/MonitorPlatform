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
<title>节点信息</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript" src="../resources/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/Chart.min.js"></script>
<script type="text/javascript" src="../resources/line.js"></script>
<link href="../resources/monitor.ico" rel="shortcut icon" />
</head>
<body>
	<%@ include file="../zero/masterpage.jsp"%>
	<input id="siteid" type="hidden" value="<s:property value="id"/>">
	<div class="container">
		<div class="col-md-2">
			<ul class="nav bs-sidenav" id='myTab'>
				<li><a href="#key" data-toggle="tab">主机支持的操作</a></li>
				<li><a href="#home" data-toggle="tab">远程主机信息</a></li>
				<li><a href="#node" data-toggle="tab">服务状态</a></li>
				<li><a href="#disk" data-toggle="tab">盘信息</a></li>
				<li><a href="#volume" data-toggle="tab">卷信息</a></li>
				<li><a href="#peer" data-toggle="tab">节点信息</a></li>
				<li><a href="#samba" data-toggle="tab">samba信息</a></li>
			</ul>
		</div>

		<div class="col-md-10">
			<div class="tab-content">

				<div class="tab-pane fade in active" id="key">
					<div>
						<div class="page-header">
							<h1>主机支持的操作</h1>
						</div>

						<div>
							<s:iterator value="keys" id="keys" var="var">
								<button class="btn btn-primary" data-toggle="modal"
									data-target="#myModal" style="margin: 5px"><s:property value="var" /></button>
							</s:iterator>
						</div>
					</div>
				</div>

				<div class="tab-pane fade" id="home">
					<div class="page-header">
						<h1>远程主机信息</h1>
					</div>
					<table class="table table-bordered">
						<tr>
							<td><h4>操作系统</h4></td>
							<td><h4>
									<s:property value="serverinfo.os" />
								</h4></td>
						</tr>
						<tr>
							<td><h4>cpu</h4></td>
							<td><h4>
									<s:property value="serverinfo.cpu" />
								</h4></td>
						</tr>
						<tr>
							<td><h4>内核</h4></td>
							<td><h4>
									<s:property value="serverinfo.kernelname" />
									<s:property value="serverinfo.kernelrelease" />
								</h4></td>
						</tr>
					</table>
				</div>

				<div class="tab-pane fade" id="volume">
					<div id="volumeinfotips" class="alert alert-info">可以将卷状态为Started的卷挂载出来,为了文件安全挂载点为/gluster/卷名</div>
					<div class="form-inline">
						<div class='form-group'>
							<label class='sr-only' for='volumename'>挂载卷名</label> <input
								id='volumename' name='volumename' class='form-control'
								maxlength='10' placeholder='卷名'>
						</div>

						<button id="mountvolumebutton" class='btn btn-default'>挂载</button>
					</div>

					<div class="form-inline">
						<div class='form-group'>
							<h1>挂载状态</h1>
						</div>

						<div class='form-group' style='float: right;'>
							<h1>
								<button id="refreshmountinfobutton"
									class='btn btn-default tabs-right'>刷新</button>
							</h1>
						</div>
					</div>

					<table class="table table-bordered">
						<tr>
							<th>卷名</th>
							<th>使用(kb)</th>
							<th>可用(kb)</th>
							<th>挂载点</th>
							<th>操作</th>
						</tr>
						<tbody id="volumestatus_table">

						</tbody>
					</table>

					<div id="volumestatetips" class="alert alert-info">不要将挂载的卷停止或者删除，否则会引起异常</div>
					<div class="form-inline">
						<div class='form-group'>
							<h1>卷信息</h1>
						</div>

						<div class='form-group' style='float: right;'>
							<h1>
								<button id="refreshvolumeinfobutton" class='btn btn-default'>刷新</button>
							</h1>
						</div>

						<div class='form-group' style='float: right;'>
							<h1>
								<button id="resetvolumeinfobutton" class='btn btn-default'>重置卷</button>
							</h1>
						</div>

					</div>
					<table class="table table-bordered">
						<tr>
							<th>卷名</th>
							<th>卷类型</th>
							<th>传输类型</th>
							<th>卷状态</th>
							<th>块总数</th>
							<th>复制因子</th>
							<th>brick列表</th>
							<th>操作</th>
						</tr>
						<tbody id="volumeinfos_table">
						</tbody>
					</table>
				</div>

				<div class="tab-pane fade" id="disk">
					<div class="page-header">
						<h1>磁盘信息</h1>
					</div>
					<table class="table table-bordered">
						<tr>
							<th>节点名</th>
							<th>盘符</th>
							<th>盘大小</th>
							<th>uuid</th>
							<th>格式化类型</th>
							<th>是否被使用</th>
						</tr>
						<s:iterator value="disks" id="disks">
							<tr>
								<td><s:property value="hostname" /></td>
								<td><s:property value="disk" /></td>
								<td><s:property value="disksize" /></td>
								<td><s:property value="uuid" /></td>
								<td><s:property value="fstype" /></td>
								<td><s:property value="used" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>

				<div class="tab-pane fade" id="node">
					<div class="page-header">
						<h1>服务状态</h1>
					</div>
					<table class="table table-bordered">
						<tr>
							<th>主机名</th>
							<th>节点状态</th>
							<th>磁盘容量</th>
							<th>管理地址</th>
							<th>业务地址</th>
							<th>infiniband地址</th>
							<!-- 				<th>nfs服务状态</th>     -->
							<th>smb服务</th>
							<th>浮动ip状态</th>
							<!-- 				<th>iscsi状态</th>      -->
							<th>unistorage状态</th>
						</tr>
						<s:iterator value="nodes" id="nodes">
							<tr>
								<td><s:property value="hostname" /></td>
								<td><s:property value="nodestat" /></td>
								<td><s:property value="diskvolume" /></td>
								<td><s:property value="manageip" /></td>
								<td><s:property value="trafficip" /></td>
								<td><s:property value="ibip" /></td>
								<!-- 				<td><s:property value="nfsstatus" /></td>     -->
								<td><s:property value="smbstatus" /></td>
								<td><s:property value="ctdbstatus" /></td>
								<!-- 				<td><s:property value="iscsistatu" /></td>    -->
								<td><s:property value="unistoragestatus" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>

				<div class="tab-pane fade" id="peer">

					<div id="peerinfotips" class="alert alert-info">请确定在节点上的卷全部删除的情况下删除节点</div>

					<div class="form-inline">
						<div class='form-group'>
							<label class='sr-only' for='nodename'>节点名</label> <input
								id='nodename' name='nodename' class='form-control'
								maxlength='10' placeholder='节点名'>
						</div>

						<button id="addnodebutton" class='btn btn-default'>添加节点</button>
					</div>

					<div class="form-inline">
						<div class='form-group'>
							<h2>节点状态</h2>
						</div>
						<div class='form-group' style='float: right;'>
							<h1>
								<button id="refreshnodeinfobutton"
									class='btn btn-default tabs-right'>刷新</button>
							</h1>
						</div>

						<div class='form-group' style='float: right;'>
							<h1>
								<button id="resetnodeinfobutton" class='btn btn-default'>重置节点</button>
							</h1>
						</div>
					</div>

					<table class="table table-bordered">
						<tr>
							<th>节点名</th>
							<th>连接状态</th>
							<th>ip地址</th>
							<th>操作</th>
						</tr>
						<tbody id="peers_table">
						</tbody>
					</table>
				</div>

				<div class="tab-pane fade" id="samba">

					<div id="sambainfotips" class="alert alert-info">选择samba服务提供的文件夹</div>
					<div class="form-inline">
						<div class='form-group'>
							<label class='sr-only' for='dirname'>共享文件夹名</label> <input
								id='dirname' name='dirname' class='form-control' maxlength='10'
								placeholder='共享文件夹名'>
						</div>

						<div class='form-group'>
							<label>文件路径</label> <select id="dirpath" class="form-control">
							</select>
						</div>

						<div class='form-group'>
							<label>授权用户</label> <select id="sambauser" class="form-control">
							</select>
						</div>

						<button id="addsambadir" class='btn btn-default'>添加</button>
						<button id="refreshsambainfo" class='btn btn-default'>刷新</button>
					</div>

					<div class="page-header">
						<h1>samba信息</h1>
					</div>
					<table class="table table-bordered">
						<tr>
							<th>共享文件夹名</th>
							<th>文件路径</th>
							<th>来宾</th>
							<th>只读</th>
							<th>用户组</th>
							<th>用户</th>
							<th>操作</th>
						</tr>
						<tbody id="samba_table">

						</tbody>
					</table>
					<div class="page-header">
						<h1>samba用户</h1>
					</div>
					<div id="sambausertips" class="alert alert-info">添加或者删除samba用户</div>
					<div class="form-inline">
						<div class='form-group'>
							<label>存在用户</label> <select id="sambauserfordelete"
								class="form-control">
							</select>
							<button id="delsambauser" class='btn btn-default'>删除用户</button>
						</div>
						<div class='form-group'>
							<button id="addsambauser" class='btn btn-default'>添加用户</button>
						</div>
					</div>

				</div>

			</div>
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
					<h4 class="modal-title" id="myModalLabel">添加samba用户</h4>
				</div>
				
				<div class="modal-body">
					<form class="modal_login_form" hidden="true">
						<input id="smbusername" name='username' type="text" class="form-control"
						placeholder="Username" maxlength='12'> <input
						name='password' id="smbpasswd" type="password" class="form-control"
						placeholder="Password" maxlength='12'>
					</form>
					<div id="cmdresult">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="smbuser-ensure" class="btn btn-info">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
</body>
</html>