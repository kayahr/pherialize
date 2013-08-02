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

import java.nio.charset.Charset;


/**
 * The main interface to Pherialize. Just implements the static methods
 * serialize and unserialize for easier usage of the Serializer and
 * Unserializer classes.
 *
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class Pherialize
{
    /**
     * Hide constructor
     */

    private Pherialize()
    {
        // Empty
    }


    /**
     * Returns the serialized representation of the specified object.
     *
     * @param object
     *            The object to serialize
     * @param charset
     *            The charset of data.
     * @return The serialized representation of the object
     */

    public static String serialize(final Object object, Charset charset)
    {
        Serializer pherialize;

        pherialize = new Serializer(charset);
        return pherialize.serialize(object);
    }


    /**
     * Returns the serialized representation of the specified object.
     *
     * @param object
     *            The object to serialize
     * @return The serialized representation of the object
     */

    public static String serialize(final Object object)
    {
        Serializer pherialize;

        pherialize = new Serializer();
        return pherialize.serialize(object);
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
     * @param charset
     *            The charset of data.
     * @return The unserialized object
     */

    public static Mixed unserialize(final String data, Charset charset)
    {
        Unserializer unserializer;

        unserializer = new Unserializer(data, charset);
        return unserializer.unserializeObject();
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

    public static Mixed unserialize(final String data)
    {
        Unserializer unserializer;

        unserializer = new Unserializer(data);
        return unserializer.unserializeObject();
    }
}
