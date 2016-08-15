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
      <li><a href="javascript:void (0);">管理员</a></li>
      <li><a href="javascript:void (0);">管理员详情</a></li>
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
          <span>管理员详情</span>
        </div>
      </div>
      <div class="box-content">
        <h4 class="page-header">管理员</h4>
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-sm-2 control-label">用户名</label>
              <div class="col-sm-4">
                  <input type="text" readonly="true" id="mm_manager_nickname" class="form-control" value="${admin.mm_manager_nickname}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
              </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">用户手机号</label>
            <div class="col-sm-4">
              <input type="text" readonly="true" id="mm_manager_mobile" class="form-control" value="${admin.mm_manager_mobile}" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">用户密码</label>
            <div class="col-sm-4">
              <input type="text" id="mm_manager_password" class="form-control"  placeholder="新密码" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label">角色</label>
            <div class="col-lg-8">

              <c:if test="${roleRname != null}"><div id="rname">${roleRname}</div></c:if>
              <c:if test="${role != null}"><div id="rname">${role.name}</div></c:if>

            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">管理区域</label>
            <div class="col-lg-8">
              <div id="permissions">${admin.provinceName}${admin.cityName}${admin.countryName}</div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">状态</label>
            <div class="col-lg-8">
              <c:if test="${admin.mm_manager_is_use=='1'}"><div id="status">启用</div></c:if>
              <c:if test="${admin.mm_manager_is_use=='0'}"><div id="status">禁用</div></c:if>
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
              <button type="button" class="btn btn-primary" onclick="saveRole('${admin.mm_manager_id}')">确定修改</button>
              <button type="button" class="btn btn-primary" onclick="manageEmp('${admin.mm_manager_id}','1')">启用</button>
              <button type="button" class="btn btn-primary" onclick="manageEmp('${admin.mm_manager_id}','0')">禁用</button>
              <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  <%--$.ajax({--%>
    <%--url: '/permission/list.do',--%>
    <%--type: 'post',--%>
    <%--success: function (_data) {--%>
      <%--var data = $.parseJSON(_data);--%>
      <%--if (data) {--%>
        <%--var ownPermission = "${sessionScope.powers}";--%>
        <%--var newNode = "";--%>
        <%--for (var i = 0; i < data.length; i++) {--%>
          <%--var per = data[i];--%>
          <%--var childNode = "";--%>
          <%--if (per.child) {--%>
            <%--for (var j = 0; j < per.child.length; j++) {--%>
              <%--if (ownPermission.indexOf(per.child[j].id) != -1 || ownPermission.replace("/\s/g", "") == "all") {--%>
                <%--childNode += "<input type='checkbox' name='permissions' id='" + per.id + j + "' value='" + per.child[j].id + "'>" + per.child[j].name;--%>
              <%--}--%>
            <%--}--%>
          <%--}--%>
          <%--if (childNode != "") {--%>
            <%--newNode += "<span><input name='permission' type='checkbox' id='" + per.id + "' onclick=\"checkChild(this)\" value='" + per.id + "'>" + per.name;--%>
            <%--newNode += childNode;--%>
            <%--newNode += "</span><br/>";--%>
          <%--}--%>
        <%--}--%>
        <%--$("#permissions").append(newNode);--%>
      <%--}--%>
    <%--}--%>
  <%--});--%>
  function saveRole(mm_manager_id){
    var mm_manager_password = $("#mm_manager_password").val();
    if(mm_manager_password.replace(/\s/g, '') == ''){
        alert("密码不能为空");
        return;
    }
    if(mm_manager_password.length<6 || mm_manager_password.length>18){
      alert("密码长度至少6位,最多18位");
      return;
    }
    mm_manager_password = hex_md5(mm_manager_password);
    $.ajax({
      cache: true,
      type: "POST",
      url:"/changePass.do",
      data:{"mm_manager_password":mm_manager_password, "mm_manager_id":mm_manager_id},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=admin/list&page=1";
        }else{
          var _case = {1:"修改失败"};
          alert(_case[data.code])
        }
      }
    });
  };


  function manageEmp(_id,_type){
    $.ajax({
      cache: true,
      type: "POST",
      url:"/admin/updateType.do",
      data:{"mm_manager_id":_id, "mm_manager_is_use":_type},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("修改成功");
          window.location.href = "#module=admin/list&page=1";
        }else{
          var _case = {1:"修改失败"};
          alert(_case[data.code])
        }
      }
    });
  };

  //
  function checkChild(_node) {
    var id = $(_node).val();//这个是点击的那个checkbox的id值，就是最前面那个checkbox
    if ($(_node).attr("checked")) {//要是这个地方选中的话，点击的时候把他后面的那些都置为不选中
      //这个选择是以【id】为开始的那些id的属性checked修改   明白不啊？不啊 擦擦擦
      $("input[id^='" + id + "']").attr("checked", false);
    } else {
      $("input[id^='" + id + "']").attr("checked", true);
    }
  };
</script>


