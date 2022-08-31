package cafe.bean.mybatis;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AnalyticVisitDTO {

  private Date date_row;
  private int month;
  private int cnt;
}
