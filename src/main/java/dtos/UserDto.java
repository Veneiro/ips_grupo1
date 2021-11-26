package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public int id;
    public String userName;
    public String password;
    public String category;
    public String expiration;
    public int id_asociado;
}
