package com.top.cap.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.top.cap.model.Book;

/**
 * Data Access Object to CRUD Books.
 */
@Component
public class BookDAO extends DAO {
	
	/**
	 * Load an existing book, the partitionKey book instance must have the id field populated or this call with fail.
	 */
	public Book load(Book partitionKey) throws Exception {
		
		DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>().withLimit(properties.getQueryPageSize()).withHashKeyValues(partitionKey);;
		
		List<Book> bookList = getAmazonDynamoDB().query(Book.class, queryExpression);
		
		Book book = null;
		for (int i = 0; i < bookList.size(); i++) {
		    System.out.println(bookList.get(i));
		    book = bookList.get(i);
		}
		return book;
	}
	
	/**
	 * Find and return all books, limit query results based on application.properties defined query page size.
	 */
	public List<Book> findAll() throws Exception {

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val2", new AttributeValue().withS("Book"));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("ProductCategory = :val1").withExpressionAttributeValues(eav).withLimit(properties.getQueryPageSize());
        
        List<Book> scanResult = getAmazonDynamoDB().scan(Book.class, scanExpression);
        
        for (Book book : scanResult) {
            System.out.println(book);
        }
        
        return scanResult;
	}

}
