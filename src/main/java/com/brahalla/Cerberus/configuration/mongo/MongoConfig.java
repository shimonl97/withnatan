package com.brahalla.Cerberus.configuration.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * Created by 0503337710 on 11/09/2016.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.brahalla.Cerberus.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "appgroup";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI("mongodb://dani:danidani@ds041556.mlab.com:41556/appgroup"));
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.brahalla.Cerberus";
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(new ZonedDateTimeToZonedDateTimeConverter(), new LocalDateTimeToLocalDateTimeConverter(),
                new LocalDateToLocalDateConverter()));
    }
}
