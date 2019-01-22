package com.nigam.profile.controller;

import com.nigam.profile.entity.Profile;
import com.nigam.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("profile1")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("profile/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") Integer id) {
        Profile profile = profileService.getProfileById(id);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }
    @GetMapping("profiles")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> list = profileService.getAllProfile();
        return new ResponseEntity<List<Profile>>(list, HttpStatus.OK);
    }
    @PostMapping("profile")
    public ResponseEntity<Void> addProfile(@RequestBody Profile profile, UriComponentsBuilder builder) {
        boolean flag = profileService.addProfile(profile);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/profile/{id}").buildAndExpand(profile.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("profile")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        profileService.updateProfile(profile);
        return new ResponseEntity<Profile>(profile, HttpStatus.OK);
    }
    @DeleteMapping("profile/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable("id") Integer id) {
        profileService.deleteProfile(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
