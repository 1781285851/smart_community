package cn.com.kaituo.smart_community.gatesvr.filter;

public class ProxyConstants {
    private ProxyConstants() { }
    /**
     * 对外API的摘要KEY参数 属性名
     */
    public static final String DIGEST_KEY = "key";

    /** 外部API请求时，签名头 名称 */
    public static final String SIGN_HEADER = "sign";

    /** API入参签名时，post 方法的 body签名属性 */
    public static final String POST_BODY_KEY = "jsonBody";

    /** zuul filter 过滤链 执行终止标识 */
    public static final String ZUUL_PROXY_RESULT_KEY = "isSuccess";
}
