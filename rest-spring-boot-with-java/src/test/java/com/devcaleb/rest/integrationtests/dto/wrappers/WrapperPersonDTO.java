package com.devcaleb.rest.integrationtests.dto.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WrapperPersonDTO {

    @JsonProperty("_embedded")
    private PersonEmbeddedDTO embeddedDTO;

    public WrapperPersonDTO() {
    }

    public PersonEmbeddedDTO getEmbeddedDTO() {
        return embeddedDTO;
    }

    public void setEmbeddedDTO(PersonEmbeddedDTO embeddedDTO) {
        this.embeddedDTO = embeddedDTO;
    }
}
