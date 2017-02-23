package com.brahalla.Cerberus.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by dani on 10/5/2016.
 */
@Component
public class ImageUploadService {
    @Value("${aws.region}")
    private String region;
    @Value("${aws.accessKeyId}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.bucket}")
    private String bucketName;


    private BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAJ3W6LSNHBM3LVWKA","6Qta76RLLyrnmSp5NapTcVZfAC/zfybcJbb1Ycyb");
    private AmazonS3 s3Client = new AmazonS3Client(credentials);

    public boolean UploadObjectSingleOperation(String fileName,String image) {
        s3Client.setRegion(Region.getRegion(Regions.EU_WEST_1));
        byte[] bI = org.apache.commons.codec.binary.Base64.decodeBase64(image.getBytes());
        InputStream fis = new ByteArrayInputStream(bI);
            try {
                System.out.println("Uploading a new object to S3 from a file\n");
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(bI.length);
                metadata.setContentType("image/png");
                metadata.setCacheControl("public, max-age=31536000");
                s3Client.putObject(bucketName, fileName, fis, metadata);
                s3Client.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
                return true;

            } catch (AmazonServiceException ase) {
                System.out.println("Caught an AmazonServiceException, which " +
                        "means your request made it " +
                        "to Amazon S3, but was rejected with an error response" +
                        " for some reason.");
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
            } catch (AmazonClientException ace) {
                System.out.println("Caught an AmazonClientException, which " +
                        "means the client encountered " +
                        "an internal error while trying to " +
                        "communicate with S3, " +
                        "such as not being able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());
            }
            return false;

    }

}
