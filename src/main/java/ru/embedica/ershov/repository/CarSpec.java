package ru.embedica.ershov.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.embedica.ershov.model.Car;



@Component
public class CarSpec {

    public static Specification<Car> carNumber(String carNumber) {
        return (Specification<Car>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("carNumber"), carNumber);
    }

    public static Specification<Car> carBrand(String brand) {
        return (Specification<Car>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("brand"), brand);
    }

    public static Specification<Car> carColor(String color) {
        return (Specification<Car>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Car> carId(Long id) {
        return (Specification<Car>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Car> carYearOfIssue(Short yearOfIssue) {
        return (Specification<Car>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("yearOfIssue"), yearOfIssue);
    }

}
