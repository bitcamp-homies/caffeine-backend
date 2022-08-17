package cafe.service;

import java.util.List;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;

public interface CafeService {

  //JPA
	List<CafeDTO> getCafeListAll();

  //MyBatis
  List<CafeDTOMybatis> getCafeListAllMybatis();

  List<CafePointTest> getCafeListBoundary(double userLong, double userLat);
}
