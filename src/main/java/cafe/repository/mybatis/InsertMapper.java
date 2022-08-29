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

	
	@Insert("insert into cafes(user_id,cafe_name,address1,address2,address3,about)"
			+ "values(#{user_id},#{cafe_name},#{address1},#{address2},#{address3},#{about})")
	public void InsertCafes(Map<String, Object> map2);

	@Insert("insert into cafes_pics (cafe_id, img_file, file_path)"
			+ "values(${cafe_id},#{img_file},#{file_path})")
	public void insertCafepics(Map<String, String> map);
	
	@Insert("insert into cafes_product_list (cafe_id)"
			+ "values(${cafe_id})")
	public int insertcafes_product_list(Map<String, String> map);

	@Insert("insert into products (product_name_kor,product_name_eng,category,subcategory,price)"
			+ "values(#{product_name_kor},#{product_name_eng},#{category},#{subcategory},${price})")
	public int insertproducts(Map<String, String> map);

	@Insert("insert into cafes_product_list_items (product_list_id, product_id, recommended)"
			+ "values(${product_list_id},${product_id},#{recommended})")
	public void insertcafes_product_list_items(Map<String, String> map);

	@Insert("insert into products_img (product_id, img_file, file_path)"
			+ "values(${product_id},#{img_file},#{file_path})")
	public void insertproducts_img(Map<String, String> map);
  

}
