package de.berlin.gd.kantine_event_storming.fuellstands_system;

public interface Timer {
    void startTimedTrigger(int seconds);

    void addTaskToTrigger(TriggerableTask task);

}
