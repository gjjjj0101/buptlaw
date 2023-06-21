package edu.bupt.law.regulations.controller;

import edu.bupt.law.common.utils.UUidUtils;
import edu.bupt.law.regulations.controller.request.MixedQuery;
import edu.bupt.law.regulations.service.RegulationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;

@RestController
@RequestMapping("/regulation")
public class RegulationController {

    @Autowired
    RegulationService regulationService;

    @GetMapping("/title")
    public ResponseEntity<?> getRegulationByTitle(){
        return null;
    }

    @GetMapping("/level")
    public ResponseEntity<?> getRegulationByLevel(@RequestParam String level,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(regulationService.findByLevel(level, PageRequest.of(page - 1, size)));
    }


    @GetMapping("/office")
    public ResponseEntity<?> getRegulationByOffice(@RequestParam String office,
                                                   @RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(regulationService.findByOffice(office, PageRequest.of(page - 1, size)));
    }

    @GetMapping("/id")
    public ResponseEntity<?> getRegulationById(@RequestParam String id){
        return ResponseEntity.ok(regulationService.findById(id));
    }

    @PostMapping("/mixed")
    public ResponseEntity<?> getRegulationByMixed(@RequestBody MixedQuery query,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(regulationService.mixedSearch(query, PageRequest.of(page - 1, size)));
    }

}
