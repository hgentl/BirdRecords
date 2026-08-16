import java.time.LocalDate;

/**
 * Demonstrates the main functionality of the Bird Records project.
 *
 * This class was added for portfolio purposes to demonstrate how the
 * Bird and BirdSightings classes can be used outside of the BlueJ
 * development environment.
 */
public class Main
{
    public static void main(String[] args)
    {
        System.out.println("--- Bird Records Demonstration ---\n");

        // Create the bird sightings collection.
        BirdSightings sightings = new BirdSightings();

        // Create birds representing species observed at different locations.
        Bird robin = new Bird("Robin", "Richmond Park");
        Bird robinWimbledon = new Bird("Robin", "Wimbledon Common");
        Bird heron = new Bird("Grey Heron", "Richmond Park");
        Bird kingfisher = new Bird("Kingfisher", "London Wetland Centre");

        // Add the birds to the collection.
        sightings.addEntry(robin);
        sightings.addEntry(robinWimbledon);
        sightings.addEntry(heron);
        sightings.addEntry(kingfisher);

        // Record sightings for each bird.
        sightings.updateData(robin, LocalDate.of(2026, 8, 10));
        sightings.updateData(robin, LocalDate.of(2026, 8, 12));

        sightings.updateData(
            robinWimbledon,
            LocalDate.of(2026, 8, 11)
        );

        sightings.updateData(
            heron,
            LocalDate.of(2026, 8, 9)
        );

        sightings.updateData(
            kingfisher,
            LocalDate.of(2026, 8, 13)
        );

        sightings.updateData(
            kingfisher,
            LocalDate.of(2026, 8, 14)
        );

        // Display the recorded sightings.
        System.out.println("--- Recorded Sightings ---\n");
        sightings.displayMapContent();

        // Display total sightings by species.
        System.out.println("\n--- Total Sightings by Species ---\n");

        System.out.println(sightings.totalSightings());

        // Export the collection to a CSV file.
        System.out.println("\n--- Exporting Records ---\n");

        try
        {
            sightings.createCSV("bird-sightings.csv");
            System.out.println(
                "Bird sightings exported to bird-sightings.csv"
            );
        }
        catch (Exception e)
        {
            System.out.println(
                "Unable to export bird sightings: " + e.getMessage()
            );
        }

        System.out.println("\n--- Demonstration Complete ---");
    }
}