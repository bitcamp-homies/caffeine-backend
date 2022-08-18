package cafe.bean.mybatis;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CafeDTOCoordTemp {
  private int cafe_id;
  private int user_id;
  private String cafe_name;
  private String address1;
  private String address2;
  private String address3;
  private String address4;
  private double longitude;
  private double latitude;
}
