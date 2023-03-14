package teamPro.bbangShuttle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import teamPro.bbangShuttle.vo.ItemVO;
import teamPro.bbangShuttle.vo.ReviewVO;

import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO<T> {

    private String error;
    private List<T> itemList;
    private Optional<ItemVO> item;
    private List<ReviewVO> review;
}
