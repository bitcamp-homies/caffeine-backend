package cafe.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;
@Repository
@Transactional
@Mapper
public interface SelectMapper { 

  @Select("SELECT * FROM CafeDTO")
  public List<CafeDTOMybatis> getCafeListAll();
  @Select("select * from users where nickname =#{NickName}")
  public String NickNameCheck(Map<String, String> map);
  @Select("select * from users where email = #{Email}")
  public String EmailCheck(Map<String, String> map);

  //풍혁0818 : point mapping try1 >> success
  @Select("SELECT cafe_id, user_id, cafe_name, address1, address2, address3, address4, ST_Y(coord) AS latitude, ST_X(coord) AS longitude, ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance from cafes")
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
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "from cafes "
      + "WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), coord) < 3000")
  List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);


}
