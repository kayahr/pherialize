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

import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Tests the MixedHashMap class
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class MixedHashMapTest extends TestCase
{
    /** The test map */
    private Map map;
    
    
    /**
     * @see junit.framework.TestCase#setUp()
     */
    
    public void setUp()
    {
        this.map = new MixedHashMap();
        this.map.put("1", "value 1");
        this.map.put(new Mixed("2"), "value 2");
        this.map.put("3", new Mixed("value 3"));
        this.map.put(new Mixed("4"), new Mixed("value 4"));
    }
    
    
    /**
     * Returns the test suite.
     * 
     * @return The test suite
     */

    public static Test suite()
    {
        return new TestSuite(MixedHashMapTest.class);
    }


    /**
     * Tests normal put and get
     */

    public void testPutAndGet()
    {
        assertEquals("value 1", ((Mixed) this.map.get("1")).toString());
        assertEquals("value 2", ((Mixed) this.map.get("2")).toString());
        assertEquals("value 3", ((Mixed) this.map.get("3")).toString());
        assertEquals("value 4", ((Mixed) this.map.get("4")).toString());
        assertEquals("value 1", ((Mixed) this.map.get(new Mixed("1")))
            .toString());
        assertEquals("value 2", ((Mixed) this.map.get(new Mixed("2")))
            .toString());
        assertEquals("value 3", ((Mixed) this.map.get(new Mixed("3")))
            .toString());
        assertEquals("value 4", ((Mixed) this.map.get(new Mixed("4")))
            .toString());
    }
    
    
    /**
     * Tests containsKey
     */

    public void testContainsKey()
    {
        assertTrue(this.map.containsKey("2"));
        assertFalse(this.map.containsKey("5"));
        assertTrue(this.map.containsKey(new Mixed("2")));
        assertFalse(this.map.containsKey(new Mixed("5")));
        assertTrue(this.map.containsKey(Integer.valueOf(2)));
        assertFalse(this.map.containsKey(Integer.valueOf(5)));
        assertTrue(this.map.containsKey(new Mixed(Integer.valueOf(2))));
        assertFalse(this.map.containsKey(new Mixed(Integer.valueOf(5))));
    }
    
    
    /**
     * Tests containsValue
     */

    public void testContainsValue()
    {
        assertTrue(this.map.containsValue("value 2"));
        assertFalse(this.map.containsValue("value 5"));
        assertTrue(this.map.containsValue(new Mixed("value 2")));
        assertFalse(this.map.containsValue(new Mixed("value 5")));
    }
    
    
    /**
     * Tests remove
     */

    public void testRemove()
    {
        this.map.remove("2");
        assertEquals(3, this.map.size());
        this.map.remove(new Mixed("3"));
        assertEquals(2, this.map.size());
    }
}
