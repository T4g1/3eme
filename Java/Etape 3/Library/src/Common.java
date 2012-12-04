import java.io.UnsupportedEncodingException;

/**
 * Utility class
 */
public class Common {
    /**
     * Convertit un tableau de byte en string
     * @param array
     * @return 
     */
    public static String byteToString(byte[] array) {
        try {
            return new String(array, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "UNSUPPORTED_ENCODING";
        } catch (NullPointerException ex) {
            return "NULL_VALUE";
        }
    }
}
