package com.news.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodeUtil {
    public static String decodeTest(String encryptedText) {

        // 解密Base64字符串
        String decryptedText = new String(Base64.getDecoder().decode(encryptedText), StandardCharsets.UTF_8);
        return decryptedText;
    }

    public static String decodeString(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            return null;
        }

        int key = 0x114;
        int iv = 0x514;

        // Decode the Base64 encoded string
        byte[] byteArr = Base64.getDecoder().decode(encoded);
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for (byte b : byteArr) {
            int charCode = b & 0xFF; // Get unsigned byte value
            String hexCode = Integer.toHexString(charCode);

            if (temp.length() < 4) {
                temp.append(hexCode);
            } else {
                int d = Integer.parseInt(temp.toString(), 16);
                int sum = (d ^ key) - iv;
                String xx = Integer.toHexString(sum);

                // Ensure the sum is a 4-digit hex code
                while (xx.length() < 4) {
                    xx = "0" + xx;
                }

                String x1 = "0x" + xx.substring(0, 2);
                String x2 = "0x" + xx.substring(2, 4);

                result.append((char) Integer.parseInt(x1.substring(2), 16));
                result.append((char) Integer.parseInt(x2.substring(2), 16));

                temp.setLength(0); // Reset temp
                temp.append(hexCode);
            }
        }

        // Final part of the decryption
        int d = Integer.parseInt(temp.toString(), 16);
        int sum = (d ^ key) - iv;
        String xx = Integer.toHexString(sum);

        while (xx.length() < 4) {
            xx = "0" + xx;
        }

        String x1 = "0x" + xx.substring(0, 2);
        String x2 = "0x" + xx.substring(2, 4);

        result.append((char) Integer.parseInt(x1.substring(2), 16));
        result.append((char) Integer.parseInt(x2.substring(2), 16));

        return result.toString();
    }
}
