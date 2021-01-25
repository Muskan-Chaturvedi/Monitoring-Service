package com.training.monitor.commons;

public enum ReportTypeEnum {

    CSV("csv"),
    XLSX("xlsx");

    private final String value;

    ReportTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(ReportTypeEnum reportTypeEnum){
        return this.getValue().equals(reportTypeEnum.getValue());
    }
}