package com.nigam.profile.service;

import com.nigam.profile.entity.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllProfile();
    Profile getProfileById(int id);
    boolean addProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(int id);
}
