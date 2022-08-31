package cafe.bean.mybatis;

import java.sql.Date;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class UserDateDTO {
  
  private int num;
  private int month;
  private Date create_date;
}
