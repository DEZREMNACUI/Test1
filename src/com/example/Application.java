package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        String pageUrl = "http://10.122.7.154/javaweb/data/images-url.txt";
        String directory = "C:\\images";
        PageReader pageReader = new PageReader();
        List<String> imageUrls = pageReader.getImageUrls(pageUrl);
        List<ImageReader> imageReaders = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            ImageReader imageReader = new ImageReader();
            imageReader.download(imageUrl, directory);
            imageReaders.add(imageReader);
        }

        Collections.sort(imageReaders, (ir1, ir2) -> Integer.compare(ir2.getSize(), ir1.getSize()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\images\\images-sort.txt"))) {
            for (ImageReader imageReader : imageReaders) {
                writer.write(imageReader.getSize() + " " + imageReader.getUrl() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
