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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.ailis.pherialize.exceptions.SerializeException;


/**
 * Serializes Java objects in a PHP serializer format string.
 * 
 * @author Klaus Reimer (k.reimer@iplabs.de)
 * @version $Revision$
 */

public class Serializer
{
    /** The string buffer used for serializing */
    private StringBuffer buffer;

    /** The object history for resolving references */
    private List history;


    /**
     * Constructor
     */

    public Serializer()
    {
        super();
        this.buffer = new StringBuffer();
        this.history = new ArrayList();
    }


    /**
     * @see java.lang.Object#toString()
     */

    public String toString()
    {
        return this.buffer.toString();
    }


    /**
     * This method is used internally for recursively scanning the object while
     * serializing.
     * 
     * @param object
     *            The object to serialize
     */

    public void serializeObject(Object object)
    {
        if (object == null)
        {
            serializeNull();
        }
        else if (serializeReference(object))
        {
            return;
        }
        else if (object instanceof String)
        {
            serializeString((String) object);
        }
        else if (object instanceof Character)
        {
            serializeCharacter((Character) object);
        }
        else if (object instanceof Integer)
        {
            serializeInteger(((Integer) object).intValue());
        }
        else if (object instanceof Short)
        {
            serializeInteger(((Short) object).intValue());
        }
        else if (object instanceof Byte)
        {
            serializeInteger(((Byte) object).intValue());
        }
        else if (object instanceof Long)
        {
            serializeLong(((Long) object).longValue());
        }
        else if (object instanceof Double)
        {
            serializeDouble(((Double) object).doubleValue());
        }
        else if (object instanceof Float)
        {
            serializeDouble(((Float) object).doubleValue());
        }
        else if (object instanceof Boolean)
        {
            serializeBoolean((Boolean) object);
        }
        else if (object instanceof Collection)
        {
            serializeCollection((Collection) object);
            return;
        }
        else if (object instanceof Map)
        {
            serializeMap((Map) object);
            return;
        }
        else
        {
            throw new SerializeException("Unable how to serialize "
                + object.getClass().getName());
        }

        this.history.add(object);
    }


    /**
     * Tries to serialize a reference if the specified object was already
     * serialized. It returns true in this case. If the object was not
     * serialized before then false is returned.
     * 
     * @param object
     *            The object to serialize
     * @return If a reference was serialized or not
     */

    private boolean serializeReference(Object object)
    {
        Iterator iterator;
        int index;
        boolean isReference;

        iterator = this.history.iterator();
        index = 0;
        isReference = false;
        while (iterator.hasNext())
        {
            if (iterator.next() == object)
            {
                this.buffer.append("R:");
                this.buffer.append(index + 1);
                this.buffer.append(';');
                isReference = true;
                break;
            }
            index++;
        }
        return isReference;
    }


    /**
     * Serializes the specified string and appends it to the serialization
     * buffer.
     * 
     * @param string
     *            The string to serialize
     */

    private void serializeString(String string)
    {
        this.buffer.append("s:");
        this.buffer.append(string.length());
        this.buffer.append(":\"");
        this.buffer.append(string);
        this.buffer.append("\";");
    }


    /**
     * Serializes the specified character and appends it to the serialization
     * buffer.
     * 
     * @param value
     *            The value to serialize
     */

    private void serializeCharacter(Character value)
    {
        this.buffer.append("s:1:\"");
        this.buffer.append(value);
        this.buffer.append("\";");
    }


    /**
     * Adds a serialized NULL to the serialization buffer.
     */

    private void serializeNull()
    {
        this.buffer.append("N;");
    }


    /**
     * Serializes the specified integer number and appends it to the
     * serialization buffer.
     * 
     * @param number
     *            The integer number to serialize
     */

    private void serializeInteger(int number)
    {
        this.buffer.append("i:");
        this.buffer.append(number);
        this.buffer.append(';');
    }


    /**
     * Serializes the specified lonf number and appends it to the serialization
     * buffer.
     * 
     * @param number
     *            The lonf number to serialize
     */

    private void serializeLong(long number)
    {
        if ((number >= Integer.MIN_VALUE) && (number <= Integer.MAX_VALUE))
        {
            this.buffer.append("i:");
        }
        else
        {
            this.buffer.append("d:");
        }
        this.buffer.append(number);
        this.buffer.append(';');
    }


    /**
     * Serializes the specfied double number and appends it to the serialization
     * buffer.
     * 
     * @param number
     *            The number to serialize
     */

    private void serializeDouble(double number)
    {
        this.buffer.append("d:");
        this.buffer.append(number);
        this.buffer.append(';');
    }


    /**
     * Serializes the specfied boolean and appends it to the serialization
     * buffer.
     * 
     * @param value
     *            The value to serialize
     */

    private void serializeBoolean(Boolean value)
    {
        this.buffer.append("b:");
        this.buffer.append(value.booleanValue() ? 1 : 0);
        this.buffer.append(';');
    }


    /**
     * Serializes the specfied collection and appends it to the serialization
     * buffer.
     * 
     * @param collection
     *            The collection to serialize
     */

    private void serializeCollection(Collection collection)
    {
        Iterator iterator;
        int index;

        this.history.add(collection);
        this.buffer.append("a:");
        this.buffer.append(collection.size());
        this.buffer.append(":{");
        iterator = collection.iterator();
        index = 0;
        while (iterator.hasNext())
        {
            serializeObject(Integer.valueOf(index));
            this.history.remove(this.history.size() - 1);
            serializeObject(iterator.next());
            index++;
        }
        this.buffer.append('}');
    }


    /**
     * Serializes the specfied map and appends it to the serialization buffer.
     * 
     * @param map
     *            The map to serialize
     */

    private void serializeMap(Map map)
    {
        Iterator iterator;
        Object key;

        this.history.add(map);
        this.buffer.append("a:");
        this.buffer.append(map.size());
        this.buffer.append(":{");
        iterator = map.keySet().iterator();
        while (iterator.hasNext())
        {
            key = iterator.next();
            serializeObject(key);
            this.history.remove(this.history.size() - 1);
            serializeObject(map.get(key));
        }
        this.buffer.append('}');
    }
}
