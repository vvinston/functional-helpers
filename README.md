# Functional Helpers

Simple toolkit makes it easier to work with Java 8 function classes. This project does not aim to provide total functional support to Java but to redound extend business logic into smaller chunks and organize these steps into a working function.

## Usage

The toolkit provides two facade classes for the provide access to the features, called _Functions_ and _Predicates_. Setting up and decorating functions might be done by calling static methods on these. The implementation classes are not directly reachable.     

## Flavors

The helper classes come in multiple flavors. Depending on the implemented interface(s) they could come with different arguments and somewhat different behaviour, but the basic idea is the same behind. The project currently comes in the following flavors:

##### Conditional

For the case when multiple ways of execution is possible based on the content of the input.

##### Either

For the case depending on the input we would like to return with different data type. Highly similar with Conditional flavor but adds extra flexibility with return type.

##### Deterministic

For the case we expect the very same result for the same input every times (like in case of real functional programming) and we want to avoid calculate it again.

##### Memoic

Similar to deterministic family but with less flexibility. This implementation builds on the top of map's _computeIfAbsent_ method, preventing the usage of various cache implementations but map. Contrary in some cases this might be faster

##### Guarded

For the case payload might throws exception but we want to handle it within the function.

##### Nullable

For the case decorated function could return with null and we want to enforce type correctness.

##### Rerunnable

For the case the original function could be ended up with throwing exception (just like in case of Guarded flavor) and we want to do more attempts calling it.

## Support matrix

The following table shows the support of the different flavors and interfaces:

|            	| Conditional 	| Either    	| Deterministic 	|Memoic         | Guarded   	| Nullable  	| Rerunnable 	|
|------------	|-------------	|-----------	|---------------	|-----------    |-----------	|-----------	|------------	|
| Function   	| Supported   	| Supported 	| Supported     	| Supported		| Supported 	| Supported 	| Supported  	|
| BiFunction 	| Supported   	| Supported   	| Supported       	| 				| Supported   	| Supported 	| Supported    	|
| Consumer   	| Supported   	|           	|               	|				| Supported 	|           	| Supported    	|
| BiConsumer 	| Supported   	|           	|               	|				| Supported   	|           	| Supported    	|
| Supplier   	|           	|           	|               	|				| Supported   	| Supported    	| Supported    	|

## Predicate wrappers

To make them interchangeable, similar way as it happens at [Google](https://github.com/google/guava/blob/master/guava/src/com/google/common/base/Functions.java) with their function implementation, this toolbox provides support to wrap Predicate into Function and the way back. This works both way with one and two argument versions of them. The following mappings are possible:

- Function -> Predicate
- Predicate -> Function
- BiFunction -> BiPredicate
- BiPredicate -> BiFunction

## Partial application

For BiFunctions and BiConsumers are solving the conversion from two-parameter solutions into single-parameter solutions.

Just like in case of partial application within functional programming, from the client perspective, one of the two parameters of the function (or comsumer) will be bounded and during application only the other one must be provided. The bounding will happen during creation of the instance.
  
The partial applications exist in two flavors, left and right. The left will bound the first parameter and the right will bound the second respectively.

|            | Bounding first parameter | Bounding second parameter |
| ---------- | -----------              | -----------               |
| BiFunction | LeftAppliedFunction      | RightAppliedFunction      |
| BiConsumer | LeftAppliedConsumer      | RightAppliedConsumer      |

## Examples

### Example 1: Having a bulletproof business function

```java
final int numberOfRetries = 3;
final Function<String, String> businessFunction = i -> i;
final Function<String, String> fallbackFunction = i -> i;
final Function<String, String> composed = Functions
        .doTry(Functions.attempt(businessFunction).times(numberOfRetries))
        .inCaseOf(RerunnableException.class)
        .fallbackTo(fallbackFunction);

final String result = composed.apply("example");
```

### Example 2: Processing input using multiple steps

```java
final int numberOfRetries = 3;
final Function<String, Token> tokenize = i -> new Token();
final Function<Token, Query> createQuery = i -> new Query();
final Function<Query, Result> execute = i -> new Result();
final Function<Result, Optional<Integer>> extract = i -> Optional.of(1);

final Function<String, Query> generateQuery = Functions.deterministic(tokenize.andThen(createQuery));
final Function<Query, Result> guardedExecute = Functions
        .doTry(Functions.attempt(execute).times(numberOfRetries))
        .inCaseOf(RerunnableException.class)
        .fallbackTo(i -> new Result());
final Function<Result, Integer> guardedExtract = Functions.nullableWithFallbackValue(extract, 0);

final Function<String, Integer> acquireData = generateQuery.andThen(guardedExecute).andThen(guardedExtract);
```

### Example 3: Wrapping function around with predicate

```java
final Function<String, Boolean> function1 = i -> false;
final Function<String, Integer> function2 = i -> 1;
final Function<String, Integer> function3 = i -> 2;

final Function<String, Integer> composed = Functions
        .when(Functions.predicateOf(function1))
        .then(function2)
        .otherwise(function3);
```