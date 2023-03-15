package teamPro.bbangShuttle.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@RequiredArgsConstructor
public class OrderVO extends OrderItemVO{

    private int orderNo;
    private String userID;
    private String delyAddr;
    private String userPhone;
    private Date orderDate;
    private String payment;
    private String orderState;
    private String receiver;
}
