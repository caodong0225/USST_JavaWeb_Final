package caodong0225.common.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jyzxc
 * @since 2024-11-17
 */
@Getter
@Setter
public class GoodsOrderedVO {
    // 商品id
    @Schema(description = "商品ID", example = "1")
    private Integer id;
    // 商品名称
    @Schema(description = "商品名称", example = "商品1")
    private String goodsName;
    // 商品单价
    @Schema(description = "商品单价", example = "100.00")
    private Double price;
    // 购买数量
    @Schema(description = "购买数量", example = "1")
    private Integer number;
    // 总金额
    @Schema(description = "总消费金额", example = "100.00")
    private Double totalPrice;
}
