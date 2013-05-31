util = {
	pageSize : 20
};

function getErrorMsg(action){
	if(typeof action == 'string' ){
		return '操作失败, 原因: ' + action.responseText;
	}
	if(action.result){
		return '操作失败, 原因: ' + action.failureType + ' ' + action.result.message;
	}
	if(action.response){
		return '操作失败, 原因: ' + action.response.status + ' ' + action.response.statusText;
	}
	return '操作失败, 服务器错误!';
}
