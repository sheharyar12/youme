package com.youme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "core-user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoreUser {

    private UserDetails userDetails;
    private Double rating;
    private List<Friends> friends;
    private List<Interactions> interactions;

}
