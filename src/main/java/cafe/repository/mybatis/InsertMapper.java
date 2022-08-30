package cafe.repository.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Mapper
public interface InsertMapper {

	@Insert("insert into users(user_type, insta_account, name, nickname, email, password, business_name, business_reg_num, business_address)"
			+ "values(#{user_type}, #{insta_account}, #{name}, #{nickname}, #{email}, #{password}, #{business_name}, #{business_reg_num}, #{business_address})")
	public int createMember(Map<String, String> map);

	@Insert("insert into payment(user_id, cafe_id, product_count, product_id, total_price, purchase_way)"
			+ "values(#{user_id}, #{cafe_id}, #{product_count}, #{product_id}, #{total_price}, #{purchase_way})")
	void paymentList(Map<String, String> map);
	
	@Insert("insert into users_profile_img(profile_id, path,profile_img)"
			+ "values(#{user_id},#{path},#{img})")
	public void InsertProfileimg(Map<String, String> map);
  
	@Insert("insert into like_lists(user_id, list_name)" + "values((select user_id from users where email = #{email}) ,'내가 좋아하는 카페')")
	public void makeList(@Param("email")String email);

	@Insert("insert into like_cafes(list_id, cafe_id)" + "values((select list_id from like_lists where user_id = (select user_id from users where email = #{email})) , ${randNum})")
	public void makeCafeList(@Param("email")String email, @Param("randNum") int randNum);

	@Insert("insert into like_cafes(list_id, cafe_id) values ((select list_id from like_lists where user_id = (select user_id from users where email = #{email})), #{cafe_id})")
	public void updateLikeList(@Param("cafe_id")int cafe_id, @Param("email")String email);
}
