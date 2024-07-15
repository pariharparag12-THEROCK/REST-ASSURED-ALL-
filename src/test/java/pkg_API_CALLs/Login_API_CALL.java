package pkg_API_CALLs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import pkg_BASE_DATA.BaseDATAClass;

import static io.restassured.RestAssured.*;
import pkg_POJO_CLASS.LoginRequestBody;
import pkg_POJO_CLASS.LoginResponseBody;

public class Login_API_CALL extends BaseDATAClass{

	@Test
	public void LoginToApplication() {
		 
		
		LoginRequestBody loginrequestbody = new LoginRequestBody();
		
		loginrequestbody.setUserEmail("restassuredTesting@gmail.com");
		loginrequestbody.setUserPassword("Thunderball@13");
		
		
		RestAssured.baseURI ="https://rahulshettyacademy.com/";
		
		
				Response	resp = 	given()
		
							.body(loginrequestbody)
							
							.header("Content-Type", "application/json")
							
							.log().all()
							
							.when()
							
							.post("api/ecom/auth/login")
							
							.then()
							
							.log().all()
							
							.extract()
							
							.response();
		
		
			int statuscode = 	resp.statusCode();
			Assert.assertEquals(statuscode, 200);
				
			
			LoginResponseBody loginresponsebody = resp.as(LoginResponseBody.class);
			
			TOKEN =	loginresponsebody.getToken();
			System.out.println("Token is fetching "+ TOKEN);
			
			MESSAGE = loginresponsebody.getMessage();
			System.out.println("Message is fetching "+ MESSAGE);
			
			USERID = loginresponsebody.getUserId();
			System.out.println("USERID is fetching "+ USERID);
				
	}

}
