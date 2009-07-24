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
 * Test class for global test cases in Pherialize
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class PherializeTest extends TestCase
{
    /**
     * Returns the test suite.
     * 
     * @return The test suite
     */

    public static Test suite()
    {
        return new TestSuite(PherializeTest.class);
    }


    /**
     * Tests unserializing a complex array
     */

    public void testComplex()
    {
        String s;
        Mixed data;
        
        s = "a:2:{i:0;a:10:{s:8:\"uniqueId\";s:18:\"1:769cf9c69a1e278e\";s:6:\"source\";i:1;s:2:\"id\";s:16:\"769cf9c69a1e278e\";s:4:\"name\";s:9:\"44057.JPG\";s:6:\"length\";s:6:\"186118\";s:12:\"lastModified\";s:13:\"1105351935558\";s:5:\"width\";s:3:\"578\";s:6:\"height\";s:3:\"382\";s:8:\"mimeType\";s:10:\"image/jpeg\";s:3:\"url\";s:39:\"http://127.0.0.1:10414/769cf9c69a1e278e\";}i:1;a:10:{s:8:\"uniqueId\";s:18:\"1:1a1fbcbbd9c3de8d\";s:6:\"source\";i:1;s:2:\"id\";s:16:\"1a1fbcbbd9c3de8d\";s:4:\"name\";s:12:\"testbild.jpg\";s:6:\"length\";s:6:\"229417\";s:12:\"lastModified\";s:13:\"1130771289683\";s:5:\"width\";s:4:\"1600\";s:6:\"height\";s:4:\"1200\";s:8:\"mimeType\";s:10:\"image/jpeg\";s:3:\"url\";s:39:\"http://127.0.0.1:10414/1a1fbcbbd9c3de8d\";}}";
        data = Pherialize.unserialize(s);
        assertEquals(s, Pherialize.serialize(data));
    }
}
