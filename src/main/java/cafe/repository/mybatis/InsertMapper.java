package cafe.repository.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
  
	@Insert("INSERT INTO analytic_visit VALUES(DATE_FORMAT(now(), '%Y-%m-%d'), 1)")
	public void createRowAnalyticVisit();

}
