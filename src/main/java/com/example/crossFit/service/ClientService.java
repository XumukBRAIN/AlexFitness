package com.example.crossFit.service;

import com.example.crossFit.exceptions.EntityAlreadyIsRegisteredException;
import com.example.crossFit.exceptions.EntityNotFoundException;
import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.Item;
import com.example.crossFit.model.entity.Orders;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.ItemRepo;
import com.example.crossFit.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private Integer countOrders = 11;

    private final ClientRepo clientRepo;
    private final OrdersRepo ordersRepo;
    private final ItemRepo itemRepo;

    private final static String SUBJECT = "Ваш заказ создан!";
    private final static String TEXT = "! Благодарим за выбор нашего магазина! Вашему заказу присвоен номер: ";

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
    public ClientService(ClientRepo clientRepo, OrdersRepo ordersRepo, ItemRepo itemRepo) {
        this.clientRepo = clientRepo;
        this.ordersRepo = ordersRepo;
        this.itemRepo = itemRepo;
    }

    @Transactional(readOnly = true)
    public Client getVisitor(UUID id) {
        Client client = clientRepo.findById(id);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, "Клиент с таким ID не найден");
        }
        return client;
    }


    @Transactional(readOnly = true)
    public Client findByPhoneNumber(String phoneNumber) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не найден");
        }
        return client;
    }

    @Transactional
    public void registerVisitor(Client client) {
        if (clientRepo.findByPhoneNumber(client.getPhoneNumber()) != null) {
            throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                    "Клиент с таким номером телефона уже зарегистрирован");
        }
        if (clientRepo.findByEmail(client.getEmail()) != null) {
            throw new EntityAlreadyIsRegisteredException(HttpStatus.BAD_REQUEST,
                    "Клиент с такой электронной почтой уже зарегистрирован");
        }
        client.setBalance(BigDecimal.valueOf(0));

        clientRepo.save(client);
    }

    @Transactional
    public void deleteClient(String phoneNumber) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не зарегистрирован в базе");
        }
        clientRepo.delete(client);
    }

    @Transactional
    public void payClient(String phoneNumber, BigDecimal money) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не найден в базе");
        }
        client.setBalance(client.getBalance().add(money));

        clientRepo.save(client);

    }

    @Transactional
    public void createMyOrders(String phoneNumber, Integer id, String title) {
        Client client = clientRepo.findByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Клиент с таким номером телефона не найден");
        }

        Optional<Item> item = itemRepo.findById(id);
        if (!item.isPresent()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND,
                    "Товар не найден");
        }

        Orders o = ordersRepo.findByClientId(client.getId());
        if (o != null) {
            o.setItems(item.get());
            o.setSum(o.getSum().add(item.get().getPrice()));
        } else {

            Orders orders = new Orders();
            orders.setSum(BigDecimal.valueOf(0));
            orders.setClientId(client.getId());
            orders.setTitle(title);
            orders.setPhoneNumber(phoneNumber);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/");
            synchronized (this) {
                orders.setNumber(dateFormat.format(new Date()) + countOrders);
                countOrders++;
            }

            orders.setSum(orders.getSum().add(item.get().getPrice()));
            orders.setItems(item.get());

            ordersRepo.save(orders);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(mail);
            mailMessage.setTo(client.getEmail());
            mailMessage.setSubject(SUBJECT);
            mailMessage.setText(client.getName() + TEXT + orders.getNumber());

            mailSender.send(mailMessage);


        }

    }
}

