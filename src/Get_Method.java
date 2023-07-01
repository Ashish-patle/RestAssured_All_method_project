import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Get_Method {

	public static void main(String[] args) {
		//declare base URL
				RestAssured.baseURI = "https://reqres.in/api/";
				
				// validate status code
				int statusCode = given().header("Content-Type","application/json").when().get("/users?page=2").then().extract().response().statusCode();
				System.out.println("status code is "+statusCode);
				
				// validate response body
				
				String responseBody = given().header("Content-Type","application/json").when().get("/users?page=2").then().extract().response().asString();
				System.out.println("response body is "+responseBody);
				
				JsonPath jsp = new JsonPath(responseBody);
				int datasize = jsp.getList("data").size();
				
				// assert the total count object inside the data array
				
				Assert.assertEquals(datasize,6);
				
				// validate each object in data array
				
				for(int i=0; i<datasize; i++) {
					
				String id=jsp.getString("data ["+i+"].id");
				String email=jsp.getString("data ["+i+"].email");
				String fname=jsp.getString("data ["+i+"].first_name");
				String lname=jsp.getString("data ["+i+"].last_name");
				String avatar=jsp.getString("data ["+i+"].avatar");
				
				Assert.assertNotNull(id);
				Assert.assertNotNull(email);
				Assert.assertNotNull(fname);
				Assert.assertNotNull(lname);
				Assert.assertNotNull(avatar);
				
				Assert.assertTrue(Integer.parseInt(id)>=7 && Integer.parseInt(id)<=12);
				Assert.assertTrue(email.contains("@reqres.in"));
				
				}
					System.out.println("GET method");
				
			}
		}

	


