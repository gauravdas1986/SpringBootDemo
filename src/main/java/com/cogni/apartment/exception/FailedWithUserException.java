package com.cogni.apartment.exception;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FailedWithUserException extends HystrixCommand<String> {

    protected FailedWithUserException() {
        super(HystrixCommandGroupKey.Factory.asKey("FailedWithUserException"));
    }

    @Override
    protected String run() throws Exception {
        throw new RuntimeException("My user exception");
    }
}
