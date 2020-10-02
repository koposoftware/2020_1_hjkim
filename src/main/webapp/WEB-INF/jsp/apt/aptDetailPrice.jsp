<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="margin-top-20">
	<span class="select-box-text">평수를 선택해 보세요 </span>
	<select class="dropdown" id="dd">
		<option value="all" selected="selected">전체</option>
	</select>
</div>
<div style="width: 100%;">
	<canvas id="canvas"></canvas>
</div>
<hr class="hr-style">
<div class="table-div">
	<h3>-- 실거래가 목록 --</h3>
	<table class="table table-bordered margin-top-20 price-table">
		<tr>
			<th>거래일</th>
			<th>가격</th>
			<th>면적</th>
			<th>층수</th>
		</tr>
		<c:forEach var="aptPrice" items="${ aptPriceList }">
			<tr>
				<td>${ aptPrice.yymm }${ aptPrice.dd }</td>
				<td>${ aptPrice.price } 만원</td>
				<td>${ aptPrice.area }m<sup>2</sup></td>
				<td>${ aptPrice.floor } 층</td>
			</tr>
		</c:forEach>
	</table>
</div>
<script>
	var all = [];
	var jsonData = {};
	var colorSet = ["#1ca392", "#c8713e", "#57B4C1", "#FB8851", "#B90F43", "#FFCC56", "#92beaf", "#92769e"
	];
	var labelSet = []
	var labelSetOverlab
	$.getJSON("${ pageContext.request.contextPath }/apt/priceChart.json?aptCode=${ aptCode }", addData);
	function addData(data) {
		var title = Object.keys(data)
		console.log(title)
		for (var i = 0; i < title.length; i++) {
			var dataPointPush = [], dataPointMin = [], dataPointMax = [];

			eval("var area" + String(title[i]).replace(".", "") + "=[]")
			for (var j = 0; j < data[title[i]].length; j++) {
				var xy = data[title[i]]
				var date = String(xy[j].yymm);
				var yyyy = date.substr(0, 4);
				var mm = date.substr(4, 2);
				console.log(data)
				console.log(yyyy + " " + mm)
				var formatX = yyyy + "년" + mm + "월"
				var com_ym = new Date(yyyy, mm - 1, 31);

				dataPointPush.push({
					x : formatX,
					y : xy[j].avgPrice
				})
				dataPointMin.push({
					x : formatX,
					y : xy[j].minPrice
				})
				dataPointMax.push({
					x : formatX,
					y : xy[j].maxPrice
				})
				labelSet.push(formatX)
			}
			eval("area" + String(title[i]).replace(".", "")).push({
				label : "평균 거래가",
				backgroundColor : colorSet[i],
				borderColor : colorSet[i],
				data : dataPointPush,
				fill : false,
			},{
				label : "가장 낮은 거래가",
				backgroundColor : colorSet[i+1],
				borderColor : colorSet[i+1],
				data : dataPointMin,
				fill : false,
			},{
				label : "가장 높은 거래가",
				backgroundColor : colorSet[i+2],
				borderColor : colorSet[i+2],
				data : dataPointMax,
				fill : false,
			})
			all.push({
				label : title[i] + " m² ",
				backgroundColor : colorSet[i],
				borderColor : colorSet[i],
				data : dataPointPush,
				fill : false,
			})
			var jsonName = "area" + String(title[i]).replace(".", "")
			jsonData["area" + String(title[i]).replace(".", "")] = eval(jsonName)
			$('#dd').append("<option value='"+ jsonName +"'>" + title[i] + "</option>")
		}
		jsonData["all"] = all
		dps = jsonData["all"]
		labelSetOverlab = Array.from(new Set(labelSet));
		config.data["labels"] = labelSetOverlab.sort()
		config.data["datasets"] = jsonData["all"]
		myChart.update();

	}

	var config = {
		type : 'line',
		data : {
			datasets : [{
				label : "My First dataset",
				fill : false,
				lineTension : 0.1,
				backgroundColor : "rgba(75,192,192,0.4)",
				borderColor : "rgba(75,192,192,1)",
				borderCapStyle : 'butt',
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgba(75,192,192,1)",
				pointBackgroundColor : "#fff",
				pointBorderWidth : 1,
				pointHoverRadius : 5,
				pointHoverBackgroundColor : "rgba(75,192,192,1)",
				pointHoverBorderColor : "rgba(220,220,220,1)",
				pointHoverBorderWidth : 2,
				pointRadius : 1,
				pointHitRadius : 10,
				data : [],
				spanGaps : false,
			}
			]
		},
		options : {
			responsive : true,
			title : {
				display : true,
				text : '실거래가 그래프'
			},
			tooltips : {
				mode : 'x',
				intersect : false,
				callbacks : {
					label : function(tooltipItem, data) {
						var label = data.datasets[tooltipItem.datasetIndex].label || '';

						if (label) {
							label += ': ';
						}
						label += tooltipItem.yLabel + "만원";
						return label;
					}
				}
			},
			hover : {
				mode : 'nearest',
				intersect : true
			},
			scales : {
				x : {
					display : true,
					scaleLabel : {
						display : true,
						labelString : 'Month'
					}
				},
				y : {
					display : true,
					scaleLabel : {
						display : true,
						labelString : 'Value'
					}
				},
				yAxes : [{
					ticks : {
						callback : function(value, index, values) {
							if (parseInt(value) >= 1000) {
								return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "(만원)";
							} else {
								return value + "(만원)";
							}
						}
					}
				}
				]
			}
		}
	};

	$(document).on('change', '#dd', function() {
		var e = document.getElementById("dd");
		var selected = e.options[e.selectedIndex].value;
		config.data["labels"] = labelSetOverlab.sort()
		config.data["datasets"] = jsonData[selected]
		myChart.update();
	})
	
	var ctx = document.getElementById('canvas').getContext('2d');
	ctx.canvas.width = window.innerWidth;
	window.myChart = new Chart(ctx, config);
</script>
