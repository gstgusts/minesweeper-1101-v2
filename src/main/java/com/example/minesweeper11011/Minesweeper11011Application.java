package com.example.minesweeper11011;

import com.example.minesweeper11011.data.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Minesweeper11011Application {

	public static Game game;

	public static void main(String[] args) {
		SpringApplication.run(Minesweeper11011Application.class, args);
	}

}
