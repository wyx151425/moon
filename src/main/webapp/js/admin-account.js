/**
 * Created by WangZhenqi on 2016/12/21.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//鐢ㄦ埛淇℃伅淇敼鎵嬫満鍙风爜鍊掕鏃�
var InterValObj; //timer鍙橀噺锛屾帶鍒舵椂闂�
var count = 60; //闂撮殧鍑芥暟锛�1绉掓墽琛�
var curCount;//褰撳墠鍓╀綑绉掓暟

//timer澶勭悊鍑芥暟
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//鍋滄璁℃椂鍣�
        $("#get_code").removeAttr("disabled");//鍚敤鎸夐挳
        $("#get_code").html("閲嶆柊鍙戦€侀獙璇佺爜");
    }
    else {
        curCount--;
        $("#get_code").html(curCount + "绉掑悗閲嶆柊鑾峰彇");
    }
}

/*
 * 鐧诲綍js楠岃瘉
 */
var account = function () {
    return {
        CheckName: function () {
            $("#nameMsg").removeClass().html("");
            var name = $.trim($("#name").val());
            if (name.length < 2) {
                showError("濮撳悕闀垮害涓嶈兘灏戜簬2浣�");

            } else if (name.length > 15) {
                showError("濮撳悕闀垮害涓嶈兘澶т簬2浣�");

            } else {

                return true;
            }
            return false;
        },

        CheckQQ: function () {
            $("#qqMsg").removeClass().html("");
            var qq = $.trim($("#qq").val());
            if (qq.length >= 5 && checkNumber(qq)) {

                return true;
            } else {
                showError("qq鍙蜂笉鑳戒负绌�");

            }
            return false;
        },

        CheckMobile: function () {
            $("#mobileMsg").removeClass().html("");
            var mobile = $.trim($("#mobile").val());
            if (checkMobile(mobile)) {

                return true;
            } else {
                showError("鎵嬫満鍙锋湁璇�");

            }
            return false;
        },

        CheckPhone: function () {
            $("#phoneMsg").removeClass().html("");
            var phone = $.trim($("#phone").val());
            if (checkTelNum(phone)) {

                return true;
            } else {
                showError("鎵嬫満鍙锋湁璇�");

            }
            return false;
        },

        CheckCompany: function () {
            $("#companyMsg").removeClass().html("");
            var company = $.trim($("#company").val());
            if (company == '') {
                showError("鍏徃鍚嶇О涓嶈兘涓虹┖");

                return false;
            } else {

                return true;
            }
            return true;
        },

        CheckWebsite: function () {
            $("#websiteMsg").removeClass().html("");
            var website = $.trim($("#website").val());
            if (website == '') {
                showError("缃戠珯涓嶈兘涓虹┖");

                return false;
            } else {

                return true;
            }
            return true;
        },

        CheckAddress: function () {
            $("#addressMsg").removeClass().html("");
            var address = $.trim($("#address").val());
            if (address == '') {
                showError("鍦板潃閿欒");
                return false;
            } else {
                return true;
            }
            return true;
        },

        SelectType: function () {

            var devType = $("input[name='UserForm[creditType]']:checked").val();
            if (devType == 0) { //涓汉寮€鍙戣€呴€夐」
                $("#company_tr").hide();
                $("#website_tr").hide();
            } else {
                $("#company_tr").show();
                $("#website_tr").show();
            }
            return true;
        },

        CheckCom: function () {
            $("#addressMsg").removeClass().html("");
            var address = $.trim($("#address").val());
            if (address == '') {
                showError("鍏徃缃戝潃閿欒");

                return false;
            } else {

                return true;
            }
            return true;
        },

        CheckPost: function () {
            $("#postMsg").removeClass().html("");
            var post = $.trim($("#post").val());
            if (post.length == 6 && checkNumber(post)) {

                return true;
            } else {
                showError("鍦板潃閿欒");

                return true;
            }
            return false;
        },

        SendSms: function () {

        },

        validateAll: function () {

            var devType = $("input[name='UserForm[creditType]']:checked").val();
            if (devType == 0) { //涓汉寮€鍙戣€呴€夐」
                return this.CheckName() && this.CheckMobile() && this.CheckQQ()
            } else {
                銆€//浼佷笟閫夐」
                return this.CheckName() && this.CheckMobile() && this.CheckQQ() && this.CheckCompany()
            }
        }
    }
}();


//瀵嗙爜妫€娴�
function CheckOld() {

    var old = $.trim($("#oldpsw").val());
    if (old.length >= 6) {
        return true;
    } else {
        showError("璇峰～鍐欑幇鏈夊瘑鐮�");
        return false;
    }
}
function CheckPsw() {


    var psw = $.trim($("#password").val());
    if (psw.length >= 6) {
        return true;
    } else {

        showError("瀵嗙爜闀垮害涓嶈兘灏戜簬6涓瓧绗�");
        return false;
    }
}
function CheckRepsw() {
    var psw = $.trim($("#password").val());
    var repsw = $.trim($("#repsw").val());
    if (repsw.length == 0) {
        showError("璇峰啀娆″～鍐欏瘑鐮�");
        return false;
    } else if (psw == repsw) {
        return true;
    } else {
        showError("閲嶅鐨勫瘑鐮佷笉涓€鑷�");
        return false;
    }
}

function validateAll() {
    if (CheckOld() && CheckPsw() && CheckRepsw()) {
        return true;
    } else {
        return false;
    }

}

function updatePwd() {

    if (validateAll()) {
        $("#user-form").attr("disabled", true);
        var form = $("#user-form");
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function (response) {
                $("#user-form").attr("disabled", false);
                if ('success' == response) {
                    showSuccess("淇敼瀵嗙爜鎴愬姛")
                    setTimeout(function () {
                        //window.location.href = "/app/list"
                        window.location.reload();
                    }, 1000);
                    ;

                } else {
                    showError(response);
                }
            }
        });
    }
}

function cancleUpdatePwd() {
    $("#modify-pwd-note").hide();
    $("#modify-pwd-note").html("");
    $("#user-form").attr("disabled", true);

    $("#oldpsw").val("");
    $("#password").val("");
    $("#repsw").val("");

}

//璐︽埛璁剧疆
var indexPageObj = {
    //鍔犺浇椤甸潰鑷姩鎵ц
    "autoload": function () {
    },
    //娉ㄥ唽鍏冪礌浜嬩欢
    "eventReg": function () {
        $("#get_code").click(function () {
            $("#valid_msg").html("");
            銆€//娓呯┖閿欒淇℃伅

            var mobile = $.trim($("#validemobile").val())
            var data = {"mobile": mobile};
            if (!checkMobile(mobile)) {
                $("#valid_msg").html("璇疯緭鍏ユ纭殑鎵嬫満鍙风爜");
                return;
            }
            銆€銆€//璁剧疆button鏁堟灉锛屽紑濮嬭鏃�
            $(this).attr("disabled", true);
            curCount = count;
            $("#get_code").html(curCount + "绉掑悗閲嶆柊鑾峰彇");
            InterValObj = window.setInterval(SetRemainTime, 1000); //鍚姩璁℃椂鍣紝1绉掓墽琛屼竴娆�
            $.post(createUrl('account/ajaxSendSms'), data, function (result) {
                if (result == "success") {
                    $("#valid_msg").html("宸插彂閫侀獙璇佺爜");

                } else {
                    $("#valid_msg").html(result);
                }
            }, 'html');
        });

        $("#save_mobile").click(function () {
            var mobile = $.trim($("#validemobile").val())
            var validCode = $.trim($("#validcode").val())
            var data = {"mobile": mobile, "validCode": validCode};
            $.post(createUrl('account/validCode'), data, function (result) {
                if (result == "success") {
                    showSuccess("淇敼鎴愬姛");
                    $("#UserForm_code").val(validCode);
                    $("#UserForm_mobile").val(mobile);
                    $("#mobile").val(mobile);
                    $("#get_code").val("鑾峰彇楠岃瘉鐮�").attr("disabled", false);
                    $("#change-phone").modal('hide');
                } else {
                    $("#valid_msg").html(result);
                }
            }, 'html');
        });
    }
};

//閲嶆柊鐢熸垚鎶ヨ鑱旂郴浜哄垪琛�
function resetAlarmUser(data) {
    $("#alarmlist").empty();
    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;
        var optionValue = data[i].name + "-" + data[i].tel;
        $("#alarmlist").append("<option value='" + id + "'>" + optionValue + "</option>");  //娣诲姞涓€椤筼ption
    }
}

//鎶ヨ淇℃伅璁剧疆
var alarmSmsObj = {
    //鍔犺浇椤甸潰鑷姩鎵ц
    "autoload": function () {

    },
    //娉ㄥ唽鍏冪礌浜嬩欢
    "eventReg": function () {

        $("#optionsRadios1").on('switchChange.bootstrapSwitch', function (e, state) {
            if (state) {//on
                var data = {"reveiveAlarmFlag": 1};
                $.post(createUrl('account/ajaxSetReceiveAlarmSms'), data, function (result) {

                    if (result.msg == "success") {
                        showSuccess("璁剧疆鎴愬姛");
                    } else {
                        showError("璁剧疆澶辫触")
                    }
                }, 'json');
            } else {//0FF
                var data = {"reveiveAlarmFlag": 0};
                $.post(createUrl('account/ajaxSetReceiveAlarmSms'), data, function (result) {

                    if (result.msg == "success") {
                        showSuccess("璁剧疆鎴愬姛");
                    } else {
                        showError("璁剧疆澶辫触")
                    }
                }, 'json');
            }
        });

        //璁剧疆鏄惁鎺ユ敹鎶ヨ鐭俊
        $("#saveAlarmSmsReceive").click(function () {
            var reveiveAlarmFlag = $("#reveiveAlarmFlag").val();
            var data = {"reveiveAlarmFlag": reveiveAlarmFlag};
            $.post(createUrl('account/ajaxSetReceiveAlarmSms'), data, function (result) {

                if (result.msg == "success") {
                    showSuccess("璁剧疆鎴愬姛");
                } else {
                    showError("璁剧疆澶辫触")
                }
            }, 'json');
        });

        //娣诲姞鎶ヨ鐭俊鑱旂郴浜�
        $("#saveAlarmSmsUser").click(function () {


            //濡傛灉瓒呰繃3涓仈绯讳汉,娣诲姞鑱旂郴浜烘寜閽笉鍙敤
            $("#err_msg").html("");

            if ($('select[name="alarmlistname"] option').size() >= 3) {
                showError("鏈€澶氬彧鑳芥坊鍔�3涓仈绯讳汉");
                return;
            }

            var alarmName = $.trim($("#alarmName").val())
            var alarmTel = $.trim($("#alarmTel").val())
            if (alarmName == "" || alarmTel == "") {
                showError("鎺ユ敹浜哄鍚嶅拰鎺ユ敹浜烘墜鏈哄彿涓嶈兘涓虹┖");
                return;
            }
            if (!checkMobile(alarmTel)) {
                showError("鎵嬫満鍙风爜鏍煎紡鏈夎");
                return;
            }

            var data = {"alarmName": alarmName, "alarmTel": alarmTel};
            $.post(createUrl('account/ajaxSetAlarmSmsUser'), data, function (result) {
                if (result.msg == "success") {
                    showSuccess("鎴愬姛娣诲姞鎶ヨ鍙风爜");
                    resetAlarmUser(result.data);
                } else {
                    showError(result.msg);
                }
            }, 'json');
        });

        //鍒犻櫎鎶ヨ鐭俊鑱旂郴浜�
        $("#deleteAlarmSmsUser").click(function () {

            var id = $("#alarmlist").val(); //鑾峰彇閫変腑鐨勮仈绯讳汉,鏈夊彲鑳芥槸澶氶€�,"渚嬪11,12"
            if (id == null) {
                showError("璇烽€夋嫨鍏朵腑涓€涓仈绯讳汉");
                return;
            }

            var data = {"id": id};
            $.post(createUrl('account/ajaxDeleteAlarmSmsUser'), data, function (result) {
                if (result.msg == "success") {

                    resetAlarmUser(result.data);
                    showSuccess("鎴愬姛鍒犻櫎鎶ヨ鍙风爜");
                } else {
                    showError(result.msg);
                }
            }, 'json');
        });

    }

};


//receive email setting
var alarmEmailObj = {
    //鍔犺浇椤甸潰鑷姩鎵ц
    "autoload": function () {

    },
    //娉ㄥ唽鍏冪礌浜嬩欢
    "eventReg": function () {

        $("#optionsRadiosEmail").on('switchChange.bootstrapSwitch', function (e, state) {
            if (state) {//on
                var data = {"receive_email": 1};
                $.post(createUrl('account/ajaxSetReceiveEmail'), data, function (result) {

                    if (result.msg == "success") {
                        showSuccess("璁剧疆鎴愬姛");
                    } else {
                        showError("璁剧疆澶辫触")
                    }
                }, 'json');
            } else {//0FF
                var data = {"receive_email": 0};
                $.post(createUrl('account/ajaxSetReceiveEmail'), data, function (result) {

                    if (result.msg == "success") {
                        showSuccess("璁剧疆鎴愬姛");
                    } else {
                        showError("璁剧疆澶辫触")
                    }
                }, 'json');
            }
        });

    }

};

//url缁勮
var createUrl = function (route, params) {
    var paramStr = '';
    for (var key in params) {
        paramStr += '&' + key + '=' + params[key];
    }
    ;
    return BmobNamespace.baseUrlWithHost + '/' + route + paramStr;
};

//鍏叡鍑芥暟
var commonfunc = function () {
    $('.common_select_option_list li').click(function () {
        $(this).parent().find('a').removeClass('active');
        $(this).find('a').addClass('active');
    });
    $(".common_select").each(function () {
        $(this).find("p").html($(this).find('a.active').html());
    });
};

//鍏叡鍑芥暟

var _fade = function (el, msg) {
    $(el).focus();
    if ("block" == $(el).css("display")) {
        $(el).hide();
    }
    if (msg) {
        $(el).html(msg).css({
            opacity: 1.0
        }).fadeIn("fast").animate({
            opacity: 1.0
        }, 3000).fadeOut("slow");
    }
};


//upload鍒濆鍖�
//鏀跺叆鏄庣粏鍥捐〃
var _uploadInit = function () {
    $('#file_upload_img_1').uploadify({
        'auto': true,
        'queueSizeLimit': 1,
        'multi': false,
        //'removeCompleted' : false,
        'removeTimeout': 5,
        'fileSizeLimit': '2MB',
        'fileTypeDesc': 'Image Files',
        'fileTypeExts': '*.jpg;*.png',
        'buttonText': '娴忚鍥剧墖',
        'swf': BmobNamespace.uploadSwf,
        'uploader': BmobNamespace.uploadFile,
        'formData': {'f': 'credit_photo'},
        'onUploadError': function (file, errorCode, errorMsg, errorString) {
            $('#file_msg_img_1').attr("class", "alert alert-error").html('涓婁紶澶辫触锛�');
        },
        'onSelectError': function (file, errorCode, errorMsg) {
            // Load the swfupload settings
            var settings = this.settings;

            // Run the default event handler
            if ($.inArray('onSelectError', settings.overrideEvents) < 0) {
                switch (errorCode) {
                    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                        if (settings.queueSizeLimit > errorMsg) {
                            showError('\nThe number of files selected exceeds the remaining upload limit (' + errorMsg + ').');
                        } else {
                            showError('\nThe number of files selected exceeds the queue size limit (' + settings.queueSizeLimit + ').');
                        }
                        break;
                    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                        showError('\n鏂囦欢鍚�: "' + file.name + '" 瓒呰繃鎺у埗澶у皬 (' + settings.fileSizeLimit + ').');
                        break;
                    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                        showError('\n鏂囦欢鍚� "' + file.name + '" 涓虹┖.');
                    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                        showError('\n鏂囦欢鍚� "' + file.name + '" 涓嶆槸姝ｇ‘鐨勬枃浠剁被鍨�.');
                }
            }
        },
        'onCancel': function (file) {
            $('#file_img_display_1').val('');
            showError('涓婁紶琚彇娑堬紒');
        },
        'onUploadSuccess': function (file, data, response) {

            if (data == '-1') {
                showError('鏂囦欢鏍煎紡涓嶆纭紝涓婁紶澶辫触锛�');
            } else if (data == '-2') {
                showError('鏂囦欢澶у皬涓嶈兘瓒呰繃' + BmobNamespace.limit_size + '锛屼笂浼犲け璐ワ紒');
            } else if (data) {
                var file = jQuery.parseJSON(data);
                $('#credit_front_photo').val(file.filepath);
                $('#file_img_display_1_div').show();
                $('#file_img_display_1').attr("src", BmobNamespace.hostUrl + "/" + file.filepath).show();
                showSuccess('涓婁紶鎴愬姛:' + file.filename);
            } else {
                showError('涓婁紶澶辫触');
            }
        }
    });

    //$("#del_img_1").on("click", function () {
    //    $("#file_img_display_1").attr("src", BmobNamespace.watermarkEmptyImg);
    //    $("#credit_front_photo").val("")
    //});

    $('#file_upload_img_2').uploadify({
        'auto': true,
        'queueSizeLimit': 1,
        'multi': false,
        //'removeCompleted' : false,
        'removeTimeout': 5,
        'fileSizeLimit': '2MB',
        'fileTypeDesc': 'Image Files',
        'fileTypeExts': '*.jpg;*.png',
        'buttonText': '娴忚鍥剧墖',
        'swf': BmobNamespace.uploadSwf,
        'uploader': BmobNamespace.uploadFile,
        'formData': {'f': 'credit_photo'},
        'onUploadError': function (file, errorCode, errorMsg, errorString) {
            $('#file_msg_img_2').attr("class", "alert alert-error").html('涓婁紶澶辫触锛�');
        },
        'onSelectError': function (file, errorCode, errorMsg) {
            // Load the swfupload settings
            var settings = this.settings;

            // Run the default event handler
            if ($.inArray('onSelectError', settings.overrideEvents) < 0) {
                switch (errorCode) {
                    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                        if (settings.queueSizeLimit > errorMsg) {
                            this.queueData.errorMsg = '\nThe number of files selected exceeds the remaining upload limit (' + errorMsg + ').';
                        } else {
                            this.queueData.errorMsg = '\nThe number of files selected exceeds the queue size limit (' + settings.queueSizeLimit + ').';
                        }
                        break;
                    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                        this.queueData.errorMsg = '\n鏂囦欢鍚�: "' + file.name + '" 瓒呰繃鎺у埗澶у皬 (' + settings.fileSizeLimit + ').';
                        break;
                    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                        this.queueData.errorMsg = '\n鏂囦欢鍚� "' + file.name + '" 涓虹┖.';
                    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                        this.queueData.errorMsg = '\n鏂囦欢鍚� "' + file.name + '" 涓嶆槸姝ｇ‘鐨勬枃浠剁被鍨�.';
                }
            }
        },
        'onCancel': function (file) {
            $('#file_img_display_2').val('');
            $('#file_msg_img_2').attr("class", "alert alert-error").html('涓婁紶琚彇娑堬紒');
        },
        'onUploadSuccess': function (file, data, response) {
            if (data == '-1') {
                $('#file_msg_img_2').attr("class", "alert alert-error").html('鏂囦欢鏍煎紡涓嶆纭紝涓婁紶澶辫触锛�');
            } else if (data == '-2') {
                $('#file_msg_img_2').attr("class", "alert alert-error").html('鏂囦欢澶у皬涓嶈兘瓒呰繃' + BmobNamespace.limit_size + '锛屼笂浼犲け璐ワ紒');
            } else if (data) {
                var file = jQuery.parseJSON(data);
                $('#credit_back_photo').val(file.filepath);
                $('#file_img_display_2').attr("src", BmobNamespace.hostUrl + "/" + file.filepath).show();
                $('#file_msg_img_2').attr("class", "alert alert-success").html('涓婁紶鎴愬姛:' + file.filename);
            } else {
                $('#file_msg_img_2').attr("class", "alert alert-error").html('涓婁紶澶辫触锛�');
            }
        }
    });

    $('#file_upload_img_3').uploadify({
        'auto': true,
        'queueSizeLimit': 1,
        'multi': false,
        //'removeCompleted' : false,
        'removeTimeout': 5,
        'fileSizeLimit': '2MB',
        'fileTypeDesc': 'Image Files',
        'fileTypeExts': '*.jpg;*.png',
        'buttonText': '娴忚鍥剧墖',
        'swf': BmobNamespace.uploadSwf,
        'uploader': BmobNamespace.uploadFile,
        'formData': {'f': 'credit_photo'},
        'onUploadError': function (file, errorCode, errorMsg, errorString) {
            $('#file_msg_img_2').attr("class", "alert alert-error").html('涓婁紶澶辫触锛�');
        },
        'onSelectError': function (file, errorCode, errorMsg) {
            // Load the swfupload settings
            var settings = this.settings;

            // Run the default event handler
            if ($.inArray('onSelectError', settings.overrideEvents) < 0) {
                switch (errorCode) {
                    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                        if (settings.queueSizeLimit > errorMsg) {
                            showError('\nThe number of files selected exceeds the remaining upload limit (' + errorMsg + ').');
                        } else {
                            showError('\nThe number of files selected exceeds the queue size limit (' + settings.queueSizeLimit + ').');
                        }
                        break;
                    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                        showError('\n鏂囦欢鍚�: "' + file.name + '" 瓒呰繃鎺у埗澶у皬 (' + settings.fileSizeLimit + ').');
                        break;
                    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                        showError('\n鏂囦欢鍚� "' + file.name + '" 涓虹┖.');
                    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                        showError('\n鏂囦欢鍚� "' + file.name + '" 涓嶆槸姝ｇ‘鐨勬枃浠剁被鍨�.');
                }
            }
        },
        'onCancel': function (file) {
            $('#file_img_display_3').val('');
            $showError('涓婁紶琚彇娑堬紒');
        },
        'onUploadSuccess': function (file, data, response) {
            if (data == '-1') {
                showError('鏂囦欢鏍煎紡涓嶆纭紝涓婁紶澶辫触锛�');
            } else if (data == '-2') {
                showError('鏂囦欢澶у皬涓嶈兘瓒呰繃' + BmobNamespace.limit_size + '锛屼笂浼犲け璐ワ紒');
            } else if (data) {
                var file = jQuery.parseJSON(data);
                $('#company_credit_photo').val(file.filepath);
                $('#file_img_display_3_div').show();
                $('#file_img_display_3').attr("src", BmobNamespace.hostUrl + "/" + file.filepath).show();
                showSuccess('涓婁紶鎴愬姛:' + file.filename);
            } else {
                showError('涓婁紶澶辫触');
            }
        }
    });

    //$("#del_img_2").on("click", function () {
    //    $("#file_img_display_2").attr("src", BmobNamespace.watermarkEmptyImg);
    //    $("#credit_back_photo").val("")
    //});
};

var _infoFormSubmit = function () {
    var form = $("#info-form");
    var msg_err = $("#msg_error");

    var credit_name = form.find("input[name=credit_name]").val().replace(/(^\s*)|(\s*$)/g, "");
    var credit_num = form.find("input[name=credit_num]").val().replace(/(^\s*)|(\s*$)/g, "");
    var credit_front_photo = form.find("input[name=credit_front_photo]").val().replace(/(^\s*)|(\s*$)/g, "");
    var company_credit_photo = form.find("input[name=company_credit_photo]").val().replace(/(^\s*)|(\s*$)/g, "");
    var mobile = $("#UserForm_mobile").val();
    var qq = $("#qq").val();

    if (credit_name && credit_num && credit_front_photo) {
        if (credit_name.length < 2 || credit_name.length > 10 || !checkChinese(credit_name)) {
            showError("璇佷欢濮撳悕蹇呴』涓烘纭殑涓枃鍚嶇О");
            return false
        }
        msg = checkIDNO(credit_num);
        if ("楠岃瘉閫氳繃!" != msg) {
            showError(msg);
            return false;
        }
        msg = isQQ(qq);
        if (!msg) {
            showError("璇疯緭鍏ユ纭殑QQ鍙风爜");
            return false;
        }
        if (!checkMobile(mobile)) {
            showError("璇疯緭鍏ユ纭殑鎵嬫満鍙风爜");
            return;
        }

        // var radioType = $('#input[name="credit_type"]:checked').val();
        var radioType = form.find("input[name=credit_type]:checked").val();
        // alert(radioType);
        if (1 == radioType && !company_credit_photo) { //浼佷笟甯愬彿
            showError("蹇呴』涓婁紶浼佷笟鎵х収");
            return false;
        }
        return true;
    } else {
        showError("鎵€鏈夊繀濉」涓嶈兘涓虹┖");
        return false;
    }
    return false;
};

//鏀跺叆鏄庣粏椤甸潰鎵€鏈変簨浠�
var infoPageObj = {
    //鍔犺浇椤甸潰鑷姩鎵ц
    "autoload": function () {
        _uploadInit();
    },
    //娉ㄥ唽鍏冪礌浜嬩欢
    "eventReg": function () {
        //$("#info-form").submit(function (event) {
        //    event.preventDefault();
        //});
        $("#info-form").find("input[name=credit_type]").change(function () {
            var t = $(this).val();
            if (t == 1) {
                $("#company_credit_photo_tr").show();
            } else {
                $("#company_credit_photo_tr").hide();
            }
        });
        $("#submit_save").click(function () {
            if (_infoFormSubmit()) {
                $("#submit_save").prop("disabled", true);

                var form = $("#info-form");
                var credit_name = form.find("input[name=credit_name]").val().replace(/(^\s*)|(\s*$)/g, "");
                var credit_num = form.find("input[name=credit_num]").val().replace(/(^\s*)|(\s*$)/g, "");
                var credit_front_photo = form.find("input[name=credit_front_photo]").val().replace(/(^\s*)|(\s*$)/g, "");
                var company_credit_photo = form.find("input[name=company_credit_photo]").val().replace(/(^\s*)|(\s*$)/g, "");
                var credit_type = form.find("input[name=credit_type]:checked").val();
                var mobile = $("#UserForm_mobile").val();
                var qq = $("#qq").val();
                //if($("#mobile2").val()!="0"&&$("#qq2").val()!="0"){
                //    mobile="";
                //    qq="";
                //}
                //if($("#mobile2").val()!=""&&$("#qq2").val()!=""){
                //    mobile="";
                //    qq="";
                //}

                var data = {
                    "credit_name": credit_name,
                    "credit_num": credit_num,
                    "credit_front_photo": credit_front_photo,
                    "company_credit_photo": company_credit_photo,
                    "credit_type": credit_type,
                    "credit_qq": qq,
                    "credit_tel": mobile
                };
                //$.post(BmobNamespace.infoPostAjax,data, function(result){
                //    $("#submit_save").prop( "disabled", false );
                //    if(result=="success"){
                //        $("#verify_status").html("寰呭鏍�");
                //        showSuccess("鎻愪氦鎴愬姛");
                //    }else{
                //        showError(result)
                //    }
                //},'json');

                $.ajax({
                    type: 'POST',
                    url: BmobNamespace.infoPostAjax,
                    data: data,
                    dataType: 'json',
                    async: false,
                    success: function (result) {
                        $("#submit_save").prop("disabled", false);
                        if (result == "success") {
                            $("#verify_status").html("寰呭鏍�");
                            showSuccess("鎻愪氦鎴愬姛");
                        } else {
                            showError(result)
                        }
                    }
                });


                // $("#info-form").submit();
            }

        });
    }
};


///=======================================================
/// Function Name:checkIsEmpty
/// Function Desc:妫€鏌ユ枃鏈涓槸鍚︽湁鍊�
///=======================================================
function checkIsEmpty(obj) {
    if (obj != null) {
        if (document.getElementById(obj).value.trim() == "")
            return false;
        return true;
    }
}

///=======================================================
/// Function Name:checkTextLength
/// Function Desc:妫€鏌ユ枃鏈涓緭鍏ユ枃鏈殑闀垮害鏄惁绗﹀悎瑕佹眰
///=======================================================
function checkTextLength(obj, length) {
    if (obj != null) {
        if (document.getElementById(obj).value.trim().length > length)
            return false;
        return true;
    }
}


///=======================================================
/// Function Name:checkIsDecimal
/// Function Desc:妫€楠岃緭鍏ョ殑鏁板瓧鏄惁涓鸿揣甯佺被鍨�
///=======================================================
function checkIsDecimal(value, length) {
    value = new String(value);

    if (value.match(/^\d*\.?\d{0,2}$/) == null)
        return false;

    var sp = value.split(".");
    if (sp.length == 1) {
        if (sp[0].trim().length < 1) {
            return false;
        }
    }
    else if (sp.length == 2) {
        if (sp[0].trim().length < 1 || sp[1].length != length) {
            return false;
        }
    }
    else {
        return false;
    }

    return true;
}
///=======================================================
/// Function Name:CheckIsMoney
/// Function Desc:妫€楠岃緭鍏ョ殑鏁板瓧鏄惁涓洪噾棰濓紝涓や綅灏忔暟
///=======================================================
function CheckIsMoney(value) {
    if (value == 0) {
        return false;
    }
    var reg = new RegExp(/^\d*\.?\d{0,2}$/);
    if (!reg.test(value)) {
        return false;
    }
    if (Number(value) < 0.01) {
        return false;
    }
    return true;
}

///=======================================================
/// Function Name:checkIsDecimal
/// Function Desc:妫€楠岃緭鍏ョ殑鏁板瓧鏄潪璐熸诞鐐瑰瀷
///=======================================================
function checkIsNumberic(value) {
    value = new String(value);

    if (value.match(/^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$/g) == null)
        return false;

    return true;
}

///=======================================================
/// Function Name:checkIsCharAndNum
/// Function Desc:杈撳叆妗嗕腑鍙兘杈撳叆鏂囧瓧銆佹暟瀛楀拰瀛楁瘝
///=======================================================
function checkIsCharAndNum(value) {
    //var value = document.getElementById(obj).value;
    var reg = /^([A-Z]|[^u4E00-u9FA5]|[a-z]|[\d])*$/g;

    if (reg.exec(value) == null) {
        return false;
    }

    return true;
}

///=======================================================
/// Function Name:checkNumber
/// Function Desc:杈撳叆妗嗕腑鍙兘杈撳叆鏁板瓧
///=======================================================
function checkNumber(value) {
    var number = value.trim();
    var reg = /[^0-9]/g;

    if (reg.exec(number) == null) {
        return true;
    }

    return false;
}
///=======================================================
/// Function Name:checkPlusNumber
/// Function Desc:杈撳叆妗嗕腑鍙兘杈撳叆姝ｆ暣鏁�
///=======================================================
function checkPlusNumber(value) {
    var number = value.trim();
    var reg = /^[1-9]\d*$/;

    if (reg.exec(number) == null) {
        return false;
    }

    return true;
}

///=======================================================
/// Function Name:checkLetter
/// Function Desc:杈撳叆妗嗕腑鍙兘杈撳叆瀛楁瘝
///=======================================================
function checkLetter(value) {
    var chars = value.trim();
    var reg = /[^A-Za-z]/g;

    if (reg.exec(chars) == null) {
        return true;
    }

    return false;
}

///=======================================================
/// Function Name:checkChinese
/// Function Desc:杈撳叆妗嗕腑鍙兘杈撳叆涓枃瀛楃
///=======================================================
function checkChinese(value) {
    var chars = value.trim();
    var reg = /[^\u4E00-\u9FA5]/g;
    if (reg.exec(chars) == null) {
        return true;
    }
    return false;
}

/// Function Name:IsURL
/// Function Desc:鏄惁涓篣RL
///=======================================================
function IsURL(value) {
    var chars = value.trim();
    r = false;
    var parat = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if (parat.exec(chars) == null)
        r = false;
    else
        r = true;
    return r;
}


///=======================================================
/// Function Name:checkEmail
/// Function Desc:妫€鏌ユ枃鏈涓緭鍏ョ殑Email鏄惁姝ｇ‘
///=======================================================
function checkEmail(str) {
    var r = false;
    var parat = /^([a-zA-Z0-9_\-\.\+]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    if (parat.exec(str) == null)
        r = false;
    else
        r = true;
    return r;
}
///=======================================================
/// Function Name:checkzipcode
/// Function Desc:妫€鏌ユ槸鍚︿负閭斂缂栫爜
///=======================================================
function checkZipCode(str) {
    var r = false;
    var parat = /^[0-9]{6}$/;

    if (parat.exec(str) == null)
        r = false;
    else
        r = true;
    return r;
}
///=======================================================
/// Function Name:checkMobile
/// Function Desc:妫€鏌ユ枃鏈涓緭鍏ョ殑鏄惁涓烘墜鏈哄彿鐮侊紝鎵嬫満鍙�
/// 鐮佸彲涓�11浣嶆垨12浣嶏紝褰撲负11浣嶆椂绗竴涓暟瀛楀繀闇€涓�1绗簩涓暟
/// 瀛椾负3鎴�5锛屽鏋滀负12浣嶆墜鏈哄彿鏃剁涓€涓暟瀛楀繀闇€涓�0锛岀浜屼釜
/// 鏁板瓧蹇呴渶涓�1锛岀涓変釜鏁板畾涓�3鎴�5
///=======================================================
function checkMobile(val) {
    if (val == "")
        return false;
    var number = val;
    if (val.trim() == '')
        return false;

    var r = false;
    var regx = /^(130|131|132|133|134|135|136|137|138|139|158|159|147)\d{8}$/;
    var regx2 = /^(0130|0131|0132|0133|0134|0135|0136|0137|0138|0139|0158|0159|0147)\d{8}$/;
    var regx3 = /^\d{11}$/;
    var regx4 = /^\d{12}$/;

    if (number.length == 12) {
        if (regx4.exec(number) == null)
            r = false;
        else
            r = true;
    }
    if (number.length == 11) {
        if (regx3.exec(number) == null)
            r = false;
        else
            r = true;
    }
    return r;
}

///=======================================================
/// Function Name:checkTelNum
/// Function Desc:妫€鏌ユ枃鏈涓緭鍏ョ殑鏄骇鏈哄彿鐮�
///=======================================================
function checkTelNum(number) {
    if (number == "")
        return false;
    var t = false;

    var patrn = /^[0-9]{7,8}$/;
    var patrn1 = /^[0-9]{3,4}[-]{1}[0-9]{7,8}[-]{1}[0-9]{2,4}$/;
    var patrn2 = /^[0-9]{3,4}[-]{1}[0-9]{7,8}$/;
    var patrn3 = /^[0-9]{7,8}[-]{1}[0-9]{2,4}$/;
    var patrn4 = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
    var patrn5 = /^[48]00[-]{1}\d{3}[-]{1}\d{4}$/;
    if (patrn.exec(number) == null && patrn1.exec(number) == null && patrn2.exec(number) == null && patrn3.exec(number) == null && patrn4.exec(number) == null && patrn5.exec(number) == null) {
        t = false;
    }
    else {
        t = true;
    }
    return t;
}


///=======================================================
/// Function Name:checkIDNO
/// Function Desc:妫€鏌ユ枃鏈涓緭鍏ヨ韩浠借瘉鍙风爜
///=======================================================
function checkIDNO(idcard) {
    var Errors = new Array(
        "楠岃瘉閫氳繃!",
        "韬唤璇佸彿鐮佷綅鏁颁笉瀵�!",
        "韬唤璇佸彿鐮佸嚭鐢熸棩鏈熻秴鍑鸿寖鍥存垨鍚湁闈炴硶瀛楃!",
        "韬唤璇佸彿鐮佹牎楠岄敊璇�!",
        "韬唤璇佸湴鍖洪潪娉�!"
    );
    var area = {
        11: "鍖椾含",
        12: "澶╂触",
        13: "娌冲寳",
        14: "灞辫タ",
        15: "鍐呰挋鍙�",
        21: "杈藉畞",
        22: "鍚夋灄",
        23: "榛戦緳姹�",
        31: "涓婃捣",
        32: "姹熻嫃",
        33: "娴欐睙",
        34: "瀹夊窘",
        35: "绂忓缓",
        36: "姹熻タ",
        37: "灞变笢",
        41: "娌冲崡",
        42: "婀栧寳",
        43: "婀栧崡",
        44: "骞夸笢",
        45: "骞胯タ",
        46: "娴峰崡",
        50: "閲嶅簡",
        51: "鍥涘窛",
        52: "璐靛窞",
        53: "浜戝崡",
        54: "瑗胯棌",
        61: "闄曡タ",
        62: "鐢樿們",
        63: "闈掓捣",
        64: "瀹佸",
        65: "鏂扮枂",
        71: "鍙版咕",
        81: "棣欐腐",
        82: "婢抽棬",
        91: "鍥藉"
    }

    var idcard, Y, JYM;
    var S, M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
//鍦板尯妫€楠�
    if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
//韬唤鍙风爜浣嶆暟鍙婃牸寮忔楠�
    switch (idcard.length) {
        case 15:
            if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//娴嬭瘯鍑虹敓鏃ユ湡鐨勫悎娉曟€�
            } else {
                ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//娴嬭瘯鍑虹敓鏃ユ湡鐨勫悎娉曟€�
            }
            if (ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        case 18:
//18浣嶈韩浠藉彿鐮佹娴�
//鍑虹敓鏃ユ湡鐨勫悎娉曟€ф鏌�
//闂板勾鏈堟棩:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
//骞冲勾鏈堟棩:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
            if (parseInt(idcard.substr(6, 4)) % 4 == 0 || (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0 )) {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闂板勾鍑虹敓鏃ユ湡鐨勫悎娉曟€ф鍒欒〃杈惧紡
            } else {
                ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//骞冲勾鍑虹敓鏃ユ湡鐨勫悎娉曟€ф鍒欒〃杈惧紡
            }
            if (ereg.test(idcard)) {//娴嬭瘯鍑虹敓鏃ユ湡鐨勫悎娉曟€�
//璁＄畻鏍￠獙浣�
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                    + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                    + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                    + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                    + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                    + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                    + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                    + parseInt(idcard_array[7]) * 1
                    + parseInt(idcard_array[8]) * 6
                    + parseInt(idcard_array[9]) * 3;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y, 1);//鍒ゆ柇鏍￠獙浣�
                if (M == idcard_array[17])
                    return Errors[0]; //妫€娴婭D鐨勬牎楠屼綅
                else
                    return Errors[3];
            }
            else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }
}

///=======================================================
/// Function Name:checkHtml
/// Function Desc:妫€鏌ユ枃鏈涓緭鍏ュ唴瀹规槸鍚﹀惈鏈塇TML鏍囪
///=======================================================
function checkHtml(value) {
    var chars = value;

    var reg = /[\<\>\<>\/>\<\/>]/g;

    if (reg.exec(chars) == null) {
        return true;
    }
    return false;
}


function isQQ(aQQ) {
    var bValidate = RegExp(/^[1-9][0-9]{4,9}$/).test(aQQ);
    if (bValidate) {
        return true;
    }
    else
        return false;
}

//@magic
var TEAM = {
    isSub: 0,
    delId: 0,
    init: function () {
        $('#switch_box').bootstrapSwitch();
        $("#mail").bind('blur', this.validateEmail);
    },
    eventReg: function () {
        $(".btn-del").click(function () {
            //alert('')
            $("#deltr").modal('show');
            TEAM.delId = $(this).data('value');
            //console.log(this.delId);
        });

    },
    CheckName: function () {
        $("#nameMsg").removeClass().html("");
        var name = $.trim($("#name").val());
        if (name.length < 2) {
            showError("濮撳悕闀垮害涓嶈兘灏戜簬2浣�");

        } else if (name.length > 15) {
            showError("濮撳悕闀垮害涓嶈兘澶т簬2浣�");

        } else {

            return true;
        }
        return false;
    },
    validateAll: function () {
        if (this.CheckName()) {
            var permission = $("#permission").val();

            if (permission == '') {
                showError('璇疯嚦灏戦€夋嫨涓€涓簲鐢ㄨ繘琛屾潈闄愬垎閰�');
                return false;
            }

            $("#mail").blur();
            var s = TEAM.isSub;
            if (s != 1) {
                return true;
            }
        }

        return false;
    },

    validateEmail: function () {
        var mail = $(this).val();
        var email = $("#email").val();


        var valiEmail = function (val) { //email, 0涓轰笉鍚堟硶, 1涓哄悎娉�;
            var val = val,
                r = "";
            r = val.match(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-]{2,4})+/ig);
            if (r != null) {
                r = 1;
            }
            else {
                r = 0;
            }
            return r;
        };
        //alert(mail);
        if (email != undefined && email == mail) {
            TEAM.isSub = 2;
            return true; //濡傛灉鏄紪杈�,骞朵笖閭璺熶箣鍓嶇殑閭涓€鑷村垯涓嶉渶瑕佸垽鏂�
        }

        var isok = valiEmail(mail);
        if (!isok) {
            showError("閭鏍煎紡閿欒");
            TEAM.isSub = 1;
            return false;
        }


        $.ajax({
            type: 'GET',
            url: BmobNamespace.validateEmail,
            data: {'email': mail},
            dataType: 'json',
            success: function (response) {
                if (response.ok == 'yes') {
                    TEAM.isSub = 1;
                    showError(response.msg);
                    return false;
                } else {
                    TEAM.isSub = 0;
                }
            }
        });


    },
    //鍒犻櫎璁板綍
    DelRow: function () {
        var id = TEAM.delId;
        console.log(id);
        if (id) {
            $.ajax({
                type: 'GET',
                url: BmobNamespace.delRowUrl,
                data: {'id': id},
                dataType: 'json',
                //async: false,
                success: function (response) {
                    console.log(response);
                    if ('success' == response.msg) {
                        $("#deltr").modal('hide');
                        showSuccess("鍒犻櫎鎴愬姛");
                        $("#row-" + response.id).remove();
                        //window.location.href = BmobNamespace.browserUrl;
                    } else {
                        $("#deltr").modal('hide');
                        showError(response.msg);
                    }
                }
            });
        } else {
            showError("涓嶅湪鍒楄〃涓垨宸茬粡鍒犻櫎");
        }

    },

};