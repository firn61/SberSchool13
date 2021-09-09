package edu.sber.lect7.pluginRootDirectory;

import edu.sber.lect7.DumbEncrypter;
import edu.sber.lect7.DumbWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class CustomClassLoader extends URLClassLoader {

    private URL[] urls;
    private static Map<String, Class> loadedClasses = new HashMap<>();
    private boolean isEncrypted = false;
    private byte key;

    public CustomClassLoader(URL[] urls, boolean isEncrypted) {
        super(urls);
        this.urls = urls;
        this.isEncrypted = isEncrypted;
        this.key = key;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //if class already loaded
        if (loadedClasses.containsValue(name)) {
            return loadedClasses.get(name);
        }
        if (!name.startsWith("Plugin")) {
            return ClassLoader.getSystemClassLoader().loadClass(name);
        }
        try {
            URL u = new URL(urls[0] + "/" + name + ".class");
            URLConnection connection = u.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();
            while (data != -1) {
                buffer.write(data);
                data = input.read();
            }
            input.close();
            byte[] classData = buffer.toByteArray();
            System.out.println("========= original");
            printBytes(classData);
//            System.out.println("");
//            System.out.println("========= encoded");
//            byte[] codedClassData = DumbEncrypter.code(classData);
            //DumbWriter.writeFile(codedClassData, "D:/test", name + ".class");
//            printBytes(codedClassData);
//            System.out.println("");
            System.out.println("========= decoded");
            byte[] decdodedClassData = DumbEncrypter.encode(classData);
            printBytes(decdodedClassData);
            System.out.println("");
            Class<?> c = super.defineClass(name, decdodedClassData, 0, classData.length);
            if (c != null) {
                loadedClasses.put(name, c);
            }
            return c;
        } catch (NullPointerException e) {
            System.out.println("NPE in class loader " + name);
        } catch (ClassFormatError e) {
            System.out.println("class format error");
        } catch (IOException e) {
            System.out.println("IOE in classLoader");
        } catch (LinkageError e) {
            System.out.println("Linkage error");
        }
        throw new ClassNotFoundException();
    }

    private void printBytes(byte[] bytes) {
        byte delimiter = 0;
        for (byte byteE : bytes) {
            delimiter++;
            System.out.print(Integer.toHexString(byteE) + " ");
            if (delimiter == 16) {
                delimiter = 0;
                System.out.println("");
            }
        }
    }

    @Override
    protected Class<?> findClass(String moduleName, String name) {
        return super.findClass(moduleName, name);
    }
}
