package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDatabase implements PersonDatabase {
    private final Map<Integer, Person> idMap;
    private final Map<String, List<Person>> nameMap;
    private final Map<String, List<Person>> addressMap;
    private final Map<String, List<Person>> phoneNumberMap;
    private final ReadWriteLock lock;
    private final AtomicInteger size;

    public ReadWriteDatabase() {
        idMap = new HashMap<>();
        nameMap = new HashMap<>();
        addressMap = new HashMap<>();
        phoneNumberMap = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        size = new AtomicInteger(0);
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            idMap.put(person.id(), person);

            nameMap.putIfAbsent(person.name(), new ArrayList<>());
            nameMap.get(person.name()).add(person);

            addressMap.putIfAbsent(person.address(), new ArrayList<>());
            addressMap.get(person.address()).add(person);

            phoneNumberMap.putIfAbsent(person.phoneNumber(), new ArrayList<>());
            phoneNumberMap.get(person.phoneNumber()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idMap.get(id);
            nameMap.get(person.name()).remove(person);
            addressMap.get(person.address()).remove(person);
            phoneNumberMap.get(person.phoneNumber()).remove(person);
            idMap.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressMap.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneNumberMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public int size() {
        return size.get();
    }
}
