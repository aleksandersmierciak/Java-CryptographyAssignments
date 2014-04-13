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

@RunWith(Parameterized.class)
public class TextMergerTests {
    private final String expectedOutput;

    private final String actualOutput;

    public TextMergerTests(String[] input, String expectedOutput) {
        this.expectedOutput = expectedOutput;

        TextMerger merger = new TextMerger();
        actualOutput = merger.merge(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                new String[]{"Ala", " ma", " ko", "ta,"}, "Ala ma kota,",
                        },
                        {
                                new String[]{"a ko", "t ma", " Ale"}, "a kot ma Ale",
                        },
                        {
                                new String[]{"A qu", "ick ", "brow", "n fo", "x ju", "mps ", "over", " the", " laz", "y do", "g."},
                                "A quick brown fox jumps over the lazy dog."
                        }
                };
        return Arrays.asList(data);
    }

    @Test
    public void testMergedFragmentsAreNotNull() {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testMergedFragmentsAreValid() {
        assertThat(actualOutput, is(equalTo(expectedOutput)));
    }
}
