package controlador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class compartirFactura extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					compartirFactura frame = new compartirFactura();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public compartirFactura() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Asunto:");
		lblNewLabel.setBounds(10, 98, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cuerpo:");
		lblNewLabel_1.setBounds(10, 145, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 165, 414, 170);
		contentPane.add(textArea_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Guardar mensaje generico");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxNewCheckBox.setBounds(10, 342, 186, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_2 = new JLabel("Remitente:");
		lblNewLabel_2.setBounds(10, 24, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Destinatario:");
		lblNewLabel_3.setBounds(10, 62, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(54, 95, 370, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(84, 59, 340, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(74, 21, 350, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(Color.GREEN);
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setBounds(107, 415, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(206, 415, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
}
