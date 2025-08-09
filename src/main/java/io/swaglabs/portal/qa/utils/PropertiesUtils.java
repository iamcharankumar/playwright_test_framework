package io.swaglabs.portal.qa.utils;

import io.swaglabs.portal.qa.exceptions.WebUtilsException;
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
        log.info("Loading the Properties file....");
        InputStream fileInputStream;
        Properties properties;
        try {
            fileInputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new WebUtilsException("Properties File failed loading..." + e.getMessage());
        }
        return properties;
    }
}