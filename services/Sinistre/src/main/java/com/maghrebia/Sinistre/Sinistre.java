package com.maghrebia.Sinistre;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Contract;

import java.util.Date;

@Document(collection = "Sinistre")

@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Sinistre {
    @Id
    private String id;
    private String Description;
    private Date DateSinistre;
    private  Double MontantRembourssement;
    private String status;
    @JsonProperty("Image")
    private String Image;
    @Lob
    @JsonProperty("ImageData")
    private byte[] imageData;
    private String imageBase64;;
    private String type_prop;
}
