package rs.ac.bg.fon.banksystem.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.banksystem.model.CreditBureauReport;
import rs.ac.bg.fon.banksystem.model.LegalEntity;
import rs.ac.bg.fon.banksystem.service.CreditBureauService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/bureauReports")
public class CreditBureauController {
    private CreditBureauService creditBureauService;
    public CreditBureauController(){
        creditBureauService=new CreditBureauService();
    }
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<HttpStatus> addBureauReport(@RequestBody CreditBureauReport report) {
        try {
            creditBureauService.addBureauReport(report);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception ex) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("errorMessage", ex.getMessage());
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).headers(responseHeaders).body(null);

        }

    }


    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<CreditBureauReport>> getAllReports() throws Exception {
        try {
            List<CreditBureauReport> reports= creditBureauService.getAllReports();
            return ResponseEntity.ok().body(reports);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    @CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteReport(@PathVariable(name = "id")   Long reportId) throws Exception {
        try {
            creditBureauService.deleteReport(reportId);
            return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @GetMapping("/search/{value}")
    @CrossOrigin
    public ResponseEntity<List<CreditBureauReport>> getReportsByValue(@PathVariable(name = "value") String searchValue){
        System.out.println("POZVANA METODA");
        try {
            List<CreditBureauReport> dbReports=creditBureauService.getByValue(searchValue);
            HttpHeaders responseHeaders = new HttpHeaders();
            return  ResponseEntity.ok().headers(responseHeaders).body(dbReports);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ArrayList<>());
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<CreditBureauReport> getEntityById(@PathVariable Long id) {
        CreditBureauReport report = creditBureauService.getById(id);
        System.out.println("***************************VRACENI REZULTAT MEDOTE GET BY ID **************************");
        System.out.println(report);
        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.ok().headers(responseHeaders).body(report);
    }

    @PutMapping("{id}")
    public ResponseEntity<CreditBureauReport> updateCreditBureauReport(@PathVariable long id, @RequestBody CreditBureauReport reportDetails) {
        try {
            reportDetails.setId(id);
            creditBureauService.update(reportDetails);
            HttpHeaders responseHeaders = new HttpHeaders();
            return ResponseEntity.ok().headers(responseHeaders).body(reportDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
