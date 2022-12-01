package com.morris.metaj.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
public class MetaJAwsIamServiceTest {

    @Autowired
    private MetaJAwsIamServiceImpl metaJAwsIamService;

    @MockBean
    private MetaJAwsIamServiceImpl mockMetaJAwsIamService;

    private static final String TEST_ARN_1 = "arn:aws:iam:us-east-1:1234567890:test-example";
    private static final String TEST_ACCOUNT_1 = "1234567890";

    @Test
    void contextLoads() {
        Assertions.assertNotNull(metaJAwsIamService);
    }

    @Test
    void getAccountIdWithCorrectSplit() {
        Mockito.when(mockMetaJAwsIamService.getAccountId()).thenReturn(TEST_ARN_1.split(":")[4]);
        String response = mockMetaJAwsIamService.getAccountId();
        Assertions.assertEquals(TEST_ACCOUNT_1, response);
    }

    @Test
    void ensureGetAccountIdOnlyReturnsAccountId() {
        Mockito.when(mockMetaJAwsIamService.getAccountId()).thenReturn(TEST_ARN_1.split(":")[4]);
        String response = mockMetaJAwsIamService.getAccountId();
        Assertions.assertNotEquals(TEST_ARN_1, response);
    }
}
