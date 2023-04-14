/*
 * $Id: Test.java,v 1.1 2006/04/15 14:40:06 platform Exp $
 * Created on 2006-4-15
 */
package org.json.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.Yytoken;
import org.junit.Test;

public class TestJson {

    @Test
    public void testDecode() throws Exception {
        System.out.println("=======decode=======");

        String s = "[0,10]";
        Object obj = JSONValue.parse(s);
        JSONArray array = (JSONArray) obj;
        System.out.println("======the 2nd element of array======");
        System.out.println(array.get(1));
        System.out.println();
        assertEquals("10", array.get(1).toString());
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
    public void testJSONObjectMap() {
        final HashMap<String, String> testMap = new HashMap<>();
        testMap.put("First key", "First value");
        testMap.put("Second key", "Second value");
        final JSONObject jsonObject = new JSONObject(testMap);

        var string = jsonObject.toJSONString();
        assertTrue(string.contains("\"First key\":\"First value\""));
        assertTrue(string.contains("\"Second key\":\"Second value\""));
    }

    @Test
    public void testExceptionHandling() {
        final String testString = "This is not a valid JSON string";
        final Object result = JSONValue.parse(testString);
        assertEquals(null, result);

        final String testString2 = "{\"key\":\"value\"}}";
        final Object result2 = JSONValue.parse(testString2);
        assertEquals(null, result2);

        final String testString3 = "{\"key\":[\"value\"}}";
        final Object result3 = JSONValue.parse(testString3);
        assertEquals(null, result3);
    }

    @Test
    public void testEncode() throws Exception {
        System.out.println("=======encode=======");

        final JSONArray array = new JSONArray();
        array.add("first");
        array.add(100L);
        array.add(1000.21);
        array.add(Boolean.TRUE);
        array.add(null);

        final HashMap<String, String> map = new HashMap<>();
        map.put("name", "foo");
        map.put("num", "100");
        map.put("balance", "1000.21");
        map.put("is_vip", "true");
        map.put("nickname", null);
        array.add(map);

        System.out.println(array);
        System.out.println();

        final String jsonText = JSONValue.toJSONString(array);
        System.out.println(jsonText);
        System.out.println();

        final Object obj = JSONValue.parse(jsonText);
        final JSONArray array2 = (JSONArray) obj;
        System.out.println(array2);
        System.out.println();

        for (int i = 0; i < array.size(); i++) {
            assertEquals(array.get(i), array2.get(i));
        }
    }

    @Test
    public void testYytokenToString() {
        Yytoken token1 = new Yytoken(Yytoken.TYPE_VALUE, "test");
        assertEquals("VALUE(test)", token1.toString());

        Yytoken token2 = new Yytoken(Yytoken.TYPE_LEFT_BRACE, "{");
        assertEquals("LEFT BRACE({)", token2.toString());

        Yytoken token3 = new Yytoken(Yytoken.TYPE_RIGHT_BRACE, "}");
        assertEquals("RIGHT BRACE(})", token3.toString());

        Yytoken token4 = new Yytoken(Yytoken.TYPE_LEFT_SQUARE, "[");
        assertEquals("LEFT SQUARE([)", token4.toString());

        Yytoken token5 = new Yytoken(Yytoken.TYPE_RIGHT_SQUARE, "]");
        assertEquals("RIGHT SQUARE(])", token5.toString());

        Yytoken token6 = new Yytoken(Yytoken.TYPE_COMMA, ",");
        assertEquals("COMMA(,)", token6.toString());

        Yytoken token7 = new Yytoken(Yytoken.TYPE_COLON, ":");
        assertEquals("COLON(:)", token7.toString());
    }
}
