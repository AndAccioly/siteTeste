package mensagens;

public class MensagemTexto {

	private Mensagens m;
	
	public MensagemTexto() {
		
	}
	
	public MensagemTexto(Mensagens m) {
		this.m = m;
	}

	public Mensagens getMensagem() {
		return m;
	}

	public void setMensagem(Mensagens m) {
		this.m = m;
	}
	

	public String getMensagemTexto() {
		switch(this.m) {
		case DADOS_INVALIDOS:
			return "Dados inválidos!";
		case JOGO_NAO_EXISTE:
			return "Jogo não existe.";
		case REMOCAO_CONCLUIDA:
			return "Remoção concluída";
		}
		return null;
		
	}
	
	public static String getMensagemTexto(Mensagens men) {
		switch(men) {
		case DADOS_INVALIDOS:
			return "Dados inválidos!";
		case JOGO_NAO_EXISTE:
			return "Jogo não existe.";
		case REMOCAO_CONCLUIDA:
			return "Remoção concluída";
		}
		return null;
		
	}
}
