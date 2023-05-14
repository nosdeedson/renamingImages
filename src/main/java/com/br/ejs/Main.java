package com.br.ejs;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args[0] == null || args[1] == null){
            throw new RuntimeException("Passe o caminho da pasta com as imagens e o nome da primeira imagem");
        }
        String path = args[0];
        Integer numberName = Integer.valueOf(args[1]);
        if(!path.startsWith("/")){
            path = "/" + path;
        }
        if(!path.endsWith("/")){
            path += "/";
        }
        System.out.println(numberName);
        System.out.println(path);
        renamingFilesRandomNumbersName(path);
        renamingFilesFromFolderVideos(path, numberName);
    }

    private static void renamingFilesRandomNumbersName(String path){
        System.out.println("####################### RENAMING WITH RANDOM NUMBERS ###############################");
//        System.out.printf("------------------- %s%n ----------------------------", path);
        File videos = new File(path);
        File[] files = videos.listFiles(File::isFile);
        Set<Integer> mix = new HashSet<>(1000);
        Random random = new Random();
        if (files != null){
            for (int i = 0; i < files.length + 100; i++) {
                mix.add(Integer.valueOf(random.nextInt(1000) * random.nextInt(1000)));
            }
            List<Integer> mixList = new ArrayList<>(mix);
            for (int i = 0; i < files.length; i++) {
                File newName = new File(path + mixList.get(i));
                try {
                    System.out.println(files[i].renameTo(newName));
//                    System.out.println(newName.getName());
                } catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void renamingFilesFromFolderVideos(String path, Integer numberName) throws InterruptedException {
        System.out.println("##################### RENAMING TO PUT IN IMAGES ###################");
        File videos = new File(path);
        File[] files = videos.listFiles(File::isFile);
        // always change
        long name = numberName;
        files = videos.listFiles(File::isFile);
        if (files != null){
            for (int i = 0; i < files.length; i++){
                File newName = new File(path +  name);
                try {
                    System.out.println(String.valueOf(files[i].renameTo(newName)) + " = " + name);
                    name++;
                }catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
        }
    }
}