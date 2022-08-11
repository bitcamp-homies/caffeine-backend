package cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.bean.CafeDTO;
import cafe.repository.CafeRepository;

@Service
public class CafeServiceImpl implements CafeService {

	@Autowired
	private CafeRepository cafeRepository;
	
	@Override
	public List<CafeDTO> getCafeListAll() {
		
		return cafeRepository.getCafeListAllGangnam();
	}
}
