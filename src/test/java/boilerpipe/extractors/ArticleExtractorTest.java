package boilerpipe.extractors;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class ArticleExtractorTest {

    @Test
    public void test1() throws Exception {

        ArticleExtractor articleExtractor = ArticleExtractor.getInstance();

        String text = articleExtractor.getText( new URL( "http://arstechnica.com/business/2015/02/fcc-votes-for-net-neutrality-a-ban-on-paid-fast-lanes-and-title-ii/") );

        System.out.printf( "%s\n", text );

    }

}