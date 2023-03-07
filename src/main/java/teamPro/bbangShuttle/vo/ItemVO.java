package teamPro.bbangShuttle.vo;

import lombok.*;

@Getter @Setter
@RequiredArgsConstructor
public class ItemVO {

    private int itemNo;
    private String itemCategory;
    private String itemName;
    private int itemAmount;
    private Integer itemPrice;
    private String imgName;

}
