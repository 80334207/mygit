
//取消文件上传
function cancelQueue(instance) {
	var progressid = instance.customSettings.progressTarget;
	$("#"+progressid).find("input[name='fileindex']").each(function(){
		//var fileindex = $(this).val();
		//var file = instance.getFile(fileindex);
		if($(this).attr("checked")){
			instance.stopUpload();
			var stats = instance.getStats();
			//instance.cancelUpload(file.id);
			$(this).parent().parent().remove();
			if(stats.files_queued == 0){
				document.getElementById(instance.customSettings.cancelButtonId).disabled = true;
				document.getElementById(instance.customSettings.stopuploadButtonID).disabled = true;
				document.getElementById(instance.customSettings.uploadButtonID).disabled = true;
			}
		}
	});
}

//在文件选取窗口将要弹出时触发
function fileDialogStart() {

}
//停止上传
function stopfileupload(instance){
	var progressid = instance.customSettings.progressTarget;
	var filenum = 0;
	$("#"+progressid).find("input[name='fileindex']").each(function(){
		var fileindex = $(this).val();
		var file = instance.getFile(fileindex);
		if(checkfilestatus(file.filestatus) == "正在上传..."){
			var id = instance.customSettings.stopuploadButtonID;
			if($(this).attr("checked")){
				document.getElementById(id).disabled = true;
				document.getElementById(instance.customSettings.uploadButtonID).disabled = false;
				instance.stopUpload();
				filenum++;
			}
		}
	});
	if(filenum == 0)
		alert("没有可以暂停上传的文件！");
}

//上传
function uploadfile(instance){
	var progressid = instance.customSettings.progressTarget;
	var filenum = 0;
	$("#"+progressid).find("input[name='fileindex']").each(function(){
		var fileindex = $(this).val();
		var file = instance.getFile(fileindex);
		if(checkfilestatus(file.filestatus) != "上传成功..."){
			var id = instance.customSettings.uploadButtonID;
			if($(this).attr("checked")){	
				document.getElementById(id).disabled = true;
				document.getElementById(instance.customSettings.stopuploadButtonID).disabled = false;
				alert(file.id);
				instance.startUpload(file.id);
				filenum++;
			}	
		}
	});
	if(filenum == 0)
		alert("至少选择一个还没上传的文件！");
}

//判断文件状态
function checkfilestatus(status){
	var filestatus="";
	switch(status){
		case SWFUpload.FILE_STATUS.QUEUED:				//文件正在队列中等待上传
			filestatus = "等待上传...";
			break;
		case SWFUpload.FILE_STATUS.IN_PROGRESS:			//文件正在上传
			filestatus = "正在上传...";
			break;
		case SWFUpload.FILE_STATUS.ERROR:				//文件在添加到队列或是上传的时候出现了错误
			filestatus = "上传错误...";
			break;
		case SWFUpload.FILE_STATUS.COMPLETE:			//文件已上传成功
			filestatus = "上传成功...";
			break;
		case SWFUpload.FILE_STATUS:						//文件被取消上传
			filestatus = "取消上传...";
			break;
		case -5:
			filestatus = "上传停止...";
			break;
	}
	return filestatus;
}
function initprogress(file,id){
	var filestatus = checkfilestatus(file.filestatus);
	
	var size = (file.size)/1024;
	var sizestr = size.toString();
	sizestr = sizestr.substring(0, sizestr.indexOf(".")+3);
	var html = "<tr>" +
				"<td><input type='checkbox' id='fileindex_"+file.index+"' name='fileindex' value='"+file.index+"' checked='checked'/></td>" +
				"<td align='center' id='filename_"+file.index+"' title='filename'>"+file.name+"</td>" +
				"<td align='center' id='filesize_"+file.index+"'>"+sizestr+"kb</td>" +
				"<td align='center' id='fileuploadprogress_"+file.index+"'>" +
				"<div class='loadgdtk' id='progress_"+file.index+"'><div class='loadgdt'></div></div>"+
				"</td>" +
				"<td align='center' id='fileuploadstate_"+file.index+"' style='color:blue;'>"+filestatus+"</td></tr>";
	$("#"+id).append(html);
}
//当一个文件被添加到上传队列时会触发此事件
function fileQueued(file) {
	var progressid = this.customSettings.progressTarget;
//	判断上传列表中文件是否有重复
	var isexist = false;
	initprogress(file,progressid);
	
}
/*文件选取错误时触发 file 错误文件对象，
 * errorcode 错误代码
 * （SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED用户选取文件超过指定数量，
 * SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT文件size超过指定大小,
 * SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE文件为空
 * SWFUpload.QUEUE_ERROR.INVALID_FILETYPE不允许的文件类型）*/
function fileQueueError(file, errorCode, message) {
	try {
		switch(errorCode)
		{
			case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
				alert("您选择的图片超出指定数量，最多只能选择"+message+"张图片！");
				break;
			case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
				alert("您选择的图片太大了，每张图片最大支持10MB！");
				break;
			case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
				alert("您选择的文件格式错误，只能上传jpg、gif、bmp等格式图片！");
				break;
			case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
				alert("选择文件不能为空！");
				break;
			default:
				if(file != null)
					break;
		}
		
	} catch (e) {
//		this.debug(e);
	}
}
//当文件选取完毕且选取的文件经过处理后（指添加到上传队列），会立即触发该事件
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (this.getStats().files_queued > 0) {
			document.getElementById(this.customSettings.uploadButtonID).disabled = false;
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
	} catch (e) {
//		this.debug(e);
	}
}
//当文件即将上传时会触发该事件
function uploadStart(file) {
	var index = file.index;
	var filestatus = checkfilestatus(file.filestatus);
	$("#fileuploadstate_"+index).html(filestatus);
	$("#fileuploadstate_"+index).css("color","#666");
	return true;
}

//该事件会在文件的上传过程中反复触发，可以利用该事件来实现上传进度条
function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var index = file.index;
		$("#progress_"+index).find("div").css("width",percent+"px");
	} catch (e) {
		
	}
}
	var files_path = "";
//上传成功时触发
function uploadSuccess(file, serverData) {
	var filestatus = checkfilestatus(file.filestatus);
	var index = file.index;
	$("#fileuploadstate_"+index).html(filestatus);
	$("#fileuploadstate_"+index).css("color","green");
	
	alert(serverData);
}

//当一个文件上传流程完成时触发，表示下一个文件可以上传了(注意swfupload是一队列形式上传，即一个文件一个文件上传，每个文件上传成功都会触发该事件，所以判断所有文件都上传完时必要要以上传完的文件数和队列中的文件数比较是否一致)
function uploadComplete(file) {
	//alert(this.getStats().successful_uploads);
	//alert(this.getStats().files_queued);

	
	document.getElementById(this.customSettings.uploadButtonID).disabled = true;
	//document.getElementById(this.customSettings.cancelButtonId).disabled = false;
	document.getElementById(this.customSettings.stopuploadButtonID).disabled = true;
	
	
}

function uploadError(file, errorCode, message) {
	alert("错误代码："+errorCode+"错误信息:"+message);
	
	var filestatus = checkfilestatus(file.filestatus);
	var index = file.index;
	$("#fileuploadstate_"+index).html(filestatus);
	$("#fileuploadstate_"+index).css("color","red");
}