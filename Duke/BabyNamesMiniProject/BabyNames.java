import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
/**
 * Trieda {@code BabyNames} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class BabyNames {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames += 1;
            }
            else {
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total girls = " + totalGirls);
        System.out.println("Total number of names = " + totalNames);
        System.out.println("Total number of boy names = " + totalBoyNames);
        System.out.println("Total number of girl names = " + totalGirlNames);
    }
    
    public void testTotaBirths() {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        String filePath = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(filePath);
        int count = 0;
        int rank = -1;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count += 1;
                if (rec.get(0).equals(name)) {
                    rank = count;
                    break;
                }
            }
        }
        
        return rank;
    }
    
    public void testGetRank() {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year, name, gender);
        
        if (rank != -1) {
            System.out.println("Name " + name + " of gender " + gender +
                                    " gets in year " + year + " rank: " + rank);          
        }
        else {
            System.out.println("Name " + name + " of gender " + gender + 
                                    " does not appear in file.");
        }
    }
    
    public String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        String filePath = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(filePath);
        int count = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count += 1;
                if (count == rank) {
                    name = rec.get(0);
                }
            }
        }    
        return name;
    }
    
    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        
        System.out.println(getName(year, rank, gender));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);    
        
        System.out.println(name + " born in " + year + " would be "
                            + newName + " if he/she was born in " + newYear);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int rank = 0;
                
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int count = 0;
            int currentRank = -1;
            
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    count += 1;
                    if (rec.get(0).equals(name)) {
                        currentRank = count;
                        break;
                    }
                }
            }
            
            if (currentRank != -1) {
                if (rank == 0) {
                    rank = currentRank;
                    year = Integer.parseInt(f.getName().substring(3,7));
                }
                else {
                    if (currentRank < rank) {
                        rank = currentRank;
                        year = Integer.parseInt(f.getName().substring(3,7));
                    }
                }
            }
        }
        
        return year;
    }
    
    public double getAverageRank(String name, String gender) {
        double averageRank = -1.0;
        int totalRank = 0;
        int totalCount = 0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int count = 0;
            int rank = 0;
            
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    count += 1;
                    if (rec.get(0).equals(name)) {
                        rank = count;
                    }
                }  
            }
            
            if (rank != 0) {
                totalRank += rank;
                totalCount += 1;
            }            
        }
        
        if (totalRank != 0) {
            averageRank = (double) totalRank / totalCount;
        }
              
        return averageRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirthsRankedHigher = 0;
        String filePath = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(filePath);
        int totalBirths = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    totalBirthsRankedHigher = totalBirths;
                }
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        
        return totalBirthsRankedHigher;
    }
}
