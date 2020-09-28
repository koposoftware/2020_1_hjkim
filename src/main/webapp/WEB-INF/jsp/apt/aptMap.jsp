<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>하나방</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<spring:eval expression="@property['kakaoJavaScrpitkey']"></spring:eval>&libraries=clusterer"></script>
<jsp:include page="/WEB-INF/jsp/include/link.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		let sidebar = $('.sidebar-fixed')
		/* $('.sidebar-menu').css( "margin-top", sidebar.height() ); */
		$('.sidebar-content').css({
			"max-height" : 'calc(100% - ' + sidebar.height()+ 'px)' ,
			"height" : 'calc(100% - ' + sidebar.height()+ 'px)' 
		})
		
		/* 아파트 상세보기 화면 
			왼쪽에 리스트를 맵에서 봤던 그대로 가져오기 위해 , display 속성을 이용*/
		<c:if test="${ not empty detailVO }">
			console.log('${ detailVO }')
			$(".map-class").css('display','none');
			$(".apt-detail").show();
		</c:if>
		<c:if test="${ empty detailVO }">
			$(".map-class").css('display','block');
		</c:if> 
		
		$('#searchTextBox').keyup(function(){
			searchKeyword($('#searchTextBox').val());
		})
	})
	
	function searchClick() {
		searchKeyword($('#searchTextBox').val());
	}
	
</script>
</head>
<body class="boxed">

	<!-- Loader -->
	<div class="fh5co-loader"></div>
	<div class="check">
		<div id="wrap">
			<div id="fh5co-page">
				<header>
					<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
				</header>
				<!-- Home Section -->
			</div>
		</div>
	</div>
	<section>
		<div class="page-wrapper chiller-theme toggled">
			<a id="show-sidebar" class="btn btn-sm btn-dark margin-top-100" href="#"> <i class="fas fa-bars"></i>
			</a>
			<nav id="sidebar" class="sidebar-wrapper margin-top-100">
				<div class="sidebar-fixed">
					<div class="sidebar-brand">
						<a href="#">아파트 검색</a>
						<div id="close-sidebar">
							<i class="fas fa-times"></i>
						</div>
					</div>
					<div class="sidebar-search">
						<div>
							<div class="input-group">
								<input type="text" class="form-control search-menu" id="searchTextBox" placeholder="Search...">
								<div class="input-group-append" onclick="searchClick()">
									<span class="input-group-text"> <i class="fa fa-search" aria-hidden="true"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="sidebar-content">
					<!-- sidebar-search  -->
					<div class="sidebar-menu">
						<div id="apt-search-list" class="search-box"></div>
						<div id ="apt-click-list"></div>
						<div class="header-menu">
							<span>이 지역 아파트 목록</span>
						</div>
						<div id="apt-area-list"></div>
					</div>
					<!-- sidebar-menu  -->
				</div>
				
			</nav>
			<!-- sidebar-wrapper  -->
			<main class="page-content margin-top-80">
				<div class="container-fluid apt-detail" style="display: none;">
				</div>
				<div class="container-fluid map-class">
					<div id="map"></div>
				</div>
				<footer id="footer" class="footer">
					<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
				</footer>
			</main>
		</div>
		<!-- footer Section -->

		<!-- End of footer -->
	</section>
	<script>
		var clickedOverlay = null;
		// 아파트 상세보기를 클릭했을 때 목록을 유지시키기 위해 lat lng값을 전역변수로 줌
		var lat = 37.5561010650993;
		var lng = 126.628731548188;
		
		var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
			'center' : new kakao.maps.LatLng(lat,lng),
			level : 5
		// 지도의 확대 레벨 
		});

		//하나금융티아이 이미지 설정
		var imageSrc = "${pageContext.request.contextPath}/resources/images/markers/hanamarker.png", imageSize = new kakao.maps.Size(37, 50), // 마커이미지의 크기입니다
		imageOption = {
			offset : new kakao.maps.Point(15, 40)
		};

		// 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption), markerPosition = new kakao.maps.LatLng(37.5561010650993, 126.628731548188); // 마커가 표시될 위치입니다

		//일반 마커의 이미지
		var aptImgSrc = "${pageContext.request.contextPath}/resources/images/markers/marker.png", aptSize = new kakao.maps.Size(37, 48), aptOption = {
			offset : new kakao.maps.Point(15, 40)
		};
		var aptImage = new kakao.maps.MarkerImage(aptImgSrc, aptSize, aptOption);
		//지도 최대 확대 레벨 막음
		map.setMaxLevel(13);

		// 마커 클러스터러를 생성합니다 
		var clusterer = new kakao.maps.MarkerClusterer({
			map : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
			averageCenter : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
			minLevel : 7
		// 클러스터 할 최소 지도 레벨 
		});

		var content = ''
			content += '<div class="wrap">'
			content += '    <div class="info">'
			content +='        <div class="title">'
			content +='            하나금융티아이 본사'
			content +='            <div class="close" onclick="closeCustomOverlay()" title="닫기"></div>'
			content +='        </div>'
			content +='        <div class="body">'
			content +='            <div class="img">'
			content +='					<img src="data:image/jpeg;base64,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" width="73" height="70">' 
			content +='           </div>'
			content +='            <div class="desc">' 
			content +='                <div class="ellipsis"><br><br><br>인천광역시 서구 청라동 에코로 181</div>' 
			content +='                <div class="jibun ellipsis">(우) 22742 </div>'
			content +='                <div><a href="https://www.hanati.co.kr/kor/main.jsp" target="_blank" class="link">홈페이지</a></div>' 
			content +='            </div>'
			content +='        </div>'
			content +='    </div>'
			content +='</div>';

		//마커를 클릭하면 장소명을 표출할 인포윈도우
		var infowindow = new kakao.maps.InfoWindow({
			zIndex : 1
		});

		//처음 지도 정보
		var bounds = map.getBounds();


        /*--------------------------------------------------
                    처음 지도를 띄울 때 마커를 추가한다
        --------------------------------------------------*/
		addMarker(bounds)

        
        /*--------------------------------------------------
                    중심좌표나 확대수준이 변경되면 호출한다.
        --------------------------------------------------*/
        kakao.maps.event.addListener(map, 'idle', function() {
			bounds = map.getBounds()
			//중심좌표나 확대수준이 변경되면 마커 다시생성
			addMarker(bounds)
		});

		var marker = new kakao.maps.Marker({
			position : markerPosition,
			image : markerImage
		// 마커이미지 설정 
		});

		//하나금융티아이 마커 추가
		clusterer.addMarker(marker);
		var overlay = new kakao.maps.CustomOverlay({
			content : content,
			map : map,
			position : marker.getPosition()
		});
		clickedOverlay = overlay;

		kakao.maps.event.addListener(marker, 'click', function() {
			overlay.setMap(map);
			if (clickedOverlay) {
				clickedOverlay.setMap(null);
			}
			overlay.setMap(map);
			overlay.setZIndex(3); //커스텀overlay z-index 변경
			map.panTo(marker.getPosition()); //중심좌표 이동
			clickedOverlay = overlay;
		});

		// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
		function closeOverlay() {
			overlay.setMap(null);
		}
		function displayInfo(infomarker, infodata) {
			$.ajax({
				url : '${pageContext.request.contextPath}/apt/aptBasic/' + infodata.kaptCode,
				type : 'get',
				success : function(data) {
					var content = ''
					content += '<div class="wrap">'
					content += '    <div class="info">'
					content += '        <div class="title">'
					content += data.aptBasicVO.kaptName
					content += '&nbsp;&nbsp;<i class="far fa-heart" onclick="myFunction(this); basket(\''+ infodata.kaptCode + '\')"></i>'
					content += '            <div class="close" onclick="closeCustomOverlay()" title="닫기"></div>'
					content += '        </div>'
					content += '        <div class="body">'
					content += '            <div class="desc">'
					content += '                <div class="ellipsis"> <b>(주소)</b><br>'
					content += data.aptDetailVO.kaptAddr
					content += '				</div>'
					content += '                <div class="ellipsis"> <b>(도로명)</b><br>'
					content += data.aptDetailVO.doroJuso
					content += '				</div>'
					content += '                <div class="jibun ellipsis">| 동수 | '
					content += data.aptDetailVO.kaptDongCnt
					content += ' 					|세대수 | ' + data.aptDetailVO.kaptDaCnt
					content += '				</div>'
					content += '            </div>'
					content += '        </div>'
					content += '        <div class="row">'
					content += '			<button class="btn btn-outline-info btn-sm mx-auto" onclick= aptDetailInfo(\''+ infodata.kaptCode +'\')>상세보기</button>'
					content += '        </div>'
					content += '    </div>'
					content += '</div>';
					displayOverlay(infomarker, content, data)
					displayList(data);			//지도에 표시되고 있는 정보들 좌측 리스트에 출력
				}
			})
		}
		function displayOverlay(infomarker, content, data) {

			var customOverlay = new kakao.maps.CustomOverlay({
				content : content,
				position : infomarker.getPosition()
			});
			kakao.maps.event.addListener(infomarker, 'click', function() {
				if (clickedOverlay) {
					clickedOverlay.setMap(null);
				}
				customOverlay.setMap(map);
				customOverlay.setZIndex(3); //커스텀overlay z-index 변경
				map.panTo(infomarker.getPosition()); //중심좌표 이동
				clickedOverlay = customOverlay;
				
				displayListWithClick('clear');	//선택한 아파트를 출력하기 전에 리스트를 비움
				displayListWithClick(data);		//선택한 아파트 최상단에 출력 (검색기능 추가시, 2번째 출력)
			});
		}
		function closeCustomOverlay() {
			clickedOverlay.setMap(null);
		}
		function addMarker(bounds) {
			//지도에 나오는 영역만 latlng값을 가져오기 위함
			let da = bounds.da;
			let ia = bounds.ia;
			let ja = bounds.ja;
			let ka = bounds.ka;
			let params = "?da=" + da + "&ia=" + ia + "&ja=" + ja + "&ka=" + ka;
			// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
			$.get("${ pageContext.request.contextPath }/apt/aptLatLng.json" + params, function(data) {
				// 데이터에서 좌표 값을 가지고 마커를 표시합니다
				// 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
				var aptNo;
				var overlay;
				var markers = $(data.positions).map(function(i, position) {
					return new kakao.maps.Marker({
						position : new kakao.maps.LatLng(position.lat, position.lng),
						image : aptImage
					});
				});
				//오버레이를 표시하기 전에, 아파트 목록을 지운다.
				displayList('clear');
				//마커에 커스텀 오버레이 표시
				for (var i = 0; i < data.positions.length; i++) {
					/* console.log(markers[i].getPosition()) */
					displayInfo(markers[i], data.positions[i])
				}

				// 클러스터러에 마커들을 추가합니다
				clusterer.addMarkers(markers);
				// 100px에 마커있으면 마커생성
				clusterer.setGridSize(100);
			});
		}

		function displayList(data) {
			if (data == 'clear') {
				$('#apt-area-list').empty();
			} else {
				let str = '';
				str += '<div class="apt-list-one" onclick="aptDetailInfo(\'' + data.aptBasicVO.kaptCode + '\')">'
				str += '	<div class="apt-list-one-title">' + data.aptBasicVO.kaptName + '</div>'
				str += '<span>주소&nbsp;&nbsp;| </span><span>' + data.aptDetailVO.kaptAddr + '</span><br>'
				str += '<span>세대수&nbsp;| </span><span>' + data.aptDetailVO.kaptDaCnt + '</span>'
				str += '<div>'
				str += '<hr>'
				$('#apt-area-list').append(str);
			}
		}
		
		function displayListWithClick(data) {
			if (data == 'clear') {
				$('#apt-click-list').empty();
			} else {
				let str = '';
				str += '<div class="header-menu">'
				str += '	<span>클릭된 아파트</span>'
				str += '</div>'
				str += '<div class="apt-click-one" onclick="aptDetailInfo(\'' + data.aptBasicVO.kaptCode + '\')">'
				str += '	<div class="apt-click-one-title">' + data.aptBasicVO.kaptName + '</div>'
				str += '<span>주소&nbsp;&nbsp;| </span><span>' + data.aptDetailVO.kaptAddr + '</span><br>'
				str += '<span>세대수&nbsp;| </span><span>' + data.aptDetailVO.kaptDaCnt + '</span>'
				str += '<div>'
				str += '<hr>'
				$('#apt-click-list').append(str);
			}
		}

		function aptDetailInfo(kaptCode) {
			lat = map.getCenter().getLat();
			lng = map.getCenter().getLng();
			console.log(lat + " , " + lng);
			$.ajax({
				url : '${ pageContext.request.contextPath }/apt/' + kaptCode + '/detailinfo',
				type : 'get',
				success : function(data) {
					//map-class는 숨기고 apt-detail을 보이게 한후, html을 삽입함
					$(".map-class").css('display', 'none');
					$(".apt-detail").css('display', 'block');
                    $('#apt-detail-nav').removeClass('active')
                    $('#apt-consulting-nav').removeClass('active')
                    $('#apt-price-nav').addClass('active')
					$('.apt-detail').html($.trim(data));
				},
				error : function() {
					alert('실패')
				}
			})
		}
		
		/*-------------------------------------------------------------
			키워드 검색 시 수행되는 함수
		-------------------------------------------------------------*/
		function searchKeyword(searchText){
			$.ajax({
				url : '${ pageContext.request.contextPath }/apt/search',
				type : 'post',
				data : {
					searchText : searchText
				},
				success : function(data){
					$('#apt-search-list').html(data);
				}
			})
		}
		/*-------------------------------------------------------------
			지역 클릭시 좌표 이동
		-------------------------------------------------------------*/
		function aptMoveCenter(lat,lng){
			lat = lat
			lng = lng
			map.setCenter(new kakao.maps.LatLng(lat, lng));
		}
		/*-------------------------------------------------------------
			장바구니
		------------------------------------------------------------- */
		function myFunction(x){
			x.classList.toggle("fas");
		}
		function basket(kaptCode){
			console.log(kaptCode)
		}
	</script>
	<script>
		jQuery(function($) {

			$(".sidebar-dropdown > a").click(function() {
				$(".sidebar-submenu").slideUp(200);
				if ($(this).parent().hasClass("active")) {
					$(".sidebar-dropdown").removeClass("active");
					$(this).parent().removeClass("active");
				} else {
					$(".sidebar-dropdown").removeClass("active");
					$(this).next(".sidebar-submenu").slideDown(200);
					$(this).parent().addClass("active");
				}
			});

			$("#close-sidebar").click(function() {
				$(".page-wrapper").removeClass("toggled");
			});
			$("#show-sidebar").click(function() {
				$(".page-wrapper").addClass("toggled");
			});

		});
	</script>

</body>
</html>