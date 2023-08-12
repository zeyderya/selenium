package day04_GetTagNameGetAttribute_Xpath_Css;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_Xpath {
    public static void main(String[] args) throws InterruptedException {
        /*
            Bir webelementi locate etmek istediğimizde o elementin unique olması gerekir. Xpath kullanımını
        biliyorsak istediğimiz webelementi her zaman unique alabiliriz.
         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //web sayfasına gidin. https://www.amazon.com/
        driver.get("https://www.amazon.com/");
        driver.navigate().refresh();
        driver.navigate().refresh();

        //Search(ara) "city bike"(arama kutusunu xpath ile locate edelim)
        WebElement aramaKutusu = driver.findElement(By.xpath("//*[@type='text']"));
        aramaKutusu.sendKeys("city bike"+ Keys.ENTER);
        /*
            Xpath kullanımına // sembolleri ile başlarız. Bu sembollerden sonra tag name belirtmemiz gerekir.
        tag name yerine * sembolunü kullanabilirsiniz. Bu sembol tag name farketmeksizin anlamına gelir.
        sonra köşeli parantez açıp kullanacağımız attribüte'ten önce @ sembolu kullanmalıyız. Sonrasında da
        attribüte yazılıp = TEK TIRNAK içince atributün değeri yazılmalıdır ve köşeli parantez kapatılır.
        SYNTAX:
                //tagName[@attributeName='atributeDeğeri']
                //*[@*='atribüteDeğeri'] --> Eğer bu kullanımda birden fazla sonuç verirse * koyduğumuz yerlere
                                             tagName ve attributeName'i belirtmemiz gerekir. Buna rağmen birden fazla
                                             sonuç verirse o webelementin index'ini aşağıdaki şekilde belirtiriz.
                                             (//tagName[@attributeName='atributeDeğeri'])[index]
            Xpath kullanamadığız zaman bir webelementin locate'i ile alakalı birden fazla sonuç çıkarsa bütün sonuçları
        bir list'e atıp index ile o webelemnti handle etmen gerekecekti. Dolayısıyla yukarıdaki örnekteki gibi
        xpath kullanımında birden fazla sonuç alırsak direk xpath üzerinden index ile bunu çözebiliriz.
         */


        //Amazon'da görüntülenen ilgili sonuçların sayısını yazdırın
        WebElement aramaSonucu = driver.findElement(By.xpath("(//*[@class='sg-col-inner'])[1]"));
        System.out.println(aramaSonucu.getText());

        //Sadece sonuc sayısını yazdırınız
        String [] sonucSayisi = aramaSonucu.getText().split(" ");
        System.out.println("Sonuc Sayisi = "+sonucSayisi[2]);

        //Sonra karşınıza çıkan ilk sonucun resmine tıklayın.
        driver.findElement(By.xpath("(//h2//a)[1]")).click();
        //xpath alırken direk tag'ları kullanarakta unique sonuç vermezse yukarıdaki örnekteki gibi index ile elementi handle edebiliriz


        //sayfayı kapatınız
        driver.close();
    }
}