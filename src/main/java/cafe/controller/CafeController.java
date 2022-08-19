package cafe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.service.CafeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
		 String check = cafeService.EmailCheck(map);
		 String findNickName;
		  if(check == null) {
			  findNickName = "ok";
		  }else {
			  findNickName = "fail";
		  }
		return findNickName;
	}
  	
  	
  	@PostMapping(value ="/cafe/createMember")
  	public int createMember(@RequestParam Map<String,String>map) {
  		String sung = map.get("sung");
  		String name = map.get("name");
  		String user_type = map.get("user_type");
  		if(user_type == "") {
  			user_type = "NomalUser";
  		}
  		map.put("name", sung+name);
  		map.put("user_type", user_type);
  		System.out.println(map);
  		return cafeService.createMember(map);
  	}
}
