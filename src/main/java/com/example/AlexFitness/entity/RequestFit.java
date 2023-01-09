package com.example.AlexFitness.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "request_fit")
public class RequestFit {

    @Id
    private UUID id;
    private String title;
    private LocalDateTime reqData;
    @JoinColumn(name = "sub_id")
    private int subId;
    @JoinColumn(name = "coach_id")
    private int coachId;

    public UUID getId() {
        return id;
    }

    public RequestFit() {
    }

    public RequestFit(UUID id, String title, LocalDateTime reqData, int subId, int coachId) {
        this.id = id;
        this.title = title;
        this.reqData = reqData;
        this.subId = subId;
        this.coachId = coachId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getReqData() {
        return reqData;
    }

    public void setReqData(LocalDateTime reqData) {
        this.reqData = reqData;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
}
