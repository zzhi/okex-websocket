package com.biup.okex.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyEAESUtil {
	private static final String keyWord = "myenergydomain";
	
	/**
	 * 加密解密工具类
	 * 
	 * @param content 需要加密的内容
	 * @param keyWord 加密密钥
	 * @return byte[] 加密后的字节数组
	 */
	public static byte[] encrypt(String content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyWord.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param content 加密后的内容
	 * @return String 加密后的字符串
	 */
	public static String encrypttoStr(String content) {
		return parseByte2HexStr(encrypt(content));
	}

	/**
	 * 解密
	 * 
	 * @param content  待解密内容
	 * @param keyWord 解密密钥
	 * @return byte[]
	 */
	public static byte[] decrypt(byte[] content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyWord.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param content 待解密内容(字符串)
	 * @param keyWord 解密密钥
	 * @return byte[]
	 */
	public static byte[] decrypt(String content) {
		return decrypt(parseHexStr2Byte(content));
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return String
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return byte[]
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
            return null;
        }
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static int randomnums(String encryptResult) {
		Random randomX = new Random();
		int randomnum = 2 + randomX.nextInt(encryptResult.length() - 1);

		return randomnum;
	}

	public static String jiami(String content) {
		// 原始密文
		String randomnum = encrypttoStr(content);
		// 随机数
		int randomX = randomnums(randomnum);
		// 调换转换后的密文
		String hexrando = randomnum.substring(0, randomX);
		String hexranum = randomnum.substring(randomX);
		String hexrandonum = hexranum + hexrando;
		// 16进制随机数
		String num = Integer.toHexString(randomX);
		// 调换位子
		if (num.length() == 1) {
			num = 0 + num;
		}
		// 最终加密的密文
		String hexrandomX = num + hexrandonum;
		return hexrandomX;
	}

	public static byte[] jiemi(String content) {
		try {
//			System.out.println("password=============" + content);
			// 随机数
			String num = content.substring(0, 2);
			int randomX = Integer.parseInt(num, 16);
			// 调换转换后的密文
			String hexrandonum = (content.substring(2));
			String hexrando = hexrandonum.substring(0, hexrandonum.length() - randomX);
			String hexranum = hexrandonum.substring(hexrandonum.length() - randomX);
			String randomnum = hexranum + hexrando;
//			System.out.println("randomnum=============" + randomnum);
			return decrypt(randomnum);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * cookie
	 * 
	 * @param content 加密后的内容
	 * @return String 加密后的字符串
	 */
	public static String cookieencrypttoStr(String content) {
		return parseByte2HexStr(encrypt(content));
	}

	/**
	 * @param content cookie待解密内容(字符串)
	 * @param keyWord 解密密钥
	 * @return byte[]
	 */
	public static byte[] cookiedecrypt(String content) {
		return decrypt(parseHexStr2Byte(content));
	}

	public static String cookiejiami(String content, String sysid) {
		// 原始密文
		String randomnum = cookieencrypttoStr(content);
		// 随机数
		int randomX = randomnums(randomnum);
		// 16进制随机数和系统ID
		String num1 = Integer.toHexString(randomX);
		if (num1.length() == 1) {
			num1 = 0 + num1;
		}
		String sid = Integer.toHexString(Integer.valueOf(sysid));
		if (sid.length() == 1) {
			sid = 0 + sid;
		}
		// 调换转换后的密文
		String hexrando = randomnum.substring(0, randomX);
		String hexranum = randomnum.substring(randomX);
		// 最终加密密文
		String hexrandomX = num1 + hexranum + sid + hexrando;
		return hexrandomX;
	}

	public static Map cookiejiemi(String content) {
		// 随机数
		String num = content.substring(0, 2);
		int randomX = Integer.parseInt(num, 16);
		// 带SYSid 的密文
		String hexrandomSYSid = content.substring(2, content.length());
		String hexrando = hexrandomSYSid.substring(0, hexrandomSYSid.length() - (randomX + 2));
		String hexranum = hexrandomSYSid.substring(hexrandomSYSid.length() - (randomX + 2), hexrandomSYSid.length());
		String hexrandomSYSidX = hexranum + hexrando;
		String sysid = hexrandomSYSidX.substring(0, 2);
		String hexrandomX = hexrandomSYSidX.substring(2, hexrandomSYSidX.length());
		// 转成10进制的SYSid
		int sys = Integer.parseInt(sysid, 16);
		Map map = new HashMap();
		map.put("sysid", sys);
		map.put("hexrandomX", new String(cookiedecrypt(hexrandomX)));
		return map;
	}

	public static void main(String[] args) {
		String mima = "76f562b6-61b75b55-b938e14a-31a30";
		System.out.println("加密后：" + new String(MyEAESUtil.jiami(mima)));
		String encryptResultStr = "11EB4CFB790A32F067C69D1DD45BC96C709A423A3EDB13B79DA11A9AF0E13A772D6BBC9CB5814D4A57247569838D889141";
		System.out.println("解密后：" + new String(MyEAESUtil.jiemi(encryptResultStr)));
	}

}
