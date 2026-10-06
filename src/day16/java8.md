1. Why Java 8 Was RevolutionaryBefore Java 8, Java was almost purely an object-oriented language. Doing basic data processing required verbose boilerplate: anonymous inner classes, nested for loops, temporary mutable collections, and explicit state tracking.Imperative (Java 7):   Loop through elements -> Check condition -> Mutate result list
   Functional (Java 8):   Source Stream -> Declarative Pipeline (Filter -> Map) -> Collect
   Java 8 introduced Functional Programming concepts to the JVM alongside object-oriented paradigms. Instead of telling Java how to iterate and mutate state step-by-step (imperative), developers declare what transformation to apply (declarative)

2. Lambda Expressions
   What Problem Did It Solve?
   In Java 7 and earlier versions, Java treated functions as second-class citizens. You could not pass a method/function directly into another method as an argument—you could only pass primitives or object references.

To pass a unit of behavior (for example, how to sort a list or what task a thread should run), developers had to wrap that function inside an Anonymous Inner Class.

Java 7 (Passing Behavior via Object Wrapper):
Method Call ──► Anonymous Object Container (new Runnable() {...}) ──► Function

This traditional approach introduced three major architectural bottlenecks:

Syntax Bloat & Noise: Writing 5 to 6 lines of structural boilerplate just to execute a single line of actual logic made code difficult to read and maintain.

Overhead of .class Generation: Every anonymous inner class compiled into its own dedicated file on disk (e.g., PayrollProcessor$1.class). In large enterprise applications with thousands of callbacks, this drastically increased JAR sizes, slowed down application startup times, and consumed significant Metaspace / PermGen memory during class loading.

Heap Space Pollution & Garbage Collection Pressure: Every time an anonymous inner class was instantiated inside a loop or high-frequency method, a new object allocation occurred on the JVM heap space, increasing Garbage Collection (GC) frequency and latency spikes.

Syntax Breakdown
A Lambda Expression is essentially an anonymous function—a method without a declared name, return type, or access modifier—that can be passed around as if it were a data value.

Lambda Syntax: (parameters) -> {body}

The syntax consists of three main components:

Parameters: Input arguments matching the target functional interface method signature.

Arrow Token (->): Separates the parameters from the execution logic.

Body: The expressions or block of code to execute.

Syntax Variations
No Parameters:

() -> System.out.println("Processing Payroll for Suresh");

Single Parameter (Inferred Type):
When there is only one parameter, parentheses are optional, and Java's compiler automatically infers the data type from context.

salary -> salary * 1.10  // Applies 10% appraisal bonus

Multiple Parameters:

(baseSalary, allowance) -> baseSalary + allowance

Multi-line Body with Explicit Braces & Return:
When the logic requires multiple statements, standard braces {} and an explicit return statement are mandatory.

(baseSalary, taxRate) -> {
double taxDeduction = baseSalary * taxRate;
double netSalary = baseSalary - taxDeduction;
return netSalary;
};

How Lambdas Work Internally: invokedynamic
A common misconception among developers is that Java 8 lambdas are merely syntactic sugar that automatically translates into anonymous inner classes under the hood. This is completely false.

If lambdas created anonymous inner classes, they would still suffer from class-loading overhead, Metaspace pollution, and heap allocations. Instead, Java 8 leverages the invokedynamic (indy) bytecode instruction (originally introduced in JSR 292 for dynamic languages on the JVM).

ava 7 Anonymous Class Mechanics:
Source Code ──► Compiles Outer$1.class ──► ClassLoader Loads to Metaspace ──► Heap Allocation (new Outer$1())

Java 8 Lambda Mechanics:
Source Code ──► Desugars to Private Method ──► invokedynamic Instruction ──► LambdaMetafactory Linkage ──► Direct Invocation

The 3-Step Execution Pipeline of invokedynamic
1.Desugaring Phase (Compile Time):
When the Java compiler (javac) encounters a lambda expression, it extracts the lambda's body and transforms it into a private static (or instance) method inside the host class. No extra .class files are created on disk.

2.Bootstrap & CallSite Linkage (First Execution):
The first time the bytecode containing the lambda runs, the JVM executes the invokedynamic instruction, invoking a bootstrap method:

java.lang.invoke.LambdaMetafactory.metafactory(...)

LambdaMetafactory dynamically generates a lightweight, optimized call site in memory using java.lang.invoke.MethodHandle

3.Direct Execution (Subsequent Executions):
The dynamically generated call site links directly to the desugared method. For stateless lambdas, the JVM reuses the same instance, avoiding repeated heap object allocations entirely.

