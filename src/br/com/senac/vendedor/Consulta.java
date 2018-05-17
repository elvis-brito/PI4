/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senac.vendedor;

import br.com.senac.cliente.ClienteCadastro;
import br.com.senac.cliente.ClienteControl;
import br.com.senac.cliente.ClienteEndereco;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jussara Andrade
 */
public class Consulta extends javax.swing.JFrame {
    List<ClienteCadastro> clienteCadastro;
    List<ClienteEndereco> clienteEndereco;
    DefaultTableModel tmCliente = new DefaultTableModel (null, new String []{"ID", "Nome", "Sexo", "RG", "CPF", "Data Nascimento", "E-mail", "Celular", "Telefone", "Data Cadastro"});
    DefaultTableModel tabelaEndereco = new DefaultTableModel (null, new String []{"Rua", "Número", "Bairro", "Cidade", "UF", "CEP", "Complemento"});
    /** Creates new form Pesquisa */
    public Consulta() {
        initComponents();
        setLocationRelativeTo(null);  
        setResizable(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPesquisar = new javax.swing.JPanel();
        lblPesquisar = new javax.swing.JLabel();
        txtPesquisaDadosCliente = new javax.swing.JTextField();
        ButtonPesquisarDados = new javax.swing.JButton();
        TabelaDadosClientes = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelPesquisar.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Clientes"));

        lblPesquisar.setText("Pesquisar:");

        ButtonPesquisarDados.setText("Pesquisar");
        ButtonPesquisarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPesquisarDadosActionPerformed(evt);
            }
        });

        tbCliente.setModel(tmCliente);
        tbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClienteMouseClicked(evt);
            }
        });
        TabelaDadosClientes.setViewportView(tbCliente);

        javax.swing.GroupLayout jPanelPesquisarLayout = new javax.swing.GroupLayout(jPanelPesquisar);
        jPanelPesquisar.setLayout(jPanelPesquisarLayout);
        jPanelPesquisarLayout.setHorizontalGroup(
            jPanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TabelaDadosClientes)
                    .addGroup(jPanelPesquisarLayout.createSequentialGroup()
                        .addComponent(lblPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisaDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonPesquisarDados)
                        .addContainerGap(507, Short.MAX_VALUE))))
        );
        jPanelPesquisarLayout.setVerticalGroup(
            jPanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPesquisarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisar)
                    .addComponent(txtPesquisaDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonPesquisarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabelaDadosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPesquisarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPesquisarDadosActionPerformed
            
        
          ClienteControl cli = new ClienteControl();
          clienteCadastro = cli.listaCliente(txtPesquisaDadosCliente.getText());
          
       
        if (clienteCadastro == null){
            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado");
        }else{
            while(tmCliente.getRowCount() > 0){
                 tmCliente.removeRow(0);
            }
            
            String [] campos = new String[]{null, null, null, null, null, null, null, null, null, null};
                for(int i = 0; i < clienteCadastro.size(); i++){
                    
                tmCliente.addRow(campos);
                tmCliente.setValueAt(clienteCadastro.get(i).getIdCliente(), i, 0);
                tmCliente.setValueAt(clienteCadastro.get(i).getNomeCliente(), i, 1);
                tmCliente.setValueAt(clienteCadastro.get(i).getSexoCliente(), i, 2);
                tmCliente.setValueAt(clienteCadastro.get(i).getRgCliente(), i, 3);
                tmCliente.setValueAt(clienteCadastro.get(i).getCpfCliente(), i, 4);
                tmCliente.setValueAt(clienteCadastro.get(i).getDataNascimentoCliente(), i, 5);
                tmCliente.setValueAt(clienteCadastro.get(i).getEmailCliente(), i, 6);
                tmCliente.setValueAt(clienteCadastro.get(i).getCelularCliente(), i, 7);
                tmCliente.setValueAt(clienteCadastro.get(i).getTelefoneCliente(), i, 8);
                tmCliente.setValueAt(clienteCadastro.get(i).getDataCadastroCliente(), i, 9);
                
                
                }
        
         
        
        }
        
    }//GEN-LAST:event_ButtonPesquisarDadosActionPerformed

    private void tbClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClienteMouseClicked
   
    }//GEN-LAST:event_tbClienteMouseClicked

    /**
     * @param args the command line arguments
     */
  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonPesquisarDados;
    private javax.swing.JScrollPane TabelaDadosClientes;
    private javax.swing.JPanel jPanelPesquisar;
    private javax.swing.JLabel lblPesquisar;
    private javax.swing.JTable tbCliente;
    private javax.swing.JTextField txtPesquisaDadosCliente;
    // End of variables declaration//GEN-END:variables

    

}
