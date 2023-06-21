package edu.bupt.law.user.controller;

import edu.bupt.law.user.controller.request.AddRecordRequest;
import edu.bupt.law.user.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
public class UserController {
    @PostMapping("/records")
    public void addRecord(@AuthenticationPrincipal User user, @RequestBody AddRecordRequest addRecordRequest){

    }

}
