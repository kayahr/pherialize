/*
 * $Id$
 * Copyright (c) 2009 Klaus Reimer
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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test class for Pherialize
 *
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class UnserializerTest extends TestCase
{
    /**
     * Returns the test suite.
     *
     * @return The test suite
     */

    public static Test suite()
    {
        return new TestSuite(UnserializerTest.class);
    }


    /**
     * Tests unserializing a string
     */

    public void testUnserializeString()
    {
        String data;

        data = "s:4:\"Test\"";
        assertEquals("Test", Pherialize.unserialize(data).toString());
    }


    /**
     * Tests unserializing a UTF-8 string
     */
    public void testUnserializeUnicodeString()
    {
        String data;

        data = "s:52:\"Что такое машинное обучение?\";";
        assertEquals("Что такое машинное обучение?",
            Pherialize.unserialize(data).toString());
    }


    /**
     * Tests unserializing an integer
     */

    public void testUnserializeInteger()
    {
        assertEquals(0, Pherialize.unserialize("i:0;").toInt());
        assertEquals(-1, Pherialize.unserialize("i:-1;").toInt());
        assertEquals(2147483647, Pherialize.unserialize("i:2147483647;")
            .toInt());
        assertEquals(-2147483648, Pherialize.unserialize("i:-2147483648;")
            .toInt());
    }


    /**
     * Tests unserializing a Double
     */

    public void testUnserializeDouble()
    {
        assertEquals(0, Pherialize.unserialize("d:0.0;").toDouble(), 0);
        assertEquals(-1, Pherialize.unserialize("d:-1.0;").toDouble(), 0);
        assertEquals(-10.1, Pherialize.unserialize("d:-10.1;").toDouble(), 0);
        assertEquals(545.159122, Pherialize.unserialize("d:545.159122;")
            .toDouble(), 0);
    }


    /**
     * Tests unserializing a Boolean
     */

    public void testUnserializeBoolean()
    {
        assertEquals(false, Pherialize.unserialize("b:0;").toBoolean());
        assertEquals(true, Pherialize.unserialize("b:1;").toBoolean());
    }


    /**
     * Tests unserializing a Null
     */

    public void testUnserializeNull()
    {
        assertNull(Pherialize.unserialize("N;"));
    }


    /**
     * Tests unserializing a map
     */

    public void testUnserializeMap()
    {
        MixedArray test;

        test = Pherialize.unserialize(
            "a:3:{s:4:\"key1\";s:4:\"Test\";i:1;i:12345;s:4:\"key2\";b:1;}")
            .toArray();
        assertEquals(3, test.size());
        assertEquals(new Mixed("Test"), test.get("key1"));
        assertEquals(new Mixed(Integer.valueOf(12345)), test.get(Integer
            .valueOf(1)));
        assertEquals(new Mixed(Boolean.TRUE), test.get("key2"));
    }

    /**
     * Tests unserializing a session
     */

    public void testUnserializeSession()
    {
        MixedArray test;

        test = Pherialize.unserializeSession(
                "login_ok|b:1;nome|s:4:\"sica\";inteiro|i:34;").toArray();
        assertEquals(3, test.size());
        assertEquals(new Mixed(Boolean.TRUE), test.get("login_ok"));
        assertEquals(new Mixed("sica"), test.get("nome"));
        assertEquals(new Mixed(Integer.valueOf(34)), test.get("inteiro"));
    }


    /**
     * Tests unserializing a List
     */

    public void testUnserializeList()
    {
        MixedArray test;

        test = Pherialize.unserialize(
            "a:3:{i:0;s:4:\"Test\";i:1;i:12345;i:2;b:1;}").toArray();
        assertEquals(3, test.size());
        assertEquals(new Mixed("Test"), test.get(0));
        assertEquals(new Mixed(Integer.valueOf(12345)), test.get(1));
        assertEquals(new Mixed(Boolean.TRUE), test.get(2));
    }


    /**
     * Tests unserializing an array with a reference in it.
     */

    public void testPherializeeference()
    {
        MixedArray test;

        test = Pherialize
            .unserialize(
                "a:5:{i:0;s:6:\"Test 1\";i:1;s:6:\"Test 2\";i:2;s:6:\"Test 1\";i:3;R:3;i:4;s:6:\"Test 1\";}")
            .toArray();
        assertEquals(new Mixed("Test 1"), test.get(0));
        assertEquals(new Mixed("Test 2"), test.get(1));
        assertEquals(new Mixed("Test 1"), test.get(2));
        assertEquals(new Mixed("Test 2"), test.get(3));
        assertEquals(new Mixed("Test 1"), test.get(4));
        assertSame(test.get(1), test.get(3));
        assertNotSame(test.get(0), test.get(2));
        assertNotSame(test.get(0), test.get(4));
        assertNotSame(test.get(2), test.get(4));
    }


    /**
     * Tests unserializing a complex array
     */

    public void testComplex()
    {
        MixedArray persons;
        MixedArray arthur, ford;

        persons = Pherialize
            .unserialize(
                "a:2:{i:0;a:5:{s:4:\"name\";s:11:\"Arthur Dent\";s:3:\"age\";i:43;s:9:\"earthling\";b:1;s:7:\"special\";N;s:7:\"comrade\";a:5:{s:4:\"name\";s:12:\"Ford Prefect\";s:3:\"age\";i:39;s:9:\"earthling\";b:0;s:7:\"special\";N;s:7:\"comrade\";R:2;}}i:1;R:7;}")
            .toArray();
        assertEquals(2, persons.size());

        arthur = persons.getArray(0);
        assertEquals("Arthur Dent", arthur.getString("name"));
        assertEquals(43, arthur.getInt("age"));
        assertEquals(true, arthur.getBoolean("earthling"));
        assertNull(arthur.getString("special"));

        ford = persons.getArray(1);
        assertEquals("Ford Prefect", ford.getString("name"));
        assertEquals(39, ford.getInt("age"));
        assertEquals(false, ford.getBoolean("earthling"));
        assertNull(ford.getString("special"));

        assertSame(ford, arthur.getArray("comrade"));
        assertSame(arthur, ford.getArray("comrade"));
    }
}
