package com.apereg.cn.exceptions;

import java.io.IOException;
import java.io.Serial;

public class InvalidConfigException extends IOException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidConfigException() {
        super();
    }

    public InvalidConfigException(String message) {
        super(message);
    }
}
