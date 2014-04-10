import com.asmierciak.cryptography.cryptosystems.RsaKeyGenerator;
import com.asmierciak.cryptography.cryptosystems.RsaPrivateKey;
import com.asmierciak.cryptography.cryptosystems.RsaPublicKey;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RsaKeyGeneratorTests {
    private final BigInteger p;

    private final BigInteger q;

    /**
     * Represents the result of multiplying p and q (symbol: n).
     */
    private final BigInteger modulus;

    /**
     * Represents the public exponent (symbol: e).
     */
    private final BigInteger publicExponent;

    /**
     * Represents the private exponent (symbol: d).
     */
    private final BigInteger privateExponent;

    public RsaKeyGeneratorTests(BigInteger p, BigInteger q, BigInteger modulus, BigInteger publicExponent, BigInteger privateExponent) {
        this.p = p;
        this.q = q;
        this.modulus = modulus;
        this.publicExponent = publicExponent;
        this.privateExponent = privateExponent;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                { {
                    BigInteger.valueOf(61), BigInteger.valueOf(53),
                    BigInteger.valueOf(3233), BigInteger.valueOf(17), BigInteger.valueOf(2753)
                } };
        return Arrays.asList(data);

    }

    @Test
    public void publicKeyTests() {
        RsaKeyGenerator generator = new RsaKeyGenerator();

        RsaPublicKey publicKey = generator.getPublicKey();
        Assert.assertEquals(publicKey.getModulus(), modulus);
        Assert.assertEquals(publicKey.getPublicExponent(), publicExponent);

        RsaPrivateKey privateKey = generator.getPrivateKey();
        Assert.assertEquals(privateKey.getModulus(), modulus);
        Assert.assertEquals(publicKey.getPublicExponent(), publicExponent);
    }
}
