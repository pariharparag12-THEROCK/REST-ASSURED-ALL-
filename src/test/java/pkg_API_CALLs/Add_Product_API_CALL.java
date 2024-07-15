package pkg_API_CALLs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pkg_BASE_DATA.BaseDATAClass;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Add_Product_API_CALL extends BaseDATAClass{

	
	@Test
	public void AddProductToApplication() {
		
		
		String path =	System.getProperty("user.dir")+"\\src\\test\\resources\\Screenshot 2024-07-15 172227.png";
		
		File file = new File(path);
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
			Response resp 		=	given()
								
								.header("Authorization", TOKEN)
								
								.param("productName", "TShirt")
								.param("productAddedBy", USERID)
								.param("productCategory", "FASHION")
								.param("productSubCategory", "Shirt")
								.param("productPrice", 500)
								.param("productDescription", "Lee Cooper Tshirt")
								.param("productFor", "Men")
								.multiPart("productImage", file)
								
						
								.log().all()
								
								.when()
								
								.post("api/ecom/product/add-product")
								
								.then()
								
								.log().all()
								
								.extract()
							
								.response();
								
		
			JsonPath jp =	resp.jsonPath();
	
			PRODUCT_ID = jp.getString("productId");
			
			System.out.println("ProductId is fetching "+PRODUCT_ID);
		
		
		
	}
	
	
	
	
	
	
	
	
}
