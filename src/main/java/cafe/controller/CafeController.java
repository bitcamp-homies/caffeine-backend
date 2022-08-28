package cafe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.UsersDTO;
import cafe.service.CafeService;

@RestController
@CrossOrigin(origins = "*")
public class CafeController {

	@Autowired
	private CafeService cafeService;
		
	@GetMapping(value = "/cafe/listAll")
	public List<CafeDTO> getCafeListAll() {
		return cafeService.getCafeListAll();
	}

	@GetMapping(value = "/cafe/listAllMybatis")
	public List<CafeDTOMybatis> getCafeIdOne() {
		return cafeService.getCafeListAllMybatis();
	}

	@GetMapping(value = "/cafe/cafeDistLocation")
	public List<CafeDTOMybatis> getCafeDistLocation() {
		return cafeService.getCafeDistLocation();
	}

	@GetMapping(value = "/cafe/listAlllWithCoordMybatis")
	public List<CafeDTOCoordTemp> getCafesListWithCoord(@RequestParam(value = "userLong") double userLong, @RequestParam(value = "userLat") double userLat){
	  List<CafeDTOCoordTemp> list = cafeService.getCafesListWithCoordMybatis(userLong, userLat);
	  return list;
	}
  
  
  @GetMapping(value = "/cafe/NickNameCheck")
  public String NickNameCheck(@RequestParam Map<String,String>map) {
	  String check = cafeService.NickNameCheck(map);
	  String findNickName;
	  if(check == null) {
		  findNickName = "ok";
	  }else {
		  findNickName = "fail";
	  }
	  
	  return findNickName;
  }
  	@GetMapping(value = "/cafe/EmailCheck")
	public String EmailCheck(@RequestParam Map<String,String>map) {
		UsersDTO userDTO = cafeService.EmailCheck(map);
		 String findEmail;
		  if(userDTO == null) {
			  findEmail = "ok";
		  }else {
			  findEmail = "fail";
		  }
		return findEmail;
	}
  	
  	
  	@PostMapping(value ="/cafe/createMember")
  	public int createMember(@RequestParam Map<String,String>map) {
  		String user_type = map.get("user_type");
  		if(user_type == "") {
  			user_type = "user";	
  		}else if(Integer.parseInt(user_type) == 1){
  			user_type = "business";
  		}
  		map.put("user_type", user_type);
  		return cafeService.createMember(map);
  	}
  	
  	@PostMapping(value ="/cafe/Login")
  	public UsersDTO Login(@RequestParam Map<String,String>map) {
  		UsersDTO userDTO = cafeService.Login(map);
  		return userDTO;
  	}
  	 
	@GetMapping(value = "/cafe/listBoundary3000Mybatis")
	public List<CafeDTOCoordTemp> getCafesListBoundary3000(@RequestParam(value = "userLong") double userLong, @RequestParam(value = "userLat") double userLat){
	  
	  List<CafeDTOCoordTemp> list = cafeService.getCafesListBoundary3000Mybatis(userLong, userLat);
	  return list;
	}
	
	@GetMapping(value = "/cafe/listBoundaryMybatis")
	public List<CafeDTOCoordTemp> getCafesListBoundary(
	    @RequestParam(value = "userLong") double userLong, 
	    @RequestParam(value = "userLat") double userLat, 
	    @RequestParam(value = "boundary") int boundary,
	    @RequestParam(value = "openFilter") Boolean openFilter,
	    @RequestParam(value = "petFilter") Boolean petFilter,
	    @RequestParam(value = "parkingFilter") Boolean parkingFilter
	    )
	{
	  
	  List<CafeDTOCoordTemp> list = cafeService.getCafesListBoundary(userLong, userLat, boundary, openFilter, petFilter, parkingFilter);
	  return list;
	}
	
	@GetMapping(value = "/cafe/getCafeitemList")
	public List<CafeitemDTO>getCafeitemList(@RequestParam Map<String,String>map){
		return cafeService.getCafeitemList(map);
	}
	@GetMapping(value = "/cafe/updateCoordMybatis")
	public void updateCoord(@RequestParam(value = "longitude") double longitude, @RequestParam(value = "latitude") double latitude, @RequestParam(value = "cafe_id") long cafe_id) {
	  cafeService.updateCoordMybatis(longitude, latitude, cafe_id);
	  
	  return ;
	}
	
	@GetMapping(value = "/cafe/updateCafeinfo")
	public void updateCafeinfo(
	    @RequestParam(value = "opentime") int opentime, 
	    @RequestParam(value = "closetime") int closetime, 
	    @RequestParam(value = "pet") String pet,
	    @RequestParam(value = "parking") String parking,
	    @RequestParam(value = "cafe_id") int cafe_id
	    )
	  {
	    cafeService.updateCafeinfo(opentime, closetime, pet, parking, cafe_id);
  	}
	
	@GetMapping(value = "/cafe/getAllUserMybatis")
	public List<UsersDTO> getAllUser() {
	  return cafeService.getAllUser();
	}
	
	@GetMapping(value = "/cafe/updateUserMybatis")
	public void updateUser(
	  @RequestParam(value = "user_id") int user_id,
	  @RequestParam(value = "business_reg_num", required = false) int business_reg_num,
	  @RequestParam(value = "user_type") String user_type,
	  @RequestParam(value = "name") String name,
	  @RequestParam(value = "nickname") String nickname,
	  @RequestParam(value = "email") String email,
	  @RequestParam(value = "password") String password,
	  @RequestParam(value = "business_name", required = false) String business_name,
	  @RequestParam(value = "business_address", required = false) String business_address
	  ) 
	{
	  cafeService.updateUser(
	      user_id, business_reg_num, user_type, name, nickname, email, password, business_name, business_address
    );
	  return ;
	}
	
	@GetMapping(value = "/cafe/getCafeByInstaMybatis")
	public CafeDTO getCafeByInsta(@RequestParam(value = "insta_account")String insta_account) {
	  CafeDTO cafe = cafeService.getCafeByInsta(insta_account);
	  return cafe;
	}
	
}
