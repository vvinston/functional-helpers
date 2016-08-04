# Functional Helpers

Simple toolkit makes it easier to work with Java 8 functional classes.

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
