package cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.CafesDTO;
import cafe.bean.mybatis.Cafes_picsDTO;
import cafe.bean.mybatis.ProductsDTO;
import cafe.bean.mybatis.PaymentDTO;
import cafe.bean.mybatis.UserProfileDTO;
import cafe.bean.mybatis.UsersDTO;
import cafe.bean.mybatis.Cafes_product_listDTO;
import cafe.repository.jpa.CafeRepository;
import cafe.repository.mybatis.InsertMapper;
import cafe.repository.mybatis.SelectMapper;
import cafe.repository.mybatis.UpdateMapper;

@Service
public class CafeServiceImpl implements CafeService {

  @Autowired
  private CafeRepository cafeRepository;

  @Autowired
  private PaymentDTO paymentDTO;

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
  public List<CafeDTOCoordTemp> getCafesListBoundary(double userLong, double userLat, int boundary, Boolean openFilter, Boolean petFilter, Boolean parkingFilter) {
    
    int openFilterNum, petFilterNum, parkingFilterNum;
    if(openFilter) {
      openFilterNum = 1;
    }else {
      openFilterNum = 0;
    }
    if(petFilter) {
      petFilterNum = 1;
    }else {
      petFilterNum = 0;
    }
    if(parkingFilter) {
      parkingFilterNum = 1;
    }else {
      parkingFilterNum = 0;
    }
    
    List<CafeDTOCoordTemp> list = selectMapper.getCafesListBoundary(userLong, userLat, boundary, openFilterNum, petFilterNum, parkingFilterNum);
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
  public void updateCafeinfo(int opentime, int closetime, String pet, String parking, int cafe_id) {
    updateMapper.updateCafeinfo(opentime, closetime, pet, parking, cafe_id);
    
  }

  @Override
  public List<UsersDTO> getAllUser() {
    
    return selectMapper.getAllUser();
  }
  
  @Override
  public void updateUser(
      int user_id, 
      int business_reg_num, 
      String user_type, 
      String name, 
      String nickname,
      String email, 
      String password, 
      String business_name, 
      String business_address
      ) 
  {
    updateMapper.updateUser(user_id, business_reg_num, name, nickname, email, password, business_name, business_address);
    return ;
  }
  
  @Override
  public CafeDTO getCafeByInsta(String insta_account) {
    
    return selectMapper.getCafeByInsta(insta_account);
  }

  @Override
  public List<CafeitemDTO> getCafeitemList(Map<String, String> map) {
  	return selectMapper.getCafeitemList(map);
  }


@Override
public UsersDTO UserCheck(Map<String, String> map) {
	
	return selectMapper.UserCheck(map);
}


@Override
public int updateMember(Map<String, String> map) {
	return updateMapper.updateMember(map);
}
  
  
  @Override
  public List<CafeitemDTO> getCafeitem(Map<String, String> map) {
  	return selectMapper.getCafeitem(map);
  }
  
  
  @Override
  public UsersDTO getMember(Map<String, String> map) {
  	return selectMapper.getMember(map);
  }
  

//웅비 해당 제품정보 불러오기
@Override
public List<CafeitemDTO> getProductInfo(String product_id) {
  return selectMapper.getProductInfo(product_id);
}

//웅비 결제정보 저장하기
@Override
public void paymentList(Map<String, String> map) {
  insertMapper.paymentList(map);
}



@Override
public void InsertProfileimg(Map<String, String> map) {
		insertMapper.InsertProfileimg(map);
}


@Override
public UserProfileDTO selectProfileimg(Map<String, String> map) {
	return selectMapper.selectProfileimg(map);
}


@Override
public void updateProfileimg(Map<String, String> map) {
	updateMapper.updateProfileimg(map);

}


@Override
public void InsertCafes(Map<String, Object> map2) {
	insertMapper.InsertCafes(map2);
	
}


@Override
public CafesDTO getcafes(Map<String, String> map) {
	return selectMapper.getcafes(map);
}


@Override
public List<Cafes_picsDTO> getcafefics(Map<String, String> map) {
	return selectMapper.getcafefics(map);
}


@Override
public Cafes_picsDTO getcafeficsprofile(Map<String, String> map) {
	return selectMapper.getcafeficsprofile(map);
}


@Override
public void insertCafepics(Map<String, String> map) {
	insertMapper.insertCafepics(map);
}


@Override
public void updateCafepics(Map<String, String> map) {
	updateMapper.updateCafepics(map);
}


@Override
public int insertcafes_product_list(Map<String, String> map) {
	return insertMapper.insertcafes_product_list(map);
}


@Override
public int insertproducts(Map<String, String> map) {
	return insertMapper.insertproducts(map);
}


@Override
public ProductsDTO selectproducts(Map<String, String> map) {
	return selectMapper.selectproducts(map);
}


@Override
public Cafes_product_listDTO selectcafes_product_list(Map<String, String> map) {
	return selectMapper.selectcafes_product_list(map);
}


@Override
public void insertcafes_product_list_items(Map<String, String> map) {
	insertMapper.insertcafes_product_list_items(map);
	
}


@Override
public void insertproducts_img(Map<String, String> map) {
	insertMapper.insertproducts_img(map);
	
}


}
