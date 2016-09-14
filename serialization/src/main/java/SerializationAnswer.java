import java.io.Serializable;

/**
 * Created by Yaroslav on 10.09.16.
 */
public interface SerializationAnswer extends Serializable {
    Throwable getException();

    Object getResult();
}
