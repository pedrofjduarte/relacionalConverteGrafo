package com.modeloRelacional;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Banco {

    @XStreamAlias("Version")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("METADATA")
    private Metadata metadata;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
