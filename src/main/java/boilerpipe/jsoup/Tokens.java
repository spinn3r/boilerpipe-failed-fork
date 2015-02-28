package boilerpipe.jsoup;

import boilerpipe.util.UnicodeTokenizer;
import com.google.common.collect.Lists;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.List;

/**
 *
 */
public class Tokens {

    private static final String ANCHOR_TAG_NAME = "a";

    public static List<Token> tokenize( Element element ) {

        List<Token> result = Lists.newArrayList();

        for (Node current : element.childNodes()) {

            String text = null;
            boolean anchorText = false;

            if ( current instanceof Element ) {

                Element currentElement = (Element)current;

                text = currentElement.ownText();

                anchorText = ANCHOR_TAG_NAME.equals( currentElement.tagName() );

            }

            if ( current instanceof TextNode ) {

                TextNode currentTextNode = (TextNode)current;

                text = currentTextNode.text();

            }

            final String[] tokens = UnicodeTokenizer.tokenize( text );

            for (String token : tokens) {
                result.add( new Token( token, anchorText ) );
            }

        }

        return result;

    }

}
