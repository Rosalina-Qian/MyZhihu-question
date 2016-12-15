function collect(id){
	//添加收藏
 	document.getElementById('collection'+id).style.display="";
}
function newCollect(id){
 	//创建收藏夹
 	document.getElementById('favorite'+id).style.display="";
 	document.getElementById('collection'+id).style.display="none";
}
function cancelCollect(id){
	//取消收藏
 	document.getElementById('collection'+id).style.display="none";
}
function cancelFavorite(id){
	//取消添加到收藏夹
 	document.getElementById('favorite'+id).style.display="none";
}
function answer(n){
	//添加回答
	document.getElementById('myanswer').style.display=n?'block':'none';     /* 点击按钮打开/关闭 对话框 */
}
function comment(id){
	//alert('mycomment'+id);
	//添加评论
		document.getElementById('mycomment'+id).style.display="block";
}
function replyOthers(id){
	//alert('mycomment'+id);
	//回复他人
		document.getElementById('replycomment'+id).style.display="block";
}
//鼠标放上显示关注用户
function MM_over(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "block";
}
//鼠标离开隐藏关注
function MM_out(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.style.display = "none";
	
}

function clickThanks(mmObj) {
	var mSubObj = mmObj.getElementsByTagName("div")[0];
	mSubObj.html("Hello <b>world!</b>");
	
}
//var num = 0;
//感谢
//function thanks(id, uid, qid){
//	num++;
//	if(num % 2 != 0){
//		document.getElementById('thanks'+id).innerHTML = "取消感谢";
//		window.location.href='thanks?objectId='+id+'&userId='+uid+'&questionId='+qid;
//	}else{
//		document.getElementById('thanks'+id).innerHTML = "感谢";
//		
//	}
//}

//没有帮助
function help(id){
	num++;
	if(num % 2 != 0){
		document.getElementById('help'+id).innerHTML = "取消没有帮助";
	}else{
		document.getElementById('help'+id).innerHTML = "没有帮助";
	}
}
