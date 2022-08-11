package cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cafe.bean.CafeDTO;
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
}