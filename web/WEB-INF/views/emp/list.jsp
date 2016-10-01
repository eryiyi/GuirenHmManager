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
      <li><a href="javascript:void(0)">会员列表</a></li>
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
          <span>会员</span>
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
      <!-- style -->
      <style>
          .w12{
              max-width: 12rem;
          }
      </style>
      <!-- style -->
        <form class="form-inline">

          <div class="form-group">
            <div class="col-sm-4">
              <input type="text" id="keywords" placeholder="用户名 手机号" value="${query.keyword}" id="keywords" class="form-control"  data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>


          <div class="form-group">
            <select class="form-control w12" id="ischeck">
              <option value="">--审核状态--</option>
              <option value="0" ${query.ischeck=='0'?'selected':''}>未审核</option>
              <option value="1" ${query.ischeck=='1'?'selected':''}>已审核</option>
              <option value="2" ${query.ischeck=='2'?'selected':''}>未通过</option>
            </select>
          </div>

          <%--<div class="form-group">--%>
            <%--<select class="form-control w12" id="mm_emp_regtime">--%>
              <%--<option value="">--注册时间--</option>--%>
              <%--<option value="0" ${query.mm_emp_regtime=='0'?'selected':''}>不限</option>--%>
              <%--<option value="1" ${query.mm_emp_regtime=='1'?'selected':''}>今日注册</option>--%>
            <%--</select>--%>
          <%--</div>--%>

          <div class="form-group">
            <button type="submit" onclick="searchOrder('1')" class="btn form-control btn-warning btn-sm btn-block">查找</button>
          </div>
          </form>

            <%--<form action="" class="form">--%>
            <%--<div class="form-group">--%>
              <%--<div class="col-md-2 col-lg-2">--%>
                <%--<button type="button" onclick="Daochu_Select()" class="btn w12 form-control btn-block btn-danger btn-sm">批量导出Excel</button>--%>
              <%--</div>--%>
              <%--<div class="col-md-2 col-lg-2">--%>
                <%--<button type="button" onclick="P_Weishen_Select('0')" class="btn w12 form-control btn-block btn-danger btn-sm">批量未审</button>--%>
              <%--</div>--%>
              <%--<div class="col-md-2 col-lg-2">--%>
                <%--<button type="button" onclick="P_Weishen_Select('1')" class="btn w12 form-control btn-block btn-danger btn-sm">批量审核</button>--%>
              <%--</div>--%>
              <%--<div class="col-md-2 col-lg-2">--%>
                <%--<button type="button" onclick="P_Weishen_Select('2')" class="btn w12 form-control btn-block btn-danger btn-sm">批量不通过</button>--%>
              <%--</div>--%>
            <%--</div>--%>
            <%--</form>--%>
          <%--<form action="" class="form">--%>
            <%--<div class="form-group">--%>
              <%--<div class="col-md-2 col-lg-2">--%>
                <%--<button type="button" onclick="P_Login_Select('1')" class="btn w12 form-control btn-block btn-danger btn-sm">批量禁止登陆</button>--%>
              <%--</div>--%>
              <%--<div class="col-md-2 col-lg-2">--%>
                <%--<button type="button" onclick="P_Login_Select('0')" class="btn w12 form-control btn-block btn-danger btn-sm">批量允许登陆</button>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</form>--%>


        <table class="table">
          <thead>
          <tr>
            <%--<th>全选<input type="checkbox" name="allmails" onclick="checkAll()"></th>--%>
              <th>#</th>
            <th>姓名</th>
            <th>电话</th>
            <th>头像</th>
            <th>公司</th>
            <th>省</th>
            <th>市</th>
            <th>县</th>
            <th>邮箱</th>
            <th>行业</th>
            <th>注册时间</th>
            <th>审核状态</th>
            <th>操作</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${list}" var="e" varStatus="st">
            <tr>
              <%--<td><input type="checkbox" id="${e.mm_emp_id}" name="checkbox_one"></td>--%>
                <td>${st.index+1}</td>
              <td>${e.mm_emp_nickname}</td>
              <td>${e.mm_emp_mobile}</td>
              <td><img src="${e.mm_emp_cover}" style="width: 60px;height: 60px;"></td>
                <td>${e.mm_emp_company}</td>
                <td>${e.provinceName}</td>
                <td>${e.cityName}</td>
                <td>${e.areaName}</td>
                <td>${e.mm_emp_email}</td>
                <td>${e.mm_hangye_name}</td>
                <td>${e.mm_emp_regtime}</td>
              <td>
                <c:if test="${e.ischeck=='0'}">未审核</c:if>
                <c:if test="${e.ischeck=='1'}">已审核</c:if>
                <c:if test="${e.ischeck=='2'}">未通过</c:if>
              </td>
              <td>
                <a class="btn btn-default btn-sm" href="#module=/emp/toUpdatePwr&mm_emp_id=${e.mm_emp_id}" role="button">修改密码</a>
              </td>
              <td>
                <a class="btn btn-default btn-sm" href="#module=/emp/detail&mm_emp_id=${e.mm_emp_id}" role="button">编辑</a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>

          <div style="margin-top: 20px;border-top: 1px solid #dedede;padding-bottom:15px; height: 50px">
            <span style="line-height:28px;margin-top:25px;padding-left:10px; float: left">共${page.count}条/${page.pageCount}页</span>
            <ul class="pagination" style="padding-left:100px; float: right">
              <li>
                <a style="margin-right:20px">每页显示&nbsp;<select name="size" id="size" onchange="nextPage('1')">
                  <option value="10" ${query.size==10?'selected':''}>10</option>
                  <option value="20" ${query.size==20?'selected':''}>20</option>
                  <option value="30" ${query.size==30?'selected':''}>30</option>
                  <option value="100" ${query.size==100?'selected':''}>100</option>
                </select>&nbsp;条</a>
              </li>
              <c:choose >
                <c:when test="${page.page == 1}">
                  <li><a href="javascript:void(0)">首页</a></li>
                  <li><a href="javascript:void(0)"><span class="left">《</span></a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="javascript:void(0);" onclick="nextPage('1')">首页</a></li>
                  <li><a href="javascript:void(0);" onclick="nextPage('${page.page-1}')"><span class="left">《</span></a></li>
                </c:otherwise>
              </c:choose>
              <li><a style="height: 30px; width: 100px">第<input style="width: 40px;height:20px;" type="text" id="index" name="index" onkeyup="searchIndex(event)" value="${page.page}"/> 页</a></li>

              <c:choose>
                <c:when test="${page.page == page.pageCount}">
                  <li><a href="javascript:void(0)"><span class="right">》</span></a></li>
                  <li><a href="javascript:void(0)">末页</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="javascript:void(0);" onclick="nextPage('${page.page+1}')"><span class="right">》</span></a></li>
                  <li><a href="javascript:void(0);" onclick="nextPage('${page.pageCount}')">末页</a></li>
                </c:otherwise>
              </c:choose>
            </ul>
          </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  function searchIndex(e){
    if(e.keyCode != 13) return;
    var _index = $("#index").val();
    var size = getCookie("contract_size");
    var keywords = $("#keywords").val();
    var ischeck = $("#ischeck").val();
//    var mm_emp_regtime = $("#mm_emp_regtime").val();
    if(_index <= ${page.pageCount} && _index >= 1){
      alert("searchIndex");
      window.location.href="#module=/emp/list&page="+_index+"&size="+size+"&keyword="+keywords
      +"&mm_emp_regtime="+mm_emp_regtime
      +"&ischeck="+ischeck + "&_t=" + new Date().getTime();
    }else{
      alert("请输入1-${page.pageCount}的页码数");
    }
  }
  function nextPage(_page) {
    var page = parseInt(_page);
    var size = $("#size").val();
    var keywords = $("#keywords").val();
    var ischeck = $("#ischeck").val();
    var mm_emp_regtime = $("#mm_emp_regtime").val();

    addCookie("contract_size", size, 36);
    if ((page <= ${page.pageCount} && page >= 1)) {
      window.location.href="#module=/emp/list&page="+page+"&size="+size+"&keyword="+keywords
      +"&mm_emp_regtime="+mm_emp_regtime
      +"&ischeck="+ischeck + "&_t=" + new Date().getTime();
    } else {
      alert("请输入1-${page.pageCount}的页码数");
    }
  }

  function searchOrder(_page){
    var page = parseInt(_page);
    var size = $("#size").val();
    var keywords = $("#keywords").val();
    var ischeck = $("#ischeck").val();
    var mm_emp_regtime = $("#mm_emp_regtime").val();

    addCookie("contract_size", size, 36);
    if ((page <= ${page.pageCount} && page >= 1)) {
      window.location.href="#module=/emp/list&page="+page+"&size="+size+"&keyword="+keywords
      +"&mm_emp_regtime="+mm_emp_regtime
      +"&ischeck="+ischeck + "&_t=" + new Date().getTime();
    } else {
      alert("请输入1-${page.pageCount}的页码数");
    }
  }

  function checkAll() {
    var all = document.getElementsByName("allmails")[0];
    var select = document.getElementsByName("checkbox_one");
    if (all.checked) {
      for (var i = 0; i < select.length; i++) {
        select[i].checked = true;
      }
    } else {
      for (var i = 0; i < select.length; i++) {
        select[i].checked = false;
      }
    }
  }

  function Daochu_Select(){
    var select_id = '';
    var select = document.getElementsByName("checkbox_one");
    for (var i = 0; i < select.length; i++) {
      if(select[i].checked == true){
        select_id = select_id+select[i].id +',';
      }
    }
    if(select_id == ''){
      alert('请选择数据！');
      return
    }else{
      if(confirm("确定要导出所选择的会员吗？")){
        $.ajax({
          url:"/emp/daochuAll.do",
          data:{"ids":select_id},
          type:"POST",
          success:function(_data){
            var data = $.parseJSON(_data);
            if(data.success){
              window.location.href = "/upload"+data.data ;//这样就可以弹出下载对话框了
            }else{
              var _case = {1:"导出失败"};
              alert(_case[data.code])
            }
          }
        });
      }
    }
  }

  function P_Weishen_Select(_type){
    //
    var select_id = '';
    var select = document.getElementsByName("checkbox_one");
    for (var i = 0; i < select.length; i++) {
      if(select[i].checked == true){
        select_id = select_id+select[i].id +',';
      }
    }
    if(select_id == ''){
      alert('请选择数据！');
      return
    }else{
      if(confirm("确定要批量处理所选择的用户吗？")){
        $.ajax({
          url:"/emp/pShenheAction.do",
          data:{"ids":select_id,"type":_type},
          type:"POST",
          success:function(_data){
            var data = $.parseJSON(_data);
            if(data.success){
              if(_type == '0'){
                alert("批量未审成功！");
              }
              if(_type == '1'){
                alert("批量审核成功！");
              }
              if(_type == '2'){
                alert("批量不通过成功！");
              }
              //刷新当前页
              window.location.reload();
            }else{
              var _case = {1:"批量处理失败"};
              alert(_case[data.code])
            }
          }
        });
      }
    }
  }

  function P_Login_Select(_type){
    //登陆
    var select_id = '';
    var select = document.getElementsByName("checkbox_one");
    for (var i = 0; i < select.length; i++) {
      if(select[i].checked == true){
        select_id = select_id+select[i].id +',';
      }
    }
    if(select_id == ''){
      alert('请选择数据！');
      return
    }else{
      if(confirm("确定要批量处理所选择的用户吗？")){
        $.ajax({
          url:"/emp/pLoginAction.do",
          data:{"ids":select_id,"type":_type},
          type:"POST",
          success:function(_data){
            var data = $.parseJSON(_data);
            if(data.success){
              if(_type == '0'){
                alert("批量处理用户-允许登陆成功！");
              }
              if(_type == '1'){
                alert("批量处理用户-禁止登陆成功！");
              }
              //刷新当前页
              window.location.reload();
            }else{
              var _case = {1:"批量处理失败"};
              alert(_case[data.code])
            }
          }
        });
      }
    }
  }

  function P_delete_Select(_type){
    //批量删除用户
    var select_id = '';
    var select = document.getElementsByName("checkbox_one");
    for (var i = 0; i < select.length; i++) {
      if(select[i].checked == true){
        select_id = select_id+select[i].id +',';
      }
    }
    if(select_id == ''){
      alert('请选择数据！');
      return
    }else{
      if(confirm("确定要批量删除所选择的用户吗？")){
        $.ajax({
          url:"/emp/pDeleteEmpAction.do",
          data:{"ids":select_id},
          type:"POST",
          success:function(_data){
            var data = $.parseJSON(_data);
            if(data.success){
                alert("批量删除用户成功！");
              //刷新当前页
              window.location.reload();
            }else{
              var _case = {1:"批量处理失败"};
              alert(_case[data.code])
            }
          }
        });
      }
    }
  }

</script>


