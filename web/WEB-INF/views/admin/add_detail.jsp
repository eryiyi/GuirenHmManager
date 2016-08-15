<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ page import="com.liangxunwang.unimanager.model.CityObj" %>
<div class="row">
  <div id="breadcrumb" class="col-xs-12">
    <a href="#" class="show-sidebar">
      <i class="fa fa-bars"></i>
    </a>
    <ol class="breadcrumb pull-left">
      <li><a href="javascript:void(0)"  onclick="toPage('mainPage','')">主页</a></li>
      <li><a href="javascript:void (0);">管理员</a></li>
      <li><a href="javascript:void (0);">添加管理员-会员详情</a></li>
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
          <span>添加管理员-会员详情</span>
        </div>
      </div>
      <div class="box-content">
        <%--<h4 class="page-header">会员详情</h4>--%>
        <form class="form-horizontal" role="form">
          <input type="hidden" value="${empVO.mm_emp_id}" id="mm_manager_id">
          <input type="hidden" value="${empVO.mm_emp_password}" id="mm_manager_password">
          <div class="form-group">
            <label class="col-sm-2 control-label">用户名</label>
              <div class="col-sm-4">
                  <input type="text" id="mm_manager_nickname" readonly="true" class="form-control" value="${empVO.mm_emp_nickname}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
              </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">用户手机号</label>
            <div class="col-sm-4">
              <input type="text" id="mm_manager_mobile" readonly="true"  class="form-control" value="${empVO.mm_emp_mobile}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-2 control-label">公司名称</label>
            <div class="col-sm-4">
              <input type="text" id="mm_emp_company" readonly="true"  class="form-control" value="${empVO.mm_emp_company}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">注册日期</label>
            <div class="col-sm-4">
              <input type="text" id="mm_emp_regtime" readonly="true" class="form-control" value="${empVO.mm_emp_regtime}" >
            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-2 control-label">管理的省份</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_provinceId" onchange="selectCitys()">
                <option value="">--选择省份--</option>
                <c:forEach items="${listProvinces}" var="e" varStatus="st">
                  <option value="${e.provinceID}"  >${e.province}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">管理的城市</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_cityId" onchange="selectCountrys()">
                <option value="">--选择城市--</option>
                <%--<c:forEach items="${listCitys}" var="e" varStatus="st">--%>
                  <%--<option value="${e.cityID}"  ${empVO.mm_emp_cityId==e.cityID?'selected':''}>${e.city}</option>--%>
                <%--</c:forEach>--%>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">管理的县区</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_countryId" >
                <option value="">--选择县区--</option>
                <%--<c:forEach items="${listsCountry}" var="e" varStatus="st">--%>
                  <%--<option value="${e.areaID}"  ${empVO.mm_emp_countryId==e.areaID?'selected':''}>${e.area}</option>--%>
                <%--</c:forEach>--%>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">管理员类型</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_manager_type">
                <option value="">--选择管理员类型--</option>
                <option value="1" >县级</option>
                <option value="2" >市级</option>
                <option value="3" >省级</option>
                <option value="4" >全国</option>
                <option value="0" >顶级</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">选择角色</label>
            <div class="col-sm-4">
              <select class="form-control" id="permissions">
                <option value="">--选择选择角色--</option>
                <c:forEach items="${roles}" var="e" varStatus="st">
                  <option value="${e.id}">${e.name}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${empVO.mm_emp_id}')">添加</button>
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

    var mm_manager_id = $("#mm_manager_id").val();
    var mm_manager_mobile = $("#mm_manager_mobile").val();
    var mm_manager_nickname = $("#mm_manager_nickname").val();
    var mm_manager_password = $("#mm_manager_password").val();
    var mm_manager_type = $("#mm_manager_type").val();
    var mm_manager_area_uuid;
    if(mm_manager_type == '0'){
      mm_manager_area_uuid='';
    }
    if(mm_manager_type == '1'){
      mm_manager_area_uuid= $("#mm_emp_countryId").val();
    }
    if(mm_manager_type == '2'){
      mm_manager_area_uuid= $("#mm_emp_cityId").val();
    }
    if(mm_manager_type == '3'){
      mm_manager_area_uuid= $("#mm_emp_provinceId").val();
    }
    if(mm_manager_type == '4'){
      mm_manager_area_uuid='';
    }
    var mm_manager_is_use = '1';
    var permissions = $("#permissions").val();

    if(mm_manager_type.replace(/\s/g, '') == ''){
      alert("请选择管理员类型");
      return;
    }
    if(mm_manager_area_uuid.replace(/\s/g, '') == ''){
      alert("请选择要管理的区域");
      return;
    }
    if(permissions.replace(/\s/g, '') == ''){
      alert("请选择角色");
      return;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"/admin/addAdmin.do",
      data:{"mm_manager_id":mm_manager_id,
        "mm_manager_mobile":mm_manager_mobile,
        "mm_manager_nickname":mm_manager_nickname,
        "mm_manager_password":mm_manager_password,
        "mm_manager_type":mm_manager_type,
        "mm_manager_area_uuid":mm_manager_area_uuid,
        "mm_manager_is_use":mm_manager_is_use,
        "permissions":permissions
      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("添加成功");
          window.location.href = "#module=/admin/list";
        }else{
          var _case = {1:"添加失败,该用户如果已经是管理员，不能重复设置"};
          alert(_case[data.code])
        }
      }
    });
  };

  function selectCitys(){
    var citys = ${listCitysAll};
    var province = $("#mm_emp_provinceId").val();
    var ret = '';
    for(var i= citys.length-1; i>=0; i-- ){
      if(citys[i].father==province){
        ret += "<option value='"+citys[i].cityID+"'>"+citys[i].city+"</option>";
      }
    }
    $("#mm_emp_cityId").html(ret);
  };

  function selectCountrys(){
    var countrys = ${listsCountryAll};
    var city = $("#mm_emp_cityId").val();
    var ret = '';
    for(var i= countrys.length-1; i>=0; i-- ){
      if(countrys[i].father==city){
        ret += "<option value='"+countrys[i].areaID+"'>"+countrys[i].area+"</option>";
      }
    }
    $("#mm_emp_countryId").html(ret);
  };


</script>


