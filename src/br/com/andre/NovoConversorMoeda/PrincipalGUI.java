package br.com.andre.NovoConversorMoeda;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class PrincipalGUI {

	private JFrame frmConversorDeMoeda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalGUI window = new PrincipalGUI();
					window.frmConversorDeMoeda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public PrincipalGUI() throws ParserConfigurationException, SAXException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	private void initialize() throws ParserConfigurationException, SAXException {
		Combinacoes teste = new Combinacoes();
		Vector<String> listaCombina = teste.retornaListaCombinacao();
		Vector<String> listaTextoOrdenada = teste.retornaListaTextoOrdenada();
		Vector<String> listaTextoOriginal = teste.retornaListaTextoOriginal();

		frmConversorDeMoeda = new JFrame();
		frmConversorDeMoeda.setTitle("Conversor de Moeda");
		frmConversorDeMoeda.setBounds(100, 100, 450, 300);
		frmConversorDeMoeda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorDeMoeda.getContentPane().setLayout(null);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		// VERIFICA SE O CAMPO DE TEXTO VALOR ESTA RECEBENDO LETRA, SE SIM ELA NAO
		// ACEITA O VALOR POREM QUALQUER OUTRO VALOR É ACEITO COM POR EXEMPLO PONTUAÇÕES
		// EM GERAL
		formattedTextField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if (Character.isLetter(c) && !e.isAltDown()) {
					e.consume();
				}
			}
		});

		formattedTextField.setBounds(48, 152, 333, 20);
		frmConversorDeMoeda.getContentPane().add(formattedTextField);

		JButton btnNewButton = new JButton("Converter");
		btnNewButton.setBounds(158, 200, 101, 23);

		frmConversorDeMoeda.getContentPane().add(btnNewButton);

		JComboBox<String> comboBox = new JComboBox<String>(listaTextoOrdenada);
		comboBox.setBounds(48, 55, 333, 22);
		frmConversorDeMoeda.getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("Conversão");
		lblNewLabel.setBounds(48, 30, 74, 14);
		frmConversorDeMoeda.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Valor");
		lblNewLabel_1.setBounds(48, 127, 46, 14);
		frmConversorDeMoeda.getContentPane().add(lblNewLabel_1);
		frmConversorDeMoeda.setLocationRelativeTo(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ARMAZENA VALORES DOS INPUTS
				String Combinacao = comboBox.getSelectedItem().toString();
				String ValorString = formattedTextField.getText();
				String NovaValorString = ValorString.replace(",", ".");

				// CAPTURA A EXCECAO DE NUMBERFORMAT
				try {
					double Valor = Double.parseDouble(NovaValorString);
					ConsumirAPI resultadoAPI = new ConsumirAPI();
					resultadoAPI.ChamaAPI(listaCombina.get(listaTextoOriginal.indexOf(Combinacao)), Valor);

					System.exit(0);
				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null,
							"Você digitou algum caracter inválido, digite apenas números separados por apenas um '.' (Ponto) ou ',' (Vírgula). ",
							"CARACTER DIGITADO INVÁLIDO!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
