package DriverFactory;

import org.testng.annotations.Test;

public class Apptest {

	
	
	//first click add TESTNG LIBRARY THEN import org.testNG.annotation
	
	
	@Test    
	
	
	public void kickStart() throws Throwable
	//public static void main(String[] args) throws Throwable
	{
		
		
		Driverscript ds = new Driverscript();
		
		
		try
		
		
		{
			ds.startTest();
			
			
			
		}
		catch(Exception e)
		
		
		{
			e.printStackTrace();
			
			
		}
		
		
		}
		
	}
	






                                                     
