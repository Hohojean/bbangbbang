package teamPro.bbangShuttle.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class OrderItemVO {

    private int orderNo;
    private String itemName;
    private int itemNo;
    private int itemAmount;
    private Integer itemPrice;

}
