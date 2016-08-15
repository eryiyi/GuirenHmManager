<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="/js/Util.js"></script>
<style>
    form {
        margin: 0;
    }

    textarea {
        display: block;
    }
</style>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javaScript:void(0)">主页</a></li>
            <li><a href="javaScript:void(0)">订单管理</a></li>
            <li><a href="javaScript:void(0)">订单详情</a></li>
        </ol>
        <div id="social" class="pull-right">
            <a href="#"><i class="fa fa-google-plus"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-youtube"></i></a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>订单详情</span>
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
                <h4 class="page-header">订单详情</h4>
                <dl class="dl-horizontal">
                    <dt>订单号</dt>
                    <dd>${vo.order_no}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>名称</dt>
                    <dd>${vo.mm_emp_nickname}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>电话</dt>
                    <dd>${vo.mm_emp_mobile}</dd>
                </dl>

                <dl class="dl-horizontal">
                    <dt>购买数量</dt>
                    <dd>${vo.goods_count}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>总金额</dt>
                    <dd>${vo.payable_amount}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>下单时间</dt>
                    <dd>${um:format(vo.create_time, "yyyy-MM-dd HH:mm:ss")}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>付款时间</dt>
                    <dd>${um:format(vo.pay_time, "yyyy-MM-dd HH:mm:ss")}</dd>
                </dl>

                <dl class="dl-horizontal">
                    <dt>订单状态</dt>
                    <dd>
                        <c:if test="${vo.status=='1'}">订单生成</c:if>
                        <c:if test="${vo.status=='2'}">订单支付</c:if>
                        <c:if test="${vo.status=='3'}">订单取消</c:if>
                        <c:if test="${vo.status=='4'}">订单作废</c:if>
                    </dd>
                </dl>

                <dl class="dl-horizontal">
                    <dt>支付状态</dt>
                    <dd>
                        <c:if test="${vo.pay_status=='0'}">未支付</c:if>
                        <c:if test="${vo.pay_status=='1'}">已支付</c:if>
                        <c:if test="${vo.pay_status=='2'}">退款</c:if>
                    </dd>
                </dl>

                <dl class="dl-horizontal">
                    <dt>买家留言</dt>
                    <dd>${vo.postscript}</dd>
                </dl>

            </div>
        </div>
    </div>
</div>
