

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
    
    @Test
    public void objectCreationTests()
    {
        boolean pass;
        
        // False positive object creation tests
        try {
            Bird testNullValueSpecies = new Bird(null, "test", true);
            Bird testNullValueLocation = new Bird("test", null, true);
            
            Bird testBlankValueSpecies = new Bird(" ", "test", true);
            Bird testBlankValueLocation = new Bird("test", " ", true);
            
            System.out.println("Failed to prevent the creation of invalid Bird objects");
            pass = false;
            
        } catch (Exception e4) {
            pass = true;
        }
        
        assert pass == true;
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
    
}