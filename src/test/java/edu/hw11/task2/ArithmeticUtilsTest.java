package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArithmeticUtilsTest {

    @Test
    void testChangedMethod() throws NoSuchMethodException {
        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();
        ByteBuddyAgent.install();
        DynamicType.Loaded<ArithmeticUtils> dynamicType = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.is(ArithmeticUtils.class.getMethod("sum", int.class, int.class)))
            .intercept(MethodDelegation.to(Multiply.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        assertEquals(arithmeticUtils.sum(2, 5), 10);
    }
}
