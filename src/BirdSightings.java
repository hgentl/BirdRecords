import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * holds a set of birds and the dates they were sigted. 
 * 
 * Contans CRUD operations along with some display options and a method to
 * create Csv file enabling persistant storage.
 *
 */
public class BirdSightings
{
    // represents a map of brids to a list of dates they were sigted 
    private Map<Bird, ArrayList<LocalDate>> dataset;
 
    /**
     * Constructor for objects of class Collection
     */
    public BirdSightings()
    {
        dataset = new HashMap<Bird, ArrayList<LocalDate>>();
    }

    /**
     * 
     * Adds an bird object to the map
     * 
     * @param bird represents a Bird object
     * @return ture if the Bird is added to the map
     * @throws IllegalArgumentException if the paramiter has a null value
     */
    public boolean addEntry(Bird bird) {
        
        if (bird == null) {
            throw new IllegalArgumentException("null value passed");
        }
        
        var value = dataset.putIfAbsent(bird, new ArrayList<LocalDate>());
        
        if (value == null) {
            return true;
        } else {
            throw new IllegalArgumentException(bird + " cound not be added to the dataset");
        }
            
    }
    
    /**
     * 
     * Clears all data from the map
     */
    public void clear() {
        dataset.clear();
    }
    
    /**
     * 
     * Populates the map with 3 examples sutible for testing
     */
    public void populate() {
        dataset.clear();
        
        LocalDate dateA = LocalDate.of(2026, 04, 20);
        LocalDate dateB = LocalDate.of(2026, 04, 21);
        
        Bird exampleA = new Bird("Puffin", "Island", false);
        Bird exampleB = new Bird("Swallow", "Open pasture", true);
        Bird exampleC = new Bird("Swallow", "Grassland", true);
        
        addEntry(exampleA);
        addEntry(exampleB);
        addEntry(exampleC);
        
        updateData(exampleA, 2026, 4, 20);
        updateData(exampleA, 2026, 11, 21);
        
        updateData(exampleB, 2026, 10, 20);
        updateData(exampleB, 2026, 3, 21);
        
        updateData(exampleC, 2026, 3, 1);
        updateData(exampleC, 2026, 12, 20);
        
    }
    
    /**
     * Removes a bird key and all its values from the map
     * 
     * @param obj represents the brid object to be removed from the map
     * @return ture if obj is present and has been removed from the map
     * @throws IllegalArgumentException is obj is not in the map or a null type
     */
    public boolean removeEntry(Bird obj) {
        
        if (dataset.containsKey(obj)) {
            dataset.remove(obj);
            return true;
        } else {
            throw new IllegalArgumentException(obj + "Not present in the dataset");
        }

    }
    
    /**
     * 
     * Adds a date value to the a given bird key
     * 
     * @param obj represents a bird object
     * @param year an integer representing the year
     * @param month an integer representing the month
     * @param day an integer representing the day
     * @return true if the sighting value was added to the bird key
     * @throws IllegalArgumentException if an invalid date format is passed 
     * @throws IllegalArgumentException if obj is not in the dataset or a null value
     */
    public boolean updateData(Bird obj, int year, int month, int day) {        
        // compound the date from the params
        String dateStr = String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day);
        // set the format for the date
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/M/d");
        
        try {
            // validate the date
            LocalDate dateSighted = LocalDate.parse(dateStr, format);
            // check for the key
            if (dataset.containsKey(obj)) {
                ArrayList<LocalDate> bird = dataset.get(obj);
                bird.add(dateSighted);
                return true;
            } else {
                throw new IllegalArgumentException("The bird is not present in the dataset");
            }
        
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error prashing date" + e.getMessage());
        } 
    
    }
    
    /**
     * Returns all of the values for a given key
     * 
     * @param obj represents a bird key value
     * @return an ArrayList of all the dates a given bird was seen
     * @throws IllegalArgumentException
     */
    public ArrayList<LocalDate> getKeyData(Bird obj) {
        
        if(dataset.containsKey(obj)) {
            ArrayList<LocalDate> data = dataset.get(obj);
            return data;
        } else {
            throw new IllegalArgumentException("The requested bird is not in the dataset");
        }
        
    }
    
        
    /**
     * Outputs a formatted vesion of the map
     * 
     * @return a formatted string of the map's content
     * 
     */
    public String displayMapContent() {
        if (dataset.isEmpty()) {
            return "The dataset is currently empty";
            }
            
        String returnString = "";
        
        for (Bird key : dataset.keySet()) {
            returnString += key.getSpecies() + ", Seen at: " + key.getLocation() + " on: " + dataset.get(key) + "\n";
            returnString = returnString.replaceAll("\\[|\\]|", "");
        }
 
        return returnString;
    }
    
    /**
     * Returns the total number of sigtings for each species of bird 
     * regardless of location.
     * 
     * @return a HashMap containing each bird and the total number of sightings
     */
    public Map<String, Integer> totalSightings() {
        // holds each bird's name and how often they were seen
        HashMap<String, Integer> totalSightings = new HashMap<>();
        
        String birdName;
        // keeps a list of all the sightings
        ArrayList<LocalDate> totalValues;
        // holds the number of sightings which need to be updated
        int currentKeyTotal;
        
        if (dataset.isEmpty()) { // return null?
            throw new IllegalArgumentException("The dataset is currently empty");
        }
        
        for (Bird key : dataset.keySet()) {
            birdName = key.getSpecies();
            /*
             * If key is not unique then compound the all of is sightings 
             * at each locaton is been seen at.
             */
            if (totalSightings.containsKey(birdName)) {
                // add the current key's sightings to the total
                currentKeyTotal = totalSightings.get(birdName);
                // get the current number of signings 
                totalValues = dataset.get(key);
                currentKeyTotal += totalValues.size();
                
                totalSightings.put(birdName, currentKeyTotal);
                
            } else {
                // if key is unique then populate it's sightings
                totalValues = dataset.get(key);
                totalSightings.put(birdName, totalValues.size());
            }
    
        }
        
        return totalSightings;
    }
    
    /**
     * Writes the data in the map to a csv file for future use.
     * 
     * @param filename represents the target filename for the csv file
     * @return true if the csv file is successfuly created
     * @throws IllegalArgumentException if the filename's extention is not .csv
     * @throws IOException if an IO error is encountered
     */
    public boolean exportToCsv(String filename) throws IOException {
        // represents each line in the file
        String lineWriter;
        // represents the dates of all the bird sightings as a String
        String values;
        
        // ensure filename ends in .csv
        if (!filename.contains(".csv")) {
            throw new IllegalArgumentException("invalid file extention, filename must include .csv");
        } 
        
        try {
            FileWriter output = new FileWriter(filename);
            
            for (Bird key : dataset.keySet()) {
                // get all the vaules for the current key
                values = dataset.get(key).toString();
                //  compund each line and format it
                lineWriter = key.toString() + "," + values.replaceAll("\\[|\\]|\\s", "") +"\n";
                // write to the file
                output.write(lineWriter);
            }
            // close FileWriter after use
            output.close();
        } catch(IOException e) {
            throw new IOException("A problem arose while attempting to create the CSV file");
        }
        
        return true;
    }
    
    }
