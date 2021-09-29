import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Trieda {@code Part1} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        
        for (CSVRecord record : parser) {
            if ((Double.parseDouble(record.get("TemperatureF"))) != -9999) {
                if (coldestSoFar == null) {
                    coldestSoFar = record;
                }
                else {
                    double curent = Double.parseDouble(record.get("TemperatureF"));
                    double coldest = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            
                    if (curent < coldest) {
                        coldestSoFar = record;
                    }
                }
            }
        }
        
        return coldestSoFar;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.print("Coldest tempreture is: ");
        System.out.print(coldestHour.get("TemperatureF"));
        System.out.print(" at ");
        System.out.println(coldestHour.get(0));
    }
    
    public File fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = current;
                coldestFile = f;
            }
            else {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if (currentTemp < coldestTemp) {
                    coldestSoFar = current;
                    coldestFile = f;
                }
            }
        }
        
        return coldestFile;
    }
    
    public void testFileWithColdestTemperature() {
        File coldest = fileWithColdestTemperature();
        System.out.print("Coldest day was in file: ");
        System.out.println(coldest.getName());
        
        String path = coldest.getAbsolutePath();
        FileResource fr = new FileResource(path);                
        
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.print("Coldest temperature on that day was ");
        System.out.println(coldestTemp.get("TemperatureF"));
        
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser parser2 = fr.getCSVParser();
        for (CSVRecord r : parser2) {
            System.out.print(r.get("DateUTC"));
            System.out.print(": ");
            System.out.println(r.get("TemperatureF"));
        }
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        
        for (CSVRecord record : parser) {
            if ((record.get("Humidity")).equals("N/A")) {
            }
            else {
                if (lowestSoFar == null) {
                    lowestSoFar = record;
                }
                else {
                    double currentHumidity = Double.parseDouble(record.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                    
                    if (currentHumidity < lowestHumidity) {
                        lowestSoFar = record;                    
                    }
                }                
            }
        }
    
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.print("Lowest Humidity was ");
        System.out.print(csv.get("Humidity"));
        System.out.print(" at ");
        System.out.println(csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            CSVRecord currentLowestHumidity = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumiditySoFar == null) {
                lowestHumiditySoFar = currentLowestHumidity;
            }
            else {
                double currentHumidity = Double.parseDouble(currentLowestHumidity.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                
                if (currentHumidity < lowestHumidity) {
                    lowestHumiditySoFar = currentLowestHumidity;
                }
            }
        }
        
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        
        System.out.print("Lowest Humidity was ");
        System.out.print(lowest.get("Humidity"));
        System.out.print(" at ");
        System.out.println(lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0.0;
        double sumOfTemps = 0.0;
        int countOfRecords = 0;
        
        for (CSVRecord record : parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            
            if (currentTemp != -9999) {
                sumOfTemps += currentTemp;
                countOfRecords += 1;
            }
            
        }
        
        averageTemp = sumOfTemps / countOfRecords;
        
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.print("Average temperature in file is ");
        System.out.println(averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double averageTemp = 0.0;
        double sumOfTemps = 0.0;
        int countOfRecords = 0;
        
        for (CSVRecord record : parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                        
            if ((currentTemp != -9999) && (record.get("Humidity") != "N/A")) {
                int currentHumidity = Integer.parseInt(record.get("Humidity"));
                
                if (currentHumidity >= value) {
                    sumOfTemps += currentTemp;
                    countOfRecords += 1;
                }
            }
        }
        
        averageTemp = sumOfTemps / countOfRecords;
        
        return averageTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.print("Average tempature when high humidity is ");
        System.out.println(averageTemperatureWithHighHumidityInFile(parser, 80));
    }
}
