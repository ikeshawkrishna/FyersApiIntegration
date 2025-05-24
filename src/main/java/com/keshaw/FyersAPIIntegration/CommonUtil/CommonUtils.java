package com.keshaw.FyersAPIIntegration.CommonUtil;

import com.keshaw.FyersAPIIntegration.Exceptions.InvalidRequestException;
import com.tts.in.utilities.Tuple;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    public void checkForExceptionFromTuple(Tuple<JSONObject, JSONObject> tuple) throws InvalidRequestException {
        if(tuple.Item2() != null){
            throw new InvalidRequestException(tuple.Item2().getString("error_message"));
        }
    }
}
