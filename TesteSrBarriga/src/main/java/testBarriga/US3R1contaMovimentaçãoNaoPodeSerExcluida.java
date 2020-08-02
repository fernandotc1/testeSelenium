package testBarriga;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US3R1contaMovimentaçãoNaoPodeSerExcluida {

	private static final String MENU_NOVO_ADMIN = "/html/body/nav/div/div[2]/ul/li[2]/a";
	private static final String USUARIO_ADMIN = "TesteAdmBoaVista";
	private static final String MENU_CONTA = "/html/body/nav/div/div[2]/ul/li[2]";
	private static final String MENU_CONTA_ADICIONAR = "/html/body/nav/div/div[2]/ul/li[2]/ul/li[1]";
	private static final String MENU_RESUMO_MENSAL = "/html/body/nav/div/div[2]/ul/li[4]";
	private static final String MENU_CONTA_SALVAR = "/html/body/div[2]/form/div[2]";
	private static final String MENU_CONTA_LISTAR = "/html/body/nav/div/div[2]/ul/li[2]/ul/li[2]/a";
	private static final String MENU_CONTA_EXCLUIR_USUARIO_JA_CADASTRADO_LINHA_1 = "/html/body/table/tbody/tr[1]/td[2]/a[2]";
	private static final String MENU_LOGIN = "/html/body/nav/div/div[2]/ul/li[1]";
	private static final String EMAIL_ADMIN = "adm@teste.com";
	private static final String SENHA_ADMIN = "123456";
	private static final String BOTAO_LOGIN_ADMIN = "/html/body/div[2]/form/button";
	private static final String USUARIO_1 = "Carlos";
	
	private static final String MENU_CRIAR_MOVIENTACAO = "/html/body/nav/div/div[2]/ul/li[3]";
	private static final String BOTAO_SALVAR_MOVIENTACAO = "/html/body/div[2]/form/div[4]";
	private static final String BOTAO_EXCLUIR_MOVIENTACAO = "/html/body/div[2]/table/tbody/tr/td[6]/a";	
	private static final String MENU_HOME = "/html/body/nav/div/div[2]/ul/li[1]";
	private static final String BOTAO_SAIR = "/html/body/nav/div/div[2]/ul/li[5]";

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
	public void testeUS3R1contaMovimentaçãoNaoPodeSerExcluida() throws InterruptedException {

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
		
		dsl.clicarXach(MENU_CONTA);
		dsl.clicarXach(MENU_CONTA_ADICIONAR);
		dsl.escreve("nome", USUARIO_1);
		dsl.clicarXach(MENU_CONTA_SALVAR);
		Assert.assertEquals("Já existe uma conta com esse nome!", dsl.obterMensagemUsuarioJaCadastrado());

		String usuarioJaCadastrado = dsl.obterMensagemUsuarioJaCadastrado();
		if (usuarioJaCadastrado != null) {

			dsl.clicarXach(MENU_CONTA);
			dsl.clicarXach(MENU_CONTA_LISTAR);
			dsl.clicarXach(MENU_CONTA_EXCLUIR_USUARIO_JA_CADASTRADO_LINHA_1);
			Assert.assertEquals("Conta removida com sucesso!", dsl.obterMensagemContaExcluidaSucesso());

			dsl.clicarXach(MENU_CONTA);
			dsl.clicarXach(MENU_CONTA_ADICIONAR);
			dsl.escreve("nome", USUARIO_1);
			dsl.clicarXach(MENU_CONTA_SALVAR);
			Assert.assertEquals("Conta adicionada com sucesso!", dsl.obterMensagemCadastradoSucesso());

			
				Date dataAtual = DataUtils.ObterDatasComDiferencaDias(0);

				dsl.clicarXach(MENU_CRIAR_MOVIENTACAO);
				dsl.selecionarCombo("tipo", "Receita");
				dsl.setDataMovimentacao(DataUtils.obterDataFormatada(dataAtual));
				dsl.setDataPagamento(DataUtils.obterDataFormatada(dataAtual));
				dsl.setDescricao("Movimentaçao do Teste");
				dsl.setInteressado("Interessadoo a você");
				dsl.setValor("500");
				dsl.setConta("Carlos");
				dsl.setStatusPago();
				dsl.clicarXach(BOTAO_SALVAR_MOVIENTACAO);
				Assert.assertEquals("Movimentação adicionada com sucesso!", dsl.obterMensagemMovientacaoSucesso());

				dsl.clicarXach(MENU_CONTA);
				dsl.clicarXach(MENU_CONTA_LISTAR);
				dsl.clicarXach(MENU_CONTA_EXCLUIR_USUARIO_JA_CADASTRADO_LINHA_1);
				Assert.assertEquals("Conta em uso na movimentações", dsl.obterMensagemMovientacaoEmUso());

				dsl.clicarXach(MENU_RESUMO_MENSAL);
				dsl.clicarXach(BOTAO_EXCLUIR_MOVIENTACAO);
				Assert.assertEquals("Movimentação removida com sucesso!",
						dsl.obterMensagemMovientacaoExcluidaSucesso());				
											
				dsl.clicarXach(MENU_HOME);
				dsl.clicarXach(BOTAO_SAIR);
			}
		}
	}
