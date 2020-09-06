package com.json_dynamic_key_parsing.data.remote.retrofit;

/**
        * This is the class whose instance would be returned during API call.
        * This APiResponse would represent various stages of an API call. This method is used
        * to avoid the need to have multiple callbacks for each stage
        *
        * @param <T>
 */
public class ApiResponse<T> {

    public enum State {
        LOADING, SUCCESS, FAILURE, ERROR
        //Failure when API hit itself fails because of some error
        //Error when API hit is success but the API throws some error
    }

    //denotes current state of an API call
    public State currentState = State.LOADING;

    //would be null for all cases except SUCCESS
    public T data = null;

    //would be null for all cases except FAILURE
    public Throwable throwable = null;
}
