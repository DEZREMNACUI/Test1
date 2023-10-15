package com.example;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageReader {

    private int size;
    private String url;

    public void download(String imageUrl, String directory) {
        String savePath = this.buildSavePath(imageUrl, directory);
        try (BufferedInputStream in = new BufferedInputStream(new URL(imageUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            Path path = Paths.get(savePath);
            this.setSize((int) Files.size(path));
            this.setUrl(imageUrl); // 设置url的值
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: download image from url and save to directory;
        // down and save to savePath;
    }


    private String buildSavePath(String imageUrl, String directory) {
        String path = imageUrl.replace("http://", "")
                .replace("/", File.separator); // 使用系统文件分隔符
        return directory + File.separator + path;
    }


    public int getSize() {
        return size;
    }
    // TODO: Implement getting image size logic
    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
