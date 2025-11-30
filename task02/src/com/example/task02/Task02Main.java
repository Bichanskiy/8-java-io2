package com.example.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));


    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        // Path adaptedPath = Paths.get("/Users/admin/IdeaProjects/8-java-io2/" + rootDir);
        List<Path> result = new ArrayList<Path>();

        if (Files.isRegularFile(rootDir)){
            result.add(rootDir);
            return result;
        }

        try (Stream<Path> stream = Files.list(rootDir)){
            stream.forEach(item ->{
                if (Files.isDirectory(item)) {
                    try {
                        result.addAll(listFiles(item));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    result.add(item);
                }
            });

            }
        return result;
        }
}

