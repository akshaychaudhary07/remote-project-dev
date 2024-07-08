package com.demo;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\aryan\\Desktop\\29-05-2024 Job4Jobless\\finalfrontend\\src\\app\\about");
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files to read in the folder.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(1);
        long startTime = System.currentTimeMillis();

        for (File file : files) {
            if (file.isFile()) {
                FileReadTask task = new FileReadTask(file);
                executor.submit(task);
            }
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to read all files: " + (endTime - startTime) + " milliseconds");
        System.out.println("Total number of threads used: " + FileReadTask.getThreadCount());
    }
}
