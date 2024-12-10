package usst.web.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Getter
@Setter
public class BaseDataResponse extends BaseResponse {
    private Object data;

    public BaseDataResponse() {
        super();
    }

    public BaseDataResponse(Object data) {
        super();
        this.data = data;
    }

    public BaseDataResponse(Integer code, String message) {
        super(code, message);
    }

    public BaseDataResponse(Integer code, String message, Object data) {
        super(code, message);
        this.data = data;
    }
}
