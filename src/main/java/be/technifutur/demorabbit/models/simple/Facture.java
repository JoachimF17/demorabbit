package be.technifutur.demorabbit.models.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facture implements Serializable
{
    private String nomClient;
    private double prixTotal;
    private String adresseFacturation;
}
