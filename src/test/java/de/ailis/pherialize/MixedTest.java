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
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Tests the Mixed class
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class MixedTest extends TestCase
{
    /**
     * Returns the test suite.
     * 
     * @return The test suite
     */

    public static Test suite()
    {
        return new TestSuite(MixedTest.class);
    }


    /**
     * Tests a string as a mixed value.
     */

    public void testString()
    {
        Mixed mixed;
        
        mixed = new Mixed("Test");
        assertEquals(Mixed.TYPE_STRING, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertTrue(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(0, mixed.toByte());
        assertEquals('T', mixed.toChar());
        assertEquals(0, mixed.toDouble(), 0);
        assertEquals(0, mixed.toFloat(), 0);
        assertEquals(0, mixed.toInt());
        assertEquals(0, mixed.toLong());
        assertEquals(0, mixed.toShort());
        assertEquals("Test", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }


    /**
     * Tests a boolean true as a mixed value.
     */

    public void testBooleanTrue()
    {
        Mixed mixed;
        
        mixed = new Mixed(true);
        assertEquals(Mixed.TYPE_BOOLEAN, mixed.getType());
        assertTrue(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(1, mixed.toByte());
        assertEquals('t', mixed.toChar());
        assertEquals(1, mixed.toDouble(), 0);
        assertEquals(1, mixed.toFloat(), 0);
        assertEquals(1, mixed.toInt());
        assertEquals(1, mixed.toLong());
        assertEquals(1, mixed.toShort());
        assertEquals("true", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a boolean false as a mixed value.
     */

    public void testBooleanFalse()
    {
        Mixed mixed;
        
        mixed = new Mixed(false);
        assertEquals(Mixed.TYPE_BOOLEAN, mixed.getType());
        assertTrue(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(false, mixed.toBoolean());
        assertEquals(0, mixed.toByte());
        assertEquals('f', mixed.toChar());
        assertEquals(0, mixed.toDouble(), 0);
        assertEquals(0, mixed.toFloat(), 0);
        assertEquals(0, mixed.toInt());
        assertEquals(0, mixed.toLong());
        assertEquals(0, mixed.toShort());
        assertEquals("false", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a character as a mixed value.
     */

    public void testChar()
    {
        Mixed mixed;
        
        mixed = new Mixed('a');
        assertEquals(Mixed.TYPE_CHAR, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertTrue(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(0, mixed.toByte());
        assertEquals('a', mixed.toChar());
        assertEquals(0, mixed.toDouble(), 0);
        assertEquals(0, mixed.toFloat(), 0);
        assertEquals(0, mixed.toInt());
        assertEquals(0, mixed.toLong());
        assertEquals(0, mixed.toShort());
        assertEquals("a", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a number character as a mixed value.
     */

    public void testNumberChar()
    {
        Mixed mixed;
        
        mixed = new Mixed('5');
        assertEquals(Mixed.TYPE_CHAR, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertTrue(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(5, mixed.toByte());
        assertEquals('5', mixed.toChar());
        assertEquals(5, mixed.toDouble(), 0);
        assertEquals(5, mixed.toFloat(), 0);
        assertEquals(5, mixed.toInt());
        assertEquals(5, mixed.toLong());
        assertEquals(5, mixed.toShort());
        assertEquals("5", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a byte as a mixed value.
     */

    public void testByte()
    {
        Mixed mixed;
        
        mixed = new Mixed((byte) 127);
        assertEquals(Mixed.TYPE_BYTE, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertTrue(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertTrue(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(127, mixed.toByte());
        assertEquals('1', mixed.toChar());
        assertEquals(127, mixed.toDouble(), 0);
        assertEquals(127, mixed.toFloat(), 0);
        assertEquals(127, mixed.toInt());
        assertEquals(127, mixed.toLong());
        assertEquals(127, mixed.toShort());
        assertEquals("127", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a short as a mixed value.
     */

    public void testShort()
    {
        Mixed mixed;
        
        mixed = new Mixed((short) 32767);
        assertEquals(Mixed.TYPE_SHORT, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertTrue(mixed.isShort());
        assertTrue(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(-1, mixed.toByte());
        assertEquals('3', mixed.toChar());
        assertEquals(32767, mixed.toDouble(), 0);
        assertEquals(32767, mixed.toFloat(), 0);
        assertEquals(32767, mixed.toInt());
        assertEquals(32767, mixed.toLong());
        assertEquals(32767, mixed.toShort());
        assertEquals("32767", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests an int as a mixed value.
     */

    public void testInt()
    {
        Mixed mixed;
        
        mixed = new Mixed(2147483647);
        assertEquals(Mixed.TYPE_INT, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertTrue(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertTrue(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(-1, mixed.toByte());
        assertEquals('2', mixed.toChar());
        assertEquals(2147483647, mixed.toDouble(), 0);
        assertEquals(2147483647, mixed.toFloat(), 0);
        assertEquals(2147483647, mixed.toInt());
        assertEquals(2147483647, mixed.toLong());
        assertEquals(-1, mixed.toShort());
        assertEquals("2147483647", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a long as a mixed value.
     */

    public void testLong()
    {
        Mixed mixed;
        
        mixed = new Mixed(9223372036854775807L);
        assertEquals(Mixed.TYPE_LONG, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertTrue(mixed.isLong());
        assertFalse(mixed.isShort());
        assertTrue(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(-1, mixed.toByte());
        assertEquals('9', mixed.toChar());
        assertEquals(9223372036854775807L, mixed.toDouble(), 0);
        assertEquals(9223372036854775807L, mixed.toFloat(), 0);
        assertEquals(-1, mixed.toInt());
        assertEquals(9223372036854775807L, mixed.toLong());
        assertEquals(-1, mixed.toShort());
        assertEquals("9223372036854775807", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a long as a mixed value.
     */

    public void testFloat()
    {
        Mixed mixed;
        
        mixed = new Mixed((float) 12.34);
        assertEquals(Mixed.TYPE_FLOAT, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertTrue(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertTrue(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(12, mixed.toByte());
        assertEquals('1', mixed.toChar());
        assertEquals(12.34, mixed.toDouble(), 0);
        assertEquals(12.34000015258789, mixed.toFloat(), 0);
        assertEquals(12, mixed.toInt());
        assertEquals(12, mixed.toLong());
        assertEquals(12, mixed.toShort());
        assertEquals("12.34", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a double as a mixed value.
     */

    public void testDouble()
    {
        Mixed mixed;
        
        mixed = new Mixed(12.34);
        assertEquals(Mixed.TYPE_DOUBLE, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertTrue(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertTrue(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(12, mixed.toByte());
        assertEquals('1', mixed.toChar());
        assertEquals(12.34, mixed.toDouble(), 0);
        assertEquals(12.34000015258789, mixed.toFloat(), 0);
        assertEquals(12, mixed.toInt());
        assertEquals(12, mixed.toLong());
        assertEquals(12, mixed.toShort());
        assertEquals("12.34", mixed.toString());
        assertNull(mixed.toList());
        assertNull(mixed.toMap());
    }
    
    
    /**
     * Tests a list as a mixed value.
     */

    public void testList()
    {
        Mixed mixed;        
        List list;
        Map map;
        
        list = new ArrayList();
        list.add(new Mixed(1));
        list.add(new Mixed("Test"));
        list.add(new Mixed(Boolean.TRUE));
               
        mixed = new Mixed(list);
        assertEquals(Mixed.TYPE_LIST, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertFalse(mixed.isString());
        assertFalse(mixed.isMap());
        assertTrue(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(0, mixed.toByte());
        assertEquals('[', mixed.toChar());
        assertEquals(0, mixed.toDouble(), 0);
        assertEquals(0, mixed.toFloat(), 0);
        assertEquals(0, mixed.toInt());
        assertEquals(0, mixed.toLong());
        assertEquals(0, mixed.toShort());
        assertEquals("[1, Test, true]", mixed.toString());
        assertEquals(list, mixed.toList());
        map = mixed.toMap();
        assertEquals(3, map.size());
        assertEquals(1, ((Mixed) map.get(Integer.valueOf(0))).toInt());
        assertEquals("Test", ((Mixed) map.get(Integer.valueOf(1))).toString());
        assertEquals(true, ((Mixed) map.get(Integer.valueOf(2))).toBoolean());
        assertEquals(new Mixed(1), map.get(Integer.valueOf(0)));
        assertEquals(new Mixed("Test"), map.get(Integer.valueOf(1)));
        assertEquals(new Mixed(true), map.get(Integer.valueOf(2)));
    }
    
    
    /**
     * Tests a map as a mixed value.
     */

    public void testMap()
    {
        Mixed mixed;        
        List list;
        Map map;
        
        map = new HashMap();
        map.put(new Mixed(0), new Mixed("Item 1"));
        map.put(new Mixed(1), new Mixed("Item 2"));
        map.put(new Mixed("key3"), new Mixed("Item 3"));
               
        mixed = new Mixed(map);
        assertEquals(Mixed.TYPE_MAP, mixed.getType());
        assertFalse(mixed.isBoolean());
        assertFalse(mixed.isByte());
        assertFalse(mixed.isChar());
        assertFalse(mixed.isDouble());
        assertFalse(mixed.isFloat());
        assertFalse(mixed.isInt());
        assertFalse(mixed.isLong());
        assertFalse(mixed.isShort());
        assertFalse(mixed.isNumber());
        assertFalse(mixed.isString());
        assertTrue(mixed.isMap());
        assertFalse(mixed.isList());
        assertEquals(true, mixed.toBoolean());
        assertEquals(0, mixed.toByte());
        assertEquals('{', mixed.toChar());
        assertEquals(0, mixed.toDouble(), 0);
        assertEquals(0, mixed.toFloat(), 0);
        assertEquals(0, mixed.toInt());
        assertEquals(0, mixed.toLong());
        assertEquals(0, mixed.toShort());
        assertEquals("{key3=Item 3, 0=Item 1, 1=Item 2}", mixed.toString());
        assertEquals(map, mixed.toMap());
        list = mixed.toList();
        assertEquals(3, list.size());
        assertTrue(list.contains(new Mixed("Item 1")));
        assertTrue(list.contains(new Mixed("Item 2")));
        assertTrue(list.contains(new Mixed("Item 3")));
    }
    
    
    /**
     * Test equals method
     */

    public void testEquals()
    {
        assertFalse(new Mixed("1").equals(new Mixed("2")));
        assertTrue(new Mixed("1").equals(new Mixed("1")));
        assertFalse(new Mixed("1").equals("2"));
        assertTrue(new Mixed("1").equals("1"));
        assertFalse(new Mixed("1").equals(new Mixed(new Integer(2))));
        assertTrue(new Mixed("1").equals(new Mixed(new Integer(1))));
        assertFalse(new Mixed("1").equals(new Integer(2)));
        assertTrue(new Mixed("1").equals(new Integer(1)));
    }
}
