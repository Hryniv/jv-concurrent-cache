package mate.academy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Map<K, V> cache = new HashMap<>();

    public V get(K key) {
        V value = null;
        try {
            readWriteLock.readLock().lock();
            value = cache.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }

        return value;
    }

    public void put(K key, V value) {
        try {
            readWriteLock.writeLock().lock();
            cache.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        try {
            readWriteLock.writeLock().lock();
            cache.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        try {
            readWriteLock.writeLock().lock();
            cache.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        int size = 0;
        try {
            readWriteLock.readLock().lock();
            size = cache.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return size;
    }
}
