package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.dbmodels.App;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by dani on 10/5/2016.
 */
public interface AppRepository extends MongoRepository<App, String> {

    List<App> findByLocationNear(Point location, Distance distance);
    App findOneByName(String name);
    List<App> findByUserId(String userId);

}