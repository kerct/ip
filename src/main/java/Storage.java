import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scanner getReader() throws FileNotFoundException {
        return new Scanner(file);
    }

    public void appendToFile(String input) throws IOException {
        // creates a FileWriter in append mode
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(input + System.lineSeparator());
        fw.close();
    }
}