package ru.embedica.ershov.service;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import ru.embedica.ershov.model.Base;
import ru.embedica.ershov.model.BaseStatisticBrand;
import ru.embedica.ershov.model.BaseStatisticColor;
import ru.embedica.ershov.model.Car;
import ru.embedica.ershov.repository.CarRepository;
import ru.embedica.ershov.repository.CarSpec;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
@Slf4j
public class CarServiceImp implements CarService{

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private EntityManager em;

    @CachePut(value = "saveCar")
    public Long save(Car car){
            log.info("Try save car by car number", car.getCarNumber());
            if(carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).isEmpty()) {
                log.info("Save car by car number", car.getCarNumber());
                return carRepository.save(car).getId();
            }
        log.info("Not save car by car number", car.getCarNumber());
        return car.getId();
    }

    @Cacheable(value = "carInfo")
    public List<Car> info(Car car){

        List <Car> cars = carRepository.findAll(Specification
                .where(CarSpec.carId(car.getId()))
                .or(CarSpec.carBrand(car.getBrand()))
                .or(CarSpec.carColor(car.getColor()))
                .or(CarSpec.carNumber(car.getCarNumber()))
                .or(CarSpec.carYearOfIssue(car.getYearOfIssue()))
                );
        log.info("Get info by param");
        return cars;
    }

    @Transactional
    @CacheEvict("deleteCar")
    public String delete(Long id) throws UnexpectedRollbackException{
        try {
            carRepository.deleteById(id);
        }
        catch (UnexpectedRollbackException e){
            log.info(e.getMessage() + "Object not found! UnexpectedRollbackException");
            return "Object not found!";
        }
        catch (Exception e){
            log.info(e.getMessage() + "Object not found!");
            return "Object not found!";
        }
        log.info("Car with id = " + id +  " is delete");
        return "Car with id = " + id +  " is delete";
    }

    @Override
    @Transactional
    @Cacheable(value = "baseStatistic")
    public Base baseInfo() {
        log.info("Get baseInfo");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Base> cr = cb.createQuery(Base.class);
        Root<Car> root = cr.from(Car.class);
        cr.multiselect(cb.count(root),
                cb.max(root.get("dateAdded")),
                cb.min(root.get("dateAdded"))
                );
        TypedQuery<Base> query =  em.createQuery(cr);
        List<Base> bases = query.getResultList();

        cb = em.getCriteriaBuilder();
        CriteriaQuery<BaseStatisticColor> queryObj = cb.createQuery(BaseStatisticColor.class);
        root = queryObj.from(Car.class);
        queryObj.groupBy(root.get("color"));
        queryObj.multiselect(cb.count(root), root.get("color"));
        TypedQuery<BaseStatisticColor> queryStatistic =  em.createQuery(queryObj);
        List<BaseStatisticColor> baseStatisticColors = queryStatistic.getResultList();
        bases.get(0).setCountColor(baseStatisticColors);

        cb = em.getCriteriaBuilder();
        CriteriaQuery<BaseStatisticBrand> queryBrand = cb.createQuery(BaseStatisticBrand.class);
        root = queryBrand.from(Car.class);
        queryBrand.groupBy(root.get("brand"));
        queryBrand.multiselect(cb.count(root), root.get("brand"));
        TypedQuery<BaseStatisticBrand> queryStatisticBrand =  em.createQuery(queryBrand);
        List<BaseStatisticBrand> baseStatisticBrands = queryStatisticBrand.getResultList();
        bases.get(0).setCountBrand(baseStatisticBrands);

        return  bases.get(0);
    }


}
