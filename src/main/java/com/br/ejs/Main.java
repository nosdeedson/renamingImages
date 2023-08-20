package com.br.ejs;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3) {
            throw new RuntimeException("Passe o caminho da pasta com as imagens, e numero para o primeiro nome de imagem." +
                    " O range para o Random");
        }
        String path = args[0];
        Integer numberName = Integer.valueOf(args[1]);
        Integer rangeOfTheRandom = Integer.valueOf(args[2]);
        if(!path.startsWith("/")){
            path = "/" + path;
        }
        if(!path.endsWith("/")){
            path += "/";
        }
        System.out.println("caminho: " + path);
        System.out.println("number name: " + numberName);
        System.out.println("rangeOfTheRandom: " + rangeOfTheRandom);
        renamingFilesRandomNumbersName(path, rangeOfTheRandom);
        Thread.sleep(5000);
        renamingFilesFromFolderVideos(path, numberName);
    }

    private static synchronized void renamingFilesRandomNumbersName(String path, Integer rangeOfTheRandom){
        System.out.println("####################### RENAMING WITH RANDOM NUMBERS ###############################");
        File videos = new File(path);
        File[] files = videos.listFiles(File::isFile);
        System.out.println(files.length);
        if(files.length >= rangeOfTheRandom){
            throw new IllegalArgumentException("Range of the Random must be greater than: " + files.length);
        }
        Set<Integer> mix = new HashSet<>(rangeOfTheRandom);
        Random random = new Random();
        if (files != null){
            do {
                mix.add(Integer.valueOf(random.nextInt(rangeOfTheRandom ) + 20000));
            } while (mix.size() <= (files.length + 50));
            System.out.println(mix.size());
            List<Integer> mixList = new ArrayList<>(mix);
            int cont = 0;
            for (int i = 0; i < files.length; i++) {
                File newName = new File(path + mixList.get(i));
                try {
                    System.out.println("i: " + i + " " + files[i].renameTo(newName));
//                    System.out.println(newName.getName());
                } catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static void renamingFilesFromFolderVideos(String path, Integer numberName)  {
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