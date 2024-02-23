package com.platzi.pizzeria.entity.projection;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface InfoBasicUser {

    @JsonProperty(value = "administrator")
    boolean getAdministrator ();

    @JsonProperty(value = "image")
    String getProfileImage();

    @JsonProperty(value = "person_name")
    String getPersonName();

    @JsonProperty(value = "person_lastname")
    String getPersonLastname();

}
