package com.Controller;

import com.Entity.Parameters;
import com.Entity.Cache;
import com.Entity.Statistics;
import com.Entity.Triangle;
import com.Service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TriangleController {

    private TriangleService service;

    private static final Logger log = Logger.getLogger(TriangleController.class);

    @Autowired
    public TriangleController(TriangleService service) {
        this.service = service;
    }

    private static final String template = "firstSide = %s, SecondSide = %s, thirdSide = %s";

    @RequestMapping("/greeting")
    public ResponseEntity greeting(@ModelAttribute Parameters parameters) {

        try {
            Triangle triangle = service.solveEquetion(parameters.getFirstSide(),
                    parameters.getSecondSide(),parameters.getThirdSide());
            if (triangle == null) {
                log.info("Incorrect parameters:" + parameters.getFirstSide()
                        + " " + parameters.getSecondSide() + " " + parameters.getThirdSide());
                return ResponseEntity.status(400).body("Invalid parameters");

            }
            log.info("HTTP status 200, response :" + triangle.toString());
            return ResponseEntity.ok(triangle);

        } catch (NumberFormatException exception) {
            log.info("HTTP status 200, response :" + parameters.getFirstSide()
                    + " " + parameters.getSecondSide() + " " + parameters.getThirdSide());
            return ResponseEntity.status(400).body("Incorrect parameters");
        }

    }

    @GetMapping("/getAll")
    public List<Cache> getAll(){
        return service.getAll();
    }

    @PostMapping("/triangle/async")
    public ResponseEntity<String> processListAsync(@RequestBody List<Parameters> list){
        return ResponseEntity.ok().body(service.asynchCalculate(list).toString());
    }

    @GetMapping("/async/{id}")
    public List<Triangle> getAsyncrAnswers(@PathVariable("id") String id){
        return service.getAnswerById(id);
    }
}
