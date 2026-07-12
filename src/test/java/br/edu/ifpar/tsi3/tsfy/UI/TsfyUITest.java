package br.edu.ifpar.tsi3.tsfy.UI;

import br.edu.ifpar.tsi3.tsfy.dominio.Musica;
import br.edu.ifpar.tsi3.tsfy.dominio.Playlist;
import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TsfyUITest {

    @Test
    void deveCriarPlaylistEAdicionarMusica() {
        Usuario dono = new Usuario("123", "Ana", "1234");
        Playlist playlist = new Playlist(dono, "Rock", "Músicas favoritas");
        Musica musica = new Musica("Bohemian Rhapsody", "Queen", "Queen", 5.55);

        playlist.adicionarAPlaylist(musica);

        assertNotNull(playlist);
        assertEquals("Rock", playlist.getNome());
        assertEquals(1, playlist.getMusicas().size());
        assertEquals("Bohemian Rhapsody", playlist.getMusicas().get(0).getTitulo());
    }

    @Test
    void deveCriarPlaylistNoArrayDaInterface() throws Exception {
        TsfyUI ui = new TsfyUI();

        Field scannerField = TsfyUI.class.getDeclaredField("sc");
        scannerField.setAccessible(true);
        scannerField.set(ui, new Scanner("Rock\nMúsicas favoritas\n0\n999\n"));

        Field musicasField = TsfyUI.class.getDeclaredField("todasAsMusicas");
        musicasField.setAccessible(true);
        musicasField.set(ui, new Musica[]{new Musica("Bohemian Rhapsody", "Queen", "Queen", 5.55)});

        Field quantidadeMusicasField = TsfyUI.class.getDeclaredField("quantidadeMusicas");
        quantidadeMusicasField.setAccessible(true);
        quantidadeMusicasField.setInt(ui, 1);

        Method metodo = TsfyUI.class.getDeclaredMethod("criarPlaylist");
        metodo.setAccessible(true);
        metodo.invoke(ui);

        Field playlistsField = TsfyUI.class.getDeclaredField("todasAsPlaylists");
        playlistsField.setAccessible(true);
        Playlist[] playlists = (Playlist[]) playlistsField.get(ui);

        Field quantidadePlaylistsField = TsfyUI.class.getDeclaredField("quantidadePlaylists");
        quantidadePlaylistsField.setAccessible(true);
        int quantidadePlaylists = quantidadePlaylistsField.getInt(ui);

        assertEquals(1, quantidadePlaylists);
        assertEquals("Rock", playlists[0].getNome());
        assertEquals(1, playlists[0].getMusicas().size());
    }
}
