package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.dbmodels.Group;
import com.brahalla.Cerberus.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Me on 9/29/2016.
 */
public interface GroupRepository extends MongoRepository<Group, String> {

    //List<Group> findByLocationNear(Point location, Distance distance);
    List<Group> findByLocationNear(Point location);
   List<Group> findByMembersIn(String member);
    Page<Group> findByNameStartsWith(String name, Pageable page);
    GeoResults<Group> findTop30ByLocationNear(Point location, Range<Distance> distance);
    Page<Group> findBy(TextCriteria criteria, Pageable page);
    List<Group> findByMembersInOrderByLastPostDateDesc(String member);


}
