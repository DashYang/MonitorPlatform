// 路径配置
require.config({
	paths : {
		'echarts' : '../resources/build/echarts',
		'echarts/chart/bar' : '../resources/build/echarts', // 把所需图表指向单文件
		'echarts/chart/line' : '../resources/build/echarts'
	}
});

var lasttime = "0000-00-00 00:00:00";
var siteid;

function getResultOfLogs(date){
	var result = "";
	siteid = $("#siteid").val();
	$.post("../getResultOfLogs.action", {
		siteid : siteid,
		date: date
	}, function(data) {
		result = data;
		$(".modal-body").html(result);
	});
}

$(document).ready(function() {
	$(".btn-primary").click(function() {
		var title = $(this).text();
		getResultOfLogs(title);
		$(".modal-title").text(title);
		$(".modal-body").text("loading");
		$('#myModal').modal('show');
	});
});

function drawGraph(time, y1, y2, myChart,title,label1,label2,unit) {
	option = {
		title : {
			text : title,
		},
		tooltip : {
			trigger : 'item'
		},
		legend : {
			data : [ label1, label2 ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			boundaryGap : true,
			data : time
		// need data time
		} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
				formatter : '{value} ' + unit
			},
			splitArea : {
				show : true
			}
		} ],
		series : [ {
			name : label1,
			type : 'line',
			itemStyle : {
				normal : {
					lineStyle : {
						shadowColor : 'rgba(0,0,0,0.4)',
						shadowBlur : 5,
						shadowOffsetX : 3,
						shadowOffsetY : 3
					}
				}
			},
			data : y1, // need data cpu
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
/*			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			} */
		}, {
			name : label2,
			type : 'line',
			itemStyle : {
				normal : {
					lineStyle : {
						shadowColor : 'rgba(0,0,0,0.4)',
						shadowBlur : 5,
						shadowOffsetX : 3,
						shadowOffsetY : 3
					}
				}
			},
			data : y2, // need data mem
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
	/*		markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}  */
		} ]
	};

	// 为echarts对象加载数据
	myChart.setOption(option);
}

// 使用
require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载line模块，按需加载
], function(ec) {
	// 基于准备好的dom，初始化echarts图表
	var cpuandmemChart = ec.init(document.getElementById('cpuandmem'));
	var netinfoChart = ec.init(document.getElementById('netinfo'));
	var ibinfoChart = ec.init(document.getElementById('ibinfo'));
	siteid = $("#siteid").val();
	// alert(siteid);
	$.post("../getLineinfo.action", {
		siteid : siteid
	}, function(data) {
		var d = eval("(" + data + ")");
		var time = d.time;
		var cpuuseratio = d.cpu;
		var memory = d.memory;
		var netsend = d.netsend;
		var netreceive = d.netreceive;
		var ibsend = d.ibsend;
		var ibreceive = d.ibreceive;
		var times = time.split(",");
		lasttime = times[times.length - 1];
		$("#lasttime").text(lasttime);
		drawGraph(time.split(","), cpuuseratio.split(","), memory.split(","),
				cpuandmemChart,'cpu和内存使用情况','cpu使用率','内存使用率','%');
		drawGraph(time.split(","), netsend .split(","), netreceive.split(","),
				netinfoChart,'net流量','net发送量','net接受量','kb');
		drawGraph(time.split(","), ibsend .split(","), ibreceive.split(","),
				ibinfoChart,'infiniband流量','ib发送量','ib接受量','kb');
		
	}, "json");

	// lastData += Math.random() * ((Math.round(Math.random() * 10) % 2)
	// == 0 ? 1 : -1);
	// lastData = lastData.toFixed(1) - 0;
	// axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

	// 动态数据接口 addData
	/*
	 * myChart.addData([ [ 0, // 系列索引 Math.round(Math.random() * 1000), // 新增数据
	 * true, // 新增数据是否从队列头部插入 false // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头 ], [
	 * 1, // 系列索引 lastData, // 新增数据 false, // 新增数据是否从队列头部插入 false, //
	 * 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头 axisData // 坐标轴标签 ] ]);
	 */
	timeTicket = setInterval(function() {

		$.post("../getCurrentLineinfo.action", {
			siteid : siteid,
			lasttime : lasttime
		}, function(data) {
			var d = eval("(" + data + ")");
			var time = d.time;
			var cpuuseratio = d.cpu;
			var memory = d.memory;
			var netsend = d.netsend;
			var netreceive = d.netreceive;
			var ibsend = d.ibsend;
			var ibreceive = d.ibreceive;
			var times = time.split(",");
			lasttime = times[times.length - 1];
			$("#lasttime").text("最后更新时间 "+lasttime);
	//		alert("add size:" + times.length );
			for(var index = 0;index < times.length;index++){
	//			alert(cpuuseratio.split(",")[index] + "\n " + memory.split(",")[index] + "\n" + times[index]);
				cpuandmemChart.addData([ [ 0, // 系列索引
				                    cpuuseratio.split(",")[index], // 新增数据
				                    false, // 新增数据是否从队列头部插入
				                    false // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				                    ], [ 1, // 系列索引
				                         memory.split(",")[index], // 新增数据
				                         false, // 新增数据是否从队列头部插入
				                         false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				                         times[index] // 坐标轴标签
				                  ] ]);
				netinfoChart.addData([ [ 0, // 系列索引
				                         	netsend.split(",")[index], // 新增数据
						                    false, // 新增数据是否从队列头部插入
						                    false // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						                    ], [ 1, // 系列索引
						                         netreceive.split(",")[index], // 新增数据
						                         false, // 新增数据是否从队列头部插入
						                         false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						                         times[index] // 坐标轴标签
						                  ] ]);
				ibinfoChart.addData([ [ 0, // 系列索引
				                         	ibsend.split(",")[index], // 新增数据
						                    false, // 新增数据是否从队列头部插入
						                    false // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						                    ], [ 1, // 系列索引
						                         ibreceive.split(",")[index], // 新增数据
						                         false, // 新增数据是否从队列头部插入
						                         false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						                         times[index] // 坐标轴标签
						                  ] ]);
			}
		}, "json");

	}, 5000);
});