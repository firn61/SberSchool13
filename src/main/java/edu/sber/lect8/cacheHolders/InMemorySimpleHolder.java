package edu.sber.lect8.cacheHolders;

import java.util.*;

public class InMemorySimpleHolder implements SimpleHolder {

    Map<String, Map<ArgsHolder, Object>> resultMap = new HashMap<>();

    @Override
    public Object get(String name, Object[] args) {
        System.out.println("method " + name + "from cache");
        return resultMap.get(name).get(new ArgsHolder(args));
    }

    @Override
    public void add(String name, Object[] args, Object result) {
        if (resultMap.containsKey(name)){
            System.out.println("Already contains");
            resultMap.get(name).put(new ArgsHolder(args), result);
        } else {
            System.out.println("put new");
            Map<ArgsHolder, Object> storedMap = new HashMap<>();
            storedMap.put(new ArgsHolder(args), result);
            resultMap.put(name, storedMap);
        }
    }

    @Override
    public void add(String name, Object[] args, Object result, int elLimit) {
        List fullList = (List) result;
        List limitList = new ArrayList(fullList.subList(0, elLimit));
        add(name, args, limitList);
    }

    @Override
    public boolean isPresent(String name, Object[] args) {
        if (resultMap.containsKey(name)) {
            System.out.println("name exist");
            Map<ArgsHolder, Object> cachedMethods = resultMap.get(name);
            if (cachedMethods.containsKey(new ArgsHolder(args))) {
                System.out.println("result founded");
                return true;
            }
        }
        System.out.println("not exist");
        return false;
    }
}

