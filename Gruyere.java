package gruyere;

import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gruyere {	
	
  public static void main(String[] args) throws Exception{	 
	  
	  WebDriver driver = new FirefoxDriver();	  
	  setUp(driver);	
	  String user = "test";
	  create_user(driver, user);
	  home(driver);	 
	  create_snippet(driver);
	  signout(driver);
	  signin(driver);
	  verify_snippet(driver, user);
	  remove_snippet(driver);
	  create_private_snippet(driver);
	  signout(driver);	  
	  create_user(driver, "test1");
	  associate_homepage_to_user(driver);
	  home(driver);	  
	  privacy_private_snippet(driver);
	  checking_home_components(driver);
	  tearDown(driver);
  }
  
  @Before
	public static void setUp(WebDriver driver) throws Exception{
		//Method that executes the first system operations.
		driver.get("https://google-gruyere.appspot.com/start");
		driver.findElement(By.linkText("Agree & Start")).click();			
	}    
  
    public static void create_user(WebDriver driver, String user){
		//Method that creates a new account.		
		driver.findElement(By.linkText("Sign up")).click();
		send_keys(driver, user);		
		submit(driver);
		String  test = "Account created.";
		test(driver, test);		
	}  
    
    public static void associate_homepage_to_user(WebDriver driver){	
    	 //Method that associates homepage with user.
    	 home(driver);
    	 driver.findElement(By.linkText("Profile")).click();
    	 driver.findElement(By.xpath("//input[contains(@name,'web_site')]")).sendKeys("https://www.linkedin.com/");
    	 submit(driver);
    	 driver.findElement(By.linkText("My Snippets")).click();
    	 driver.findElement(By.linkText("My site")).click();    
    	 test(driver, "Be great at what you do"); //checking if one component of LinkedIn page was loaded.   	 
    	 driver.navigate().back();    	 
	} 
  
  public static void create_snippet(WebDriver driver){	  
	  //Method that creates a snippet.
	  driver.findElement(By.linkText("New Snippet")).click();	  
	  driver.findElement(By.cssSelector("textarea[name='snippet']")).sendKeys("testing snippet ...");  
	  submit(driver);
	  String  test = "testing snippet ...";
	  test(driver, test);		
  }    
  
  public static void verify_snippet(WebDriver driver, String user) {	
	  //Method that verifies a snippet.
	  send_keys(driver, user);
	  login(driver);	  
	  String  test = "testing snippet ...";
	  test(driver, test);	  	
  }  
  
  public static void remove_snippet(WebDriver driver) {	 
	  //Method that removes a snippet.
	  driver.findElement(By.linkText("My Snippets")).click();
	  driver.findElement(By.linkText("[X]")).click();	    
	  String  test = "No snippets.";
	  test(driver, test);
  } 
  
  public static void create_private_snippet(WebDriver driver) {	 
	  //Method that creates a private snippet.
	  driver.findElement(By.linkText("Profile")).click();
	  driver.findElement(By.cssSelector("textarea[name='private_snippet']")).sendKeys("testing private snippet ...");
	  submit(driver);  
	  driver.findElement(By.xpath("//a[contains(@id,'show')]")).click();
	  String  test = "testing private snippet ...";
	  test(driver, test);	  
  }
  
  public static void privacy_private_snippet(WebDriver driver) {	
	  //Method that checks if other user is able to see the private snippet from other.
	  String  test = "Private snippet";
	  test(driver, test);	  
  }
  
  public static void checking_home_components(WebDriver driver) {	
	  //Method that checks Home web components.
	  driver.findElement(By.linkText("All snippets")).click();
	  test(driver, "Gruyere is the cheesiest application on the web.");	
	  home(driver); 	 
	  
	  driver.findElement(By.linkText("Homepage")).click();
	  test(driver, "Kraft");	
	  driver.navigate().back();	  
	  
	  test(driver, "Cheddar");
	  test(driver, "Gruyere is the cheesiest application on the web.");	  
	  test(driver, "Brie");
	  test(driver, "Brie is the queen of the cheeses!!!");
	  
	  signout(driver);
  }
  
  private static void home(WebDriver driver) {
	  //Method that clicks in Home link (to avoid replicated code).
	  driver.findElement(By.linkText("Home")).click();	 	
	}  
  
  private static void test(WebDriver driver, String test) {
	  //Method that verifies some messages in the screen.
	  boolean message = driver.getPageSource().contains(test);		
		if (message == true) {
			System.out.println(test + "created or verified.");			
		} else {
			System.out.println(test + "not created or not verified.");
		}		
	}
  
  public static void signout(WebDriver driver) {	 
	  //Method that signs out (to avoid replicated code).
	  driver.findElement(By.linkText("Sign out")).click();	  	  	
  }
  
  public static void signin(WebDriver driver) {	  
	  //Method that signs in (to avoid replicated code).
	  driver.findElement(By.linkText("Sign in")).click();  	  	
  }
  
  public static void login(WebDriver driver) {	 
	  //Method that logs in (to avoid replicated code).
	  driver.findElement(By.xpath("//input[contains(@value,'Login')]")).click();	   	
  }
  
  public static void send_keys(WebDriver driver, String user) {	  
	  //Method that fills user content to Sign in on Sing up (to avoid replicated code).
	  driver.findElement(By.xpath("//input[contains(@name,'uid')]")).sendKeys(user);
	  driver.findElement(By.xpath("//input[contains(@name,'pw')]")).sendKeys(user);  	  	
  }  
  
  public static void submit(WebDriver driver) {	 
	  //Method that clicks in Submit action buttons (to avoid replicated code).
	  driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
  }  
  
  @After
    //Method that quits the driver.
	public static void tearDown(WebDriver driver){	  
	  driver.quit();	
	}   
}
