package com.morris.metaj.controller;

import com.morris.metaj.model.MetaInstance;
import com.morris.metaj.service.impl.InstanceInitializerImpl;
import com.morris.metaj.service.impl.InstanceMappingsImpl;
import com.morris.metaj.service.impl.MetaJAwsIamServiceImpl;
import com.morris.metaj.service.impl.MetaJSetupServiceImpl;
import com.morris.metaj.utils.FileUtil;
import com.morris.metaj.utils.ObjectMapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MetaInstanceController.class)
@ActiveProfiles("test")
public class MetaInstanceControllerTest {

    @Autowired
    private MetaInstanceController metaInstanceController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstanceInitializerImpl instanceInitializer;

    @MockBean
    private InstanceMappingsImpl instanceMappings;

    @MockBean
    private MetaJAwsIamServiceImpl metaJAwsIamService;

    @MockBean
    private MetaJSetupServiceImpl metaJSetupService;

    public final String META_INSTANCE_JSON = "/meta-instance.json";
    public static final String CONTENT_TYPE = "application/json";

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(metaInstanceController);
    }

    @Test
    public void getMetaInstanceTest() throws Exception {
        String metaInstanceJsonAsString = FileUtil.getResourceAsStringValue(META_INSTANCE_JSON);
        MetaInstance metaInstance = ObjectMapperHelper.convertJsonStringToObject(metaInstanceJsonAsString, MetaInstance.class);
        when(instanceInitializer.initMetaInstance()).thenReturn(metaInstance);

        mockMvc.perform(get("/meta/instance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(metaInstanceJsonAsString)))
                .andExpect(header().string("Content-Type", CONTENT_TYPE));
    }
}
