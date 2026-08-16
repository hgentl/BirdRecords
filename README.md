## Project Outline
This project was developed as part of my studies towards the Object-Oriented Java Programming module at The Open University. It models a record-keeping system for a bird-watching society, allowing members to record bird species, the locations where they were sighted, and the dates of those sightings.

### Assigment Context
The assignment required me to develop a small application for a real-world organisation to manage a collection of objects that were important to it. I chose a bird-watching society that needed to keep track of bird species observed at different locations.

The project also introduced defensive programming, error handling, unit testing, and persistent storage through CSV files.

---

## Features
* Records bird species and the locations where they were sighted.
* Stores multiple sighting dates for each bird/location combination.
* Validates bird and sighting data before adding it to the collection.
* Supports adding, retrieving, updating, and removing records.
* Calculates total sightings for each species across different locations.
* Displays the recorded sightings in a readable format.
* Exports the collection to a CSV file for persistent storage.
* Includes JUnit tests covering normal and invalid use cases.

---

## Design Highlights
The project utilises defencive programing practeses and error handling to ensure a robust and CRUD system which can handle errors. The system encorprates 

### Data Modeling
The collection is represented using a `Map<Bird, ArrayList<LocalDate>>`, with each Bird object acting as a key and its associated value containing the dates on which it was sighted.

This structure allows multiple locations to be represented for the same species while keeping each location's individual sighting dates separate.

### Defensive Programming
Input is validated before it is added to the system. Bird objects reject null and empty species or location values, while the collection validates that referenced birds exist before updating their sighting data.

Species and location names are also normalised to lowercase when a Bird object is created, helping to prevent accidental duplication caused by differences in capitalisation.

### Error Handling
Invalid operations result in `IllegalArgumentException`, while errors encountered during CSV creation are handled using `IOException`. Date input is validated using LocalDate, with invalid dates converted into meaningful exceptions for the caller.

### Persistence
The `BirdSightings` class can export the current collection to a CSV file, allowing the recorded data to persist beyond the lifetime of the application.

The export method validates the requested file extension and handles I/O errors rather than allowing them to fail silently.

### Object Equality and Hashing
`Bird` overrides `equals()` and `hashCode()` so that birds with the same species and location are treated as equal. This is particularly important because Bird objects are used as keys within the HashMap that stores the collection.

The implementation allows the collection to identify an existing bird/location combination reliably rather than relying on object identity.

### Automated Testing
The project includes JUnit tests for both `Bird` and `BirdSightings`. The tests cover normal behaviour as well as invalid inputs and edge cases, including null values, empty data, invalid dates, duplicate entries, missing records, and invalid file extensions.

---

## Demonstration

Todo, (after Main.java has been written)

**Note**: This project was originally developed using BlueJ as part of a university module focused on object-oriented design rather than building complete Java applications. To make the project easier to explore outside the university environment, I have added a small Main.java class that demonstrates the core functionality. The demonstration is intended to showcase the design of the classes rather than every aspect of the original assignment.

---

## Reflections
This project changed how I approached error handling. Rather than assuming that users would always provide valid data, I began thinking about what could go wrong at each stage of the program and how those situations should be handled.

Writing the JUnit tests reinforced this approach. Testing invalid inputs and edge cases made me realise that reliable software requires considering failure cases as part of the design, rather than adding error handling after the main functionality has been written.

### Future Improvments
If I were assinged a similar task in a proffetional evnironment, I would imporve and develop additional handling of application logic, ensuring seperation between the collection layer, user actions and application logic. 

