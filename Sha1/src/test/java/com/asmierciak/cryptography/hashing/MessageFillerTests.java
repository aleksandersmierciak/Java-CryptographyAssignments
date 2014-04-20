package com.asmierciak.cryptography.hashing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.asmierciak.util.numbers.LongConversions.bytesToLong;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class MessageFillerTests {
    private byte[] input;

    private byte[] actualOutput;

    private byte[] expectedOutput;

    public MessageFillerTests(byte[] input, byte[] expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.actualOutput = MessageFiller.fillMessage(input);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {
                                new byte[]{0},
                                new byte[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                        },
                        {
                                new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
                                new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15}
                        },
                        {
                                new byte[]{-91, 4, -24, -20, -37, -50, -41, 109, 92, 63, -39, -78, -6, 66, -67, 95, -38, -35, -29, -44, -30, -30, -07, -31, 116, -36, -52, 13, -76, -32, 31, -47, 76, -31, 16, 104, -8, -50, -40, -52, -67, 56, 11, 100, -31, 85, -24, -48, 88, -55, -48, 33, -36, 83, -98, 90, -52, -67, 116, -74, 72, 76, 124, 34, -96},
                                new byte[]{-91, 4, -24, -20, -37, -50, -41, 109, 92, 63, -39, -78, -6, 66, -67, 95, -38, -35, -29, -44, -30, -30, -7, -31, 116, -36, -52, 13, -76, -32, 31, -47, 76, -31, 16, 104, -8, -50, -40, -52, -67, 56, 11, 100, -31, 85, -24, -48, 88, -55, -48, 33, -36, 83, -98, 90, -52, -67, 116, -74, 72, 76, 124, 34, -96, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65}
                        }
                };
        return Arrays.asList(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsNull() {
        MessageFiller.fillMessage(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfInputIsEmpty() {
        MessageFiller.fillMessage(new byte[0]);
    }

    @Test
    public void testResultingMessageIsNotNull() {
        assertThat(actualOutput, is(notNullValue()));
    }

    @Test
    public void testResultingMessageLengthIsDivisibleBy64() {
        assertThat(actualOutput.length % 64, is(equalTo(0)));
    }

    @Test
    public void testResultingMessageLast8BytesAreOriginalMessageLength() {
        long last8Bytes =
                bytesToLong(Arrays.copyOfRange(actualOutput, actualOutput.length - 8, actualOutput.length));
        assertThat(last8Bytes, is(equalTo((long)input.length)));
    }

    @Test
    public void testOriginalMessageHasOneAppendedFirst() {
        byte one = actualOutput[input.length];
        assertThat(one, is(equalTo((byte)1)));
    }

    @Test
    public void testResultingMessageIsPaddedWithZerosForLengthToBeMultipleOf448() {
        byte[] allegedZeros =
                Arrays.copyOfRange(actualOutput, input.length + 1, actualOutput.length - 8);
        int transientLength = input.length + 1;
        int paddedLength = -(transientLength % 64) + 56;
        if (paddedLength < 0) paddedLength += 64;
        byte[] expectedZeros = new byte[paddedLength];
        Arrays.fill(expectedZeros, (byte)0);
        assertThat(allegedZeros, is(equalTo(expectedZeros)));
    }

    @Test
    public void testResultingMessageIsValid() {
        assertThat(actualOutput, is(equalTo(expectedOutput)));
    }
}
