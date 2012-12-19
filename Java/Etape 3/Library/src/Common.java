import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
    
    public static byte[] toByte(Object object) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        
        try {
            out = new ObjectOutputStream(bos);   
            out.writeObject(object);
            return bos.toByteArray();
        } catch (IOException ex) {
            System.out.println("toByte failed");
            return null;
        }
    }
    
    public static Object fromByte(byte[] l_byte) {
        ByteArrayInputStream bis = new ByteArrayInputStream(l_byte);
        ObjectInput in;
        
        try {
            in = new ObjectInputStream(bis);
            return in.readObject(); 
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("fromByte failed");
            return null;
        }
    }
}
