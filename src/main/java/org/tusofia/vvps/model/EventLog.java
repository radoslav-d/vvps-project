package org.tusofia.vvps.model;

import org.tusofia.vvps.utils.UserIdParser;

public class EventLog {

    private String datetime;
    private String eventContext;
    private String component;
    private String eventName;
    private String description;
    private String derivedUserId;

    public String getDatetime() {
        return datetime;
    }

    public EventLog setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getEventContext() {
        return eventContext;
    }

    public EventLog setEventContext(String eventContext) {
        this.eventContext = eventContext;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public EventLog setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public EventLog setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventLog setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDerivedUserId() {
        if (derivedUserId == null) {
            deriveUserId();
        }
        return derivedUserId;
    }

    public EventLog setDerivedUserId(String derivedUserId) {
        this.derivedUserId = derivedUserId;
        return this;
    }

    public EventLog deriveUserId() {
        derivedUserId = UserIdParser.deriveUserIdFromEventDescription(getDescription());
        return this;
    }
}
