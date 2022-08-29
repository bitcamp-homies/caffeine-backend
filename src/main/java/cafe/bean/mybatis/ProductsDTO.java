package cafe.bean.mybatis;

import lombok.Data;

@Data
public class ProductsDTO {
	private int product_id; 
	private String product_name_kor, product_name_eng, category, subcategory;
	private int price;
}
