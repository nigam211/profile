package com.nigam.profile.dao.impl;

import com.nigam.profile.dao.ProfileDAO;
import com.nigam.profile.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class ProfileDAOImpl implements ProfileDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Profile getProfileById(int id) {
        String sql = "SELECT id, name, email,address,img FROM profile WHERE id = ?";
        RowMapper<Profile> rowMapper = new BeanPropertyRowMapper<Profile>(Profile.class);
        Profile profile=jdbcTemplate.queryForObject(sql,rowMapper,id);
        return profile;
    }
    @Override
    public List<Profile> getAllProfile() {
        String sql = "SELECT id, name, email,address,img FROM profile";
        //RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        RowMapper<Profile> rowMapper = new BeanPropertyRowMapper<Profile>(Profile.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void addProfile(Profile profile) {

        //Add article
        String sql = "INSERT INTO profile (id, name, email,address,img) values (?, ?, ?,?,?)";
        jdbcTemplate.update(sql, profile.getId(), profile.getName(), profile.getEmail(),profile.getAddress(),profile.getImg());

        //Fetch article id
        sql = "SELECT id FROM profile WHERE name = ?,email=?,address=? and img=?";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, profile.getName(),profile.getEmail(),profile.getAddress(),profile.getImg());

        //Set article id
        profile.setId(id);
    }

    @Override
    public void updateProfile(Profile profile) {

        String sql = "UPDATE profile SET name=?, email=?,address=?,img=? WHERE id=?";
        jdbcTemplate.update(sql, profile.getName(),profile.getEmail(),profile.getAddress(),profile.getImg());
    }
    @Override
    public void deleteProfile(int id) {
        String sql = "DELETE FROM profile WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean profileExists(String name, String email) {
        String sql = "SELECT count(*) FROM profile WHERE name = ?,email=?,address=? and img=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, name, email);
        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }
}
