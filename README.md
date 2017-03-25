# Functional Helpers

Simple toolkit makes it easier to work with Java 8 function classes. This project does not aim to provide total functional support to Java but to redound extend business logic into smaller chunks and organize these steps into a working function.

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

To make them interchangeable, similar way as it happens at [Google](https://github.com/google/guava/blob/master/guava/src/com/google/common/base/Functions.java) with their function implementation, this toolbox provides support to wrap Predicate into Function and the way back. This works both way with one and two argument versions of them.



## Examples

### Example 1: Having a bulletproof business function

```java
final Function<String, String> businessFunction = ...;
final Function<String, String> fallbackFunction = ...;
final Function<String, String> decoratedFunction = GuardedFunction
  .doTry(RerunnableFunction.attempt(businessFunction).times(3))
  .inCaseOf(RerunnableException.class)
  .fallbackTo(fallbackFunction);
  
final String result = decoratedFunction.apply("test");
```

### Example 2: Processing input using multiple steps

```java
final Function<String, A> tokenize = ...;
final Function<A, B> query = ...;
final Function<B, C> extract = ...;
final Function<C, Result> generate = ...;
final Result defaultErrorMessage = ...;

final Function<A, C> acquireExternalData = DeterministicFunction
    .getMapCachedFunction(RerunnableFunction.attempt(query.andThen(extract)).times(3));

final Function<C, Result> guardedGenerate = NullableFunction
    .getFunctionWithConstantFallbackValue(generate, defaultErrorMessage);

final Result = acquireExternalData.andThen(tokenize).andThen(guardedGenerate).apply(input);

```
