package com.morris.metaj.controller;

import com.morris.metaj.model.MetaInstance;
import com.morris.metaj.service.InstanceInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/meta")
public class MetaInstanceController {
    private static final Logger logger = LoggerFactory.getLogger(MetaInstanceController.class);

    @Autowired
    private InstanceInitializer instanceInitializer;

    @GetMapping("/instance")
    public MetaInstance getMetaInstance() throws IOException {
        return instanceInitializer.initMetaInstance();
    }
}
