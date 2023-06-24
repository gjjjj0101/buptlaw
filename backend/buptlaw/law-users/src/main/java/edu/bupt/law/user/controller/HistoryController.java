package edu.bupt.law.user.controller;

import edu.bupt.law.user.controller.request.RegulationHistoryRequest;
import edu.bupt.law.user.dto.RecordHistoryMapper;
import edu.bupt.law.user.model.User;
import edu.bupt.law.user.model.history.RecordHistory;
import edu.bupt.law.user.model.history.RegulationHistory;
import edu.bupt.law.user.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    RecordHistoryMapper recordHistoryMapper;

    @PostMapping("/regulations")
    public ResponseEntity<?> addRegulationHistory(Authentication authentication, @RequestBody RegulationHistoryRequest regulationHistoryRequest){
        RegulationHistory regulationHistory = historyService.addRegulationHistory(authentication.getName(), regulationHistoryRequest.getRegulationId(),
                regulationHistoryRequest.getTitle(),regulationHistoryRequest.getAction());
        return regulationHistory == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(regulationHistory);
    }


    @GetMapping("/records")
    public ResponseEntity<?> getRecordsHistory(Authentication authentication,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<RecordHistory> recordHistories = historyService.getRecordsHistoryByPage(authentication.getName(), PageRequest.of(page - 1, size));
        return ResponseEntity.ok(recordHistories.map(recordHistoryMapper::toRecordHistoryDto));
    }

    @GetMapping("/regulations")
    public ResponseEntity<?> getRegulationsHistory(Authentication authentication,
                                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<RegulationHistory> regulationHistories = historyService.getRegulationsHistoryByPage(authentication.getName(), PageRequest.of(page - 1, size));
        return ResponseEntity.ok(regulationHistories);
    }

    @GetMapping("/records/action")
    public ResponseEntity<?> getRecordsHistoryByAction(Authentication authentication,
                                                       @RequestParam(value = "action") String action,
                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<RecordHistory> recordHistories = historyService.getRecordsHistoryByPageAndAction(authentication.getName(), action, PageRequest.of(page - 1, size));
        return ResponseEntity.ok(recordHistories.map(recordHistoryMapper::toRecordHistoryDto));
    }

    @GetMapping("/regulations/action")
    public ResponseEntity<?> getRegulationsHistoryByAction(Authentication authentication,
                                                           @RequestParam(value = "action") String action,
                                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<RegulationHistory> regulationHistories = historyService.getRegulationsHistoryByPageAndAction(authentication.getName(), action, PageRequest.of(page - 1, size));
        return ResponseEntity.ok(regulationHistories);
    }
}
