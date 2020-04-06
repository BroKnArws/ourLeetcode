package com.neu.controller;

import com.neu.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/circle")
public class CircleController {

    @Autowired
    CircleService circleService;






}
