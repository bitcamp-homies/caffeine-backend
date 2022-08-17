package cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafePointTest;
import cafe.repository.jpa.CafeRepository;
import cafe.repository.mybatis.SelectMapper;

@Service
public class CafeServiceImpl implements CafeService {
  
	@Autowired
	private CafeRepository cafeRepository;

  @Autowired
  private SelectMapper selectMapper;
	
	@Override
	public List<CafeDTO> getCafeListAll() {
		
		return cafeRepository.getCafeListAllGangnam();
	}

  @Override
  public List<CafeDTOMybatis> getCafeListAllMybatis() {
    return selectMapper.getCafeListAll();
  }
  
  @Override
  public List<CafePointTest> getCafeListBoundary(double userLong, double userLat) {
    
    List<CafePointTest> list = selectMapper.getCafeListBoundary(userLong, userLat);
    
    return list;
  }
}
