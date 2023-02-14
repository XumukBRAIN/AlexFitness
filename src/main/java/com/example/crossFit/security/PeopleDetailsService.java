package com.example.crossFit.security;

import com.example.crossFit.model.entity.Client;
import com.example.crossFit.model.entity.Coach;
import com.example.crossFit.model.entity.Manager;
import com.example.crossFit.repository.ClientRepo;
import com.example.crossFit.repository.CoachRepo;
import com.example.crossFit.repository.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("peopleDetailsService")
public class PeopleDetailsService implements UserDetailsService {

    private final ClientRepo clientRepo;
    private final ManagerRepo managerRepo;
    private final CoachRepo coachRepo;


    @Autowired
    public PeopleDetailsService(ClientRepo clientRepo, ManagerRepo managerRepo, CoachRepo coachRepo) {
        this.clientRepo = clientRepo;
        this.managerRepo = managerRepo;
        this.coachRepo = coachRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepo.findByPhoneNumber(username);
        if (client != null) {
            return new ClientDetails(client);
        }

        Manager manager = managerRepo.findByPhoneNumber(username);
        if (manager != null) {
            return new ManagerDetails(manager);
        }

        Coach coach = coachRepo.findByPhoneNumber(username);
        if (coach != null) {
            return new CoachDetails(coach);
        } else throw new UsernameNotFoundException("Неверный логин!");

    }
}
