function ajaxTest(name, ajax, valid, skipped) {
	if(skipped){
		ok(true, "skipped!");
		return;
	}
	asyncTest(name, function() {
		$.ajax(ajax).done(function(result) {
			valid(result);
		}).fail(function(x, text, thrown) {
			ok(false, "ajax failed: " + text);
		}).always(function() {
			start();
		});
	});
}

test("normal ok", function(){
	ok([] == false, "empty array equals false!");
});

test("normal equal", function(){
	equal(![], false, "not empty array equals false!");
});

asyncTest("ajax", function(){
	$.ajax({
		url : 'data.json',
		type : 'GET',
		dataType : 'json',
		timeout : 1000
	}).done(function(result) {
        ok(result.success);
        equal(result.data.name, 'qunit');
    }).fail(function(x, text, thrown) {
		console.debug(x, text, thrown);
        ok(false, "ajax failed: " + text);
    }).always(function(){
        start();
    });
});

ajaxTest("ajax test", {
	url : 'data.json',
	type : 'GET',
	dataType : 'json',
	timeout : 1000
}, function(result){
	ok(result.success);
    equal(result.data.name, 'qunit');
});