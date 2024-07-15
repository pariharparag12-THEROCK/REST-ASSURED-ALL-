package pkg_API_CALLs;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pkg_BASE_DATA.BaseDATAClass;

import static io.restassured.RestAssured.*;

public class Order_PLaced extends BaseDATAClass {

	RequestSpecification requestspecification;
	ResponseSpecification responsespecification;
	
	@BeforeClass
	public void initRequestAndResponse() {
		
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		
		requestspecbuilder.setBaseUri("https://rahulshettyacademy.com/");
		requestspecbuilder.addHeader("Authorization", TOKEN);
		requestspecbuilder.log(LogDetail.ALL);
		requestspecification = requestspecbuilder.build();
		
		
		ResponseSpecBuilder responsespecbuilder = new ResponseSpecBuilder();
		
		responsespecbuilder.expectStatusCode(201);
		responsespecbuilder.log(LogDetail.ALL);
		responsespecification = responsespecbuilder.build();
		
	}
	
	
	
	@Test
	public void OrderPlacedIntoApplication() {
		
		Response resp =  given()
						
						.header("Content-Type", "application/json")
						
						.spec(requestspecification)
						
						.body("{\r\n"
								+ "  \"orders\": [\r\n"
								+ "    {\r\n"
								+ "      \"country\": \"British Indian Ocean Territory\",\r\n"
								+ "      \"productOrderedId\": \""+PRODUCT_ID+"\"\r\n"
								+ "    }\r\n"
								+ "  ]\r\n"
								+ "}")
															
						.when()
						
						.post("api/ecom/order/create-order")
						
						.then()
						
						.spec(responsespecification)
						
						.extract()
						
						.response();
		
		String response = resp.asPrettyString();
		
		JsonPath jp = new JsonPath(response);
		
		ORDER_ID = jp.getString("orders[0]");
		
		System.out.println("OrderId is fetching : "+ ORDER_ID);
		
	}

}
