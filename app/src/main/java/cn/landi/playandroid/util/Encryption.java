package cn.landi.playandroid.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/7/12
 * @edit TODO
 */
public class Encryption {

    /**
     *
     * @param data 要加密的字符串
     * @param key 私钥:
     *            AES固定格式为128/192/256 bits.即:16/24/32bytes。
     *            DES固定格式为128bits，即8bytes。
     *
     * @param iv 初始化向量参数
     *              AES 为16bytes.
     *              DES 为8bytes.
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key, String iv) throws Exception
    {
        try
        {
            /**
             *
             * AES和DES 一共有4种工作模式:
             *     1.电子密码本模式(ECB) -- 缺点是相同的明文加密成相同的密文，明文的规律带到密文。
             *     2.加密分组链接模式(CBC)
             *     3.加密反馈模式(CFB)
             *     4.输出反馈模式(OFB)四种模式
             *
             * PKCS5Padding: 填充方式
             *
             * 加密方式/工作模式/填充方式
             * DES/CBC/PKCS5Padding
             */
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] dataBytes = data.getBytes();

            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            SecretKeySpec keyspec = new SecretKeySpec(
                    new byte[] {(byte) 0xB8, 0x15, (byte) 0xFC, 0x56, (byte) 0xD6, (byte) 0xF2, 0x47, (byte) 0xdf, (byte) 0x8D, (byte) 0xB7, (byte) 0xa6, 0x77, 0x39, 0x5d, (byte) 0xf3, 0x73, (byte) 0xB8, 0x15, (byte) 0xFC, 0x56, (byte) 0xD6, (byte) 0xF2, 0x47, (byte) 0xdf }, "DES");
            IvParameterSpec ivspec = new IvParameterSpec(new byte[] {0x22, 0x44, 0x66, (byte) 0x88, 0x11, 0x33, 0x55, 0x77});

            /**
             * 初始化，此方法可以采用三种方式，按服务器要求来添加。
             * (1)无第三个参数 --> iv
             * (2)第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)
             * (3)采用此代码中的IVParameterSpec --> 指定好了的
             *
             * 解密使用 DECRYPT_MODE 方式
             */
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(dataBytes);

            return Base64.encodeToString(encrypted, Base64.DEFAULT);

//            return new sun.misc.BASE64Encoder().encode(encrypted);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    public static String desEncrypt(String dataEncode, String key, String iv) throws Exception
//    {
//        try
//        {
//            //先用Base64解码
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(dataEncode);
//
//            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "DES");
//            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
//
//            // 解密使用 DECRYPT_MODE 方式
//            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//
//            byte[] original = cipher.doFinal(encrypted1);
//            String originalString = new String(original);
//            return originalString;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static void main(String args[]) throws Exception
    {
        String data = "5D37B14553DED44778ACA0DE041B640E";
        String key = "B815FC56D6F247df8DB7a677395df373";
        String iv = "2244668811335577";

//        String en = encrypt(data, key, iv);
//        String de = desEncrypt(en, key, iv);

//        System.out.println("明文: " + data);
//        System.out.println("加密: " + en);
//        System.out.println("解密: " + de);


            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] k = new byte[] {(byte) 0xB8, 0x15, (byte) 0xFC, 0x56, (byte) 0xD6, (byte) 0xF2, 0x47,(byte) 0xdf,(byte) 0x8D,(byte) 0xB7,(byte) 0xa6,0x77,0x39,0x5d,(byte) 0xf3,0x73};
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(k));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[] {0x22, 0x44, 0x66, (byte) 0x88, 0x11, 0x33, 0x55, 0x77}));
            byte[] result = cipher.doFinal(data.getBytes());

//            String base64 = Base64.encodeToString(result, Base64.DEFAULT);
            byte[] base64 = Base64.encode(result, Base64.DEFAULT);
            System.out.println("结果: " + new String(base64));
    }

}
