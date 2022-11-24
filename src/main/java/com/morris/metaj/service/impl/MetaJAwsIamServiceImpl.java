package com.morris.metaj.service.impl;

import com.morris.metaj.service.MetaJAwsIamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;

@Service
public class MetaJAwsIamServiceImpl implements MetaJAwsIamService {
    private static final Logger logger = LoggerFactory.getLogger(MetaJAwsIamServiceImpl.class);

    @Autowired
    AwsClientsBuilderImpl clientsBuilder;

    @Override
    public String getAccountId() {
        IamClient iamClient = clientsBuilder.getIamClient(Region.AWS_GLOBAL);
        ListUsersResponse listUsersResponse = iamClient.listUsers();
        return listUsersResponse.users().get(0).arn().split(":")[4];
    }
}
