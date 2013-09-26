package edu.knowitall.taggers.tag;

import java.util.Collections;
import java.util.List;
import com.google.common.collect.Lists;
import edu.knowitall.taggers.SentenceFunctions;
import edu.knowitall.taggers.StringFunctions;
import edu.knowitall.tool.typer.Type;
import edu.knowitall.tool.chunk.ChunkedToken;
import edu.knowitall.tool.stem.Lemmatized;

/***
 * Search for exact keyword matches, ignoring case.
 * @author schmmd
 *
 */
public class CaseInsensitiveKeywordTagger extends KeywordTagger {
    public CaseInsensitiveKeywordTagger(String name, List<String> keywords) {
        super(name, Lists
                .transform(keywords, StringFunctions.toLowerCase));
    }

    public CaseInsensitiveKeywordTagger(String name, String keyword) {
        super(name, Collections.singletonList(keyword.toLowerCase()));
    }

    @Override
    public List<Type> findTagsJava(final List<Lemmatized<ChunkedToken>> sentence) {
        return super.findTagsJava(SentenceFunctions.lowercase(sentence));
    }
}
