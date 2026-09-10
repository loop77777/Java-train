# Java fundamentals — variables, data types, and arrays with instructor

Sat, 22 Aug 26

### Java Primitives and Number Literals

- Underscore in numeric literals (e.g. 15_000) is for readability only, no effect on value
- Hexadecimal prefix 0x, binary prefix 0b, octal prefix 0
- Suffix rules for literals:
    - L or l required for long (8 bytes); omitting causes compile error if value exceeds int range
    - f or F required for float; omitting defaults to double
- float vs. double precision:
    - float: 4 bytes, 7 decimal places of precision
    - double: 8 bytes, 14 decimal places of precision
    - Real-world use: GPS coordinates (latitude/longitude in maps, Uber, Swiggy) need ~16 decimal places, hence double

### Binary and Hexadecimal Conversion

- Binary 1010000 (101 followed by four zeros) = 80
    - Positional values: 2⁶×1 + 2⁵×0 + 2⁴×1 + lower bits×0 = 64 + 16 = 80
- Hexadecimal 0x64 = 100
    - 16⁰×4 + 16¹×6 = 4 + 96 = 100
- Conversion rule: work right to left, multiply each digit by its base raised to its position index

### Instance Variables, Objects, and Memory

- Two variable categories in Java: instance variables and class (static) variables
- Instance variables defined inside the class but outside any method
- Default values assigned automatically when object is created:
    - int → 0, double → 0.0, boolean → false, object/String → null
- Each object gets its own separate copy of instance variables (unlike C/C++ global variables)
- Reference variable is the name pointing to the heap object (analogy: a person’s name pointing to the physical person)
- new keyword allocates the object on the heap; reference variable holds the address
- Multiple objects of the same class are independent (day11.demo vs. demo1 example: separate room details per hotel guest)

### Arrays and Pass by Value vs. Reference

- Array: contiguous memory location holding multiple values of the same type
- Three phases to create an array: declaration, construction (heap allocation), initialization
- Arrays in Java are dynamically allocated on the heap, even for primitive types
- Eight primitive types: byte, short, int, long, float, double, char, boolean; String is a reference type
- Pass by value: a copy is passed; modifying the copy does not affect the original (Xerox analogy)
- Pass by reference: modification on the reference affects the original location

### Next Steps

- **Download Java 21 and Spring Tools for Windows**

---

Chat with meeting transcript: https://notes.granola.ai/t/98f80020-ea1f-422b-88fb-2058bce6b053-00demib2