package com.example.practica.controllers;

import com.example.practica.entities.Laptop;
import com.example.practica.repositories.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private Logger log = LoggerFactory.getLogger(LaptopController.class);

    LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @RequestMapping("/api/v2/read")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @RequestMapping("/api/v2/read/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id){
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        return optionalLaptop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/v2/insert")
    public Laptop insert(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    @PutMapping("/api/v2/update")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            log.warn("Trying to modify a non existent laptop");
            return ResponseEntity.badRequest().build();
        } else{
            Laptop result = laptopRepository.save(laptop);
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/api/v2/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if(laptopRepository.findById(id).isEmpty()){
            log.warn("Laptop with given id do not exist");
            return ResponseEntity.badRequest().build();
        } else{
            laptopRepository.deleteById(id);
            return (ResponseEntity) ResponseEntity.ok();
        }
    }

    @DeleteMapping("/api/v2/delete")
    public ResponseEntity delete(){
        if(laptopRepository.findAll().isEmpty()){
            log.warn("Laptops list is empty");
            return ResponseEntity.badRequest().build();
        } else{
            laptopRepository.deleteAll();
            return (ResponseEntity) ResponseEntity.ok();
        }
    }

}
