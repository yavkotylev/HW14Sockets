/**
 * Created by Yaroslav on 10.09.16.
 */
public class SerializationAnswerImpl implements SerializationAnswer {
    private final Throwable exception;
    private final Object result;

    public SerializationAnswerImpl(Throwable exception, Object result) {
        this.exception = exception;
        this.result = result;
    }

    public Throwable getException() {
        return exception;
    }

    public Object getResult() {
        return result;
    }
}
