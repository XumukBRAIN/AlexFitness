package com.example.crossFit.service;


import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Accountant;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.RequestFit;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.RequestFitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RequestFitService {
    private final RequestFitRepo requestFitRepo;
    private final ClientRepo clientRepo;
    private final AccountantService accountantService;
    private static final String NAME = "Бухгалтерия";

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
    public RequestFitService(RequestFitRepo requestFitRepo, ClientRepo clientRepo, AccountantService accountantService) {
        this.requestFitRepo = requestFitRepo;
        this.clientRepo = clientRepo;
        this.accountantService = accountantService;
    }


    public void createRequest(RequestFit requestFit) {
        requestFitRepo.save(requestFit);
    }

    @Transactional(readOnly = true)
    public RequestFit findByPhoneNumber(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заявка с указанным номером телефона не найдена");
        }
        return requestFit;
    }

    @Transactional(readOnly = true)
    public List<RequestFit> showAllRequestFitIsNotApprove() {
        List<RequestFit> requestFitsList = requestFitRepo.findAllByIsApprovedNull();
        if (requestFitsList.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Новых заявок на абонемент не найдено");
        }
        return requestFitsList;
    }

    @Transactional(readOnly = true)
    public List<RequestFit> findAllRequestFit() {
        return requestFitRepo.findAll();
    }

    @Transactional
    public void deleteRequestFit(String phoneNumber) {
        findByPhoneNumber(phoneNumber);
        requestFitRepo.deleteByPhoneNumber(phoneNumber);
    }

    @Transactional
    public void rejectRequestFit(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заявка с указанным номером телефона не найдена");
        }
        requestFit.setApproved(false);

        requestFitRepo.save(requestFit);

        sendMessage(requestFit.getEmail(), "Отказ", "К сожалению, Ваша заявка отклонена!");
    }

    @Transactional
    public void approve(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Заявка с указанным номером телефона не найдена");
        }
        Client client = clientRepo.findByPhoneNumber(phoneNumber);

        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с указанным номером телефона не найден в базе");
        }

        requestFit.setApproved(true);
        requestFitRepo.save(requestFit);

        client.setCoach(requestFit.getCoachId());
        client.setSubscriptionId(requestFit.getSubId());
        clientRepo.save(client);

        sendMessage(client.getEmail(), "Одобрено", "Одобрено! Ваша заявка ждет оплаты");

    }

    @Transactional
    public void subscriptionPayment(BigDecimal money, String email) {
        Client client = clientRepo.findByEmail(email);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с указанной электронной почтой не найден в базе");
        }
        client.setBalance(client.getBalance().subtract(money));
        clientRepo.save(client);

        Accountant accountant = accountantService.findByName(NAME);
        accountant.setBalance(accountant.getBalance().add(money));
        accountantService.save(accountant);
    }

    @Transactional
    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }


}
