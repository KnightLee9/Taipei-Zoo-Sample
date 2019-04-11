package com.knight.taiepizoo.model;

public class ClickEvent {
    ClickEventType type;
    Object eventData;

    public ClickEvent(ClickEventType type, Object eventData) {
        this.type = type;
        this.eventData = eventData;
    }

    public ClickEventType getType() {
        return type;
    }

    public ClickEvent setType(ClickEventType type) {
        this.type = type;
        return this;
    }

    public Object getEventData() {
        return eventData;
    }

    public ClickEvent setEventData(Object eventData) {
        this.eventData = eventData;
        return this;
    }
}
