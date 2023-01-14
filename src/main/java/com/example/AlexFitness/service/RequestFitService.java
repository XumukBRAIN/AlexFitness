package com.example.AlexFitness.service;


import com.example.AlexFitness.model.entity.Client;
import com.example.AlexFitness.model.entity.RequestFit;
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
    private final ClientService clientService;

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
    public RequestFitService(RequestFitRepo requestFitRepo, ClientService clientService) {
        this.requestFitRepo = requestFitRepo;
        this.clientService = clientService;
    }

    @Transactional
    public void createRequest(RequestFit requestFit) {
        requestFitRepo.save(requestFit);
    }

    @Transactional(readOnly = true)
    public RequestFit findByPhoneNumber(String phoneNumber) {
        return requestFitRepo.findByPhoneNumber(phoneNumber);
    }

    @Transactional(readOnly = true)
    public List<RequestFit> findNotApprovedRequests() {
        return requestFitRepo.findAllByIsApprovedNull();
    }


    @Transactional
    public void rejectRequestFit(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new RuntimeException("Заявка с таким номером телефона не найдена");
        }
        requestFit.setApproved(false);

        sendMessage("andrew_1375@mail.ru", "Отказ", "Ваша заявка отклонена!!!!!!!!");
    }

    @Transactional
    public void approve(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new RuntimeException("Заявка с таким номером телефона не найдена");
        }
        Client client1 = clientService.findByPhoneNumber(phoneNumber);
        if (client1 == null) {
            throw new RuntimeException("Клиент с таким номером телефона не найден");
        }
        requestFit.setApproved(true);
        client1.setCoach(requestFit.getCoachId());
        client1.setSubscriptionId(requestFit.getSubId());
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
