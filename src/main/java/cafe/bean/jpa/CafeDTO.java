package cafe.bean.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name="cafes")
@Data
public class CafeDTO {

	@Column(name="cafe_id", nullable = false, unique = true)
	@Id //primary key로 설정
	private int cafe_id;

	@Column(name="user_id", nullable = false, unique = true)
	private int user_id;

	@Column(name="cafe_name", nullable = false, unique = true, length = 200)
	private String cafe_name;

	@Column(name="address1", nullable = false, unique = true, length = 200)
	private String address1;

	@Column(name="address2", nullable = false, unique = true, length = 200)
	private String address2;

	@Column(name="address3", nullable = false, unique = true, length = 200)
	private String address3;

	@Column(name="address4", nullable = true, unique = true, length = 200)
	private String address4;

// cafes_pics added
  @Column(name="img_file", nullable = false, unique = true, length = 200)
	private String img_file;

	@Column(name="file_path", nullable = true, unique = true, length = 50)
	private String file_path;

//	@Column(name="coordinate", nullable=true, unique = true)
//	private Point coordinate;

	@Column(name="about", nullable = true, unique = true, length = 4000)
	private String about;

	@Column(name="subfolder", nullable = true, unique = true, length = 200)
	private String subfolder;
	
	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", 
				this.cafe_id,
				this.cafe_name,
				this.address1,
				this.address2,
				this.address3,
				this.address4,
//				this.coordinate.getX(),
//				this.coordinate.getY(),
				this.about,
				this.subfolder);
	}
}
