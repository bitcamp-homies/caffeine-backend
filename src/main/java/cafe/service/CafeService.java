package cafe.service;

import java.util.List;

import cafe.bean.jpa.CafeDTO;

public interface CafeService {

	List<CafeDTO> getCafeListAll();

}
