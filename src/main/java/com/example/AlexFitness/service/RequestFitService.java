package com.example.AlexFitness.service;


import com.example.AlexFitness.model.entity.Client;
import com.example.AlexFitness.model.entity.RequestFit;
import com.example.AlexFitness.repository.ClientRepo;
import com.example.AlexFitness.repository.RequestFitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestFitService {
    private final RequestFitRepo requestFitRepo;
    private final ClientRepo clientRepo;

    @Value("${fitness.mail.username}")
    private String mail;

    /**
     * Сервис по отправке писем
     */
    private JavaMailSender mailSender;

    @Autowired(required = false)
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Autowired
    public RequestFitService(RequestFitRepo requestFitRepo, ClientRepo clientRepo) {
        this.requestFitRepo = requestFitRepo;
        this.clientRepo = clientRepo;
    }


    public void createRequest(RequestFit requestFit) {
        requestFitRepo.save(requestFit);
    }


    public RequestFit findByPhoneNumber(String phoneNumber) {
        return requestFitRepo.findByPhoneNumber(phoneNumber);
    }


    public List<RequestFit> findNotApprovedRequests() {
        return requestFitRepo.findAllByIsApprovedNull();
    }


    public void rejectRequestFit(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new RuntimeException("Заявка с таким номером телефона не найдена");
        }
        requestFit.setApproved(false);
        requestFitRepo.save(requestFit);

        sendMessage(requestFit.getEmail(), "Отказ", "Ваша заявка отклонена!!!!!!!!");
    }

    @Transactional
    public void approve(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new RuntimeException("Заявка с таким номером телефона не найдена");
        }
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new RuntimeException("Клиент с таким номером телефона не найден");
        }

        requestFit.setApproved(true);
        requestFitRepo.save(requestFit);

        client.setCoach(requestFit.getCoachId());
        client.setSubscriptionId(requestFit.getSubId());
        clientRepo.save(client);

        sendMessage(client.getEmail(), "Одобрено", "Ваша заявка принята!");

    }


    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

}
