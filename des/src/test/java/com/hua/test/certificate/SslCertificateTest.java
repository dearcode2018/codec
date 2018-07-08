/**
 * 描述: 
 * SslCertificateTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.certificate;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.crypto.Cipher;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SslCertificateTest
 */
public final class SslCertificateTest extends BaseTest {

	/**
	 * 证书加密
	 * 1.生成keyStore文件
	 * keytool -genkey -validity 有效期(天数) -alias 别名 -keyalg 算法(例如 RSA) -	keystore 存储路径
	 * 示例:     keytool -genkey -validity 36000 -alias www.zlex.org -keyalg RSA -keystore d:\zlex.keystore  
	 * 
	 * 2.生成自签名证书
	 * 证书是对外公开的公钥凭证, 导出证书 
	 * keytool -export -keystore keyStore文件的存放路径 -alias 导出keyStore文件中指定的别名 -file 证书存放路径 -rfc
	 * 示例: keytool -export -keystore d:\zlex.keystore -alias www.zlex.org -file d:\zlex.cer -rfc 
	 * 
	 * 3. 通过keyStore/证书文件 展开程序编写
	 * 
	 */
	/* Java密钥库 (Java Key Store , JKS) */
	public static final String KEY_STORE = "JKS";
	
	public static final String X509 = "X.509";
	
	public static final String SunX509 = "SunX509";  

	public static final String SSL = "SSL";  	
	
    private String password = "123456";  
    private String alias = "www.hua.com";  
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHttps() {
		try {
			String keyStorePath = ProjectUtil.getAbsolutePath("/doc/hua.keystore", true);
	    	String certificatePath = ProjectUtil.getAbsolutePath("/doc/hua.cer", true);
			URL url = new URL("https://www.hua.com:8080/examples/");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			configSSLSocketFactory(connection, password, keyStorePath, keyStorePath);
			
			InputStream inputStream = connection.getInputStream();
			int length = connection.getContentLength();
			
			DataInputStream dataInputStream = new DataInputStream(inputStream);
		    byte[] data = new byte[length];  
		    dataInputStream.readFully(data); 
		    dataInputStream.close();
		    System.err.println(new String(data));  
		    connection.disconnect();  
		} catch (Exception e) {
			log.error("testHttps =====> ", e);
		}
	}
    
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCertificate() {
		try {
				String keyStorePath = ProjectUtil.getAbsolutePath("/doc/hua.keystore", true);
		    	String certificatePath = ProjectUtil.getAbsolutePath("/doc/hua.cer", true);
		      	System.err.println("公钥加密——私钥解密");  
		        String inputStr = "Ceritifcate";  
		        byte[] data = inputStr.getBytes();  
		  
		        byte[] encrypt = encryptByPublicKey(data,  
		                certificatePath);  
		  
		        byte[] decrypt = decryptByPrivateKey(encrypt,  
		                keyStorePath, alias, password);  
		        String outputStr = new String(decrypt);  
		  
		        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
		  
		        // 验证数据一致  
		        assertArrayEquals(data, decrypt);  
		  
		        // 验证证书有效  
		        assertTrue(verifyCertificate(certificatePath)); 			
			
		} catch (Exception e) {
			log.error("testCertificate =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param keyStorePath KeyStore存放路径
	 * @param alias 别名
	 * @param password 密钥库密码
	 * @return
	 * @author qianye.zheng
	 */
	private static PrivateKey getPrivateKey(String keyStorePath, String alias, String password) throws Exception
	{
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		// 私钥
		PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
		
		return privateKey;
	}
	
	/**
	 * 
	 * @description 获取KeyStore
	 * @param keyStorePath KeyStore存放路径
	 * @param password 密钥库密码
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception
	{
		InputStream inputStream = new FileInputStream(keyStorePath);
		KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
		// 加载keyStore文件
		keyStore.load(inputStream, password.toCharArray());
		// 关闭流
		IOUtil.close(inputStream);
		
		return keyStore;
	}
	
	/**
	 * 
	 * @description 由证书获取公钥
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static PublicKey getPublicKey(String certificatePath) throws Exception
	{
		//  java.security.cert.Certificate;  
		Certificate certificate = getCertificate(certificatePath);
		PublicKey publicKey = certificate.getPublicKey();
		
		return publicKey;
	}
	
	/**
	 * 
	 * @description 获取证书
	 * @param certificatePath 证书存放路径
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static Certificate getCertificate(String certificatePath) throws Exception
	{
		InputStream inputStream = new FileInputStream(certificatePath);
		CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
		Certificate certificate = certificateFactory.generateCertificate(inputStream);
		
		// 关闭流
		IOUtil.close(inputStream);
		
		return certificate;
	}
	
	/**
	 * 
	 * @description 根据KeyStore获取证书
	 * @param keyStorePath keyStore存储路径
	 * @param alias 别名
	 * @param password 密钥库密码
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception
	{
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		Certificate certificate = keyStore.getCertificate(alias);
		
		return certificate;
	}
	
	/**
	 * 
	 * @description 私钥加密
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception
	{
		// 取得私钥
		PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 私钥解密
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception
	{
		// 取得私钥
		PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 公钥加密
	 * @param data
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception
	{
		// 取得公钥
		PublicKey publicKey = getPublicKey(certificatePath);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 公钥解密
	 * @param data
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception
	{
		// 取得公钥
		PublicKey publicKey = getPublicKey(certificatePath);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @description 验证证书有效性
	 * @param certificatePath
	 * @return
	 * @author qianye.zheng
	 */
	public static boolean verifyCertificate(String certificatePath)
	{
		return verifyCertificate(new Date(), certificatePath);
	}
	
	/**
	 * 
	 * @description 验证证书有效性
	 * @param date 当前日期
	 * @param certificatePath
	 * @return
	 * @author qianye.zheng
	 */
	public static boolean verifyCertificate(Date date, String certificatePath)
	{
		boolean status = false;
		try
		{
			// 取得证书
			Certificate certificate = getCertificate(certificatePath);
			// 验证证书是否过期或是否有效
			status = verifyCertificate(date, certificate);
		} catch (Exception e)
		{
			status = false;
			e.printStackTrace();
		}
		
		return status;
	}
	
	/**
	 * 
	 * @description 
	 * @param date
	 * @param certificate
	 * @return
	 * @author qianye.zheng
	 */
	private static boolean verifyCertificate(Date date, Certificate certificate)
	{
		boolean status = true;
		try
		{
			// 取得证书
			X509Certificate x509Certificate = (X509Certificate) certificate;
			// 验证证书是否过期或是否有效
			x509Certificate.checkValidity(date);
		} catch (Exception e)
		{
			status = false;
			e.printStackTrace();
		}
		
		return status;
	}
	
	/**
	 * 
	 * @description 私钥签名
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static byte[] sign(final byte[] data, String keyStorePath, String alias,  
            String password) throws Exception
	{
		
		X509Certificate x509Certificate = (X509Certificate) getCertificate(keyStorePath, alias, password);
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		// 取得私钥
		PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());

		
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
		signature.initSign(privateKey);
		signature.update(data);
		// 生成签名字节
		byte[] bts = signature.sign();
		
		return bts;
	}
	
	/**
	 * 
	 * @description 公钥验证签名是否正确
	 * @param data 数据
	 * @param certificatePath
	 * @param sign 签名值
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static boolean verify(final byte[] data, final String certificatePath, final byte[] sign) throws Exception
	{
		boolean flag = false;
		// 取得证书
		X509Certificate certificate = (X509Certificate) getCertificate(certificatePath);
		
		// 获得公钥
		PublicKey publicKey = certificate.getPublicKey();
		// 用公钥校验数字签名
		Signature signature = Signature.getInstance(certificate.getSigAlgName());
		signature.initVerify(publicKey);
		signature.update(data);
		// 验证签名是否正确
		flag = signature.verify(sign);
		
		return flag;
	}		
	
	/**
	 * 
	 * @description 
	 * @param date
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @author qianye.zheng
	 */
	public static boolean verifyCertificate(Date date, String keyStorePath, String alias, String password)
	{
		boolean status = true;
		try
		{
			// 取得证书
			Certificate certificate = getCertificate(keyStorePath, alias, password);
			// 验证证书是否过期或是否有效
			status = verifyCertificate(date, certificate);
		} catch (Exception e)
		{
			status = false;
			e.printStackTrace();
		}
		
		return status;
	}
	
	/**
	 * 
	 * @description 验证证书
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @author qianye.zheng
	 */
	public static boolean verifyCertificate(String keyStorePath, String alias, String password)
	{
		return verifyCertificate(new Date(), keyStorePath, alias, password);
	}
	
	/**
	 * 
	 * @description 获得SSLSocketFactory
	 * @param password
	 * @param keyStorePath
	 * @param trustKeyStorePath
	 * @return
	 * @throws Exception
	 * @author qianye.zheng
	 */
	private static SSLSocketFactory getSSLSocketFactory(final String password, final String keyStorePath,
			final String trustKeyStorePath) throws Exception
	{
		// 初始化密钥库
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(SunX509);
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		keyManagerFactory.init(keyStore, password.toCharArray());
		
		// 初始化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(SunX509);
		KeyStore trustKeyStore = getKeyStore(trustKeyStorePath, password);
		trustManagerFactory.init(trustKeyStore);
		
		// 初始化 SSL 上下文
		SSLContext sslContext = SSLContext.getInstance(SSL);
		sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		
		return sslSocketFactory;
	}
	
	/**
	 * 
	 * @description 
	 * @param connection
	 * @param password
	 * @param keyStorePath
	 * @param trustKeyStorePath
	 * @throws Exception
	 * @author qianye.zheng
	 */
	public static void configSSLSocketFactory(final HttpsURLConnection connection, final String password,
			final String keyStorePath, final String trustKeyStorePath) throws Exception
	{
		connection.setSSLSocketFactory(getSSLSocketFactory(password, keyStorePath, trustKeyStorePath));
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
