package com.android.lauracalvente.app4_poketri.Utils;

import com.android.lauracalvente.app4_poketri.Pojos.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Laura on 07/01/2017.
 */

public class Coleccion {

    final private static int NUM_IMG = 6;
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<String> img = new ArrayList<>();

    static {
        players.add(new Player("Carlos","10","28-01-2015 23:02:00"));
        players.add(new Player("Laura","5","12-12-2016 01:02:00"));
        players.add(new Player("Ismael","30","05-05-2016 22:02:50"));
        players.add(new Player("Pedro","1","07-11-2016 11:05:15"));
        players.add(new Player("Sole","18","28-12-2016 11:06:00"));

        img.add(new String("http://orig12.deviantart.net/6c15/f/2012/220/9/d/pixel_mew_by_eri_tchi-d4lcoyj.png"));
        img.add(new String("http://rs795.pbsrc.com/albums/yy232/PixKaruumi/Pokemon%20Pixels/078.png~c200"));
        img.add(new String("http://vignette3.wikia.nocookie.net/gameshark-para-pokemon/images/9/99/Charizard_sprite_by_kriss80858-d5rv76b.png/revision/latest?cb=20141231181052&path-prefix=es"));
        img.add(new String("https://sdl-stickershop.line.naver.jp/products/0/0/1/6742/iphone/main@2x.png"));
        img.add(new String("http://img08.deviantart.net/b65a/i/2016/108/d/a/blastoise_gen1_sprite_recolored_by_skyfyre13-d9zdd93.png"));
        img.add(new String("http://dl.stickershop.line.naver.jp/products/0/0/1/6743/android/stickers/12344679.png"));
    }

    public static String dameImg(){
        String imagen = "";
        Random ale = new Random();
        int num = ale.nextInt(NUM_IMG);

        for(int i=0; i < img.size(); i++)
            if(i == num)
                imagen = img.get(i);

        return imagen;
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}