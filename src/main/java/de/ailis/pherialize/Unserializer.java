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
import java.util.List;
import java.util.Map;

import de.ailis.pherialize.exceptions.UnserializeException;


/**
 * Unserializers a PHP serializer format string into a Java object
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class Unserializer
{
    /** The current pointer in the data */
    private int pos;

    /** The data to unserialize */
    private String data;

    /** The object history for resolving references */
    private List history;


    /**
     * Constructor
     * 
     * @param data
     *            The data to unserialize
     */

    public Unserializer(String data)
    {
        super();
        this.data = data;
        this.pos = 0;
        this.history = new ArrayList();
    }


    /**
     * Returns the unserialized object of the specified PHP serialize format
     * string. The returned object is wrapped in a Mixed object allowing easy
     * conversion to any data type needed. This wrapping is needed because PHP
     * is a loosely typed language and it is quite propable that a boolean is
     * sometimes a int or a string. So with the Mixed wrapper object you can
     * easily decide on your own how to interpret the unserialized data.
     * 
     * @param data
     *            The serialized data
     * @return The unserialized object
     */

    public static Mixed unserialize(String data)
    {
        Unserializer unserializer;

        unserializer = new Unserializer(data);
        return unserializer.unserializeObject();
    }

    
    /**
     * Unserializes the next object in the data stream.
     * 
     * @return The unserializes object
     */

    private Mixed unserializeObject()
    {
        char type;
        Mixed result;

        type = this.data.charAt(this.pos);
        switch (type)
        {
            case 's':
                result = unserializeString();
                break;

            case 'i':
                result = unserializeInteger();
                break;

            case 'd':
                result = unserializeDouble();
                break;

            case 'b':
                result = unserializeBoolean();
                break;

            case 'N':
                result = unserializeNull();
                break;

            case 'a':
                result = unserializeArray();
                break;
            
            case 'R':
                result = unserializeReference();
                break;

            default:
                throw new UnserializeException(
                    "Unable to unserialize unknown type " + type);
        }
        
        this.history.add(result);
        return result;
    }


    /**
     * Unserializes the next object in the data stream into a String.
     * 
     * @return The unserialized String
     */

    private Mixed unserializeString()
    {
        int pos, length;

        pos = this.data.indexOf(':', this.pos + 2);
        length = Integer.parseInt(this.data.substring(this.pos + 2, pos));
        this.pos = pos + length + 4;
        return new Mixed(this.data.substring(pos + 2, pos + 2 + length));
    }
    
    
    /**
     * Unserializes the next object in the data stream into an Integer.
     * 
     * @return The unserialized Integer
     */

    private Mixed unserializeInteger()
    {
        Integer result;
        int pos;

        pos = this.data.indexOf(';', this.pos + 2);
        result = Integer.valueOf(this.data.substring(this.pos + 2, pos));
        this.pos = pos + 1;
        return new Mixed(result);
    }
    
    
    /**
     * Unserializes the next object in the data stream into an Double.
     * 
     * @return The unserialized Double
     */

    private Mixed unserializeDouble()
    {
        Double result;
        int pos;

        pos = this.data.indexOf(';', this.pos + 2);
        result = Double.valueOf(this.data.substring(this.pos + 2, pos));
        this.pos = pos + 1;
        return new Mixed(result);
    }
    
    
    /**
     * Unserializes the next object in the data stream as a reference.
     * 
     * @return The unserialized reference
     */

    private Mixed unserializeReference()
    {
        int index;
        int pos;

        pos = this.data.indexOf(';', this.pos + 2);
        index = Integer.parseInt(this.data.substring(this.pos + 2, pos));
        this.pos = pos + 1;
        return (Mixed) this.history.get(index);
    }
    
    
    /**
     * Unserializes the next object in the data stream into a Boolean.
     * 
     * @return The unserialized Boolean
     */

    private Mixed unserializeBoolean()
    {
        Boolean result;

        result = Boolean.valueOf(this.data.charAt(this.pos + 2) == '1');
        this.pos += 4;
        return new Mixed(result);
    }
    
    
    /**
     * Unserializes the next object in the data stream into a Null
     * 
     * @return The unserialized Null
     */

    private Mixed unserializeNull()
    {
        this.pos += 2;
        return null;
    }
    
    
    /**
     * Unserializes the next object in the data stream into an array. This
     * method returns an ArrayList if the unserialized array has numerical
     * keys starting with 0 or a HashMap otherwise.
     * 
     * @return The unserialized array
     */

    private Mixed unserializeArray()
    {
        Map map;
        List list;
        int pos;
        int max;
        int i;
        Object key, value;
        boolean isList;
        
        pos = this.data.indexOf(':', this.pos + 2);
        max = Integer.parseInt(this.data.substring(this.pos + 2, pos));
        this.pos = pos + 2;
        map = new MixedHashMap(max);
        for (i = 0; i < max; i++)
        {
            key = unserializeObject();
            value = unserializeObject();
            map.put(key, value);
        }
        this.pos++;
        
        // Check if map is more like a list and return ArrayList then
        list = new ArrayList();
        isList = true;
        for (i = 0; i < max; i++)
        {
            key = Integer.valueOf(i);
            if (!map.containsKey(key))
            {
                isList = false;
                break;
            }
            value = map.get(Integer.valueOf(i));
            list.add(value);
        }
        if (isList)
        {
            return new Mixed(list);
        }
        else
        {
            return new Mixed(map);
        }
    }
}
