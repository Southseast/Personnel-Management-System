package cc.southseast.controller.verification.sm2;

import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

/**
 * @Author: Southseast
 * @Date: 2019/1/4 8:22 PM
 * @Version 1.0
 * SM2
 */
public class SM2Result {

    // 签名r
    public BigInteger r;
    public BigInteger s;
    // 验签R
    public BigInteger R;
    // 密钥交换
    public byte[] sa;
    public byte[] sb;
    public byte[] s1;
    public byte[] s2;
    public ECPoint keyra;
    public ECPoint keyrb;

    public SM2Result() {
    }
}
