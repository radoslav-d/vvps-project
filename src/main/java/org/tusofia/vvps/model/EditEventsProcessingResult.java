package org.tusofia.vvps.model;

public class EditEventsProcessingResult {

    private double averageEditEventsPerUser;
    private double varianceEditEventsPerUser;
    private double standardDeviationEditEventsPerUser;

    public double getAverageEditEventsPerUser() {
        return averageEditEventsPerUser;
    }

    public EditEventsProcessingResult setAverageEditEventsPerUser(double averageEditEventsPerUser) {
        this.averageEditEventsPerUser = averageEditEventsPerUser;
        return this;
    }

    public double getVarianceEditEventsPerUser() {
        return varianceEditEventsPerUser;
    }

    public EditEventsProcessingResult setVarianceEditEventsPerUser(double varianceEditEventsPerUser) {
        this.varianceEditEventsPerUser = varianceEditEventsPerUser;
        return this;
    }

    public double getStandardDeviationEditEventsPerUser() {
        return standardDeviationEditEventsPerUser;
    }

    public EditEventsProcessingResult setStandardDeviationEditEventsPerUser(double standardDeviationEditEventsPerUser) {
        this.standardDeviationEditEventsPerUser = standardDeviationEditEventsPerUser;
        return this;
    }
}
