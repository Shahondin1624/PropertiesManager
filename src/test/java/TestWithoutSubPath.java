import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Retriever;

public class TestWithoutSubPath {

    private final Retriever retriever = new Retriever(Constants.PROPERTIES);
    private final String subPath = "testWithoutSubPath.properties";

    @Test
    public void testNormalRetrieve() {
        String result = retriever.retrieve(subPath, Constants.TEST_NORMAL_RETRIEVE);
        Assertions.assertEquals(Constants.NORMAL_RETRIEVE, result);
    }

    @Test
    public void testNonExistingRetrieve() {
        String key = Constants.NOT_EXISTING;
        String result = retriever.retrieve(subPath, key);
        Assertions.assertEquals(key, result);
    }
}
