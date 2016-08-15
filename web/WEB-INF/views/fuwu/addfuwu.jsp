<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
<script type="text/javascript"
        src="http://webapi.amap.com/maps?v=1.3&key=98ec561802a0063ec6d8301ae2321505"></script>

<div class="row">
  <div id="breadcrumb" class="col-xs-12">
    <a href="#" class="show-sidebar">
      <i class="fa fa-bars"></i>
    </a>
    <ol class="breadcrumb pull-left">
      <li><a href="javascript:void(0)"  onclick="toPage('mainPage','')">主页</a></li>
      <li><a href="javascript:void(0)">服务管理</a></li>
      <li><a href="javascript:void(0)">添加服务</a></li>
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
          <span>添加服务</span>
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
        <h4 class="page-header">添加服务</h4>
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <label class="col-sm-2 control-label">服务名称</label>
            <div class="col-sm-4">
              <input type="text" id="add_one" class="form-control" placeholder="服务名称" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">服务电话</label>
            <div class="col-sm-4">
              <input type="text" id="add_two" class="form-control" placeholder="服务电话" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">服务内容</label>
            <div class="col-sm-4">
              <input type="text" id="add_three" class="form-control" placeholder="服务内容" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">服务类型</label>
            <div class="col-sm-4">
              <select class="form-control" id="mm_fuwu_type">
                <option value="">--选择服务类型--</option>
                <option value="0" }>苗木商店</option>
                <option value="1" }>装车工人</option>
                <option value="2" }>物流中心</option>
                <option value="3" }>嫁接团队</option>
                <option value="4" }>吊车服务</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">微网站链接</label>
            <div class="col-sm-4">
              <input type="text" id="mm_fuwu_url" class="form-control" placeholder="只有服务类型选择苗木商店的时候有效" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">经纬度</label>
            <div class="col-sm-4">
              <input type="text" id="lat" class="form-control" placeholder="纬度" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
            </div>
            <div class="col-sm-4">
              <input type="text" id="lng" class="form-control" placeholder="经度" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
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

  <div class="col-xs-12 col-sm-12">
    <div class="box">
      <div class="box-header">
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
      <div class="box-content" style="height: 500px">
        <h4 class="page-header">地图</h4>
        <div id="mapContainer"></div>
      </div>
    </div>
  </div>

</div>

<script type="text/javascript">
  function saveP(){
    var add_one = $("#add_one").val();
    var add_two = $("#add_two").val();
    var add_three = $("#add_three").val();
    var mm_fuwu_type = $("#mm_fuwu_type").val();
    var lat = $("#lat").val();
    var lng = $("#lng").val();
    var mm_fuwu_url = $("#mm_fuwu_url").val();

    if(add_one.replace(/\s/g, '')==''){
      alert("请输入正确的服务名称");
      return;
    }
    if(add_two.replace(/\s/g, '')==''){
      alert("请输入正确的服务电话");
      return;
    }
    if(mm_fuwu_type.replace(/\s/g, '')==''){
      alert("请选择服务类型");
      return;
    }
    if(lat.replace(/\s/g, '')==''){
      alert("请点击地图选择经纬度");
      return;
    }
    if(lng.replace(/\s/g, '')==''){
      alert("请点击地图选择经纬度");
      return;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"/fuwu/addFuwu.do",
      data:{"mm_fuwu_nickname":add_one, "mm_fuwu_tel":add_two,"mm_fuwu_content":add_three,"mm_fuwu_type":mm_fuwu_type,"lat":lat,"lng":lng,"mm_fuwu_url":mm_fuwu_url},
      async: false,
      success: function(_data) {
        var data = $.parseJSON(_data);
        if(data.success){
          alert("执行成功");
          window.location.href = "#module=fuwu/list";
        }else{
          alert("执行失败，请检查")
        }
      }
    });
  }
</script>
<script type="text/javascript">
  //初始化地图对象，加载地图
  ////初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
  var map = new AMap.Map('mapContainer', {
    resizeEnable: true
  });
  //地图中添加地图操作ToolBar插件
  map.plugin(['AMap.ToolBar'], function() {
    //设置地位标记为自定义标记
    var toolBar = new AMap.ToolBar();
    map.addControl(toolBar);
  });

  var mapObj = new AMap.Map("mapContainer",{
    rotateEnable:true,
    dragEnable:true,
    zoomEnable:true,
    zooms:[3,18]
    //二维地图显示视口
//    view: new AMap.View2D({
//      center:new AMap.LngLat(118.783897, 32.058875),//地图中心点
//      zoom:15 //地图显示的缩放级别
//    })
  });
//  mapObj.plugin(["AMap.ToolBar"],function(){
//    toolBar = new AMap.ToolBar();
//    mapObj.addControl(toolBar);
//  });
  var marker = new AMap.Marker({
    position:mapObj.getCenter()
  });
  marker.setMap(mapObj);

  //为地图注册click事件获取鼠标点击出的经纬度坐标
  var clickEventListener=AMap.event.addListener(mapObj,'click',function(e){
    document.getElementById("lat").value=e.lnglat.getLat();
    document.getElementById("lng").value=e.lnglat.getLng();

    marker.setMap(null);
    mapObj.setCenter( new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat()));
    marker = new AMap.Marker({
      position:new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat())
    });
    marker.setMap(mapObj);
  });
</script>