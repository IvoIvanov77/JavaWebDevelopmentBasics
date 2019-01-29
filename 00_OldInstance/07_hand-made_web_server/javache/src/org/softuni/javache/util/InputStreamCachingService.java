package org.softuni.javache.util;

import org.softuni.javache.io.Reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class InputStreamCachingService {
    private String content;

    public InputStreamCachingService() {
    }

    public InputStream getOrCacheInputStream(InputStream inputStream) throws IOException {

        if(content == null){
            int counter =0;
            while (counter++ < 5000 || this.content == null){
                content = Reader.readAllLines(inputStream);
                if( this.content.length() > 0){
                    break;
                }
            }

            if(this.content == null){
                throw new IllegalArgumentException("Content loading failure");
            }

        }
        return new ByteArrayInputStream(content.getBytes());
    }

    public void evictCache(){
        this.content = null;
    }
}
