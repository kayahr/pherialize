/*
 * $Id$
 * Copyright (C) 2009 Klaus Reimer <k@ailis.de>
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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * A Map/List implementation with Mixed keys and values.
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class MixedArray extends LinkedHashMap<Object, Object>
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

    public MixedArray(final int initialCapacity, final float loadFactor)
    {
        super(initialCapacity, loadFactor);
    }


    /**
     * Constructor
     * 
     * @param initialCapacity
     *            The initial capacity
     */

    public MixedArray(final int initialCapacity)
    {
        super(initialCapacity);
    }


    /**
     * Constructor
     */

    public MixedArray()
    {
        super();
    }


    /**
     * Constructor
     * 
     * @param map
     *            The map to copy
     */

    public MixedArray(final Map<?, ?> map)
    {
        super(map);
    }


    /**
     * Constructor
     * 
     * @param list
     *            The list to copy
     */

    public MixedArray(final Collection<?> list)
    {
        super();

        int i;
        Iterator<?> iterator;

        i = 0;
        iterator = list.iterator();
        while (iterator.hasNext())
        {
            this.put(new Mixed(i), iterator.next());
            i++;
        }
    }


    /**
     * @see java.util.HashMap#get(java.lang.Object)
     */

    @Override
    public Object get(final Object key)
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
     * Returns value for specified key as mixed value.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public Mixed getMixed(final Object key)
    {
        return (Mixed) get(key);
    }


    /**
     * Returns value for specified key as string.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public String getString(final Object key)
    {
        return getMixed(key).toString();
    }


    /**
     * Returns value for specified key as boolean.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public boolean getBooolean(final Object key)
    {
        return getMixed(key).toBoolean();
    }


    /**
     * Returns value for specified key as byte.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public byte getByte(final Object key)
    {
        return getMixed(key).toByte();
    }


    /**
     * Returns value for specified key as char.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public char getChar(final Object key)
    {
        return getMixed(key).toChar();
    }


    /**
     * Returns value for specified key as double.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public double getDouble(final Object key)
    {
        return getMixed(key).toDouble();
    }


    /**
     * Returns value for specified key as float.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public float getFloat(final Object key)
    {
        return getMixed(key).toFloat();
    }


    /**
     * Returns value for specified key as int.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public int getInt(final Object key)
    {
        return getMixed(key).toInt();
    }


    /**
     * Returns value for specified key as boolean.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public boolean getBoolean(final Object key)
    {
        return getMixed(key).toBoolean();
    }


    /**
     * Returns value for specified key as list.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public MixedArray getArray(final Object key)
    {
        return getMixed(key).toArray();
    }


    /**
     * Returns value for specified key as long.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public long getLong(final Object key)
    {
        return getMixed(key).toLong();
    }


    /**
     * Returns value for specified key as short.
     * 
     * @param key
     *            The key
     * @return The value
     */

    public short getShort(final Object key)
    {
        return getMixed(key).toShort();
    }


    /**
     * Returns value for specified key as custom type
     * 
     * @param key
     *            The key
     * @param type
     *            The type
     * @return The value
     */

    public Object getType(final Object key, final int type)
    {
        return getMixed(key).toType(type);
    }


    /**
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */

    @Override
    public Object put(final Object key, final Object value)
    {
        Mixed mixedKey, mixedValue;
        
        if (!(key instanceof Mixed))
            mixedKey = new Mixed(key);
        else
            mixedKey = (Mixed) key;
        if (!(value instanceof Mixed))
            mixedValue = new Mixed(value);
        else
            mixedValue = (Mixed) value;
        return super.put(mixedKey, mixedValue);
    }


    /**
     * @see java.util.HashMap#containsKey(java.lang.Object)
     */

    @Override
    public boolean containsKey(final Object key)
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

    @Override
    public boolean containsValue(final Object value)
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
     * Checks if value exists in array.
     * 
     * @param value
     *            The value to check
     * @return If value exists or not
     */

    public boolean contains(final Object value)
    {
        return containsValue(value);
    }


    /**
     * @see java.util.HashMap#remove(java.lang.Object)
     */

    @Override
    public Object remove(final Object key)
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


    /**
     * Returns value with specified index.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public Object get(final int index)
    {
        return get(new Mixed(index));
    }


    /**
     * Returns value as mixed value.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public Mixed getMixed(final int index)
    {
        return (Mixed) get(index);
    }


    /**
     * Returns value as string.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public String getString(final int index)
    {
        return getMixed(index).toString();
    }


    /**
     * Returns value as boolean.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public boolean getBooolean(final int index)
    {
        return getMixed(index).toBoolean();
    }


    /**
     * Returns value as byte.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public byte getByte(final int index)
    {
        return getMixed(index).toByte();
    }


    /**
     * Returns value as char.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public char getChar(final int index)
    {
        return getMixed(index).toChar();
    }


    /**
     * Returns value as double.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public double getDouble(final int index)
    {
        return getMixed(index).toDouble();
    }


    /**
     * Returns value as float.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public float getFloat(final int index)
    {
        return getMixed(index).toFloat();
    }


    /**
     * Returns value as int.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public int getInt(final int index)
    {
        return getMixed(index).toInt();
    }


    /**
     * Returns value as boolean.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public boolean getBoolean(final int index)
    {
        return getMixed(index).toBoolean();
    }


    /**
     * Returns value as array.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public MixedArray getArray(final int index)
    {
        return getMixed(index).toArray();
    }


    /**
     * Returns value as long.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public long getLong(final int index)
    {
        return getMixed(index).toLong();
    }


    /**
     * Returns value as short.
     * 
     * @param index
     *            The index
     * @return The value
     */

    public short getShort(final int index)
    {
        return getMixed(index).toShort();
    }


    /**
     * Returns value as custom type
     * 
     * @param index
     *            The index
     * @param type
     *            The type
     * @return The value
     */

    public Object getType(final int index, final int type)
    {
        return getMixed(index).toType(type);
    }
}
