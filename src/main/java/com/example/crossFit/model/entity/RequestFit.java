package com.example.crossFit.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_fit")
@Getter
@Setter
@NoArgsConstructor
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
    private String email;

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


    public RequestFit(String title, Integer subId, Integer coachId, LocalDateTime reqDate, String phoneNumber) {
        this.title = title;
        this.subId = subId;
        this.coachId = coachId;
        this.reqDate = reqDate;
        this.phoneNumber = phoneNumber;
    }


}
