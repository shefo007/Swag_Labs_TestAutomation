package com.saucedemo.swagLabs.exceptions;

public class HeadlessNotSupportedException extends  IllegalStateException{

    public HeadlessNotSupportedException() {
        super("Headless not supported for this browser");
    }
}
