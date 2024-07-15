package pkg_API_CALLs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pkg_BASE_DATA.BaseDATAClass;

import static io.restassured.RestAssured.*;

public class Delete_Order_API_CALL extends BaseDATAClass{
	
	@Test
	public void DeleteOrderInApplication() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		Response resp =		 given()
		
							.header("Authorization", TOKEN)
							
							.pathParam("KeyOfPathParam", "6695023cae2afd4c0b258ac3")
							
							.log().all()
							
							.when()
							
							.delete("api/ecom/order/delete-order/{KeyOfPathParam}")
							
							.then()
							
							.log().all()
							
							.extract()
							
							.response();
		
		
		JsonPath jp =	resp.jsonPath();
		
		String Deletemessage = jp.getString("message[0]");
		
		System.out.println("Fetching Delete Message : "+ Deletemessage);
		
				
	}

}
