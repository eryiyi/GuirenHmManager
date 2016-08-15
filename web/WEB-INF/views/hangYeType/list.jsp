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
      <li><a href="javascript:void(0)">行业管理</a></li>
      <li><a href="javascript:void(0)">行业列表</a></li>
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
          <span>行业列表</span>
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
          <form class="form-inline">
            <input type="hidden" id="mm_emp_type" value="${mm_emp_type}" >

            <div class="form-group">
              <div class="col-sm-4">
                <input type="text" id="keywords" placeholder="行业名称" value="${query.keywords}" id="keywords" class="form-control"  data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
              </div>
            </div>

            <%--<div class="form-group">--%>

              <%--<select class="form-control" id="mm_hangye_fid">--%>
                <%--<option value="">--选择父行业--</option>--%>
                <%--<c:forEach items="${listTop}" var="e" varStatus="st">--%>
                  <%--<option value="${e.mm_hangye_id}" ${query.mm_hangye_fid==e.mm_hangye_id?'selected':''}>${e.mm_hangye_name}</option>--%>
                <%--</c:forEach>--%>
              <%--</select>--%>
            <%--</div>--%>

            <div class="form-group">
              <button type="submit" onclick="searchOrder()" class="btn form-control btn-warning btn-sm btn-block">查找</button>
            </div>
          </form>

        <table class="table">
          <thead>
          <tr>
            <th>行业名称</th>
            <%--<th>父级行业</th>--%>
            <th>操作</th>
            <%--<th>操作</th>--%>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list}" var="e" varStatus="st">
            <tr>
              <td>${e.mm_hangye_name}</td>
              <%--<td>${e.fName}</td>--%>
              <td>
                <a class="btn btn-default btn-sm" href="javascript:void (0)" onclick="editRole('${e.mm_hangye_id}')" role="button">编辑</a>
              </td>
              <%--<td>--%>
                <%--<a class="btn btn-default btn-sm" href="javascript:void (0)" onclick="deleteRole('${e.mm_level_id}')" role="button">删除</a>--%>
              <%--</td>--%>
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
    var keywords = $("#keywords").val();
//    var mm_hangye_fid = $("#mm_hangye_fid").val();
    var is_top = $("#is_top").val();
      window.location.href="#module=/hangYeTypeController/list&keywords="+keywords;
  }

  function editRole(_id){
    if(confirm("确定要编辑该行业么？")){
      $.ajax({
        type: "GET",
        data:{"mm_hangye_id":_id},
        url: "/hangYeTypeController/edit.do",
        success: function(response){
          $("#content").html(response);
        }
      });
    }
  }

  function deleteRole(_id){
    if(confirm("确定要删除该行业么？")){
      $.ajax({
        url:"/hangYeTypeController/delete.do",
        data:{"mm_hangye_id":_id},
        type:"POST",
        success:function(_data){
          var data = $.parseJSON(_data);
          if(data.success){
            alert("删除成功");
            window.location.href = "#module=hangYeTypeController/list";
          }else{
            var _case = {1:"删除失败"};
            alert(_case[data.code])
          }
        }
      });
    }
  }
</script>


