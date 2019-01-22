package com.nigam.profile.dao;

import com.nigam.profile.entity.Profile;

import java.util.List;

public interface ProfileDAO {


    Profile getProfileById(int id);
    List<Profile> getAllProfile();
    void addProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(int id);
    boolean profileExists(String name, String email);
}
