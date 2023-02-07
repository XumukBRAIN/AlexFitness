package com.example.crossFit.service;


import com.example.crossFit.exceptions.ResourceAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Accountant;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.RequestFit;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.RequestFitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public String createRequest(RequestFit requestFit) {
        RequestFit requestFit1 = requestFitRepo.findByPhoneNumber(requestFit.getPhoneNumber());
        if (requestFit1 != null) {
            throw new ResourceAlreadyIsRegisteredException("Заявка с указанным номером телефона "
                    + requestFit.getPhoneNumber() + " уже была зарегистрирована!");
        }
        requestFitRepo.save(requestFit);

        return "Заявка успешно зарегистрирована!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public RequestFit findByPhoneNumber(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new ResourceNotFoundException("Заявка с указанным номером телефона:" + phoneNumber
                    + " не найдена!");
        }
        return requestFit;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public List<RequestFit> showAllRequestFitIsNotApprove() {
        List<RequestFit> requestFitsList = requestFitRepo.findAllByIsApprovedNull();
        if (requestFitsList.isEmpty()) {
            throw new ResourceNotFoundException("Новых заявок на абонемент не найдено!");
        }
        return requestFitsList;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public List<RequestFit> findAllRequestFit() {
        if (requestFitRepo.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Заявок не найдено!");
        }
        return requestFitRepo.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public String deleteRequestFit(String phoneNumber) {
        if (requestFitRepo.findByPhoneNumber(phoneNumber) == null) {
            throw new ResourceNotFoundException("Заявка с указанным номером телефона: " + phoneNumber
                    + " не зарегистрирована!");
        }
        requestFitRepo.deleteByPhoneNumber(phoneNumber);

        return "Заявка успешно удалена";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public String rejectRequestFit(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new ResourceNotFoundException("Заявка с указанным номером телефона: "
                    + phoneNumber + " не найдена!");
        }
        requestFit.setApproved(false);

        requestFitRepo.save(requestFit);

        sendMessage(requestFit.getEmail(), "Отказ", "К сожалению, Ваша заявка отклонена!");

        return "Заявка успешно обработана!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public String approve(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber);
        if (requestFit == null) {
            throw new ResourceNotFoundException("Заявка с указанным номером телефона: "
                    + phoneNumber + " не найдена!");
        }
        Client client = clientRepo.findByPhoneNumber(phoneNumber);

        if (client == null) {
            throw new ResourceNotFoundException("Клиент с указанным номером телефона: "
                    + phoneNumber + " не зарегистрирован!");
        }

        requestFit.setApproved(true);
        requestFitRepo.save(requestFit);

        client.setCoach(requestFit.getCoachId());
        client.setSubscriptionId(requestFit.getSubId());
        clientRepo.save(client);

        sendMessage(client.getEmail(), "Одобрено", "Одобрено! Ваша заявка ждет оплаты");

        return "Заявка успешно обработана!";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public String subscriptionPayment(BigDecimal money, String email) {
        Client client = clientRepo.findByEmail(email);
        if (client == null) {
            throw new ResourceNotFoundException("Клиент с указанной электронной почтой: "
                    + email + " не зарегистрирован!");
        }
        client.setBalance(client.getBalance().subtract(money));
        clientRepo.save(client);

        Accountant accountant = accountantService.findByName(NAME);
        accountant.setBalance(accountant.getBalance().add(money));
        accountantService.save(accountant);

        return "Оплата прошла успешно!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void sendMessage(String to, String subject, String text) {
        if (mailSender == null) {
            return;
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }


}
