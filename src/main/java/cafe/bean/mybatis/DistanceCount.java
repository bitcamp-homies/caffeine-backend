package cafe.bean.mybatis;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DistanceCount {
  private int OneKm;
  private int ThreeKm;
  private int SevenKm;
}
