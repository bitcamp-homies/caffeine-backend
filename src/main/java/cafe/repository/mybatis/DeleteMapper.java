package cafe.repository.mybatis;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Mapper
public interface DeleteMapper {

  @Delete("delete from payment where payment_num = #{payment_num}")
  void deleteOrderList(Map<String, String> map); 
  
}
