/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifpar.tsi3.tsfy.controladores;

import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;
import java.util.ArrayList;

/**
 *
 * @author 1071759
 */
public class UsuarioControlador {
    
    private ArrayList<Usuario> todosOsPerfis = new ArrayList<>();
    
    public boolean registrarUsuario(Usuario usuario) {
        for (Usuario existente : this.todosOsPerfis) {
            if (existente.getCpf().equals(usuario.getCpf())) {
                return false;
            }
        }
        this.todosOsPerfis.add(usuario);
        return true;
    }
    
    public Usuario autenticar(String cpf, String senha) {
        for (Usuario usuario : this.todosOsPerfis) {
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
    
    public ArrayList<Usuario> listarUsuarios() {
        return new ArrayList<>(this.todosOsPerfis);
    }
}
