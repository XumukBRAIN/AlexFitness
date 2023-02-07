package com.example.crossFit.service;

import com.example.crossFit.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilService {

    private final RequestFitService requestFitService;
    private final ClientRepo clientRepo;
    private static final String SUBJECT = "АКЦИЯ!";
    private static final String TEXT = "Уведомляем Вас об акции Приведи друга!";

    @Autowired
    public UtilService(RequestFitService requestFitService, ClientRepo clientRepo) {
        this.requestFitService = requestFitService;
        this.clientRepo = clientRepo;
    }

    /**
     * Автономный метод для ежедневной рассылки писем по всем почтовым ящикам.
     * fixedDelay 17800000 = рассылка каждые два дня.
     */
    @Transactional
    public void sendToAllAutomaticDaily() {
        clientRepo.findAll().stream().filter(client -> client.getEmail() != null).forEach(client ->
                requestFitService.sendMessage(client.getEmail(), SUBJECT, TEXT)
        );
    }

    /**
     * Метод для единоразовой рассылки писем по всем почтовым ящикам
     *
     * @param title заголовок из json
     * @param text  текст из json
     */
    @Transactional
    public void sendToAll(String title, String text) {
        clientRepo.findAll().stream().filter(client -> client.getEmail() != null).forEach(client ->
                requestFitService.sendMessage(client.getEmail(), title, text)
        );
    }

}

