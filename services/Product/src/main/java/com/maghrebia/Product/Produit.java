package  com.maghrebia.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @JsonProperty("type")
    private String type;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Tarifs")
    private Double Tarifs;
    @JsonProperty("Image")
    private String Image;
    @Lob
    @JsonProperty("ImageData")
    private byte[] imageData;

    @Column(columnDefinition = "LONGTEXT")
    private String imageBase64;
    private String qrCodeUrl;
    private String typeUrl;
    // Nouveaux attributs pour le ML
    @JsonProperty("NombreSinistres")
    private int nombreSinistres;

    @JsonProperty("AgeClient")
    private int ageClient;

    @JsonProperty("DureeContrat")
    private int dureeContrat;

    @JsonProperty("PrimeBase")
    private double primeBase;

    @JsonProperty("Localisation")
    private String localisation;


    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

}
