package com.demo;

import java.io.File;

public class ProcessFileTask {
    private final File file;
    private final byte[] content;

    public ProcessFileTask(File file, byte[] content) {
        this.file = file;
        this.content = content;
    }

    public void process() {
        // Add your file processing logic here
        System.out.println("Processing file: " + file.getName());
        // Example: print the size of the file content
        System.out.println("File content size: " + content.length + " bytes");
    }
}
