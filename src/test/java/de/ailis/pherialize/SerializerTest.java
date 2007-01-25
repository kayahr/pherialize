/*
 * $Id$
 * Copyright (c) 2006 Klaus Reimer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to 
 * deal in the Software without restriction, including without limitation the 
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE. 
 */

package de.ailis.pherialize;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test class for Serializer
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class SerializerTest extends TestCase
{
    /**
     * Returns the test suite.
     * 
     * @return The test suite
     */

    public static Test suite()
    {
        return new TestSuite(SerializerTest.class);
    }


    /**
     * Tests serializing a string
     */

    public void testSerializeString()
    {
        String test;

        test = "Test";
        assertEquals("s:4:\"Test\";", Pherialize.serialize(test));
    }


    /**
     * Tests serializing a string
     */

    public void testSerializeCharacter()
    {
        Character test;

        test = new Character('t');
        assertEquals("s:1:\"t\";", Pherialize.serialize(test));
    }


    /**
     * Tests serializing an integer value.
     */

    public void testSerializeInteger()
    {
        assertEquals("i:0;", Pherialize.serialize(Integer.valueOf(0)));
        assertEquals("i:-1;", Pherialize.serialize(Integer.valueOf(-1)));
        assertEquals("i:2147483647;", Pherialize.serialize(Integer
            .valueOf(2147483647)));
        assertEquals("i:-2147483648;", Pherialize.serialize(Integer
            .valueOf(-2147483648)));
    }


    /**
     * Tests serializing a short value.
     */

    public void testSerializeShort()
    {
        assertEquals("i:0;", Pherialize.serialize(Short.valueOf((short) 0)));
        assertEquals("i:-1;", Pherialize.serialize(Short.valueOf((short) -1)));
        assertEquals("i:32767;", Pherialize.serialize(Short
            .valueOf((short) 32767)));
        assertEquals("i:-32768;", Pherialize.serialize(Short
            .valueOf((short) -32768)));
    }


    /**
     * Tests serializing a byte value.
     */

    public void testSerializeByte()
    {
        assertEquals("i:0;", Pherialize.serialize(Byte.valueOf((byte) 0)));
        assertEquals("i:-1;", Pherialize.serialize(Byte.valueOf((byte) -1)));
        assertEquals("i:127;", Pherialize.serialize(Byte.valueOf((byte) 127)));
        assertEquals("i:-128;", Pherialize.serialize(Byte.valueOf((byte) -128)));
    }


    /**
     * Tests serializing a long value.
     */

    public void testSerializeLong()
    {
        assertEquals("i:0;", Pherialize.serialize(Long.valueOf(0)));
        assertEquals("i:-1;", Pherialize.serialize(Long.valueOf(-1)));
        assertEquals("i:2147483647;", Pherialize.serialize(Long
            .valueOf(2147483647)));
        assertEquals("i:-2147483648;", Pherialize.serialize(Long
            .valueOf(-2147483648)));
        assertEquals("d:2147483648;", Pherialize.serialize(Long
            .valueOf(2147483648L)));
        assertEquals("d:-2147483649;", Pherialize.serialize(Long
            .valueOf(-2147483649L)));
    }


    /**
     * Tests serializing a double value.
     */

    public void testSerializeDouble()
    {
        assertEquals("d:0.0;", Pherialize.serialize(Double.valueOf(0)));
        assertEquals("d:-1.0;", Pherialize.serialize(Double.valueOf(-1)));
        assertEquals("d:-10.1;", Pherialize.serialize(Double.valueOf(-10.1)));
        assertEquals("d:545.159122;", Pherialize.serialize(Double
            .valueOf(545.159122)));
    }


    /**
     * Tests serializing a float value.
     */

    public void testSerializeFloat()
    {
        assertEquals("d:0.0;", Pherialize.serialize(Float.valueOf(0)));
        assertEquals("d:-1.0;", Pherialize.serialize(Float.valueOf(-1)));
        assertEquals("d:-10.100000381469727;", Pherialize.serialize(Float
            .valueOf((float) -10.100000381469727)));
        assertEquals("d:545.1591186523438;", Pherialize.serialize(Float
            .valueOf((float) 545.1591186523438)));
    }


    /**
     * Tests serializing a boolean value.
     */

    public void testSerializeBoolean()
    {
        assertEquals("b:0;", Pherialize.serialize(Boolean.FALSE));
        assertEquals("b:1;", Pherialize.serialize(Boolean.TRUE));
    }


    /**
     * Tests serializing a null value.
     */

    public void testSerializeNull()
    {
        assertEquals("N;", Pherialize.serialize(null));
    }


    /**
     * Tests serializing a collection of various values.
     */

    public void testSerializeCollection()
    {
        List test;

        test = new ArrayList();
        test.add("Test");
        test.add(Integer.valueOf(12345));
        test.add(Boolean.TRUE);
        assertEquals("a:3:{i:0;s:4:\"Test\";i:1;i:12345;i:2;b:1;}", Pherialize
            .serialize(test));
    }


    /**
     * Tests serializing a map of various values.
     */

    public void testSerializeMap()
    {
        Map test;

        test = new LinkedHashMap();
        test.put("key1", "Test");
        test.put(Integer.valueOf(1), Integer.valueOf(12345));
        test.put("key2", Boolean.TRUE);
        assertEquals(
            "a:3:{s:4:\"key1\";s:4:\"Test\";i:1;i:12345;s:4:\"key2\";b:1;}",
            Pherialize.serialize(test));
    }


    /**
     * Tests serializing an array with a reference in it.
     */

    public void testSerializeReference()
    {
        List test;
        List test1;
        List test2;

        test = new ArrayList();
        test1 = new ArrayList();
        test2 = new ArrayList();
        test1.add("Test");
        test2.add("Test");
        test.add(test1);
        test.add(test2);
        test.add(test1);
        test.add(test2);
        test.add(test1);
        assertEquals(
            "a:5:{i:0;a:1:{i:0;s:4:\"Test\";}i:1;a:1:{i:0;s:4:\"Test\";}i:2;R:2;i:3;R:4;i:4;R:2;}",
            Pherialize.serialize(test));
    }


    /**
     * Tests serializing a complex array
     */

    public void testComplex()
    {
        Map arthur, ford;
        List persons;

        persons = new ArrayList();

        arthur = new LinkedHashMap();
        arthur.put("name", "Arthur Dent");
        arthur.put("age", Integer.valueOf(43));
        arthur.put("earthling", Boolean.TRUE);
        arthur.put("special", null);

        ford = new LinkedHashMap();
        ford.put("name", "Ford Prefect");
        ford.put("age", Integer.valueOf(39));
        ford.put("earthling", Boolean.FALSE);
        ford.put("special", null);

        arthur.put("comrade", ford);
        ford.put("comrade", arthur);

        persons.add(arthur);
        persons.add(ford);

        assertEquals(
            "a:2:{i:0;a:5:{s:4:\"name\";s:11:\"Arthur Dent\";s:3:\"age\";i:43;s:9:\"earthling\";b:1;s:7:\"special\";N;s:7:\"comrade\";a:5:{s:4:\"name\";s:12:\"Ford Prefect\";s:3:\"age\";i:39;s:9:\"earthling\";b:0;s:7:\"special\";N;s:7:\"comrade\";R:2;}}i:1;R:7;}",
            Pherialize.serialize(persons));
    }


    /**
     * Tests serializing a Serializable object
     */

    public void testSerializeSerializable()
    {
        Person person;

        person = new Person("Arthur Dent", 42, true, null);
        assertEquals("O:6:\"person\":4:{s:4:\"name\";s:11:\"Arthur Dent\";s:3:\"age\";i:42;s:9:\"earthling\";b:1;s:7:\"special\";N;}",
            Pherialize.serialize(person));
    }


    /**
     * Tests serializing a Serializable inherited object
     */

    public void testSerializeInheritedSerializable()
    {
        InheritedPerson person;

        person = new InheritedPerson("Arthur Dent", 42, true, null);
        assertEquals("O:15:\"inheritedperson\":4:{s:4:\"name\";s:11:\"Arthur Dent\";s:3:\"age\";i:42;s:9:\"earthling\";b:1;s:7:\"special\";N;}",
            Pherialize.serialize(person));
    }
    
    
    /**
     * Test serializing file data map
     */
    
    public void testSerializeFileDataMap()
    {
        Map f1, f2, m;
        String s1, s2;
        
        f1 = new LinkedHashMap();
        f1.put("uniqueId", "1:769cf9c69a1e278e");
        f1.put("source", Integer.valueOf(1));
        f1.put("id", "769cf9c69a1e278e");
        f1.put("name", "44057.JPG");
        f1.put("length", "186118");
        f1.put("lastModified", "1105351935558");
        f1.put("width", "578");
        f1.put("height", "382");
        f1.put("mimeType", "image/jpeg");
        f1.put("url", "http://127.0.0.1:10414/769cf9c69a1e278e");

        f2 = new LinkedHashMap();
        f2.put("uniqueId", "1:1a1fbcbbd9c3de8d");
        f2.put("source", Integer.valueOf(1));
        f2.put("id", "1a1fbcbbd9c3de8d");
        f2.put("name", "testbild.jpg");
        f2.put("length", "229417");
        f2.put("lastModified", "1130771289683");
        f2.put("width", "1600");
        f2.put("height", "1200");
        f2.put("mimeType", "image/jpeg");
        f2.put("url", "http://127.0.0.1:10414/1a1fbcbbd9c3de8d");
        
        m = new LinkedHashMap();
        m.put(Integer.valueOf(0), f1);
        m.put(Integer.valueOf(1), f2);
        
        s1 = "a:2:{i:0;a:10:{s:8:\"uniqueId\";s:18:\"1:769cf9c69a1e278e\";s:6:\"source\";i:1;s:2:\"id\";s:16:\"769cf9c69a1e278e\";s:4:\"name\";s:9:\"44057.JPG\";s:6:\"length\";s:6:\"186118\";s:12:\"lastModified\";s:13:\"1105351935558\";s:5:\"width\";s:3:\"578\";s:6:\"height\";s:3:\"382\";s:8:\"mimeType\";s:10:\"image/jpeg\";s:3:\"url\";s:39:\"http://127.0.0.1:10414/769cf9c69a1e278e\";}i:1;a:10:{s:8:\"uniqueId\";s:18:\"1:1a1fbcbbd9c3de8d\";s:6:\"source\";i:1;s:2:\"id\";s:16:\"1a1fbcbbd9c3de8d\";s:4:\"name\";s:12:\"testbild.jpg\";s:6:\"length\";s:6:\"229417\";s:12:\"lastModified\";s:13:\"1130771289683\";s:5:\"width\";s:4:\"1600\";s:6:\"height\";s:4:\"1200\";s:8:\"mimeType\";s:10:\"image/jpeg\";s:3:\"url\";s:39:\"http://127.0.0.1:10414/1a1fbcbbd9c3de8d\";}}";
        s2 = Pherialize.serialize(m);
        assertEquals(s1, s2);
    }
    
    
    /**
     * Test serializing array
     */
    
    public void testSerializeArray()
    {
        String[] strings;
        String s1, s2;
        
        strings = new String[3];
        
        strings[0] = "String 1";
        strings[1] = "String 2";
        strings[2] = "String 3";
        
        s1 = "a:3:{i:0;s:8:\"String 1\";i:1;s:8:\"String 2\";i:2;s:8:\"String 3\";}";
        s2 = Pherialize.serialize(strings);
        assertEquals(s1, s2);
    }
}
