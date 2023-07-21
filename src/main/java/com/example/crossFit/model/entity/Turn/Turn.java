package com.example.crossFit.model.entity.Turn;

public enum Turn {
    PERSONAL_TRAINING(1, "Персональная тренировка", "в течение двух рабочих дней"),
    AEROBIC_TRAINING(2, "Аэробная тренировка", "в течение трех рабочих дней"),
    POWERS_TRAINING(3, null, null),
    DANCE_TRAINING(4, null, null),
    UNKNOWN(-1, "Неизвестный тип параметра", null);

    private final Integer id;
    private final String description;
    private final String serviceTime;

    Turn(Integer id, String description, String serviceTime) {
        this.id = id;
        this.description = description;
        this.serviceTime = serviceTime;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getServiceTime() {
        return serviceTime;
    }

}

