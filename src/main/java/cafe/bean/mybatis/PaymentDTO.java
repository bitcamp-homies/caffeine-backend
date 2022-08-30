package cafe.bean.mybatis;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PaymentDTO {
 private int payment_num;
 private int user_id;
 private int cafe_id;
 private int product_count;
 private int product_id;
 private int total_price;
 private int purchase_way; 
 private Date create_At;
}
