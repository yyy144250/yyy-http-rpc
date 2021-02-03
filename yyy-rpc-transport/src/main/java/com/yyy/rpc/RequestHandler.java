package com.yyy.rpc;

import java.io.InputStream;
import java.io.OutputStream;

public interface RequestHandler {
    void resHandler(InputStream req, OutputStream res);
}
