DEPRECATION NOTICE
==================

This project is no longer maintained since years as I'm no longer active in 
Java and PHP development. Feel free to take over the project if you think the 
world still needs this.


Pherialize
==========

Description
-----------

Pherialize is a small library allowing serializing Java objects into the
PHP serializing format and unserializing data from 
this format back into Java objects.
  
So with Pherialize you can serialize PHP objects and unserialize them into
Java objects and vice versa. With a similiar implementation in JavaScript
you can also use Pherialize to transfer data between Java and JavaScript if
you like.
  
Up to now Pherialize can serialize the following Java data types:
  
* null
* Boolean
* String
* Char
* Byte
* Integer
* Short
* Long
* Float
* Double
* List
* Map
* Arrays
* Serializable (Through Reflection API)
* Mixed 
* Enums (Value used as string)
  
The Unserializer supports the following PHP data types:
  
* null
* boolean
* string
* int
* double
* array
  
References are supported, too. This allows you to serialize and unserialize
even complex types with circular references in it.


License
-------

Copyright (c) 2006 Klaus Reimer <k@ailis.de>

Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.


Download
--------

The JAR can be downloaded from my [Maven Repository][1] or if you are
using Maven you can simply add it as a dependency:

    <repositories>
      <repository>
        <id>ailis-releases</id>
        <name>Ailis Maven Releases</name>
        <url>http://nexus.ailis.de/content/groups/public/</url>
      </repository>
    </repositories>

    <dependencies>
      <dependency>   
        <groupId>de.ailis.pherialize</groupId>
        <artifactId>pherialize</artifactId>
        <version>1.2.1</version>
      </dependency>
    </dependencies>


Usage
-----

### How to serialize data

For serializing a Java object into a PHP serialization format string you
just use the static method Pherialize.serialize(). Just pass the object you
want to serialize to this method and you get a string in return which you
can then unserialize in PHP.
  
Example:
  
    List list;

    list = new ArrayList();
    list.add("A string");
    list.add(Integer.valueOf(12345));
    list.add(Boolean.TRUE);
    System.out.println(Pherialize.serialize(list));

The result is printed to stdout and looks like this:
  
    a:3:{i:0;s:8:"A string";i:1;i:12345;i:2;b:1;}

Now you can use this string in PHP to unserialize it back into a PHP array:
  
    $data = unserialize('a:3:{i:0;s:8:"A string";i:1;i:12345;i:2;b:1;}');
    var_dump($data);

Result is a PHP array with exactly the data and types you have added to the
array with Java:

    array(3) {
      [0]=>
      string(8) "A string"
      [1]=>
      int(12345)
      [2]=>
      bool(true)
    }

### How to unserialize data

Let's assume you have serialized the PHP array from the previous example and
you have stored this serialized string in the variable _data_. For unserializing and
printing the value you just have to use the Pherialize.unserialize() method.

Example:
  
    MixedArray list;

    list = Pherialize.unserialize(data).toArray();
    System.out.println("Item 1: " + list.getString(0));
    System.out.println("Item 2: " + list.getInteger(1));
    System.out.println("Item 3: " + list.getBoolean(2));

The correct result printed to stdout is this:
  
    Item 1: A string
    Item 2: 12345
    Item 3: true


Types
-----

Because the data types in PHP are different to the types in Java conversion
is not always possible without switching to a different data type. If for
example you serialize a Java _Byte_ then you will get a PHP _int_ because PHP
does only knows the number types _int_ and _double_. So when you unserialize this
_int_ back into Java then you will end up with an _Integer_ and not with a _Byte_.
The same problem comes up with _Long_ objects. Pherialize serializes a _Long_
into a _int_ if it fits an _Integer_. Otherwise it's serialized into a _double_
  
To make life easier while unserializing PHP data into Java all PHP data
types are wrapped by an object of type _Mixed_. 
From this object you can 
convert the data easily in whatever you need (as long as the conversion makes
sense). 
 
Here is a complete list of the conversions performed when serializing Java
data types and unserializing them in PHP:

<table>
  <tr>
    <th>Java class</th>
    <th>PHP type</th>  
  </tr>
  <tr>
    <td>null</td>
    <td>null</td>
  </tr>
  <tr>
    <td>Boolean</td>
    <td>boolean</td>
  </tr>
  <tr>
    <td>Character</td>
    <td>string</td>
  </tr>
  <tr>
    <td>Byte</td>
    <td>int</td>
  </tr>
  <tr>
    <td>Short</td>
    <td>int</td>
  </tr>
  <tr>
    <td>Integer</td>
    <td>int</td>
  </tr>
  <tr>
    <td>Long</td>
    <td>int or double (Depending on how large the value is)</td>
  </tr>
  <tr>
    <td>Float</td>
    <td>double</td>
  </tr>
  <tr>
    <td>Double</td>
    <td>double</td>
  </tr>
  <tr>
    <td>String</td>
    <td>string</td>
  </tr>
  <tr>
    <td>Collection</td>
    <td>array</td>
  </tr>
  <tr>
    <td>Map</td>
    <td>array</td>
  </tr>
  <tr>
    <td>Serializable</td>
    <td>object</td>
  </tr>
  <tr>
    <td>Enum</td>
    <td>Value used as a string</td>
  </tr>
  <tr>
    <td>Mixed</td>
    <td>Depends on the raw type in the Mixed wrapper</td>
  </tr>
</table>

And here is a list of performed conversion when you unserialize PHP
data in Java. But note that you will never get in touch with these
conversions because it's completely hidden by the <Mixed wrapper>.

<table>
  <tr>
    <th>PHP type</th>
    <th>Java class</th>
  </tr>
  <tr>
    <td>null</td>
    <td>null</td>
  </tr>
  <tr>
    <td>boolean</td>
    <td>Boolean</td>
  </tr>
  <tr>
    <td>int</td>
    <td>Integer</td>
  </tr>
  <tr>
    <td>double</td>
    <td>Double</td>
  </tr>
  <tr>
    <td>string</td>
    <td>String</td>
  </tr>
  <tr>
    <td>array</td>
    <td>Map</td>
  </tr>
</table>
  
[1]: http://nexus.ailis.de/content/repositories/releases/de/ailis/pherialize/pherialize/ "Maven Repository"
