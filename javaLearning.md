# Java for a JavaScript Developer

This is a practical map of Java: the vocabulary you will hear, the conventions that make code look professional, the parts that feel different from JavaScript, and the traps interviewers like to test.

The most useful mindset shift is this:

> Java is a **statically typed, class-based language**. You describe the allowed shape of data before running the program; the compiler checks that shape. JavaScript is more flexible at runtime, while Java asks for more precision up front.

Your installed JDK is Java 26. The core examples in this guide use Java 17-compatible syntax unless noted; that is a safe baseline for many jobs and interviews.

## 1. The Java map in one minute

```text
source code (.java) --javac--> bytecode (.class) --JVM--> running program
                                  |
                                  +--> often packaged as a JAR
```

| Term | Expand it / meaning | Why you care |
|---|---|---|
| **JDK** | Java Development Kit | What you install to develop: compiler (`javac`), launcher (`java`), debugger and libraries. |
| **JVM** | Java Virtual Machine | Runs Java bytecode. The JVM is why the same compiled program can run on different operating systems with a compatible runtime. |
| **JRE** | Java Runtime Environment | Historical term for the runtime needed to run Java; in modern development, install a JDK. |
| **JIT** | Just-In-Time compiler | JVM component that can compile frequently used bytecode into fast machine code while the program runs. |
| **GC** | Garbage Collector | Reclaims memory for unreachable objects. It is automatic, but it does **not** manage files, sockets, or database connections—close those yourself. |
| **API** | Application Programming Interface | A contract of classes, methods, and behavior—e.g. `List` is an API. |
| **SDK** | Software Development Kit | A broad kit for developing with a platform; a JDK is Java's SDK. |
| **JAR** | Java ARchive | ZIP-like package of compiled classes/resources, often a library or application. |
| **OOP** | Object-Oriented Programming | Organizing code around objects, state, and behavior. |
| **POJO** | Plain Old Java Object | A normal Java class without framework-specific requirements. |
| **DTO** | Data Transfer Object | A small object/record used to move data between layers or over an API. |
| **DAO** | Data Access Object | A class/interface that isolates database persistence operations. |
| **ORM** | Object-Relational Mapping | Maps Java objects to relational database tables (for example, Hibernate/JPA). |
| **JDBC** | Java Database Connectivity | Standard Java API for talking to relational databases. |
| **IDE** | Integrated Development Environment | IntelliJ IDEA, Eclipse, VS Code, etc. |
| **LTS** | Long-Term Support | A Java release supported for longer by vendors. Learn the language, then check what version a project actually uses. |

Two commands to remember in PowerShell:

```powershell
javac HotelsAndFlight.java  # compile source to HotelsAndFlight.class
java HotelsAndFlight        # run the class (no .class extension)
```

In real projects, Maven or Gradle normally compiles, tests, downloads dependencies, and builds the JAR for you.

## 2. JavaScript → Java: the important differences

| JavaScript instinct | Java equivalent / correction |
|---|---|
| `let value = ...` can later hold any type | A Java variable has one declared type: `String name`, `int count`. `var` only infers that type; it is **not** JavaScript `var`. |
| Objects are freely extended at runtime | A Java class declares its fields and methods. You normally cannot attach a new field to one object later. |
| `undefined` and `null` | Java references can be `null`; there is no JavaScript-style `undefined`. Local variables must be definitely assigned before use. |
| Numbers are normally one `number` type | Java has `byte`, `short`, `int`, `long`, `float`, `double`, plus `BigDecimal` for exact decimal money. |
| `===` compares primitive values / object identity | Java `==` compares primitive values, but compares **identity** for objects. Use `.equals()` for value equality. |
| `Array`, `Map`, `Set` | `ArrayList`, `HashMap`, `HashSet` are common mutable implementations; program to `List`, `Map`, `Set` interfaces. |
| Array methods such as `.map()` | Streams provide `map`, `filter`, `collect`, but a plain loop is often clearer. |
| `try/catch` for errors | Java has checked exceptions: some methods require handling or declaring them. |
| `async/await` | `CompletableFuture`, threads, and framework-specific async APIs. Java does not make every I/O API awaitable by default. |

Java is **pass-by-value**. For an object parameter, the copied value is the reference. Therefore a method can mutate the object through its copied reference, but cannot make the caller's variable point to another object.

```java
static void rename(StringBuilder text) {
    text.append("!");                 // caller sees this mutation
    text = new StringBuilder("other"); // only this local copy changes
}
```

## 3. Naming standards: make your code look like Java

Java naming is conventional, but the conventions are strong. Following them instantly makes your code easier to read in a team. The Java Language Specification recommends descriptive mixed-case class/interface names and lowercase package names; widely distributed package names conventionally begin with a reversed domain. [Official naming conventions](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-6.html)

| What you name | Style | Good examples | Avoid |
|---|---|---|---|
| Package | all lowercase, dot-separated | `com.example.booking`, `in.acme.payments` | `com.Example`, `my_package` |
| Module | same family as its principal package | `com.example.booking` | arbitrary names |
| Class / record / enum | `UpperCamelCase`, noun | `BookingService`, `Hotel`, `PaymentStatus` | `booking_service`, `doBooking` |
| Interface | `UpperCamelCase`, noun or capability adjective | `List`, `PaymentGateway`, `Runnable` | an automatic `I` prefix such as `IUserService` |
| Method | `lowerCamelCase`, verb phrase | `calculateTotal()`, `sendEmail()` | `Calculate_Total()`, `total()` when it performs an action |
| Variable / parameter | `lowerCamelCase`, descriptive noun | `guestCount`, `bookingRequest` | `x`, `data`, `temp` (unless a tiny scope) |
| Boolean | lower camel case, question-like | `isPaid`, `hasSeats`, `canRetry` | `paidFlag`, `checkPaid()` |
| Constant (`static final`) | `UPPER_SNAKE_CASE` | `MAX_RETRIES`, `DEFAULT_TIMEOUT` | `maxRetries` for a true shared constant |
| Enum constant | `UPPER_SNAKE_CASE` | `PAYMENT_PENDING`, `IN_PROGRESS` | `PaymentPending` |
| Generic type parameter | short uppercase convention | `T` type, `E` element, `K` key, `V` value, `R` result | `TypeOfUser` |
| Test class / method | mirrors target; descriptive behavior | `BookingServiceTest`, `shouldRejectPastDate()` | `test1()` |

### Naming rules that prevent confusion

- A **class** represents a thing: `Invoice`, `Customer`, `BookingService`.
- A **method** does something: `createBooking`, `findById`, `isAvailable`.
- A collection name should be plural: `bookings`, `activeUsers`; one item should be singular: `booking`, `user`.
- Use a domain unit in names: `timeoutMillis`, `priceInPaise`, `createdAt`. This makes bugs visible.
- Do not include the type in the name: prefer `users`, not `userList`; prefer `name`, not `strName`.
- Avoid abbreviations unless they are universal in your domain (`id`, `url`, `http`, `sql`). `custAddr` is less readable than `customerAddress`.
- Acronyms inside camelCase normally behave as a word: `parseJson`, `customerId`, `apiClient`, `httpServer`, `url`. An established Java API may use historical exceptions such as `URL` and `UUID` as type names.
- `getName()` / `setName(...)` is familiar JavaBeans-style naming. For booleans, `isActive()` is conventional. A `record` uses component-style accessors: `booking.id()`, not `getId()`.

## 4. Read a normal Java file

```java
package com.example.booking;             // package: first non-comment statement

import java.math.BigDecimal;             // import types, not files
import java.util.Objects;

public final class Booking {             // one public top-level class per matching file
    private static final int MAX_GUESTS = 8;

    private final String id;              // instance field: each Booking has one
    private final BigDecimal totalPrice;

    public Booking(String id, BigDecimal totalPrice) { // constructor: no return type
        this.id = Objects.requireNonNull(id, "id");
        this.totalPrice = Objects.requireNonNull(totalPrice, "totalPrice");
    }

    public String getId() {               // instance method
        return id;
    }

    public boolean canAddGuests(int currentGuests, int newGuests) {
        return currentGuests + newGuests <= MAX_GUESTS;
    }
}
```

Key words:

- **Class**: blueprint/type; `Booking`.
- **Object / instance**: an actual `new Booking(...)` value.
- **Field**: data stored on a class/object; JavaScript often calls this a property.
- **Method**: a function declared in a class or interface.
- **Constructor**: code used by `new`; same name as the class and has no return type.
- **`this`**: the current object. Use it to disambiguate a field from a parameter with the same name.
- **`static`**: belongs to the class, not a particular object. `main` is static because Java starts it before it has an object.
- **`final`**: cannot be reassigned/overridden/extended depending on where it appears. A `final List` cannot point elsewhere, but the list may still be mutable.

For a public top-level class, the filename must match exactly: `Booking` lives in `Booking.java`. Packages create a namespace and commonly map to folders, e.g. `com.example.booking.Booking` → `com/example/booking/Booking.java`.

### Access control: an interview staple

| Modifier | Visible from | Typical use |
|---|---|---|
| `private` | only the declaring class | fields and implementation details |
| *(no modifier)* | classes in the same package | package-private collaboration; do not call it “default access” in code because `default` is a keyword |
| `protected` | same package, plus subclasses (with nuanced cross-package rules) | a deliberate inheritance extension point |
| `public` | everywhere the type is accessible | small, intentional API surface |

Default to `private` fields and expose behavior, not unrestricted state. That is **encapsulation**.

## 5. Types: the part Java makes explicit

### Primitive types vs references

Primitives contain their value directly; they are not objects. Reference variables contain either a reference to an object/array or `null`.

| Primitive | Typical use | Literal example | Wrapper object |
|---|---|---|---|
| `boolean` | true/false | `true` | `Boolean` |
| `byte` | compact binary data | `(byte) 12` | `Byte` |
| `short` | uncommon integer storage | `(short) 12` | `Short` |
| `int` | normal whole number | `42` | `Integer` |
| `long` | large whole number | `42L` | `Long` |
| `float` | uncommon lower-precision decimal | `1.5F` | `Float` |
| `double` | normal approximate decimal/scientific number | `1.5` | `Double` |
| `char` | one UTF-16 code unit | `'A'` | `Character` |

Use `int` for most counts, `long` for large IDs/timestamps/counts when needed, and `double` for approximate measurements. For currency, use `BigDecimal` (or store the smallest currency unit in a `long`), never `double`.

```java
// Exact decimal from text; new BigDecimal(0.1) captures a binary approximation.
BigDecimal total = new BigDecimal("0.10").add(new BigDecimal("0.20"));
```

**Widening** (`int` → `long`) is safe and implicit. **Narrowing** (`long` → `int`) requires a cast and can lose information:

```java
long visitors = 3_000_000_000L;
int truncated = (int) visitors; // compiles; value overflows—be intentional
```

### `var`, `final`, and `null`

```java
var hotelName = "Grand Palace"; // inferred as String at compile time
// hotelName = 42;              // compile error: it remains a String

final int maxGuests = 8;
// maxGuests = 9;               // compile error

String note = null;             // legal reference value
// note.length();               // NullPointerException at runtime
```

Use `var` when the right side makes the type obvious; use the explicit type when it documents an important abstraction, such as `List<Booking> bookings = ...`. Prefer `final` for parameters/local variables that should not be reassigned; it makes reasoning easier.

### Arrays are not lists

```java
int[] scores = {90, 80, 70};
scores[0] = 95;
int size = scores.length;       // field, not length()

String[] names = new String[3]; // all elements initially null
```

Arrays have fixed length and are covariant (`String[]` can be assigned to `Object[]`), which can fail later at runtime. Generic collections are safer and usually preferred.

## 6. Control flow and operators

The syntax will feel familiar: `if`, `else`, `switch`, `for`, `while`, `break`, `continue`, `&&`, `||`, `!`, ternary `?:` all exist.

```java
for (int index = 0; index < names.size(); index++) {
    System.out.println(names.get(index));
}

for (String name : names) { // enhanced for-loop / for-each
    System.out.println(name);
}

String label = switch (status) { // switch expression (modern Java)
    case CONFIRMED -> "Confirmed";
    case CANCELLED -> "Cancelled";
    default -> "Pending";
};
```

Unlike JavaScript, integer division discards the fraction:

```java
System.out.println(5 / 2);      // 2
System.out.println(5 / 2.0);    // 2.5
```

## 7. Equality, strings, and objects

Every class ultimately inherits from `Object`. The important methods are `equals`, `hashCode`, `toString`, and (rarely) `clone`.

```java
String first = new String("java");
String second = new String("java");

System.out.println(first == second);        // false: different objects
System.out.println(first.equals(second));   // true: same characters
System.out.println(Objects.equals(first, second)); // null-safe true
```

Use:

- `==` for primitive values, enum constants, and “are these literally the same object?” identity checks.
- `.equals()` for value equality; call it on a known non-null value or use `Objects.equals(a, b)`.
- `hashCode()` consistently with `equals()` whenever objects will be keys in a `HashMap` or members of a `HashSet`: equal objects **must** have equal hash codes.

`String` is immutable. Operations such as `toUpperCase` or concatenation return a new string. In a large loop, prefer `StringBuilder` over repeated `+` concatenation.

## 8. Classes, inheritance, interfaces, records, and enums

### Classes and composition

Prefer **composition** (“has a”) over inheritance (“is a”) unless the subtype genuinely satisfies the parent contract.

```java
public final class BookingService {
    private final PaymentGateway paymentGateway; // has a dependency

    public BookingService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
}
```

### Inheritance and polymorphism

```java
abstract class Payment {
    abstract boolean authorize();
}

final class CardPayment extends Payment {
    @Override
    boolean authorize() {
        return true;
    }
}

Payment payment = new CardPayment();
payment.authorize(); // dynamic dispatch: runs CardPayment's override
```

- `extends` inherits from **one** class (single implementation inheritance).
- `implements` fulfills one or more interfaces.
- `@Override` asks the compiler to verify that you really override a parent method. Use it every time you intend to override.
- An `abstract` class cannot be instantiated and may contain state/implemented methods.
- An `interface` defines a contract; it can have `default` and `static` methods, but is not a substitute for shared mutable state.

### Records: excellent DTOs/value carriers

```java
public record Guest(String name, int nights) {
    public Guest {
        if (nights < 1) {
            throw new IllegalArgumentException("nights must be positive");
        }
    }
}

Guest guest = new Guest("Asha", 2);
System.out.println(guest.name()); // component accessor, not getName()
```

A record generates `private final` component fields, accessors, a constructor, and value-based `equals`, `hashCode`, and `toString`. It is shallowly immutable: a record holding a mutable `List` does not magically make that list immutable.

### Enums: finite named values

```java
enum BookingStatus {
    PENDING, CONFIRMED, CANCELLED
}
```

Use an enum instead of strings such as `"confirmed"` when the valid values are known. Compare enum values with `==`; enum constants are single instances.

## 9. Collections and generics

Program to an interface; select the implementation based on behavior.

| Need | Interface | Usual implementation | Notes |
|---|---|---|---|
| ordered, indexed sequence | `List<E>` | `ArrayList<E>` | duplicates allowed; fast `get(index)`, append usually fast |
| unique elements | `Set<E>` | `HashSet<E>` | no duplicates; no predictable iteration order |
| key → value lookup | `Map<K,V>` | `HashMap<K,V>` | keys must have stable `equals`/`hashCode` |
| first-in-first-out | `Queue<E>` | `ArrayDeque<E>` | use for a queue |
| last-in-first-out | `Deque<E>` | `ArrayDeque<E>` | use as a stack; avoid legacy `Stack` |

```java
List<String> cities = new ArrayList<>(); // diamond <> infers String
cities.add("Delhi");

Map<String, Integer> roomsByHotel = new HashMap<>();
roomsByHotel.put("Grand Palace", 40);
int rooms = roomsByHotel.getOrDefault("Unknown", 0);
```

`List<String>` means “a list whose elements are Strings.” Generics give compile-time type safety and usually remove casts. Generic types are **invariant**: `List<Integer>` is not a `List<Number>`.

Read wildcard types as sentences:

```java
static double sum(List<? extends Number> numbers) { // produces Numbers
    double total = 0;
    for (Number number : numbers) total += number.doubleValue();
    return total;
}

static void addDefaults(List<? super Integer> destination) { // consumes Integers
    destination.add(0);
}
```

Rule of thumb: **PECS** = *Producer Extends, Consumer Super*.

### Mutable, fixed-size, and unmodifiable are different

```java
List<String> mutable = new ArrayList<>(List.of("A", "B"));
mutable.add("C");

List<String> fixedSize = Arrays.asList("A", "B");
fixedSize.set(0, "Z"); // allowed
// fixedSize.add("C"); // UnsupportedOperationException

List<String> unmodifiable = List.of("A", "B");
// unmodifiable.set(0, "Z"); // UnsupportedOperationException
```

`List.of(...)` is unmodifiable and rejects `null` elements; it does not deep-freeze mutable elements. [Official collection-factory documentation](https://docs.oracle.com/en/java/javase/26/core/creating-immutable-lists-sets-and-maps.html)

## 10. Exceptions and resource management

An exception is an object that interrupts normal flow. Important categories:

| Category | Examples | Must the compiler force you to handle it? |
|---|---|---|
| Checked exception | `IOException`, `SQLException` | Yes: catch it or declare `throws` (with some special cases). |
| Unchecked exception (`RuntimeException`) | `NullPointerException`, `IllegalArgumentException`, `IndexOutOfBoundsException` | No. Usually indicates a programming/validation problem. |
| Error | `OutOfMemoryError`, `StackOverflowError` | No. Usually do not catch these as normal application flow. |

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    return reader.readLine();
} catch (IOException exception) {
    throw new UncheckedIOException("Could not read " + path, exception);
} // reader closes automatically even if reading throws
```

Use **try-with-resources** for anything implementing `AutoCloseable` (files, streams, sockets, database statements). It closes resources deterministically; GC is not a cleanup strategy.

Good exceptions explain the violated rule and preserve the cause when wrapping:

```java
throw new IllegalArgumentException("guestCount must be between 1 and 8");
throw new BookingException("Payment provider failed", cause);
```

Do not use exceptions for normal branching, swallow them with an empty `catch`, or write `catch (Exception e)` without a real recovery/translation plan.

## 11. Lambdas, streams, and `Optional`

### Lambdas and functional interfaces

A **functional interface** has one abstract method. Lambdas implement one concisely.

```java
Predicate<Guest> isLongStay = guest -> guest.nights() >= 7;
Function<Guest, String> nameOf = Guest::name; // method reference
```

Common types: `Predicate<T>` answers true/false, `Function<T, R>` transforms, `Consumer<T>` takes a value and returns nothing, and `Supplier<T>` produces a value.

### Streams

Streams are pipelines over data, not a collection type. Intermediate operations (`filter`, `map`, `sorted`) are lazy; a terminal operation (`toList`, `collect`, `count`, `forEach`) runs the pipeline.

```java
List<String> names = guests.stream()
        .filter(guest -> guest.nights() >= 2)
        .map(Guest::name)
        .sorted()
        .toList(); // returns an unmodifiable list in current Java
```

Do not mutate the source collection during a stream. Do not reach for parallel streams until you understand the workload, side effects, and thread safety. A simple loop is often the best answer in an interview when clarity matters.

### `Optional<T>`

`Optional` represents a possibly absent result—not a replacement for every nullable field/parameter.

```java
Optional<Guest> guest = repository.findById(id);
String name = guest.map(Guest::name).orElse("Unknown");
```

Avoid `optional.get()` unless absence is impossible by a proven invariant. Prefer `orElse`, `orElseGet`, `orElseThrow`, `map`, and `flatMap`.

## 12. The Java interview edge-case deck

For each item, predict the output/error before reading the explanation. That is how these become instinctive.

### 1. `==` versus `.equals()`

```java
String a = new String("hi");
String b = new String("hi");
System.out.println(a == b);       // false
System.out.println(a.equals(b));  // true
```

`==` on references checks identity, not contents. The language specification explicitly calls out `String` here. [JLS equality operators](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-15.html)

### 2. Wrapper identity and the Integer cache

```java
Integer smallA = 127;
Integer smallB = 127;
Integer bigA = 128;
Integer bigB = 128;

System.out.println(smallA == smallB); // true
System.out.println(bigA == bigB);     // do not rely on a result
System.out.println(bigA.equals(bigB)); // true
```

Boxing constant integral values from `-128` through `127` is guaranteed to produce indistinguishable references; beyond that, identity assumptions are invalid. Always use `.equals()` for wrapper values. [JLS boxing conversion](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-5.html)

### 3. Unboxing `null`

```java
Integer guests = null;
// int count = guests; // NullPointerException: implicit unboxing calls intValue()
```

Wrappers can be `null`; primitives cannot. Be careful with `Boolean`, `Integer`, and values from maps/DTOs.

### 4. `final` reference is not immutable object

```java
final List<String> cities = new ArrayList<>();
cities.add("Mumbai");  // allowed
// cities = new ArrayList<>(); // not allowed
```

`final` prevents reassignment of the variable. It does not freeze the referenced list.

### 5. `byte` arithmetic promotes to `int`

```java
byte value = 127;
// value = value + 1; // compile error: expression is int
value += 1;           // compiles; implicit cast; value becomes -128 (overflow)
```

Do not use narrow integer types for normal arithmetic without thinking about promotion and overflow.

### 6. Integer overflow is silent

```java
int max = Integer.MAX_VALUE;
System.out.println(max + 1); // -2147483648
```

Use `Math.addExact`, `Math.multiplyExact`, a wider type, or `BigInteger` when overflow must be detected/prevented.

### 7. `NaN` is not equal to itself

```java
double notANumber = Double.NaN;
System.out.println(notANumber == notANumber); // false
System.out.println(Double.isNaN(notANumber)); // true
```

The JLS also specifies that `-0.0 == 0.0` is true. Do not use direct floating-point equality for calculated decimal values without a carefully selected tolerance/strategy.

### 8. `List<Integer>.remove` chooses index overload

```java
List<Integer> numbers = new ArrayList<>(List.of(10, 20, 30));
numbers.remove(1);                // removes index 1 (20)
numbers.remove(Integer.valueOf(10)); // removes the value 10
```

Overloading selects `remove(int index)` for the primitive literal `1`.

### 9. `Arrays.asList` plus a primitive array

```java
int[] values = {1, 2, 3};
List<int[]> list = Arrays.asList(values); // one element: the whole int[]
System.out.println(list.size());           // 1
```

Generics work with reference types, not primitives. Use `Arrays.stream(values).boxed().toList()` when you really need a `List<Integer>`.

### 10. Array covariance fails at runtime; generics prevent it earlier

```java
Object[] objects = new String[1];
// objects[0] = 42; // compiles, then ArrayStoreException at runtime

// List<Object> objects = new ArrayList<String>(); // does not compile
```

This is why generic collections are generally safer than arrays.

### 11. Mutating a `HashMap` key breaks lookup

```java
// If a key's fields used by equals/hashCode change after put(),
// the map may no longer find that key in its original hash bucket.
```

Use immutable keys (records are often suitable when their components are also stable) or never mutate equality-relevant fields while the key is in a hash-based collection.

### 12. Overloading is compile-time; overriding is runtime

```java
class Printer {
    void print(Object value) { System.out.println("object"); }
    void print(String value) { System.out.println("string"); }
}

Object value = "hello";
new Printer().print(value); // object: overload picked from declared type Object
```

Method **overloading** selects a signature at compile time. Method **overriding** selects an implementation at runtime based on the actual object. `static`, `private`, and `final` methods are not polymorphically overridden.

### 13. `finally` can hide a result or exception

```java
static int surprising() {
    try { return 1; }
    finally { return 2; }
}
// surprising() returns 2
```

Never `return`, `throw`, or otherwise abruptly complete from `finally`; it can suppress the original return/exception.

### 14. `switch` fall-through is real in statement form

```java
switch (status) {
    case PENDING:
        notifyUser();
    case CONFIRMED:
        chargeCard(); // also runs for PENDING without break/return
        break;
    default:
        logUnknown();
}
```

Prefer arrow-style switch expressions/statements when appropriate; they do not fall through.

### 15. `orElse` is eager; `orElseGet` is lazy

```java
String name = Optional.of("Asha").orElse(expensiveFallback());
// expensiveFallback() runs anyway

String lazyName = Optional.of("Asha").orElseGet(this::expensiveFallback);
```

Use `orElseGet` when creating the fallback is costly or has side effects.

## 13. A strong answer framework for interviews

When asked a Java question, answer in this order:

1. State the rule precisely: “`==` compares object references; `.equals` compares the value according to that type.”
2. Give a tiny example.
3. State the practical choice: “For strings and DTO-like objects, I use `Objects.equals` or `.equals`; I reserve `==` for primitives, enums, and intentional identity checks.”
4. Mention the edge case only if useful: null safety, hash-code contract, wrapper cache, etc.

High-frequency topics to be ready for:

- JDK/JVM/JRE and compile → bytecode → JVM execution
- primitive vs wrapper; boxing/unboxing
- `==`, `.equals`, `hashCode`, immutable strings
- `final`, `finally`, `finalize` (the latter is obsolete/deprecated; do not use it)
- interface vs abstract class; composition vs inheritance
- overloading vs overriding; `static` and polymorphism
- access modifiers and package-private
- checked vs unchecked exceptions; try-with-resources
- `ArrayList` vs `LinkedList`; `HashMap` behavior; `HashMap` vs `ConcurrentHashMap`
- generic invariance and `? extends` / `? super`
- `synchronized`, race conditions, immutability, thread-safe collections (learn concepts before memorizing APIs)
- `List.of`, `Arrays.asList`, `Collections.unmodifiableList`, and what “immutable/unmodifiable” actually means

## 14. A focused four-week path

Do the coding, not just the reading. Write each idea from scratch and deliberately trigger its failure case.

| Week | Learn | Build / practise |
|---|---|---|
| 1 | syntax, types, operators, methods, arrays, control flow, classes, constructors, access modifiers | Rebuild `HotelsAndFlight` with input validation, then write 15 tiny “predict output” programs. |
| 2 | collections, generics, strings, equality, records/enums, exceptions, file I/O | Create a small booking CLI: add/search/cancel bookings; persist simple text/CSV data with try-with-resources. |
| 3 | interfaces, inheritance, composition, streams, lambdas, `Optional`, unit testing with JUnit | Add a `PaymentGateway` interface with fake implementations; test success/failure behavior. |
| 4 | Maven/Gradle, HTTP/JSON, SQL/JDBC or Spring Boot basics, concurrency foundations | Build a small REST API or command-line expense tracker; explain every design choice aloud. |

Daily routine: 30–45 minutes of focused code, 10 minutes of reading error messages, and one edge case from section 12. Never copy an error fix without explaining what type, scope, access rule, or runtime state caused it.

## 15. Your next concrete exercises

1. Refactor `HotelsAndFlight`: rename `totalBills` to `totalRevenue`, reject negative bills, make its fields `final` where possible, and decide whether money should be `BigDecimal`.
2. Write `Booking` as a record with validation and an enum `BookingStatus`.
3. Implement `List<Booking>` operations: add, find by ID, remove, total price. Use `Objects.equals` for ID comparison.
4. Write tests for empty input, `null`, duplicate ID, index zero/out-of-range, negative money, and very large guest counts.
5. Explain aloud why a `HashMap` key must not mutate after insertion.

## Reliable references

- [Java Language Specification, Java SE 26](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/index.html) — the precise language rules.
- [JLS naming conventions](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-6.html) — packages, classes, interfaces, methods, fields, constants, type parameters.
- [JLS conversions and boxing](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-5.html) — primitive/wrapper behavior.
- [JLS expressions and equality](https://docs.oracle.com/en/java/javase/26/docs/specs/jls/jls-15.html) — `==`, numeric behavior, operators, lambdas, switch expressions.
- [Java core libraries documentation](https://docs.oracle.com/en/java/javase/26/core/index.html) and [API reference](https://docs.oracle.com/en/java/javase/26/docs/api/index.html) — look up exact standard-library behavior.
- [Creating unmodifiable collections](https://docs.oracle.com/en/java/javase/26/core/creating-immutable-lists-sets-and-maps.html) — `List.of`, `Set.of`, and `Map.of` semantics.

---

### Pocket checklist

```text
Types first.  Objects may be null.  == is identity for objects.
equals + hashCode travel together.  Prefer interfaces and composition.
Close resources.  Make invalid states hard to create.
Name types as nouns, methods as verbs, booleans as questions.
```
