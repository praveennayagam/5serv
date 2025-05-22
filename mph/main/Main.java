package mph.main;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import mph.mongoconnect.MongoDBHandler;
import mph.parser.FixedWidthParser;

public class Main {
    private static final String MODEL_PACKAGE="mph.pojo";
    public static void main(String[] args) throws IOException{
        String bankDirPath = "InputDirectory";
        File bankDir = new File(bankDirPath);
        Map<String, List<File>> dateToFileMap = new TreeMap<>();

        for (File file : bankDir.listFiles((dir, name) -> name.endsWith(".txt"))) {
            String header = Files.lines(file.toPath()).findFirst().orElse(null);
            if (header != null && header.startsWith("HDR|")) {
                String[] parts = header.split("\\|");
                //System.out.println(parts[0]+" "+parts[1]+""+parts[2]);
                if (parts.length >= 2) {
                    String dateStr = parts[1];
                    dateToFileMap.computeIfAbsent(dateStr, k -> new ArrayList<>()).add(file);
                }
            }
        }

        for(Map.Entry<String,List<File>> entry : dateToFileMap.entrySet()){
            String dateStr = entry.getKey();
            List<File> files = entry.getValue();
            LocalDate date = LocalDate.parse(dateStr,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String folderName=date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path targetDir = Paths.get("Control", folderName);
            Files.createDirectories(targetDir);

            for(File file:files){
                Path targetFile = targetDir.resolve(file.getName());
                Files.copy(file.toPath(),targetFile,StandardCopyOption.REPLACE_EXISTING);
            }

            for(File file:files){
                System.out.println("\nProcessing file: "+file.getName());
                List<String> lines = Files.readAllLines(file.toPath());
                List<String> dataLines = lines.subList(1,lines.size()-1);
                String fileName = lines.get(0).split("\\|")[2];
                String baseName = fileName.replaceAll("\\d+$","");
                String className = MODEL_PACKAGE+"."+baseName;

                try {
                    Class<?> modelClass = Class.forName(className);
                    List<Object> details = new ArrayList<>();

                    for(String line: dataLines){
                        try{
                            Object parsed = FixedWidthParser.parseLine(line,modelClass);
                            details.add(parsed);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
//                    for(Object detail:details){
//                        System.out.println(detail);
//                    }
                    //System.out.println(details);

                    System.out.println("Parsed "+ details.size()+" records:");
                    MongoDBHandler.insertRecords(baseName,details);
                } catch (ClassNotFoundException e) {
                    System.out.println("No model class found for: "+ className);
                }
            }
        }
    }
}