//package lixo;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import br.jogos.modelos.Categoria;
//import br.jogos.modelos.Jogo;
//import br.jogos.repository.CategoriaRepo;
//import br.jogos.repository.JogoRepo;
//
//public class BancoArquivo {
//
//	private String arqJogos;	
//
//	public BancoArquivo() {
//		this.arqJogos = "C:\\Users\\F176680\\workspace-estudos\\demo\\src\\main\\resources\\banco-de-jogos.txt";
//	}
//	
//	public List<Jogo> lerTodosJogos() {
//		List<Jogo> jogos = new ArrayList<Jogo>();
//		
//		File fJogos = new File(arqJogos);
//		
//		try {
//			System.out.println("Iniciando ler todos os jogos");
//			String aux;
//			String splitado[];
//			Jogo jogo;
//			Scanner scan = new Scanner(fJogos);		
//			while(scan.hasNextLine()) {
//				jogo = new Jogo();
//				aux = scan.nextLine();
//				splitado = aux.split(";");
//				
//				jogo.setId(Integer.parseInt(splitado[0]));
//				jogo.setNome(splitado[1]);
//				jogo.setDescricao(splitado[2]);
//				jogo.setNota(Integer.parseInt(splitado[3]));
//				//jogo.setCategorias(splitado[4]);
//				//System.out.println(jogo.formatado());
//				jogos.add(jogo);
//			}
//			
//			System.out.println("Finalizando ler todos os jogos");
//			scan.close();
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		
//		return jogos;
//	}
//	
//	public boolean alterarJogo(int id, String nome, String descricao, int nota) {
//		String jogos = "";
//		
//		File fJogos = new File(arqJogos);
//		
//		try {
//			System.out.println("Iniciando alteração");
//			String aux;
//			String splitado[];
//			Scanner scan = new Scanner(fJogos);
//			
//			while(scan.hasNextLine()) {
//				aux = scan.nextLine();
//				splitado = aux.split(";");
//				
//				if (id == Integer.parseInt(splitado[0])){
//					jogos += splitado[0] + ";" + nome + ";" + descricao + ";" + nota + ";" + splitado[4] + ";"; 
//					jogos += "\r\n";
//				}else {
//					jogos += aux + "\r\n";
//				}
//			}
//			
//			FileWriter w = new FileWriter(arqJogos);
//			w.write(jogos);
//			w.close();
//			
//			System.out.println("Finalizando alteração");
//			scan.close();
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		return true;
//	}
//	
//	public Jogo buscarJogoPorId(int id) {
//		Jogo jogo = null;
//		File fJogos = new File(arqJogos);
//		
//		try {
//			System.out.println("Iniciando busca por ID");
//			String aux;
//			String splitado[];
//			Scanner scan = new Scanner(fJogos);
//			
//			while(scan.hasNextLine()) {
//				aux = scan.nextLine();
//				splitado = aux.split(";");
//				
//				if (id == Integer.parseInt(splitado[0])){
//					jogo = new Jogo(Integer.parseInt(splitado[0]), splitado[1], Integer.parseInt(splitado[3]), splitado[2]);
//				}
//			}
//			
//			scan.close();
//			System.out.println("Finalizando busca por ID");
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		
//		return jogo;
//	}
//	
//	public boolean adicionarJogo(Jogo jogo, Categoria c1, Categoria c2, JogoRepo jogoRepo, CategoriaRepo categoriaRepo) {		
//		File fJogos = new File(arqJogos);
//		String jogos = "";
//		
//		categoriaRepo.save(c1);
//		categoriaRepo.save(c2);
//		jogoRepo.save(jogo);
//		
//		jogo.setId(0);		
//		
//		try {
//			System.out.println("Iniciando adicionar jogo");
//			String aux;
//			Scanner scan = new Scanner(fJogos);
//			
//			while(scan.hasNextLine()) {
//				aux = scan.nextLine();				
//				jogos += aux; 
//				jogos += "\r\n";
//			}
//			
//			jogos += jogo.formatado();
//			
//			FileWriter w = new FileWriter(arqJogos);
//			w.write(jogos);
//			
//			scan.close();
//			w.close();
//			
//			System.out.println("Finalizando adicionar jogo");
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		return true;
//	}
//	
//	public boolean removerJogo(int id) {
//		File fJogos = new File(arqJogos);
//		String jogos = "";
//		
//		try {
//			System.out.println("Iniciando remoção");
//			String aux;
//			String splitado[];
//			Scanner scan = new Scanner(fJogos);
//			
//			while(scan.hasNextLine()) {
//				aux = scan.nextLine();
//				splitado = aux.split(";");
//				
//				if (id != Integer.parseInt(splitado[0])){
//					jogos += aux; 
//					jogos += "\r\n";
//				}
//			}
//			
//			FileWriter w = new FileWriter(arqJogos);
//			w.write(jogos);
//			
//			scan.close();
//			w.close();
//			
//			System.out.println("Finalizando remoção");
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		return true;
//	}
//	
//	public Jogo buscarJogoPorNome(String nome) {
//		Jogo jogo = new Jogo();
//		File fJogos = new File(arqJogos);
//		
//		try {
//			System.out.println("Iniciando busca por nome");
//			String aux;
//			String splitado[];
//			Scanner scan = new Scanner(fJogos);
//			
//			while(scan.hasNextLine()) {
//				aux = scan.nextLine();
//				splitado = aux.split(";");
//				System.out.println(splitado[1]);
//				if (nome.contains(splitado[1])){
//					jogo = new Jogo(Integer.parseInt(splitado[0]), splitado[1], Integer.parseInt(splitado[3]), splitado[2]);
//					break;
//				}
//			}
//			
//			scan.close();
//			System.out.println("Finalizando busca por nome");
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		
//		return jogo;
//	}
//	
//	public String getArqJogos() {
//		return arqJogos;
//	}
//
//	public void setArqJogos(String arqJogos) {
//		this.arqJogos = arqJogos;
//	}
//}
