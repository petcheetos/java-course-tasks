package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibTest {

    @Test
    void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
        IllegalAccessException {
        DynamicType.Unloaded<?> type = new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Modifier.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public @NotNull ByteCodeAppender appender(@NotNull Target target) {
                    return (methodVisitor, context, methodDescription) -> {
                        Label label = new Label();
                        methodVisitor.visitCode();
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.I2L);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitLabel(label);
                        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_1);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Fibonacci", "fib", "(I)J");
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "Fibonacci", "fib", "(I)J");
                        methodVisitor.visitInsn(Opcodes.LADD);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitEnd();

                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make();

        Class<?> loaded = type.load(getClass().getClassLoader()).getLoaded();
        Method method = loaded.getDeclaredMethod("fib", int.class);
        long result = (long) method.invoke(loaded.getDeclaredConstructor().newInstance(), 8);
        assertEquals(21, result);
    }
}
