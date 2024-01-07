package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassCreatorTest {

    @Test
    void testToString()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DynamicType.Unloaded<?> unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .name("SayHelloClass")
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make();

        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader()).getLoaded();
        assertEquals(dynamicType.getDeclaredConstructor().newInstance().toString(), "Hello, ByteBuddy!");
    }
}
