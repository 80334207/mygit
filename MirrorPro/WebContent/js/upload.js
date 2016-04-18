/*$(function() {
	initSWFUpload();
	
	
	
});*/

function addface(){
	var $form = $("#uploadform");
	if($form.is(":hidden")){
		$form.show();
	}
}

function close(){
	$("#uploadform").hide();
}


var upload;
function initSWFUpload() {

	upload = new SWFUpload({
		// 处理文件上传的url
		upload_url : "../faceDetect.htm", // 路径写全，否则Firefox下会出现404错误。自由修改处一：处理文件上传的url路径，注意还要写全部
		file_post_name : "photo", // 文件的key
//		post_params : {
//			"mid" : meetingid
//		},//请求参数
		// 上传文件限制设置
		file_size_limit : "10MB", // 10MB
		file_types : "*.jpg;*.jpeg;*.gif;*.png,*.bmp", //此处也可以修改成你想限制的类型，比如：*.doc;*.wpd;*.pdf
		file_types_description : "Image Files", //打开文件选择窗口的文件类型，全文件类型 All files
		file_upload_limit : "9", //最大上传队列
		file_queue_limit : "9", //最大等待队列

		// 事件处理设置（所有的自定义处理方法都在handler.js文件里）
		file_dialog_start_handler : fileDialogStart, //在文件选取窗口将要弹出时触发
		file_queued_handler : fileQueued, //当一个文件被添加到上传队列时会触发此事件，提供的唯一参数为包含该文件信息的file object对象
		file_queue_error_handler : fileQueueError, /*当文件添加到上传队列失败时触发此事件，失败的原因可能是文件大小超过了你允许的数值、
														      文件是空的或者文件队列已经满员了等。
														      该事件提供了三个参数。第一个参数是当前出现问题的文件对象，第二个参数是具体的错误代码，
														      可以参照SWFUpload.QUEUE_ERROR中定义的常量*/
		file_dialog_complete_handler : fileDialogComplete, /*当文件选取完毕且选取的文件经过处理后（指添加到上传队列），
																      会立即触发该事件。可以在该事件中调用this.startUpload()方法来实现文件的自动上传
																      参数number of files selected指本次在文件选取框里选取的文件数量
																      参数number of files queued指本次被添加到上传队列的文件数量
																      参数total number of files in the queued指当前上传队列里共有多少个文件（包括了本次添加进去的文件）*/

		upload_start_handler : uploadStart, /*当文件即将上传时会触发该事件,该事件给了你在文件上传前的最后一次机会来验证文件信息、
											                  增加要随之上传的附加信息或做其他工作。可以通过返回false来取消本次文件的上传
												      参数file object为当前要上传的文件的信息对象*/
		upload_progress_handler : uploadProgress, /*该事件会在文件的上传过程中反复触发，可以利用该事件来实现上传进度条
														      参数file object为文件信息对象
														      参数bytes complete为当前已上传的字节数
														      参数total bytes为文件总的字节数*/
		upload_error_handler : uploadError, /*文件上传被中断或是文件没有成功上传时会触发该事件。
												      停止、取消文件上传或是在uploadStart事件中返回false都会引发这个事件，
												      但是如果某个文件被取消了但仍然还在队列中则不会触发该事件
												      参数file object为文件信息对象
												      参数error code为错误代码，具体的可参照SWFUpload.UPLOAD_ERROR中定义的常量*/
		upload_success_handler : uploadSuccess, /*当一个文件上传成功后会触发该事件
								     				      参数file object为文件信息对象
							       				                  参数server data为服务器端输出的数据*/
		upload_complete_handler : uploadComplete, /*当一次文件上传的流程完成时（不管是成功的还是不成功的）会触发该事件，
														      该事件表明本次上传已经完成，上传队列里的下一个文件可以开始上传了。
														      该事件发生后队列中下一个文件的上传将会开始*/

		// 按钮设置
		button_image_url : "", // 按钮图标
		button_placeholder_id : "spanButtonPlaceholder", //绑定文件浏览按钮，文件选择窗口时通过swfupload.swf打开的这里提供了一个flash按钮替换页面中的dom元素
		button_text : "浏览", //指定Flash按钮上的文字，也可以是html代码
		button_text_style : "color: #1C02F5; font-size: 16pt;", //Flash按钮上的文字的样式，使用方法见示例
		button_width : 30, //按钮的宽度，如果给定按钮的图标，宽度就是图标的宽
		button_height : 18, //按钮的高度，如果给定按钮的图标，高度就是图标的高
		button_text_top_padding : 4, //指定Flash按钮顶部的内边距，可使用负值
		button_text_left_padding : 4, //指定Flash按钮左边的内边距，可使用负值
		button_disabled : false, //按钮的显示状态，true为禁用

		// swf设置
		flash_url : "swfupload/swfupload.swf", //swfuploud加载路径，支持falsh9以上

		custom_settings : { //自定义属性
			progressTarget : "fsUploadProgress", //上传进度内容的dom元素对象
			cancelButtonId : "btnCancel", //全部取消上传按钮id
			uploadButtonID : "btnUpload",
			stopuploadButtonID : "btnStopUpload"
		},

		// Debug 设置
		debug : false
	});
	upload.stopped = false;
}