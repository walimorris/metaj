package com.morris.metaj.controller;

import com.morris.metaj.model.MetaInstance;
import com.morris.metaj.service.impl.InstanceInitializerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meta")
public class MetaInstanceController {
    private static final Logger logger = LoggerFactory.getLogger(MetaInstanceController.class);

    @Autowired
    private InstanceInitializerImpl instanceInitializer;

    @GetMapping("/instance")
    public MetaInstance getMetaInstance() throws IOException {
        return instanceInitializer.initMetaInstance();
    }

    @GetMapping("/test")
    public ResponseEntity<List<String>> getTest() {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        return ResponseEntity.ok(list);
    }
}
