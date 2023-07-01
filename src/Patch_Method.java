import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Patch_Method {

	public static void main(String[] args) {
		// declare base URL
		
				RestAssured.baseURI = "https://reqres.in/";
				
				String requestBody = "{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}";
				// validate the status code
				
				int statusCode = given().header("Content-Type","application/json").body(requestBody).when().patch("/api/users/2").then().extract().response().statusCode();
				System.out.println("statuscode is "+statusCode);
				
				String responseBody =given().header("Content-Type","application/json").body(requestBody).when().patch("/api/users/2").then().extract().response().asString();

				System.out.println("response body "+responseBody);
				
				// parse the response body
				JsonPath jspreq = new JsonPath(requestBody);
				String req_name = jspreq.getString("name");
				String req_job = jspreq.getString("job");
				

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

	


