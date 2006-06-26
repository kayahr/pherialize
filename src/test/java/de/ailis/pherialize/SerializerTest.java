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
import java.util.HashMap;
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
        assertEquals("s:4:\"Test\";", Serializer.serialize(test));
    }


    /**
     * Tests serializing a string
     */

    public void testSerializeCharacter()
    {
        Character test;

        test = new Character('t');
        assertEquals("s:1:\"t\";", Serializer.serialize(test));
    }


    /**
     * Tests serializing an integer value.
     */

    public void testSerializeInteger()
    {
        assertEquals("i:0;", Serializer.serialize(Integer.valueOf(0)));
        assertEquals("i:-1;", Serializer.serialize(Integer.valueOf(-1)));
        assertEquals("i:2147483647;", Serializer.serialize(Integer
            .valueOf(2147483647)));
        assertEquals("i:-2147483648;", Serializer.serialize(Integer
            .valueOf(-2147483648)));
    }


    /**
     * Tests serializing a short value.
     */

    public void testSerializeShort()
    {
        assertEquals("i:0;", Serializer.serialize(Short.valueOf((short) 0)));
        assertEquals("i:-1;", Serializer.serialize(Short.valueOf((short) -1)));
        assertEquals("i:32767;", Serializer.serialize(Short
            .valueOf((short) 32767)));
        assertEquals("i:-32768;", Serializer.serialize(Short
            .valueOf((short) -32768)));
    }


    /**
     * Tests serializing a byte value.
     */

    public void testSerializeByte()
    {
        assertEquals("i:0;", Serializer.serialize(Byte.valueOf((byte) 0)));
        assertEquals("i:-1;", Serializer.serialize(Byte.valueOf((byte) -1)));
        assertEquals("i:127;", Serializer.serialize(Byte.valueOf((byte) 127)));
        assertEquals("i:-128;", Serializer.serialize(Byte.valueOf((byte) -128)));
    }


    /**
     * Tests serializing a long value.
     */

    public void testSerializeLong()
    {
        assertEquals("i:0;", Serializer.serialize(Long.valueOf(0)));
        assertEquals("i:-1;", Serializer.serialize(Long.valueOf(-1)));
        assertEquals("i:2147483647;", Serializer.serialize(Long
            .valueOf(2147483647)));
        assertEquals("i:-2147483648;", Serializer.serialize(Long
            .valueOf(-2147483648)));
        assertEquals("d:2147483648;", Serializer.serialize(Long
            .valueOf(2147483648L)));
        assertEquals("d:-2147483649;", Serializer.serialize(Long
            .valueOf(-2147483649L)));
    }


    /**
     * Tests serializing a double value.
     */

    public void testSerializeDouble()
    {
        assertEquals("d:0.0;", Serializer.serialize(Double.valueOf(0)));
        assertEquals("d:-1.0;", Serializer.serialize(Double.valueOf(-1)));
        assertEquals("d:-10.1;", Serializer.serialize(Double.valueOf(-10.1)));
        assertEquals("d:545.159122;", Serializer.serialize(Double
            .valueOf(545.159122)));
    }


    /**
     * Tests serializing a float value.
     */

    public void testSerializeFloat()
    {
        assertEquals("d:0.0;", Serializer.serialize(Float.valueOf(0)));
        assertEquals("d:-1.0;", Serializer.serialize(Float.valueOf(-1)));
        assertEquals("d:-10.100000381469727;", Serializer.serialize(Float
            .valueOf((float) -10.100000381469727)));
        assertEquals("d:545.1591186523438;", Serializer.serialize(Float
            .valueOf((float) 545.1591186523438)));
    }


    /**
     * Tests serializing a boolean value.
     */

    public void testSerializeBoolean()
    {
        assertEquals("b:0;", Serializer.serialize(Boolean.FALSE));
        assertEquals("b:1;", Serializer.serialize(Boolean.TRUE));
    }


    /**
     * Tests serializing a null value.
     */

    public void testSerializeNull()
    {
        assertEquals("N;", Serializer.serialize(null));
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
        assertEquals("a:3:{i:0;s:4:\"Test\";i:1;i:12345;i:2;b:1;}", Serializer
            .serialize(test));
    }


    /**
     * Tests serializing a map of various values.
     */

    public void testSerializeMap()
    {
        Map test;

        test = new HashMap();
        test.put("key1", "Test");
        test.put(Integer.valueOf(1), Integer.valueOf(12345));
        test.put("key2", Boolean.TRUE);
        assertEquals(
            "a:3:{s:4:\"key1\";s:4:\"Test\";i:1;i:12345;s:4:\"key2\";b:1;}",
            Serializer.serialize(test));
    }


    /**
     * Tests serializing an array with a reference in it.
     */

    public void testSerializeReference()
    {
        List test;
        String test2;

        test = new ArrayList();
        test2 = "Test 2";
        test.add("Test 1");
        test.add(test2);
        test.add(new String("Test 1"));
        test.add(test2);
        test.add(new String("Test 1"));
        assertEquals(
            "a:5:{i:0;s:6:\"Test 1\";i:1;s:6:\"Test 2\";i:2;s:6:\"Test 1\";i:3;R:3;i:4;s:6:\"Test 1\";}",
            Serializer.serialize(test));
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
            Serializer.serialize(persons));
    }
}
