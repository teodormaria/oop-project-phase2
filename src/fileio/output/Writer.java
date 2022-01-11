package fileio.output;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class used to write the output files
 */
public final class Writer {
    /**
     * File to be written in
     */
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * writes given JSONObject into file and closes it
     * @param object
     */
    public void writeAndCloseJSON(final JSONObject object) {
        try {
            file.write(object.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
