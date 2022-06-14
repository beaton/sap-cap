package com.top.cap.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.top.cap.properties.Properties;

public class DAO {
	
	private DynamoDBMapper dynamoDBMapper;
	
	@Autowired
	protected Properties properties;
	
	protected DynamoDBMapper getAmazonDynamoDB() throws Exception {
		if (dynamoDBMapper == null) {
			
			String region = properties.getRegion();

			AWSCredentialsProvider credentials;
			String activeProfile = properties.getActiveProfile();

			if (activeProfile.equals("dev")) {
				// Pull credentials locally from ~/.aws/credentials.
				String credential = properties.getCredential();
				credentials = new ProfileCredentialsProvider(credential);
			} else {
				// Assume credentials are configured in the EC2 Instance.
				credentials = new InstanceProfileCredentialsProvider(false);
			}

			try {
				AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
					.withCredentials(credentials)
					.withRegion(region)
					.build();
				dynamoDBMapper = new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
			} catch (Exception ex) {
				throw new Exception(ex.getMessage(), ex);
			}
		}
		return dynamoDBMapper;
	}

}
