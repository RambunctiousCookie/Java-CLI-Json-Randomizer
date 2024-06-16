# Java Json Randomizer
This is a simple project that generates a randomized json output with configurable attributes and key-value data. It uses a command-line interface.

## Setup
Simply run Maven to install the required dependencies, and then run the project via `Main`.

## Technologies/Learning Points
1. **Enum Singleton**: The singleton class `GroupJsonCreator.java` is a demonstration of the enum singleton pattern (https://dzone.com/articles/java-singletons-using-enum). This pattern enables the creation and usage of thread-safe singletons without the necessity for dependency injection, but has drawbacks in not being easy to subclass or leverage OOP with.
2. **Reflection**: The enums located in `src/main/java/reflectionPractice/PersonValues` are automatically accessed by the program and printed to a reference file every time the program is run.
3. **Recursion**: The command-line functions for  `getValidIntegerInput()` leverage recursion using a simple base case.
