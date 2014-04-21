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
                                new byte[]{0, -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8}
                        },
                        {
                                new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
                                new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 120}
                        },
                        {
                                new byte[]{-91, 4, -24, -20, -37, -50, -41, 109, 92, 63, -39, -78, -6, 66, -67, 95, -38, -35, -29, -44, -30, -30, -07, -31, 116, -36, -52, 13, -76, -32, 31, -47, 76, -31, 16, 104, -8, -50, -40, -52, -67, 56, 11, 100, -31, 85, -24, -48, 88, -55, -48, 33, -36, 83, -98, 90, -52, -67, 116, -74, 72, 76, 124, 34, -96},
                                new byte[]{-91, 4, -24, -20, -37, -50, -41, 109, 92, 63, -39, -78, -6, 66, -67, 95, -38, -35, -29, -44, -30, -30, -7, -31, 116, -36, -52, 13, -76, -32, 31, -47, 76, -31, 16, 104, -8, -50, -40, -52, -67, 56, 11, 100, -31, 85, -24, -48, 88, -55, -48, 33, -36, 83, -98, 90, -52, -67, 116, -74, 72, 76, 124, 34, -96, -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 8}
                        },
                        { // "Ala ma kota"
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97},
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,88}
                        },
                        { // "The quick brown fox jumps over the lazy dog"
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103},
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,88}
                        },
                        { // "Zażółć gęślą jaźń"
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124},
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-48}
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
        assertThat(last8Bytes, is(equalTo((long)input.length * 8L)));
    }

    @Test
    public void testOriginalMessageHasOneAppendedFirst() {
        byte one = actualOutput[input.length];
        assertThat(one, is(equalTo((byte)0x80)));
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
