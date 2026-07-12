/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpar.tsi3.tsfy.dominio;

import java.util.ArrayList;

/**
 *
 * @author 1071759
 */
public class Playlist {
    
    private Usuario dono;
    
    private String nome;
    
    private String descricao;
    
    private ArrayList<Musica> musicas;
    
    public Playlist() {
        this.musicas = new ArrayList<>();
    }

    public Playlist(Usuario dono, String nome, String descricao) {
        this.dono = dono;
        this.nome = nome;
        this.descricao = descricao;
        this.musicas = new ArrayList<>();
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }
    
    public void adicionarAPlaylist(Musica musica){
        this.musicas.add(musica);
    }
    
    @Override
    public String toString() {
        return "Playlist{nome='" + nome + "', descricao='" + descricao + "', dono='" + (dono != null ? dono.getNome() : "sem dono") + "'}";
    }
    
    //TODO: e os demais métodos de manipular músicas da playlist.
}
