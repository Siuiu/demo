package com.ly.demo.bean;

import cn.hutool.json.JSONUtil;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JavaDeCoding {
    public static void main(String[] args) throws Exception {
        String script = "public class Demo {public String test(String s){return s;}}\n";

        // 创建一个JavaCompiler实例
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 编译Java代码并将其保存到内存中
        MemoryJavaFileManager fileManager = new MemoryJavaFileManager(compiler.getStandardFileManager(null, null, null));
        SimpleJavaFileObject sourceFile = new MemoryJavaSourceFile("Demo", script);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(sourceFile);
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();

        // 加载编译后的类
        Class<?> demoClass = fileManager.getClassLoader(null).loadClass("Demo");

        // 创建类的实例
        Object demoObject = demoClass.getDeclaredConstructor().newInstance();

        // 调用test方法
        Method testMethod = demoClass.getMethod("test");
        Object hello = testMethod.invoke(demoObject,"dsadsa");
        System.out.println(JSONUtil.toJsonStr(hello));

    }
}

// 自定义JavaFileManager用于保存编译后的类字节码到内存中
class MemoryJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final Map<String, MemoryJavaClass> classes = new HashMap<>();

    MemoryJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        MemoryJavaClass file = new MemoryJavaClass(className);
        classes.put(className, file);
        return file;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return new MemoryClassLoader(classes);
    }
}

// 自定义JavaFileObject用于保存Java源代码到内存中
class MemoryJavaSourceFile extends SimpleJavaFileObject {
    private final String source;

    MemoryJavaSourceFile(String name, String source) {
        super(URI.create("memory:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.source = source;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return source;
    }
}

// 自定义ClassLoader用于加载内存中的类字节码
class MemoryClassLoader extends ClassLoader {
    private final Map<String, MemoryJavaClass> classes;

    MemoryClassLoader(Map<String, MemoryJavaClass> classes) {
        this.classes = classes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        MemoryJavaClass file = classes.get(name);
        if (file == null) {
            return super.findClass(name);
        }
        byte[] bytes = file.getBytes();
        return defineClass(name, bytes, 0, bytes.length);
    }
}

// 自定义JavaClass用于保存编译后的类字节码到内存中
class MemoryJavaClass extends SimpleJavaFileObject {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    MemoryJavaClass(String name) {
        super(URI.create("memory:///" + name.replace('.', '/') + Kind.CLASS.extension), Kind.CLASS);
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }

    byte[] getBytes() {
        return outputStream.toByteArray();
    }
}

