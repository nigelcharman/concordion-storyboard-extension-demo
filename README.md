# Introduction
------------

This project demonstrates the usage of the [Concordion](http://concordion.org) [Storyboard Extension](http://github.com/concordion/concordion-storyboard-extension) with [Selenium WebDriver](http://docs.seleniumhq.org/projects/webdriver/).

Example output is shown [here](http://concordion.github.io/concordion-storyboard-extension-demo/spec/org/concordion/ext/demo/StoryboardDemo.html).

# Running the tests
---------------------------

The tests use Selenium's FirefoxDriver, so you'll need to have Firefox installed (or you could change the code to use a different driver).

The download includes support to run the tests with the [Gradle](http://www.gradle.org/) build tool. The code base includes the Gradle Wrapper, which will automatically download the correct version of Gradle. 

Gradle can be run from the command line or from your IDE:

Command line
============
From the command line, `cd` to the folder containing a copy of this project, and run 

  `./gradlew clean test` on Unix-based systems, or 
  `.\gradlew clean test` on Windows.

This will download the required dependencies, clean the existing project, recompile all source code and run all the tests. 

View the Concordion output in `build/reports/spec/org/concordion/ext/demo/StoryboardDemo.html`.


IDE
===
For Eclipse and NetBeans, you will need to install a Gradle plugin to your IDE before importing the project. See [Gradle tooling](https://www.gradle.org/tooling) for details.

On importing the project to your IDE, the required dependencies will be downloaded.

Under the `src/test/java` folder, find the `StoryboardDemo` class in the `org.concordion.ext.demo` package and run as a JUnit test. The location of the Concordion output is shown on the standard output console.

What you should see
-------------------

The tests will open (and close) a number of Firefox browsers and perform a Google search.
    
### JUnit output
The test should pass successfully, though the console output will show a failure with the message:

> <-- Note: This test has been marked as EXPECTED_TO_FAIL
...\concordion\org\concordion\ext\demo\StoryboardDemoFailing.html
Successes: 0, Failures: 0, Exceptions: 1   <-- Note: This test has been marked as EXPECTED_TO_FAIL

> ...\concordion\org\concordion\ext\demo\StoryboardDemoException.html
Successes: 0, Failures: 0, Exceptions: 1   <-- Note: This test has been marked as EXPECTED_TO_FAIL

> ...\concordion\org\concordion\ext\demo\StoryboardDemo.html
Successes: 0, Failures: 0, Ignored: 2, Exceptions: 1


This test deliberately fails in order to demonstrate the extension.  It uses Concordion's `@ExpectedToFail` annotation to keep the JUnit passing (you'd normally only use this when you have a partially implemented feature).

### Concordion output
The output folder should contain the following specification. (You can see an example of it [here](http://concordion.github.io/concordion-storyboard-extension-demo/spec/org/concordion/ext/demo/StoryboardDemo.html).
    
#### StoryboardDemo.html
3 links to a numnber of specifications that demonstrate different aspects of the storyboard

Potential Issues
------------------------
### Proxy

If you are behind a HTTP proxy server, you may need to configure the proxy to allow access to www.google.com

The easiest way to do this may be to add the following lines to the Browser() constructor:

```java
    System.setProperty("http.proxyHost", "<proxy.host>");
    System.setProperty("http.proxyPort", "<proxy.port>");
```    

replacing `<proxy.host>` with the host name of the proxy server, and `<proxy.port>` with the port number.

If your proxy requires authentication, you will also need to set the properties `http.ProxyUser` and `http.proxyPassword`.


Additional Gradle Files
-----------------------
`dev.gradle` is only needed if you want to run against snapshot or local builds of the concordion-screenshot-extension.

`publish.gradle` is only needed if you want to publish the output to Github pages.

If copying the project for your own use, you probably won't want either of these files.


Mailing List
-----------------
Feel free to discuss this demo project on the Concordion [mailing list](https://groups.google.com/d/forum/concordion).
