package cafe.bean.mybatis;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CafeDTOMybatis {
  private int cafe_id;
  private int user_id;
  private String cafe_name;
  private String address1;
  private String address2;
  private String address3;
  private String address4;
  private String about;
  private String subfolder;
}
