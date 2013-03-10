$(function(){
	var messageIndex = 0;
	var name;
	$('#joinBtn').click(function(){
		name = $('input[name="name"]').val();
		$('#joinBtn').hide();
		$('#changeBtn').show();
		$('#content,#post').show();
		pollForMessages();
		setTimeout(function(){postMessage({name : 'SYSTEM', content : name + ' join this room!'});}, 500);
	});
	$('#changeBtn').click(function(){
		name = $('input[name="name"]').val();
	});
	$('#post form').submit(function(){
		var data = {name : name, content : $('input[name="content"]').val()};
		postMessage(data);
		return false;
	});
	function postMessage(data){
		$.post('/chat', data, function(){
			$('input[name="content"]').val('');
		});
	}

	function pollForMessages(){
		$.get('/chat', {name : name, index : messageIndex}, function(result){
			for(var i = 0;i<result.length;i++){
				$('#content').append(formatMessage(result[i]));
				messageIndex++;
			}
			pollForMessages();
		});
	}
	function formatMessage(msg){
		var n = msg.name;
		if(n == name){
			n = '<span class="self">' + n + '</span>';
		}else if(n == 'SYSTEM'){
			n = '<span class="system">' + n + '</span>';
		}
		return '[' + n + ' ' + msg.time + '] ' + msg.content + '<br>';
	}
	
});