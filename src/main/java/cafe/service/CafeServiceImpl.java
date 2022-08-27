package cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.UsersDTO;
import cafe.repository.jpa.CafeRepository;
import cafe.repository.mybatis.InsertMapper;
import cafe.repository.mybatis.SelectMapper;
import cafe.repository.mybatis.UpdateMapper;

@Service
public class CafeServiceImpl implements CafeService {

  @Autowired
  private CafeRepository cafeRepository;

  @Autowired
  private SelectMapper selectMapper;

  @Autowired
  private InsertMapper insertMapper;

  @Autowired
  private UpdateMapper updateMapper;

  @Override
  public List<CafeDTO> getCafeListAll() {
    return cafeRepository.getCafeListAllGangnam();
  }
 

  @Override
  public List<CafeDTOMybatis> getCafeListAllMybatis() {
    return selectMapper.getCafeListAll();
  }

  @Override
  public List<CafeDTOMybatis> getCafeDistLocation() {
    return selectMapper.getCafeDistLocation();
  }

  @Override
  public String NickNameCheck(Map<String, String> map) {
    return selectMapper.NickNameCheck(map);
  }

  @Override
  public UsersDTO EmailCheck(Map<String, String> map) {
    return selectMapper.EmailCheck(map);
  }

  @Override
  public int createMember(Map<String, String> map) {
    return insertMapper.createMember(map);
  }

  @Override
  public List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(double userLong, double userLat) {

    List<CafeDTOCoordTemp> list = selectMapper.getCafesListWithCoordMybatis(userLong, userLat);
    return list;

  }

  @Override
  public List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(double userLong, double userLat) {

    List<CafeDTOCoordTemp> list = selectMapper.getCafesListBoundary3000Mybatis(userLong, userLat);
    return list;
  }
  
  @Override
  public List<CafeDTOCoordTemp> getCafesListBoundary(double userLong, double userLat, int boundary) {
    
    List<CafeDTOCoordTemp> list = selectMapper.getCafesListBoundary(userLong, userLat, boundary);
    return list;
  }

  @Override
  public void updateCoordMybatis(double longitude, double latitude, long cafe_id) {

    updateMapper.updateCoordMybatis(longitude, latitude, cafe_id);

    return;
  }

  @Override
  public UsersDTO Login(Map<String, String> map) {
    UsersDTO userDTO = selectMapper.Login(map);
    return userDTO;
  }


@Override
public List<CafeitemDTO> getCafeitemList(Map<String, String> map) {
	return selectMapper.getCafeitemList(map);
}


@Override
public List<CafeitemDTO> getCafeitem(Map<String, String> map) {
	return selectMapper.getCafeitem(map);
}
}
