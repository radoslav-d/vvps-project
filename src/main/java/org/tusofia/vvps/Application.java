package org.tusofia.vvps;

import org.tusofia.vvps.model.EventLog;
import org.tusofia.vvps.model.EditEventsProcessingResult;
import org.tusofia.vvps.process.StatisticsEngine;
import org.tusofia.vvps.xlsx.XlsxReaderFacade;

import java.util.Set;

public class Application {

    public static void main(String[] args) throws Exception {
        try (XlsxReaderFacade readerFacade = new XlsxReaderFacade("/Users/i530753/Downloads/Logs_Course A_StudentsActivities.xlsx")) {
            Set<EventLog> eventLogs = readerFacade.load();
            StatisticsEngine engine = new StatisticsEngine();
            EditEventsProcessingResult result = engine.processEditEventLogs(eventLogs);
            System.out.println("Average: " + result.getAverageEditEventsPerUser());
            System.out.println("Variance: " + result.getVarianceEditEventsPerUser());
            System.out.println("Standard deviation: " + result.getStandardDeviationEditEventsPerUser());
        }
    }
}
