package boilerpipe;

import boilerpipe.document.TextDocument;
import boilerpipe.extractors.ArticleExtractor;
import boilerpipe.sax.BoilerpipeSAXInput;
import boilerpipe.sax.HTMLDocument;
import boilerpipe.sax.HTMLFetcher;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class TextDocumentParserTest {

    @Test
    public void testBasicDocument1() throws Exception {

        TextDocument doc = parse( "/test1.html" );

        assertEquals( "[TextBlock{isContent=false, text=hello world, labels=null, offsetBlocksStart=0, offsetBlocksEnd=0, numWords=2, numWordsInAnchorText=0, numWordsInWrappedLines=2, numWrappedLines=1, textDensity=2.0, linkDensity=0.0, containedTextElements={2}, numFullTextWords=0, tagLevel=3}]",
                      doc.getTextBlocks().toString() );

    }

    @Test
    public void testBasicDocument2() throws Exception {

        TextDocument doc = parse( "/test2.html" );

        assertEquals( "[TextBlock{isContent=false, text=hello world, labels=null, offsetBlocksStart=0, offsetBlocksEnd=0, numWords=2, numWordsInAnchorText=0, numWordsInWrappedLines=2, numWrappedLines=1, textDensity=2.0, linkDensity=0.0, containedTextElements={2}, numFullTextWords=0, tagLevel=3}, TextBlock{isContent=false, text=this is a link and this is mixed content., labels=null, offsetBlocksStart=1, offsetBlocksEnd=1, numWords=9, numWordsInAnchorText=4, numWordsInWrappedLines=9, numWrappedLines=1, textDensity=9.0, linkDensity=0.44444445, containedTextElements={5, 6, 7, 9, 10}, numFullTextWords=0, tagLevel=4}]",
                      doc.getTextBlocks().toString() );

    }

    private TextDocument parse( String path ) throws Exception {

        URL url = getClass().getResource( path );

        final HTMLDocument htmlDoc = HTMLFetcher.fetch( url );

        return new BoilerpipeSAXInput(htmlDoc.toInputSource())
            .getTextDocument();

    }

}