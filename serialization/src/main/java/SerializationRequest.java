import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by Yaroslav on 10.09.16.
 */
public interface SerializationRequest extends Serializable{
    String getMethodName();
    Object[] getArgs();
}
