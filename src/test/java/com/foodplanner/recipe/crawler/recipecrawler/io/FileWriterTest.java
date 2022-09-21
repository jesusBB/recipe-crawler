package com.foodplanner.recipe.crawler.recipecrawler.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileWriterTest {

    @Test
    void testWriteFile() throws IOException {
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(
                        "C:\\usr\\bin\\Git Repositories\\recipe-crawler\\src\\main\\resources\\output\\bbcRecipes.txt"));
        writer.write(str);

        writer.close();
    }

}
