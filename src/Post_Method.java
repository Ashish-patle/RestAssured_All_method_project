import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Post_Method {

	public static void main(String[] args) {
		// Declare the base URL
		
				RestAssured.baseURI = "https://reqres.in/";
				
				// configure request body
				
				String requestBody = "{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"leader\"\r\n"
						+ "}";
				// fetch status code and response body
				
				int statusCode = given().header("Content-Type","application/json")
						.body(requestBody).when().post("api/users").then().extract().statusCode();
				System.out.println(statusCode);
				
				String responseBody = given().header("Content-Type","application/json")
						.body(requestBody).when().post("api/users").then().extract().response().asString();
				System.out.println(responseBody);
				
				// parse the request body and response body
				
				JsonPath jspr = new JsonPath(requestBody);
				String req_name = jspr.getString("name");
				System.out.println(req_name);
				String req_job = jspr.getString("job");
				System.out.println(req_job);
				
				JsonPath jsp = new JsonPath(responseBody);
				String res_name = jsp.getString("name");
				System.out.println(res_name);
				String res_job = jsp.getString("job");
				System.out.println(res_job);
				String res_id = jsp.getString("id");
				String res_createdAt = jsp.getString("createdAt");
				
				// generate new date
				
				String expectedDate = res_createdAt.substring(0,10);
				System.out.println(expectedDate);
				String currentDate = LocalDate.now().toString().substring(0,10);
				System.out.println(currentDate);
				
				// validate response body and status code
				Assert.assertEquals(statusCode,201);
				Assert.assertEquals(req_name,res_name);
				Assert.assertEquals(req_job,res_job);
				Assert.assertEquals(expectedDate,currentDate);
				Assert.assertNotNull(res_id);
				
			}

		}

	


