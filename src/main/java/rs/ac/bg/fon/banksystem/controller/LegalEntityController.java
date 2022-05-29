package rs.ac.bg.fon.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.banksystem.communication.Response;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.service.LegalEntityService;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/legalEntities")
public class LegalEntityController {
    @Autowired
    private LegalEntityService legalEntityService;
    public LegalEntityController() {

    }
    @GetMapping
    public ResponseEntity<Response> getAllEmployees() {
        Response response = new Response();
        try {
            List<LegalEntity> entities = legalEntityService.findAll();
            response.setResponseData(entities);
            response.setResponseException(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);


        } catch (Exception ex) {
            response.setResponseData(null);
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
    }

    @CrossOrigin
    @PostMapping("/new")
    public ResponseEntity<Response> createLegalEntity(@RequestBody LegalEntity entity) throws Exception {
        Response response = new Response();
        try {
            LegalEntity save = legalEntityService.save(entity);
            response.setResponseData(save);
            response.setResponseException(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);


        } catch (Exception ex) {
            response.setResponseData(null);
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Response> getEntityById(@PathVariable Long id) {
        Response response = new Response();
        try {
            LegalEntity entity = legalEntityService.getById(id);
            response.setResponseData(entity);
            response.setResponseException(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);


        } catch (Exception ex) {
            response.setResponseData(null);
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }


    }

    // build update employee REST API

    @PutMapping("{id}")
    public ResponseEntity<Response> updateEmployee(@PathVariable long id, @RequestBody LegalEntity entityDetails) {
        Response response=new Response();
        try {
            entityDetails.setId(id);
            LegalEntity updated=legalEntityService.update(entityDetails);
            response.setResponseData(updated);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setResponseException(e);
            response.setResponseData(null);
            return ResponseEntity.ok().body(response);
        }

    }

    // build delete employee REST API

    @DeleteMapping("{id}")
    @CrossOrigin
    public ResponseEntity<Response> deleteEmployee(@PathVariable Long id) {
        Response response=new Response();
        try {
            LegalEntity entity = legalEntityService.getById(id);
            legalEntityService.delete(entity);
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            response.setResponseException(e);
            response.setResponseData(null);
            return ResponseEntity.ok().body(response);
        }


    }

    @GetMapping("/search/{value}")
    @CrossOrigin
    public ResponseEntity<Response> getLegalEntitiesByValue(@PathVariable(name = "value") String searchValue) {
        Response response = new Response();
        try {
            List<LegalEntity> dbEntities = legalEntityService.getByValue(searchValue);
            response.setResponseData(dbEntities);
            response.setResponseException(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);


        } catch (Exception ex) {
            response.setResponseData(new ArrayList() {
            });
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }




    }



}
