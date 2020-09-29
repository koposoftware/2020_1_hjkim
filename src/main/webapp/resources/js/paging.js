/**
 * 페이징 처리를 위한 javascript
 */
//이전 버튼 이벤트

function fn_prev(page, range, rangeSize) {
	var page = ((range - 2) * rangeSize) + 1;
	var range = range - 1;
	selectList(page,range);
}

//페이지 번호 클릭
function fn_pagination(page, range, rangeSize) {
	var page = page;
	var range = range;
	selectList(page,range);
}

//다음 버튼 이벤트
function fn_next(page, range, rangeSize) {
	var page = parseInt((range * rangeSize)) + 1;
	var range = parseInt(range) + 1;
	selectList(page, range);
}

