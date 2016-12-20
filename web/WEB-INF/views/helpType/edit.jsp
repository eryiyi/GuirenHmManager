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
      <li><a href="javascript:void(0)">服务类型</a></li>
      <li><a href="javascript:void(0)">修改服务类型</a></li>
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
          <span>修改服务类型</span>
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
        <h4 class="page-header">修改服务类型</h4>

        <form class="form-horizontal" role="form">
          <input type="hidden" value="${helpType.help_type_id}" id="help_type_id">


          <div class="form-group">
            <label class="col-sm-2 control-label">类型</label>
            <div class="col-sm-4">
              <input type="text" id="help_type_name" value="${helpType.help_type_name}" class="form-control" placeholder="类型" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">排序</label>
            <div class="col-sm-4">
              <input type="text" id="top_number"  value="${helpType.top_number}" class="form-control" placeholder="排序 数字" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">父类型</label>

            <div class="col-sm-4">
              <select class="populate placeholder" name="help_type_f_id" id="help_type_f_id">
                <option value="">-- 选择类别 --</option>
                <c:forEach items="${list}" var="s">
                  <option value="${s.help_type_id}" ${helpType.help_type_f_id==s.help_type_id?'selected':''}>${s.help_type_name}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveP()">修改</button>
              <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function saveP(){
    var help_type_id = $("#help_type_id").val();
    var help_type_name = $("#help_type_name").val();
    var top_number = $("#top_number").val();
    var help_type_f_id = $("#help_type_f_id").val();

    if(help_type_f_id.replace(/\s/g, '') == ''){
      help_type_f_id = '0';
    }

    if(help_type_name.replace(/\s/g, '') == ''){
      alert("类型名称不能为空");
      return;
    }

    if(top_number.replace(/\s/g, '') == ''){
      alert("排序数字不能为空");
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"/helpTypeController/edit.do",
      data:{"help_type_id":help_type_id, "help_type_name":help_type_name,"top_number":top_number,"help_type_f_id":help_type_f_id},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=helpTypeController/list" + "&_t=" + new Date().getTime();
        }else{
          alert("修改失败，请检查")
        }
      }
    });
  }
</script>
