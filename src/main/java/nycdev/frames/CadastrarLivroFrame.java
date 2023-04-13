package nycdev.frames;

import nycdev.SistemaLivro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarLivroFrame extends JFrame{
    JLabel nome, autor, numPag;
    JTextField nomeIn, autorIn, numPagIn;
    JButton salvarButton, cancelarButton;

    SistemaLivro sis;
    public CadastrarLivroFrame(SistemaLivro sis) {
        this.sis = sis;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setAll();
        setLabels();
        setFields();
        setButtons();

        add(nome);
        add(nomeIn);
        add(autor);
        add(autorIn);
        add(numPag);
        add(numPagIn);
        add(cancelarButton);
        add(salvarButton);
    }

    private void setAll() {
        setTitle("Cadastrar livro");
        setSize(400, 200);
        setResizable(false);
        setBackground(Color.lightGray);
        getContentPane().setLayout(new GridLayout(4,2));
        setLocation(330, 80);
    }

    private void setLabels() {
        nome = new JLabel("Nome: ", JLabel.CENTER);
        nome.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        autor = new JLabel("Autor: ", JLabel.CENTER);
        autor.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        numPag = new JLabel("Número de páginas: ", JLabel.CENTER);
        numPag.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    }

    private void setFields() {
        nomeIn = new JTextField();
        autorIn = new JTextField();
        numPagIn = new JTextField();
    }

    private void setButtons() {
        salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeLivro = nomeIn.getText();
                String nomeAutor = autorIn.getText();
                String numPag = numPagIn.getText();
                sis.cadastraLivro(nomeLivro, nomeAutor, numPag);
            }
        });
        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastrarLivroFrame.super.dispose();
            }
        });
    }

    public static void main(String[] args) {
        SistemaLivro s = new SistemaLivro();
        JFrame j = new CadastrarLivroFrame(s);
        j.setVisible(true);
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
