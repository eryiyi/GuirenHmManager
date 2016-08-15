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
      <li><a href="javascript:void (0);">信息管理</a></li>
      <li><a href="javascript:void (0);">信息详情</a></li>
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
          <span>信息详情</span>
        </div>
      </div>
      <div class="box-content">
        <%--<h4 class="page-header">会员详情</h4>--%>
        <form class="form-horizontal" role="form">
          <input type="hidden" value="${recordVO.mm_msg_id}" id="mm_msg_id">
            <div class="form-group">
                <label class="col-sm-2 control-label">信息类型</label>
                <div class="col-lg-8">
                    <c:if test="${mm_msg_type == 0}"><div id="mm_msg_type">文字</div></c:if>
                    <c:if test="${mm_msg_type != 1}"><div id="mm_msg_type">图片</div></c:if>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">内容</label>
                <div class="col-lg-8">
                    <div id="mm_msg_content">${recordVO.mm_msg_content}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">信息图片</label>
                <div class="col-lg-8">
                    <div id="mm_msg_picurl">${recordVO.mm_msg_picurl}</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">时间戳</label>
                <div class="col-lg-8">
                    <div id="dateline">${um:format(recordVO.dateline, "yyyy-MM-dd HH:mm:ss")}</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">是否置顶</label>
                <div class="col-sm-4">
                    <select class="form-control" id="is_top">
                        <option value="">--请选择--</option>
                        <option value="0" ${recordVO.is_top=='0'?'selected':''}>否</option>
                        <option value="1" ${recordVO.is_top=='1'?'selected':''}>是</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">置顶数字</label>
                <div class="col-sm-4">
                    <input type="text" id="top_num" class="form-control" placeholder="置顶数字越大 越靠前" value="${recordVO.top_num}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                </div>
            </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${recordVO.mm_msg_id}')">修改</button>
                <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

  function saveRole(mm_emp_id){
    var mm_msg_id = $("#mm_msg_id").val();
    var is_top = $("#is_top").val();
    var top_num = $("#top_num").val();

    $.ajax({
      cache: true,
      type: "POST",
      url:"/record/update.do",
      data:{
        "mm_msg_id":mm_msg_id,
        "is_top":is_top,
        "top_num":top_num
      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=record/listQiugou";
        }else{
          var _case = {1:"修改失败"};
          alert(_case[data.code])
        }
      }
    });
  };

</script>


