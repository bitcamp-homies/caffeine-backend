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
  @Select("SELECT *, "
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "FROM CafeDTO")
  List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);

  //풍혁0819 : 3000m 이내의 카페만 져오기
  @Select("SELECT *, "
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "from CafeDTO "
      + "WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), coord) < 3000")
  List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);

  //풍혁0826 : 변경되는 반경 ( + filter ) 적용시켜서 List가져오기
  @Select("SELECT *, "
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "from CafeDTO "
      + "WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), coord) < ${boundary * 1000} AND "
      + "IF(${openFilter}, opentime <= HOUR(NOW())*100+MINUTE(NOW()) AND closetime >= HOUR(NOW())*100+MINUTE(NOW()), opentime > 0) AND "
      + "IF(${petFilter}, pet='Y', (pet='Y' || pet='N')) AND "
      + "IF(${parkingFilter}, parking='Y', (parking='N' || parking='Y')) "
      + "ORDER BY distance "
      )
  
  public List<CafeDTOCoordTemp> getCafesListBoundary(
      @Param("userLong") double userLong,
      @Param("userLat")double userLat,
      @Param("boundary")int boundary,
      @Param("openFilter")int openFilterNum, 
      @Param("petFilter")int petFilterNum, 
      @Param("parkingFilter")int parkingFilterNum
      );

  @Select("select * from users where email = #{id} and password = #{password}")
  public UsersDTO Login(Map<String, String> map);
  
  @Select("SELECT c.cafe_id, p.*, pi2.*,cpli.recommended\r\n"
  		+ "FROM cafes c\r\n"
  		+ "LEFT JOIN cafes_product_list cpl ON c.cafe_id = cpl.cafe_id\r\n"
  		+ "INNER JOIN cafes_product_list_items cpli ON cpl.product_list_id = cpli.product_list_id\r\n"
  		+ "LEFT JOIN products p ON cpli.product_id = p.product_id\r\n"
  		+ "INNER JOIN products_img pi2 ON p.product_id = pi2.product_id\r\n"
  		+ "WHERE c.cafe_id = ${cafe_id}")
  public List<CafeitemDTO> getCafeitemList(Map<String, String> map);


  
  @Select("SELECT c.cafe_id, p.*, pi2.*\r\n"
  		+ "FROM cafes c\r\n"
  		+ "LEFT JOIN cafes_product_list cpl ON c.cafe_id = cpl.cafe_id\r\n"
  		+ "INNER JOIN cafes_product_list_items cpli ON cpl.product_list_id = cpli.product_list_id\r\n"
  		+ "LEFT JOIN products p ON cpli.product_id = p.product_id\r\n"
  		+ "INNER JOIN products_img pi2 ON p.product_id = pi2.product_id\r\n"
  		+ "WHERE c.cafe_id = ${cafe_id} and pi2.product_id = ${product_id};")
  public List<CafeitemDTO> getCafeitem(Map<String, String> map);
  
  @Select("Select * from users where email = #{user_id}")
  public UsersDTO getMember(Map<String, String> map);


}
