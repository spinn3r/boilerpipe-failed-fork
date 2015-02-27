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
    public void test1() throws Exception {

        URL url = getClass().getResource( "/test1.html" );


        final HTMLDocument htmlDoc = HTMLFetcher.fetch( url );

        final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource())
                                   .getTextDocument();

        assertEquals( "[TextBlock{isContent=false, text=hello world, labels=null, offsetBlocksStart=0, offsetBlocksEnd=0, numWords=2, numWordsInAnchorText=0, numWordsInWrappedLines=2, numWrappedLines=1, textDensity=2.0, linkDensity=0.0, containedTextElements={2}, numFullTextWords=0, tagLevel=3}]",
                      doc.getTextBlocks().toString() );

        //String text = articleExtractor.process(doc);

        //System.out.printf( "%s\n", text );

    }

}