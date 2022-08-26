package cafe.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.UsersDTO;
@Repository
@Transactional
@Mapper
public interface SelectMapper { 

  @Select("SELECT * FROM CafeDTO")
  public List<CafeDTOMybatis> getCafeListAll();

  @Select("select * from users where nickname =#{NickName}")
  public String NickNameCheck(Map<String, String> map);
  
  @Select("select * from users where email = #{Email}")
  public UsersDTO EmailCheck(Map<String, String> map);

  @Select("SELECT * FROM CafeDTO GROUP BY address2;")
  List<CafeDTOMybatis> getCafeDistLocation();

  //풍혁0818 : point mapping try1 >> success
  @Select("SELECT cafe_id, user_id, cafe_name, address1, address2, address3, address4, insta_account, about, subfolder, img_file, file_path, insta_account, ST_Y(coord) AS latitude, ST_X(coord) AS longitude, ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance from CafeDTO")
  List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);

  //풍혁0819 : 3000m 이내의 카페만 져오기
  @Select("SELECT "
      + "cafe_id, "
      + "user_id, "
      + "cafe_name, "
      + "address1, "
      + "address2, "
      + "address3, "
      + "address4, "
      + "about,"
      + "insta_account,"
      + "subfolder,"
      + "img_file,"
      + "file_path,"
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "from CafeDTO "
      + "WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), coord) < 3000")
  List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);
  
  @Select("select * from users where email = #{id} and password = #{password}")
  public UsersDTO Login(Map<String, String> map);
  
  @Select("SELECT c.cafe_id, p.*, pi2.*\r\n"
  		+ "FROM cafes c\r\n"
  		+ "LEFT JOIN cafes_product_list cpl ON c.cafe_id = cpl.cafe_id\r\n"
  		+ "INNER JOIN cafes_product_list_items cpli ON cpl.product_list_id = cpli.product_list_id\r\n"
  		+ "LEFT JOIN products p ON cpli.product_id = p.product_id\r\n"
  		+ "INNER JOIN products_img pi2 ON p.product_id = pi2.product_id\r\n"
  		+ "WHERE c.cafe_id = ${cafe_id};\r\n"
  		+ "")
  public List<CafeitemDTO> getCafeitemList(Map<String, String> map);


}
