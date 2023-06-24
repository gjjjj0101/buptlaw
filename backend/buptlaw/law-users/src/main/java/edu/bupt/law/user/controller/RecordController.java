package edu.bupt.law.user.controller;

import edu.bupt.law.user.controller.request.AddRecordRequest;
import edu.bupt.law.user.dto.RecordMapper;
import edu.bupt.law.user.dto.model.RecordDto;
import edu.bupt.law.user.dto.model.RecordHistoryDto;
import edu.bupt.law.user.model.Record;
import edu.bupt.law.user.model.User;
import edu.bupt.law.user.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @Autowired
    RecordMapper recordMapper;

    @PostMapping("/records")
    public ResponseEntity<?> addRecord(Authentication authentication, @RequestBody AddRecordRequest addRecordRequest){
        Record record = recordService.addRecord(addRecordRequest.getTitle(), addRecordRequest.getDescription(),
                addRecordRequest.getContent(), addRecordRequest.getType(), authentication.getName());
        return record == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(recordMapper.toRecordDto(record));
    }

    @GetMapping("/records")
    public ResponseEntity<?> getRecords(Authentication authentication,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size){
        Page<Record> records = recordService.getRecordsByPage(authentication.getName(), PageRequest.of(page - 1, size));
        Page<RecordDto> recordDtos = records.map(recordMapper::toRecordDto);
        return ResponseEntity.ok(recordDtos);
    }

    @GetMapping("/record")
    public ResponseEntity<?> getRecordsByAction(Authentication authentication,
                                                @RequestParam(value = "id") Long id){
        Record record = recordService.getRecordById(id);
        return record == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(recordMapper.toRecordDto(record));
    }

    @PutMapping("/records")
    public ResponseEntity<?> updateRecord(Authentication authentication,
                                          @RequestParam(value = "id") Long id,
                                          @RequestBody AddRecordRequest addRecordRequest){
        Record record = recordService.updateRecord(id, addRecordRequest.getTitle(), addRecordRequest.getDescription(),
                addRecordRequest.getContent(), addRecordRequest.getType(), authentication.getName());
        return record == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(recordMapper.toRecordDto(record));
    }

    @DeleteMapping("/records")
    public ResponseEntity<?> deleteRecord(Authentication authentication,
                                          @RequestParam(value = "id") Long id){
        Boolean result = recordService.deleteRecord(id);
        return result ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
