package cafe.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;
import cafe.bean.mybatis.CafesMenuDTO;
import cafe.bean.mybatis.UsersDTO;
@Repository
@Transactional
@Mapper
public interface SelectMapper { 

  @Select("SELECT * FROM CafeDTO")
  public List<CafeDTOMybatis> getCafeListAll();

  @Select("select * from allproduct where cafe_id = ${cafe_id}")
  public List<CafesMenuDTO> getCafesMenusAll(Map<Integer, String> map);

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


}
