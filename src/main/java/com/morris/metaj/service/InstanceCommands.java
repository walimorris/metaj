package com.morris.metaj.service;

/**
 * {@code com/morris/metaj/service/InstanceCommands.java} exposes numerous ec2-metadata utils commands
 * that exposes aws ec2 apis for gathering instance metadata throughout metaj.
 */
public interface InstanceCommands {

    /**
     * Get instance command.
     *
     * @return String[]
     */
    String[] instanceCommand();

    /**
     * Get public host name command.
     *
     * @return String[]
     */
    String[] publicHostnameCommand();

    /**
     * Get local host name command.
     *
     * @return String[]
     */
    String[] localHostnameCommand();

    /**
     * Get availability zone command.
     *
     * @return String[]
     */
    String[] availabilityZoneCommand();

    /**
     * Get instance AMI ID command.
     *
     * @return String[]
     */
    String[] instanceAmiIdCommand();

    /**
     * Get instance AMI launch index command.
     *
     * @return String[]
     */
    String[] instanceAmiLaunchIndexCommand();

    /**
     * Get Instance AMI Manifest path command.
     *
     * @return String[]
     */
    String[] instanceAmiManifestPathCommand();

    /**
     * Get instance type command.
     *
     * @return String[]
     */
    String[] instanceTypeCommand();

    /**
     * get instance local IPv4 command.
     *
     * @return String[]
     */
    String[] instanceLocalIpv4Command();

    /**
     * Get instand kernel id command.
     *
     * @return String[]
     */
    String[] instanceKernelIdCommand();

    /**
     * Get instance public keys command.
     *
     * @return String[]
     */
    String[] instancePublicKeysCommand();

    /**
     * Get instance ancestor AMI IDs command.
     *
     * @return String[]
     */
    String[] instanceAncestorAmiIdsCommand();

    /**
     * Get instance block device mapping command.
     *
     * @return String[]
     */
    String[] instanceBlockDeviceMappingCommand();

    /**
     * Get instance product codes command.
     *
     * @return String[]
     */
    String[] instanceProductCodesCommand();
}
