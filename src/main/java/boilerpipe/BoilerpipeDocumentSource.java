package boilerpipe;


import boilerpipe.document.TextDocument;

/**
 * Something that can be represented as a {@link TextDocument}.
 */
public interface BoilerpipeDocumentSource {
    TextDocument toTextDocument() throws BoilerpipeProcessingException;
}
