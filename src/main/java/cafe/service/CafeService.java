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

	//수정 : 회원가입시 임의로 관심목록 카페 한개 추가
	public void makeList(String email);

	public void makeCafeList(String email, int randNum);
	
	//관심목록 얻어오기
	public List<Map<Object, Object>> getLikeList(String email);

	public List<CafeDTOMybatis> getCafeDistLocation();

	public List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(double userLong, double userLat);

	public List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(double userLong, double userLat);

	public void updateCoordMybatis(double longitude, double latitude, long cafe_id);

	public UsersDTO Login(Map<String, String> map);


}
