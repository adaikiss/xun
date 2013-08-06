function loadMap(x, y) {
	for ( var j = y - 10; j < y + 11; j++) {
		for ( var i = x - 10; i < x + 11; i++) {
			createMapImage(i, j, getTerrain(Math.floor(Math.random() * 7)), x, y);
		}
	}
};

function createMapImage(x, y, type, cx, cy) {
	var offset = y - cy;
	var _cx = cl + (x - cx) * aw / 2 - offset * aw / 2;
	var _cy = ct + (x - cx) * ahm / 2 + offset * ahm / 2;
	var l = _cx - aw / 2;
	var t = _cy - ahm / 2 - ahr;
	// console.debug(_cx, _cy, l, t);
	var img = $('<img style="position:absolute;" src="images/terrain_' + type
			+ '.png"/>');
	img.css({
		left : l,
		top : t,
		width : aw,
		height : ah
	});
	img.appendTo(imgLayer);
	createMapArea(_cx, _cy, x, y);
};
function createMapArea(x, y, px, py) {
	var x1 = x, y1 = y - ahm / 2;
	var x2 = x + aw / 2, y2 = y;
	var x3 = x, y3 = y + ahm / 2;
	var x4 = x - aw / 2, y4 = y;

	var area = $('<area shape="poly" coords="'
			+ [ x1, y1, x2, y2, x3, y3, x4, y4 ].join() + '" />');
	area.appendTo(mapLayer);
	area.click(function() {
		console.debug(px, py);
	});
};