package testBarriga;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {

	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void escreve(String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);

	}

	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");

	}

	public String obterValorCampoVerficar(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");

	}

	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();

	}

	public void clicarXach(String by) {
		driver.findElement(By.xpath(by)).click();

	}

	public boolean isRadioMarcado(String id) {

		return driver.findElement(By.id(id)).isSelected();

	}

	public void selecionarCombo(String id, String valor) {

		Select combo = new Select(driver.findElement(By.id(id)));
		combo.selectByVisibleText(valor);

	}

	public String obterValorCombo(String id) {

		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);

		return combo.getFirstSelectedOption().getText();

	}

	public void clicarBotao(String id) {

		driver.findElement(By.id(id)).click();

	}

	public void clicarLink(String link) {

		driver.findElement(By.linkText(link)).click();
	}

	public String obterTexto(By by) {

		return driver.findElement(by).getText();
	}

	public String obterTexto(String id) {

		return obterTexto(By.id(id));
	}

	public String obterMensagemUmailJaCadastrado() {

		return obterTexto(By.xpath("/html/body/div[1]']"));
	}
	
	public String obterMensagemErroDataMovimentacao() {

		return obterTexto(By.xpath("/html/body/div[1]"));
	}
	
	public String obterMensagemUsuarioJaCadastrado() {

		return obterTexto(By.xpath("/html/body/div[1]"));
	}

	public String obterMensagemEmailJaCadastrado() {

		return obterTexto(By.xpath("/html/body/div[1]"));
	}
	
	public String obterMensagemSucesso() {

		return obterTexto(By.xpath("//div[@class='alert alert-sucesso']"));

	}

	public String obterMensagemMovientacaoSucesso() {

		return obterTexto(By.xpath("/html/body/div[1]"));

	}
	
	public String dataMovientacaoMaiorQueAtual() {

		return obterTexto(By.xpath("/html/body/div[1]"));

	}

	public String obterMensagemMovientacaoExcluidaSucesso() {

		return obterTexto(By.xpath("/html/body/div[1]"));

	}

	public String obterMensagemContaExcluidaSucesso() {

		return obterTexto(By.xpath("/html/body/div[1]"));

	}

	public String dataMovimentacaoObrigatorio() {

		return obterTexto(By.xpath("/html/body/div[1]/ul/li[1]"));

	}

	public String dataPagamentoObrigatorio() {

		return obterTexto(By.xpath("/html/body/div[1]/ul/li[2]"));

	}

	public String descricaoOrigatorio() {

		return obterTexto(By.xpath("/html/body/div[1]/ul/li[3]"));

	}

	public String InteressadoObrigatorio() {

		return obterTexto(By.xpath("/html/body/div[1]/ul/li[4]"));

	}

	public String ValorObrigatório() {

		return obterTexto(By.xpath("/html/body/div[1]/ul/li[5]"));

	}

	public String ValorDeveSerNúmero() {

		return obterTexto(By.xpath("/html/body/div[1]/ul/li[6]"));
	}

	public String obterMensagemContaAlteradaSucesso() {

		return obterTexto(By.xpath("/html/body/div[1]"));

	}

	public String obterMensagemMovientacaoEmUso() {

		return obterTexto(By.xpath("/html/body/div[1]"));

	}

	public String obterMensagemCadastradoSucesso() {

		return obterTexto(By.xpath("/html/body/div[1]"));
	}

	public String obterMensagemErro() {

		return obterTexto(By.xpath("//div[@class='alert alert-danger']"));
	}

	public String obterMensagemLogadoExito() {

		return obterTexto(By.xpath("/html/body/div[1]"));
	}
	
	public String obterMensagemErroEmailJaCadastrado() {

		return obterTexto(By.xpath("/html/body/div[1]"));
	}
	
	

	public void EsperaImplicita() {

		// colocar na linha anterior onde vai ser a espera
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// colocar na linha anterior onde vai acabar a espera
		// driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

	}

	public void EsperaExplicita(String id) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id"))).click();

	}

	public void setDataMovimentacao(String data) {
		escreve("data_transacao", data);

	}

	public void setDataPagamento(String data) {
		escreve("data_pagamento", data);

	}

	public void setDescricao(String descricao) {
		escreve("descricao", descricao);

	}

	public void setInteressado(String interesssado) {
		escreve("interessado", interesssado);

	}

	public void setValor(String valor) {
		escreve("valor", valor);

	}

	public void setConta(String conta) {
		selecionarCombo("conta", conta);

	}

	public void setStatusPago() {
		clicarRadio("status_pago");

	}

	// public void salvar() {
	// clicarBotaoPorTexto("Salvar");;
	// }

	public static Date dataAtual(int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, dias);

		return calendar.getTime();

	}

	public static String obterDataAtual(Date data) {

		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");

		return format.format(data);

	}	
	
	public void clicarTabEnter(String id_campo) {
		driver.findElement(By.id(id_campo)).sendKeys(Keys.TAB);		
		driver.findElement(By.id(id_campo)).sendKeys(Keys.ENTER);

		
	}
}
