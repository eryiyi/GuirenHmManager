<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<script type="text/javascript" src="/js/Util.js"></script>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javaScript:void(0)">主页</a></li>
            <li><a href="javaScript:void(0)">电影上传</a></li>
            <li><a href="javaScript:void(0)">修改电影</a></li>
        </ol>
        <div id="social" class="pull-right">
            <a href="#"><i class="fa fa-google-plus"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-youtube"></i></a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改电影</span>
                </div>
                <div class="box-icons">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="expand-link">
                        <i class="fa fa-expand"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
                <div class="no-move"></div>
            </div>
            <div class="box-content">
                <h4 class="page-header">电影信息</h4>

                <form id="save_form" class="form-horizontal" method="post" role="form">
                    <input id="id" name="id" hidden="true" value="${videos.id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">电影标题</label>

                        <div class="col-sm-4">
                            <input type="text" id="paopao_title" value="${videos.title}" class="form-control" placeholder="电影标题"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电影内容</label>

                        <div class="col-sm-8">
                            <textarea id="paopao_content" value="${videos.content}" class="form-control" placeholder="电影内容" data-toggle="tooltip"
                                      data-placement="bottom" title="Tooltip for name">${videos.content}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">电影路径</label>

                        <div class="col-sm-8">
                            <textarea id="paopao_videourl" value="${videos.videoUrl}"  class="form-control" placeholder="电影路径" data-toggle="tooltip"
                                      data-placement="bottom" title="Tooltip for name">${videos.videoUrl}</textarea>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="col-sm-2 control-label">分类</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="video_type_id">
                                <option value="">--选择电影分类--</option>
                                <c:forEach items="${listDianyingType}" var="e" varStatus="st">
                                    <option value="${e.video_type_id}" ${e.video_type_id==videos.video_type_id?'selected':''}>${e.video_type_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">视频文件</label>--%>

                        <%--<div class="col-sm-10">--%>
                            <%--<input type="file" name="fileVideo" id="fileVideoUpload" style="float: left;"/>--%>
                            <%--<input type="button" value="上传" onclick="uploadVideo()" style="float: left;"/><br/><br/>--%>

                            <%--<div id="videoDiv" style="padding: 10px"></div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="button" class="btn btn-primary" onclick="save();">
                                <span><i class="fa fa-clock-o"></i></span>
                                提交
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        // Initialize datepicker
        $('#theme_start_date').datepicker({setDate: new Date()});
        $('#theme_end_date').datepicker({setDate: new Date()});
        WinMove();
    });
    function uploadImage() {
        $.ajaxFileUpload(
                {
                    url: "/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: 'fileUpload',                        //文件选择框的id属性
                    dataType: 'json',                                     //服务器返回的格式，可以是json, xml
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.success) {
                            var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + data.data + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
//                  var imageDivHtml = $("#imageDiv").html() + html;
                            $("#imageDiv").html(html);
                        } else {
                            if (data.code == 1) {
                                alert("上传图片失败");
                            } else if (data.code == 2) {
                                alert("上传图片格式只能为：jpg、png、gif、bmp、jpeg");
                            } else if (data.code == 3) {
                                alert("请选择上传图片");
                            } else {
                                alert("上传失败");
                            }
                        }
                    }
                }
        );
    }

    function deleteImage(e, node) {
        if (e.button == 2) {
            if (confirm("确定移除该文件吗？")) {
                $(node).remove();
            }
        }
    }
    ;

    function save() {
        var id = $("#id").val();

        var title = $("#paopao_title").val();

        if (title.replace(/\s/g, '') == '') {
            alert("电影标题不能为空");
            return;
        }
        var content = $("#paopao_content").val();
        if (content.replace(/\s/g, '') == '') {
            alert("电影内容不能为空");
            return;
        }


        var paopao_videourl = $("#paopao_videourl").val();
        if (paopao_videourl.replace(/\s/g, '') == '') {
            alert("电影路径不能为空");
            return;
        }

        var video_type_id = $("#video_type_id").val();
        if (video_type_id.replace(/\s/g, '') == '') {
            alert("电影类别不能为空");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/editVideos.do",
            data: {"title": title, "content": content, "id": id, "videoUrl": paopao_videourl, "video_type_id": video_type_id},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    window.location.href = "#module=listVideos&page=1"  + "&size=10"  + "&_t="+ new Date().getTime();
                } else {
                    var _case = {1: "电影标题不能为空", 2: "电影内容不能为空", 4: "请输入电影路径"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;
    function isInteger(str) {

        var regu = /^[-]{0,1}[0-9]{1,}$/;

        return regu.test(str);

    }
    ;


    //  $(document).ready(function() {
    //    var Qiniu_UploadUrl = "http://up.qiniu.com";
    //    var progressbar = $("#progressbar"),
    //            progressLabel = $(".progress-label");
    //    progressbar.progressbar({
    //      value: false,
    //      change: function() {
    //        progressLabel.text(progressbar.progressbar("value") + "%");
    //      },
    //      complete: function() {
    //        progressLabel.text("Complete!");
    //      }
    //    });
    //    $("#btn_upload").click(function() {
    //      //普通上传
    //      var Qiniu_upload = function(f, token, key) {
    //        var xhr = new XMLHttpRequest();
    //        xhr.open('POST', Qiniu_UploadUrl, true);
    //        var formData, startDate;
    //        formData = new FormData();
    //        if (key !== null && key !== undefined) formData.append('key', key);
    //        formData.append('token', token);
    //        formData.append('file', f);
    //        var taking;
    //        xhr.upload.addEventListener("progress", function(evt) {
    //          if (evt.lengthComputable) {
    //            var nowDate = new Date().getTime();
    //            taking = nowDate - startDate;
    //            var x = (evt.loaded) / 1024;
    //            var y = taking / 1000;
    //            var uploadSpeed = (x / y);
    //            var formatSpeed;
    //            if (uploadSpeed > 1024) {
    //              formatSpeed = (uploadSpeed / 1024).toFixed(2) + "Mb\/s";
    //            } else {
    //              formatSpeed = uploadSpeed.toFixed(2) + "Kb\/s";
    //            }
    //            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
    //            progressbar.progressbar("value", percentComplete);
    //            // console && console.log(percentComplete, ",", formatSpeed);
    //          }
    //        }, false);
    //
    //        xhr.onreadystatechange = function(response) {
    //          if (xhr.readyState == 4 && xhr.status == 200 && xhr.responseText != "") {
    //            var blkRet = JSON.parse(xhr.responseText);
    //            console && console.log(blkRet);
    //            $("#dialog").html(xhr.responseText).dialog();
    //          } else if (xhr.status != 200 && xhr.responseText) {
    //
    //          }
    //        };
    //        startDate = new Date().getTime();
    //        $("#progressbar").show();
    //        xhr.send(formData);
    //      };
    //      var token = $("#token").val();
    //      if ($("#file")[0].files.length > 0 && token != "") {
    //        Qiniu_upload($("#file")[0].files[0], token, $("#key").val());
    //      } else {
    //        console && console.log("form input error");
    //      }
    //    })
    //  })
</script>

