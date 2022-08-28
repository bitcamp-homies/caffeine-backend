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
  @Update("UPDATE cafes_coord SET cafe_coord = POINT(${longitude}, ${latitude}) WHERE cafe_id = ${cafe_id}")
  void updateCoordMybatis(@Param("longitude")double longitude, @Param("latitude")double latitude, @Param("cafe_id")long cafe_id);

  @Update("UPDATE cafe_info SET opentime = ${opentime}, closetime = ${closetime}, pet = #{pet}, parking = #{parking} WHERE cafe_id = ${cafe_id}")
  void updateCafeinfo(
      @Param("opentime")int opentime, 
      @Param("closetime")int closetime, 
      @Param("pet")String pet, 
      @Param("parking")String parking, 
      @Param("cafe_id")int cafe_id
      );
 
  @Update("UPDATE users "
      + "SET "
      + "business_reg_num = IF(USER_TYPE='business', ${business_reg_num}, NULL), "
      + "name= #{name}, "
      + "nickname= #{nickname}, "
      + "email= #{email}, "
      + "password= #{password}, "
      + "business_name = IF(USER_TYPE='business', #{business_name}, NULL), "
      + "business_address = IF(USER_TYPE='business', #{business_address}, NULL) "
      + "WHERE user_id = ${user_id}")
  void updateUser(
      @Param("user_id")int user_id, 
      @Param("business_reg_num")int business_reg_num,
      @Param("name")String name, 
      @Param("nickname")String nickname, 
      @Param("email")String email,
      @Param("password")String password, 
      @Param("business_name")String business_name, 
      @Param("business_address")String business_address
  );
  
}
