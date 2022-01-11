package main;

import checker.Checker;
import common.Constants;
import fileio.input.Input;
import fileio.input.InputLoader;
import fileio.output.OutputGenerator;
import fileio.output.Writer;
import santa.Santa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File outputDirectory = new File(Constants.RESULT_PATH);
        File[] previousOutput = outputDirectory.listFiles();
        if (previousOutput != null) {
            for (File file : previousOutput) {
                if (!file.delete()) {
                    System.out.println("Failed to delete!");
                }
            }
        }
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String testNumber = file.getName().replaceAll("[A-Za-z]", "");
            testNumber = testNumber.replaceAll("\\.", "");
            String filepath = Constants.OUTPUT_PATH + testNumber + Constants.FILE_EXTENSION;
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                InputLoader inputLoader = new InputLoader(file.getAbsolutePath());
                Input input = inputLoader.readData();
                Santa santa = new Santa(input);
                OutputGenerator outputGenerator = OutputGenerator.getInstance();
                outputGenerator.computeAllYears(santa);
                Writer writer = new Writer(filepath);
                writer.writeAndCloseJSON(outputGenerator.getFinalOutput());
            }
        }
        Checker.calculateScore();
    }
}
