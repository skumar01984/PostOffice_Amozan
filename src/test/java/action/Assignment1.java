package action;

import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
//import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import org.junit.Before;
//import org.testng.annotations.Test;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import util.Config;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Assignment1 extends Config {

	Response response;
	String result = null;
	String getresult = null;
	JsonPath jsonPath = null;
	//List<Map<Object, Object>> resultList = null;
	List<String> resultList = null;
	static String exprValue;
	WebDriver driver;
	public String getMathAPI(String value) {
		response = given().auth().none().header("Content-Type", "application/json").contentType(ContentType.JSON).when()
				.get(mathURL + "?expr=" + value);

		System.out.println(response);

		//getresult = response.then().statusCode(200).extract().body().asString();
		getresult = response.body().asString();

		System.out.println("Result : " + getresult);
		
		return getresult;
	}
	
	@Before
	public void postMathAPI() {
		System.out.println(mathJsonExpressionData);
		response = given().auth().none().header("Content-Type", "application/json").contentType(ContentType.JSON).when()
				.body(mathJsonExpressionData).post(mathURL);
		
		//System.out.println(response);
		Assert.assertEquals(response.getStatusCode(), 200);

		result = response.body().asString();

		System.out.println("Result : " + result);
		

	}

	@Test
	public void validateExpression() {

		jsonPath = new JsonPath(result);

		resultList = jsonPath.getList("result");
				
		System.out.println("Total no. of expression : " + resultList.size());
		
		// 1 Way
		for (int i=0; i<resultList.size(); i++) {
		
		exprValue = jsonPath.getString("result["+i+"]");
		String getExpressionValue = getMathAPI(resultList.get(i));
		
		/*if (getExpressionValue.equals(exprValue)) {
			System.out.println("Expression of list is correct");
		}*/
		
		Assert.assertTrue(getExpressionValue.equals(exprValue));
		
	
	}
	
		// 2 way
		/*for (int i=0; i<resultList.size(); i++) {
			
			String exprValue = getMathAPI(resultList.get(i));
			
			while (resultList.get(i).contentEquals(exprValue)) {
				System.out.println("Expression of list is correct");
				break;
			}

		}
*/
	}

}
