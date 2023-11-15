package org.example;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DiscordLoginTest {
    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception {

        String path = "C:\\Users\\andre\\Downloads\\chromedriver-win64\\chromedriver";

        System.setProperty("webdriver.chrome.driver", path);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    /*
        Caso de Prueba 1: Intentar Iniciar Sesión sin Credenciales

        Título: Inicio de Sesión Fallido sin Credenciales
        Precondiciones: Tener acceso a la página de inicio de sesión de Discord
        Pasos:
        Abrir la página de inicio de sesión de Discord.
        Dejar los campos de correo electrónico y contraseña vacios.
        Hacer clic en el botón de inicio de sesión.
        Resultado Esperado: Se muestra un mensaje de error indicando que se deben llenar los campos.
    */
    @Test
    public void iniciarSesionFallidoConCredencialesInvalidas() {
        // Preparación de la prueba
        driver.get("https://discord.com/login");

        // Lógica de la prueba
        //Dejamos los campos vacios
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        WebElement joinButton = driver.findElement(By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div/div/div/form/div[2]/div/div[1]/div[2]/button[2]"));
        joinButton.click();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        // Verificación
        WebElement iconAlert = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/div/div/div/div/form/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/input"));
        Assert.assertEquals(true, iconAlert.isDisplayed());
        System.out.println("Se muestra el icono? " + iconAlert.isDisplayed());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    @AfterTest
    public void closeDriver() throws Exception {
        driver.quit();
    }

}
