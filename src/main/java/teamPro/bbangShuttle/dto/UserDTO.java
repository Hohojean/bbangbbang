package teamPro.bbangShuttle.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
  private String token;
  private String id;
  private String username;
  private String email;
  private String password;
}
