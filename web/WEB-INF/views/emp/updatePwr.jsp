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
      <li><a href="javascript:void (0);">会员管理</a></li>
      <li><a href="javascript:void (0);">更改密码</a></li>
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
          <span>更改密码</span>
        </div>
      </div>
      <div class="box-content">
        <%--<h4 class="page-header">会员详情</h4>--%>
        <form class="form-horizontal" role="form">
          <input type="hidden" value="${empVO.mm_emp_id}" id="mm_emp_id">
          <input type="hidden" value="${empVO.mm_emp_mobile}" id="mm_emp_mobile">
          <div class="form-group">
            <label class="col-sm-2 control-label">用户名</label>
              <div class="col-sm-4">
                  <input type="text" id="mm_emp_nickname"  readonly="true" class="form-control" value="${empVO.mm_emp_nickname}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
              </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">用户密码</label>
            <div class="col-sm-4">
              <input type="text" id="mm_emp_password" class="form-control" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${empVO.mm_emp_id}')">确定修改</button>
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
    var mm_emp_mobile = $("#mm_emp_mobile").val();
    var mm_emp_nickname = $("#mm_emp_nickname").val();
    var mm_emp_password = $("#mm_emp_password").val();

    if(mm_emp_password.replace(/\s/g, '') == ''){
        alert("密码不能为空");
        return;
    }
    if(mm_emp_password.length<6 || mm_emp_password.length>18){
      alert("密码长度至少6位,最多18位");
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"/emp/updateEmpPwr.do",
      data:{
        "mm_emp_password":mm_emp_password,
        "mm_emp_mobile":mm_emp_mobile

      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=emp/list";
        }else{
          var _case = {1:"修改失败"};
          alert(_case[data.code])
        }
      }
    });
  };

  <%--function selectColleges(){--%>
    <%--var citys =${listCitysAll};--%>
    <%--var province = $("#mm_emp_provinceId").val();--%>
    <%--var ret = '';--%>
    <%--for(var i= citys.length-1; i>=0; i-- ){--%>
      <%--if(citys[i].father==province){--%>
        <%--ret += "<option value='"+citys[i].cityID+"'>"+citys[i].city+"</option>";--%>
      <%--}--%>
    <%--}--%>
    <%--$("#mm_emp_cityId").html(ret);--%>
  <%--};--%>

</script>


