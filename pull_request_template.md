Please answer the following questions about your work:

0 - What language did you program in?
* Java.

1 - Have you manually tested the SDK?
* Tested, during development, with the vision of a client that would be consuming the SDK.

2 - Did you add a test suite? If so, how will we use it? If not, why?
* There are unit tests available to test the core functionalities of the SDK.

3 - Did you use any 3rd party library? Why did you use it? What are the tradeoffs?

All the dependencies can be seen on build.gradle.kts.
* OkHttp3: Handles network requests, caching, retry mechanisms and much more.
* Retrofit: Simplifies the process of defining and interacting with REST APIs.
* RxJava: Composes asynchronous events.


4 - Do you feel this SDK makes it easier to interact with the API?
The SDK abstracts away several complexities of making HTTP requests, handling the authorization, parsing API responses, etc.
so in essence, yes.

5 - If you had more time, what else would you add?
Ideally the SDK would have a larger test coverage and would manage all the available endpoints.

6 - What would you change in your current SDK solution?
Migrate the codebase to Kotlin.
This change would be made with the understanding that Kotlin offers several advantages over Java, including:
1. Conciseness and readability.
2. Null safety.
3. Native support for asynchronous programming (in this project RxJava had to be used).
4. Kotlin is fully interoperable with Java.
5. Etc.


7 - On a scale of 1 to 10 (10 being the highest), how would you rate this solution?

8 - Anything else we should keep in mind when we evaluate the project?
