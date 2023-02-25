package com.example.crossFit.service;


import com.example.crossFit.exceptions.ResourceAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.ResourceNotFoundException;
import com.example.crossFit.model.entity.Accountant;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.RequestFit;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.RequestFitRepo;
import com.example.crossFit.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public SuccessResponse createRequest(RequestFit requestFit) {

        Optional<RequestFit> requestFitOptional = requestFitRepo.findByPhoneNumber(requestFit.getPhoneNumber());
        if (requestFitOptional.isPresent()) {
            throw new ResourceAlreadyIsRegisteredException("Заявка с указанным номером телефона "
                    + requestFit.getPhoneNumber() + " уже была зарегистрирована!");
        }
        requestFitRepo.save(requestFit);

        return new SuccessResponse("Заявка успешно зарегистрирована!", HttpStatus.OK.value());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = true)
    public RequestFit findByPhoneNumber(String phoneNumber) {
        return requestFitRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(new ResourceNotFoundException("Заявка с указанным номером телефона: " +
                        phoneNumber + " не найдена!"));
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
    public SuccessResponse deleteRequestFit(String phoneNumber) {

        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(new ResourceNotFoundException("Заявка с указанным номером телефона: " +
                        phoneNumber + " не найдена!"));

        requestFitRepo.delete(requestFit);

        return new SuccessResponse("Заявка удалена!", HttpStatus.OK.value());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse rejectRequestFit(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(new ResourceNotFoundException("Заявка с указанным номером телефона: "
                        + phoneNumber + " не найдена!"));

        requestFit.setApproved(false);

        requestFitRepo.save(requestFit);

        sendMessage(requestFit.getEmail(), "Отказ", "К сожалению, Ваша заявка отклонена!");

        return new SuccessResponse("Заявка обработана!", HttpStatus.OK.value());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse approve(String phoneNumber) {
        RequestFit requestFit = requestFitRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(new ResourceNotFoundException("Клиент с указанным номером телефона: "
                        + phoneNumber + " не зарегистрирован!"));

        Client client = clientRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(new ResourceNotFoundException("Клиент с указанным номером телефона: "
                        + phoneNumber + " не зарегистрирован!"));

        requestFit.setApproved(true);
        requestFitRepo.save(requestFit);

        client.setCoach(requestFit.getCoachId());
        client.setSubscriptionId(requestFit.getSubId());
        clientRepo.save(client);

        sendMessage(client.getEmail(), "Одобрено", "Одобрено! Ваша заявка ждет оплаты");

        return new SuccessResponse("Заявка успешно обработана!", HttpStatus.OK.value());

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public SuccessResponse subscriptionPayment(BigDecimal money, String email) {

        Client client = clientRepo.findByEmail(email).orElseThrow(new ResourceNotFoundException("Клиент с указанной электронной почтой :"
                + email + " не зарегистрирован!"));

        client.setBalance(client.getBalance().subtract(money));
        clientRepo.save(client);

        Accountant accountant = accountantService.findByName(NAME);
        accountant.setBalance(accountant.getBalance().add(money));
        accountantService.save(accountant);

        return new SuccessResponse("Оплата прошла успешно!", HttpStatus.OK.value());
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
