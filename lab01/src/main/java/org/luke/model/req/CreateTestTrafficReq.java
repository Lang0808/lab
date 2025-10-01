package org.luke.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTestTrafficReq {

    /**
     * Total number of transactions to create.
     * Add {@link JsonProperty} because there is a conflict between {@link com.fasterxml.jackson.databind.ObjectMapper} and
     * {@link lombok.Setter} in name of setter method. So objectMapper in Spring MVC cannot fill data to this field.
     * I will research if I have time.
     */
    @JsonProperty("nTransTotal")
    private int nTransTotal;
}
