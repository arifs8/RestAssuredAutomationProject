package TestExample;

import org.apache.xmlbeans.impl.repackage.Repackage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredForPost {
	
	@Test
	public void berifyPostCall1() {
		
		
		RestAssured.baseURI ="https://api.restful-api.dev/objects";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Apple MacBook Pro 16");
		
		JSONObject data = new JSONObject();
		data.put("year", 2019);
        data.put("price", 1849.99);
        data.put("CPU model", "Intel Core i9");
        data.put("Hard disk size", "1 TB");
        
        requestBody.put("data", data);
        
        
        Response response = RestAssured
        						.given()
        							.header("Content-Type", "application/json")
        							.body(requestBody.toString())
        						.when()
        							.post()
        						.then()
        							.statusCode(200)
        							.extract().response();
        
        
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
        
        JSONObject jsb1 = new JSONObject(jsonResponse);
        
        String name = jsb1.getString("name");
        String cpuName = jsb1.getJSONObject("data").getString("CPU model");
        
        Assert.assertEquals("Apple MacBook Pro 16",name);
        Assert.assertEquals("Intel Core i9",cpuName);
        
        
		
	}
	
	
	@Test
	public void verifyPostCall2() {
		
		
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		JSONObject requestBody1 = new JSONObject();
		requestBody1.put("name", "morpheus");
		requestBody1.put("job", "leader");
		
		
		Response response = RestAssured 
								.given()
									.header("Content-Type", "application/json")
									.body(requestBody1.toString())
								.when()
									.post()
								.then()
									.statusCode(201)
									.extract().response();
		
		String jsonOutput = response.asString();
		System.out.println(jsonOutput);
		
		JSONObject jsb1 = new JSONObject(jsonOutput);
		System.out.println(jsb1);
		
		String name = jsb1.getString("name");
		String job = jsb1.getString("job");
		String id = jsb1.getString("id");
		
		
		Assert.assertEquals("morpheus", name);
		Assert.assertEquals("morpheus", name);
		Assert.assertEquals("morpheus", name);
		
									
	}
	
	@Test
	public void verifyPutCall() {
		
		RestAssured.baseURI = "https://reqres.in/api/users/2";
		
		JSONObject jsb2 = new JSONObject();
		jsb2.put("name", "morpheus");
		jsb2.put("job", "zion resident");
		
		Response response = RestAssured
								.given()
									.header("Content-Type", "application/json")
									.body(jsb2.toString())
								.when()
									.put()
								.then()
									.statusCode(200)
									.extract().response();
		
		String jsonResponse = response.asString();
		
		System.out.println(jsonResponse);
		
		JSONObject obj = new JSONObject(jsonResponse);
		
		String name = obj.getString("name");
		String job = obj.getString("job");
		
		Assert.assertEquals("morpheus", name);
		Assert.assertEquals("zion resident", job);
		
									
		
		
	}

}
