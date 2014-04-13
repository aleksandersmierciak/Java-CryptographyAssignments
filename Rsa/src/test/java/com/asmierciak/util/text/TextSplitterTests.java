package com.asmierciak.util.text;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class TextSplitterTests {
    private final int limit;

    private final String[] expectedOutput;

    private final String[] actualOutput;

    public TextSplitterTests(String input, int limit, String[] expectedOutput) {
        this.limit = limit;
        this.expectedOutput = expectedOutput;

        TextSplitter splitter = new TextSplitter();
        actualOutput = splitter.split(input, limit);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                "Ala ma kota,", 3, new String[]{"Ala", " ma", " ko", "ta,"},
                        },
                        {
                                "a kot ma Ale", 4, new String[]{"a ko", "t ma", " Ale"},
                        },
                        {
                                "A quick brown fox jumps over the lazy dog.",
                                4, new String[]{"A qu", "ick ", "brow", "n fo", "x ju", "mps ", "over", " the", " laz", "y do", "g."},
                        }
                };
        return Arrays.asList(data);
    }

    @Test
    public void testSplitFragmentsAreNotNull() {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testSplitFragmentsAreShorterThanLimit() {
        // TODO: see if it is possible to use Hamcrest
        //       to compare every String's length
        for (String element : actualOutput) {
            if (element.length() > limit) {
                fail("Exceeded limit of " + limit + " with '" + element + "'");
            }
        }
    }

    @Test
    public void testSplitFragmentsAreValid() {
        assertThat(actualOutput, is(equalTo(expectedOutput)));
    }
}
