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
	 
	@Insert("insert into users(user_type, insta_account, name, nickname, email, password, business_name, business_reg_num, business_address, business_address1)"
			+ "values(#{user_type}, #{insta_account}, #{name}, #{nickname}, #{email}, #{password}, #{business_name}, #{business_reg_num}, #{business_address}, #{business_address1})")
	public int createMember(Map<String, String> map);
  
}
