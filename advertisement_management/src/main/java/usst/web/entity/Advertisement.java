package usst.web.entity;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class Advertisement implements Serializable {
    private Integer adId;
    private String adName;
    private String adUrl;
    private String adImage;
    private Timestamp adStartTime;
    private Timestamp adEndTime;
    private Byte adUse;
    private String adFeature;
}
