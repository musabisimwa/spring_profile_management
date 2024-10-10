package com.example.profile_management.services;

import com.example.profile_management.entity.User;
import com.example.profile_management.exceptions.ResourceNotFoundException;
import com.example.profile_management.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServices implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Long saveUser(User user) {
        try {
            Long id = userRepository.save(user).getId();
            log.info("saved user {}",id);
            return id;
        }

        catch (Exception e){
            log.error("db insertion error {}",e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<User> fetchAllUsers() {
        try{
            List<User> users = userRepository.findAll();
            log.info("got {} records",users.size());
            return  users;
        }catch (Exception e){
            log.error("db select all error {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(User user, Long id) {
        try{
            User dbUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user id does not exist"));
                // comparing the new object with the one in the db
             if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())){dbUser.setFirstName(user.getFirstName());}
             if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())){dbUser.setLastName(user.getLastName());}
             if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())){dbUser.setEmail(user.getEmail());}
             if (Objects.nonNull(user.getPhoneNumber()) && !"".equalsIgnoreCase(user.getPhoneNumber())){dbUser.setPhoneNumber(user.getPhoneNumber());}
             //allowing username change
            if (Objects.nonNull(user.getUserName()) && !"".equalsIgnoreCase(user.getUserName())){dbUser.setUserName(user.getUserName());}
             userRepository.save(dbUser);
             return dbUser;



        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        catch (Exception e){
            log.error("db update error {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User fetch(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
            log.info("got user {}",user);

            return user;
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        catch (Exception e){
            log.error("db SELECT error {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            // delete users that actually exist
            userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));
            userRepository.deleteById(id);

            log.info("deleted user {}",id);
        }
        catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        catch (Exception e){
            log.error("db DELETE error {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
