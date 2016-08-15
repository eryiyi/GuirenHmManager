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
      <li><a href="javascript:void(0)">县区管理</a></li>
      <li><a href="javascript:void(0)">修改县区</a></li>
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
          <span>修改县区</span>
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
        <h4 class="page-header">修改县区</h4>
        <form class="form-horizontal" role="form">
          <input type="hidden" id="id" value="${provinceObj.id}">
          <div class="form-group">
            <label class="col-sm-2 control-label">县区</label>
            <div class="col-sm-4">
              <input type="text" id="area" readonly="true" value ="${provinceObj.area}" class="form-control" placeholder="县区名称" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">置顶数字</label>
            <div class="col-sm-4">
              <input type="text" id="topnum" value ="${provinceObj.topnum}"  class="form-control" placeholder="置顶数字，越大越靠前" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">控制显示隐藏</label>
            <div class="col-sm-4">
              <select class="form-control" id="is_use">
                <option value="">--请选择--</option>
                <option value="0" ${provinceObj.is_use=='0'?'selected':''}>隐藏</option>
                <option value="1" ${provinceObj.is_use=='1'?'selected':''}>显示</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveP()">确定</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function saveP(){
    var id = $("#id").val();
    var topnum = $("#topnum").val();
    var is_use = $("#is_use").val();

    if(topnum.replace(/\s/g, '')==''){
      alert("请输入正确的置顶数字");
      return;
    }
    if(is_use.replace(/\s/g, '')==''){
      alert("请输入正确的等级描述");
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"/country/editCountry.do",
      data:{"topnum":topnum, "is_use":is_use, "id":id},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("执行成功");
          window.location.href = "#module=country/list";
        }else{
          alert("执行失败，请检查")
        }
      }
    });
  }
</script>


