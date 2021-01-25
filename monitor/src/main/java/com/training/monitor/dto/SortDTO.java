package com.training.monitor.dto;

import com.training.monitor.commons.SortOrder;

public class SortDTO <T> extends PageDTO {
    private T sortBy;
    private SortOrder sortOrder;

    public SortDTO(T sortBy) {
        this.sortBy = sortBy;
        this.sortOrder = SortOrder.ASC;
    }

    public T getSortBy() {
        return sortBy;
    }

    public void setSortBy(T sortBy) {
        this.sortBy = sortBy;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
