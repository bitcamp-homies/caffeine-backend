package cafe.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;
@Repository
@Transactional
@Mapper
public interface SelectMapper {

  @Select("SELECT * FROM CafeDTO")
  List<CafeDTOMybatis> getCafeListAll();

  @Select("SELECT GID, ST_Distance_Sphere(POINT(${userLong},${userLat}), PT) AS distance FROM GEO_TEST WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), PT) < 2000")
  List<CafePointTest> getCafeListBoundary(@Param("userLong")double userLong, @Param("userLat")double userLat);
}
