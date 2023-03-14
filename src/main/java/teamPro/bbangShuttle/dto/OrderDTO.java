package teamPro.bbangShuttle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO<T> {


        private String error;
        private List<T> orderList;
        private Optional<OrderVO> order;
    }

