/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpar.tsi3.tsfy.controladores;

import br.edu.ifpar.tsi3.tsfy.dominio.Musica;
import java.util.ArrayList;

/**
 *
 * @author 1071759
 */
public class MusicaControlador {

    
    public ArrayList<Musica> todasAsMusicas = new ArrayList<>();
    
    
    // Como sei que a música ja não existe?
    public boolean registrarMusica(String titulo, String compositor, String interprete, Double duracao) {
        Musica novaMusica = new Musica(titulo, compositor, interprete, duracao);
        this.todasAsMusicas.add(novaMusica);
        return true;
    }
    
}
