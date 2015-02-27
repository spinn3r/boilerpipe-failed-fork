package com.spinn3r.test.corpora;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Code that tests for the assertion of a test but instead of testing with
 * data embedded in unit tests we test with data loaded from system resources.
 */
public class CorporaAsserter {

    /**
     * When in update mode, we just write data to resources and all tests pass.
     * This allows us to bulk approve a large number of tests if we've updated
     * an algorithm and think that all of them pass.
     */
    public static boolean UPDATE_MODE = true;

    // TODO: I need an easy way to configure this locally...
    public static String ROOT = "/projects/boilerpipe/src/test/resources/";

    private final Class<?> parent;

    /**
     * Create a corpora assertion tester using the given class as a parent
     * for logging and resource loading.
     * @param parent
     */
    public CorporaAsserter(Class<?> parent) {
        this.parent = parent;
    }

    public void assertCorpora( String name, String actual ) throws IOException {

        String path = String.format( "/corpora/%s.%s.dat", parent.getName(), name );

        if ( UPDATE_MODE ) {

            File file = new File( ROOT, path );

            Files.createDirectories( Paths.get( file.getParent() ) );

            try( OutputStream out = new FileOutputStream( file ) ) {

                out.write( actual.getBytes( Charsets.UTF_8 ) );

            }

        } else {

            try (InputStream is = parent.getResourceAsStream( path )) {

                byte[] data = ByteStreams.toByteArray( is );

                String expected = new String( data, Charsets.UTF_8 );

                assertEquals( expected, actual );

            }

        }

    }

}
