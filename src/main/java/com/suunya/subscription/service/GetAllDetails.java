package com.suunya.subscription.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suunya.subscription.entity.SearchItem;
import com.suunya.subscription.entity.User;
import com.suunya.subscription.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class GetAllDetails {
    @Autowired
    UserRepository userRepository;

    public User getAllDetails (String profileName) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.github.com/users/" +profileName, String.class);
        log.info("profile value",response);
       // JSONObject jsonObject = new JSONObject(response);
        User user = new ObjectMapper().readValue(response, User.class);
        log.info("profile value",user.toString());
        return user;

    }

    public User searchUserDetail (String profileName) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.github.com/users/" +profileName, String.class);
        log.info("profile value",response);
        // JSONObject jsonObject = new JSONObject(response);
        User user = new ObjectMapper().readValue(response, User.class);
        log.info("profile value",user.toString());
        SearchItem searchItem = SearchItem.builder().userId(user.getId()).
                lastSearchedTime(LocalDateTime.now()).searchText(profileName).build();
        userRepository.save(searchItem);
        log.info("data saved successfully");
        return user;

    }

    public void deleteSearchItem(String profileName) {

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.github.com/users/" +profileName, String.class);
        log.info("profile value",response);
        userRepository.deleteById(profileName);
        log.info("data deleted successfully");
    }
}

