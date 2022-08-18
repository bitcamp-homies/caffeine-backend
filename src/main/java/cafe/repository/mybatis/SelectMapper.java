package cafe.repository.mybatis;

import java.util.List;

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
  List<CafeDTOMybatis> getCafeListAll();

  //풍혁0817 : geospatial test
  @Select("SELECT GID, ST_Distance_Sphere(POINT(${userLong},${userLat}), PT) AS distance FROM GEO_TEST WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), PT) < 3000")
  List<CafePointTest> getCafeListBoundary(@Param("userLong")double userLong, @Param("userLat")double userLat);

  //풍혁0818 : point mapping try1
  @Select("SELECT cafe_id, user_id, cafe_name, address1, address2, address3, address4, ST_Y(coord) AS latitude, ST_X(coord) AS longitude from cafes")
  List<CafeDTOCoordTemp> getCafesListWithCoord();

  //풍혁0818 : cafe_id와 kakao map api로 부터 받은 좌표 가져와서 DB coord 최신화
  @Update("UPDATE cafes SET COORD = POINT(${longitude}, ${latitude}) WHERE cafe_id = ${cafe_id}")
  void updateCoord(@Param("longitude")double longitude, @Param("latitude")double latitude, @Param("cafe_id")long cafe_id);

}
