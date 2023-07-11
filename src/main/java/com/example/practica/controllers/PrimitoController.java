package com.example.practica.controllers;

import com.example.practica.entities.Primito;
import com.example.practica.repositories.PrimitoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PrimitoController {

    private PrimitoRepository primitoRepository;

    public PrimitoController(PrimitoRepository primitoRepository) {
        this.primitoRepository = primitoRepository;
    }

    public PrimitoRepository getPrimitoRepository() {
        return primitoRepository;
    }

    public void setPrimitoRepository(PrimitoRepository primitoRepository) {
        this.primitoRepository = primitoRepository;
    }

    @RequestMapping("/api/v1/primitos")
    public List<Primito> findAll(){
        return primitoRepository.findAll();
    }

    @RequestMapping("/api/v1/primito/{id}")
    public ResponseEntity<Primito> findById(@PathVariable Long id){

        Optional<Primito> primitoOptional = primitoRepository.findById(id);

        return primitoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/api/v1/primitos")
    public Primito create(@RequestBody Primito primito){
        return primitoRepository.save(primito);
    }

    @PutMapping("/api/v1/primito/update/{id}")
    public ResponseEntity<Primito> update(@PathVariable Long id, @RequestBody Primito primitoDetails) {
        Optional<Primito> optionalPrimito = primitoRepository.findById(id);

        if (optionalPrimito.isPresent()) {
            Primito primito = optionalPrimito.get();
            primito.setName(primitoDetails.getName());
            primito.setAge(primitoDetails.getAge());

            Primito updatedPrimito = primitoRepository.save(primito);
            return ResponseEntity.ok(updatedPrimito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping("/hola")
    public String holaMundo(){
        return "Hola mundo kukoooooooo";
    }

    @RequestMapping("/")
    public String getBootstrap(){
        return """
                                
                <!doctype html>
                <html lang="ar" dir="rtl">
                  <head>
                    <!-- Required meta tags -->
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                                
                    <!-- Bootstrap CSS -->
                    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.rtl.min.css" integrity="sha384-PJsj/BTMqILvmcej7ulplguok8ag4xFTPryRq8xevL7eBYSmpXKcbNVuy+P0RMgq" crossorigin="anonymous">
                                
                    <title>MITOTITO</title>
                  </head>
                  <body>
                    <h1>KUKKOOOOOOOOOOOOO</h1>
                    <button class="btn btn-primary"> EL PEPE </button>
                                
                    <!-- Optional JavaScript; choose one of the two! -->
                                
                    <!-- Option 1: Bootstrap Bundle with Popper -->
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
                                
                    <!-- Option 2: Separate Popper and Bootstrap JS -->
                    <!--
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
                    -->
                  </body>
                </html>
                                
                """;
    }

}
