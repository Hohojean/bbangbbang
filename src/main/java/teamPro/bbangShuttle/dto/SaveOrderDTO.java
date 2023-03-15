package teamPro.bbangShuttle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveOrderDTO<T> {
    private String error;
    private OrderVO orderVO;
    private List<T> orderItemList;
}
