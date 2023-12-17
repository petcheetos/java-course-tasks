package edu.hw10.task2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final Path cacheFilePath;

    private CacheProxy(Object target, Path path) {
        this.target = target;
        this.cache = new HashMap<>();
        this.cacheFilePath = path;
    }

    @SuppressWarnings("unchecked")
    static <T> T create(Object target, Class<?> interfaceClass, Path path) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class[] {interfaceClass},
            new CacheProxy(target, path)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = method.getName() + Arrays.toString(args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);
                if (method.getAnnotation(Cache.class).persist()) {
                    writeCacheToFile();
                }
                return result;
            }
        }
        return method.invoke(target, args);
    }

    private void writeCacheToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(cacheFilePath)) {
            for (Map.Entry<String, Object> entry : cache.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
