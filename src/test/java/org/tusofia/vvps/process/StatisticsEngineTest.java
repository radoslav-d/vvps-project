package org.tusofia.vvps.process;

import org.junit.Assert;
import org.junit.Test;
import org.tusofia.vvps.model.EditEventsProcessingResult;
import org.tusofia.vvps.model.EventConstants;
import org.tusofia.vvps.model.EventLog;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StatisticsEngineTest {

    private static final String FIRST_USER_ID = "1111";
    private static final String SECOND_USER_ID = "2222";
    private static final String OTHER_EVENT_NAME = "not-meaningful";
    private static final double DELTA = 0.0001;

    @Test
    public void processEditEventLogs_Should_Return_Valid_EditEventsProcessingResult_Average() {
        StatisticsEngine engine = new StatisticsEngine();
        EditEventsProcessingResult result = engine.processEditEventLogs(getTestEditEvents());
        Assert.assertEquals(0.56666, result.getAverageEditEventsPerUser(), DELTA);
    }

    @Test
    public void processEditEventLogs_Should_Return_Valid_EditEventsProcessingResult_StandardDeviation() {
        StatisticsEngine engine = new StatisticsEngine();
        EditEventsProcessingResult result = engine.processEditEventLogs(getTestEditEvents());
        Assert.assertEquals(0.23333, result.getStandardDeviationEditEventsPerUser(), DELTA);
    }

    @Test
    public void processEditEventLogs_Should_Return_Valid_EditEventsProcessingResult_Variance() {
        StatisticsEngine engine = new StatisticsEngine();
        EditEventsProcessingResult result = engine.processEditEventLogs(getTestEditEvents());
        Assert.assertEquals(0.054444, result.getVarianceEditEventsPerUser(), DELTA);
    }

    private Set<EventLog> getTestEditEvents() {
        EventLog eventLog1 = new EventLog().setDerivedUserId(FIRST_USER_ID)
                                           .setComponent(EventConstants.WIKI_COMPONENT)
                                           .setEventName(EventConstants.WIKI_UPDATED_EVENT);
        EventLog eventLog2 = new EventLog().setDerivedUserId(FIRST_USER_ID)
                                           .setComponent(EventConstants.ASSIGNMENT_COMPONENT)
                                           .setEventName(OTHER_EVENT_NAME);
        EventLog eventLog3 = new EventLog().setDerivedUserId(SECOND_USER_ID)
                                           .setComponent(EventConstants.WIKI_COMPONENT)
                                           .setEventName(EventConstants.WIKI_UPDATED_EVENT);
        EventLog eventLog4 = new EventLog().setDerivedUserId(SECOND_USER_ID)
                                           .setComponent(EventConstants.FILE_COMPONENT)
                                           .setEventName(OTHER_EVENT_NAME);
        EventLog eventLog5 = new EventLog().setDerivedUserId(FIRST_USER_ID)
                                           .setComponent(EventConstants.WIKI_COMPONENT)
                                           .setEventName(EventConstants.WIKI_UPDATED_EVENT);
        EventLog eventLog6 = new EventLog().setDerivedUserId(SECOND_USER_ID)
                                           .setComponent(EventConstants.SYSTEM_COMPONENT)
                                           .setEventName(OTHER_EVENT_NAME);
        EventLog eventLog7 = new EventLog().setDerivedUserId(FIRST_USER_ID)
                                           .setComponent(EventConstants.WIKI_COMPONENT)
                                           .setEventName(EventConstants.WIKI_UPDATED_EVENT);
        EventLog eventLog8 = new EventLog().setDerivedUserId(FIRST_USER_ID)
                                           .setComponent(EventConstants.WIKI_COMPONENT)
                                           .setEventName(EventConstants.WIKI_UPDATED_EVENT);
        return new HashSet<>(Arrays.asList(eventLog1, eventLog2, eventLog3, eventLog4, eventLog5, eventLog6, eventLog7, eventLog8));
    }
}
