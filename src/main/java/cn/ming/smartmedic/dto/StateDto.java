package cn.ming.smartmedic.dto;


/**
 * @author root
 *         Created on 2018/3/5
 */
public enum StateDto {

    SUCCESS(200, "请求成功"),

    WRONG(400, "错误的请求"),
    UNAUTHORIZED(401,"用户未登录"),
    PARAMERR(402,"参数错误"),
    FORBIDDEN(403, "禁止访问"),
    UNAVILIABLE(500, "服务器内部错误"),
    TIMEOUT(501, "访问超时"),
    MICROSERVICEUNREACH(601, "微服务暂时不可用"),
    ROBOT_VALID_ERROR(701, "话术配置错误"),
    ENTERAIROBOTWRONG(3001, "进入通话响应失败");

    /**
     * http状态码
     */
    private int httpCode;

    /**
     * 状态描述
     */
    private String message;

    StateDto(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
