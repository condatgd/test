package de.berlin.gd.kantine_event_storming.fuellstands_system.impl;

import de.berlin.gd.kantine_event_storming.fuellstands_system.Fuellstandssystem;
import de.berlin.gd.kantine_event_storming.fuellstands_system.TriggerableTask;

public class FuellstandssystemImpl implements Fuellstandssystem, TriggerableTask {

    private long zaehler;

    @Override
    public void erhoeheEssensZaehlerUm1() {
        TimerImpl statistikTimer = new TimerImpl();
        statistikTimer.addTaskToTrigger(new StatistikBerechnungImpl());
        statistikTimer.startTimedTrigger(10);

        TimerImpl essensVerringerungsTimer = new TimerImpl();
        statistikTimer.addTaskToTrigger(this);
        statistikTimer.startTimedTrigger(30*60);
    }

    @Override
    public long essensZaehler() {
        return zaehler;
    }

    @Override
    public void doRun() {
        zaehler--;
    }
}
