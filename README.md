# Flight

## Architecture
- The app leverages the MVVM architecture to facilitate separation of concerns.
- There are several layers in the app: Data Access, Presentation, Use Cases.
- The view model is responsible for exposing data the relevant views through LiveData.
- The view model also interacts with Use Cases to handle domain logic such as retrieving data from a source or transforming data sets so that they are ready for presentation. 
- Use cases represent all the actions within the app i.e. retrieving data from Cloud Storage as a result of a button click
- Data access is abstracted away by repository classes. Data retrieved by a repository class can come from sources such as a database or the API.  

### Domain/Data Layer
- The json structure received from the API closely resembles the data that are displayed to the UI.
- Due to this, there is no data model mapper created for the data layer.

### Dependency Injection
- The app utilises dagger for dependency injection. 
- This allows for cleaner code and delegates the gluing of dependencies to the library.

### Testing
- The MVVM architecture allows testing to be done on each layer independently of one another.
- The app contains a unit test for LoadFlightUseCase and a UI test for the flight detail screen.

## Assumptions
- The flight json structure doesn't contain all the necessary data to populate the UI in the flight detail screen, so a placeholder value is used instead.
- Similarly, the QR image is generated using the flight number.
- All flights are assumed to be non-stop flights.
- Dates are stored as UTC.
- Flights are sorted in descending order of their departure date.

## Approach
The app was built with testability and maintainability in mind. Several classes implement an interface or abstract class to enable mocking. 
Additionally, dependency injection is used early on to reduce coupling between different components in the app.  


## Steps to build
- cd to the root directory of the app and run ```./gradlew build.``` 
- All the necessary code should be generated at this point. Run ```./gradlew assemble``` to produce the APK.
- Deploy the build to a device or an emulator through Android Studio or ADB.
