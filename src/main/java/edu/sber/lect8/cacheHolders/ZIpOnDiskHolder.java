package edu.sber.lect8.cacheHolders;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZIpOnDiskHolder extends OnDiskHolder{

    String path;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Object get(String name, Object[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                new GZIPInputStream(new FileInputStream(path + getArgsHashCode(args)))))) {
            Object result = in.readObject();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(String name, Object[] args, Object result) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(
                new GZIPOutputStream(new FileOutputStream(path + getArgsHashCode(args)))))) {
            oos.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(String name, Object[] args, Object result, int elLimit) {
        List fullList = (List) result;
        List limitList = new ArrayList(fullList.subList(0, elLimit));
        add(name, args, result);
    }

    @Override
    public boolean isPresent(String name, Object[] args) {
        File file = new File(path + getArgsHashCode(args));
        return file.exists() && !file.isDirectory();
    }

}
