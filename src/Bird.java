/**
 * Write a description of class Obj here.
 *
 */
public class Bird
{
    
    private String species;
    private String location;
    private boolean endangered;
    
    /**
     * Constructor for objects of class Obj
     * 
     * @param species represents the name of the species of bird
     * @param location represents the location the bird was seen
     * @param endangered represes if the bird is classes as an endangerd species
     * @throws IllegalArgumentException is species or location are empty or null
     */
    public Bird(String species, 
                String location, 
                boolean endangered)
    {
        
        // format species & location
        species = species.trim().toLowerCase();
        location = location.trim().toLowerCase();
        
        
        if (species.equals(null) || location.equals(null) ||
            species.isEmpty() || location.isEmpty()) {
            throw new IllegalArgumentException("species or location data cannot be empty or null");
        } 
        
        this.species = species;
        this.location = location;
        
        this.endangered = endangered;
    

    }

    /**
     * @ return species
     */
    public String getSpecies() {
        return species;
    }
    
    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * @return endangered
     */
    public boolean getEndangered() {
        return endangered;
    }
    
    /**
     * toSting method
     * 
     * @return a String representation of this object
     */
    public String toString() {
        String status;
        
        if (getEndangered()) {
            status = "true";
        } else {
            status = "false";
        }
        
        return species + ", " + location + ", " + status;
    }
        
    /**
     * Equals method
     * 
     * @param, obj: represents the object to be compared to
     * @return ture if the compaered object is an instance of Bird
     */
    @Override
    public boolean equals(Object obj) {
        
        if (!(obj instanceof Bird)) {
            return false;
        }
        
        Bird b = (Bird) obj;
        
        return getSpecies() == b.getSpecies() && 
                getLocation() == b.getLocation();
        
        }
    
    /**
     * Compares another Birds Species value with this Birds specues value
     * 
     * @param bird, Represents the Bird object to be compared with 
     * @return 0 if equal, > 0 if bigger or < 0 if lower
     */
    public int compareTo(Bird bird) {
        
        return getSpecies().compareTo(bird.getSpecies());
    }
    
    /**
     * Hash method
     * 
     * @return a hash code representing this object
     */
    @Override
    public int hashCode() {
        int startingValue = 3;
        
        int hash = startingValue * species.hashCode() * location.hashCode();
        
        return hash;
    }
}