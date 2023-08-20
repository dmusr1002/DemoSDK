# The One SDK

## Building the SDK JAR with Gradle
This README provides instructions on how to build an SDK JAR using Gradle from the command line.

### Build Process
1. Clone the Repository.
2. Navigate to the SDK Project repository.
3. Build the SDK JAR:
``
   ./gradlew jar
``
4. Locate the SDK JAR.
``
    build/libs/TheOneSDK-<VERSION>.jar
``

The build process can also be done leveraging Intellij's internal gradle tasks.

## Consuming the SDK
This SDK can be consumed by any application that has interpolarity with Java (i.e., Android, Java/Kotlin frameworks, etc.) by several means:
1. Integrating the project has a submodule.
2. Integrating the JAR directly into the project.
3. Create an instance of TheOneSDKBuilder.
```kotlin
TheOneSDKBuilder builder = new TheOneSDKBuilder();
```
4. Set your access key from TheOneAPI. https://the-one-api.dev/documentation
```java
String accessToken = "your-access-token";
builder.setAPIKey(accessToken);
```
5. Configure Rate Limiter (Optional):
   
If you want to set a rate limiter for controlling API request rates, use the setRateLimiter method:
```java
RateLimiter rateLimiter = new RateLimiter(100, 1, TimeUnit.MINUTES);
builder.setRateLimiter(rateLimiter);
```
6. Build TheOneSDK Instance:

Finally, use the build method to create an instance of TheOneSDK with the configured settings:
```java
TheOneSDK theOneSDK = builder.build();
```


### Available methods
1. getMovies
2. getMovieByID
3. getQuotes
4. getQuotesByMovieID
5. getQuoteByID

All the exposed methods can be executed on a filter-basis by passing an instance of FilterData.

### Filtering Properties Usage Guide
The filtering properties in this SDK allow you to customize and refine the results of your API queries.

This guide provides an overview of how to use the filtering properties effectively.


#### FilterData Class

You can add filter criteria to your API request by creating instances of FilterContent and adding them to the FilterData object using the addFilter method. A FilterContent consists of three components:

1. **FilterOption**: Specifies the filtering operation to perform, such as exact match, negation, or regex. 
2. **FilterField**: Indicates the field in the API data to apply the filter to, such as "name" or "runtimeInMinutes."
3. **FilterValue**: Specifies the value to filter by, which can be a string or number depending on the field.

Here's an example of how to add a filter to the FilterData object:

```java
FilterData filterData = new FilterData();
filterData.addFilter(new FilterContent(FilterOption.MATCH, FilterField.NAME, "The Matrix"));
```

#### FilterOption Enum
The FilterOption enum represents various filtering operations that can be applied to filter criteria. These operations include exact match, negation, regex, and more.

Here are some of the available FilterOption values:

* **MATCH**: Performs an exact match.
* **NEGATE_MATCH**: Negates the match.
* **INCLUDE**: Includes a value.
* **EXCLUDE**: Excludes a value.
* **REGEX**: Performs filtering using regular expressions.
* **GREATER_THAN**: Filters for values greater than a specified value.
* **GREATER_THAN_OR_EQUAL_TO**: Filters for values greater than or equal to a specified value.

#### Setting a Limit
You can set a limit on the number of items to be processed per API request using the setLimit method. 

The limit ensures that only a specific number of items are returned in the response. 

If not provided, no limit will be set on the API calls.
```java
FilterData filterData = new FilterData();
filterData.setLimit(10); // Set a limit of 10 items per request
```


For more information about the available filters, please check https://the-one-api.dev/documentation.
## Executing Unit Tests
The Unit Tests can be executed directly within IntelliJ, or using the command line.
``
    ./gradlew test
``

![img.png](images%2Fimg.png)


## Conclusion
The TheOneSDKBuilder class simplifies the process of configuring and creating instances of TheOneSDK with the desired settings. By following the steps outlined in this guide, you can easily set up your SDK instance to interact with The One API while maintaining control over authentication and rate limiting.