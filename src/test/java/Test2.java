import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



// P1r65#2dS@iT
public class Test2 {
    private WebDriver driver;

    @BeforeClass
    @Parameters({"webdriverPath"})
    public void setUp(String webdriverPath) {
        System.setProperty("webdriver.edge.driver", webdriverPath);

        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest() {
        driver.get("https://smcuat.sahayog.com:2950/fininfra/ui/SSOLogin.jsp");

        WebElement textBox = driver.findElement(By.id("details-button"));
        WebElement link = driver.findElement(By.id("proceed-link"));

        textBox.click();
        link.click();

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        WebElement userInput = driver.findElement(By.id("usertxt"));
        userInput.sendKeys("SAH052");
        WebElement passwordInput = driver.findElement(By.id("passtxt"));
        passwordInput.sendKeys("Ver@123");

        WebElement loginButton = driver.findElement(By.id("Submit"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("appSelect")));

        Select select = new Select(selectElement);
        select.selectByVisibleText("CRM");

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent();

        waitForPageToLoad(driver, 30);

        int maxAttempts = 5;
        int attempt = 0;
        boolean success = false;

        while (attempt < maxAttempts && !success) {
            try {
                driver.switchTo().defaultContent();
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("ScreensTOCFrm")));
                success = true;
            } catch (Exception e) {
                try {
                    Thread.sleep(2000); // 2 seconds
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("Functionmain")));

        WebElement tdElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spanFor2")));
        Actions actions = new Actions(driver);
        actions.moveToElement(tdElement).perform();
        tdElement.click();

        WebElement iframe1 = driver.findElement(By.id("ifrmFor2"));
        driver.switchTo().frame(iframe1);

        WebElement tdElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("spanFor4")));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(tdElement1).perform();
        tdElement1.click();

        WebElement Customer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subviewspanFor41")));
        Actions customerAction = new Actions(driver);
        customerAction.moveToElement(Customer).perform();
        Customer.click();

        driver.switchTo().defaultContent();
        switchFrames(driver);

        WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("AccountModBO.Gender")));
        Select genderdropdown = new Select(gender);
        genderdropdown.selectByVisibleText("M");

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("AccountBO.Cust_First_Name")));
        firstName.sendKeys("Swapnil");
	     
	     WebElement fullName = driver.findElement(By.name("AccountBO.Cust_Last_Name"));
	     fullName.sendKeys("swapnil bhurkunde");
	     
	     WebElement doB = driver.findElement(By.name("3_AccountBO.Cust_DOB"));   
	     doB.sendKeys("28/05/1998");
	     
	     WebElement motherName= driver.findElement(By.name("AccountBO.MaidenNameOfMother"));
	     motherName.sendKeys("Vrinda Bhurkunde");
	     
	     WebElement inputElement = driver.findElement(By.name("AccountBO.short_name"));
	     inputElement.sendKeys("SB");
	     
	     WebElement nativeLangCode = driver.findElement(By.name("AccountModBO.NativeLangCode"));  // Create a Select object
	      select = new Select(nativeLangCode);// Select the option by visible text
	     select.selectByVisibleText("ENGLISH");
	     
	     //CRM Alerts
	     WebElement crmAlerts = driver.findElement(By.name("AccountModBO.Enable_Alerts"));
	     Select CRMselect = new Select(crmAlerts);
	     CRMselect.selectByValue("Y");
	    
	     
	     WebElement defaultChannelsForCrm = driver.findElement(By.name("AccountBO.DefaultChannel_Alert"));
	      select = new Select(defaultChannelsForCrm);
	     select.selectByValue("EMAIL");

	     
	     //segment
	     WebElement Segment = driver.findElement(By.name("btnone_AccountModBO.Segmentation_Class"));
	     Segment.click();
	     String mainWindowHandle = driver.getWindowHandle();
	     for (String handle : driver.getWindowHandles()) {
	         if (!handle.equals(mainWindowHandle)) {
	             driver.switchTo().window(handle);
	             break;
	         }
	     }
//	     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
//	     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
//	     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
//	     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	     switchFrames2(driver);

	     
	     WebElement submitCatValueList = driver.findElement(By.name("Submit"));
	     submitCatValueList.click();
	     driver.switchTo().defaultContent();  //is this necesary
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	    WebElement fontElement = driver.findElement(By.xpath("//font[@class='fntColData' and text()='CLASS A']"));
	           fontElement.click();     

	           driver.close();
	           
	           driver.switchTo().window(mainWindowHandle);
	           
	           //preffered locale
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	           
	           //subSegment
	           WebElement subSegment = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("AccountModBO.SubSegment")));
	           Select selectSubSegment = new Select(subSegment);
	           selectSubSegment.selectByVisibleText("Sub Class A");
	           
	         
	          
	           // Switch back to the main window if needed
	          driver.switchTo().window(mainWindowHandle);
	           
	          

	           
	      
	                    //relationShip manager
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	                    
	                     WebElement relationShipManager = driver.findElement(By.name("btnone_Acc_manager"));
	                     relationShipManager.click();
	                  
	                  //mainWindowHandle = driver.getWindowHandle();
	                  for (String handle : driver.getWindowHandles()) {
	                      if (!handle.equals(mainWindowHandle)) {
	                          driver.switchTo().window(handle);
	                          break;
	                      }
	                  }
	               

	                  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
	                  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                  WebElement managerRelation = driver.findElement(By.name("Submit"));
	                  managerRelation.click();
	                  
	                  driver.switchTo().defaultContent();
	                  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	                  WebElement relationManager = driver.findElement(By.xpath("//font[@class='fntColData' and text()='UBSADMIN']"));
	                  relationManager.click();     

	                         driver.close();
	                         driver.switchTo().window(mainWindowHandle);
	               
	                   //preffered locale
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	                         
	                          WebElement prefferedLocale = driver.findElement(By.name("btnone_PsychographicBO.Preferred_Locale"));
	                          prefferedLocale.click();
	                       
	                      // mainWindowHandle = driver.getWindowHandle();
	                       for (String handle : driver.getWindowHandles()) {
	                           if (!handle.equals(mainWindowHandle)) {
	                               driver.switchTo().window(handle);
	                               break;
	                           }
	                       }
	                    

	                       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
	                       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                       WebElement submitLocale = driver.findElement(By.name("Submit"));
	                       submitLocale.click();
	                       
	                       driver.switchTo().defaultContent();
	                       wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	                       WebElement prefferedLocaleoption = driver.findElement(By.xpath("//font[@class='fntColData' and text()='en_US']"));
	                       prefferedLocaleoption.click();     

	                              driver.close();
	                              driver.switchTo().window(mainWindowHandle);
	                              
	                              //region
	                              
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                              wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	                              
	                               WebElement region = driver.findElement(By.name("btnone_AccountModBO.region"));
	                               region.click();
	                            
	                            //mainWindowHandle = driver.getWindowHandle();
	                            for (String handle : driver.getWindowHandles()) {
	                                if (!handle.equals(mainWindowHandle)) {
	                                    driver.switchTo().window(handle);
	                                    break;
	                                }
	                            }
	                         

	                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
	                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                            WebElement submitRegion = driver.findElement(By.name("Submit"));
	                            submitRegion.click();
	                            
	                            driver.switchTo().defaultContent();
	                            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	                            WebElement regionOption = driver.findElement(By.xpath("//font[@class='fntColData' and text()='URBAN AREA']"));
	                            regionOption.click();     

	                                   driver.close();
	                                   driver.switchTo().window(mainWindowHandle);
	                                   
	                                   
	                                   //tax deducted
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                                   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	                                   
	                                    WebElement taxDeducted = driver.findElement(By.name("btnone_AccountModBO.Tds_tbl"));
	                                    taxDeducted.click();
	                                 
	                                // mainWindowHandle = driver.getWindowHandle();
	                                 for (String handle : driver.getWindowHandles()) {
	                                     if (!handle.equals(mainWindowHandle)) {
	                                         driver.switchTo().window(handle);
	                                         break;
	                                     }
	                                 }
	                              

	                                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
	                                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                                 WebElement submitTax = driver.findElement(By.name("Submit"));
	                                 submitTax.click();
	                                 
	                                 driver.switchTo().defaultContent();
	                                 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	                                 WebElement taxDeduct = driver.findElement(By.xpath("//font[@class='fntColData' and text()='DSFSDF']"));
	                                 taxDeduct.click();     

	                                        driver.close();
	                                        driver.switchTo().window(mainWindowHandle);
	                         
	                                        
	                                        
	                                        //title
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                                        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	                                        
	                                         WebElement title = driver.findElement(By.name("btnone_AccountModBO.Salutation_code"));
	                                         title.click();
	                                      
	                                      //mainWindowHandle = driver.getWindowHandle();
	                                      for (String handle : driver.getWindowHandles()) {
	                                          if (!handle.equals(mainWindowHandle)) {
	                                              driver.switchTo().window(handle);
	                                              break;
	                                          }
	                                      }
	                                   

	                                      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
	                                      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                                      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                                      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                                      WebElement submitButton = driver.findElement(By.name("Submit"));
	                                      submitButton.click();
	                                      
	                                      driver.switchTo().defaultContent();
	                                      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	                                      WebElement Title = driver.findElement(By.xpath("//font[@class='fntColData' and text()='MR.']"));
	                                             Title.click();     

	                                             driver.close();
	                                             driver.switchTo().window(mainWindowHandle);


	                                      
	                                        //primary sol id
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                                             wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	                                             
	                                              WebElement primarySolId = driver.findElement(By.name("btnone_AccountBO.Primary_sol_id"));
	                                              primarySolId.click();
	                                           
	                                          // mainWindowHandle = driver.getWindowHandle();
	                                           for (String handle : driver.getWindowHandles()) {
	                                               if (!handle.equals(mainWindowHandle)) {
	                                                   driver.switchTo().window(handle);
	                                                   break;
	                                               }
	                                           }
	                                        

	                                           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
	                                           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	                                           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	                                           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	                                           WebElement submitSol = driver.findElement(By.name("Submit"));
	                                           submitSol.click();
	                                           
	                                           driver.switchTo().defaultContent();
	                                           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
	                                           WebElement primarySOLID = driver.findElement(By.xpath("//font[@class='fntColData' and text()='Branch1']"));
	                                           primarySOLID.click();     

	                                                  driver.close();
	                                                  driver.switchTo().window(mainWindowHandle);
	                                                  
	                                                  
	                                                  
	                                                  
			//////// Contact///////////////
	     
	     
//	     // mainWindowHandle  =driver.getWindowHandle();
//			for (String  handle : driver.getWindowHandles()) {
//				System.out.println(mainWindowHandle);
//				System.out.println(handle);
//
//			}
	       
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
			 
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
			 
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
			 

			WebElement contact = driver.findElement(By.id("fnttpageCont3"));
			contact.click();

			WebElement buttonElement = driver.findElement(By.name("Add Address Details"));
			buttonElement.click();

			
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(mainWindowHandle)) {
					driver.switchTo().window(handle);
					break;
				}
			}

			// house
			WebElement House = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("AccountBO.Address.house_no")));
			House.sendKeys("123 Example Street");
			

			// city
			WebElement city = driver.findElement(By.name("btnone_AccountBO.Address.city"));
			city.click();
			
			
			String cityWindowHandle = driver.getWindowHandle();
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(mainWindowHandle) && !handle.equals(cityWindowHandle)) {    //1st switch  this switchb is what remains even after the execution is completedfor adress to switch for this i needed to store the window in a string and the reiterate to cherck which one matches
					driver.switchTo().window(handle);
					break;
				}
			}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));

			 submitButton = driver.findElement(By.className("sbttn"));
			submitButton.click();
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
			Title= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//font[@class='fntColData' and text()='CHENNAI']")));
			//  = driver.findElement(By.xpath("//font[@class='fntColData' and text()='CHENNAI']"));
			Title.click();
			driver.close();
			driver.switchTo().window(cityWindowHandle);        
			

			
			// country
			WebElement country = driver.findElement(By.name("btnone_AccountBO.Address.country"));
			country.click();

			

			cityWindowHandle = driver.getWindowHandle();
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(mainWindowHandle) && !handle.equals(cityWindowHandle)) {
					driver.switchTo().window(handle);
					break;
				}
			}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
			submitButton = driver.findElement(By.className("sbttn"));
			submitButton.click();
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
			Title = driver.findElement(By.xpath("//font[@class='fntColData' and text()='INDIA']"));
			Title.click();
			driver.close();
			driver.switchTo().window(cityWindowHandle);
			
			WebElement street = driver.findElement(By.name("AccountBO.Address.street_no"));
			street.sendKeys("test");

			WebElement streetname = driver.findElement(By.name("AccountBO.Address.street_name"));
			streetname.sendKeys("test");

			// state
			WebElement state = driver.findElement(By.name("btnone_AccountBO.Address.state"));
			state.click();

			

			
			for (String handle : driver.getWindowHandles()) {
				System.out.println(mainWindowHandle);
				if (!handle.equals(mainWindowHandle) && !handle.equals(cityWindowHandle)) {
					driver.switchTo().window(handle);
					break;
				}
			}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
			submitButton = driver.findElement(By.className("sbttn"));
			submitButton.click();
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
			Title = driver.findElement(By.xpath("//font[@class='fntColData' and text()='MAHARASHTRA']"));
			Title.click();
			driver.close();
			driver.switchTo().window(cityWindowHandle);

			 //postal code
				WebElement postalCode = driver.findElement(By.name("AccountBO.Address.zip"));
				postalCode.sendKeys("123456");
				
				WebElement saveButton = driver.findElement(By.xpath("//input[@class='sbttn' and @type='button' and @name='Save' and @onclick='saveEduDet()']"));
				saveButton.click();

				alert = driver.switchTo().alert();
				alert.accept();

				
			    driver.switchTo().window(mainWindowHandle);
				
				
				
			// PHONE and EMAIL
				

				
			
				
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));

				
			WebElement phoneAndEmail = driver.findElement(By.id("fnttpagePhone"));
			phoneAndEmail.click();

			WebElement addButton = driver.findElement(By.cssSelector("input[type='button'][name='Add Phone and E-mail']"));
			addButton.click();

			
			   
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(mainWindowHandle)) {
						driver.switchTo().window(handle);
						break;
					}
				}
				
				
				WebElement countryCode = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneNo.cntrycode"));
				countryCode.sendKeys("91");
				WebElement phoneNumber = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneNo.localcode"));
				phoneNumber.sendKeys("1234567890");
				WebElement type = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneEmailType"));
				 select = new Select(type);
				 select.selectByValue("CELLPH");
				

				
				 WebElement saveInfo = driver.findElement(By.cssSelector("input[type='button'][name='Save'][value='Save']"));

				// Click on the button to trigger the onclick event
				 saveInfo.click();
				
				alert =driver.switchTo().alert();
				alert.accept();
				driver.switchTo().window(mainWindowHandle);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));

				WebElement Preferred_Contact_No_Type = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneEmailType"));
				 select = new Select(Preferred_Contact_No_Type);
				select.selectByVisibleText("Cell Phone");
				
				WebElement Preferred_Mobile_Type_for_Alerts  = driver.findElement(By.name("AccountBO.Preferred_Mobile_Alert_Type"));
				 select = new Select(Preferred_Mobile_Type_for_Alerts);
				select.selectByVisibleText("Cell Phone");
				
				WebElement PreferredEMailIDType   = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneEmailType1"));
				 select = new Select(PreferredEMailIDType);
				select.selectByVisibleText("Communication");
				
				
				
				
				
	         driver.switchTo().window(mainWindowHandle);   //we are already on the window why do we need to switch
	     
	     
	     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
			
			
			// email in contact
			phoneAndEmail.click();
			// addButton = driver.findElement(By.cssSelector("input[type='button'][name='Add Phone and E-mail']"));
			addButton.click();

			
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(mainWindowHandle)) {
						driver.switchTo().window(handle);
						break;
					}
				}
			
			
			
			WebElement dropdown = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneOrEmail"));
	        Select selectContact = new Select(dropdown);
	        selectContact.selectByValue("EMAIL");
	        
	        
	        WebElement emailInput = driver.findElement(By.name("AccountBO.PhoneEmail.Email"));
	        emailInput.sendKeys("test@example.com");
	        
	        
	        WebElement Emaildropdown = driver.findElement(By.name("AccountBO.PhoneEmail.PhoneEmailType1"));

	        // Create a Select object
	        Select Emailselect = new Select(Emaildropdown);

	        // Select the option with value "COMMEML"
	        Emailselect.selectByValue("COMMEML");

	        
	         saveButton = driver.findElement(By.name("Save"));

	        // Click the "Save" button
	        saveButton.click();

				
	        driver.switchTo().window(mainWindowHandle);   
	        
	        
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	   		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
	   		
				
				//Identtification documents details
				
				
				WebElement IdentificationTab = driver.findElement(By.cssSelector("font#fnttpageCont5.grayTab"));
				IdentificationTab.click();
				
				
				WebElement AddIdentificationDetails = driver.findElement(By.name("AddIdentificationDetails"));
				AddIdentificationDetails.click();
				
					for (String handle : driver.getWindowHandles()) {
						if (!handle.equals(mainWindowHandle)) {
							driver.switchTo().window(handle);
							break;
						}
					}
					
				 

				  
					wait = new WebDriverWait(driver, Duration.ofSeconds(30));
					WebElement documentType = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("EntityDocumentBO.DocTypeCode")));
					wait.until(ExpectedConditions.elementToBeClickable(documentType));
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].style.display = 'block';", documentType);
					Select selectdt = new Select(documentType);
					wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("select[name='EntityDocumentBO.DocTypeCode'] option"), 1));
					selectdt.selectByVisibleText("1");
				
					
					   WebElement DocumentCode = driver.findElement(By.name("EntityDocumentBO.DocCode"));
						Select selectdc = new Select(DocumentCode);
						selectdc.selectByVisibleText("PAN");
						
					
					
					
							
							WebElement IssueDate = driver.findElement(By.name("3_EntityDocumentBO.DocIssueDate"));
							IssueDate.sendKeys("01/05/2024");
							
							WebElement UniqueId = driver.findElement(By.name("EntityDocumentBO.ReferenceNumber"));
							UniqueId.sendKeys("ccccc6666c");
							
							
							WebElement PlaceOfIssue = driver.findElement(By.name("btnone_EntityDocumentBO.PlaceOfIssue"));
							PlaceOfIssue.click();
							
							
							
							cityWindowHandle = driver.getWindowHandle();
							for (String handle : driver.getWindowHandles()) {
								if (!handle.equals(mainWindowHandle) && !handle.equals(cityWindowHandle)) {    //1st switch  this switchb is what remains even after the execution is completedfor adress to switch for this i needed to store the window in a string and the reiterate to cherck which one matches
									driver.switchTo().window(handle);
									break;
								}
							}
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));

							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));

							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));

							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));

							  submitButton = driver.findElement(By.className("sbttn"));
							submitButton.click();
							driver.switchTo().defaultContent();
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
							 Title = driver.findElement(By.xpath("//font[@class='fntColData' and text()='CHENNAI']"));
							Title.click();
							driver.close();
							driver.switchTo().window(cityWindowHandle);
							
							
							
							
							 country = driver.findElement(By.name("btnone_EntityDocumentBO.CountryOfIssue"));
							country.click();

							

							cityWindowHandle = driver.getWindowHandle();
							for (String handle : driver.getWindowHandles()) {
								if (!handle.equals(mainWindowHandle) && !handle.equals(cityWindowHandle)) {
									driver.switchTo().window(handle);
									break;
								}
							}
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
							submitButton = driver.findElement(By.className("sbttn"));
							submitButton.click();
							driver.switchTo().defaultContent();
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
							Title = driver.findElement(By.xpath("//font[@class='fntColData' and text()='INDIA']"));
							Title.click();
							driver.close();
							driver.switchTo().window(cityWindowHandle);
							
							 saveButton = driver.findElement(By.cssSelector("input[type='button'][name='SAVE'][value='Save']"));
							saveButton.click();
							
							
							
							
							driver.switchTo().window(mainWindowHandle);
						     
           
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
							
							
							 WebElement radioButton = driver.findElement(By.xpath("//input[@type='radio' and @name='radio1']"));
						     radioButton.click();
							
							
				
				
						     driver.switchTo().window(mainWindowHandle); ///is this necessary
						     
						     //Currency
						     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
								wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
								
								
								
								WebElement CurrencyTab = driver.findElement(By.id("fnttpageCont6"));
								CurrencyTab.click();
								
							
								 addButton = driver.findElement(By.cssSelector("input[type='button'][name='ADD_CURRENCYDET'][value='Add CCY']"));
							     addButton.click();
						         
									for (String handle : driver.getWindowHandles()) {
										if (!handle.equals(mainWindowHandle)) {
											driver.switchTo().window(handle);
											break;
										}
									}
				
//									 WebElement currency = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='sbttn1' and @name='btnone_PsychographicBO.MiscellaneousInfo.strText10']")));
//									 currency.click();
//									
//										cityWindowHandle = driver.getWindowHandle();
//										  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//										for (String handle : driver.getWindowHandles()) {
//											if (!handle.equals(mainWindowHandle) && !handle.equals(cityWindowHandle)) {
//												driver.switchTo().window(handle);
//												break;
//											}
//										}
//										
//										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
//										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
//										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
//										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
//										 submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("sbttn")));
//										submitButton.click();
//										driver.switchTo().defaultContent();
//										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
//										 Title = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//font[@class='fntColData' and text()='INR']")));
//										Title.click();
//										driver.close();
									//driver.switchTo().window(cityWindowHandle);
//										
//										
//										
//								   
									wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
									
									 WebElement CurrencysaveButton =wait.until(ExpectedConditions.elementToBeClickable(By.name("SAVE")));
									 wait =  new WebDriverWait(driver, Duration.ofSeconds(30));
                    
									 CurrencysaveButton.click();
									 
									 if(isAlerPresent( driver, wait)) {
										 driver.switchTo().alert().accept();
										
										 CurrencysaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("SAVE")));
										 CurrencysaveButton.click();
									 }
									 
									 
								     driver.switchTo().window(mainWindowHandle);
									 
									 
									 //Demographic
									 
									

								     wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabViewFrm")));
									 
									   WebElement Demographic = driver.findElement(By.id("fnttab1"));
									 Demographic.click();
									 
									 driver.switchTo().defaultContent();
									 
									 
									  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab1")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
										
										
										
										//maritalStatus
										   WebElement maritalStatusDropdown = driver.findElement(By.name("DemographicModBO.Marital_Status"));
									        Select MarriageStatusselect = new Select(maritalStatusDropdown);
									        MarriageStatusselect.selectByVisibleText("SINGLE");
									        
									        
										
										 WebElement Nationality = driver.findElement(By.className("sbttn1"));
										 Nationality.click();
									 
										 
										for (String handle : driver.getWindowHandles()) {
											if (!handle.equals(mainWindowHandle)) {
												driver.switchTo().window(handle);
												break;
											}
										}
								
										
										
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
										 submitButton = driver.findElement(By.className("sbttn"));
										submitButton.click();
										driver.switchTo().defaultContent();
										wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchResult")));
										WebElement nationalityTitle = driver.findElement(By.xpath("//font[@class='fntColData' and text()='INDIAN']"));
										nationalityTitle.click();
										driver.close();
										driver.switchTo().window(mainWindowHandle);
										
									
										 driver.switchTo().defaultContent();
										 
										 
										  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab1")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
											
//											
									
											
											
											  WebElement QualificationTab =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//font[@id='fnttpageQual']")));
											  QualificationTab.click();
											  
											  
										        WebElement AddDetailsEducation = driver.findElement(By.cssSelector("input[type='button'][name='ADD_EDUDET']"));
										        AddDetailsEducation.click();
											  
												for (String handle : driver.getWindowHandles()) {
													if (!handle.equals(mainWindowHandle)) {
														driver.switchTo().window(handle);
														break;
													}
												}
											
												 maxAttempts = 5;
										             attempt = 0;
										             success = false;

										            while (attempt < maxAttempts && !success) {
										                try {
										                    // Locate the dropdown element
										                    WebElement qualificationDropdown = driver.findElement(By.name("DemographicModBO.Qualification"));

										                    // Wait until the dropdown is visible, enabled, and interactable
										                    wait.until(ExpectedConditions.visibilityOf(qualificationDropdown));
										                    wait.until(ExpectedConditions.elementToBeClickable(qualificationDropdown));

										                    // Scroll the dropdown into view
										                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", qualificationDropdown);

										                    // Use JavaScript to ensure the dropdown is interactable
										                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", qualificationDropdown);

										                    // Use Select class to select the desired value
										                    Select selectQualification = new Select(qualificationDropdown);
										                    selectQualification.selectByValue("Graduate");

										                    // If we reach here, the selection was successful
										                    success = true;
										                } catch (Exception e) {
										                    // Increment the attempt counter
										                    attempt++;

										                    // Optionally, add a short wait before retrying
										                    try {
										                        Thread.sleep(2000); // 2 seconds
										                    } catch (InterruptedException ie) {
										                        ie.printStackTrace();
										                    }
										                }
										            }
											        
										        
										        
										         WebElement universityName = driver.findElement(By.name("DemographicBO.Institute_University"));
										         universityName.sendKeys("Priyadarshini college of engineering");
										        
										        WebElement qualificationSave = driver.findElement(By.cssSelector("input[type='button'][name='SAVE'][value='Save']"));
										        qualificationSave.click();
										        
										        driver.switchTo().window(mainWindowHandle);
										        
										        
										 driver.switchTo().defaultContent();
										 
										 
										  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab1")));
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
											
//											
										        //Employment details
//										        
//									            WebElement EmploymentDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//font[@id='fnttpageEDet']")));
//										        EmploymentDetails.click();
//										        
//										        WebElement addEmployment = driver.findElement(By.cssSelector("input[type='button'][name='Add EmploymentHistory']"));
//										        addEmployment.click();
//										        
//										        
//												for (String handle : driver.getWindowHandles()) {
//													if (!handle.equals(mainWindowHandle)) {
//														driver.switchTo().window(handle);
//														break;
//													}
//												}
//
//												 WebElement employerName = wait.until(ExpectedConditions.elementToBeClickable(By.name("DemographicBO.MiscellaneousInfo.strText4")));
//											        employerName.click();
//											        employerName.sendKeys("Infosane");
//										     
//										        
//										        
//										         saveButton = driver.findElement(By.cssSelector("input[type='button'][name='SAVE']"));
//										        saveButton.click();
//
//										        
//										        driver.switchTo().window(mainWindowHandle);
//
//										        driver.switchTo().defaultContent();
//												 
//												 
//												  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab1")));
//													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
//													
////													
										        
										        //IncomeAndExpenseDetails
										        
										        WebElement IncomeAndExpenseDetails =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fnttpageIExp")));

										        IncomeAndExpenseDetails.click();
										        
										        
										        WebElement EmploymentType = driver.findElement(By.name("DemographicModBO.Employment_Status_Empl"));
										        Select EmploymentTypeDropdown = new Select(EmploymentType);
										        EmploymentTypeDropdown.selectByVisibleText("Employed");
										        
										        
										        
										        //grossIncome
										        
										        WebElement grossIncome = driver.findElement(By.name("3_DemographicBO.Annual_Salary_Income"));
										        grossIncome.sendKeys("100000");

										        
										        driver.switchTo().defaultContent();
										        System.out.println("final submit 52");

										        //FINAL Submit
										       
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
												wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("buttonFrm")));

												WebElement submit = driver.findElement(By.id("submitBut"));
												submit.click();
												
												 wait = new WebDriverWait(driver, Duration.ofSeconds(30));
											     wait.until(ExpectedConditions.alertIsPresent());

											     // Switch to the alert
											      alert = driver.switchTo().alert();
											   
											     alert.accept();
											     
											     
											     for (String handle : driver.getWindowHandles()) {
														if (!handle.equals(mainWindowHandle)) {
															driver.switchTo().window(handle);
															break;
														}
													}
											     
											     

													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
													wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("buttonFrm")));

											     
											     WebElement closeFinal = driver.findElement(By.name("cancelBut"));
											     closeFinal.click();
												
											     wait = new WebDriverWait(driver, Duration.ofSeconds(30));
											     wait.until(ExpectedConditions.alertIsPresent());
											      alert = driver.switchTo().alert();
											   
											     alert.accept();
}
	
	
	
	
	public static void switchFrames(WebDriver driver){
		 Duration timeoutDuration = Duration.ofSeconds(10);
		 WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("loginFrame")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("CRMServer")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("DataAreaFrm")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tempFrm")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("formDispFrame")));
		
	}
	
	public static void switchFrames2(WebDriver driver) {
		 Duration timeoutDuration = Duration.ofSeconds(10);
		 WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("SearchOptions")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabContentFrm")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("userArea")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("IFrmtab0")));
	}

	public static void switchWindow(WebDriver driver,String mainWindowHandle ) {
		for (String handle : driver.getWindowHandles()) {
          if (!handle.equals(mainWindowHandle)) {
              driver.switchTo().window(handle);
              break;
          }
      }
	}
	
	  public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
	        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        });
	    }
	
	  public static boolean isAlerPresent(WebDriver drive, WebDriverWait wait) {
		  try {
			  wait.until(ExpectedConditions.alertIsPresent());
			  return true;
		  }catch(Exception e) {
			  return false;
		  }
	  }
	
	}


