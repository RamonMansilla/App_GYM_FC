package com.example.app_gymfc.models;

import java.util.Date;

public interface IUser {
    long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPassword();

    Date getDateBirth();

    String getHeight();


}
