package com.morris.metaj.model;

public class MetaInstance {
    private final String id;
    private final String publicHostName;
    private final String availabilityZone;
    private final String amiId;
    private final String launchIndex;
    private final String amiManifestPath;

    public MetaInstance(String id, String publicHostName, String availabilityZone, String amiId,
                        String launchIndex, String amiManifestPath) {
        this.id = id;
        this.publicHostName = publicHostName;
        this.availabilityZone = availabilityZone;
        this.amiId = amiId;
        this.launchIndex = launchIndex;
        this.amiManifestPath = amiManifestPath;
    }

    public String getId() {
        return this.id;
    }

    public String getPublicHostName() {
        return this.publicHostName;
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
}
