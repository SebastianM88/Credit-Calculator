import java.io.FileWriter;
import java.io.IOException;

// This is a page where it is going to be declared and transposed all our calculations into the report sheet
public class CsvWriter {

    private FileWriter fileWriter;

    public CsvWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    // Header declaration
    public void writeHeader() throws IOException {
        fileWriter.append("Month");
        fileWriter.append(",");
        fileWriter.append("Mortgage");
        fileWriter.append(",");
        fileWriter.append("Balance");
        fileWriter.append(",");
        fileWriter.append("Interest");
        fileWriter.append(",");
        fileWriter.append("Paid Amount");
        fileWriter.append("\n");
    }

    // The content of each title and the place where the calculated values will be displayed
    public void writeRecord(int month, double mortgage, double balance, double interest, double paidAmount) throws IOException {
        fileWriter.append(String.valueOf(month));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(mortgage));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(balance));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(interest));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(paidAmount));
        fileWriter.append("\n");
    }


    public void closeFile() throws IOException {
        fileWriter.close();
    }
}