package edu.bupt.law.user.controller;

import edu.bupt.law.user.controller.request.RegulationHistoryRequest;
import edu.bupt.law.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @PostMapping("/regulations")
    public ResponseEntity<?> addRegulationHistory(Authentication authentication, @RequestBody RegulationHistoryRequest regulationHistoryRequest){
        return null;
    }


    @GetMapping("/records")
    public ResponseEntity<?> getRecordsHistory(Authentication authentication,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        return null;
    }

    @GetMapping("/regulations")
    public ResponseEntity<?> getRegulationsHistory(Authentication authentication,
                                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        return null;
    }

    @GetMapping("/records/action")
    public ResponseEntity<?> getRecordsHistoryByAction(Authentication authentication,
                                                       @RequestParam(value = "action") String action,
                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        return null;
    }

    @GetMapping("/regulations/action")
    public ResponseEntity<?> getRegulationsHistoryByAction(Authentication authentication,
                                                           @RequestParam(value = "action") String action,
                                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        return null;
    }
}
