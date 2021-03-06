<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>多文件上传工具</title>
		<link href="css/default.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/button.css" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="js/swfupload/handlers.js"></script>
		<script type="text/javascript">
			var swfu;			
			window.onload = function () {
				var pid = $("#person_id").val();
				swfu = new SWFUpload({
					upload_url: "faceImgAdd.htm",
					post_params: {"person_id" : pid},
					file_post_name : "photo",
					// File Upload Settings
					file_size_limit : "10 MB",	// 文件大小控制
					file_types : "*.jpg;*.jpeg;*.gif;*.png,*.bmp",
					file_types_description : "Image Files",
					file_upload_limit : "0",
									
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 180,
					button_height: 18,
					button_text : '<span class="button">请选择文件 </span>',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 0,
					button_text_left_padding: 18,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					
					// Flash Settings
					flash_url : "js/swfupload/swfupload.swf",
	
					custom_settings : {
						upload_target : "divFileProgressContainer"
					},
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function startUploadFile(){
				swfu.startUpload();
			}
			
		</script>
	</head>
	<body style="background-color: #C0D1E3; padding: 2px;">
		<input type="hidden" value="${person_id}" name="person_id" id="person_id"/>
		<div id="content">
			<form>
				<div
					style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
					<span id="spanButtonPlaceholder"></span>
					<input id="btnUpload" type="button" value="上  传"
						onclick="startUploadFile();" class="btn3_mouseout" 
						/>
					<input id="btnCancel" type="button" value="取消所有上传"
						onclick="cancelUpload();" disabled="disabled" class="btn3_mouseout" 
						/>
				</div>
			</form>
			<div id="divFileProgressContainer"></div>
			<div id="thumbnails">
				<table id="infoTable" border="0" width="530" style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
				</table>
			</div>
		</div>	
	</body>
</html>