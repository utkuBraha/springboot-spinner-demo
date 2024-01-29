package com.example.spinnerdemo.controller;

import com.example.spinnerdemo.service.SpinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spin")
public class SpinController {

    private final SpinService spinService;

    public SpinController(SpinService spinService) {
        this.spinService = spinService;
    }
    @GetMapping("/getSpin")
    public String getSpin() {
        return spinService.getSpin();
    }
}
