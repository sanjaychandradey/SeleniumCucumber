package PageObjects;

import Managers.AllDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoronaPage {


    private final WebDriver webDriver;
    public CoronaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//header//img[@alt='Bhinneka.com']//parent::a")
    private WebElement headerLogoButton;
    @FindBy(xpath = "//*[@id='usa_table_countries_today']")
    private WebElement parentTable;
    @FindBy(xpath = "(//a[contains(text(),'Coronavirus')])[1]")
    private WebElement coronaLink;
    @FindBy(xpath = "//*[@id='dismiss-button']")
    private WebElement fbutton;




    public void launchWebsite() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        coronaLink.isDisplayed();
        webDriver.manage().window().maximize();
        // webDriver.findElement(By.xpath("(//a[contains(text(),'Coronavirus')])[1]")).click();
        int size = webDriver.getWindowHandles().size();
        System.out.println("++++++++++++++++++++" + size);
        List<WebElement> iframewindow = webDriver.findElements(By.tagName("iframe"));
        int numofIframe = iframewindow.size();
        System.out.println("iframe number  ::" + numofIframe);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        for (int i = 0; i <= numofIframe; i++) {
            try {
                System.out.println(iframewindow.get(i).getAttribute("id"));
                webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                System.out.println("I am in the frame:  " + iframewindow.get(i).getAttribute("id"));
                String t = iframewindow.get(i).getAttribute("id");
                //wait for element within frameA to exist
                WebDriverWait wait = new WebDriverWait(webDriver, 400);
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(fbutton);
                webDriver.switchTo().frame(t);
                //webDriver.switchTo().frame(ele);
                //System.out.println("I am in the frame:  " + iframewindow.get(i).getAttribute("name"));
                webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                //js.executeScript("arguments[0].click();", fbutton);
                fbutton.click();
                System.out.println("Button found but not clickable");
                webDriver.switchTo().defaultContent();
            } catch (Exception e) {
                System.out.println("Incorrect frame check for the other One");
                webDriver.switchTo().defaultContent();
            }

        }

        //fbutton.click();
        //js.executeScript("arguments[0].style.border='2px solid red'", parentTable);
        js.executeScript("arguments[0].scrollIntoView(true);", parentTable);

        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //webDriver.switchTo().alert().dismiss();
        // js.executeScript("arguments[0].style.border='2px solid red'", parentTable);
        //parentTable.isDisplayed();
        // coronaLink.click();
        System.out.println("I am  in landing page");

        //checking the data for table dynamically
        //get the size of number of columns
        List<WebElement> headercolumn = webDriver.findElements(By.xpath("//*[@id='usa_table_countries_today']//thead//tr[1]//th"));
        System.out.println("Total number of column available---:  " + headercolumn.size());
        for (WebElement cl : headercolumn) {
            System.out.print(cl.getText() + ",");
        }


        //number of table row

        List<WebElement> rowelement = webDriver.findElements(By.xpath("//*[@id='usa_table_countries_today']//tbody[1]//tr"));
        System.out.println("Total number of row available---:  " + rowelement.size());
        //*[@id='usa_table_countries_today']//tbody[1]//tr[2]//td[2]
        int rowstart = 2;
        int rowend = rowelement.size();
        int colend = headercolumn.size();

        for (int j = rowstart; j <= rowend; j++)
        {
            int colstart = 2;
            for (int k = colstart; k <= colend; k++)
              {
                String dyxpath = "//*[@id='usa_table_countries_today']//tbody[1]//tr[" + j + "]//td["+k+"]";
                //System.out.println(dyxpath);
                System.out.print(webDriver.findElement(By.xpath(dyxpath)).getText() + " ,");
                colstart++;
              }
             System.out.println();
            rowstart++;
        }
    }
}
