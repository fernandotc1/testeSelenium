package testBarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US1testeLoginComExito {

	private static final String MENU_NOVO_ADMIN = "/html/body/nav/div/div[2]/ul/li[2]/a";
	private static final String USUARIO_ADMIN = "TesteAdm";
	private static final String MENU_LOGIN = "/html/body/nav/div/div[2]/ul/li[1]";
	private static final String EMAIL_ADMIN = "adm@teste.com";
	private static final String SENHA_ADMIN = "123456";
	private static final String BOTAO_LOGIN_ADMIN = "/html/body/div[2]/form/button";

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializar() {

		System.setProperty("webdriver.gecko.driver", "C:\\Desenvolvimento\\Ferramentas\\WebDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://seubarriga.wcaquino.me/login");
	    dsl = new DSL(driver);
	}
	
	@After
	public void finalizar() {

		driver.quit();		
	}

	@Test
	public void logarUsuario() throws Throwable {

		dsl.clicarXach(MENU_NOVO_ADMIN);
		dsl.escreve("nome", USUARIO_ADMIN);
		dsl.escreve("email", EMAIL_ADMIN);
		dsl.escreve("senha", SENHA_ADMIN);
		dsl.clicarTabEnter("email");

		dsl.clicarXach(MENU_LOGIN);
		dsl.escreve("email", EMAIL_ADMIN);
		dsl.escreve("senha", SENHA_ADMIN);
		dsl.clicarXach(BOTAO_LOGIN_ADMIN);
		Assert.assertEquals("Bem vindo, TesteAdm!", dsl.obterMensagemLogadoExito());

	}
}