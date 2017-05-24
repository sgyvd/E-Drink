package cn.droidlover.xdroid.cache;

/**
 * Created by wangtao on 2016/11/27.
 */

public interface ICache {
    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

    boolean contains(String key);

    void clear();

}
