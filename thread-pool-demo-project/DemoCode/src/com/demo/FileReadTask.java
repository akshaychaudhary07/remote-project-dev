package com.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class FileReadTask implements Runnable {
    private static final AtomicInteger threadCounter = new AtomicInteger(0);
    private final File file;

    public FileReadTask(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        threadCounter.incrementAndGet();
        try {
            System.out.println("Reading file: " + file.getName());
            byte[] content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            System.out.println("Completed reading file: " + file.getName());
            
            // Process the file after reading
            ProcessFileTask processTask = new ProcessFileTask(file, content);
            processTask.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getThreadCount() {
        return threadCounter.get();
    }
}
