package com.desafioetl.desafioetl.controllers;

import com.desafioetl.desafioetl.services.TransformService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TransformController {

    private final TransformService service;

    public TransformController(TransformService service) {
        this.service = service;
    }

    @GetMapping("transform")
    public String transform() throws InterruptedException {
        return service.transform();
    }

}
