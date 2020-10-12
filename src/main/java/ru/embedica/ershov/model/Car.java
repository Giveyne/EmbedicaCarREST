package ru.embedica.ershov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="[ETOPAHKXCBM]{1}\\d{3}[ETOPAHKXCBM]{2}(\\d{3}|\\d{2})",
            message="Wrong car number!")
    private String carNumber;
    private String brand;
    private String color;

    @Column(name = "year_of_issue")
    private Short yearOfIssue;
    private Date dateAdded;


}
