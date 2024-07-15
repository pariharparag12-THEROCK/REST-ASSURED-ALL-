package complexResponse;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.util.List;


public class ComplexResponseBodyAPICall {

	
	@Test
	public void FetchDataFromComplexResponseBODY() {
		
		RestAssured.baseURI = "https://reqres.in";
		
					Response resp =	 given()
									
									.queryParam("page", "2")
							
									.when()
									
									.get("api/users")
									
									.then()
									
									//.log().all()
									
									.extract()
									
									.response();
		
					
				String response =	resp.asPrettyString();
		
				System.out.println(response);
				
				
				JsonPath jp =	resp.jsonPath();
				
				ResponseBodyJSON responsebodyjson =	resp.as(ResponseBodyJSON.class);
				
			
				
			int total_pages = responsebodyjson.getTotal_pages();
			System.out.println("Fetching Total Pages value :"+ total_pages);
				
			String	urlvalue=	jp.getString("support.url");
			System.out.println("Fetching Total Pages value :"+ urlvalue);
			
			
			String	firstnamevalue =  jp.getString("data[2].first_name");
			System.out.println("Fetching firstnamevalue  :"+ firstnamevalue);
			Assert.assertEquals(firstnamevalue, "Tobias");
			
			int totalnumberofelementInArray = jp.getInt("data.size()");
			System.out.println(totalnumberofelementInArray);
			
			for (int i=0; i<=totalnumberofelementInArray-1; i++) {
				
				String lastname = jp.getString("data["+i+"].last_name");
				
				System.out.println(lastname);
				
			}
			
			System.out.println();
			
			System.out.println("Fetching data from deserialization 1st way: ");
			
			List<DATA> data =    responsebodyjson.getData();	
			DATA dataindex = data.get(3);
			String EmailValue =	dataindex.getEmail();
			System.out.println("Fetching EmailValue is: "+ EmailValue);
			
			System.out.println();
			System.out.println("Fetching data from deserialization 2ndway: ");
			
			String emailvalue4 = responsebodyjson.getData().get(4).getEmail();
			System.out.println("Fetching EmailValue is: "+ emailvalue4);
			
			System.out.println();	
			System.out.println("Fetching data without using deserialization: ");
			
			String email = jp.getString("data[4].email");
			System.out.println("Fetching EmailValue is: "+ email);
	}
}
