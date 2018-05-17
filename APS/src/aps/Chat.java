package aps;

import com.sun.glass.events.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import static javax.swing.JOptionPane.*;

public class Chat extends javax.swing.JFrame {

    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader isr;
    private boolean rodar;

    //Construtor
    public Chat(String nome) {

        initComponents();

        rodar = true;
        this.nome = nome;

        try {
            s = new Socket("127.0.0.1", 5000);

        } catch (Exception e) {
            showMessageDialog(null, "Não se conectou ao Servidor.", "ChatAPS: Erro 001", ERROR_MESSAGE);
            System.exit(0);
        }
        Thread();
    }

    private void Thread() {

        Thread t = new Thread(new Runnable() {

            String mensagem;

            @Override
            public void run() {

                try {
                    isr = new InputStreamReader(s.getInputStream());
                    br = new BufferedReader(isr);

                    while ((mensagem = br.readLine()) != null) {
                        tar_mensagemRecebida.setText(tar_mensagemRecebida.getText() + mensagem + "\n");

                        if (!rodar) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    showMessageDialog(null, "Erro na conexão com o servidor", "ChatAPS: Erro 001", ERROR_MESSAGE);
                }
            }

        });

        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tar_mensagemRecebida = new javax.swing.JTextArea();
        btn_enviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tar_mensagemEnviada = new javax.swing.JTextArea();
        btn_sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ChatAPS");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("frm_inicial"); // NOI18N
        setResizable(false);

        tar_mensagemRecebida.setEditable(false);
        tar_mensagemRecebida.setColumns(20);
        tar_mensagemRecebida.setLineWrap(true);
        tar_mensagemRecebida.setRows(5);
        jScrollPane1.setViewportView(tar_mensagemRecebida);

        btn_enviar.setText("Enviar");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        tar_mensagemEnviada.setColumns(20);
        tar_mensagemEnviada.setLineWrap(true);
        tar_mensagemEnviada.setRows(5);
        tar_mensagemEnviada.setAutoscrolls(false);
        tar_mensagemEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tar_mensagemEnviadaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tar_mensagemEnviada);

        btn_sair.setText("Sair");
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_enviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_sair)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed

        String mensagem = nome + " disse: ";

        try {
            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += tar_mensagemEnviada.getText();

            ps.println(mensagem);
            ps.flush();

            tar_mensagemEnviada.setText("");
        } catch (IOException e) {
            showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_enviarActionPerformed

    private void tar_mensagemEnviadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tar_mensagemEnviadaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String mensagem = nome + " disse: ";

            try {
                PrintStream ps = new PrintStream(s.getOutputStream());
                mensagem += tar_mensagemEnviada.getText();

                ps.println(mensagem);
                ps.flush();

                tar_mensagemEnviada.setText("");
            } catch (IOException e) {
                showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tar_mensagemEnviadaKeyPressed

    //botao sair
    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed

        try {
            s.close();
            System.exit(0);
            
        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_btn_sairActionPerformed

    public static void main(String args[]) {      
        
        java.awt.EventQueue.invokeLater(new Runnable() {
           
            @Override
            public void run() {
                Chat.setVisible(true); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_enviar;
    private javax.swing.JButton btn_sair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea tar_mensagemEnviada;
    private javax.swing.JTextArea tar_mensagemRecebida;
    // End of variables declaration//GEN-END:variables
}
