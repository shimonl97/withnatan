package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.dbmodels.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Me on 9/26/2016.
 */
public interface ReportRepository extends MongoRepository<Report, String> {

}
