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

import java.util.Optional;

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
        Optional<Client> clientOptional = clientRepo.findByPhoneNumber(username);
        if (clientOptional.isPresent()) {
            return new ClientDetails(clientOptional.get());
        }

        Optional<Manager> managerOptional = managerRepo.findByPhoneNumber(username);
        if (managerOptional.isPresent()) {
            return new ManagerDetails(managerOptional.get());
        }

        Optional<Coach> coachOptional = coachRepo.findByPhoneNumber(username);
        if (coachOptional.isPresent()) {
            return new CoachDetails(coachOptional.get());
        } else throw new UsernameNotFoundException("Неверный логин!");

    }
}
