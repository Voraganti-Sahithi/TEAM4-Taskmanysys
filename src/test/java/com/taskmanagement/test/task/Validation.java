package com.taskmanagement.test.task;
 
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
 
public class Validation {
	public static int extractId(String endpoint) {
        String[] parts = endpoint.split("/");
        String idString = parts[parts.length - 1]; // Get the last part of the URL
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid taskId in the endpoint");
        }
    }
	
	public static boolean extractString(String endpoint) {
        String[] parts = endpoint.split("/");
        String str = parts[parts.length - 1]; // Get the last part of the URL
        String str1 = parts[parts.length - 3];
        if(str.length()>20 || str1.length()>20)
        	return false;
        return true;
    }
}