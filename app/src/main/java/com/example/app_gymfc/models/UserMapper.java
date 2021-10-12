package com.example.app_gymfc.models;

public class UserMapper {
    private IUser user;

    public UserMapper(IUser user) {
        this.user = user;
    }

    public UserEntity toEntity() {
        return new UserEntity(
                this.user.getId(),
                this.user.getFirstName(),
                this.user.getLastName(),
                this.user.getEmail(),
                this.user.getPassword(),
                this.user.getDateBirth(),
                this.user.getHeight()
        );
    }

    public User toBase() {
        User userBase = new User(
                this.user.getFirstName(),
                this.user.getLastName(),
                this.user.getEmail(),
                this.user.getDateBirth(),
                this.user.getHeight()
        );
        userBase.setPassword(this.user.getPassword());
        userBase.setId(this.user.getId());
        return userBase;
    }


}
