import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Trieda {@code Export} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Export {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "fish", "nuts");
        //System.out.println(numberOfExporters(parser, "sugar"));
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                return (record.get("Country") + " : " + record.get("Exports") + " : " + record.get("Value (dollars)"));
            }
        }
    
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if (exports.contains(exportItem)) {
                count += 1;
            }
        }
        
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.print(record.get("Country"));
                System.out.print(" ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
}
