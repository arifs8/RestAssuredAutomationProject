package TestExample;

import org.apache.xmlbeans.impl.repackage.Repackage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredForGet {
	
	@Test
	public void verifyGetCall1() {
		
		RestAssured.baseURI= "https://api.restful-api.dev";
		
		Response response = RestAssured
								.given()
								.header("Content-Type", "application/json")
								.when()
								.get("/objects")
								.then()
								.statusCode(200)
								.extract().response();
		
		String jsponResponse = response.asString();
		System.out.println(jsponResponse);

		JSONArray js = new JSONArray(jsponResponse);
		System.out.println(js.toString(2));

		JSONObject jsonObje = js.getJSONObject(9);
		System.out.println("XXXXXXXXXXXXXXXXXXX" + jsonObje);

		// JSONObject js1 = new JSONObject(js);
		Assert.assertEquals("Apple iPad Mini 5th Gen", jsonObje.getString("name"));
		
		
		boolean isNamePresent = false;

        for (int i = 0; i < js.length(); i++) {
            JSONObject jsonObject = js.getJSONObject(i);
            if (jsonObject.has("name") && jsonObject.getString("name").equals("Apple iPad Mini 5th Gen")) {
                isNamePresent = true;
                break;
            }
        }
	}
	
	
	@Test
	public void verifyGetCall2() {
		
		RestAssured.baseURI="https://api.restful-api.dev/objects?id=3&id=5&id=10";
		
		String[] ids = {"3", "5", "10"};
		
		Response response = RestAssured
								.given()
									.header("Content-Type", "application/json")
									.queryParam("id", ids)
								.when()
									.get()
								.then()
									.statusCode(200)
									.extract().response();
		
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		JSONArray jsa = new  JSONArray(jsonResponse);
		System.out.println(jsa.toString(2));
		
		JSONObject jsb = jsa.getJSONObject(1);
		System.out.println(jsb);
		
		Assert.assertEquals("5", jsb.getString("id"));
		
		JSONObject jsb1=  jsb.getJSONObject("data");
		Assert.assertEquals("Brown", jsb1.getString("color"));
		
	}
	
	
	@Test
	public void verifyGetCall3() {
		
		
		RestAssured.baseURI = "https://api.restful-api.dev/objects";
		
		Response response = RestAssured
				 				.given()
				 					.header("Content-Type", "application/json")
				 				.when()
				 					.get("/7")
				 				.then()
				 				.statusCode(200)
				 					.extract().response();
		
		String jsonResponse1 = response.asString();
		System.out.println(jsonResponse1);
		
		
		JSONObject jsb = new JSONObject(jsonResponse1);
		
		String id = jsb.getString("id");
		int year = jsb.getJSONObject("data").getInt("year");
		
		Assert.assertEquals( "7", id);
		
		Assert.assertEquals( 2019, year);
		
		
	}
}
