package org.example;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class DiscordRegisterTest {
    private WebDriver driver;
    @BeforeTest
    public void setDriver() throws Exception {

        String path = "C:\\Users\\andre\\Downloads\\chromedriver-win64\\chromedriver";

        System.setProperty("webdriver.chrome.driver", path);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    /*
      Caso de Prueba 2: Registrarse en Discord con Nuevos Datos

      Título: Registro No Exitoso en Discord
      Precondiciones: Tener acceso a la página de registro de Discord
      Pasos:
      Abrir la página de registro de Discord.
      Ingresar un correo electrónico invalido.
      Crear un nombre de usuario.
      Establecer una contraseña.
      Seleccionar la fecha de nacimiento
      Aceptar condiciones
      Hacer clic en el botón de registro.
      Resultado Esperado: El usuario recibe una alerta del correo invalido y no se completa el registro
   */
    @Test
    public void registroNoExitosoEnDiscord() {
        // Preparación de la prueba
        driver.get("https://discord.com/register");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions actions = new Actions(driver);

        // Lógica de la prueba
        //Llenamos los datos "simples"
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uid_5\"]")));
        emailField.sendKeys("apa@gmail");
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uid_6\"]")));
        nameField.sendKeys("Andre");
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uid_7\"]")));
        userField.sendKeys("XD13431FWE");
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"uid_8\"]")));
        passwordField.sendKeys("12345Hola!");

        //Llenamos el campo de dia
        WebElement dayDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div/div/form/div[2]/div/fieldset/div/div[1]/div/div")));
        dayDropdown.click();
        WebElement dayToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'15')]")));
        actions.moveToElement(dayToSelect).click().perform();

        //Llenamos el campo de mes
        WebElement monthDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div/div/form/div[2]/div/fieldset/div/div[2]/div/div")));
        monthDropdown.click();
        WebElement monthToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'abril')]")));
        actions.moveToElement(monthToSelect).click().perform();

        //Llenamos el campo de anio
        WebElement yearDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div/div/form/div[2]/div/fieldset/div/div[3]/div/div")));
        yearDropdown.click();
        WebElement yearToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'1990')]")));
        actions.moveToElement(yearToSelect).click().perform();

        //Buscamos el checkbox en este caso por cssSelector
        WebElement optInCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input.inputDefault__7fb3f.input_f56cea[type='checkbox']")
        ));
        //Vemos si el checkbox no esta marcado para marcarlo
        if (!optInCheckbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optInCheckbox);
        }
        WebElement joinButton =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div/div/form/div[2]/div/div[7]/button")));
        joinButton.click();


        WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":r1:")));
        String errorMessageText = emailErrorMessage.getText();

        // Limpiar el mensaje de error para eliminar caracteres extraños como guiones o espacios iniciales/finales
        errorMessageText = errorMessageText.replace("-", "").trim();
        Assert.assertEquals("Formato de correo electrónico no válido", errorMessageText);
    }

}
