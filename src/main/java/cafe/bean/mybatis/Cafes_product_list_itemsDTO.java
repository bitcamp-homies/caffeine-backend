package cafe.bean.mybatis;

import lombok.Data;

@Data
public class Cafes_product_list_itemsDTO {
	private int seq, product_list_id, product_id; 
	private String recommended;
}
