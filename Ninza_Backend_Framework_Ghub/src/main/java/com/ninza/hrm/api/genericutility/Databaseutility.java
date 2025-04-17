package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class Databaseutility
{
	static Connection conn=null;
	static ResultSet resultset=null;
	static FileUtility flib=new FileUtility();
	
public void dBConnection() throws Exception
{
	Driver dri=new Driver();
	DriverManager.registerDriver(dri);
	conn = DriverManager.getConnection("url", "username", "password");
}

public void getdBConnection() throws Exception
{
	Driver dri=new Driver();
	DriverManager.registerDriver(dri);
	 conn = DriverManager.getConnection(flib.getDataFromPropertiesFile("DBUrl"),flib.getDataFromPropertiesFile("DB_Username"), flib.getDataFromPropertiesFile("DB_Password"));
}
public  boolean executeQueryVerifyAndGetData(String query,int columnIndex,String expectedData) throws SQLException
{
	boolean flag=false;
	ResultSet result = conn.createStatement().executeQuery(query);
	while(result.next())
	{
		if(result.getString(columnIndex).equals(expectedData))
		{
			flag=true;
			break;
		}
	}
	if(flag)
	{
		System.out.println(expectedData + "===> data verified in database table");
		return true;
	}else
	{
		System.out.println(expectedData + "===> data is not verified in database table");
		return false;
	}
}

public void closedBConnection() throws Exception
{
	conn.close();
}

public ResultSet executeSelectQuery(String query) throws Exception
{	
	    ResultSet result = null;
	Statement stat = conn.createStatement();
	result=stat.executeQuery(query);
	return result;
}

public int executeNonSelectQuery(String query) throws Exception
{
	int result=0;
	Statement stat = conn.createStatement();
	result=stat.executeUpdate(query);
	return result;
}
}
