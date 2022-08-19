/*
 * $Id: Test.java,v 1.1 2006/04/15 14:40:06 platform Exp $
 * Created on 2006-4-15
 */
package org.json.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.Yytoken;
import org.junit.Test;

public class TestJson {

	@Test
	public void testDecode() throws Exception{
		System.out.println("=======decode=======");

		String s="[0,10]";
		Object obj=JSONValue.parse(s);

		// Added parseWithException test
		Object objExceptionString = JSONValue.parseWithException(s);

		JSONArray array=(JSONArray)obj;
		JSONArray arrayException = (JSONArray)objExceptionString;

		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println(arrayException.get(1));
		System.out.println();
		assertEquals("10",array.get(1).toString());
		assertEquals("10",arrayException.get(1).toString());
	}

	@Test
	public void testJSONValueDifferentTypes(){
		Integer intTest = 2;
		Float floatTest = 2.0f;
		Double doubleTest = 2.0;
		assertEquals("2", JSONValue.toJSONString(intTest));
		assertEquals("2.0", JSONValue.toJSONString(floatTest));
		assertEquals("2.0", JSONValue.toJSONString(doubleTest));
	}

	@Test
	public void testJSONArrayCollection() {
		final ArrayList<String> testList = new ArrayList<String>();
		testList.add("First item");
		testList.add("Second item");
		final JSONArray jsonArray = new JSONArray(testList);
		
		assertEquals("[\"First item\",\"Second item\"]", jsonArray.toJSONString());
	}

	@Test
	public void testJSONArrayDifferentTypes(){
		// Added JsonArray Test different types

		int[] intTest = new int[]{1, 2};
		byte[] byteTest = new byte[]{1, 2};
		double[] doubleTest = new double[]{1.0, 2.0};

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(1);
		jsonArray.add(2);
		assertEquals("[1,2]", jsonArray.toJSONString());

		jsonArray = new JSONArray();
		jsonArray.add(1.0);
		jsonArray.add(2.0);
		assertEquals("[1.0,2.0]", jsonArray.toJSONString());

		assertEquals("[1,2]", JSONArray.toJSONString(intTest));
		assertEquals("[1,2]", JSONArray.toJSONString(byteTest));
		assertEquals("[1.0,2.0]", JSONArray.toJSONString(doubleTest));
	}

	@Test
	public void testJSONObject() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Mahdi", "Amir");
		JSONObject jsonObject = new JSONObject(hashMap);
		String s = jsonObject.toJSONString();
		assertEquals( "{\"Mahdi\":\"Amir\"}", s);
		JSONParser jsonParser = new JSONParser();
		try{
			Object obj = jsonParser.parse(s);
			assertEquals( "{\"Mahdi\":\"Amir\"}", obj.toString());

		} catch (Exception e){
			System.out.println(e.toString());
		}


	}

	@Test
	public void testJSONParseException(){
		String s = ",";
		String s1 = "{[//]}";
		JSONParser jsonParser = new JSONParser();
		assertThrows(ParseException.class, () -> jsonParser.parse(s));
		assertThrows(ParseException.class, () -> jsonParser.parse(s1));
	}

	@Test
	public void testJSONYytoken(){
		Yytoken token = new Yytoken(Yytoken.TYPE_COMMA, null);
		assertEquals("COMMA(,)",token.toString());
	}
}
