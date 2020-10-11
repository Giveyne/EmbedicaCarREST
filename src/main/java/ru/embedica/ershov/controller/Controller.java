package ru.embedica.ershov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;
import ru.embedica.ershov.model.Base;
import ru.embedica.ershov.model.Car;
import ru.embedica.ershov.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RestController
@Slf4j
public class Controller {

    @Autowired
    private CarService carService;

    @PostMapping("base/car/save")
    public HttpEntity<String> save(@RequestBody Car car) throws Exception {
        Long id = carService.save(car);
        return id != null ? ResponseEntity
                .ok("Car adding to base with id: " + id) : ResponseEntity.badRequest().body("such a machine already exists ");
    }

    @GetMapping("base/car/info")
    public HttpEntity<List<Car>> getInfo(@RequestBody Car object) {
        List <Car> info = carService.info(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @PostMapping("base/car/{id}/delete")
    public HttpEntity<String> delete(@PathVariable Long id) {

        try {
            String message  = carService.delete(id);
            return message != null ? ResponseEntity.ok(message) : ResponseEntity.notFound().build();
        }
        catch (UnexpectedRollbackException e)
        {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body("Car is't  found!");
        }

    }

    @GetMapping("base/info")
    public HttpEntity<Base> baseInfo(){
        Base baseInfo = carService.baseInfo();
        return baseInfo != null ? ResponseEntity.ok(baseInfo) : ResponseEntity.notFound().build();
    }

}
