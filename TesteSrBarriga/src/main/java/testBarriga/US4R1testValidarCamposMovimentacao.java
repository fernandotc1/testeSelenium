package testBarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US4R1testValidarCamposMovimentacao {
		
	private static final String MENU_LOGIN = "/html/body/nav/div/div[2]/ul/li[1]";
	private static final String EMAIL_ADMIN = "adm@teste.com";
	private static final String SENHA_ADMIN = "123456";
	private static final String BOTAO_LOGIN_ADMIN = "/html/body/div[2]/form/button";
	private static final String MENU_CRIAR_MOVIENTACAO = "/html/body/nav/div/div[2]/ul/li[3]";
	private static final String BOTAO_SALVAR_MOVIENTACAO = "/html/body/div[2]/form/div[4]";	

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
	public void testeValidarCamposMovimentacao() throws Throwable {
	
		dsl.clicarXach(MENU_LOGIN);
		dsl.escreve("email", EMAIL_ADMIN);
		dsl.escreve("senha", SENHA_ADMIN);
		dsl.clicarXach(BOTAO_LOGIN_ADMIN);
		Assert.assertEquals("Bem vindo, TesteAdm!", dsl.obterMensagemLogadoExito());
		
			dsl.clicarXach(MENU_CRIAR_MOVIENTACAO);
			dsl.clicarXach(BOTAO_SALVAR_MOVIENTACAO);
			Assert.assertEquals("Data da Movimentação é obrigatório", dsl.dataMovimentacaoObrigatorio());
			Assert.assertEquals("Data do pagamento é obrigatório", dsl.dataPagamentoObrigatorio());
			Assert.assertEquals("Descrição é obrigatório", dsl.descricaoOrigatorio());
			Assert.assertEquals("Interessado é obrigatório", dsl.InteressadoObrigatorio());
			Assert.assertEquals("Valor é obrigatório", dsl.ValorObrigatório());
			Assert.assertEquals("Valor deve ser um número", dsl.ValorDeveSerNúmero());

		}
	}

