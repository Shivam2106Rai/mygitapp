package com.suunya.subscription.controller;

import com.suunya.subscription.entity.User;
import com.suunya.subscription.service.GetAllDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/v1")
@Slf4j
@Validated
@EnableOAuth2Sso
public class UserController {

    @Autowired
    GetAllDetails getAllDetails;

    @GetMapping("/login_git")
    public User message(Principal principal) throws IOException {

        log.info("user{}",principal.getName());
        return  getAllDetails.getAllDetails(principal.getName());

    }

    @GetMapping("/get_info/{id}")
    public User getInfo(@PathVariable (value = "id")String id) throws IOException {

        log.info("user for search {}",id);
        return  getAllDetails.searchUserDetail(id);

    }

    @GetMapping("/delete_search_item/{item}")
    public void delete(@PathVariable (value = "item")String item) throws IOException {

        log.info("item for delete  {}",item);
          getAllDetails.deleteSearchItem(item);

    }




}
