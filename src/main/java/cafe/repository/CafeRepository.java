package cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cafe.bean.CafeDTO;

public interface CafeRepository extends JpaRepository<CafeDTO, String> {
	
	@Query("select cafeDTO from CafeDTO cafeDTO where cafeDTO.address2 = '강남구'")
	List<CafeDTO> getCafeListAllGangnam();
}
