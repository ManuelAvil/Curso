package com.example.demo.controllers;

import com.example.demo.models.Tarea;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private Repository respository;

    @GetMapping()
    public String index(){
        return "CONECTADO";
    }

    @GetMapping("tareas")
    public List<Tarea> getTareas(){
        return respository.findAll();
    }

    @PostMapping("crear")
    public ResponseEntity<String> post(@RequestBody Tarea tarea){
        try {
            respository.save(tarea);
            return new ResponseEntity<>("Tarea guardada correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar la tarea", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Tarea tarea){
        try {
            Optional<Tarea> optionalTarea = respository.findById(id);
            if (optionalTarea.isPresent()) {
                Tarea updateTarea = optionalTarea.get();
                updateTarea.setDescripcion(tarea.getDescripcion());
                updateTarea.setEstatus(tarea.getEstatus());
                respository.save(updateTarea);
                return new ResponseEntity<>("Tarea editada correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tarea no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la tarea", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            Optional<Tarea> optionalTarea = respository.findById(id);
            if (optionalTarea.isPresent()) {
                Tarea deleteTarea = optionalTarea.get();
                respository.delete(deleteTarea);
                return new ResponseEntity<>("Tarea eliminada correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tarea no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la tarea", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
