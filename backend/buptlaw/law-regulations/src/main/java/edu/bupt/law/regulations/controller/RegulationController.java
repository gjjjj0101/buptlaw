package edu.bupt.law.regulations.controller;

import edu.bupt.law.common.utils.UUidUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegulationController {

    @GetMapping("/hello")
    @Secured("ROLE_ADMIN")
    public String hello(){
        return UUidUtils.generateUuid();
    }

    @GetMapping("/hello2")
    public String hello2(){
        return UUidUtils.generateUuid();
    }

}
