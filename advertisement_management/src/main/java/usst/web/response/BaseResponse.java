package usst.web.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Getter
@Setter
public class BaseResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;

    public BaseResponse() {
        this.code = 200;
        this.message = "ok";
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse makeResponse(int code, String message) {
        return new BaseResponse(code, message);
    }

    public static BaseResponse makeResponse(int status) {
        return new BaseResponse(status, HttpStatus.valueOf(status).getReasonPhrase());
    }
}