package ru.pavelyurtaev.jobbot.util;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

public class FileUtils {
    @SneakyThrows
    public static File readFileFromClassPath(final String path) {
        return new ClassPathResource(path).getFile();
    }
}
