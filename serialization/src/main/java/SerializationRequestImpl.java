

import java.lang.reflect.Method;

/**
 * Created by Yaroslav on 10.09.16.
 */
public class SerializationRequestImpl implements SerializationRequest{
    private final String methodName;
    private final Object[] args;

    public SerializationRequestImpl(Object[] args, Method method) {
        this.args = args;
        this.methodName = method.getName();
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }
}
