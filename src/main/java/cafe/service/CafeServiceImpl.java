package cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.repository.jpa.CafeRepository;
import cafe.repository.mybatis.InsertMapper;
import cafe.repository.mybatis.SelectMapper;

@Service
public class CafeServiceImpl implements CafeService {
  
	@Autowired
	private CafeRepository cafeRepository;

	@Autowired
  	private SelectMapper selectMapper;
	
	@Autowired
	private InsertMapper insertMapper;
	
	@Override
	public List<CafeDTO> getCafeListAll() {
		
		return cafeRepository.getCafeListAllGangnam();
	}

  @Override
  public List<CafeDTOMybatis> getCafeListAllMybatis() {
    return selectMapper.getCafeListAll();
  }

@Override
public String NickNameCheck(Map<String, String> map) {
	return selectMapper.NickNameCheck(map);
}

@Override
public String EmailCheck(Map<String, String> map) {
	return selectMapper.EmailCheck(map);
	}

@Override
public int createMember(Map<String, String> map) {
	return insertMapper.createMember(map);
}
}
