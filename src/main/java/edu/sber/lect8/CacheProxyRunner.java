package edu.sber.lect8;


import edu.sber.lect8.proxy.CachedProxy;
import edu.sber.lect8.service.Service;
import edu.sber.lect8.service.ServiceImpl;

public class CacheProxyRunner {
    public static void main(String[] args) {
        Service service = new ServiceImpl();
        CachedProxy proxy = new CachedProxy(service, "D:/SberSchool13/cached/");
        Service cached = proxy.cache(service);
        cached.work("test1");
        cached.work("test3");
        cached.work("test3");
        cached.work("test3");
        cached.work("test5");
    }
}
