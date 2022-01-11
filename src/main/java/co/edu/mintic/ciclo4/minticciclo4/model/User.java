package co.edu.mintic.ciclo4.minticciclo4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "identification", "name", "birthtDay", "monthBirthtDay", "address",  "cellPhone", "email", "password", "zone", "type"})
public class User {

    @Id
    private Integer id;
    private String identification;
    private String name;
    private Date birthtDay;
    private String monthBirthtDay;
    private String address;
    private String cellPhone;
    private String email;
    private String password;
    private String zone;
    private String type;

    @DBRef(lazy = true)
    @JsonIgnore
    private List<Order> orders;
}
