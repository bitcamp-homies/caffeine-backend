package cafe.bean.mybatis;

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
  private String insta_account;
  private String about;
  private String subfolder;
  private String img_file;
  private String file_path;
  private double longitude;
  private double latitude;
  private double distance;
}
