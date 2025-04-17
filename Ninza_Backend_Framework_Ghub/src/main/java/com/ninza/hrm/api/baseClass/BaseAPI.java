package com.ninza.hrm.api.baseClass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.ninza.hrm.api.genericutility.Databaseutility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPI {
	public JavaUtility jlib=new JavaUtility();
	public FileUtility flib=new FileUtility();
	public Databaseutility dlib=new Databaseutility();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specResObj;
	
	@BeforeSuite
	public void configBS() throws Throwable
	{
		dlib.getdBConnection();
		System.out.println("=====Connect to DB====");
	    RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		builder.setBaseUri(flib.getDataFromPropertiesFile("BASEUri"));
		specReqObj = builder.build();
		
		ResponseSpecBuilder resbuilder=new ResponseSpecBuilder();
		resbuilder.expectContentType(ContentType.JSON);
		specResObj = resbuilder.build();
	}
	@AfterSuite
	public void configAS() throws Throwable
	{
		dlib.closedBConnection();;
		System.out.println("====Disconnect to DB====");
	}
}
