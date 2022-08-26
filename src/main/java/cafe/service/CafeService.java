package cafe.service;

import java.util.List;
import java.util.Map;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.UsersDTO;

public interface CafeService {

	// JPA
	List<CafeDTO> getCafeListAll();

	// MyBatis
	List<CafeDTOMybatis> getCafeListAllMybatis();

	// MyBatis
	public String NickNameCheck(Map<String, String> map);

	// MyBatis
	public UsersDTO EmailCheck(Map<String, String> map);

	// My Batis
	public int createMember(Map<String, String> map);

	public void makeList(String email);

	public void makeCafeList(String email, int randNum);

	List<CafeDTOMybatis> getCafeDistLocation();

	List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(double userLong, double userLat);

	List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(double userLong, double userLat);

	void updateCoordMybatis(double longitude, double latitude, long cafe_id);

	UsersDTO Login(Map<String, String> map);

}
