package boilerpipe.jsoup;

import boilerpipe.document.TextBlock;
import boilerpipe.document.TextDocument;
import boilerpipe.util.UnicodeTokenizer;
import com.google.common.collect.Lists;
import oracle.jrockit.jfr.events.Bits;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.BitSet;
import java.util.List;

/**
 *
 */
public class JsoupParser {

    /**
     * Parse the given HTML to return a TextDocument.
     *
     * @param html
     * @return
     */
    public TextDocument parse( String html ) {

        Document document = Jsoup.parse( html );

        String title = null;
        List<TextBlock> textBlocks = Lists.newArrayList();

        return new TextDocument( title, textBlocks );

    }

//    /**
//     * Return a new text block or null if there are no tokens in it.
//     */
//    private TextBlock newTextBlock( Element textBlockElement, int offsetBlock ) {
//
//        final String[] tokens = UnicodeTokenizer.tokenize( text );
//
//        int numWords = 0;
//        int numLinkedWords = 0;
//        int numWrappedLines = 0;
//        int currentLineLength = -1; // don't count the first space
//        final int maxLineLength = 80;
//        int numTokens = 0;
//        int numWordsCurrentLine = 0;
//
//        // TODO: rewrite this to go through each child element.. then parse it
//        // into tokens, but if the element we're on is an anchor, then that
//        // should have the specific anchor text.
//
//        for (String token : tokens) {
//
//            if (ANCHOR_TEXT_START.equals(token)) {
//                inAnchorText = true;
//            } else if (ANCHOR_TEXT_END.equals(token)) {
//                inAnchorText = false;
//            } else if (isWord(token)) {
//                numTokens++;
//                numWords++;
//                numWordsCurrentLine++;
//                if (inAnchorText) {
//                    numLinkedWords++;
//                }
//                final int tokenLength = token.length();
//                currentLineLength += tokenLength + 1;
//                if (currentLineLength > maxLineLength) {
//                    numWrappedLines++;
//                    currentLineLength = tokenLength;
//                    numWordsCurrentLine = 1;
//                }
//            } else {
//                numTokens++;
//            }
//        }
//
//        if (numTokens == 0) {
//            return null;
//        }
//
//        int numWordsInWrappedLines;
//        if (numWrappedLines == 0) {
//            numWordsInWrappedLines = numWords;
//            numWrappedLines = 1;
//        } else {
//            numWordsInWrappedLines = numWords - numWordsCurrentLine;
//        }
//
//        BitSet containedTextElements = new BitSet();
//
//        // TODO: now include the Element used for this TextBlock so that I can
//        // later (optionally) use it to convert everything to HTML.
//
//        TextBlock tb = new TextBlock( text,
//                                      containedTextElements,
//                                      numWords,
//                                      numLinkedWords,
//                                      numWordsInWrappedLines,
//                                      numWrappedLines,
//                                      offsetBlock );
//
//        return tb;
//
//    }

}
