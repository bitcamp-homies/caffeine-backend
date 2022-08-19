package cafe.service;

import java.util.List;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;

public interface CafeService {

  //JPA
	List<CafeDTO> getCafeListAll();

  //MyBatis
  List<CafeDTOMybatis> getCafeListAllMybatis();

  List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(double userLong, double userLat);

  List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(double userLong, double userLat);

  void updateCoordMybatis(double longitude, double latitude, long cafe_id);
}

