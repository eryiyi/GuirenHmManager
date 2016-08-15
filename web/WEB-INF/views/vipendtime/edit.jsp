<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)"  onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javascript:void (0);">vip到期提醒</a></li>
            <li><a href="javascript:void (0);">提醒语句设置</a></li>
        </ol>
        <div id="social" class="pull-right">
            <a href="javascript:void(0)"><i class="fa fa-google-plus"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-facebook"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-twitter"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-linkedin"></i></a>
            <a href="javascript:void(0)"><i class="fa fa-youtube"></i></a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>提醒语句设置</span>
                </div>
            </div>
            <div class="box-content">
                <h4 class="page-header">提醒语句设置</h4>
                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${vipEndTime.mm_endtime_vip_id}" name="mm_endtime_vip_id" id="mm_endtime_vip_id"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-4">
                            <input type="text" value="${vipEndTime.mm_endtime_vip_title}" id="mm_endtime_vip_title" class="form-control" placeholder="标题" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-4">
                            <textarea  value="${vipEndTime.mm_endtime_vip_content}" cols="10" rows="10" id="mm_endtime_vip_content" class="form-control" placeholder="内容" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">${vipEndTime.mm_endtime_vip_content}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="updateRole()">确认修改</button>
                            <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function updateRole() {
        var mm_endtime_vip_id = $("#mm_endtime_vip_id").val();
        var mm_endtime_vip_title = $("#mm_endtime_vip_title").val();
        var mm_endtime_vip_content = $("#mm_endtime_vip_content").val();
        if(mm_endtime_vip_title.replace(/\s/g, '') == ''){
            alert("标题不能为空");
            return;
        }
        if(mm_endtime_vip_title.length > 100){
            alert("标题长度超出限制！最多100个字");
            return;
        }
        if(mm_endtime_vip_content.replace(/\s/g, '') == ''){
            alert("内容不能为空");
            return;
        }
        if(mm_endtime_vip_content.length > 500){
            alert("内容长度超出限制！最多500个字");
            return;
        }
        $.ajax({
            url:"/vipEndTimeController/update.do?_t=" + new Date().getTime(),
            data:{"mm_endtime_vip_id":mm_endtime_vip_id, "mm_endtime_vip_title":mm_endtime_vip_title, "mm_endtime_vip_content":mm_endtime_vip_content},
            type:"post",
            success:function(_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    history.go(-1);
                } else {
                    var _case = {1: "修改失败", 2: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

</script>