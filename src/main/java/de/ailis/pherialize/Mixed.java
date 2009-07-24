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

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * A mixed data type. This data type is used to represent PHP values like
 * string, int and double. It can be converted to any other Java type by using
 * one of the to*() methods. Conversion errors results in returning a 0 because
 * that's the same behaviour as in PHP.
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class Mixed implements Serializable, Comparable<Object>
{
    /** Serial version UID */
    private static final long serialVersionUID = -599069055376420973L;

    /** Constant for unknown type */
    public static final int TYPE_UNKNOWN = -1;

    /** Constant for type "character" */
    public static final int TYPE_CHAR = 0;

    /** Constant for type "byte" */
    public static final int TYPE_BYTE = 1;

    /** Constant for type "byte" */
    public static final int TYPE_SHORT = 2;

    /** Constant for type "byte" */
    public static final int TYPE_INT = 3;

    /** Constant for type "byte" */
    public static final int TYPE_LONG = 4;

    /** Constant for type "byte" */
    public static final int TYPE_FLOAT = 5;

    /** Constant for type "byte" */
    public static final int TYPE_DOUBLE = 6;

    /** Constant for type "byte" */
    public static final int TYPE_BOOLEAN = 7;

    /** Constant for type "string" */
    public static final int TYPE_STRING = 8;

    /** Constant for type "array" */
    public static final int TYPE_ARRAY = 9;

    /** The value */
    private Object value;

    /** The data type */
    private final int type;


    /**
     * Constructor
     * 
     * @param value
     *            The real value
     */

    public Mixed(final Object value)
    {
        super();
        this.type = Mixed.getTypeOf(value);
        switch (this.type)
        {
            case TYPE_STRING:
                this.value = value.toString();
                break;

            case TYPE_ARRAY:
                if (value instanceof MixedArray)
                {
                    this.value = value;
                }
                else if (value instanceof List<?>)
                {
                    this.value = new MixedArray((List<?>) value);
                }
                else
                {
                    this.value = new MixedArray((Map<?, ?>) value);
                }
                break;

            default:
                this.value = value;
        }
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final char value)
    {
        super();
        this.value = Character.valueOf(value);
        this.type = TYPE_CHAR;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final byte value)
    {
        super();
        this.value = Byte.valueOf(value);
        this.type = TYPE_BYTE;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final short value)
    {
        super();
        this.value = Short.valueOf(value);
        this.type = TYPE_SHORT;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final int value)
    {
        super();
        this.value = Integer.valueOf(value);
        this.type = TYPE_INT;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final long value)
    {
        super();
        this.value = Long.valueOf(value);
        this.type = TYPE_LONG;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final float value)
    {
        super();
        this.value = Float.valueOf(value);
        this.type = TYPE_FLOAT;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final double value)
    {
        super();
        this.value = Double.valueOf(value);
        this.type = TYPE_DOUBLE;
    }


    /**
     * Constructor
     * 
     * @param value
     *            The mixed value
     */

    public Mixed(final boolean value)
    {
        this.value = Boolean.valueOf(value);
        this.type = TYPE_BOOLEAN;
    }


    /**
     * Returns the type of the specified object
     * 
     * @param value
     *            The object
     * @return The type
     */

    public static int getTypeOf(final Object value)
    {
        if (value instanceof String
            || (value != null && value.getClass().isEnum()))
        {
            return TYPE_STRING;
        }
        else if (value instanceof Integer)
        {
            return TYPE_INT;
        }
        else if (value instanceof List<?>)
        {
            return TYPE_ARRAY;
        }
        else if (value instanceof Map<?, ?>)
        {
            return TYPE_ARRAY;
        }
        else if (value instanceof Character)
        {
            return TYPE_CHAR;
        }
        else if (value instanceof Byte)
        {
            return TYPE_BYTE;
        }
        else if (value instanceof Short)
        {
            return TYPE_SHORT;
        }
        else if (value instanceof Integer)
        {
            return TYPE_INT;
        }
        else if (value instanceof Long)
        {
            return TYPE_LONG;
        }
        else if (value instanceof Float)
        {
            return TYPE_FLOAT;
        }
        else if (value instanceof Double)
        {
            return TYPE_DOUBLE;
        }
        else if (value instanceof Boolean)
        {
            return TYPE_BOOLEAN;
        }
        else
            return TYPE_UNKNOWN;
    }


    /**
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }


    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(final Object other)
    {
        if (other == null) return false;
        if (other instanceof Mixed)
        {
            return toString().equals(other.toString());
        }
        else
        {
            return toString().equals(other.toString());
        }
    }


    /**
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString()
    {
        if (this.value == null) return null;
        return this.value.toString();
    }


    /**
     * Returns the value converted to the specified type.
     * 
     * @param type
     *            The type to convert the object to
     * @return The converted type
     */

    public Object toType(final int type)
    {
        // Return the value right away if it's already the correct type
        if (this.type == type) return this.value;

        switch (type)
        {
            case TYPE_BOOLEAN:
                return Boolean.valueOf(toBoolean());
            case TYPE_BYTE:
                return Byte.valueOf(toByte());
            case TYPE_CHAR:
                return Character.valueOf(toChar());
            case TYPE_DOUBLE:
                return Double.valueOf(toFloat());
            case TYPE_FLOAT:
                return Float.valueOf(toFloat());
            case TYPE_INT:
                return Integer.valueOf(toInt());
            case TYPE_LONG:
                return Long.valueOf(toLong());
            case TYPE_ARRAY:
                return toArray();
            case TYPE_SHORT:
                return Short.valueOf(toShort());
            case TYPE_STRING:
                return toString();
            default:
                return this.value;
        }
    }


    /**
     * Returns mixed value as a char. If value is not a char then the first
     * character of the string representation of the value is returned. If this
     * string is empty then a 0-character is returned.
     * 
     * @return Mixed value as a char
     */

    public char toChar()
    {
        if (this.value == null) return 0;
        if (isArray())
            return (char) (((MixedArray) this.value).size() == 0 ? 0 : 1);
        if (toString().length() > 0)
        {
            return toString().charAt(0);
        }
        return 0;
    }


    /**
     * Returns mixed value as a short. If value is a boolean value then 0 is
     * returned if value is false and 1 is returned if value is true. For any
     * other data type a conversion is tried and 0 is returned if this fails.
     * 
     * @return Mixed value as a short
     */

    public short toShort()
    {
        if (this.value == null) return 0;
        if (isBoolean()) return toBoolean() ? (short) 1 : (short) 0;
        if (isArray())
            return (short) (((MixedArray) this.value).size() == 0 ? 0 : 1);
        try
        {
            return (short) toLong();
        }
        catch (final NumberFormatException e)
        {
            return 0;
        }
    }


    /**
     * Returns mixed value as a byte. If value is a boolean value then 0 is
     * returned if value is false and 1 is returned if value is true. For any
     * other data type a conversion is tried and 0 is returned if this fails.
     * 
     * @return Mixed value as a byte
     */

    public byte toByte()
    {
        if (this.value == null) return 0;
        if (isBoolean()) return toBoolean() ? (byte) 1 : (byte) 0;
        if (isArray())
            return (byte) (((MixedArray) this.value).size() == 0 ? 0 : 1);
        try
        {
            return (byte) toLong();
        }
        catch (final NumberFormatException e)
        {
            return 0;
        }
    }


    /**
     * Returns mixed value as an int. If value is a boolean value then 0 is
     * returned if value is false and 1 is returned if value is true. For any
     * other data type a conversion is tried and 0 is returned if this fails.
     * 
     * @return Mixed value as an int
     */

    public int toInt()
    {
        if (this.value == null) return 0;
        if (isBoolean()) return toBoolean() ? 1 : 0;
        if (isArray()) return ((MixedArray) this.value).size() == 0 ? 0 : 1;
        try
        {
            return (int) toLong();
        }
        catch (final NumberFormatException e)
        {
            return 0;
        }
    }


    /**
     * Returns mixed value as a long. If value is a boolean value then 0 is
     * returned if value is false and 1 is returned if value is true. For any
     * other data type a conversion is tried and 0 is returned if this fails.
     * 
     * @return Mixed value as a long
     */

    public long toLong()
    {
        if (this.value == null) return 0;
        if (isBoolean()) return toBoolean() ? 1 : 0;
        if (isArray()) return ((MixedArray) this.value).size() == 0 ? 0 : 1;
        try
        {
            return (long) toDouble();
        }
        catch (final NumberFormatException e)
        {
            return 0;
        }
    }


    /**
     * Returns mixed value as a float. If value is a boolean value then 0 is
     * returned if value is false and 1 is returned if value is true. For any
     * other data type a conversion is tried and 0 is returned if this fails.
     * 
     * @return Mixed value as a float
     */

    public float toFloat()
    {
        if (this.value == null) return 0;
        if (isBoolean()) return toBoolean() ? 1 : 0;
        if (isArray()) return ((MixedArray) this.value).size() == 0 ? 0 : 1;
        try
        {
            return Float.parseFloat(this.value.toString());
        }
        catch (final NumberFormatException e)
        {
            return 0;
        }
    }


    /**
     * Returns the value as an array. If value is not an array then null is
     * returned.
     * 
     * @return The value as an array
     */

    public MixedArray toArray()
    {
        if (this.value == null) return null;
        if (isArray())
        {
            return (MixedArray) this.value;
        }
        else
        {
            return null;
        }
    }


    /**
     * Returns mixed value as a double. If value is a boolean value then 0 is
     * returned if value is false and 1 is returned if value is true. For any
     * other data type a conversion is tried and 0 is returned if this fails.
     * 
     * @return Mixed value as a double
     */

    public double toDouble()
    {
        if (this.value == null) return 0;
        if (isBoolean()) return toBoolean() ? 1 : 0;
        if (isArray()) return ((MixedArray) this.value).size() == 0 ? 0 : 1;
        try
        {
            return Double.parseDouble(this.value.toString());
        }
        catch (final NumberFormatException e)
        {
            return 0;
        }
    }


    /**
     * Returns mixed value as a boolean. Everything except a real false, an
     * empty string, a numerical 0, a 0 character or an empty list/map is
     * handled as "true".
     * 
     * @return Mixed value as a boolean
     */

    public boolean toBoolean()
    {
        if (this.value == null) return false;
        if (isBoolean())
        {
            return ((Boolean) this.value).booleanValue();
        }
        else if (isString())
        {
            return toString().length() > 0;
        }
        else if (isNumber())
        {
            return toDouble() != 0;
        }
        else if (isChar())
        {
            return toChar() != '\0';
        }
        else if (isArray())
        {
            return toArray().size() > 0;
        }
        else
            return false;
    }


    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */

    @SuppressWarnings("unchecked")
    public int compareTo(final Object other)
    {
        if (other instanceof Mixed)
        {
            return ((Comparable<Object>) this.value)
                .compareTo(((Mixed) other).value);
        }
        else
        {
            return ((Comparable<Object>) this.value).compareTo(other);
        }
    }


    /**
     * Returns the data type (One of the TYPE_* constants)
     * 
     * @return The data type
     */

    public int getType()
    {
        return this.type;
    }


    /**
     * Returns the raw value.
     * 
     * @return The raw value
     */

    public Object getValue()
    {
        return this.value;
    }


    /**
     * Checks if type is a char.
     * 
     * @return If type is a char
     */

    public boolean isChar()
    {
        return this.type == TYPE_CHAR;
    }


    /**
     * Checks if type is a byte.
     * 
     * @return If type is a byte
     */

    public boolean isByte()
    {
        return this.type == TYPE_BYTE;
    }


    /**
     * Checks if type is a short.
     * 
     * @return If type is a short
     */

    public boolean isShort()
    {
        return this.type == TYPE_SHORT;
    }


    /**
     * Checks if type is a int.
     * 
     * @return If type is a int
     */

    public boolean isInt()
    {
        return this.type == TYPE_INT;
    }


    /**
     * Checks if type is a long.
     * 
     * @return If type is a long
     */

    public boolean isLong()
    {
        return this.type == TYPE_LONG;
    }


    /**
     * Checks if type is a float.
     * 
     * @return If type is a float
     */

    public boolean isFloat()
    {
        return this.type == TYPE_FLOAT;
    }


    /**
     * Checks if type is a double.
     * 
     * @return If type is a double
     */

    public boolean isDouble()
    {
        return this.type == TYPE_DOUBLE;
    }


    /**
     * Checks if type is a boolean.
     * 
     * @return If type is a boolean
     */

    public boolean isBoolean()
    {
        return this.type == TYPE_BOOLEAN;
    }


    /**
     * Checks if type is a number (Float, Double, Byte, Short, Int or Long).
     * 
     * @return If type is a number
     */

    public boolean isNumber()
    {
        return isFloat() || isDouble() || isByte() || isShort() || isInt()
            || isLong();
    }


    /**
     * Checks if type is a string.
     * 
     * @return If type is a string
     */

    public boolean isString()
    {
        return this.type == TYPE_STRING;
    }


    /**
     * Checks if type is an array.
     * 
     * @return If type is a array
     */

    public boolean isArray()
    {
        return this.type == TYPE_ARRAY;
    }
}
