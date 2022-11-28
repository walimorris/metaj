package com.morris.metaj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaInstance {

    @JsonProperty("id")
    private String id;

    @JsonProperty("publicHostName")
    private String publicHostName;

    @JsonProperty("localHostName")
    private String localHostName;

    @JsonProperty("availabilityZone")
    private String availabilityZone;

    @JsonProperty("amiId")
    private String amiId;

    @JsonProperty("launchIndex")
    private String launchIndex;

    @JsonProperty("amiManifestPath")
    private String amiManifestPath;

    @JsonProperty("type")
    private String type;

    @JsonProperty("localIpv4")
    private String localIpv4;

    @JsonProperty("kernelId")
    private String kernelId;

    @JsonProperty("publicKeys")
    private String publicKeys;

    @JsonProperty("ancestorAmiIds")
    private String ancestorAmiIds;

    @JsonProperty("blockDeviceMapping")
    private String blockDeviceMapping;

    @JsonProperty("productCodes")
    private String productCodes;

    public MetaInstance() {
        super();
    }

    public MetaInstance(String id, String publicHostName, String localHostName,
                        String availabilityZone, String amiId, String launchIndex,
                        String amiManifestPath, String type, String localIpv4,
                        String kernelId, String publicKeys, String ancestorAmiIds,
                        String blockDeviceMapping, String productCodes) {

        this.id = id;
        this.publicHostName = publicHostName;
        this.localHostName = localHostName;
        this.availabilityZone = availabilityZone;
        this.amiId = amiId;
        this.launchIndex = launchIndex;
        this.amiManifestPath = amiManifestPath;
        this.type = type;
        this.localIpv4 = localIpv4;
        this.kernelId = kernelId;
        this.publicKeys = publicKeys;
        this.ancestorAmiIds = ancestorAmiIds;
        this.blockDeviceMapping = blockDeviceMapping;
        this.productCodes = productCodes;
    }

    public String getId() {
        return this.id;
    }

    public String getPublicHostName() {
        return this.publicHostName;
    }

    public String getLocalHostName() {
        return this.localHostName;
    }

    public String getAvailabilityZone() {
        return this.availabilityZone;
    }

    public String getAmiId() {
        return this.amiId;
    }

    public String getLaunchIndex() {
        return this.launchIndex;
    }

    public String getAmiManifestPath() {
        return this.amiManifestPath;
    }

    public String getType() {
        return this.type;
    }

    public String getLocalIpv4() {
        return this.localIpv4;
    }

    public String getPublicKeys() {
        return this.publicKeys;
    }

    public String getKernelId() {
        return this.kernelId;
    }

    public String getAncestorAmiIds() {
        return this.ancestorAmiIds;
    }

    public String getProductCodes() {
        return this.productCodes;
    }

    public String getBlockDeviceMapping() {
        return this.blockDeviceMapping;
    }
}
