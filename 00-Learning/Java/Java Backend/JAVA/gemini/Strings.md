## Strings in Java

In Java, a **String** is not a primitive data type but rather an object representing a sequence of characters. It is an instance of the `java.lang.String` class.

### 1. What is a String?

A **String** is essentially a sequence of characters. For example, "hello" is a string containing five characters.

|Key Detail|Description|
|:--|:--|
|**`java.lang.String` Class**|Strings are objects of the built-in `String` class in Java, located in the `java.lang` package, which is automatically imported.|
|**Representing Text**|They are used to store and manipulate text data.|
|**Sequence of Characters**|Internally, a String is backed by a character array.|

### 2. String Immutability

One of the most important characteristics of Java `String` objects is that they are **immutable**.

|Key Detail|Description|
|:--|:--|
|**Immutable**|Once a `String` object is created, its value cannot be changed. Any operation that appears to modify a `String` (e.g., concatenation, `replace()`) actually results in the creation of a **new `String` object**.|
|**Implications**| - **Thread Safety:** Multiple threads can safely share a `String` without fear of its value being changed unexpectedly. <br> - **Security:** Essential for security, e.g., when `String` objects are used for network connections, database URLs, and usernames. <br> - **Performance (String Pool):** Allows for optimization through the String Pool.|

### 3. Creating Strings

There are two primary ways to create `String` objects in Java:

|Method|Description|Example|
|:--|:--|:--|
|**String Literal**|Created by enclosing characters in double quotes. Java internally uses the **String Pool** for optimization. If a `String` with the same content already exists in the pool, the existing reference is returned; otherwise, a new `String` is created and placed in the pool.|`String s1 = "Hello";`|
|**Using `new` Keyword**|Explicitly creates a new `String` object on the heap, even if an identical `String` exists in the String Pool. This always results in a new object.|`String s2 = new String("Hello");`|

### 4. Common String Methods

The `String` class provides a rich set of methods for manipulating strings.

|Method|Description|Example|
|:--|:--|:--|
|`length()`|Returns the number of characters in the string.|`"Java".length(); // 4`|
|`charAt(int index)`|Returns the character at the specified index (0-based).|`"Java".charAt(1); // 'a'`|
|`substring(int beginIndex)`<br>`substring(int beginIndex, int endIndex)`|Returns a new string that is a substring of this string. The substring begins at `beginIndex` and extends to the character at index `endIndex - 1` (or to the end of the string if `endIndex` is omitted).|`"Hello".substring(2); // "llo"`<br>`"Hello".substring(1, 4); // "ell"`|
|`indexOf(char ch)`<br>`indexOf(String str)`|Returns the index of the first occurrence of the specified character or substring. Returns -1 if not found.|`"banana".indexOf('n'); // 2`|
|`lastIndexOf(char ch)`<br>`lastIndexOf(String str)`|Returns the index of the last occurrence of the specified character or substring. Returns -1 if not found.|`"banana".lastIndexOf('n'); // 4`|
|`equals(Object anObject)`|Compares this string to the specified object. Returns `true` if the objects are the same (same characters in the same order). **Case-sensitive.**|`"hello".equals("Hello"); // false`|
|`equalsIgnoreCase(String anotherString)`|Compares this string to another string, ignoring case considerations.|`"hello".equalsIgnoreCase("Hello"); // true`|
|`compareTo(String anotherString)`|Compares two strings lexicographically. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.|`"apple".compareTo("banana"); // < 0`|
|`concat(String str)`|Concatenates the specified string to the end of this string. The `+` operator is often preferred for readability.|`"Java".concat("Programming"); // "JavaProgramming"`|
|`trim()`|Returns a copy of the string, with leading and trailing whitespace omitted.|`"  hello ".trim(); // "hello"`|
|`toLowerCase()`|Converts all of the characters in this `String` to lowercase.|`"HELLO".toLowerCase(); // "hello"`|
|`toUpperCase()`|Converts all of the characters in this `String` to uppercase.|`"hello".toUpperCase(); // "HELLO"`|
|`replace(char oldChar, char newChar)`<br>`replace(CharSequence target, CharSequence replacement)`|Returns a new string resulting from replacing all occurrences of `oldChar` with `newChar` or `target` with `replacement`.|`"Java".replace('a', 'o'); // "Jovo"`|
|`contains(CharSequence s)`|Returns `true` if and only if this string contains the specified sequence of character values.|`"Java".contains("av"); // true`|
|`startsWith(String prefix)`<br>`endsWith(String suffix)`|Tests if this string starts or ends with the specified prefix/suffix.|`"Hello".startsWith("He"); // true`|

### 5. String Comparison

Understanding how to compare strings is crucial due to their object nature and immutability.

|Operator/Method|Description|When to Use|
|:--|:--|:--|
|**`==` Operator**|Compares the **memory addresses** (references) of two objects. It returns `true` only if both references point to the exact same object in memory.|Only when you want to check if two references point to the *same object instance*, often in specific optimization scenarios (like with `String` literals due to the pool).|
|**`.equals()` Method**|Compares the **content** (character sequence) of two `String` objects. It returns `true` if the strings have the same sequence of characters, regardless of whether they are the same object in memory.|Always use `.equals()` to compare if two strings have the *same value*.|

**Example:**
```java
String s1 = "hello";         // In String Pool
String s2 = "hello";         // In String Pool, refers to same object as s1
String s3 = new String("hello"); // New object on heap

System.out.println(s1 == s2);         // true (same object in pool)
System.out.println(s1 == s3);         // false (different objects)
System.out.println(s1.equals(s3));    // true (same content)
```

### 6. String Pool

The **String Pool** (also known as String Intern Pool) is a special storage area in the Java Heap memory.

|Key Detail|Description|
|:--|:--|
|**Optimization**|Java uses the String Pool to optimize memory usage by storing only one copy of each unique string literal.|
|**How it Works**|When a string literal is created, the JVM first checks the String Pool. If an identical string already exists, its reference is returned. Otherwise, a new string object is created in the pool, and its reference is returned.|
|**`intern()` Method**|The `String.intern()` method can be used to explicitly add a `String` object created with `new` to the String Pool (if not already present) and return its canonical representation.|

### 7. `StringBuilder` and `StringBuffer`

Since `String` objects are immutable, repeated modifications (like concatenations in a loop) can lead to the creation of many intermediate `String` objects, which can be inefficient. For mutable string operations, Java provides `StringBuilder` and `StringBuffer` classes.

|Feature|`String`|`StringBuilder`|`StringBuffer`|
|:--|:--|:--|:--|
|**Mutability**|Immutable|Mutable|Mutable|
|**Thread-Safety**|Naturally Thread-safe (due to immutability)|Not Thread-safe (faster for single-threaded use)|Thread-safe (methods are synchronized)|
|**Performance**|Poor for frequent modifications|Excellent for frequent modifications in single-threaded environments|Good for frequent modifications in multi-threaded environments (slightly slower than `StringBuilder` due to synchronization overhead)|
|**When to Use**|When the string value is constant or modified infrequently.|When string modification is frequent and only one thread is accessing the object.|When string modification is frequent and multiple threads might access the object concurrently.|

**Common Methods (for `StringBuilder`/`StringBuffer`):**

*   `append(data)`: Appends data to the end of the sequence.
*   `insert(offset, data)`: Inserts data at a specified offset.
*   `delete(start, end)`: Deletes characters from `start` to `end-1`.
*   `reverse()`: Reverses the sequence of characters.
*   `toString()`: Converts the `StringBuilder`/`StringBuffer` content back to a `String` object.