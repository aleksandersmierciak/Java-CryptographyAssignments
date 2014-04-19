package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.bytes.ArrayConversions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageChunkTests {
    private final int[] expectedWords;

    private final MessageChunk chunk;

    public MessageChunkTests(byte[] inputData, int[] expectedWords) {
        this.expectedWords = expectedWords;
        chunk = new MessageChunk(inputData);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String input = "11000110001110100101001010010111010001110111101111111111101101100110100100101001011010010101101101100110110111111010100111000010101001010010111000001101101111011101101110001001111111101011010110000001110011100111001011010010001111100010000100001101111011111010110011110100011110010000101110010101100111000110111101010100111010001101110011101100010001011100101001110010110101110100110100110001010010110111100111001111110101111011111111111100001011010100100010100111011111001001111110001000011001001101010001110011";
        // Bit-compliant representation of word values, written as 2-complement signed integers
        int[] words = {
                -969256297, 1199308726, 1764321627, 1725934018, -1523708483, -611713355, -2117176622, 1042353647,
                -1393264373, -1784909996, -388174779, -898443443, 827029967, -675283923, 1218935967, -2006657933,
                -1464762923, -113341569, 1501477281, -1087636513, -651486273, -690786900, -1251141325, -169518353,
                1955924590, 662328490, -367465080, -1394100829, 259691659, -970895116, -214344779, 394994385,
                -2030617124, 615317375, -74655090, -2016396264, -1893674256, 1027046002, 1780672993, -1804993336,
                1251688592, -1977094353, 353287819, 1327182783, -225843773, -215519815, -1578141586, -1416656566,
                -1987078921, 292071501, -1806008449, -119029180, 231140670, -1673058055, -211671370, 1880026919,
                -1799715798, 1315742934, 128423456, -1597886255, 563718282, -2037337003, -142477828, -689910443,
                536852462, -1597737402, -1850240408, -1220879611, -906616, -153238585, 1321670858, 786520152,
                -193016762, 22803734, 852617525, -903010418, 1365444048, 687384549, -651625812, 323865910
        };

        Object[][] data = new Object[][]
                {
                        {
                                ArrayConversions.binaryStringToByteArray(input),
                                words
                        }
                };
        return Arrays.asList(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfChunkSizeInvalid() {
        new MessageChunk(new byte[1]);
    }

    @Test
    public void testWordsAreNotNull() {
        assertThat(chunk.getWords(), is(notNullValue()));
    }

    @Test
    public void testThereAre80Words() {
        assertThat(chunk.getWords().length, is(equalTo(80)));
    }

    @Test
    public void testInitialWordsAreValid() {
        int[] actual = Arrays.copyOfRange(chunk.getWords(), 0, 16);
        int[] expected = Arrays.copyOfRange(expectedWords, 0, 16);
        assertThat(actual, is(expected));
    }

    @Test
    public void testAllWordsAreValid() {
        assertThat(chunk.getWords(), is(expectedWords));
    }

    @Test
    public void testHashIsNotNull() {
        chunk.calculateHash(new int[5]);
        assertThat(chunk.getHash(), is(notNullValue()));
    }

    @Test
    public void testHashIsValid() {
        chunk.calculateHash(new int[5]);
        assertThat(chunk.getHash(),
                is(new int[]{652943671, 1496183782, 1736810233, 1509514887, -393992723}));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfHashGivenIsNot20Bytes() {
        chunk.calculateHash(new int[6]);
    }
}
