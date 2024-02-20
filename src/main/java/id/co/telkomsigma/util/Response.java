package id.co.telkomsigma.util;

/**
 * @author satiya
 */
public class Response {

    private String status;

    private String message;

    private Object data;

    public Response() {
    }

    public Response(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Response success() {
        return new Response(Constant.ResponseStatus.TRUE, Constant.ResponseMessage.SUCCESS, null);
    }

    public static Response success(Object data) {
        return new Response(Constant.ResponseStatus.TRUE, Constant.ResponseMessage.SUCCESS, data);
    }

    public static Response failed() {
        return new Response(Constant.ResponseStatus.FALSE, Constant.ResponseMessage.FAILED, null);
    }

    public static Response failed(String message) {
        return new Response(Constant.ResponseStatus.FALSE, Constant.ResponseMessage.FAILED + ". " + message, null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
