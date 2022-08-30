package cafe.bean.mybatis;

import lombok.Data;

@Data
public class CafesDTO {
	private int cafe_id, user_id ;
	private String cafe_name, address1, address2, address3, address4, about, subfolder;
}
