package ru.embedica.ershov;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.embedica.ershov.controller.Controller;
import ru.embedica.ershov.model.Car;
import ru.embedica.ershov.repository.CarRepository;
import ru.embedica.ershov.repository.CarSpec;
import ru.embedica.ershov.service.CarServiceImp;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTaskApplicationTests {
    @Autowired
    private Controller controller;
    private static Car car = new Car();
    private final static Short yearOfIssue = 2015;
    @Autowired
    private CarServiceImp carServiceImp;
    private static Long id = 1L;
    private static Long saveId;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    EntityManager em;
    @Before
    public void setData() {

        car.setCarNumber("T224AP750");
        car.setBrand("Lada");
        car.setColor("red");
        car.setYearOfIssue(yearOfIssue);
    }

    @Test
    public void findByCarNumber(){
        try {
            carRepository.delete(carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get());
        }
        catch (RuntimeException e){

        }
        boolean actual = carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).isEmpty();
        assertEquals(true, actual);
    }

    @Test
    public void saveCar(){
        try {
            carRepository.delete(carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get());
        }
        catch (RuntimeException e){

        }

        Car saveCar = carRepository.save(car);
        car = carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get();
        assertEquals(car, saveCar);
    }

    @Test
    public void saveCarServiceImp(){
        try {
            carRepository.delete(carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get());
        }
        catch (RuntimeException e){

        }

        saveId = carServiceImp.save(car);
        id = carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get().getId();


       assertEquals(id, saveId);

    }

    @Test
    public void saveController(){
        try {
            carRepository.delete(carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get());
        }
        catch (RuntimeException e){

        }
        System.out.println(car.getId());
        HttpEntity<String> actual = controller.save(car);
        id = carRepository.findOne(CarSpec.carNumber(car.getCarNumber())).get().getId();
        HttpEntity<String> expected = ResponseEntity.ok("Car adding to base with id: " + id);
        System.out.println(car.getId());

        assertEquals(expected, actual);

    }

}
