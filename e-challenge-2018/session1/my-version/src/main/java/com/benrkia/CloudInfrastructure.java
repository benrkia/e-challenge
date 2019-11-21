package com.benrkia;

import com.benrkia.myexceptions.CreateStoreException;
import com.benrkia.myexceptions.MachineStateException;
import com.benrkia.storage.Document;
import com.benrkia.storage.Store;
import com.benrkia.vms.Machine;
import com.benrkia.vms.MachineState;

import java.util.*;

public class CloudInfrastructure {

    TreeMap<String, Store> stores = new TreeMap<>();

    TreeMap<String, Machine> machines = new TreeMap<>();

    public void createStore(String storeName) {
        Store store = stores.get(storeName);

        if(store != null)
            throw new CreateStoreException();

        store = new Store(storeName);
        stores.put(storeName, store);
    }

    public void uploadDocument(String storeName, String ...documentNames) {
        Store store = stores.get(storeName);

        for(String documentName:documentNames)
            store.addDocument(new Document(documentName));
    }

    public String listStores() {
        StringBuilder listStores = new StringBuilder();

        for(Store store: stores.values()) {
            listStores.append(store);

            if(store != stores.lastEntry().getValue())
                listStores.append("||");
        }

        return listStores.toString();
    }


    public void deleteStore(String storeName) {
        Store store = stores.remove(storeName);
        store = null;

    }

    public void emptyStore(String storeName) {

        Store store = stores.get(storeName);
        store.empty();

    }

    public void createMachine(String machineName, String os, String disk, String memory) {
        double diskSize, memorySize;

        diskSize = Double.parseDouble(disk.substring(0, disk.length()-2));
        memorySize = Double.parseDouble(memory.substring(0, memory.length()-2));

        Machine machine = new Machine(machineName, os, diskSize, memorySize);
        machines.put(machineName, machine);

    }

    public String listMachines() {
        StringBuilder listMachines = new StringBuilder();

        for(Machine machine: machines.values()) {
            listMachines.append(machine);

            if(machine != machines.lastEntry().getValue())
                listMachines.append("||");
        }

        return listMachines.toString();
    }

    public void startMachine(String machineName) {
        Machine machine = machines.get(machineName);

        if(machine.getState() == MachineState.Running)
            throw new MachineStateException();

        machine.setState(MachineState.Running);
    }

    public void stopMachine(String machineName) {
        Machine machine = machines.get(machineName);

        machine.setState(MachineState.Stopped);
    }

    public double usedMemory(String machineName) {

        Machine machine = machines.get(machineName);

        double usedMemory = 0.0;

        if(machine.getState() == MachineState.Running)
            usedMemory = machine.getMemory();

        return usedMemory;
    }

    public double usedDisk(String id) {
        Machine machine = machines.get(id);
        double usedDisk = 0.0;

        if(machine != null)
            usedDisk = machine.getDisk();
        else
            usedDisk = storeUsedDisk(id);

        return usedDisk;
    }

    private double storeUsedDisk(String storeName) {
        Store store = stores.get(storeName);
        double storeUsedDisk = 0.0;

        for(Document document:store.getDocuments())
            storeUsedDisk += document.getUSED_DISK();

        return storeUsedDisk;
    }

    public double globalUsedDisk() {
        double globalUsedDisk = 0.0;
        for(String storeName:stores.keySet())
            globalUsedDisk += storeUsedDisk(storeName);

        for (String machineName:machines.keySet())
            globalUsedDisk += usedDisk(machineName);

        return globalUsedDisk;
    }

    public double globalUsedMemory() {
        double globalUsedMemory = 0.0;

        for(String machineName:machines.keySet())
            globalUsedMemory += usedMemory(machineName);

        return globalUsedMemory;
    }
}
