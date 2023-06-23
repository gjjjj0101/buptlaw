package edu.bupt.law.user.controller;

import edu.bupt.law.user.controller.request.AddRecordRequest;
import edu.bupt.law.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {
    @PostMapping("/records")
    public ResponseEntity<?> addRecord(Authentication authentication, @RequestBody AddRecordRequest addRecordRequest){
        return null;
    }

    @GetMapping("/records")
    public ResponseEntity<?> getRecords(Authentication authentication,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size){
        return null;
    }

    @GetMapping("/record")
    public ResponseEntity<?> getRecordsByAction(Authentication authentication,
                                                @RequestParam(value = "id") Long id){
        return ResponseEntity.ok(authentication.getName());
    }

    @PutMapping("/records")
    public ResponseEntity<?> updateRecord(Authentication authentication,
                                          @RequestParam(value = "id") Long id,
                                          @RequestBody AddRecordRequest addRecordRequest){
        return null;
    }

    @DeleteMapping("/records")
    public ResponseEntity<?> deleteRecord(Authentication authentication,
                                          @RequestParam(value = "id") Long id){
        return null;
    }

}
