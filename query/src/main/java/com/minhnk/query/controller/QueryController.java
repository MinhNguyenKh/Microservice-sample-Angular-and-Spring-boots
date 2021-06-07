package com.minhnk.query.controller;

import com.minhnk.query.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/query")
@CrossOrigin(origins = "http://localhost:4200")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @PostMapping("/events")
    public String getMessageFromEvenBus(@RequestBody String message){
        return queryService.getMessageFromEvenBus(message);
    }
}
