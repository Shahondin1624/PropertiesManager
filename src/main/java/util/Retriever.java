package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/***
 * Small class to retrieve predefined keys from .properties files
 */
public class Retriever {
    private final Logger logger = LoggerFactory.getLogger(Retriever.class);
    private String rootPath;
    private String subPath;

    /***
     *
     * @param rootPath folder in which this class is looking for files
     */
    public Retriever(String rootPath) {
        this.rootPath = rootPath;
    }

    /***
     *
     * @param rootPath folder in which this class is looking for files
     * @param subPath path to the file in which the key-value pairs are defined
     */
    public Retriever(String rootPath, String subPath) {
        this(rootPath);
        this.subPath = subPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    /***
     * Retrieves in the defined file
     * @param subPath path to the file in which the key-value pairs are defined
     * @param key whose value should be retrieved
     * @return on success the value of the defined key or on failure the key
     */
    public String retrieve(String subPath, String key) {
        return get(subPath, key);
    }

    /***
     *
     * @param key whose value should be retrieved
     * @return on success the value of the defined key or on failure the key
     * @throws IllegalStateException when no subPath is set for this retriever
     */
    public String retrieve(String key) throws IllegalStateException {
        if (subPath == null) {
            IllegalStateException e = new IllegalStateException("No exact path to retrieve from specified");
            logger.error(e.getMessage());
            throw e;
        }
        return get(subPath, key);
    }

    private String get(String subPath, String key) {
        try {
            Properties properties = new Properties();
            properties.load(Retriever.class.getModule().getResourceAsStream(rootPath + "/" + subPath));
            String retrieved = properties.getProperty(key);
            return retrieved == null? key : retrieved;
        } catch (IOException e) {
            logger.error("Error retrieving property because of {}: {}", e.getClass().getSimpleName(), e.getMessage());
            return key;
        }
    }
}
