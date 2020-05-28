package com.company.main;

import com.company.main.services.GameServer;
import com.company.main.ui.GUI;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GUI ui = new GUI(756,480);
        ui.setUpUi();
        ui.buttonHandler();
    }
}
