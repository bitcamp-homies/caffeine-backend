package cafe.service;

import java.util.List;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOMybatis;

public interface CafeService {

	List<CafeDTO> getCafeListAll();
  CafeDTOMybatis findCafeIdOne();

}
