package com.ninza.hrm.api.employeetest;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ninza.hrm.api.baseClass.BaseAPI;
import com.ninza.hrm.api.pojoclass.EmployerPOJO;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndpoint;

public class EmployeeTest extends BaseAPI{
	@Test
	public void addEmployeTest() throws Throwable 
	{
		//create an object to pojo class
		String projectName="RR_"+jlib.getRandomNumber() ;
		String userName="user_"+jlib.getRandomNumber();
		ProjectPojo pObj=new ProjectPojo(projectName, "Created", "Abhi", 0);
		given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post(IEndpoint.ADDProj)
		.then()
			.spec(specResObj)
			.log().all();
		EmployerPOJO empObj=new EmployerPOJO("TE", "01/01/2000", "abc@gmail.com",userName, 3, "9090909090", projectName, "ROLE_EMPLOYEE", userName);
		given()
			.spec(specReqObj)
			.body(empObj)
		.when()
			.post(IEndpoint.ADDEmp)
		.then()
			.assertThat().statusCode(201)
			.time(Matchers.lessThan(3000L))
			.spec(specResObj)
			.log().all();
		//verify the project in DB layer
			boolean flag=dlib.executeQueryVerifyAndGetData("select * from employee", 11, userName);
			Assert.assertTrue(flag, "Project in DB is not verified");
			Assert.assertTrue(flag, "Employee's Username in DB is not verified");
	}
@Test
public void addEmployeWithOutEmailTest()
{
	
	String projectName="RR_"+jlib.getRandomNumber();
	String userName="user_"+jlib.getRandomNumber();
	ProjectPojo pObj=new ProjectPojo(projectName, "Created", "Abhi", 0);
	given()
		.spec(specReqObj)
		.body(pObj)
	.when()
		.post(IEndpoint.ADDProj)
	.then()
		.spec(specResObj)
		.log().all();
	EmployerPOJO empObj=new EmployerPOJO("TE", "01/01/2000", "",userName, 3, "9090909090", projectName, "ROLE_EMPLOYEE", userName);
	given()
		.spec(specReqObj)
		.body(empObj)
	.when()
		.post(IEndpoint.ADDEmp)
	.then()
		.assertThat().statusCode(500)
		.spec(specResObj)
		.log().all();
	}
}
