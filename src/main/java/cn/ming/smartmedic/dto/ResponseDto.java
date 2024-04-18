package cn.ming.smartmedic.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author root
 *         Created on 2018/3/5
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    /**
     * 状态码
     */
    private int status;

    /**
     * 数据信息
     */
    private String message;

    /**
     * 数据对象
     */
    private Object data;

    public ResponseDto(StateDto stateDto, Object data) {
        this.status = stateDto.getHttpCode();
        this.message = stateDto.getMessage();
        this.data = data;
    }

    public static ResponseDto ok() {
        return ok("操作成功");
    }

    public static ResponseDto failed() {
        return failed(StateDto.WRONG, null);
    }

    public static ResponseDto failed(StateDto stateDto, Objects data) {
        return new ResponseDto(stateDto, data);
    }

    public static ResponseDto ok(String msg) {
        return new ResponseDto(StateDto.SUCCESS, msg);
    }

    public static ResponseDto ok(Object data) {
        return new ResponseDto(StateDto.SUCCESS, data);
    }

}
