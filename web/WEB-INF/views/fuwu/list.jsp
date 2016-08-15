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
      <li><a href="javascript:void(0)">服务管理</a></li>
      <li><a href="javascript:void(0)">服务列表</a></li>
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
    <div class="box ui-draggable ui-droppable">
      <div class="box-header">
        <div class="box-name ui-draggable-handle">
          <i class="fa fa-table"></i>
          <span>服务列表</span>
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
        <%--<p>For basic styling add the base class <code>.table</code> to any <code>&lt;table&gt;</code>.</p>--%>
        <table class="table">
          <thead>
          <tr>
            <th>服务名称</th>
            <th>服务电话</th>
            <th>服务描述</th>
            <th>服务类型</th>
            <th>lat</th>
            <th>lng</th>
            <th>操作</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list}" var="e" varStatus="st">
            <tr>
              <td>${e.mm_fuwu_nickname}</td>
              <td>${e.mm_fuwu_tel}</td>
              <td>${e.mm_fuwu_content}</td>
              <td>
                <c:if test="${e.mm_fuwu_type=='0'}">苗木商店</c:if>
                <c:if test="${e.mm_fuwu_type=='1'}">装车工人</c:if>
                <c:if test="${e.mm_fuwu_type=='2'}">物流中心</c:if>
                <c:if test="${e.mm_fuwu_type=='3'}">嫁接团队</c:if>
                <c:if test="${e.mm_fuwu_type=='4'}">吊车服务</c:if>
              </td>
              <td>${e.lat}</td>
              <td>${e.lng}</td>
              <td>
                <a class="btn btn-default btn-sm" href="javascript:void (0)" onclick="editRole('${e.mm_fuwu_id}')" role="button">编辑</a>
              </td>
              <td>
                <a class="btn btn-default btn-sm" href="javascript:void (0)" onclick="deleteRole('${e.mm_fuwu_id}')" role="button">删除</a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">

  function editRole(_id){
    if(confirm("确定要编辑该服务么？")){
      $.ajax({
        type: "GET",
        data:{"typeId":_id},
        url: "/fuwu/edit.do",
        success: function(response){
          $("#content").html(response);
        }
      });
    }
  }

  function deleteRole(_id){
    if(confirm("确定要删除该服务么？")){
      $.ajax({
        url:"/fuwu/delete.do",
        data:{"mm_fuwu_id":_id},
        type:"POST",
        success:function(_data){
          var data = $.parseJSON(_data);
          if(data.success){
            alert("删除成功");
            window.location.href = "#module=fuwu/list";
          }else{
            var _case = {1:"删除失败"};
            alert(_case[data.code])
          }
        }
      });
    }
  }
</script>


