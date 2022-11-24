package com.morris.metaj.service;

/**
 * {@code com/morris/metaj/service/MetaJAwsIamService.java} contains numerous methods to obtain AWS
 * Identity Access Management information used in metaj.
 *
 */
public interface MetaJAwsIamService {

    /**
     * Get account id.
     *
     * @return {@link String} AWS Account id
     */
    String getAccountId();
}
