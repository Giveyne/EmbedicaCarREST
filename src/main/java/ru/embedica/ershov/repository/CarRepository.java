package ru.embedica.ershov.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.embedica.ershov.model.Car;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface CarRepository extends CrudRepository<Car, Long>, JpaSpecificationExecutor<Car>{
}
