package com.asmierciak.cryptography.hashing;

import com.asmierciak.util.bytes.ArrayConversions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MessageChunkTests {
    private final int[] expectedWords;

    private final int[] expectedHash;

    private final MessageChunk chunk;

    public MessageChunkTests(byte[] inputData, int[] expectedWords, int[] expectedHash) {
        this.expectedWords = expectedWords;
        this.expectedHash = expectedHash;

        chunk = new MessageChunk(inputData);
        chunk.calculateHash(new int[]{0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0});
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
                                words,
                                new int[]{-705105746,744338852,-1470457558,429666915,135857433}
                        },
                        { // "Ala ma kota"
                                new byte[]{65,108,97,32,109,97,32,107,111,116,97,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,88},
                                new int[]{1097621792,1835081835,1869898112,0,0,0,0,0,0,0,0,0,0,0,0,88,1546649920,-624803626,-555170896,-1201667456,-1249607251,-1110341791,1891632385,1795752939,-1010627517,1409584047,1807974583,-146399866,-1023405051,-1406922979,-1217645681,-443109696,-629177507,60159213,-898443051,1360017012,195217544,1629267589,-1426187918,-1569465319,650596952,340981612,-2121981699,1500179822,-684183332,1381388107,1024362909,647065041,-919040714,-139233072,-846677278,1086185811,61813741,-1184686486,-485210167,641657491,-1372339213,-1287763402,2048295073,-882573045,-1253080780,1868920995,-1198619437,-2069430964,-1949492983,2038214349,1630529988,1928187511,1494267834,573492029,243899709,-1855073114,-74636596,526184858,2117499374,1522608678,-1759869042,1874226809,-837397118,-145096365},
                                new int[]{-591901441,-1429603254,2030733122,932868746,889039021}
                        },
                        { // "The quick brown fox jumps over the lazy dog"
                                new byte[]{84,104,101,32,113,117,105,99,107,32,98,114,111,119,110,32,102,111,120,32,106,117,109,112,115,32,111,118,101,114,32,116,104,101,32,108,97,122,121,32,100,111,103,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,88},
                                new int[]{1416127776,1903520099,1797284466,1870097952,1718581280,1786080624,1931505526,1701978228,1751457900,1635416352,1685022592,0,0,0,0,344,-1369813380,-17761082,-767493868,1454414425,-679487711,-1165006815,-1679054202,-1494278565,834585442,146955968,562102686,-836980106,-1082453053,907858383,-145052391,-825630707,-143570038,-1347343732,-722493619,-1432086775,-1195603504,-47812111,-279468055,-1330621811,886173966,208716711,-179585221,-857011332,-103028389,-512623087,1194234892,1344725678,-327305295,-1667836836,-1837328879,-299256726,1699489404,2100729592,-452793086,324316145,-1583161608,1914692363,460379905,-1004322818,1397583937,-1373455731,350610051,424925829,-486372438,680182652,-366511149,1753034125,-149753721,1422915758,1886517736,504677873,440333818,-577778493,2015113925,814004744,-630498036,927290567,1847874963,-347274743},
                                new int[]{-930103611,-1973453453,1422508515,-1421569341,1472268578}
                        },
                        { // "Zażółć gęślą jaźń"
                                new byte[]{90,97,-59,-68,-61,-77,-59,-126,-60,-121,32,103,-60,-103,-59,-101,108,-60,-123,32,106,97,-59,-70,-59,-124,-128,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-48},
                                new int[]{1516357052,-1011628670,-997777305,-996555365,1824818464,1784792506,-981172224,0,0,0,0,0,0,0,0,208,1036897207,240386098,1351043887,644585261,1311246885,1976376618,-941740453,-1672473110,-1878938310,-1827382061,-1716601461,1825862703,-1166496275,-658030365,764355463,1358524874,1259920002,759812247,-1343153513,-388740780,1032761864,1029088617,621400933,-999877130,-4006220,-285190627,-1870643698,1175144025,-1963797250,1272009884,187357298,1716175018,-1577806726,1131294552,-915755914,1694137404,-1573440105,-149647126,1782010687,-566923859,1933953108,10803271,442127958,885738842,1184350904,-2119523051,-398154533,2064417231,887270043,-1628938255,337026288,663623215,553646018,2033944901,-1385147585,180717519,1234009016,267334566,-2076368990,-1228600399,59196431,255943802,-1910067811,-649315331},
                                new int[]{475405260,1240989880,2145874240,-1364137051,1267724300}
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
        assertThat(chunk.getHash(), is(notNullValue()));
    }

    @Test
    public void testHashIsValid() {
        assertThat(chunk.getHash(), is(equalTo(expectedHash)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testThrowsIfHashGivenIsNot20Bytes() {
        chunk.calculateHash(new int[6]);
    }
}
