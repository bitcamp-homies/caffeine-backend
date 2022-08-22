package cafe.bean.mybatis;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UsersDTO {
	private int user_id;
	private String user_type, insta_account, name, nickname, email, password;
}
