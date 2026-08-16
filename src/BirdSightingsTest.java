import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BirdSightingsTest.
 *
 */
public class BirdSightingsTest
{
    private BirdSightings collection;
    private Bird example;
    private Bird falsePositive;
    private int testNum;
    
    /**
     * Default constructor for test class BirdSightingsTest
     */
    public BirdSightingsTest()
    {
        collection = new BirdSightings();
        // used as an example key obj
        example = new Bird("puffin", "island", false);
        // used for false positive tests
        falsePositive = new Bird("testing", "testing", true);
    }

    /**
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        // ensure a new and fresh collection every test
        collection.populate();
    }
    
    
    
    
    /**
     * Tests the addEntry function.
     * 
     * 
     */
    @Test
    public void testAddEntry() {
        // add a new bird to the dataset
        Bird test = new Bird("test", "testing", true);
        assertEquals(collection.addEntry(test), true);
        
        // attempt to add a  null value to the collection
        try {
            collection.addEntry(null);
        } catch (Exception e) {
            assertEquals("null value passed", e.getMessage());
        }
        // attempt to overwrite an existing key value by adding a new, equal key value to the dataset
        try {
            collection.addEntry(example);
        } catch (Exception e) {
            assertEquals(example + " cound not be added to the dataset", e.getMessage());
        }
        
        }
        
    @Test
    public void testRemoveEntry() {

        try {
            collection.removeEntry(example);
        }catch (Exception e) {
            assertEquals(example + "Not present in the dataset", e.getMessage());
        }
    }
    
    @Test
    public void testUpdate() {
        // try to update a birds sighting records
        assert collection.updateData(example, 2000, 1, 1);
        
        // try to insert an invalid date
        try {   
            collection.updateData(example, 1, 90, 70);
        } catch (Exception e) {
            assertEquals("Error prashing dateText '1/90/70' could not be parsed at index 0", e.getMessage());
        }
        // try to update an invalid key
        try {
            collection.updateData(falsePositive, 2000, 1, 1);
        } catch (Exception e) {
            assertEquals("The bird is not present in the dataset", e.getMessage());
        }
    }
    
    @Test
    public void testKeyData(){
        // test an existing entry
        ArrayList exampleList = collection.getKeyData(example);
        // create a new array to compare the example to
        ArrayList test = new ArrayList<LocalDate>();
        // populate the test array
        test.add(LocalDate.of(2026, 04, 20));
        test.add(LocalDate.of(2026, 11, 21));
        
        assertEquals(exampleList, test);
        
        // try to get an invalid key
        try {
            collection.getKeyData(falsePositive);
        } catch (Exception e) {
            assertEquals("The requested bird is not in the dataset", e.getMessage());
        }
    }
    
    @Test
    public void testDisplayMapContent() {
        
        // check the formmating output
        assertEquals(collection.displayMapContent(), "puffin, Seen at: island on: 2026-04-20, 2026-11-21\nswallow, Seen at: grassland on: 2026-03-01, 2026-12-20\nswallow, Seen at: open pasture on: 2026-10-20, 2026-03-21\n");
        
        // test an empty dataset
        collection.clear();
        assertEquals(collection.displayMapContent(), "The dataset is currently empty");

    }
    
    @Test
    public void testTotalsightings() {
        // check for a correct result
        String testStr = collection.totalSightings().toString();
        assertEquals("{swallow=4, puffin=2}", testStr);
        
        // test an empty collection
        try {
            collection.clear();
            collection.totalSightings();
        } catch (Exception e) {
            assertEquals("The dataset is currently empty", e.getMessage());
        }
    
    }
    
    @Test
    public void testCsvCreation() throws IOException {
        // tests file creation
        try{
            assert collection.exportToCsv("test.csv");
        } catch (IOException e) {
            throw new IOException("A problem arose while attempting to create the CSV file");
        }
        
        // try to create an invalid file format
        try {
            collection.exportToCsv("invalidfileExtention.JSON");
        } catch (Exception e) {
            assertEquals("invalid file extention, filename must include .csv", e.getMessage());
        } 
        
        
    }
    
}