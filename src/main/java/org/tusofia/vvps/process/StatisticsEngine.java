package org.tusofia.vvps.process;

import org.tusofia.vvps.model.EventConstants;
import org.tusofia.vvps.model.EventLog;
import org.tusofia.vvps.model.EditEventsProcessingResult;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class StatisticsEngine {

    public EditEventsProcessingResult processEditEventLogs(Set<EventLog> eventLogs) {
        Map<String, List<EventLog>> eventsByUsers = eventLogs.stream()
                                                             .collect(Collectors.groupingBy(EventLog::getDerivedUserId));
        double average = getEditEventsPerUser(eventsByUsers).average()
                                                            .getAsDouble();
        double variance = getEditEventsPerUser(eventsByUsers).map(value -> Math.pow(value - average, 2))
                                                             .average()
                                                             .getAsDouble();
        double standardDeviation = Math.sqrt(variance);
        return new EditEventsProcessingResult().setAverageEditEventsPerUser(average)
                                               .setVarianceEditEventsPerUser(variance)
                                               .setStandardDeviationEditEventsPerUser(standardDeviation);
    }

    private DoubleStream getEditEventsPerUser(Map<String, List<EventLog>> eventsByUsers) {
        return eventsByUsers.values()
                            .stream()
                            .mapToDouble(this::wikiEditEventsForUser);
    }

    private double wikiEditEventsForUser(List<EventLog> userEvents) {
        int totalEventsByThisUser = userEvents.size();
        long wikiEditedEventsByThisUser = userEvents.stream()
                                                    .filter(this::isWikiEditEvent)
                                                    .count();
        return (double) wikiEditedEventsByThisUser / totalEventsByThisUser;
    }

    private boolean isWikiEditEvent(EventLog eventLog) {
        return eventLog.getComponent()
                       .equals(EventConstants.WIKI_COMPONENT) && eventLog.getEventName()
                                                                         .equals(EventConstants.WIKI_UPDATED_EVENT);
    }
}
