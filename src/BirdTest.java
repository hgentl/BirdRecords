

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BirdTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BirdTest
{
    private Bird bird;
    private Bird testObjPositive;
    private Bird testObjFalsePositive;
    
    /**
     * Default constructor for test class BirdTest
     */
    public BirdTest()
    {
        bird = new Bird("test", "test location", true);
        testObjPositive = new Bird("test", "test location", true);
        testObjFalsePositive = new Bird("species", "location", false);
    }
    
    /**
     * Tests invalid Bird object creations.
     * 
     * All Bird objects species and location values should empty or equal to null.
     * All Bird object String values must contain lowercase letters in order to 
     * prevent acsidental duplication.
     */
    @Test
    public void objectCreationTests()
    {
        
        // False positive object creation tests with null values for species and location
        try {
            Bird testNullValueSpecies = new Bird(null, "test", true);
            Bird testNullValueLocation = new Bird("test", null, true);
            
        } catch (Exception e) {
            assertEquals("Invalid paramiter passed, species or location cannot be null", e.getMessage());
        }
        
        // trys to pass empty string values for species and location
        try {
            Bird testBlankValueSpecies = new Bird(" ", "test", true);
            Bird testBlankValueLocation = new Bird("test", " ", true);
        } catch (Exception e) {
            assertEquals("Invalid paramiter passed, species or location data cannot be empty", e.getMessage());
        }
        
        // test the standardisation of the Sting vaules for species & location
        Bird testStandarisation = new Bird ("Test", "teSt", true);
        assert testStandarisation.getSpecies().equals("test");
        assert testStandarisation.getLocation().equals("test");
    }
    
    @Test
    public void testHashCode() {
        assert bird.hashCode() == testObjPositive.hashCode();
        assert bird.hashCode() != testObjFalsePositive.hashCode();
    }
    
    @Test
    public void testObjectEquality() {
        assert bird.equals(testObjPositive);
        assert bird.equals(testObjFalsePositive) == false;
    }
    
    @Test
    public void testCompareTo() {
        // compare an equal object
        assertEquals(bird.compareTo(testObjPositive), 0);
        
        // compare a "greater than" object
        Bird greaterThan = new Bird("aaa", "aaa", false);
        assertEquals(bird.compareTo(greaterThan), 19);
        
        // compare a "less than" object
        Bird lessThan = new Bird("zzz", "zzz", true);
        assertEquals(bird.compareTo(lessThan), -6);
        
    }
}