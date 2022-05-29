package rs.ac.bg.fon.banksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.banksystem.communication.Response;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.service.CreditBureauService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/bureauReports")
public class CreditBureauController {
    @Autowired
    private CreditBureauService creditBureauService;

    public CreditBureauController() {

    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Response> addBureauReport(@RequestBody CreditBureauReport report) {
        Response response = new Response();
        try {
            creditBureauService.addBureauReport(report);
            response.setResponseException(null);
            response.setResponseData(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            response.setResponseData(null);
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }

    }


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Response> getAllReports() throws Exception {
        Response response = new Response();
        try {
            List<CreditBureauReport> reports = creditBureauService.getAllReports();
            response.setResponseData(reports);
            return ResponseEntity.ok().body(response);

        } catch (Exception ex) {
            response.setResponseData(null);
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteReport(@PathVariable(name = "id") Long reportId) throws Exception {
        Response response = new Response();
        try {
            creditBureauService.deleteReport(reportId);
            return ResponseEntity.ok().body(response);

        } catch (Exception ex) {
            response.setResponseData(null);
            response.setResponseException(ex);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/search/{value}")
    @CrossOrigin
    public ResponseEntity<Response> getReportsByValue(@PathVariable(name = "value") String searchValue) {
        Response response = new Response();
        try {
            List<CreditBureauReport> dbReports = creditBureauService.getByValue(searchValue);
            response.setResponseData(dbReports);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setResponseData(new ArrayList<>());
            response.setResponseException(e);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getEntityById(@PathVariable Long id) {
        Response response = new Response();
        try {
            CreditBureauReport report = creditBureauService.getById(id);
            response.setResponseData(report);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setResponseData(null);
            response.setResponseException(e);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateCreditBureauReport(@PathVariable long id, @RequestBody CreditBureauReport reportDetails) {
        Response response = new Response();
        try {
            reportDetails.setId(id);
            creditBureauService.update(reportDetails);
            response.setResponseData(reportDetails);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.setResponseData(null);
            response.setResponseException(e);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }


    }


}
