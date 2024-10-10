package com.example.profile_management.utils;

import com.example.profile_management.entity.User;
import com.example.profile_management.records.UserRecord;

public class ControllerUtils {
    //static only methods
    public static UserRecord toUserRecord(User user) {
        return  UserRecord.builder()
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .userName(user.getUserName())
                .build();
    }
    public static User fromUserRecord(UserRecord user){
        return User.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .email(user.email())
                .phoneNumber(user.phoneNumber())
                .userName(user.userName())
                .build();
    }
}
