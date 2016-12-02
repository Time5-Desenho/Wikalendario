package com.time2desenho.wikalendario.controller;

import com.time2desenho.wikalendario.model.Event;

public interface EventObserver {

    void notifyNewEvent(Event event);
}
