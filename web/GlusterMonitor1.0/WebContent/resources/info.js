$(document).ready(function() {
	$(".warning").each(function(){
		var id = $(this).attr("id");
		$.post("../getSiteinfo.action", {
			siteid : id,
		}, function(data) {
			if(data == "true"){
				$("#"+id).attr("class","success");
				var content = $("#a"+id).text();
				$("#a"+id).html("<a href='getInfo.action?id="+ id + "'>" + content );
				$("#l"+id).text("上线");
				$("#l"+id).attr("class","label label-success");
			}
			else{
				$("#"+id).attr("class","danger");
				$("#l"+id).text("离线");
				$("#l"+id).attr("class","label label-danger");
			}
		});
	});		
});