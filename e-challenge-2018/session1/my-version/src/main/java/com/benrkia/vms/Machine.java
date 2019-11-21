package com.benrkia.vms;

public class Machine {

    private String name;
    private String os;
    private double disk;
    private double memory;
    private MachineState state;

    public Machine(String name, String os, double disk, double memory) {
        this.name = name;
        this.os = os;
        this.disk = disk;
        this.memory = memory;
        this.state = MachineState.Inactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getDisk() {
        return disk;
    }

    public void setDisk(double disk) {
        this.disk = disk;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder listMachine = new StringBuilder();

        listMachine.append(name+":");

        switch (state) {
            case Inactive:
                listMachine.append("inactive");
                break;
            case Running:
                listMachine.append("running");
                break;
            case Stopped:
                listMachine.append("stopped");
                break;
        }

        return listMachine.toString();
    }
}
