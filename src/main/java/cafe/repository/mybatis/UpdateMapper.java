package cafe.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Mapper
public interface UpdateMapper {

  //풍혁0818 : cafe_id와 kakao map api로 부터 받은 좌표 가져와서 DB coord 최신화
  @Update("UPDATE cafes SET COORD = POINT(${longitude}, ${latitude}) WHERE cafe_id = ${cafe_id}")
  void updateCoordMybatis(@Param("longitude")double longitude, @Param("latitude")double latitude, @Param("cafe_id")long cafe_id);

}
