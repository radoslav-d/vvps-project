package org.tusofia.vvps.model;

public class EventConstants {

    private EventConstants() {
        // prevents instance creation
    }

    public static final String WIKI_COMPONENT = "Wiki";
    public static final String ASSIGNMENT_COMPONENT = "Assignment";
    public static final String SYSTEM_COMPONENT = "System";
    public static final String FILE_COMPONENT = "File";
    public static final String FILE_SUBMISSIONS_COMPONENT = "File submissions";
    public static final String URL_COMPONENT = "URL";

    public static final String WIKI_UPDATED_EVENT = "Wiki page updated";
    // other events can also appear here as constants
    // they are not added currently as they stay out-of-scope
}
