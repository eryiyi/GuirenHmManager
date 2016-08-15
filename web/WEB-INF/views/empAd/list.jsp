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
      <li><a href="javascript:void(0)">会员管理</a></li>
      <li><a href="javascript:void(0)">轮播图</a></li>
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
          <span>轮播图</span>
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
            <button type="submit" onclick="searchOrder('${mm_emp_id}')" class="btn btn-default btn-sm">添加</button>
        <table class="table">
          <thead>
          <tr>
            <th>会员姓名</th>
            <th>轮播图地址</th>
            <th>轮播图链接</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list}" var="e" varStatus="st">
            <tr>
              <td>${e.mm_emp_nickname}</td>
              <td>${e.mm_emp_ad_pic}</td>
              <td>${e.mm_emp_ad_url}</td>
              <td>
                <a class="btn btn-default btn-sm" href="javascript:void (0)" onclick="deleteRole('${e.mm_emp_ad_id}','${mm_emp_id}')" role="button">删除</a>
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

  function deleteRole(_id,mm_emp_id){
    if(confirm("确定要删除该轮播图么？")){
      $.ajax({
        url:"/empAd/delete.do",
        data:{"mm_emp_ad_id":_id},
        type:"POST",
        success:function(_data){
          var data = $.parseJSON(_data);
          if(data.success){
            alert("删除成功");
            window.location.href = "#module=empAd/list&mm_emp_id="+mm_emp_id;;
          }else{
            var _case = {1:"删除失败"};
            alert(_case[data.code])
          }
        }
      });
    }
  }

  function searchOrder(mm_emp_id){
    window.location.href = "#module=empAd/toAdd&mm_emp_id=" +mm_emp_id;
  }
</script>


