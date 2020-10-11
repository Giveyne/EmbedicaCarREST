package ru.embedica.ershov.service;

import ru.embedica.ershov.model.Base;
import ru.embedica.ershov.model.Car;
import java.util.List;

public interface CarService {
     Long save(Car car) throws ExceptionInInitializerError;
     List<Car> info(Car car);
     String delete(Long id);
     Base baseInfo();
}
