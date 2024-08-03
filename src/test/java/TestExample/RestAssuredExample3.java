package TestExample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Response;
import org.apache.xmlbeans.impl.repackage.Repackage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class RestAssuredExample3 {

	@Test
	public void createGet() {
		// Base URI
		RestAssured.baseURI = "https://api.restful-api.dev/objects";

		// GET request
		Response response = RestAssured.given().header("Content-Type", "application/json").when().get().then()
				.statusCode(200).extract().response();

		// Print the response
		String jsonString = response.asString();
		System.out.println("Response: " + jsonString);

		// Parse the response as JSONObject
		// JSONObject jsonResponse = new JSONObject(jsonString);

//        // Accessing data from JSONObject
//        System.out.println("Parsed JSON Response: " + jsonResponse.toString(4)); // Pretty print with indentation
//
//        // Example: Get a specific field from the JSON response
//        if (jsonResponse.has("key")) {
//            String value = jsonResponse.getString("key");
//            System.out.println("Value for 'key': " + value);
//        }
	}

	@Test
	public void createAdditionalgetCall() {
		
		RestAssured.baseURI = "https://api.restful-api.dev/objects";

        // Query parameters
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

	        // Print the response
	        String jsonString = response.asString();
	        System.out.println("Response: " + jsonString);

	        // Parse the response as JSONArray
	        JSONArray jsonResponse = new JSONArray(jsonString);
	        System.out.println("Parsed JSON Response: " + jsonResponse.toString(4)); // Pretty print with indentation

	        // Validate if id: 5 is present in the response
	        boolean isIdPresent = false;

	        for (int i = 0; i < jsonResponse.length(); i++) {
	            JSONObject jsonObject = jsonResponse.getJSONObject(i);
	            if (jsonObject.has("id") && jsonObject.getInt("id") == 5) {
	                isIdPresent = true;
	                break;
	            }
	        }

	        if (isIdPresent) {
	            System.out.println("ID 5 is present in the response.");
	        } else {
	            System.out.println("ID 5 is not present in the response.");
	        }
	        
	       JSONObject obj1 = jsonResponse.getJSONObject(1);
	       Assert.assertEquals( "Samsung Galaxy Z Fold2", obj1.getString("name"));
	        System.out.println("Passed");
	}
	
	
	@Test
	public void createGetCall2() {
		
		// Base URI
        RestAssured.baseURI = "https://api.restful-api.dev/objects";

        // GET request to /objects/7
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .when()
                .get("/7")
                .then()
                .statusCode(200)
                .extract().response();

        // Print the response
        String jsonString = response.asString();
        System.out.println("Response: " + jsonString);

        // Parse the response as JSONObject
        JSONObject jsonResponse = new JSONObject(jsonString);
        System.out.println("Parsed JSON Response: " + jsonResponse.toString(4)); // Pretty print with indentation

        // Validate specific fields in the response
      //  Assert.assertTrue("ID is not present in the response.", jsonResponse.has("id"));
        Assert.assertEquals("ID is not as expected.", 7, jsonResponse.getInt("id"));

      //  Assert.assertTrue("Name is not present in the response.", jsonResponse.has("name"));
        Assert.assertEquals("Name is not as expected.", "Apple MacBook Pro 16", jsonResponse.getString("name"));

      //  Assert.assertTrue("Category is not present in the response.", jsonResponse.has("category"));
     //   Assert.assertEquals("Category is not as expected.", "Expected Category", jsonResponse.getString("category")); 
		
		
		
	}
}
