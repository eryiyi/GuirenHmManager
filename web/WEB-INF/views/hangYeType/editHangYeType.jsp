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
      <li><a href="javascript:void(0)">行业管理</a></li>
      <li><a href="javascript:void(0)">修改行业</a></li>
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
          <span>修改行业</span>
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
        <h4 class="page-header">修改行业</h4>
        <form class="form-horizontal" role="form">
          <input type="hidden" id="mm_hangye_id" name="mm_hangye_id" value="${hangYeType.mm_hangye_id}">
          <div class="form-group">
            <label class="col-sm-2 control-label">行业名称</label>
            <div class="col-sm-4">
              <input type="text" id="mm_hangye_name" value="${hangYeType.mm_hangye_name}" name="mm_hangye_name" class="form-control" placeholder="行业名称" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <%--<div class="form-group">--%>
            <%--<label class="col-sm-2 control-label">所属父行业</label>--%>
            <%--<div class="col-sm-4">--%>
              <%--<select class="form-control" id="mm_hangye_fid">--%>
                <%--<option value="">--选择父行业--</option>--%>
                <%--<c:forEach items="${list}" var="e" varStatus="st">--%>
                  <%--<option value="${e.mm_hangye_id}" ${hangYeType.mm_hangye_fid==e.mm_hangye_id?'selected':''}>${e.mm_hangye_name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</div>--%>
          <%--</div>--%>

          <div class="form-group">
            <label class="col-sm-2 control-label">置顶数字</label>
            <div class="col-sm-4">
              <input type="text" id="top_num" value="${hangYeType.top_num}" name="top_num" class="form-control" placeholder="置顶数字，数字越大越靠前" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveP()">修改</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function saveP(){
    var mm_hangye_id = $("#mm_hangye_id").val();
    var mm_hangye_name = $("#mm_hangye_name").val();
//    var mm_hangye_fid = $("#mm_hangye_fid").val();
    var top_num = $("#top_num").val();

    if(mm_hangye_name.replace(/\s/g, '')==''){
      alert("请输入正确的行业名称");
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"/hangYeTypeController/editHangYeType.do",
      data:{"mm_hangye_name":mm_hangye_name, "mm_hangye_fid":"0", "top_num":top_num, "mm_hangye_id":mm_hangye_id},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("执行成功");
          window.location.href = "#module=hangYeTypeController/list";
        }else{
          alert("执行失败，请检查")
        }
      }
    });
  }
</script>


