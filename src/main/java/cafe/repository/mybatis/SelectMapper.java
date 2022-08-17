package cafe.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.mybatis.CafeDTOMybatis;
@Repository
@Transactional
@Mapper
public interface SelectMapper {

  @Select("SELECT * FROM cafes WHERE cafe_id = 1")
  CafeDTOMybatis findCafeIdOne();
}
