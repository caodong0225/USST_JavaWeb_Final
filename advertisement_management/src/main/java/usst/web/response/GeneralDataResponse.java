package usst.web.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Getter
@Setter
public class GeneralDataResponse<T> extends BaseResponse {
    private T data;

    public GeneralDataResponse() {
        super();
    }

    public GeneralDataResponse(T data) {
        super();
        this.data = data;
    }

    public GeneralDataResponse(Integer code, String message) {
        super(code, message);
    }

    public GeneralDataResponse(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

}
