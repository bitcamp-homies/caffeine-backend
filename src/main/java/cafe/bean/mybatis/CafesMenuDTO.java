package cafe.bean.mybatis;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CafesMenuDTO {
 private int product_id, cafe_id, price;
 private String name_kor, name_eng, recommended, category, subcategory, size1, size2, size3, size4;
}