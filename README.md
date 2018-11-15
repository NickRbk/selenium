# Selenium demo

## Overview

This app shows how to implement selenium framework for testing. As test framework was used TestNG which helps us to
implement parallel tests execution.

As default there is base URL `localhost:8085`. You can change this value in `constant/TestConst`.

To check memory usage we can use `jconsole` and connect to our target process.
Also we can start our target app with the following params `-verbose:gc -XX:+PrintGCTimeStamps -XX:+PrintGCDetails`.

_**There are some useful links**_:
- [Using JConsole](https://docs.oracle.com/javase/8/docs/technotes/guides/management/jconsole.html)
- [How to Fix Memory Leaks in Java](https://dzone.com/articles/how-fix-memory-leaks-java)

## Prerequisites
- start base project
- change `username` and `password` properties in `resources` with actual data.

## How to start app?
1) download project `git clone https://github.com/NickRbk/selenium.git`
2) enter to downloaded folder
3) run cmd `mvn test`