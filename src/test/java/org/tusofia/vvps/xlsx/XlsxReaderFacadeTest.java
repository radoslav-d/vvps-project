package org.tusofia.vvps.xlsx;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.Assert;
import org.junit.Test;
import org.tusofia.vvps.model.EventLog;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class XlsxReaderFacadeTest {

    private static final String RESOURCES_PATH = "src/test/resources/";

    @Test
    public void constructor_Should_Throw_Exception_On_Invalid_Filepath() {
        Assert.assertThrows(FileNotFoundException.class, () -> new XlsxReaderFacade("invalid-file.xlsx"));
    }

    @Test
    public void constructor_Should_Throw_Exception_On_Invalid_File_Format() {
        Assert.assertThrows(NotOfficeXmlFileException.class, () -> new XlsxReaderFacade(RESOURCES_PATH + "not-xlsx-file.txt"));
    }

    @Test
    public void load_Should_Read_Content_From_Valid_File() {
        try (XlsxReaderFacade reader = new XlsxReaderFacade(RESOURCES_PATH + "test-event-logs.xlsx")) {
            Set<EventLog> eventLogs = reader.load();
            Assert.assertEquals(4, eventLogs.size());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void load_Should_Read_Content_From_File_And_Provide_Valid_EventLogs_UserIds() {
        try (XlsxReaderFacade reader = new XlsxReaderFacade(RESOURCES_PATH + "test-event-logs.xlsx")) {
            Set<EventLog> eventLogs = reader.load();
            List<String> userIds = eventLogs.stream()
                                            .map(EventLog::getDerivedUserId)
                                            .collect(Collectors.toList());
            Assert.assertTrue(userIds.contains("8429"));
            Assert.assertTrue(userIds.contains("8414"));
            Assert.assertTrue(userIds.contains("8427"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void load_Should_Read_Content_From_File_And_Provide_Valid_EventLogs_Time() {
        try (XlsxReaderFacade reader = new XlsxReaderFacade(RESOURCES_PATH + "test-event-logs.xlsx")) {
            Set<EventLog> eventLogs = reader.load();
            List<String> datetimes = eventLogs.stream()
                                              .map(EventLog::getDatetime)
                                              .collect(Collectors.toList());
            Assert.assertTrue(datetimes.contains("2/03/21, 14:12"));
            Assert.assertTrue(datetimes.contains("2/03/21, 14:11"));
            Assert.assertTrue(datetimes.contains("1/03/21, 12:05"));
            Assert.assertTrue(datetimes.contains("26/02/21, 17:47"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void load_Should_Read_Content_From_File_And_Provide_Valid_EventLogs_EventContext() {
        try (XlsxReaderFacade reader = new XlsxReaderFacade(RESOURCES_PATH + "test-event-logs.xlsx")) {
            Set<EventLog> eventLogs = reader.load();
            List<String> eventContexts = eventLogs.stream()
                                                  .map(EventLog::getEventContext)
                                                  .collect(Collectors.toList());
            Assert.assertTrue(eventContexts.contains("Course: Semantic Web"));
            Assert.assertTrue(eventContexts.contains("Wiki: Защита на проект"));
            Assert.assertTrue(eventContexts.contains("File: Текущи резултати към 26.01.2021"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void load_Should_Read_Content_From_File_And_Provide_Valid_EventLogs_Component() {
        try (XlsxReaderFacade reader = new XlsxReaderFacade(RESOURCES_PATH + "test-event-logs.xlsx")) {
            Set<EventLog> eventLogs = reader.load();
            List<String> components = eventLogs.stream()
                                               .map(EventLog::getComponent)
                                               .collect(Collectors.toList());
            Assert.assertTrue(components.contains("System"));
            Assert.assertTrue(components.contains("Wiki"));
            Assert.assertTrue(components.contains("File"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void load_Should_Read_Content_From_File_And_Provide_Valid_EventLogs_EvenName() {
        try (XlsxReaderFacade reader = new XlsxReaderFacade(RESOURCES_PATH + "test-event-logs.xlsx")) {
            Set<EventLog> eventLogs = reader.load();
            List<String> eventNames = eventLogs.stream()
                                               .map(EventLog::getEventName)
                                               .collect(Collectors.toList());
            Assert.assertTrue(eventNames.contains("Course viewed"));
            Assert.assertTrue(eventNames.contains("Course module viewed"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
