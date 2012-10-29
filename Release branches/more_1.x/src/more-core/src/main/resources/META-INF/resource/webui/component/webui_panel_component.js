﻿/* -------------------------------------------------------------------- */
/* Panel Component */
/* -------------------------------------------------------------------- */
// /* ui_AjaxForm组建 */
WebUI.Component.$extends("ui_AjaxForm", "", {
    /** 构造方法 */
    "<init>" : function() {
        if (WebUI.isNaN($(this.getElement()).attr('onsubmit')) == true)
            this.bindEvent("submit", this.onsubmit);
    },
    /** 处理ui_AjaxForm的OnSubmit动作 */
    onsubmit : function() {
        this.doSubmit({}, function(event) {
        /* TODO OnSubmit , OK CallBack. */
        }, function(event) {
        /* TODO OnSubmit , Error CallBack. */
        });
        return false;
    },
    /** 处理ui_AjaxForm的OnSubmit动作 */
    doSubmit : function(paramData, okCallBack, errCallBack) {
        // A.准备数据
        paramData = (WebUI.isObject(paramData) == false) ? {} : paramData;
        $("#" + this.clientID + ' [comType]').each(function() {
            if (WebUI.isNaN(this.uiObject) == true)
                return;
            var uio = this.uiObject;
            if (uio.isForm() == false)
                return;
            paramData[uio.componentPath + ":value"] = uio.getValue();
        });
        // B.引发OnSubmit事件
        var $this = this;
        this.doEvent("OnSubmit", paramData, function(event) {
            if (WebUI.isFun(okCallBack) == true)
                okCallBack.call($this, {
                    event : event
                });
        }, function(event) {
            if (WebUI.isFun(errCallBack) == true)
                errCallBack.call($this, {
                    event : event
                });
        });
    },
    /** 获取到表单的值Map */
    getFormData : function() {
        // A.准备数据
        var paramData = {};
        $("#" + this.clientID + ' [comType]').each(function() {
            if (WebUI.isNaN(this.uiObject) == true)
                return;
            var uio = this.uiObject;
            if (uio.isForm() == false)
                return;
            paramData[uio.getName()] = uio.getValue();
        });
        return paramData;
    }
});
/* -------------------------------------------------------------------- */
/* Complex Component */
/* -------------------------------------------------------------------- */
/* ui_Page：分页标签 */
WebUI.Component.$extends("ui_Page", {
    /** 构造方法 */
    "<init>" : function() {}
// this.goPage = function(targetObj, goIndex) {
// if (typeof (targetObj) == 'string')
// targetObj = $("[comid='" + targetObj + "']");
// //
// var stateMap = WebUI.Core.Component.getState(targetObj);
// var clickFun=eval(stateMap.clickFun);
// if (typeof(clickFun)=='function')
// clickFun.call(_this, targetObj, goIndex);
// };
// /**计算在该分页组建上指定页码的起始记录号（只有被实例化之后的组建才可以被调用）*/
// this.evalRowNum = function(pageNum) {
// return this.state.pageSize * pageNum + this.state.startWith;
// };
});
// /* ui_SWFUpload组建 */
// WebUI.ui_SWFUpload = function(cfgMap, targetObj) {
// var _this = this;
// this.config = cfgMap;
// /* Event Handlers */
// this.EHs = {};
// this.EHs.fileQueued = function(file) {};
// this.EHs.fileQueueError = function(file, errorCode, message) {};
// this.EHs.fileDialogComplete = function(numFilesSelected, numFilesQueued) {};
// this.EHs.uploadStart = function(file) {
// return true;
// };
// this.EHs.uploadProgress = function(file, bytesLoaded, bytesTotal) {};
// this.EHs.uploadSuccess = function(file, serverData) {};
// this.EHs.uploadError = function(file, errorCode, message) {};
// this.EHs.uploadComplete = function(file) {};
// this.EHs.queueComplete = function(numFilesUploaded) {};
// /* init SWFUpload */
// var upConfig = {
// upload_url : "/common/upload!deal.do?uploadType=1",// 处理上传请求的服务器端脚本URL
// flash_url : WebUI.Core.BasePath + "/platform/_script/js/webui_resource/swfupload_v2.2.0.1/falsh/swfupload.swf",// Flash控件的URL
// file_post_name : "Filedata",// 是POST过去的$_FILES的数组名
// post_params : {
// "post_param_name_1" : "post_param_value_1",
// "post_param_name_2" : "post_param_value_2",
// "post_param_name_n" : "post_param_value_n"
// },
// use_query_string : false,
// requeue_on_error : false,
// http_success : [],// 例[ 201, 202 ],
// assume_success_timeout : 0,
// file_types : _this.config.allowFiles,// 允许上传的文件类型，例：*.jpg;*.gif
// file_types_description : _this.config.allowFilesDesc,// 文件类型描述，例：Web Image Files
// file_size_limit : _this.config.allowSize,// 上传文件体积上限，单位MB
// file_upload_limit : 10,// 限定用户一次性最多上传多少个文件，在上传过程中，该数字会累加，如果设置为“0”，则表示没有限制
// file_queue_limit : 2,// 上传队列数量限制，该项通常不需设置，会根据file_upload_limit自动赋值
// debug : false,// 是否显示调试信息
// prevent_swf_caching : false,
// preserve_relative_urls : false,
// button_placeholder_id : _this.config.uploadButtonID,// flash的上传按钮显示在html的位置，此名称的元素会被替换成object元素
// button_image_url : "http://www.swfupload.org/button_sprite.png",
// button_width : _this.config.buttonWidth,// 按钮宽度
// button_height : _this.config.buttonHeight,// 按钮高度
// button_text : "<b>Click</b> <span class=\"redText\">here</span>",
// button_text_style : ".redText { color: #FF0000; }",
// button_text_left_padding : 3,
// button_text_top_padding : 2,
// button_action : SWFUpload.BUTTON_ACTION.SELECT_FILES,
// button_disabled : false,
// button_cursor : SWFUpload.CURSOR.HAND,
// button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
// // The event handler functions are defined
// swfupload_loaded_handler : _this.EHs.swfupload_loaded_function, // 当Flash控件成功加载后触发的事件处理函数
// file_dialog_start_handler : _this.EHs.file_dialog_start_function,// 当文件选取对话框弹出前出发的事件处理函数
// file_queued_handler : typeof (fileQueued) != "undefined" ? fileQueued : _this.EHs.file_queued_function,
// file_queue_error_handler : typeof (fileQueueError) != "undefined" ? fileQueueError : _this.EHs.file_queue_error_function,
// file_dialog_complete_handler : typeof (fileDialogComplete) != "undefined" ? fileDialogComplete : _this.EHs.file_dialog_complete_function, // 当文件选取对话框关闭后触发的事件处理函数
// upload_start_handler : typeof (uploadStart) != "undefined" ? uploadStart : _this.EHs.upload_start_function, // 开始上传文件前触发的事件处理函数
// upload_progress_handler : typeof (uploadProgress) != "undefined" ? uploadProgress : _this.EHs.upload_progress_function,
// upload_error_handler : typeof (uploadError) != "undefined" ? uploadError : _this.EHs.upload_error_function,
// upload_success_handler : typeof (uploadSuccess) != "undefined" ? uploadSuccess : _this.EHs.upload_success_function, // 文件上传成功后触发的事件处理函数
// upload_complete_handler : typeof (uploadComplete) != "undefined" ? uploadComplete : _this.EHs.upload_complete_function,
// // Queue plugin event
// queue_complete_handler : typeof (queueComplete) != "undefined" ? queueComplete : _this.EHs.queueComplete,
// debug_handler : _this.EHs.debug_function,
// custom_settings : {
// componentConfig : _this.config,// 组建的配置对象
// }
// };
// // 创建上传组建
// this.SWFObject = new SWFUpload(upConfig);
// /** 取消上传 */
// this.cancelQueue = function() {
// _this.SWFObject.cancelQueue();
// };
// };
