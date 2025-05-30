package main;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import mongoconnect.MongoDBHandler;
import parser.FixedWidthParser;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String MODEL_PACKAGE = "pojo";

    public static void main(String[] args) throws IOException {
        String bankDirPath = "InputDirectory";
        File bankDir = new File(bankDirPath);
        Map<String, List<File>> dateToFileMap = new TreeMap<>();

        for (File file : bankDir.listFiles((dir, name) -> name.endsWith(".txt"))) {
            String header = Files.lines(file.toPath()).findFirst().orElse(null);
            if (header != null && header.startsWith("HDR|")) {
                String[] parts = header.split("\\|");
                if (parts.length >= 2) {
                    String dateStr = parts[1];
                    dateToFileMap.computeIfAbsent(dateStr, k -> new ArrayList<>()).add(file);
                }
            }
        }

        for (Map.Entry<String, List<File>> entry : dateToFileMap.entrySet()) {
            String dateStr = entry.getKey();
            List<File> files = entry.getValue();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String folderName = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path targetDir = Paths.get("Control", folderName);
            Files.createDirectories(targetDir);

            for (File file : files) {
                Path targetFile = targetDir.resolve(file.getName());
                Files.copy(file.toPath(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            }

            for (File file : files) {
                logger.info("Processing file: " + file.getName());
                List<String> lines = Files.readAllLines(file.toPath());
                List<String> dataLines = lines.subList(1, lines.size() - 1);
                String fileName = lines.get(0).split("\\|")[2];
                String baseName = fileName.replaceAll("\\d+$", "");
                String className = MODEL_PACKAGE + "." + baseName;

                try {
                    Class<?> modelClass = Class.forName(className);
                    List<Object> details = new ArrayList<>();

                    for (String line : dataLines) {
                        try {
                            Object parsed = FixedWidthParser.parseLine(line, modelClass);
                            details.add(parsed);
                        } catch (Exception e) {
                            logger.log(Level.SEVERE, "Error parsing line in file: " + file.getName(), e);
                        }
                    }

                    logger.info("Parsed " + details.size() + " records.");
                    MongoDBHandler.insertRecords(baseName, details);
                } catch (ClassNotFoundException e) {
                    logger.warning("No model class found for: " + className);
                }
            }
        }
    }
}
