package testBarriga;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({		
	US1testeLoginComExito.class,
	US2testValidandoUsuaroJaCadastrado.class,
	US3R1contaMovimentaçãoNaoPodeSerExcluida.class,	
	US3R2contasNaoPodeTerMesmoNome.class,	
	US4R1testValidarCamposMovimentacao.class,
	US4R2validandoTipoDeData.class,
	US4R3ValidandoMovimentacaoDatasFuturas.class,
	US5serCapazEncerrarSessao.class,
	testeBarrigaCopleto.class,
	
})
public class SuiteTeste {

}
