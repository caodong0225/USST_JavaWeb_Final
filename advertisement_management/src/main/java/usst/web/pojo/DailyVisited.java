package usst.web.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author jyzxc
 * @since 2024-12-31
 */
@Getter
@Setter
public class DailyVisited {
    private LocalDateTime date;
    private Integer count;
}
