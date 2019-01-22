package com.nigam.profile.service.impl;

import com.nigam.profile.dao.ProfileDAO;
import com.nigam.profile.entity.Profile;
import com.nigam.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDAO profileDAO;


    @Override
    public List<Profile> getAllProfile() {
        return profileDAO.getAllProfile();
    }

    @Override
    public Profile getProfileById(int id) {
        Profile obj = profileDAO.getProfileById(id);
        return obj;
    }

    @Override
    public boolean addProfile(Profile profile) {
        if (profileDAO.profileExists(profile.getName(),profile.getEmail())) {
            return false;
        } else {
            profileDAO.addProfile(profile);
            return true;
        }
    }

    @Override
    public void updateProfile(Profile profile) {
        profileDAO.updateProfile(profile);
    }

    @Override
    public void deleteProfile(int id) {
        profileDAO.deleteProfile(id);
    }
}
