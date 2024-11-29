package caodong0225.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jyzxc
 * @since 2024-11-17
 */
@Getter
@Setter
public class OrderInfoVO {
    // 订单ID
    @Schema(description = "订单ID", example = "1")
    private Integer id;
    // 订单流水号
    @Schema(description = "订单流水号", example = "20241117123456789")
    private String orderNo;
    // 订单状态
    @Schema(description = "订单状态，1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款", example = "1")
    private Integer status;
    // 订单总价
    @Schema(description = "订单总价", example = "100.00")
    private Double totalPrice;
    // 订单创建时间
    @Schema(description = "订单创建时间", example = "2024-11-17 12:34:56")
    private LocalDateTime createTime;
    // 订单商品列表
    @Schema(description = "订单商品列表")
    private List<GoodsOrderedVO> orderDetailInfoVOList;
}
