package com.youme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youme.model.CoreUser;
import com.youme.model.TotalRateCount;
import com.youme.model.UserDetails;
import com.youme.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@Slf4j
public class UserService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    public UserDetails giveGoogleUserDetails(Map<String, String> details) {
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

    private List<TotalRateCount> createTotalRatingCount(){
        List<TotalRateCount> totalRateCounts = new ArrayList<>();
        IntStream.range(0,11).forEach(value -> {
            TotalRateCount totalRateCount = TotalRateCount.builder()
                    .count(0)
                    .build();
            totalRateCounts.add(totalRateCount);
        });
        return totalRateCounts;
    }

    private CoreUser createCoreUser(UserDetails userDetails){

        CoreUser coreUser = CoreUser.builder()
                .userDetails(userDetails)
                .rating(0.0)
                .friends(new ArrayList<>())
                .interactions(new ArrayList<>())
                .totalRatingCounts(createTotalRatingCount())
                .build();
        return userRepository.save(coreUser);
    }

    private Optional<CoreUser> findCoreUser(UserDetails details){
        return userRepository.findByUserDetails_Email(details.getEmail());
    }

    public Optional<CoreUser> coreUserLogin(UserDetails userDetails){
        Optional<CoreUser> coreUser = findCoreUser(userDetails);
        if(coreUser.isPresent()){
            return coreUser;
        }else{
            coreUser = Optional.ofNullable(createCoreUser(userDetails));
        }
        return coreUser;
    }
}
