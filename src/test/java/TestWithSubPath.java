import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Retriever;

public class TestWithSubPath {
    private final Retriever retriever = new Retriever(Constants.PROPERTIES, "testWithSubPath.properties");

    @Test
    public void testNormalRetrieve() {
        String result = retriever.retrieve(Constants.TEST_NORMAL_RETRIEVE);
        Assertions.assertEquals(Constants.NORMAL_RETRIEVE, result);
    }

    @Test
    public void testNonExistingRetrieve() {
        String key = Constants.NOT_EXISTING;
        String result = retriever.retrieve(key);
        Assertions.assertEquals(key, result);
    }
}
