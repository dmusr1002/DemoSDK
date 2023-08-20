Include details about your SDK design in this file. 

## Architecture 
This project follows the principles of Clean Architecture, a software architectural pattern that emphasizes separation of concerns, maintainability, and testability. Clean Architecture provides a structured and modular way to design and develop software systems, ensuring that business logic is decoupled from implementation details and external dependencies.

The project can be seen as having 2 major components, the domain and data layers.
Obviously, Clean Architecture can have more distinct layers - specially when dealing with visual applications - but in this scenario these two were enough.

### Data
The Data layer, also known as the "Data Source" layer, is responsible for handling data access and storage. 
It includes components that interact with databases, external services, APIs, and other sources of data.
In this scenario, it contains the necessary mapping to conform with all the API responses; as well as the definition of the filtering components.

### Domain
The Domain layer, often referred to as the "Core" or "Business Logic" layer, represents the heart of the application.
It contains the business rules, entities, and use cases that are specific to the application's domain.
This is where the logic behind our API calls live.
