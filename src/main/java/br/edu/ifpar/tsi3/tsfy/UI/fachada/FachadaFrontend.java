/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpar.tsi3.tsfy.UI.fachada;

import br.edu.ifpar.tsi3.tsfy.controladores.MusicaControlador;
import br.edu.ifpar.tsi3.tsfy.controladores.PlaylistControlador;
import br.edu.ifpar.tsi3.tsfy.controladores.UsuarioControlador;

/**
 *
 * @author 1071759
 */
public class FachadaFrontend {
    
    private MusicaControlador controladorDeMusica;
    private PlaylistControlador controladorDePlaylist;
    private UsuarioControlador controladorDeUsuario;
    
    public FachadaFrontend(){
        this.controladorDeMusica = new MusicaControlador();
        this.controladorDePlaylist = new PlaylistControlador();
        this.controladorDeUsuario = new UsuarioControlador();
    }

    public MusicaControlador getControladorDeMusica() {
        return controladorDeMusica;
    }

    public void setControladorDeMusica(MusicaControlador controladorDeMusica) {
        this.controladorDeMusica = controladorDeMusica;
    }

    public PlaylistControlador getControladorDePlaylist() {
        return controladorDePlaylist;
    }

    public void setControladorDePlaylist(PlaylistControlador controladorDePlaylist) {
        this.controladorDePlaylist = controladorDePlaylist;
    }

    public UsuarioControlador getControladorDeUsuario() {
        return controladorDeUsuario;
    }

    public void setControladorDeUsuario(UsuarioControlador controladorDeUsuario) {
        this.controladorDeUsuario = controladorDeUsuario;
    }

    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        boolean sucesso = this.controladorDeMusica.registrarMusica(titulo, compositor, interprete, duracao);
        return sucesso;
    }
    
    
    
}
