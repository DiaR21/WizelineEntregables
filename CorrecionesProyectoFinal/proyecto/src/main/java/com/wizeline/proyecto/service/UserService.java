package com.wizeline.proyecto.service;

import com.wizeline.proyecto.model.UserRT;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${sping.external.service.base-url}")
    private String basePath;

    @Autowired
    private final RestTemplate restTemplate;

    public List<UserRT> getUsers(){
        UserRT[] response = restTemplate.getForObject(basePath+"/users", UserRT[].class);
        return Arrays.asList(response);
    }

    public void saveUser(UserRT user){
        restTemplate.postForObject(basePath+"/users", user, UserRT.class);
    }

    public void updateUser(Integer id, UserRT user){
        restTemplate.put(basePath+"/users/"+id, user);
    }

    public void deleteUser(Integer id){
        restTemplate.delete(basePath+"/users/"+id);
    }
}
