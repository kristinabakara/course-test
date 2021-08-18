package util;

import com.cakap.course.model.CustomeMessage;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCustomMessage {
    public static List<CustomeMessage> setMessage(String message, org.springframework.http.HttpStatus status){
        CustomeMessage cm = new CustomeMessage();
        cm.setStatusCode(status.toString());
        cm.setMessage(message);
        List<CustomeMessage> customeMessageList = new ArrayList<CustomeMessage>();
        customeMessageList.add(cm);
        return customeMessageList;
    }
}
