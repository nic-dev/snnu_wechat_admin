/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-10-7  Create	
 */
package snnu.wechat.framework.security;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.shiro.codec.Hex;

/**
 * 
 *
 */
public class VRSAUtils {
	public static final String ALGORITHYM_NAME="RSA";
	public static final String PUBLIC_KEY_HEX_STR="30819f300d06092a864886f70d010101050003818d003081890281810095df2450496d7c9705a8625bad6ea0594c3b4cba443b21122071355efa1a1bdb66e11d5736d6e65cadee26a6f67b556e1368561e69971f29cc895cd4489508ebb195e333e5ee2609710af1775dcdda377e39a54e24cb776d59e367b182c50a1301163a896b28a2a5519e63cf1aa7c3f83f594aa639232d0cea3b85de2e0cbdef0203010001";
	public static final String PUBLIC_KEY_EXP_HEX_STR="10001";
	public static final String PUBLIC_KEY_MODU_HEX_STR="0095df2450496d7c9705a8625bad6ea0594c3b4cba443b21122071355efa1a1bdb66e11d5736d6e65cadee26a6f67b556e1368561e69971f29cc895cd4489508ebb195e333e5ee2609710af1775dcdda377e39a54e24cb776d59e367b182c50a1301163a896b28a2a5519e63cf1aa7c3f83f594aa639232d0cea3b85de2e0cbdef";
	public static final String PRIVATE_KEY_HEX_STR="30820276020100300d06092a864886f70d0101010500048202603082025c0201000281810095df2450496d7c9705a8625bad6ea0594c3b4cba443b21122071355efa1a1bdb66e11d5736d6e65cadee26a6f67b556e1368561e69971f29cc895cd4489508ebb195e333e5ee2609710af1775dcdda377e39a54e24cb776d59e367b182c50a1301163a896b28a2a5519e63cf1aa7c3f83f594aa639232d0cea3b85de2e0cbdef02030100010281804fdcdfb1f4dee614680c699566dedb8d9a44c34742d2ab75c859eb51d83e8f77a3e3f3c9a4cc716713bc2356b763d38b5bc62e95f838727fc8a1ae18e5309dd68139e5ed29a7af01a2bb1795f28080cf774a6d240f776d426c70e060983660ae3e93d36a431922525148573e228026827d94375d0c3f939368e264fb4b3041a1024100d85ca1b22c28514d8a6b832c296aca746b5cd4d113ec9cb1680e1496b9f289d09632e3cb1f7dae0abcabf3e889a8c2c9723eb73731e49fd868bce5217ca7a591024100b1541ee820953b9b4df08a1ea655bd6b71f7d6a65c6a081a69f45ec92d90782d40b374e667619512120922b359c6265d846ae72ac53518bd56dae5926ace6b7f024073e1a12509ab8a682a740cec713e6632d67200f5fe62e345cd82851a797b62beda4365681c74b6ebbf43a5be6d447b1763de1d492daad2c618cadb3a0546d1510241008bc50a375cb743ac3dda82837607cead89bf1769391e3a14d7becd97345ad2de9848dab3607901cea4789ee94eb1683bfdcb6539c906f8e1043cb5069b38e87102401749b244e8ed2c437eae28d05b4237d492ad186cf5a3c638ff7c4be340458f3fa7470e0710c8cf84bc720ffdd677de49d22755a4477c94b5eac3736d26ac258f";
	public static byte[] decrypt(String privateKeyStr,byte[] raw)  {
		try {
			byte[] keyBytes = Hex.decode(privateKeyStr);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHYM_NAME);
			Key pk = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(ALGORITHYM_NAME,
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			
			cipher.init(Cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	public static String decryptJsData(String privateKeyStr,String jsData){
		StringBuilder result=new StringBuilder();
		byte []jsDataArr=new BigInteger(jsData,16).toByteArray();
		result.append(new String(decrypt(privateKeyStr, jsDataArr)));
		return result.reverse().toString();
	}
	public static void main(String[] args) {
		String jsEncoded="01bdb916039c5c9ae734cc592b9d90a89812376ad47cfa6e2d25eece6e2fb49b582e3954492c0296f2434c66a5fcd15d4fa6d5e428a5ea2e0f3ce68e9412edfc331a5e74fb317321254a5d51f4071819dd84deb17420c8d93d2a8ae5264acd5a0cc59e7c15f0251d4bbce9ccb75eb62c002af04592d46a260b8aeabd647f1871";
		jsEncoded="92861bbf430836e256e6827364cb0cfaf9fb3647adb24b59afc06a25f100fa3f0780fc8c125dcc379064b1ab0908a2798d7c33a55e9f9ce7f897765992e61959230fc8e294d79fbb92f4a83b6696bf1147cd66767a002c7133843e754ee025a19af14285f5d96c5b7f38defa99dada528cb91be1e298f4f569da263cf2ff0b1a";
		System.out.println(" result="+(decryptJsData(PRIVATE_KEY_HEX_STR, jsEncoded)));
	
		
		
	}
}
