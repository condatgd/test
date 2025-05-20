package de.berlin.gd.kantine_event_storming.fuellstands_system.impl;

import de.berlin.gd.kantine_event_storming.fuellstands_system.Timer;
import de.berlin.gd.kantine_event_storming.fuellstands_system.TriggerableTask;

import java.util.ArrayList;
import java.util.List;

public class TimerImpl implements Timer {

    private List<TriggerableTask> tasks = new ArrayList<>();
    @Override
    public void startTimedTrigger(int seconds) {
        tasks.stream().map(task -> {
            try {
                return new CronTimer(task, seconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public void addTaskToTrigger(TriggerableTask task) {
        tasks.add(task);
    }

    private class CronTimer {
        public CronTimer(TriggerableTask task, int seconds) throws InterruptedException {
            wait(seconds);
            task.doRun();
        }
    }
}
