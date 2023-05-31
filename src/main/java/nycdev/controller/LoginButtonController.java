package nycdev.controller;

import nycdev.PersonalLibrary;
import nycdev.PersonalLibrarySystem;
import nycdev.frames.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonController implements ActionListener {
    PersonalLibrary personalLibrary;
    MenuFrame menuFrame;

    public LoginButtonController(PersonalLibrary personalLibrary) {
        this.personalLibrary = personalLibrary;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personalLibrary.changeToMenu();
    }
}
