package com.training.monitor.dto;

public class OperationDTO <T,S>{
    private T gte;
    private S lte;

    public OperationDTO(T gte, S lte) {
        this.gte = gte;
        this.lte = lte;
    }

    public T getGte() {
        return gte;
    }

    public void setGte(T gte) {
        this.gte = gte;
    }

    public S getLte() {
        return lte;
    }

    public void setLte(S lte) {
        this.lte = lte;
    }
}
