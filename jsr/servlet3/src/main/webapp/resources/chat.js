$(function(){
	var messageIndex = 0;
	$('#joinBtn').click(function(){
		$('#join').hide();
		$('#content,#post').show();
		pollForMessages();
	});
	$('#post form').submit(function(){
		$.post($(this).attr('action'), {name : $('input[name="name"]').val(), content : $('input[name="content"]').val()}, function(){
			$('input[name="content"]').val('');
		});
		return false;
	});
	function pollForMessages(){
		$.get('/chat', {name : $('input[name="name"]').val(), index : messageIndex}, function(result){
			for(var i = 0;i<result.length;i++){
				$('#content').append(formatMessage(result[i]));
				messageIndex++;
			}
			pollForMessages();
		});
	}
	function formatMessage(msg){
		return '<p>[' + msg.name + ' ' + msg.time + '] ' + msg.content + '</p>';
	}
	
});