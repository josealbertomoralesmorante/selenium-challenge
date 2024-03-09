package tests;

import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PokemonTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
    	String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testPrintPokemonStartingWithS() {
        Response response = given().get("https://pokeapi.co/api/v2/pokemon?limit=300");
        List<Map<String, String>> pokemonList = response.jsonPath().getList("results");

        boolean sPokemonFound = false;

        System.out.println("POKEDEX");
        
        for (Map<String, String> pokemon : pokemonList) {        	
        	String name = pokemon.get("name");
            if (name.startsWith("s")) {
                String[] url = pokemon.get("url").split("/");
                String id = url[url.length-1];
                System.out.println("Pokemon ID : " + id + " Name: " + name);
                sPokemonFound = true;
            }     
        }
        
        Assert.assertTrue(sPokemonFound, "No se encontraron Pokémon cuyos nombres comiencen con 'S'");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}   