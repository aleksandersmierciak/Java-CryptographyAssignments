package com.asmierciak.util.bytes;

import com.asmierciak.util.bytes.MessageSplitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageSplitterTests {
    private final byte[][] expectedOutput;

    private final byte[][] actualOutput;

    public MessageSplitterTests(byte[] message, int splitCount, byte[][] expectedOutput) {
        this.expectedOutput = expectedOutput;

        actualOutput = MessageSplitter.split(message, splitCount);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                new byte[]{1,2,3,4,5,6,7,8,9}, 1,
                                new byte[][]{new byte[]{1}, new byte[]{2}, new byte[]{3}, new byte[]{4}, new byte[]{5}, new byte[]{6}, new byte[]{7}, new byte[]{8}, new byte[]{9}}
                        },
                        {
                                new byte[]{1,2,3,4,5,6,7,8,9}, 3,
                                new byte[][]{new byte[]{1,2,3}, new byte[]{4,5,6}, new byte[]{7,8,9}}
                        },
                        {
                                new byte[]{1,2,3,4,5,6,7,8,9}, 4,
                                new byte[][]{new byte[]{1,2,3,4}, new byte[]{5,6,7,8}, new byte[]{9}}
                        }
                };
        return Arrays.asList(data);
    }

    @Test
    public void testActualOutputIsNotNull() {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testActualOutputIsValid() {
        assertThat(actualOutput, is(expectedOutput));
    }
}
