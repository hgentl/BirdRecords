import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BirdSightingsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
        example = new Bird("puffin", "island", false);
        falsePositive = new Bird("testing", "testing", true);
        testNum = 1;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        collection.populate();
    }
    
    @Test
    public void testAddEntry() {
     
        try {
            collection.addEntry(null);
        } catch (Exception e) {
            assertEquals("null value passed", e.getMessage());
        }
        
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
        
        assert collection.updateData(example, 2000, 1, 1);
        
        try {   
            collection.updateData(example, 1, 90, 70);
        } catch (Exception e) {
            assertEquals("Error prashing dateText '1/90/70' could not be parsed at index 0", e.getMessage());
        }
        
        try {
            collection.updateData(falsePositive, 2000, 1, 1);
        } catch (Exception e) {
            assertEquals("The bird is not present in the dataset", e.getMessage());
        }
    }
    
    @Test
    public void testKeyData(){
        // todo
    }
    
    @Test
    public void testDisplayMapContent() {
        assertEquals(collection.displayMapContent(), "puffin, Seen at: island on: 2026-04-20, 2026-11-21\nswallow, Seen at: grassland on: 2026-03-01, 2026-12-20\nswallow, Seen at: open pasture on: 2026-10-20, 2026-03-21\n");
        
        collection.clear();
        assertEquals(collection.displayMapContent(), "The dataset is currently empty");

    }
    
    @Test
    public void testTotalsightings() {
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