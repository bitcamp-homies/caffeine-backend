package cafe.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.mybatis.CafeDTOMybatis;
@Repository
@Transactional
@Mapper
public interface SelectMapper {

  @Select("SELECT * FROM CafeDTO")
  public List<CafeDTOMybatis> getCafeListAll();
  @Select("select * from users where nickname =#{NickName}")
  public String NickNameCheck(Map<String, String> map);
  @Select("select * from users where email = #{Email}")
  public String EmailCheck(Map<String, String> map);
}
