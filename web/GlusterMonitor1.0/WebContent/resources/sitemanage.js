var global_id;

$(document).ready(function() {
	$(".select").click(function() {
		global_id = $(this).attr("id");
		$('#myModal').modal('show');
	});
	$(".ensure").click(function() {
		$.post("../deleteSite.action", {
			id : global_id
		}, function(data, status) {
			if(status == "success")
				window.location.href = "toSitemanage.action";
		});
	});
});