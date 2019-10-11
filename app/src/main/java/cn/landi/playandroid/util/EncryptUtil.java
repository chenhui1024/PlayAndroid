package cn.landi.playandroid.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	public EncryptUtil() {}
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	/**
	 * strSrc:待加密的内容
	 * encName :MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512 
	 * */
	public static String Encrypt(String strSrc, String encName) {
		MessageDigest md=null;
		String strDes=null;

		byte[] bt=strSrc.getBytes();
		try {
			if (encName==null||encName.equals("")) {
				encName="MD5";
			}
			md= MessageDigest.getInstance(encName);
			md.update(bt);
			strDes=bytes2Hex(md.digest());
		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[]bts) {
		String des="";
		String tmp=null;
		for (int i=0;i<bts.length;i++) {
			tmp=(Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length()==1) {
				des +="0";
			}
			des +=tmp;
		}
		return des;
	}

}
