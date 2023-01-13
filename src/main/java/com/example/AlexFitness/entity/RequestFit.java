package com.example.AlexFitness.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "request_fit")
public class RequestFit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "request_fit_id_seq", sequenceName = "REQUEST_FIT_ID_SEQ", allocationSize = 1)
    private Long id;
    private String title;

    @Column(name = "req_date")
    @CreationTimestamp
    private LocalDateTime reqDate;
    @JoinColumn(name = "sub_id")
    private Integer subId;
    @JoinColumn(name = "coach_id")
    private Integer coachId;
    private String phoneNumber;
    @Column(name = "is_approved")
    private Boolean isApproved;

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public RequestFit() {
    }

    public RequestFit(String title, int subId, int coachId, LocalDateTime reqDate) {
        this.title = title;
        this.subId = subId;
        this.coachId = coachId;
        this.reqDate = reqDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public LocalDateTime getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDateTime reqDate) {
        this.reqDate = reqDate;
    }
}
