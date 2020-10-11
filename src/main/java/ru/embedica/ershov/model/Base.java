package ru.embedica.ershov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Base {

    private Long countCars;
    private Date lastEntry;
    private Date firstEntry;
    private List<BaseStatisticColor> countColor;
    private List<BaseStatisticBrand> countBrand;

    public Base(Long countCars, Date lastEntry, Date firstEntry) {
        this.countCars = countCars;
        this.lastEntry = lastEntry;
        this.firstEntry = firstEntry;
    }

}
