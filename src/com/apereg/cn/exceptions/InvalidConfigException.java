package com.apereg.cn.exceptions;

import java.io.IOException;

public class InvalidConfigException extends IOException {

    public InvalidConfigException(String message) {
        super(message);
    }

}
