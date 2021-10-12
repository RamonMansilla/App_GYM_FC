package com.example.app_gymfc.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "users", indices = {@Index(value = "email", unique = true)})


public class UserEntity implements IUser {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "dateBirth")
    private Date dateBirth;

    @ColumnInfo(name = "height")
    private String height;


    public UserEntity(long id, String firstName, String lastName, String email, String password, Date dateBirth, String height) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateBirth = dateBirth;
        this.height = height;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public String getHeight() {
        return height;
    }
}
