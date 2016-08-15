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
      <li><a href="javascript:void(0)">地区管理</a></li>
      <li><a href="javascript:void(0)">省份列表</a></li>
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
          <span>省份列表</span>
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
        <%--<form class="form-inline">--%>
          <%--<div class="form-group">--%>
            <%--<select class="form-control w12" id="is_use">--%>
              <%--<option value="">--显示隐藏状态--</option>--%>
              <%--<option value="0" ${query.is_use=='0'?'selected':''}>隐藏</option>--%>
              <%--<option value="1" ${query.is_use=='1'?'selected':''}>显示</option>--%>
            <%--</select>--%>
          <%--</div>--%>
          <%--<button type="submit" onclick="searchOrder()" class="btn form-control btn-warning btn-sm btn-block">查找</button>--%>
        <%--</form>--%>

        <table class="table">
          <thead>
          <tr>
            <th>省份ID</th>
            <th>省份UUID</th>
            <th>省份</th>
            <th>信息数量</th>
            <th>是否显示</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list}" var="e" varStatus="st">
            <tr>
              <td>${e.id}</td>
              <td>${e.provinceID}</td>
              <td>${e.province}</td>
              <td>${e.msgNum}</td>
              <c:if test="${e.is_use=='0'}"><td>隐藏</td></c:if>
              <c:if test="${e.is_use=='1'}"><td>显示</td></c:if>
              <td>
                <a class="btn btn-default btn-sm" href="#module=/province/edit&id=${e.id}" role="button">设置</a>
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
  function searchOrder(){
    var is_use = $("#is_use").val();
      window.location.href="#module=/province/list&is_use="+is_use;
  }
</script>