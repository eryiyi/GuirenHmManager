package com.liangxunwang.unimanager.util;

/**
 * Created by zhl on 2015/1/29.
 */
public class Constants {
        public static final String URL = "http://www.shandongguiren.com/";
//          public static final String URL = "http://192.168.0.224:8080/";

        public static final String DOWNLOAD_URL = URL + "html/GuirenApp.apk";

        public static final String QINIU_URL = "http://7xt74j.com1.z0.glb.clouddn.com/";

        public static final String QINIU_SPACE = "paopao-pic";
        public static final String COVER_DEFAULT = "default_avatar.png";
//        public static final String FILE_PATH = "D://recordfile";

        public static final String API_KEY = "UT5s48GsO3ndHecBTyiaQoSn";
        public static final String SECRET_KEY = "YwPSU7YEftS23np6x3CsRMm5vOImb5uj";
        public static final String IOS_API_KEY = "W38xecAXVYDY955Shv0GGc8f";
        public static final String IOS_SECRET_KEY = "ZrhZicrOHvexKhRoKWFIdyaEsCTrUeom";

        public static final String SAVE_ERROR = "save_error";
        public static final String DEFAULT_SERVICE_TOP_BG = "img/user_bg.jpg";//默认背景图--顶部的
        public static final String DEFAULT_DOWNLOAD_URL = "html/download.html";//默认下载地址

        public static final String HAS_ZAN = "has_zan";

        public static final String HAS_CODE = "has_code";

        public static final String NO_SEND_CODE = "no_send_code";

        public static final String SEND_SMS_ERROR = "send_sms_error";

        public static final String HAS_EXISTS = "has_exists";

        public static final String TOO_MANY_CODE = "too_many_code";

        public static final String CODE_NOT_EQUAL = "code_not_equal";

        public static final String PHONE_ERROR = "phone_error";

        public static final String HX_ERROR = "hx_error";

        public static final String SMS_MESSAGE_URL = "http://60.209.7.78:8080/smsServer/submit";

        public static final Long DAY_MILLISECOND = 86400000L;

        //商户PID
        public static final String PARTNER = "2088421395960317";
        //商户收款账号
        public static final String SELLER = "sdgrwlkj@126.com";
        //商户私钥，pkcs8格式
        public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBxGvCO6MZlgQWV" +
                "DNyK1gCMfwfCNdJAxoYeTr2Q4DobVBtf8aSr6qHF32hpZ5XJuHd9GHVWAtF8Etao" +
                "Hhfe15S4gB/mllHpO9CF3uwTl4DRnEUk2EPLCqv0Ls8UghrVZ8/N837p3KI13wLk" +
                "Uus/YN+d5Zkwfx6PXs7HcOygC1jDAgMBAAECgYBHR93VqumXyzM+MRZhfl+ENoY7" +
                "BnnPFkUymnlKCCa1/zdpKGGmQDOZlmSJFTnUJrLYN5Leq2xB0fZcYks7RkSNYcR/" +
                "/q9mCwRf/0ihFtIKybLsFaMP9DM5qHn7r0t9MtjM9UjGVP4ub9EKbC0lmZOIZ0Lg" +
                "kE3rv7UbwccWtMheGQJBAPFShcW1SIZ4PokCQsi37cDMdEQEhhveIpHKfqyCHcVH" +
                "Ts8QLnMgKiF8Ec18RGaK+ccWZvD62EbUAsF8Zyi+vH8CQQDMJX6s+erJt+1x3H/K" +
                "Nnydx+tdC32SyxLXgc8fO13sQrd0E3XHBCT/5sXL2gSqZp5RyAQz/tvTv6XT1Rxb" +
                "alG9AkEAhaDOlnYV3PBEoJVx6bd/nd0ZHHjs0g2lUtmwX1IFrjIYP6yULAfJjJUp" +
                "PoHrVmTqfwW2pwYgMMhBZma+sN89fQJBAMNIytjlaXf1fiqBZ60kWikE6V1kdIdL" +
                "w4ZsIpoZzEsJtzxOsUfj6xDwzR2oPPZUm/ZXg83JargwiI0PO8Um+3ECQDHvrfZz" +
                "gfsYbXztvdkf7rkwC1zQkKiSLm2jz8BQdWGvUnh+kZKBDmiHrKDu3w1tBMiL3miC" +
                "vbcy26Q68j62CEA=";
        //支付宝公钥
        public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvFkyO6eom1IpsvCKs2Y55iFznFImLh5M8ON1n47+pYX1SV8cXZ+7gX0U0dFwORVgE27wv/fXC0r4UmOk5U8SD9JP56LZKiw148G8kLeGyJn8CoYVqCH0WM337K603kCd05v4lRP/jgjHASiMb2fK7mRE9MHZbeLwLzUilJwAIkwIDAQAB";


        //微信统一下单notify_url
        public static final String WEIXIN_NOTIFY_URL = URL + "pay/notify_url_wxpay.jsp";

        //支付宝回调页面
        public static final String ZFB_NOTIFY_URL = URL + "pay/notify_url_alipay.jsp";

        //appid
        public static final String WX_APP_ID = "wx4da8b73a07135cd1";//yum
        //商户号
        public static final String WX_MCH_ID = "1354652802";//yum
        //  API密钥，在商户平台设置
        public static final  String WX_API_KEY="a393cc92c26041c3cdad965a931ba537";//yum
}
