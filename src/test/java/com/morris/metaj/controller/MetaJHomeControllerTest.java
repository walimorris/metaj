package com.morris.metaj.controller;

import com.morris.metaj.controller.MetaJHomeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MetaJHomeController.class)
public class MetaJHomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MetaJHomeController metaJHomeController;

    public static final String REACT_DIV = "<div id=\"react\"></div>";
    public static final String BUILT_BUNDLE_SCRIPT = "<script src=\"built/bundle.js\"></script>";
    public static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(metaJHomeController);
    }

    @Test
    public void HomeControllerReturnsIndexWithReactAndBuiltBundles() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", CONTENT_TYPE))
                .andExpect(content().string(containsString(REACT_DIV)))
                .andExpect(content().string(containsString(BUILT_BUNDLE_SCRIPT)));
    }
}
