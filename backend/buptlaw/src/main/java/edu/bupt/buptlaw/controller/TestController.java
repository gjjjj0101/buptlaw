package edu.bupt.buptlaw.controller;


import edu.bupt.buptlaw.comm.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test1")
    Result testHello(){
        return Result.success("hello world!");
    }

}
