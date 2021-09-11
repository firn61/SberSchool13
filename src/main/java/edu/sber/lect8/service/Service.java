package edu.sber.lect8.service;

import edu.sber.lect8.annotatons.Cached;
import edu.sber.lect8.enums.CacheType;

import java.util.Date;
import java.util.List;

public interface Service {

    @Cached(identityBy = {String.class})
    double doHardWork(String name, Double var);
    @Cached(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> run(String item, Double value, Date date);
    @Cached(cacheType = CacheType.FILE, fileNamePrefix = "workNamePrefix", cutOff = 100_000, zip = true)
    List<String> work (String item);

}
