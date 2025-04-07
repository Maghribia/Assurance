package  com.maghrebia.Product;

import com.example.assurance.TypeAssurance;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int IdAssurance ;
    @Enumerated(EnumType.STRING)
    @JsonProperty("Type")
    private TypeAssurance Type;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Tarifs")
    private Double Tarifs;


    public TypeAssurance getType() {
        return Type;
    }

    public void setType(TypeAssurance type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getTarifs() {
        return Tarifs;
    }

    public void setTarifs(Double tarifs) {
        Tarifs = tarifs;
    }
}
