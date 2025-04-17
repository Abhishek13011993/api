package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ninza.hrm.api.baseClass.BaseAPI;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndpoint;
import io.restassured.response.Response;

public class ProjectTest extends BaseAPI{
	ProjectPojo pObj;
	@Test
	public void addSingleProjectWithCreated() throws Throwable {
		String expMsg="Successfully Added";
		String projectName="RCB_"+jlib.getRandomNumber();
		pObj=new ProjectPojo(projectName, "Created", "Abhi", 0 );
		//verify the projectName in API layer
		Response resp =given()
				.spec(specReqObj)
				.body(pObj)
	    .when()
	    		.post(IEndpoint.ADDProj);
	    resp.then()
	    		.assertThat().statusCode(201)
	    		.assertThat().time(Matchers.lessThan(3000l))
	    		.spec(specResObj)
	    		.log().all();
	    String actMsg=resp.jsonPath().get("msg");
	    Assert.assertEquals(expMsg, actMsg);
	    //verify the project in DB layer
	    boolean flag=dlib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	    Assert.assertTrue(flag, "Project in DB is not verified");
	}
	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void createDuplicateProjectTest()
	{
		given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post(IEndpoint.ADDProj)
		.then()
			.assertThat().statusCode(409)
			.spec(specResObj)
			.log().all();		
	}
}
