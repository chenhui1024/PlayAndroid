package cn.landi.playandroid.demo.myhttp;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class Response {


    /**
     * msg : invalid_apikey
     * code : 104
     * request : GET /v2/book/1220562
     */

    private String msg;
    private int code;
    private String request;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
