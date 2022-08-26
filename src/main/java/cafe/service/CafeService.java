package cafe.service;

import java.util.List;
import java.util.Map;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.UsersDTO;

public interface CafeService {

  //JPA
	List<CafeDTO> getCafeListAll();

  //MyBatis
  List<CafeDTOMybatis> getCafeListAllMybatis();
  
  //MyBatis
  public String NickNameCheck(Map<String, String> map);
  
  //MyBatis
  public UsersDTO EmailCheck(Map<String, String> map);
  //My Batis
  public int createMember(Map<String, String> map);

  List<CafeDTOMybatis> getCafeDistLocation();

  List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(double userLong, double userLat);

  List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(double userLong, double userLat);

  List<CafeDTOCoordTemp> getCafesListBoundary(double userLong, double userLat, int boundary, Boolean openFilter, Boolean petFilter, Boolean parkingFilter);

  void updateCoordMybatis(double longitude, double latitude, long cafe_id);

  UsersDTO Login(Map<String, String> map);

  void updateCafeinfo(int opentime, int closetime, String pet, String parking, int cafe_id);

  List<CafeitemDTO> getCafeitemList(Map<String, String> map);

}

