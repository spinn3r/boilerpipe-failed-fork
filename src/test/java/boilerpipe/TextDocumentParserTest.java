package boilerpipe;

import boilerpipe.document.TextDocument;
import boilerpipe.extractors.ArticleExtractor;
import boilerpipe.sax.BoilerpipeSAXInput;
import boilerpipe.sax.HTMLDocument;
import boilerpipe.sax.HTMLFetcher;
import com.google.common.base.Joiner;
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

    @Test
    public void testBasicDocument3() throws Exception {

        TextDocument doc = parse( "/test3.html" );

        // I can use jsoup for this by taking the code from flushBlock in
        // boilerpipe.sax.BoilerpipeHTMLContentHandler and doing a jsoup query
        // for div,p ... in jsoup and then taking the text from the elements to
        // build a  TextBlock

        assertEquals( "TextBlock{isContent=false, text=hello world 1, labels=null, offsetBlocksStart=0, offsetBlocksEnd=0, numWords=3, numWordsInAnchorText=0, numWordsInWrappedLines=3, numWrappedLines=1, textDensity=3.0, linkDensity=0.0, containedTextElements={2}, numFullTextWords=0, tagLevel=3}\n" +
                        "TextBlock{isContent=false, text=this is a link 2 and this is 3 mixed 4 content 5., labels=null, offsetBlocksStart=1, offsetBlocksEnd=1, numWords=13, numWordsInAnchorText=5, numWordsInWrappedLines=13, numWrappedLines=1, textDensity=13.0, linkDensity=0.3846154, containedTextElements={5, 6, 7, 9, 10}, numFullTextWords=0, tagLevel=4}\n" +
                        "TextBlock{isContent=false, text=this is 6, labels=null, offsetBlocksStart=2, offsetBlocksEnd=2, numWords=3, numWordsInAnchorText=0, numWordsInWrappedLines=3, numWrappedLines=1, textDensity=3.0, linkDensity=0.0, containedTextElements={12}, numFullTextWords=0, tagLevel=3}\n" +
                        "TextBlock{isContent=false, text=REALLY some 7, labels=null, offsetBlocksStart=3, offsetBlocksEnd=3, numWords=3, numWordsInAnchorText=0, numWordsInWrappedLines=3, numWrappedLines=1, textDensity=3.0, linkDensity=0.0, containedTextElements={13}, numFullTextWords=0, tagLevel=4}\n" +
                        "TextBlock{isContent=false, text=mixed 8, labels=null, offsetBlocksStart=4, offsetBlocksEnd=4, numWords=2, numWordsInAnchorText=0, numWordsInWrappedLines=2, numWrappedLines=1, textDensity=2.0, linkDensity=0.0, containedTextElements={14}, numFullTextWords=0, tagLevel=4}\n" +
                        "TextBlock{isContent=false, text=content 9., labels=null, offsetBlocksStart=5, offsetBlocksEnd=5, numWords=2, numWordsInAnchorText=0, numWordsInWrappedLines=2, numWrappedLines=1, textDensity=2.0, linkDensity=0.0, containedTextElements={15}, numFullTextWords=0, tagLevel=4}",
                      Joiner.on("\n").join( doc.getTextBlocks() ) );

    }

    private TextDocument parse( String path ) throws Exception {

        URL url = getClass().getResource( path );

        final HTMLDocument htmlDoc = HTMLFetcher.fetch( url );

        return new BoilerpipeSAXInput(htmlDoc.toInputSource())
            .getTextDocument();

    }

}