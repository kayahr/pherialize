/*
 * $Id$
 * Copyright (C) 2006 Klaus Reimer <k@ailis.de>
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

import java.util.HashMap;
import java.util.Map;


/**
 * A HashMap implementation with Mixed keys.
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class MixedHashMap extends HashMap
{
    /** Serial version UID */
    private static final long serialVersionUID = -194078593900457504L;


    /**
     * Constructor
     * 
     * @param initialCapacity
     *            The initial capacity
     * @param loadFactor
     *            The load factor
     */

    public MixedHashMap(int initialCapacity, float loadFactor)
    {
        super(initialCapacity, loadFactor);
    }


    /**
     * Constructor
     * 
     * @param initialCapacity
     *            The initial capacity
     */

    public MixedHashMap(int initialCapacity)
    {
        super(initialCapacity);
    }


    /**
     * Constructor
     */

    public MixedHashMap()
    {
        super();
    }


    /**
     * Constructor
     * 
     * @param map
     *            The map to copy
     */

    public MixedHashMap(Map map)
    {
        super(map);
    }


    /**
     * @see java.util.HashMap#get(java.lang.Object)
     */
    
    public Object get(Object key)
    {
        if (key instanceof Mixed)
        {
            return super.get(key);
        }
        else
        {
            return super.get(new Mixed(key));
        }
    }


    /**
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    
    public Object put(Object key, Object value)
    {
        if (!(key instanceof Mixed))
        {
            key = new Mixed(key);
        }
        if (!(value instanceof Mixed))
        {
            value = new Mixed(value);
        }
        return super.put(key, value);
    }


    /**
     * @see java.util.HashMap#containsKey(java.lang.Object)
     */
    
    public boolean containsKey(Object key)
    {        
        if (key instanceof Mixed)
        {
            return super.containsKey(key);
        }
        else
        {
            return super.containsKey(new Mixed(key));
        }        
    }


    /**
     * @see java.util.HashMap#containsValue(java.lang.Object)
     */
    
    public boolean containsValue(Object value)
    {
        if (value instanceof Mixed)
        {
            return super.containsValue(value);
        }
        else
        {
            return super.containsValue(new Mixed(value));
        }        
    }


    /**
     * @see java.util.HashMap#remove(java.lang.Object)
     */
    
    public Object remove(Object key)
    {
        if (key instanceof Mixed)
        {
            return super.remove(key);
        }
        else
        {
            return super.remove(new Mixed(key));
        }        
    }
}
