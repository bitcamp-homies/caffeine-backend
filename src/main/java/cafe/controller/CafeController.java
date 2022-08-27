package cafe.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
import cafe.bean.mybatis.UserProfileDTO;
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
	  // System.out.println("\n @PH LOG@ listBoundary3000... 넘어온 사용자 경위도 : " + userLong + "\t" + userLat);
    
	  List<CafeDTOCoordTemp> list = cafeService.getCafesListBoundary3000Mybatis(userLong, userLat);
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
	@GetMapping(value ="/cafe/getCafeitem")
	public List<CafeitemDTO>getCafeitem(@RequestParam Map<String,String>map){
		return cafeService.getCafeitem(map);
	}
	
	@PostMapping(value ="cafe/kakaopay")
	public String kakaopay(@RequestParam Map<String,String>map) {
		String cid = map.get("cid");
		String total_amount = map.get("total_amount");
		String item_name = map.get("item_name");
		String quantity = map.get("quantity");
		String partner_order_id = map.get("partner_order_id");
		String partner_user_id = map.get("partner_user_id");
		String vat_amount = map.get("vat_amount");
		String tax_free_amount = map.get("tax_free_amount");
		String approval_url = map.get("approval_url");
		String fail_url = map.get("fail_url");
		String cancel_url = map.get("cancel_url");
		
		try {
			URL address = new URL("http://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection serverConnection = (HttpURLConnection) address.openConnection();
			serverConnection.setRequestMethod("POST");
			serverConnection.setRequestProperty("Authorization", "KakaoAK 12659db36fb4e183b8d2a7e1a42c8b14");
			serverConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			serverConnection.setDoOutput(true);
			String parameter = "cid="+cid+
							   "&total_amount="+total_amount+
							   "&item_name="+item_name+
							   "&quantity="+quantity+
							   "&partner_order_id="+partner_order_id+
							   "&partner_user_id="+partner_user_id+
							   "&vat_amount="+vat_amount+
							   "&tax_free_amount="+tax_free_amount+
							   "&approval_url="+approval_url+
							   "&fail_url="+fail_url+
							   "&cancel_url="+cancel_url;
			OutputStream output = serverConnection.getOutputStream();
			DataOutputStream dataoutput = new DataOutputStream(output);
			dataoutput.writeBytes(parameter);
			dataoutput.close();
			
			int result = serverConnection.getResponseCode();
			
			InputStream input;
			if(result == 200) { //정상 통신 200
				input = serverConnection.getInputStream();
			}else {
				input = serverConnection.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(input); //input 된것을 읽는다.
			BufferedReader bufferedReader = new BufferedReader(reader);
			return bufferedReader.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping("/cafe/getMember")
	public UsersDTO getMember(@RequestParam Map<String,String>map) {
		return cafeService.getMember(map);
	}

	@PostMapping("/cafe/InsertProfileimg")
	public void InsertProfileimg(@RequestParam Map<String,String>map) {
		cafeService.InsertProfileimg(map);
	}
	
	@PostMapping("/cafe/selectProfileimg")
	public UserProfileDTO selectProfileimg(@RequestParam Map<String,String>map) {
		return cafeService.selectProfileimg(map);
	}
	@PostMapping("/cafe/updateProfileimg")
	public void updateProfileimg(@RequestParam Map<String,String>map) {
		cafeService.updateProfileimg(map);
	}
}
