package com.hr.management.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.IOException;

public class InputStreamWrapper extends ServletInputStream {

private final byte[] data;
private int idx = 0;

public InputStreamWrapper(byte[] data) {
this.data = data;
}

@Override
public int read() throws IOException {
if (idx >= data.length) {
return -1;
}
return data[idx++];
}

@Override
public boolean isFinished() {
return false;
}

@Override
public boolean isReady() {
return false;
}

@Override
public void setReadListener(ReadListener readListener) {}
}