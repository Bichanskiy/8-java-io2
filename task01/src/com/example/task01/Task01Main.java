package com.example.task01;

import java.io.*;
import java.util.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("src/main/resources/3724.mp3")));

    }
    // Адаптируем путь к файлу, чтобы тесты проходили и команда срабатывала
     // ProcessBuilder строит по сути "запрос" в терминал, в него указываем наши команды
     // Process отправляет эти команды
     // Дальше преобразуем, чтобы работать с полученными данными


    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String ffprobePath = "/opt/homebrew/bin/ffprobe";
        String absolutePath = "/Users/admin/IdeaProjects/8-java-io2/task01/" + file.toString();
        ProcessBuilder process = new ProcessBuilder(ffprobePath, "-v", "error", "-of", "flat", "-show_format", absolutePath);
        Process pr = process.start();
        List<String> output = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()))){
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null){
                output.add(line);
            }
        }


        pr.waitFor();

        StringBuilder name = new StringBuilder();
        for (int i = 0; i < output.size(); i++){
            if (output.get(i).startsWith("format.tags.title=")){
                String temp = output.get(i);

                char[] chars = temp.toCharArray();

                for (i = 19; i < chars.length - 1; i++){
                    name.append(chars[i]);
                }
            }

        }
        return name.toString();
    }
}
