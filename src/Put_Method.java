import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Put_Method {

	public static void main(String[] args) {
		// declare the base URL
		
				RestAssured.baseURI = "https://reqres.in/";
				
				String requestBody = "{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}";
				
				// validate status code
				
				int statusCode = given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().response().statusCode();
				
				System.out.println("status code is "+statusCode);
				// validate response body
				String responseBody = given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().response().asString();
				System.out.println("responseBody is "+responseBody);
				
				// parse the response and request body
				
				JsonPath jspr = new JsonPath(requestBody);
				String req_name = jspr.getString("name");
				String req_job = jspr.getString("job");
				
				JsonPath jsp = new JsonPath(responseBody);
				String res_name = jsp.getString("name");
				String res_job = jsp.getString("job");
				String res_updatedAt = jsp.getString("updatedAt");
				
				// generate updated At
				
				String expectedDate = res_updatedAt.substring(0,10);
				String currentDate = LocalDate.now().toString();
				System.out.println(expectedDate);
				
				// validate response body
				Assert.assertEquals(statusCode,200);
				Assert.assertEquals(req_name,res_name);
				Assert.assertEquals(req_job,res_job);
				Assert.assertEquals(expectedDate,currentDate);
				
			}

		}

	


