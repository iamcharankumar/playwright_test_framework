package io.swaglabs.portal.qa.utils;

import io.swaglabs.portal.qa.exceptions.UtilsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesUtils {

    public static Properties loadProperties(String fileName) {
        InputStream fileInputStream;
        Properties properties;
        log.info("Loading the Properties file....");
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new UtilsException("Properties File failed loading..." + e.getMessage());
        }
        return properties;
    }
}