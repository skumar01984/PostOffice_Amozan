package action;

import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import util.Config;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostMathAPI extends Config{
	
	Response response;
	String result = null;
	String getresult = null;
	JsonPath jsonPath = null;
	List<Map<Object, Object>> resultList = null;

	
	public void getMathAPI() {
		System.out.println(mathJsonExpressionData);
		response = given()
				.auth().none()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when()
				.get(mathURL);
				
				
				
		System.out.println(response);		
				
		getresult = response.then().statusCode(200).extract().body().asString();
		
		System.out.println("Result : "+result);
		
		// String[] extractJson = text.replace("},", "}").replace("....",
		// "").split("<pre>");
		//String[] extractJson = text.replace("....", employeeData).split("<pre>");

		/*json = extractJson[1].split("</pre>");
		System.out.println("=================================== \n" + json[0]);*/

	}

	public void postMathAPI() {
		System.out.println(mathJsonExpressionData);
		response = given()
				.auth().none()
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when()
				.body(mathJsonExpressionData)
				.post(mathURL);
				/*.then()
				.statusCode(200)
				.body("", arg1, arg2)*/
				
				
		System.out.println(response);		
				
		result = response.then().statusCode(200).extract().body().asString();
		
		System.out.println("Result : "+result);
		
	}
	
	public void vaidateData(){
		
		jsonPath = new JsonPath(result);

		// First Way by get value: to get number of employee from list

		/*int result = jsonPath.getInt("data.id.size()");
		System.out.println("1 way using by get value: Total no. of employee : " + result);*/

		// Second Way by get list : to get number of employee from list

		resultList = jsonPath.getList("result");
		System.out.println("2 way by get List: Total no. of expression : " + resultList.size());

	}


	
	
	public static void main(String[] args){
		
		PostMathAPI pma = new PostMathAPI();
		pma.postMathAPI();
		pma.vaidateData();
		
	}
	
	/*
	@Test(priority = 0)
	public void CheckTotalNumberOfEmployees() {
		try {
			jsonPath = new JsonPath(json[0]);

			// First Way by get value: to get number of employee from list

			int numFromsResponse = jsonPath.getInt("data.id.size()");
			System.out.println("1 way using by get value: Total no. of employee : " + numFromsResponse);

			// Second Way by get list : to get number of employee from list

			employeeList = jsonPath.getList("data");
			System.out.println("2 way by get List: Total no. of employee : " + employeeList.size());

			assertEquals(employeeList.size(), 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void getEmployeeID() {
		try {

			// To get the employee data id from list

			jsonPath = new JsonPath(json[0]);

			// 1. Way : To get the second employee id using json parser

			String id = jsonPath.getString("data.id[1]");

			// Second employee data is not present in the response text.
			// Extracted Json will return null

			if (id == null) {
				System.out.println("Second employee id is not present in the response text." + id);
			} else {
				System.out.println("Second employee id is present in the response text." + id);
			}

			System.out.println("=======================================================================\n");
			// assertEquals(id, null);

			// 2. Way using Has Map : To get the second employee id using Has Map
			if (employeeList.size() > 1) {

				
				 * employeeList.forEach(empData -> { System.out.println("Employee Data is : " +
				 * empData); }); System.out.println(
				 * "=======================================================================\n");
				 
				String empkey = null, empValue = null;
				for (int i = 1; i < employeeList.size(); i++) {

					Map<Object, Object> map = employeeList.get(i);
					if (map.containsKey("id")) {
						for (Map.Entry<Object, Object> entry : map.entrySet()) {
							empkey = entry.getKey().toString();
							empValue = entry.getValue().toString();
							System.out.println("Second employee data as key: " + empkey + " value: " + empValue);

							
							 * if (empkey.equals("id")) {
							 * System.out.println("Second employee data is present in the JSON. as Key : " +
							 * empkey + " ==> " + empValue); System.out.println(
							 * "================================================================="); }
							 

						}

					} else {
						empkey = null;
						empValue = null;
						System.out.println("Second employee data is not present in the JSON. as Key : " + empkey
								+ " ==> " + empValue);
						System.out.println("=======================================================================");
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/


}
