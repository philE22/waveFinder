package com.surfwave.waveFinder.global.file;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LocalFileUpload {
    private String savePath = "/Users/phile/Desktop/chartFolder/";

    public void upload(String imageUrl, String fileName) {

        BufferedInputStream inputStream = null;
        try {
            URL url = new URL(imageUrl);
            inputStream = new BufferedInputStream(url.openStream());
            Path saveFilePath = Path.of(savePath + fileName + ".png");
            Files.copy(inputStream, saveFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
