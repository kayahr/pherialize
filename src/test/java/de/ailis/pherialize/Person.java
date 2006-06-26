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

import java.io.Serializable;


/**
 * A test class representing a person
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class Person implements Serializable
{
    /** Serial version UID */
    private static final long serialVersionUID = 9210068051957054276L;

    /** The name */
    public String name;

    /** The age */
    protected int age;

    /** If person is an earthling */
    private boolean earthling;

    /** Special stuff */
    Object special;


    /**
     * Constructor
     * 
     * @param name
     *            The name
     * @param age
     *            The age
     * @param earthling
     *            If person is an earthling
     * @param special
     *            Special stuff
     */

    public Person(String name, int age, boolean earthling, Object special)
    {
        super();
        this.name = name;
        this.age = age;
        this.earthling = earthling;
        this.special = special;
    }


    /**
     * Returns the age.
     * 
     * @return The age
     */

    public int getAge()
    {
        return this.age;
    }


    /**
     * Sets the age.
     * 
     * @param age
     *            The age to set
     */

    public void setAge(int age)
    {
        this.age = age;
    }


    /**
     * Returns the earthling state.
     * 
     * @return The earthling state
     */

    public boolean isEarthling()
    {
        return this.earthling;
    }


    /**
     * Sets the earthling state
     * 
     * @param earthling
     *            The earthling state to set
     */

    public void setEarthling(boolean earthling)
    {
        this.earthling = earthling;
    }


    /**
     * Returns the name.
     * 
     * @return The name
     */

    public String getName()
    {
        return this.name;
    }


    /**
     * Sets the name.
     * 
     * @param name
     *            The name to set
     */

    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Returns the special stuff.
     * 
     * @return The special stuff
     */

    public Object getSpecial()
    {
        return this.special;
    }


    /**
     * Sets the special stuff.
     * 
     * @param special
     *            The special stuff to set
     */

    public void setSpecial(Object special)
    {
        this.special = special;
    }
}
