package com.doublecui.demo;


import org.ethereum.crypto.ECIESCoder;
import org.ethereum.crypto.ECKey;
import org.spongycastle.crypto.InvalidCipherTextException;

import java.io.IOException;
import java.util.Base64;

public class FOSECDSAUtil {
    public static void main(String[] args) {
        try {
            byte[] testEnc2 = decryptBASE64("zM4tAA68byjvRfH7xweTewLKACBuSj3ha0awj68tOndRhEfr/BlNTxMC6FW8FOIqPs2T6wAgb9fNJexJhcP9J5Vnm9EU0s5Otvktdv0L9ios/fo6L3G5SQ3MzSDMTGWHZyWfVSILQsLSwqS/+BqMHJABrAJwq5mCPbAz6EC5xSiYlz4SlEOV33t1zDee7r3uhvqg3plM");
            byte[] pri1 = decryptBASE64("uUJFm79zbBWYDTKbWpmOPOyUF3PExuT0M70M2Js8348=");
            System.out.println(pri1.length);

            ECKey ecKey1 = ECKey.fromPrivate(pri1);
            System.out.println("pub length:" + ECKey.fromPrivate(pri1).getPubKeyPoint().getEncoded().length);
            System.out.println("pub:" + encryptBASE64(ECKey.fromPrivate(pri1).getPubKeyPoint().getEncoded()));
            String pubStr = "BDghHrqxR/yIv3jwfK5LDX0z7lnWFgCZBKn2N4KbG2cjLq9ecJ7YpMp/I0Fu/mFRKpStZa5Q3lY1F4CGGHUfv+c=";
//            String test = "1234123412341231123123123123123123123123123123123123123123123123";
            String test = "1234";
            System.out.println(test.length());
            byte[] testEnc3 = ECIESCoder.encrypt(ecKey1.getPubKeyPoint(), decryptBASE64(test));
//            System.out.println(byteToHex(testEnc3));
            String resultTest2 = encryptBASE64(ECIESCoder.decrypt(ecKey1.getPrivKey(), testEnc3)).replace("\r\n", "");
            System.out.println(resultTest2.length());
            System.out.println("------------" + resultTest2 + "------------------------");
        } catch (InvalidCipherTextException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] decryptBASE64(String source) {
        return Base64.getDecoder().decode(source);
    }

    private static String encryptBASE64(byte[] source) {
        return Base64.getEncoder().encodeToString(source);
    }

    private static String byteToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);

            stringBuilder.append(i + ":");

            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + ";");
        }
        return stringBuilder.toString();
    }

}
