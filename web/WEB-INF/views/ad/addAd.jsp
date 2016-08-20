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
      <li><a href="javascript:void(0)">广告管理</a></li>
      <li><a href="javascript:void(0)">添加广告</a></li>
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
          <span>添加广告</span>
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
        <h4 class="page-header">添加广告</h4>

        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-sm-2 control-label" >广告图片</label>
            <div class="col-sm-10 col-md-2">
              <img class="img-thumbnail" name="imagePath" src="" id="imageDiv"  style="cursor: pointer" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" ></label>
            <div class="col-sm-10">
              <input type="file" name="file" id="fileUpload" style="float: left;" />
              <input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-10 col-sm-offset-3">
              <font color="red">*为了实现最佳的展现效果，图片最佳尺寸：1000（宽）*360（高）</font>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">链接</label>
            <div class="col-sm-4">
              <input type="text" id="mm_ad_url" class="form-control" placeholder="链接" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">排序</label>
            <div class="col-sm-4">
              <input type="text" id="mm_ad_num" value="0" class="form-control" placeholder="排序 数字" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <%--<div class="form-group">--%>
            <%--<label class="col-sm-2 control-label">选择广告类型</label>--%>
            <%--<div class="col-sm-4">--%>
              <%--<select class="form-control" id="mm_ad_type">--%>
                <%--<option value="">--请选择广告展示位置--</option>--%>
                <%--<option value="0"  selected="selected">求购页面展示</option>--%>
                <%--<option value="2" >供应页面展示</option>--%>
                <%--<option value="1" >金牌榜页面展示</option>--%>
              <%--</select>--%>
            <%--</div>--%>
          <%--</div>--%>

          <div class="form-group">
            <label class="col-sm-2 control-label">省份</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_emp_provinceId" onchange="selectCitys()">
                <option value="">--选择省份--</option>
                <c:forEach items="${listProvinces}" var="e" varStatus="st">
                  <option value="${e.provinceID}" >${e.province}</option>
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
                  <option value="${e.cityID}" >${e.city}</option>
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
                  <option value="${e.areaID}" >${e.area}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveP()">添加</button>
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

    var mm_ad_url = $("#mm_ad_url").val();
    var mm_ad_num = $("#mm_ad_num").val();
//    var mm_ad_type = $("#mm_ad_type").val();

    var imagePath = $("img[name='imagePath']").attr("src");

    var mm_emp_provinceId = $("#mm_emp_provinceId").val();
    var mm_emp_cityId = $("#mm_emp_cityId").val();
    var mm_emp_countryId = $("#mm_emp_countryId").val();


    if(imagePath.replace(/\s/g, '')==''){
      alert("请选择图片文件");
      return;
    }
//
//    if(mm_ad_url.replace(/\s/g, '')==''){
//      alert("请输入链接");
//      return;
//    }
//    if(mm_ad_type.replace(/\s/g, '')==''){
//      alert("请选择广告类型");
//      return;
//    }

//    if(mm_emp_provinceId.replace(/\s/g, '') == ''){
//      alert("请选择省份");
//      return;
//    }
//    if(mm_emp_cityId.replace(/\s/g, '') == ''){
//      alert("请选择城市");
//      return;
//    }
//    if(mm_emp_countryId.replace(/\s/g, '') == ''){
//      alert("请选择县区");
//      return;
//    }

    $.ajax({
      cache: true,
      type: "POST",
      url:"/adObj/addAd.do",
      data:{"mm_ad_pic":imagePath,
        "mm_emp_provinceId":mm_emp_provinceId,
        "mm_emp_cityId":mm_emp_cityId,
        "mm_emp_countryId":mm_emp_countryId,
        "mm_ad_url":mm_ad_url,
        "mm_ad_num":mm_ad_num,
        "mm_ad_type":"0"
      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("执行成功");
          window.location.href = "#module=adObj/list";
        }else{
          alert("执行失败，请检查")
        }
      }
    });
  }
</script>
<script type="text/javascript">
  function uploadImage() {
    $.ajaxFileUpload(
            {
              url:"/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
              secureuri:false,//是否启用安全提交，默认为false
              fileElementId:'fileUpload',                        //文件选择框的id属性
              dataType: 'json',                                     //服务器返回的格式，可以是json, xml
              success: function (data, status)  //服务器成功响应处理函数
              {
                if(data.success) {
                  document.getElementById('imageDiv').src= data.data;
                } else {
                  if(data.code == 1) {
                    alert("上传图片失败");
                  } else if(data.code == 2) {
                    alert("上传图片格式只能为：jpg、png、gif、bmp、jpeg");
                  } else if(data.code == 3) {
                    alert("请选择上传图片");
                  }else {
                    alert("上传失败");
                  }
                }
              }
            }
    );
  }

  function deleteImage(e, node) {
    if(e.button == 2) {
      if(confirm("确定移除该图片吗？")) {
        $(node).remove();
      }
    }
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

