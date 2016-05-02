package com.techmask.ressack;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    
	RestTemplate template = new TestRestTemplate();
	
	/**
     * Create the test case 
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	Map request = new HashMap();
    	Map tokenMap = new HashMap();
    	tokenMap.put("email", "edison_cyh@hotmail.com");
    	request.put("token",tokenMap);
    	HttpHeaders headers = template.postForEntity("http://localhost:8080/book/buy",request,java.lang.String.class).getHeaders();
    	System.out.println(headers);
        assertTrue( true );
    }
}
