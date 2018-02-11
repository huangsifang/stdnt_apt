package stdnt_apt;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.testng.annotations.Test;

import com.hsf.stdntapt.tool.EnumConverter;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-27
 * <p>
 * Version: 1.0
 */
public class PasswordTest extends BaseTest {

	@Test
	public void testGeneratePassword() {
		String algorithmName = "md5";
		String username = "liu";
		String password = "123";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;

		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(salt2);
		System.out.println(encodedPassword);
	}

	@Test
	public void testHashedCredentialsMatcherWithJdbcRealm() {

		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

		// 浣跨敤testGeneratePassword鐢熸垚鐨勬暎鍒楀瘑鐮�
		login("classpath:shiro-password.ini", "liu", "123");
	}

	@Test(expectedExceptions = ExcessiveAttemptsException.class)
	public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
		for (int i = 1; i <= 5; i++) {
			try {
				login("classpath:shiro-password.ini", "liu", "234");
			} catch (Exception e) {
				/* ignore */}
		}
		login("classpath:shiro-password.ini", "liu", "234");
	}
}
