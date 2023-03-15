package teamPro.bbangShuttle.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@RequiredArgsConstructor
public class CartVO extends ItemVO{
    private String userID;
    private int itemNo;
    private int cartAmount;
}
