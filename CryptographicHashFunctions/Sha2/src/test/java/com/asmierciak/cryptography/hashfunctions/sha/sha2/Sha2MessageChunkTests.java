package com.asmierciak.cryptography.hashfunctions.sha.sha2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class Sha2MessageChunkTests {
    private final int[] expectedWords;

    private final int[] expectedHash;

    private final Sha2MessageChunk chunk;

    public Sha2MessageChunkTests(byte[] inputData, int[] expectedWords, int[] expectedHash) {
        this.expectedWords = expectedWords;
        this.expectedHash = expectedHash;

        chunk = new Sha2MessageChunk(inputData);
        chunk.calculateHash(Arrays.copyOf(Sha2MessageChunk.INITIAL_HASH, Sha2MessageChunk.INITIAL_HASH.length));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        { // "Ala ma kota"
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,88},
                                new int[]{1097621792,1835081835,1869898112,0,0,0,0,0,0,0,0,0,0,0,0,88,-724001227,-2099732583,-866269104,1545553680,1561422039,1266491997,-1721368120,173044174,1137943992,54139086,43721866,-1399883693,1112672820,-328302128,-21844038,729607670,-555678303,986318234,373321467,-535817584,2140500330,1472095368,1553445232,2038827304,-948870291,-1130451784,-311305983,-1840427526,-1915929538,439503488,-2076603476,2014210359,1112336732,288978889,-880209475,-1974579490,-937549678,-771373414,-2036782975,-1288084434,1474361588,-1894079515,-1891297080,-2084127266,553891117,1436825249,-546671587,-132423680},
                                new int[]{-1472064261,-915373898,-728300260,-1725294170,-603979822,-130296967,-1748211738,339869781}
                        },
                        { // "The quick brown fox jumps over the lazy dog"
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,88},
                                new int[]{1416127776,1903520099,1797284466,1870097952,1718581280,1786080624,1931505526,1701978228,1751457900,1635416352,1685022592,0,0,0,0,344,1215789539,-946165979,1438141474,147346470,1286988929,-2073348400,777899157,1229215520,1652101295,775843077,395476701,1205838276,-182713228,1395206545,2082732661,642658196,-204074849,242423513,-1074137063,1506285376,-550455748,1721749689,-15973225,1712105681,-1330780685,-686968627,667645411,-1951436852,-1834549607,-934013613,709641788,82325619,-316961708,-1359214418,-1851811226,-1045457949,1880188090,-303793709,654621989,-1894173997,375831977,1200352507,-965686301,646706396,-1189717819,-521723207,-1958625630,-403742313},
                                new int[]{1839142220,1282396687,760981322,179845397,1011351397,-768118038,226424596,-605480839}
                        },
                        { // "Zażółć gęślą jaźń"
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-48},
                                new int[]{1516357052,-1011628670,-997777305,-996555365,1824818464,1784792506,-981172224,0,0,0,0,0,0,0,0,208,1190360467,-484056849,-143748609,2096467144,280544706,461339298,1071768906,-1728641375,-1322679964,1527862435,1839731097,-1853110143,644115463,-581534384,-189401695,-1785332553,-1577598212,-667729322,-1638311566,603496891,263284033,1072229513,84579676,1709660979,-1168219340,2069454632,1923423283,925802259,-1189795114,-1629556237,-1921937216,345418394,2110776920,1805584902,1071016118,471857565,-697104271,-279908738,-1151301164,896867899,-2074933307,398761544,-112214199,-613433077,427044383,1733737302,-238133571,1788238274},
                                new int[]{1380541078,-1060754890,-1214055097,-2127400771,1909620895,606854665,1755817925,1812012222}
                        }
                };
        return Arrays.asList(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIfChunkSizeInvalid() {
        new Sha2MessageChunk(new byte[1]);
    }

    @Test
    public void testWordsAreNotNull() {
        assertThat(chunk.getWords(), is(notNullValue()));
    }

    @Test
    public void testThereAre64Words() {
        assertThat(chunk.getWords().length, is(equalTo(64)));
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
        assertThat(chunk.getHash(), is(notNullValue()));
    }

    @Test
    public void testHashIsValid() {
        assertThat(chunk.getHash(), is(equalTo(expectedHash)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfHashGivenIsNot32Bytes() {
        chunk.calculateHash(new int[6]);
    }
}
