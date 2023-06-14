package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import bd.Direccionador;
import servicios.SendMail;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class envioEmail extends JFrame {

	private JPanel contentPane;
	private JTextField txt_asunto;
	private JTextField txt_receptor;
	private JTextField txt_remitente;
	static String numero;
	String remitente;
	private JProgressBar progressBar = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					envioEmail frame = new envioEmail(numero);
					frame.setVisible(true);
					centreWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public envioEmail(String numeroR) {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		//remitente = propiedades.getProperty("remitente ");
		numero=numeroR;
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENV\u00CDO DE FACTURA POR EMAIL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(104, 23, 257, 22);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 53, 414, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Remitente:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 75, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Receptor:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 108, 90, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Asunto:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 139, 90, 14);
		contentPane.add(lblNewLabel_3);
		
		txt_asunto = new JTextField();
		txt_asunto.setBounds(104, 137, 320, 20);
		contentPane.add(txt_asunto);
		txt_asunto.setColumns(10);
		
		txt_receptor = new JTextField();
		txt_receptor.setColumns(10);
		txt_receptor.setBounds(104, 106, 283, 20);
		contentPane.add(txt_receptor);
		
		txt_remitente = new JTextField();
		txt_remitente.setEnabled(false);
		txt_remitente.setColumns(10);
		txt_remitente.setBounds(104, 72, 320, 20);
		txt_remitente.setText("santi.buzik@gmail.com");
		contentPane.add(txt_remitente);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(envioEmail.class.getResource("/img/logolupa.png")));
		btnNewButton.setBounds(388, 95, 46, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Adjunto:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_4.setBounds(72, 287, 72, 14);
		contentPane.add(lblNewLabel_4);
		
		final JLabel lbl_adjunto = new JLabel("");
		lbl_adjunto.setBounds(154, 287, 259, 14);
		contentPane.add(lbl_adjunto);
		lbl_adjunto.setText("C:/FLOfiHotel/Imprimibles/Factura"+numero+".pdf");

		progressBar = new JProgressBar();
		progressBar.setBounds(88, 352, 249, 9);
		contentPane.add(progressBar);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(104, 173, 320, 96);
		contentPane.add(textArea);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String receptor = txt_receptor.getText();
				String asunto = txt_asunto.getText();
				String cuerpo = textArea.getText();
				sumarJProgressBar(50);
				
				SendMail send = new SendMail();
				try {
				send.enviarFactura(numero,receptor, asunto, cuerpo);
				sumarJProgressBar(50);
				JOptionPane.showMessageDialog(new JFrame(),"Factura enviada!");
				dispose();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(new JFrame(),ex.getMessage());
					// TODO: handle exception
				}
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(envioEmail.class.getResource("/img/logoenviar2.jpg")));
		btnNewButton_1.setBounds(146, 312, 132, 29);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnNewButton_1.setBorder(emptyBorder);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("Cuerpo:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 177, 90, 14);
		contentPane.add(lblNewLabel_5);
		

	}

	public String getNumero() {
		return numero;
	}
	
	private JProgressBar getJProgressBar() {
		if(progressBar==null) {
			progressBar = new JProgressBar(0,100);
			progressBar.setBounds(10, 191, 495, 20);
			progressBar.setValue(0);
			progressBar.setStringPainted(true);
		}		
		return progressBar;
	}
	
	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	private void sumarJProgressBar(int sumar) {
		getJProgressBar().setValue(getJProgressBar().getValue()+sumar);
	}

	public void setNumero(String numero) {
		envioEmail.numero = numero;
	}
}
