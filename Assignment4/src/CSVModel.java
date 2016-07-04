import java.io.*;
import java.util.*;
/**
 * Jeff Morin
 * Assignment2
 * 6/10/16
 *
 * Creates a tabular model for an input CSV file.
 * It makes available an ArrayList of Object arrays representing
 * rows and columns of the data. The types of data are not assumed;
 * thus initial object types.-
 */

public class CSVModel
{
    private int columns;
    private List<Object[]> csvContents;

    public CSVModel(File inputFile) {
        csvContents = new ArrayList<>();
        readFile(inputFile);
    }

    public CSVModel(File inputFile, int columns) {
        this.columns = columns;
        csvContents = new ArrayList<>();
        readFile(inputFile);
    }

    protected void readFile(File file) {
        try {
            FileInputStream fileStream = new FileInputStream(file);
            BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(fileStream));
            String line;
            while((line = fileReader.readLine()) != null) {
                csvContents.add((columns != 0 ?
                        line.split(",", columns) : line.split(",")));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage()+
                    "\nFile may be corrupt.");
        }
        clean();
    }

    /* Removes unnecessary grammar */
    private void clean() {
        for(Object[] content : csvContents) {
            for(int i = 0 ; i < content.length ; i++) {
                content[i] = content[i].toString()
                        .replaceAll("[\", \\s, \\$]", "");
            }
        }
    }

    /* returns the file's contents. */
    public List<Object[]> getModel() {
        return csvContents;
    }
}