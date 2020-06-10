package com.clarusone.poker.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class FileReadExceptionTest {

    @Test
    public void testCtor(){
        String message = "message";
        Exception exception = new Exception();

        FileReadException fileReadException = new FileReadException(message, exception);

        Assert.assertEquals(message, fileReadException.getMessage());
        Assert.assertEquals(exception, fileReadException.getCause());
    }

}