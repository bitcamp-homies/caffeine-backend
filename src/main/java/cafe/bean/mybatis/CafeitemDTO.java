package cafe.bean.mybatis;

import lombok.Data;

@Data
public class CafeitemDTO {
	private int cafe_id, product_id;
	private String product_name_kor, product_name_eng, category, subcategory, price, img_file, file_path;
}
