package telran.b7a.accounting.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
@NonNull
public class UserLoginDto {
	String login;
	String password;
}
