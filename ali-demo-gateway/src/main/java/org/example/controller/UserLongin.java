package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.dto.LoginDto;
import org.example.service.MxdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gatewayLogin")
public class UserLongin {
    @Autowired
    private MxdUserService mxdUserService;

    @PostMapping("userLogin")
    public LoginDto userLogin(@RequestBody LoginDto loginDto){
        /*LoginDto loginDto = new LoginDto();
        loginDto.setUserName("admin");
        loginDto.setPassWord("admin");*/
        LoginDto loginDto1 = mxdUserService.userLogin(loginDto);
        return loginDto1;
    }
}
