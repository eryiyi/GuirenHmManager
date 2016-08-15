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
      <li><a href="javascript:void (0);">排行榜</a></li>
      <li><a href="javascript:void (0);">商户详情</a></li>
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
          <span>商户详情</span>
        </div>
      </div>
      <div class="box-content">
        <form class="form-horizontal" role="form">
          <input type="hidden" value="${recordVO.mm_paihang_id}" id="mm_paihang_id">

            <div class="form-group">
                <label class="col-sm-2 control-label">商户名称</label>
                <div class="col-lg-8">
                  <div id="mm_emp_nickname">${recordVO.mm_emp_nickname}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">商户电话</label>
                <div class="col-lg-8">
                    <div id="mm_emp_mobile">${recordVO.mm_emp_mobile}</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">商户公司</label>
                <div class="col-lg-8">
                    <div id="mm_emp_company">${recordVO.mm_emp_company}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">商户地址</label>
                <div class="col-lg-8">
                    <div id="mm_emp_company_address">${recordVO.mm_emp_company_address}</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">是否显示</label>
                <div class="col-sm-4">
                    <select class="form-control" id="is_del">
                        <option value="">--请选择--</option>
                        <option value="0" ${recordVO.is_del=='0'?'selected':''}>是</option>
                        <option value="1" ${recordVO.is_del=='1'?'selected':''}>否</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">置顶数字</label>
                <div class="col-sm-4">
                    <input type="text" id="top_num" class="form-control" placeholder="置顶数字越大 越靠前" value="${recordVO.top_num}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                </div>
            </div>

            <%--<div class="form-group">--%>
                <%--<label class="col-sm-2 control-label">时间戳</label>--%>
                <%--<div class="col-lg-8">--%>
                    <%--<div id="dateline">${um:format(recordVO.end_time, "yyyy-MM-dd HH:mm:ss")}</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="form-group">
                <label class="col-sm-2 control-label">上榜到期日期</label>
                <div class="col-sm-2">
                    <input type="date" id="end_time" class="form-control" value="${recordVO.end_time}">
                </div>
            </div>

            <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${recordVO.mm_paihang_id}')">修改</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function saveRole(mm_emp_id){
    var mm_paihang_id = $("#mm_paihang_id").val();
    var is_del = $("#is_del").val();
    var top_num = $("#top_num").val();
    var end_time = $("#end_time").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:"/paihang/update.do",
      data:{
        "mm_paihang_id":mm_paihang_id,
        "is_del":is_del,
        "end_time":end_time,
        "top_num":top_num
      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=paihang/list";
        }else{
          var _case = {1:"修改失败"};
          alert(_case[data.code])
        }
      }
    });
  };
</script>


