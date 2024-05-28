import java.io.File;
import java.time.LocalDateTime;

// Here will found the code that will aim to create our file that will host all the calculations
public class FileProvider {

    private static final String FILE_NAME = "mortgage-report";
    private static final String FILE_EXTENSION = ".csv";

    // Code to establish the name of each file in order to have no duplicates
    public static File getFile(){
        String rootPath = System.getProperty("user.dir");
        return new File(rootPath + "/" + FILE_NAME + "-" + LocalDateTime.now().toString().replace(":", "") + FILE_EXTENSION);
    }
}