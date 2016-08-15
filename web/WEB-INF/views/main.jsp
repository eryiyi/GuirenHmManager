<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Start Content-->
    <div class="cat-content">

        <a href="javascript:void(0);"></a>

        <div class="row">
            <h3 class="col-md-12 cat-title">会员管理</h3>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-6">
                <a href="javascript:void(0);" onclick="toPage('/emp/list','1')" class="cat-item bg-blue" >
                    <span >会员数量</span>
                    <i>${memberCount}</i>
                    <input type="button" class="btn-export" value="导出">
                </a>
            </div>

        </div>
        <div class="row">
            <div class="col-md-4 col-sm-6">
                <a href="javascript:void(0);" onclick="toPage('/emp/list','1')" class="cat-item bg-blue" >
                    <span >今日注册会员</span>
                    <i>${countEmpDay}</i>
                    <input type="button" class="btn-export" value="导出">
                </a>
            </div>
        </div>

        <c:if test="${mm_manager_type == 0}">
            <div class="row">
                <h3 class="col-md-12 cat-title">投诉管理</h3>
            </div>
            <div class="row">
                <div class="col-md-4 col-sm-6">
                    <a href="javascript:void(0);"  onclick="toPage('report/list','1')" class="cat-item bg-orange">
                        <span >未处理举报</span>
                        <i>${countReport}</i>
                        <input type="button" class="btn-export" value="导出">
                    </a>
                </div>
            </div>
        </c:if>


    </div>
    <!--End Content-->
<script type="text/javascript">
    function toPage(_url, _page){
        if(_page != ''){
            window.location.href="#module="+_url+"&page="+_page;
        }else{
            window.location.href="#module="+_url;
        }
    }

    function daochu(_type){
        if(_type == 1){
        }
        if(_type == 2){
        }
        if(_type == 3){
        }
        if(_type == 4){
        }
        if(_type == 5){
        }
    }
</script>