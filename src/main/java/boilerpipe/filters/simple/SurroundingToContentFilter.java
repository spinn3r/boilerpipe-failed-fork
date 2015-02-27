package boilerpipe.filters.simple;

import java.util.Iterator;
import java.util.List;


import boilerpipe.BoilerpipeFilter;
import boilerpipe.BoilerpipeProcessingException;
import boilerpipe.conditions.TextBlockCondition;
import boilerpipe.document.TextBlock;
import boilerpipe.document.TextDocument;

public class SurroundingToContentFilter implements BoilerpipeFilter {
	public static final SurroundingToContentFilter INSTANCE_TEXT = new SurroundingToContentFilter(new TextBlockCondition() {
		
		@Override
		public boolean meetsCondition(TextBlock tb) {
			return tb.getLinkDensity() == 0 && tb.getNumWords() > 6;
		}
	});

	private final TextBlockCondition cond;
    public SurroundingToContentFilter(final TextBlockCondition cond) {
		this.cond = cond;
    }
    
    public boolean process(TextDocument doc)
            throws BoilerpipeProcessingException {

        List<TextBlock> tbs = doc.getTextBlocks();
        if (tbs.size() < 3) {
            return false;
        }
        
        TextBlock a = tbs.get(0);
        TextBlock b = tbs.get(1);
        TextBlock c;
        boolean hasChanges = false;
        for (Iterator<TextBlock> it= tbs.listIterator(2);it.hasNext();) {
            c = it.next();
            if(!b.isContent() && a.isContent() && c.isContent() && cond.meetsCondition(b)) {
            	b.setIsContent(true);
            	hasChanges = true;
            }
            
            a = c;
            if(!it.hasNext()) {
            	break;
            }
            b = it.next();
        }

        return hasChanges;
    }

}
