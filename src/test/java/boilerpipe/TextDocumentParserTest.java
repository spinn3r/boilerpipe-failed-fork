package boilerpipe;

import boilerpipe.document.TextDocument;
import boilerpipe.extractors.ArticleExtractor;
import boilerpipe.sax.BoilerpipeSAXInput;
import boilerpipe.sax.HTMLDocument;
import boilerpipe.sax.HTMLFetcher;
import com.google.common.base.Joiner;
import com.spinn3r.test.corpora.CorporaAsserter;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TextDocumentParserTest {

    CorporaAsserter corporaAsserter = new CorporaAsserter( getClass() );

    @Test
    public void testBasicDocument1() throws Exception {

        TextDocument doc = parse( "/test1.html" );

        corporaAsserter.assertCorpora( "testBasicDocument1", doc.getTextBlocks().toString() );

    }

    @Test
    public void testBasicDocument2() throws Exception {

        TextDocument doc = parse( "/test2.html" );

        corporaAsserter.assertCorpora( "testBasicDocument2", doc.getTextBlocks().toString() );

    }

    @Test
    public void testBasicDocument3() throws Exception {

        TextDocument doc = parse( "/test3.html" );

        // I can use jsoup for this by taking the code from flushBlock in
        // boilerpipe.sax.BoilerpipeHTMLContentHandler and doing a jsoup query
        // for div,p ... in jsoup and then taking the text from the elements to
        // build a  TextBlock

        corporaAsserter.assertCorpora( "testBasicDocument3", Joiner.on( "\n" ).join( doc.getTextBlocks() ) );

    }

    private TextDocument parse( String path ) throws Exception {

        URL url = getClass().getResource( path );

        final HTMLDocument htmlDoc = HTMLFetcher.fetch( url );

        return new BoilerpipeSAXInput(htmlDoc.toInputSource())
            .getTextDocument();

    }

}