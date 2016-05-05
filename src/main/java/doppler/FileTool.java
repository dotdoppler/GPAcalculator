package doppler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by doppler on 2016/5/1.
 */
public class FileTool {
   private static String line = "";
    public static  void writeHTML(String fileDir, String fileName, BufferedReader reader) throws IOException {
        File dir = new File(fileDir);
        if (!dir.exists())
            dir.mkdir();
        File file = new File(dir,fileName);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        while ((line = reader.readLine()) != null)
            writer.write(line + "\r\n");

        writer.close();
    }


}
