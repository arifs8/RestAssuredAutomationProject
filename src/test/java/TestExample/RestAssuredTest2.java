package TestExample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestAssuredTest2 {

    @BeforeClass
    public void setup() {
        // Initialize base URI
        RestAssured.baseURI = "https://api.restful-api.dev"; // Base URI
    }

    @SuppressWarnings("unchecked")
	@Test
    public void createObjectTest() {
        // Create data JSON object
        JSONObject data = new JSONObject();
        data.put("year", 2019);
        data.put("price", 1849.99);
        data.put("CPU model", "Intel Core i9");
        data.put("Hard disk size", "1 TB");

        // Create main JSON object
        JSONObject payload = new JSONObject();
        payload.put("name", "Apple MacBook Pro 16");
        payload.put("data", data);

        // Perform POST request
        Response response = given()
                                .contentType(ContentType.JSON)
                                .body(payload.toString())
                             .when()
                                .post("/objects") // Endpoint path
                             .then()
                                .statusCode(201)
                                .contentType(ContentType.JSON)
                                .extract().response();

        // Parse response JSON
        JSONObject jsonResponse = new JSONObject(response.asString());

        // Assertions (adjust according to actual response structure)
        assertEquals(jsonResponse.getString("name"), "Apple MacBook Pro 16");
        JSONObject responseData = jsonResponse.getJSONObject("data");
        assertEquals(responseData.getInt("year"), 2019);
        assertEquals(responseData.getDouble("price"), 1849.99);
        assertEquals(responseData.getString("CPU model"), "Intel Core i9");
        assertEquals(responseData.getString("Hard disk size"), "1 TB");
    }
    
    
    
    @Test
    public void createGetCall() {
    	
    	// Perform GET request
        Response response1 = given()
                                .contentType(ContentType.JSON)
                                
                             .when()
                                .post("/objects") // Endpoint path
                             .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .extract().response();
        
        
     // Parse response JSON
        JSONArray jsonResponse = new JSONArray(response1.asString());

        // Validate the response
        assertTrue(jsonResponse.length() > 0, "Response should contain objects");

        // Sample assertion for the first object in the array (adjust according to actual response structure)
        JSONObject firstObject = jsonResponse.getJSONObject(0);
        assertTrue(firstObject.has("id"), "First object should have 'id' field");
        assertTrue(firstObject.has("name"), "First object should have 'name' field");

        // Print the response for visual inspection (optional)
        System.out.println(jsonResponse.toString(2));
    	
    }
}
