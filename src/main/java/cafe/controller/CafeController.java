package cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;
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
	
	@GetMapping(value ="/cafe/listBoundaryMybatis")
	public List<CafePointTest> getCafeListBoundary(@RequestParam(value = "longitude") double userLong, @RequestParam(value = "latitude") double userLat){
	  
	  System.out.println("\n @Poong Log@ 받아온 데이터 : " + userLong +  " , " + userLat );
	  List<CafePointTest> list = cafeService.getCafeListBoundary(userLong, userLat);
	  
	  return list;
	}
}
