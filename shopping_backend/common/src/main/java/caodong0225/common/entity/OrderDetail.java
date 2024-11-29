package caodong0225.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author jyzxc
 * @since 2024-11-16
 */
@Getter
@Setter
public class OrderDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "订单ID", example = "1")
    private Integer orderId;
    @Schema(description = "商品ID", example = "1")
    private Integer goodsId;
    @Schema(description = "该商品的购买数量", example = "1")
    private Integer num;
    @Schema(description = "该商品总消费金额", example = "100.00")
    private Double amount;
}
