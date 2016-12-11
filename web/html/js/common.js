window.onload = function(){
    //边栏导航固定
    $('.left-menu dl dt').click(function(){
        $('.left-menu dl dt').removeClass('active');
        $(this).addClass('active');
    });
    //页中图片自适应高度
    $(window).load(function(){
        var h = $(".home-sign-item img").height();
        $(".home-sign-item").height(h);
        $(".home-sign-item .sign-panel").height(h);
        var h_li = $(".home-sign-item .sign-list li").height();
        var m = (h - h_li*3)/4;
        $(".home-sign-item .sign-list li").css({
            "margin-top":m,
            "margin-bottom":m
        })

    });
    $(window).resize(function(){
        var h = $(".home-sign-item img").height();
        $(".home-sign-item").height(h);
        $(".home-sign-item .sign-panel").height(h);
        var h_li = $(".home-sign-item .sign-list li").height();
        var m = (h - h_li*3)/4;
        $(".home-sign-item .sign-list li").css({
            "margin-top":m,
            "margin-bottom":m
        })
    });

    //浮动窗
    $(".hover-panel-mark").click(function(){
        $(".hover-panel").fadeToggle(300);
        $(this).fadeToggle(300);
    });
    $(".panel-close-btn").click(function(){
        $(".hover-panel").fadeToggle(300);

        $(".hover-panel-mark").fadeToggle(300);
    });



    //Sidebar伸缩
    $(".sidebar dt").click(function(){
        $(this).nextAll("dd").slideToggle(300);
    });

    //Navbar悬停展开
    $(".dropdown").mouseenter(function(){
        $(this).addClass("open");
    });
    $(".dropdown").mouseleave(function(){
        $(this).removeClass("open");
    });


}