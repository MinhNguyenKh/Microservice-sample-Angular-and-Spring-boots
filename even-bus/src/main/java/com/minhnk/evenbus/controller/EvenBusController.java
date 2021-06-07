package com.minhnk.evenbus.controller;

import com.minhnk.evenbus.VO.ResponseTemplateVO;
import com.minhnk.evenbus.service.EvenBusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@Slf4j
public class EvenBusController {

    @Autowired
    private EvenBusService evenBusService;

    @PostMapping("/")
    public String getEvent(@RequestBody ResponseTemplateVO responseTemplateVO){
        return evenBusService.getEvent(responseTemplateVO);
    }

    @GetMapping("/")
    public String getTest(){
        return "Minh";
    }

}
