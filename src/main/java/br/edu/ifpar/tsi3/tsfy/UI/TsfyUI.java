/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpar.tsi3.tsfy.UI;

import java.util.Arrays;
import java.util.Scanner;
import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;
import br.edu.ifpar.tsi3.tsfy.dominio.Playlist;
import br.edu.ifpar.tsi3.tsfy.dominio.Musica;

/**
 *
 * @author 1071759
 */
public class TsfyUI {

    private final Scanner sc = new Scanner(System.in);
    private Usuario[] todosOsPerfis = new Usuario[10];
    private int quantidadeUsuarios = 0;
    
    private Musica[] todasAsMusicas = new Musica[10];
    private int quantidadeMusicas = 0;
    
    private Playlist[] todasAsPlaylists = new Playlist[10];
    private int quantidadePlaylists = 0;
    
    
    public void rodar() {
        int op;
        Usuario usuarioLogado = null;

        while (true) {
            menuDeLogin();
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    criarNovoUsuario();
                    break;
                case 2:
                    usuarioLogado = autenticar();
                    if (usuarioLogado != null) {
                        System.out.println("Login realizado com sucesso.");
                    } else {
                        System.out.println("CPF ou senha inválidos.");
                    }
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarPlaylists();
                    break;
                case 5:
                    getMusicasCadastradas();
                    break;
                case 6:
                    removerMusica();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (usuarioLogado != null) {
                break;
            }
        }

        do {
            menu();
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    registrarMusica();
                    break;
                case 2:
                    editarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    criarPlaylist();
                    break;
                case 5:
                    listarPlaylists();
                    
                    break;
                case 6:
                    buscarMusicaPorID();
                    break;
                case 7:
                    removerMusica();
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (op != 0);
    }
    
    public void menu(){
        System.out.println("------ Menu de Operacoes ------");
        System.out.println("1 - Criar musica");
        System.out.println("2 - Editar musica");
        System.out.println("3 - Listar musicas");
        System.out.println("4 - criar playlists");
        System.out.println("5 - Listar playlists");
        System.out.println("6 - Buscar musica por ID");
        System.out.println("7 - Remover musica");
        System.out.println("0 - Sair");
    }

    private void registrarMusica() {
        System.out.println("Qual é o título da musica?");
        String titulo = sc.nextLine();
        System.out.println("Qual é o compositor da musica?");
        String compositor = sc.nextLine();
        System.out.println("Qual é o interprete da musica?");
        String interprete = sc.nextLine();
        System.out.println("Qual é a duracao da musica?");
        Double duracao = Double.parseDouble(sc.nextLine());

        if (this.quantidadeMusicas == this.todasAsMusicas.length) {
            this.todasAsMusicas = Arrays.copyOf(this.todasAsMusicas, this.todasAsMusicas.length + 10);
        }

        this.todasAsMusicas[this.quantidadeMusicas++] = new Musica(titulo, compositor, interprete, duracao);
        System.out.println("Música adicionada com sucesso à base de dados.");
    }

    private void criarPlaylist() {
        System.out.println("Escolha uma playlist para adicionar músicas:");
        System.out.println("Informe o nome da playlist:");
        String nome = sc.nextLine();
        System.out.println("Informe a descrição da playlist:");
        String descricao = sc.nextLine();

        if (this.quantidadePlaylists == this.todasAsPlaylists.length) {
            this.todasAsPlaylists = Arrays.copyOf(this.todasAsPlaylists, this.todasAsPlaylists.length + 10);
        }

        Playlist playlist = new Playlist(null, nome, descricao);
        this.todasAsPlaylists[this.quantidadePlaylists++] = playlist;
        System.out.println("Playlist criada com sucesso.");

        if (this.quantidadeMusicas == 0) {
            System.out.println("Nenhuma música cadastrada para adicionar à playlist.");
            return;
        }

        System.out.println("Músicas disponíveis:");
        for (int i = 0; i < this.quantidadeMusicas; i++) {
            Musica musica = this.todasAsMusicas[i];
            System.out.println("[" + i + "] " + musica.getTitulo() + " - " + musica.getInterprete());
        }

        System.out.println("Digite o número da música que deseja adicionar à playlist ou 999 para sair:");
        while (true) {
            int indice = Integer.parseInt(sc.nextLine());
            if (indice == 999) {
                break;
            }
            if (indice < 0 || indice >= this.quantidadeMusicas) {
                System.out.println("Índice inválido. Tente novamente.");
                continue;
            }

            playlist.adicionarAPlaylist(this.todasAsMusicas[indice]);
            System.out.println("Música adicionada à playlist " + playlist.getNome() + ".");
            System.out.println("Digite outro número de música ou 999 para sair:");
        }
    }
    
//    private void criarMusica() {
//        System.out.println("Qual é o título da musica?");
//        String titulo = sc.nextLine();
//        System.out.println("Qual é o compositor da musica?");
//        String compositor = sc.nextLine();
//        System.out.println("Qual é o interprete da musica?");
//        String interprete = sc.nextLine();
//        System.out.println("Qual é a duracao da musica?");
//        Double duracao = Double.parseDouble(sc.nextLine());
//        Musica musica = new Musica(titulo, compositor, interprete, duracao);
//        todasAsMusicas.add(musica);
//    }

    private void editarMusica() {
        if (this.quantidadeMusicas == 0) {
            System.out.println("Nenhuma música cadastrada para editar.");
            return;
        }
        System.out.println("Esta é meu banco de dados de músicas: ");
        for (int i = 0; i < this.quantidadeMusicas; i++){
            System.out.println("[" + i + "] " + this.todasAsMusicas[i].getTitulo() + " (intérprete: " + this.todasAsMusicas[i].getInterprete() + ")");
        }
        
        System.out.println("Informe qual é o ID da música que você deseja editar: ");
        int id = Integer.parseInt(sc.nextLine());
        if (id < 0 || id >= this.quantidadeMusicas) {
            System.out.println("ID de música inválido.");
            return;
        }
        
        System.out.println("Qual é o título da musica?");
        String titulo = sc.nextLine();
        System.out.println("Qual é o compositor da musica?");
        String compositor = sc.nextLine();
        System.out.println("Qual é o interprete da musica?");
        String interprete = sc.nextLine();
        System.out.println("Qual é a duracao da musica?");
        Double duracao = Double.parseDouble(sc.nextLine());
        
        Musica musica = this.todasAsMusicas[id];
        musica.setTitulo(titulo);
        musica.setCompositor(compositor);
        musica.setInterprete(interprete);
        musica.setDuracao(duracao);

        System.out.println("Musica editada com sucesso.");
    }

    private void listarMusicas() {
        if (this.quantidadeMusicas == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        if (this.quantidadePlaylists == 0) {
            System.out.println("Nenhuma playlist cadastrada. Mostrando todas as músicas:");
            for (int i = 0; i < this.quantidadeMusicas; i++) {
                System.out.println("[" + i + "] " + this.todasAsMusicas[i].getTitulo() + " (intérprete: " + this.todasAsMusicas[i].getInterprete() + ")");
            }
            return;
        }

        System.out.println("Escolha uma playlist para listar as músicas:");
        for (int i = 0; i < this.quantidadePlaylists; i++) {
            Playlist playlist = this.todasAsPlaylists[i];
            System.out.println("[" + i + "] " + playlist.getNome() + " - " + playlist.getDescricao());
        }
        System.out.println("[" + this.quantidadePlaylists + "] Ver todas as músicas");

        int opcao = Integer.parseInt(sc.nextLine());
        if (opcao == this.quantidadePlaylists) {
            for (int i = 0; i < this.quantidadeMusicas; i++) {
                System.out.println("[" + i + "] " + this.todasAsMusicas[i].getTitulo() + " (intérprete: " + this.todasAsMusicas[i].getInterprete() + ")");
            }
            return;
        }

        if (opcao < 0 || opcao >= this.quantidadePlaylists) {
            System.out.println("Opção de playlist inválida.");
            return;
        }

        Playlist playlist = this.todasAsPlaylists[opcao];
        if (playlist.getMusicas() == null || playlist.getMusicas().isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        System.out.println("Músicas da playlist '" + playlist.getNome() + "':");
        for (Musica musica : playlist.getMusicas()) {
            System.out.println("- " + musica.getTitulo() + " (" + musica.getInterprete() + ")");
        }
    }

    private void listarPlaylists() {
        if (this.quantidadePlaylists == 0) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }
        System.out.println("Playlists cadastradas:");
        for (int i = 0; i < this.quantidadePlaylists; i++) {
            Playlist playlist = this.todasAsPlaylists[i];
            System.out.println("[" + i + "] " + playlist.getNome() + " - " + playlist.getDescricao() + " (dono: " + (playlist.getDono() != null ? playlist.getDono().getNome() : "sem dono") + ")");
        }
    }

    private void buscarMusicaPorID() {
        if (this.quantidadeMusicas == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        System.out.println("Informe qual é o ID da música que você deseja buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        if (id < 0 || id >= this.quantidadeMusicas) {
            System.out.println("ID de música inválido.");
            return;
        }

        Musica musica = this.todasAsMusicas[id];
        System.out.println("Título: " + musica.getTitulo());
        System.out.println("Compositor: " + musica.getCompositor());
        System.out.println("Interprete: " + musica.getInterprete());
        System.out.println("Duracao: " + musica.getDuracao());
    }

    private void removerMusica() {
        if (this.quantidadeMusicas == 0) {
            System.out.println("Nenhuma música cadastrada para remover.");
            return;
        }

        System.out.println("Esta é meu banco de dados de músicas: ");
        for (int i = 0; i < this.quantidadeMusicas; i++){
            System.out.println("[" + i + "] " + this.todasAsMusicas[i].getTitulo() + " (intérprete: " + this.todasAsMusicas[i].getInterprete() + ")");
        }
        
        System.out.println("Informe qual é o ID da música que você deseja remover: ");
        int id = Integer.parseInt(sc.nextLine());
        if (id < 0 || id >= this.quantidadeMusicas) {
            System.out.println("ID de música inválido.");
            return;
        }

        for (int i = id; i < this.quantidadeMusicas - 1; i++) {
            this.todasAsMusicas[i] = this.todasAsMusicas[i + 1];
        }
        this.todasAsMusicas[--this.quantidadeMusicas] = null;
        System.out.println("Música removida com sucesso.");
    }
    
    public Usuario[] getUsuariosCadastrados() {
        return Arrays.copyOf(this.todosOsPerfis, this.quantidadeUsuarios);
    }
    
    public Musica[] getMusicasCadastradas() {
        return Arrays.copyOf(this.todasAsMusicas, this.quantidadeMusicas);
    }
    
    public Playlist[] getPlaylists() {
        return Arrays.copyOf(this.todasAsPlaylists, this.quantidadePlaylists);
    }


    // private boolean playlistExiste(String nome) {
    //     for (int i = 0; i < this.quantidadePlaylists; i++){
    //         if (this.todasAsPlaylists[i].getNome().equalsIgnoreCase(nome)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    private void menuDeLogin() {
        System.out.println("------ Menu de Login ------");
        System.out.println("1 - Criar novo usuário");
        System.out.println("2 - Autenticar");
        System.out.println("0 - Sair");
    }

    private void criarNovoUsuario() {
        System.out.println("Informe seu nome:");
        String nome = sc.nextLine();
        System.out.println("Informe seu cpf: ");
        String cpf = sc.nextLine();
        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();
        
        if (this.quantidadeUsuarios == this.todosOsPerfis.length) {
            this.todosOsPerfis = Arrays.copyOf(this.todosOsPerfis, this.todosOsPerfis.length + 10);
        }
        this.todosOsPerfis[this.quantidadeUsuarios++] = new Usuario(cpf, nome, senha);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    // private Playlist buscarPlaylistPorNome(String nome) {
    //     for (int i = 0; i < this.quantidadePlaylists; i++) {
    //         Playlist playlist = this.todasAsPlaylists[i];
    //         if (playlist.getNome().equalsIgnoreCase(nome)) {
    //             return playlist;
    //         }
    //     }
    //     return null;
    // }

    private Usuario autenticar() {
        System.out.println("Informe seu cpf: ");
        String cpf = sc.nextLine();
        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();
        
        for (int i = 0; i < this.quantidadeUsuarios; i++){
            Usuario usuario = this.todosOsPerfis[i];
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        }
        return null;
    }
    
}
