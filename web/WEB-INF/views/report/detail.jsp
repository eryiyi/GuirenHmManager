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
      <li><a href="javascript:void (0);">举报管理</a></li>
      <li><a href="javascript:void (0);">举报详情</a></li>
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
          <span>举报详情</span>
        </div>
      </div>
      <div class="box-content">
        <%--<h4 class="page-header">会员详情</h4>--%>
        <form class="form-horizontal" role="form">
          <input type="hidden" value="${reportVO.mm_report_id}" id="mm_report_id">


            <div class="form-group">
                <label class="col-sm-2 control-label">举报人</label>
                <div class="col-lg-8">
                  <div>${reportVO.empName}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">被举报人</label>
                <div class="col-lg-8">
                    <div >${reportVO.empNameReport}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">举报详细</label>
                <div class="col-lg-8">
                    <div id="mm_report_content">${reportVO.mm_report_content}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">被举报信息</label>
                <div class="col-lg-8">
                    <div >${reportVO.msgCont}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">举报时间</label>
                <div class="col-lg-8">
                    <div>${reportVO.dateline}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">是否处理</label>
                <div class="col-lg-8">
                    <c:if test="${reportVO.is_use == 0}"><div >未处理</div></c:if>
                    <c:if test="${reportVO.is_use == 1}"><div >已处理</div></c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">处理人</label>
                <div class="col-lg-8">
                    <div >${reportVO.managerName}</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">处理时间</label>
                <div class="col-lg-8">
                    <div >${reportVO.end_dateline}</div>
                </div>
            </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${reportVO.mm_report_id}')">已阅读</button>
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
    var mm_report_id = $("#mm_report_id").val();

    $.ajax({
      cache: true,
      type: "POST",
      url:"/report/update.do",
      data:{
        "mm_report_id":mm_report_id
      },
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("处理举报信息成功");
          window.location.href = "#module=/report/list";
        }else{
          var _case = {1:"处理举报信息失败"};
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


