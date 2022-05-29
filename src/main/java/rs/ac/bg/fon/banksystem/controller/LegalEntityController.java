package rs.ac.bg.fon.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.service.LegalEntityService;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/legalEntities")
public class LegalEntityController {
    @Autowired
    private LegalEntityService legalEntityService;
    public LegalEntityController() {

    }
    @GetMapping
    public List<LegalEntity> getAllEmployees() {
        return legalEntityService.findAll();
    }

    @CrossOrigin
    @PostMapping("/new")
    public ResponseEntity<LegalEntity> createLegalEntity(@RequestBody LegalEntity entity) throws Exception {

        try {
            LegalEntity save = legalEntityService.save(entity);
            return ResponseEntity.status(HttpStatus.OK).body(save);


        } catch (Exception ex) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("errorMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).headers(responseHeaders).body(null);
        }


    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<LegalEntity> getEntityById(@PathVariable Long id) {
        LegalEntity entity = legalEntityService.getById(id);
        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok().headers(responseHeaders).body(entity);
    }

    // build update employee REST API

    @PutMapping("{id}")
    public ResponseEntity<LegalEntity> updateEmployee(@PathVariable long id, @RequestBody LegalEntity entityDetails) {
        try {
            entityDetails.setId(id);
            legalEntityService.update(entityDetails);
            HttpHeaders responseHeaders = new HttpHeaders();
            return ResponseEntity.ok().headers(responseHeaders).body(entityDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    // build delete employee REST API

    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        try {
            LegalEntity entity = legalEntityService.getById(id);
            legalEntityService.delete(entity);
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }


    }

    @GetMapping("/search/{value}")
    @CrossOrigin
    public ResponseEntity<List<LegalEntity>> getLegalEntitiesByValue(@PathVariable(name = "value") String searchValue) {
        try {
            List<LegalEntity> dbEntities = legalEntityService.getByValue(searchValue);
            HttpHeaders responseHeaders = new HttpHeaders();
            return ResponseEntity.ok().headers(responseHeaders).body(dbEntities);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ArrayList<>());
        }

    }

}
