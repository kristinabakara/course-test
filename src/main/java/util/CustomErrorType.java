package util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomErrorType {
    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage =errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public static ResponseEntity returnResponseEntityError(String message){
        return new ResponseEntity(new CustomErrorType("An error occured: "+message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
