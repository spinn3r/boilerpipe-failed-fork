package com.spinn3r.test.corpora;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class CorporaCache {

    // TODO: I need an easy way to configure this locally...
    private static String ROOT = "/projects/boilerpipe/src/test/resources/";

    private final Class<?> parent;

    public CorporaCache(Class<?> parent) {
        this.parent = parent;
    }

    public void write( String key, String data ) throws IOException {

        String path = computePath( key );

        File file = new File( ROOT, path );

        Files.createDirectories( Paths.get( file.getParent() ) );

        try( OutputStream out = new FileOutputStream( file ) ) {

            out.write( data.getBytes( Charsets.UTF_8 ) );

        }

    }

    public String read( String key ) throws IOException {

        String path = computePath( key );

        try (InputStream is = parent.getResourceAsStream( path )) {

            byte[] data = ByteStreams.toByteArray( is );

            return new String( data, Charsets.UTF_8 );

        }

    }

    private String computePath( String key ) {
        return String.format( "/corpora/%s.%s.dat", parent.getName(), key );
    }

}


