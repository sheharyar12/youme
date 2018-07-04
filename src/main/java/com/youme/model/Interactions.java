package com.youme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.BsonDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Interactions {

    private String email;
    private String rating;
    private BsonDateTime date;

}
