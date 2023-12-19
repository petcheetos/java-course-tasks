package edu.hw10.task1;

import edu.hw10.task1.generators.BooleanGenerator;
import edu.hw10.task1.generators.Generator;
import edu.hw10.task1.generators.IntegerGenerator;
import edu.hw10.task1.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class RandomObjectGenerator {
    private final Map<Class<?>, Generator<?>> generators;

    public RandomObjectGenerator() {
        generators = new HashMap<>();
        generators.put(String.class, new StringGenerator());
        generators.put(int.class, new IntegerGenerator());
        generators.put(boolean.class, new BooleanGenerator());
    }

    public <T> T nextObject(Class<T> tClass) {
        Constructor<?>[] constructors = tClass.getConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("Class doesn't have constructors");
        }
        Constructor<T> constructor = (Constructor<T>) constructors[0];

        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            args[i] = getRandomValue(parameter);
        }
        try {
            return constructor.newInstance(args);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T nextObject(Class<T> tClass, String factoryMethodName) {
        Method[] methods = tClass.getMethods();
        Method factoryMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(factoryMethodName) && method.getReturnType().equals(tClass)) {
                factoryMethod = method;
            }
        }
        if (factoryMethod == null) {
            throw new RuntimeException("Factory method does not exist");
        }

        Parameter[] parameters = factoryMethod.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            args[i] = getRandomValue(parameter);
        }
        try {
            return (T) factoryMethod.invoke(null, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getRandomValue(Parameter parameter) {
        Class<?> parameterType = parameter.getType();
        Generator<?> generator = generators.get(parameterType);
        return generator.generate(parameter);
    }
}
