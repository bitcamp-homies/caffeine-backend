package cafe.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cafe.bean.jpa.CafeDTO;
import cafe.bean.mybatis.CafeDTOCoordTemp;
import cafe.bean.mybatis.CafeDTOMybatis;
import cafe.bean.mybatis.CafeitemDTO;
import cafe.bean.mybatis.CafesDTO;
import cafe.bean.mybatis.Cafes_picsDTO;
import cafe.bean.mybatis.ProductsDTO;
import cafe.bean.mybatis.UserProfileDTO;
import cafe.bean.mybatis.UsersDTO;
import cafe.bean.mybatis.Cafes_product_listDTO;
import cafe.bean.mybatis.PaymentDTO;
@Repository
@Transactional
@Mapper
public interface SelectMapper { 

  @Select("SELECT c.*, u.insta_account, GROUP_CONCAT(cp.img_file) AS img_file, cp.file_path, cc.cafe_coord AS coord\r\n"
        + "FROM cafes c\r\n"
        + "INNER JOIN users u ON c.user_id = u.user_id\r\n"
        + "INNER JOIN cafes_pics cp ON c.cafe_id = cp.cafe_id\r\n"
        + "LEFT JOIN cafes_coord cc ON c.cafe_id = cc.cafe_id\r\n"
        + "GROUP BY c.cafe_id")
  public List<CafeDTOMybatis> getCafeListAll();

  @Select("select * from users where nickname =#{NickName}")
  public String NickNameCheck(Map<String, String> map);
  
  @Select("select * from users where email = #{Email}")
  public UsersDTO EmailCheck(Map<String, String> map);

  @Select("SELECT * FROM CafeDTO GROUP BY address2;")
  List<CafeDTOMybatis> getCafeDistLocation();

  //풍혁0818 : point mapping try1 >> success
  @Select("SELECT *, "
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "FROM CafeDTO")
  List<CafeDTOCoordTemp> getCafesListWithCoordMybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);

  //풍혁0819 : 3000m 이내의 카페만 져오기
  @Select("SELECT *, "
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "from CafeDTO "
      + "WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), coord) < 3000")
  List<CafeDTOCoordTemp> getCafesListBoundary3000Mybatis(@Param("userLong")double userLong, @Param("userLat")double userLat);

  //풍혁0826 : 변경되는 반경 ( + filter ) 적용시켜서 List가져오기
  @Select("SELECT *, "
      + "ST_Y(coord) AS latitude, "
      + "ST_X(coord) AS longitude, "
      + "ST_Distance_Sphere(POINT(${userLong}, ${userLat}), coord) AS distance "
      + "from CafeDTO "
      + "WHERE ST_Distance_Sphere(POINT(${userLong},${userLat}), coord) < ${boundary * 1000} AND "
      + "IF(${openFilter}, opentime <= HOUR(NOW())*100+MINUTE(NOW()) AND closetime >= HOUR(NOW())*100+MINUTE(NOW()), opentime > 0) AND "
      + "IF(${petFilter}, pet='Y', (pet='Y' || pet='N')) AND "
      + "IF(${parkingFilter}, parking='Y', (parking='N' || parking='Y')) "
      + "ORDER BY distance "
      )
  
  public List<CafeDTOCoordTemp> getCafesListBoundary(
      @Param("userLong") double userLong,
      @Param("userLat")double userLat,
      @Param("boundary")int boundary,
      @Param("openFilter")int openFilterNum, 
      @Param("petFilter")int petFilterNum, 
      @Param("parkingFilter")int parkingFilterNum
      );

  @Select("select * from users where email = #{id} and password = #{password}")
  public UsersDTO Login(Map<String, String> map);
  
  @Select("SELECT c.cafe_id, p.*, pi2.*,cpli.recommended\r\n"
        + "FROM cafes c\r\n"
        + "LEFT JOIN cafes_product_list cpl ON c.cafe_id = cpl.cafe_id\r\n"
        + "INNER JOIN cafes_product_list_items cpli ON cpl.product_list_id = cpli.product_list_id\r\n"
        + "LEFT JOIN products p ON cpli.product_id = p.product_id\r\n"
        + "INNER JOIN products_img pi2 ON p.product_id = pi2.product_id\r\n"
        + "WHERE c.cafe_id = ${cafe_id}")
  public List<CafeitemDTO> getCafeitemList(Map<String, String> map);


  
  @Select("SELECT c.cafe_id, p.*, pi2.*\r\n"
        + "FROM cafes c\r\n"
        + "LEFT JOIN cafes_product_list cpl ON c.cafe_id = cpl.cafe_id\r\n"
        + "INNER JOIN cafes_product_list_items cpli ON cpl.product_list_id = cpli.product_list_id\r\n"
        + "LEFT JOIN products p ON cpli.product_id = p.product_id\r\n"
        + "INNER JOIN products_img pi2 ON p.product_id = pi2.product_id\r\n"
        + "WHERE c.cafe_id = ${cafe_id} and pi2.product_id = ${product_id};")
  public List<CafeitemDTO> getCafeitem(Map<String, String> map);
  
  @Select("Select * from users where email = #{user_id}")
  public UsersDTO getMember(Map<String, String> map);
  
  @Select("Select * from users_profile_img where profile_id = #{user_id}")
  public UserProfileDTO selectProfileimg(Map<String, String> map);
  
  @Select("Select * from cafes where user_id = ${user_id}")
  public CafesDTO getcafes(Map<String, String> map);

  @Select("Select * from cafes_pics where cafe_id = ${cafe_id}")
  public List<Cafes_picsDTO> getcafefics(Map<String, String> map);

  @Select("Select * from cafes_pics where cafe_id =${cafe_id} and  img_file like '%profile%'")
  public Cafes_picsDTO getcafeficsprofile(Map<String, String> map);

  @Select("Select * from products where product_name_kor=#{product_name_kor} and product_name_eng = #{product_name_eng}"
        + "and category=#{category} and subcategory=#{subcategory} and price = #{price}")
  public List<ProductsDTO> selectproducts(Map<String, String> map);

  @Select("Select * from cafes_product_list where cafe_id=${cafe_id}")
  public List<Cafes_product_listDTO> selectcafes_product_list(Map<String, String> map);

  @Select("SELECT * from users")
  public List<UsersDTO> getAllUser();
  
  @Select("SELECT * from CafeDTO WHERE insta_account = #{insta_account}")
  public CafeDTO getCafeByInsta(@Param("insta_account")String insta_account);
        
  // 웅비 해당제품의 정보 가져오기
  @Select("select * from products where product_id = ${product_id}")
  public List<CafeitemDTO> getProductInfo(String product_id);
  
  // 웅비 결제 정보 가져오기
  @Select("SELECT payment.payment_num, payment.user_id ,payment.cafe_id ,payment.product_count ,payment.product_id ,payment.total_price ,payment.purchase_way,payment.create_At,products.product_name_kor from payment AS payment left outer join products  as products on payment.product_id = products.product_id")
  public List<PaymentDTO> getOrderList(String user_id);


  @Select("select cafe_id from like_cafes where list_id = (select list_id from like_lists where user_id = (select user_id from users where email = #{email}))")
  public List<Map<Object, Object>> getLikeList(@Param("email") String email);
	
}