package com.ninza.hrm.api.genericutility;

import java.util.List;
import com.jayway.jsonpath.JsonPath;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;


public class JsonUtils {
	public String getDataOnJsonPath(Response resp,String jsonXPath)
	{
		List<Object> list = JsonPath.read(resp.asString(), jsonXPath);
		return list.get(0).toString();
	}
	public String getDataOnXpathPath(Response resp,String xmlPath)
	{
		return resp.xmlPath().get(xmlPath);
	}
	public boolean VerifyDataOnJsonPath(Response resp,String jsonXpath,String expectedData)
	{
		List<String> list = JsonPath.read(resp.asString(),jsonXpath);
		boolean flag=false;
		for(String str:list)
		{
			if(str.equals(expectedData))
			{
				System.out.println(expectedData + " is available==PASS");
				flag=true;
			}
		}
		return flag;
	}
	public String getAccessToken()
	{
	
	Response resp= given()
		.formParam("client_id", "ninza-client")
		.formParam("client_secret","gPQBf1Yxew5OMccMhzos1GefIyiSnXzM")
		.formParam("grant_type","client_credentials")
	.when()
		.post("http://49.249.28.218.8180/auth/realms/ninza/protocol/openid-connect/token");
	resp.then()
		.log().all();
	String token=resp.jsonPath().get("access_token");
	return token;
	}
}
