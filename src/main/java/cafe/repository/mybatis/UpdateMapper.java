package cafe.repository.mybatis;

import java.util.Map;

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
  @Update("UPDATE cafes_coord SET cafe_coord = POINT(${longitude}, ${latitude}) WHERE cafe_id = ${cafe_id}")
  void updateCoordMybatis(@Param("longitude")double longitude, @Param("latitude")double latitude, @Param("cafe_id")long cafe_id);
  
  @Update("UPDATE users_profile_img SET path=#{path}, profile_img=#{img} WHERE profile_img = ${user_id}")
  void updateProfileimg(Map<String, String> map);

  //테스트 코멘트

  @Update("UPDATE cafe_info SET opentime = ${opentime}, closetime = ${closetime}, pet = #{pet}, parking = #{parking} WHERE cafe_id = ${cafe_id}")
  void updateCafeinfo(
      @Param("opentime")int opentime, 
      @Param("closetime")int closetime, 
      @Param("pet")String pet, 
      @Param("parking")String parking, 
      @Param("cafe_id")int cafe_id
      );
 
}
