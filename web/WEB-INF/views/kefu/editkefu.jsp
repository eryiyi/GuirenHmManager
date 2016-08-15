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
      <li><a href="javascript:void(0)">客服电话</a></li>
      <li><a href="javascript:void(0)">编辑客服</a></li>
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
          <span>编辑客服</span>
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
        <h4 class="page-header">编辑客服</h4>
        <form class="form-horizontal" role="form">
          <input type="hidden" id="mm_tel_id" value="${levelObj.mm_tel_id}">

          <div class="form-group">
            <label class="col-sm-2 control-label">客服电话</label>
            <div class="col-sm-4">
              <input type="text" id="mm_tel" value ="${levelObj.mm_tel}"  class="form-control" placeholder="服务电话" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">省份</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_provinceId"  onchange="selectCitys()">
                <option value="">--选择省份--</option>
                <c:forEach items="${listProvinces}" var="e" varStatus="st">
                  <option value="${e.provinceID}"  ${empVO.mm_emp_provinceId==e.provinceID?'selected':''}>${e.province}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">城市</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_cityId" onchange="selectCountrys()">
                <option value="">--选择城市--</option>
                <c:forEach items="${listCitys}" var="e" varStatus="st">
                  <option value="${e.cityID}"  ${empVO.mm_emp_cityId==e.cityID?'selected':''}>${e.city}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">县区</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_countryId" >
                <option value="">--选择县区--</option>
                <c:forEach items="${listsCountry}" var="e" varStatus="st">
                  <option value="${e.areaID}"  ${empVO.mm_emp_countryId==e.areaID?'selected':''}>${e.area}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveP()">确定</button>
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
    var mm_tel_id = $("#mm_tel_id").val();
    var mm_tel = $("#mm_tel").val();

    var mm_emp_provinceId = $("#mm_emp_provinceId").val();
    var mm_emp_cityId = $("#mm_emp_cityId").val();
    var mm_emp_countryId = $("#mm_emp_countryId").val();


    if(mm_tel.replace(/\s/g, '')==''){
      alert("请输入正确的客服电话");
      return;
    }

//    if(mm_emp_provinceId.replace(/\s/g, '')==''){
//      alert("请选择省份");
//      return;
//    } if(mm_emp_cityId.replace(/\s/g, '')==''){
//      alert("请选择城市");
//      return;
//    } if(mm_emp_countryId.replace(/\s/g, '')==''){
//      alert("请选择地区");
//      return;
//    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"/kefu/editKefu.do",
      data:{"mm_emp_provinceId":mm_emp_provinceId, "mm_emp_cityId":mm_emp_cityId, "mm_emp_countryId":mm_emp_countryId,"mm_tel_id":mm_tel_id, "mm_tel":mm_tel},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("执行成功");
          window.location.href = "#module=kefu/list";
        }else{
          alert("执行失败，请检查")
        }
      }
    });
  }
</script>