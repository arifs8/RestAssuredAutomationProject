package TestExample;

import io.restassured.response.*;

import org.testng.annotations.Test;

import io.restassured.*;

public class TestExamples {

	
	@Test
	public void test1() {

	Response response =	RestAssured.get("https://api.restful-api.dev/objects");
	
	System.out.println(response.getStatusCode());
	System.out.println(response.getTime());
	System.out.println(response.getBody().asString());
	

	}

}
