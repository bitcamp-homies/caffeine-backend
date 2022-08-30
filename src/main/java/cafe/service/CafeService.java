package cafe.service;

import java.util.List;
import java.util.Map;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.UserProfileDTO;
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

	// 수정 : 회원가입시 임의로 관심목록 카페 한개 추가
	public void makeList(String email);

  List<CafeDTOCoordTemp> getCafesListBoundary(double userLong, double userLat, int boundary, Boolean openFilter,
      Boolean petFilter, Boolean parkingFilter);

	public void makeCafeList(String email, int randNum);

	// 관심목록 얻어오기
	public List<Map<Object, Object>> getLikeList(String email);

	List<CafeDTOMybatis> getCafeDistLocation();

	List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(double userLong, double userLat);

	List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(double userLong, double userLat);

	List<CafeDTOCoordTemp> getCafesListBoundary(double userLong, double userLat, int boundary, Boolean openFilter,
			Boolean petFilter, Boolean parkingFilter);

	void updateCoordMybatis(double longitude, double latitude, long cafe_id);

  List<UsersDTO> getAllUser();

  void updateUser(
      int user_id, 
      int business_reg_num,
      String user_type, 
      String name, 
      String nickname, 
      String email,
      String password, 
      String business_name, 
      String business_address
      );

  CafeDTO getCafeByInsta(String insta_account);

  List<CafeitemDTO> getCafeitem(Map<String, String> map);
  
  UsersDTO getMember(Map<String, String> map);
  
  UserProfileDTO selectProfileimg(Map<String, String> map);
  
  //웅비 해당 제품정보 가져오기
  List<CafeitemDTO> getProductInfo(String product_id);

  //웅비 결제 정보 저장
  void paymentList(Map<String, String> map);

	UsersDTO Login(Map<String, String> map);

	void updateCafeinfo(int opentime, int closetime, String pet, String parking, int cafe_id);

	List<CafeitemDTO> getCafeitemList(Map<String, String> map);

	List<CafeitemDTO> getCafeitem(Map<String, String> map);

	UsersDTO getMember(Map<String, String> map);

	void InsertProfileimg(Map<String, String> map);

	UserProfileDTO selectProfileimg(Map<String, String> map);

	void updateProfileimg(Map<String, String> map);

	void updateLikeList(int cafe_id, String email);

	void deleteLikeList(int cafe_id, String email);
  
  void updateProfileimg(Map<String, String> map);

}
