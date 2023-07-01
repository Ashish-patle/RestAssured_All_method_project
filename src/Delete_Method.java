import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Delete_Method {

	public static void main(String[] args) {
		// declare base URL
				RestAssured.baseURI = "https://reqres.in/";
				
				// validate status Code
				
				int statusCode = given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().response().statusCode();
				System.out.println(statusCode);

	}

}
