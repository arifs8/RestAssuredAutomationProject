package TestExample;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResTAssuredGetCall2 {
	
	private RequestSpecification  requestSpec;
	private ResponseSpecification responseSpec;

	
	@BeforeClass
    public void setup() {
		
		RestAssured.baseURI ="https://api.restful-api.dev/objects";
		
		 requestSpec = new RequestSpecBuilder()
				 .setBaseUri(RestAssured.baseURI)
				
				 .addHeader("Content-Type", "application/json")
				 .build();
	}
}
