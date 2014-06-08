function getResultOfshell(cmd) {
	var result = "";
	var siteid = $("#siteid").val();
	$.post("../getResultofCommand.action", {
		siteid : siteid,
		cmd: cmd
	}, function(data) {
		result = data;
		$("#cmdresult").html(result);
	});
}

function umountvolume(volumename ) {
	$("#volumeinfotips").text("loading");
	var siteid = $("#siteid").val();
	$.post("../umountVolume.action", {
		siteid : siteid,
		volumename : volumename
	}, function(data) {
		$("#volumeinfotips").html(data);
		refreshvolstat();
	}, "json"); 
}

function refreshvolstat() {
	$("#volumestatus_table").text("loading");
	var siteid = $("#siteid").val();
	$.post("../getVolumestatus.action", {
		siteid : siteid
	}, function(data) {
		$("#volumestatus_table").html(data);
		
		$(".btn-danger").click(function() {
			var tds = $(this).parent().parent().children();
			var volumename = tds.eq(0).text();
			
			umountvolume(volumename );
			//alert( tds.eq(0).text() + " " + tds.eq(3).text() );
		});
		
	}, "json");
}

function changeVolumeStatus(volumename , type) {
	$("#volumestatetips").text("loading");
	var siteid = $("#siteid").val();
	$.post("../changeVolumeStatus.action", {
		siteid : siteid,
		volumename : volumename,
		type : type
	}, function(data) {
		$("#volumestatetips").html(data);
		
		refreshvolinfos();
	}, "json"); 
}

function refreshvolinfos() {
	$("#volumeinfos_table").text("loading");
	var siteid = $("#siteid").val();
	$.post("../getVolumeinfo.action", {
		siteid : siteid
	}, function(data) {
		$("#volumeinfos_table").html(data);
		
		//stop_volume
		$(".btn-stop").click(function() {
			var tds = $(this).parent().parent().children();
			var volumename = tds.eq(0).text();
			
			changeVolumeStatus(volumename , "stopvolume");
		});
		
		$(".btn-start").click(function() {
			var tds = $(this).parent().parent().children();
			var volumename = tds.eq(0).text();
			
			changeVolumeStatus(volumename , "startvolume");
		});
		
		$(".btn-delete").click(function() {
			var tds = $(this).parent().parent().children();
			var volumename = tds.eq(0).text();
			
			changeVolumeStatus(volumename , "deletevolume");
		});
	}, "json");
}

function nodeOperation(nodename , type) {
	$("#peerinfotips").text("loading");
	var siteid = $("#siteid").val();
	$.post("../editNodeStatus.action", {
		siteid : siteid,
		nodename : nodename,
		type : type
	}, function(data) {
		$("#peerinfotips").html(data);
		refreshpeers();
	}, "json"); 
}

function smbuserOperation(username , password , type) {
	$("#sambausertips").text("loading");
	var siteid = $("#siteid").val();
	$.post("../editSambaUser.action", {
		siteid : siteid,
		username : username,
		password : password,
		type : type
	}, function(data) {
		$("#sambausertips").html(data);
		
		refreshsambauser( "#sambauser" );
		refreshsambauser( "#sambauserfordelete");
	}, "json"); 
}

function refreshpeers(){
	$("#peers_table").text("loading");
	var siteid = $("#siteid").val();
	$.post("../getPeerinfo.action", {
		siteid : siteid
	}, function(data) {
		$("#peers_table").html(data);
		
		$(".btn-delete-node").click(function() {
			var tds = $(this).parent().parent().children();
			var nodename = tds.eq(0).text();
			
			nodeOperation(nodename , "deletenode");
		});
		
	}, "json");
}

function deleteSambaDir( sambaname ) {
	$("#sambainfotips").text("loading");
	var siteid = $("#siteid").val();
	$.post("../editSambaFolder.action", {
		siteid : siteid,
		sambaname : sambaname,
		type : "delete"
	}, function(data) {
		$("#sambainfotips").html(data);
		
		refreshSambainfo();
	}, "json"); 
}

function createSambaDir( sambaname , path , user) {
	$("#sambainfotips").text("loading");
	var siteid = $("#siteid").val();
	$.post("../editSambaFolder.action", {
		siteid : siteid,
		sambaname : sambaname,
		path : path,
		user : user,
		type : "create"
	}, function(data) {
		$("#sambainfotips").html(data);
		
		refreshSambainfo();
	}, "json"); 
}

//refresh samba user
function refreshsambauser( DOMname ) {
	//refresh samba user
	var siteid = $("#siteid").val();
	$.post("../getBriefOfSambaInfo.action", {
		siteid : siteid,
		type : "user"
	}, function(data) {
		$(DOMname).html(data);
	
	}, "json");
}

function refreshSambainfo() {
	//refresh samba list
	$("#samba_table").text("loading");
	var siteid = $("#siteid").val();
	$.post("../getSambaInfo.action", {
		siteid : siteid
	}, function(data) {
		$("#samba_table").html(data);
		
		$(".btn-delete-samba").click(function() {
			var tds = $(this).parent().parent().children();
			var sambaname = tds.eq(0).text();
			
			deleteSambaDir( sambaname );
		});
		
	}, "json");
	
	//refresh samba dir
	var siteid = $("#siteid").val();
	$.post("../getBriefOfSambaInfo.action", {
		siteid : siteid,
		type : "path"
	}, function(data) {
		$("#dirpath").html(data);
	
	}, "json");
	
	refreshsambauser( "#sambauser" );
	refreshsambauser( "#sambauserfordelete");
}

function mountvolume(volumename) {
	$("#volumeinfotips").text("loading");
	var siteid = $("#siteid").val();
	
	$.post("../mountVolume.action", {
		siteid : siteid,
		volumename : volumename
	}, function(data) {
		$("#volumeinfotips").html(data);
		
		refreshvolstat();
	}, "json"); 
}

$(document).ready(function() {
//	alert(siteid + "site");
	refreshvolstat();
	
	refreshvolinfos();
	
	refreshpeers();
	
	refreshSambainfo();
	$(".btn-primary").click(function() {
		var title = $(this).text();
		getResultOfshell(title);
		$('.modal-content').attr("style","");
		$(".modal-title").text(title);
		$("#cmdresult").text("loading");
		$('#myModal').modal('show');
		$('.modal_login_form').attr("hidden",true);
		$('#smbuser-ensure').attr("disabled","disabled");
	});
	
	$("#mountvolumebutton").click(function() {
		var volumename = $("#volumename").val();
		$(this).attr("disabled","disabled");
		mountvolume(volumename );
		$(this).removeAttr("disabled");
	});
	
	$("#refreshmountinfobutton").click(function() {
		refreshvolstat();
		
	});
	
	$("#refreshvolumeinfobutton").click(function() {
		refreshvolinfos();
		
	});
	
	$("#resetvolumeinfobutton").click(function() {
		changeVolumeStatus("" , "resetvol");
		
	});
	
	$("#refreshnodeinfobutton").click(function() {
		refreshpeers();
	});
	
	$("#addnodebutton").click(function() {
		var nodename = $("#nodename").val();
		nodeOperation(nodename , "addpeer");
	});
	
	$("#resetnodeinfobutton").click(function() {
		nodeOperation("" , "resetnode");
	});
	
	$("#refreshsambainfo").click(function() {
		refreshSambainfo();
	});
	
	$("#addsambadir").click(function() {
		var sambaname = $("#dirname").val();
		var path = $("#dirpath").val();
		var user = $("#sambauser").val();
		
		createSambaDir( sambaname , path , user);
	});
	
	$("#addsambauser").click(function() {
		$('#myModal').modal('show');
		$('.modal_login_form').attr("hidden",false);
		$('#cmdresult').text("");
		$(".modal-title").text("添加samba用户");
		$('.modal-content').attr("style","width:360px");
		$('#smbuser-ensure').removeAttr("disabled");
	});
	
	$("#smbuser-ensure").click(function() {
		var username = $("#smbusername").val();
		var password = $("#smbpasswd").val();
		smbuserOperation(username , password , "addsambauser");
		$('#myModal').modal('hide');
	});
	
	$("#delsambauser").click(function() {
		var username = $("#sambauserfordelete").val();
		//alert(username);
		smbuserOperation(username , "" , "delsambauser");
	});
});