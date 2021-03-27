package org.tusofia.vvps.xlsx;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.tusofia.vvps.model.EventLog;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class XlsxReaderFacade implements AutoCloseable {

    private FileInputStream fileInputStream;
    private XSSFWorkbook workbook;

    public XlsxReaderFacade(String filePath) throws IOException {
        fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);

    }

    public Set<EventLog> load() {
        XSSFSheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = worksheet.rowIterator();
        Set<EventLog> eventLogs = new HashSet<>();
        rowIterator.next(); // Gets the header row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            EventLog eventLog = extractEventLogFromRow(row);
            eventLogs.add(eventLog);
        }
        return eventLogs;
    }

    private EventLog extractEventLogFromRow(Row row) {
        String datetime = getStringFromCell(row, 0);
        String eventContext = getStringFromCell(row, 1);
        String component = getStringFromCell(row, 2);
        String eventName = getStringFromCell(row, 3);
        String description = getStringFromCell(row, 4);
        return new EventLog().setDatetime(datetime)
                             .setEventContext(eventContext)
                             .setComponent(component)
                             .setEventName(eventName)
                             .setDescription(description);
    }

    private String getStringFromCell(Row row, int cellIndex) {
        return row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK)
                  .getStringCellValue();
    }

    public void close() throws Exception {
        workbook.close();
        fileInputStream.close();
    }
}
