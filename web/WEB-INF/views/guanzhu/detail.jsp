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
      <li><a href="javascript:void (0);">关注管理</a></li>
      <li><a href="javascript:void (0);">关注详情</a></li>
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
          <span>关注详情</span>
        </div>
      </div>
      <div class="box-content">
        <%--<h4 class="page-header">会员详情</h4>--%>
        <form class="form-horizontal" role="form">
          <input type="hidden" value="${guanzhuAreaObjVO.mm_guanzhu_id}" id="mm_guanzhu_id">

            <div class="form-group">
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-lg-8">
                  <div>${guanzhuAreaObjVO.mm_emp_nickname}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">申请区域</label>
                <div class="col-lg-8">
                    <div id="area_name">${guanzhuAreaObjVO.area_name}</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">时间戳</label>
                <div class="col-lg-8">
                    <%--<div id="reg_time">${um:format(guanzhuAreaObjVO.reg_time, "yyyy-MM-dd HH:mm:ss")}</div>--%>
                    <div id="reg_time">${guanzhuAreaObjVO.reg_time}</div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">是否审核</label>
                <div class="col-sm-4">
                    <select class="form-control" id="ischeck">
                        <option value="">--请选择--</option>
                        <option value="0" ${guanzhuAreaObjVO.ischeck=='0'?'selected':''}>未审核</option>
                        <option value="1" ${guanzhuAreaObjVO.ischeck=='1'?'selected':''}>通过</option>
                        <option value="2" ${guanzhuAreaObjVO.ischeck=='2'?'selected':''}>不通过</option>
                    </select>
                </div>
            </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${guanzhuAreaObjVO.mm_guanzhu_id}')">修改</button>
                <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

  function saveRole(mm_guanzhu_id){
    var mm_guanzhu_id = $("#mm_guanzhu_id").val();
    var ischeck = $("#ischeck").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:"/guanzhu/update.do",
      data:{
        "mm_guanzhu_id":mm_guanzhu_id,
        "ischeck":ischeck
      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=guanzhu/list&page=1";
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


