package com.youme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youme.model.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public UserDetails loginUser(Map<String, String> details) {
        String userJson;
        UserDetails userDetails = null;
        try {
            userJson = objectMapper.writeValueAsString(details);
            userDetails = objectMapper.readValue(userJson, UserDetails.class);
        } catch (JsonProcessingException e) {
            log.error("Not able to get details. " + e.getLocalizedMessage());
        } catch (IOException e) {
            log.error("UserDetails created I/O exception. " + e.getLocalizedMessage());
        }
        return userDetails;
    }
}
