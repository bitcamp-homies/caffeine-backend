package cafe.bean.mybatis;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CafePointTest {
	private long gid;
	private Point pt;
}
