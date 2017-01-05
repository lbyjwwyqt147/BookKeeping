package pers.liujunyi.bookkeeping.cache;



import pers.liujunyi.bookkeeping.entity.TCoreUser;

public class MemcachedUtilTest {

	public static void main(String[] strings){
		
		MemcachedUtil.put("hello", "word", 60);
		
		String hello = (String) MemcachedUtil.get("hello");
		
		System.out.println(hello);
		//Assert.assertEquals("word", hello);
		
		 for(int i = 0; i < 10; ++i) {  
	            TCoreUser  user = new TCoreUser();
	            user.setLoginUser("login_"+i);
	            user.setLoginPwd("123456"+i);
	            MemcachedUtil.put("login_" + i, user, 60);  
	            Object obj = MemcachedUtil.get("login_" + i);
	            TCoreUser  u = (TCoreUser) obj;
	            System.out.println(u.getLoginUser());
	            //Assert.assertEquals(user, obj);  
	        } 
		
	}
}
