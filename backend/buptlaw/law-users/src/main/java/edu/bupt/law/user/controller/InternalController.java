package edu.bupt.law.user.controller;

import edu.bupt.law.user.dto.model.CaseDto;
import edu.bupt.law.user.dto.model.CaseReasonNumsDto;
import edu.bupt.law.user.dto.model.CaseStatusNumsDto;
import edu.bupt.law.user.dto.model.CaseTypeNumsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @GetMapping("/cases/reason/nums")
    public ResponseEntity<?> getCasesReasonNums(){
        List<CaseReasonNumsDto> caseReasonNumsDtos = new ArrayList<>();
        caseReasonNumsDtos.add(new CaseReasonNumsDto().setCaseReason("010000").setNum(new Random().nextInt(200)));
        caseReasonNumsDtos.add(new CaseReasonNumsDto().setCaseReason("020000").setNum(new Random().nextInt(200)));
        caseReasonNumsDtos.add(new CaseReasonNumsDto().setCaseReason("030000").setNum(new Random().nextInt(200)));
        caseReasonNumsDtos.add(new CaseReasonNumsDto().setCaseReason("040000").setNum(new Random().nextInt(200)));
        caseReasonNumsDtos.add(new CaseReasonNumsDto().setCaseReason("050000").setNum(new Random().nextInt(200)));
        return ResponseEntity.ok(caseReasonNumsDtos);
    }

    @GetMapping("/cases/type/nums")
    public ResponseEntity<?> getCasesTypeNums(){
        List<CaseTypeNumsDto> caseTypeNumsDtos = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            caseTypeNumsDtos.add(new CaseTypeNumsDto().setCaseType(String.format("%02d",i) + "00").setNum(new Random().nextInt(200)));
        }
        return ResponseEntity.ok(caseTypeNumsDtos);
    }

    @GetMapping("/cases/status/nums")
    public ResponseEntity<?> getCasesStatusNums(){
        List<CaseStatusNumsDto> caseStatusNumsDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            caseStatusNumsDtos.add(new CaseStatusNumsDto().setStatus(String.format("%02d",i)).setNum(new Random().nextInt(200)));
        }
        return ResponseEntity.ok(caseStatusNumsDtos);
    }


    @GetMapping("/cases")
    public ResponseEntity<?> getCases(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size){
        CaseDto caseDto = new CaseDto();
        caseDto.setCaseId(1L).setUserId(1L).setCaseType("010000").setCaseReason("0100")
                .setCaseStatus("0").setCaseName("生态环境损害赔偿诉讼案").setCaseDescription("生态环境损害赔偿诉讼案").setCaseContent("生态环境损害赔偿诉讼案")
                .setOpenTime(LocalDateTime.now()).setCreateTime(LocalDateTime.now());
        List<CaseDto> caseDtos = new ArrayList<>();
        caseDtos.add(caseDto);
        PageImpl<CaseDto> caseDtoPageable = new PageImpl<>(caseDtos);
        return ResponseEntity.ok(caseDtoPageable);
    }
}
