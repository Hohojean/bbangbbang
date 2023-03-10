package teamPro.bbangShuttle.dto;

import java.util.List;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDTO<T> {
// => List Type Response 용도	
	
	private String error;
	private List<T> data;
}

