# Mobiquity Test

## Design Patterns

* Decorator - it's used for include extra functionality in runtime when adding an item into the list of items in a package
* Strategy - responsible for define the strategy in how to organize and which items should be placed in the Package 
* Chain of Responsibility - it's being used to implement/coordinate the validation for Packages and its respective items when inserting/creating those to produce the result of the algorithm

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
mvn clean test
```

### Output after running the tests

```
Results :

Tests run: 28, Failures: 0, Errors: 0, Skipped: 0
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Mockito](https://site.mockito.org/) - Used to help mock objects
* [Lombok](https://projectlombok.org/) - Used to prevent boilerplate in the project
* [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) - Provides some easy and nice validations using a clean API


## Authors

* **Douglas Agliardi** - *Initial work*