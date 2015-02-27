/**
 * boilerpipe
 *
 * Copyright (c) 2009 Christian Kohlschütter
 *
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package boilerpipe.filters.simple;



import boilerpipe.BoilerpipeFilter;
import boilerpipe.BoilerpipeProcessingException;
import boilerpipe.document.TextBlock;
import boilerpipe.document.TextDocument;

/**
 * Marks all blocks as boilerplate.
 * 
 * @author Christian Kohlschütter
 */
public final class MarkEverythingBoilerplateFilter implements BoilerpipeFilter {
    public static final MarkEverythingBoilerplateFilter INSTANCE = new MarkEverythingBoilerplateFilter();
    private MarkEverythingBoilerplateFilter() {
    }

    public boolean process(final TextDocument doc)
            throws BoilerpipeProcessingException {

        boolean changes = false;

        for (TextBlock tb : doc.getTextBlocks()) {
            if (tb.isContent()) {
                tb.setIsContent(false);
                changes = true;
            }
        }

        return changes;

    }
}
