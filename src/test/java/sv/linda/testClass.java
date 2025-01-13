package sv.linda;

import java.io.InputStream;

public class testClass {
    private InputStream mock;

    public testClass(){
        this.mock = new java.io.ByteArrayInputStream(" ".getBytes());
    }

    public void setMock(String mockText){
        this.mock = new java.io.ByteArrayInputStream(mockText.getBytes());
    }

    public InputStream getMock(){
        return this.mock;
    }

    public InputStream useMock(String mockText){
        this.mock = new java.io.ByteArrayInputStream(mockText.getBytes());
        return this.mock;
    }
}
