package autologin;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoLogin {
	
    static WebDriver driver = new FirefoxDriver();
    private static String baseUrl;
    static Wait<WebDriver> wait;
    private static StringBuffer verificationErrors = new StringBuffer();
 /*  
    public AutoLogin(WebDriver driver) {
            this.driver = driver;
    }
*/    
    
    @Test 
    private static boolean loginTest(String username, String password, String name) throws Exception {
    	//logowanie sie i sprawdzenie imienia i nazwiska

    	
    	if(!driver.findElement(By.cssSelector("#panel")).isDisplayed())
    	{
    		return false;
    	}
    		//System.out.println("#panel : " + driver.findElement(By.cssSelector("#panel")).isDisplayed());  	

     	driver.findElement(By.className("ic-user")).click();
    	driver.findElement(By.className("ic-user")).clear();
    	driver.findElement(By.className("ic-user")).sendKeys(username);
    	
    	driver.findElement(By.className("ic-key")).click();
    	driver.findElement(By.className("ic-key")).clear();
    	driver.findElement(By.className("ic-key")).sendKeys(password);
    	
    	driver.findElement(By.name("submit")).click();
    	
    	driver.findElement(By.cssSelector("div.contents")).isDisplayed();
 
       	//if(driver.findElement(By.cssSelector("#panel .inside > .contents .avatar > p")).isDisplayed())
    	if(driver.findElement(By.name("submit")).isDisplayed())
    	{
       		// pobranie imienia i nazwiska do sprawdzenia
       		String name_s = driver.findElement(By.cssSelector("#panel .inside > .contents .avatar > p ")).getText().replace(" wyloguj", ""); 
    		
       		System.out.println("Logowanie " + name + " ...");
       		System.out.println("login: " + username);
       		System.out.println("has³o: " + password);
       		System.out.println("Zalogowano:" + name_s);
       		
    		System.out.println("Twoje imie i nazwisko: " + name);
    		System.out.println("Pobrane imie i nazwisko: " + name_s);
    		
    		System.out.println("Czy poprawne imie i nazwisko: " + name_s.equals(name.toUpperCase()));
    		
    		if(name_s.equals(name.toUpperCase()))
    			return true;
    		else
    			return false;    			
    	}else{
    		System.out.println("blad logowania!");
    		return false;
    	}
    }
    
	@Test
	public static boolean logout() {
		
		
		//driver.findElement(By.cssSelector("#panel .inside > .contents .avatar > p ")).isDisplayed();		
		System.out.println("logout....");
		
		if(driver.findElement(By.linkText("wyloguj")).isDisplayed())
		{
			driver.findElement(By.linkText("wyloguj")).click();
			return true;
		}else
		{
			return false;
		}
	 }

	public static void main(String[] args) throws Exception {

		setUp();
    	driver.get(baseUrl);		
    	
    	System.out.println("#######################");
    	checkTitle();
    	System.out.println("#######################");
    	
    	System.out.println("# test1");
    	loginTest("karolkochan@gmail.com", "karol", "Karol Kochan");
    	logout();
    	
    	System.out.println("# test2");
    	loginTest("kl@klqmedia.com", "qwerty654321", "Krzysztof £ukasiñski");   	
    	logout();
    	
    	System.out.println("# test3");
    	loginTest("jan.nowak@gmail.com", "janek", "Jan Nowak");
    	logout();

    	System.out.println("#######################");
		tearDown();
        System.out.println("DONE ...");
       
	}
    public static void checkTitle(){
    	String title = new String("TRUE DETECTION");
		 if(driver.findElement(By.cssSelector("#panel")).isDisplayed());	  
		 if(title.equals(driver.getTitle()))
		 {
			 System.out.println("Wczytano stronê: " + driver.getTitle());
		 }else{
			 System.out.println("Coœ nie tak z: " + title);
		 }
    }
    
	@Before
    public static void setUp() throws Exception {
        baseUrl = "http://ivan.dev/";
               
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(0, TimeUnit.MILLISECONDS);
        
        wait = new WebDriverWait(driver, 30);        
    }	
    @After
    public static void tearDown() throws Exception {
        driver.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
        }
    }
}
