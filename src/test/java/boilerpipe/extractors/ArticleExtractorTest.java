package boilerpipe.extractors;

import boilerpipe.document.TextDocument;
import boilerpipe.sax.BoilerpipeSAXInput;
import boilerpipe.sax.HTMLDocument;
import boilerpipe.sax.HTMLFetcher;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class ArticleExtractorTest {

    @Test
    public void test1() throws Exception {

        ArticleExtractor articleExtractor = ArticleExtractor.getInstance();

        URL url = new URL( "http://arstechnica.com/business/2015/02/fcc-votes-for-net-neutrality-a-ban-on-paid-fast-lanes-and-title-ii/");

        //String text = articleExtractor.getText( url );

        final HTMLDocument htmlDoc = HTMLFetcher.fetch( url );

        final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource())
                                   .getTextDocument();

        //String text = articleExtractor.process(doc);

        //System.out.printf( "%s\n", text );

    }

}