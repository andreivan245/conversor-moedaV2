package br.com.andre.NovoConversorMoeda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.google.gson.Gson;


public class ConsumirAPI {
	
	
	//CONSUMINDO A API DA Awesome API 
	public void ChamaAPI(String Combinacao, Double Valor) {
		
		try{
			
			URL url = new URL("https://economia.awesomeapi.com.br/json/"+Combinacao);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200) {
				System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output,json="";
			while ((output = br.readLine()) != null) {
				json+= output;
			}
			
			
			//TRANSFORMANDO O JSON EM UM OBJETO
			Gson gson = new Gson();
			ValoresMoedaAPI[] moeda = gson.fromJson(json, ValoresMoedaAPI[].class);
			
			
			
			
			for(ValoresMoedaAPI valores : moeda) {
			  //CONVERTENDO A COTACAO PARA O CALCAULO E FORMATANDO O VALOR DO RESULTADO DO CALCULO
			  double Cotacao = Double.parseDouble(valores.ask);
			  double resultado = Valor * Cotacao;
			  NumberFormat formatar = new DecimalFormat("#0.00");
			  
			  
			  //FORMATANDO A DATA RECEBIDA DO JSON
			  String pattern = "yyyy-MM-dd hh:mm:ss";
			  String newpattern = "dd/MM/yyyy hh:mm:ss";
			  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			  Date datas =  sdf.parse(valores.create_date.toString());
			  SimpleDateFormat newsdf = new SimpleDateFormat(newpattern);
			  String dataFormatada = newsdf.format(datas);
			  String NovadataFormatada = dataFormatada.replace(" ", " às ");
			  
			  //PAINEL COM O RESULTADO DA CONVERSÃO
			  String textoFormatado = valores.name.toString().replace("/", " para ");
			  JOptionPane.showMessageDialog(null, "O valor "+ formatar.format(Valor) +" de "+ textoFormatado + " é " + formatar.format(resultado) + ". Baseado na cotação de " + NovadataFormatada,"Resultado", JOptionPane.PLAIN_MESSAGE);
				
			}
			
			
			conn.disconnect();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Essa combinação é inválida, por favor tente outra.","COMBINAÇÃO INVÁLIDA", JOptionPane.ERROR_MESSAGE);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			//TELA DE ERRO CASO OCORRA ALGUM ERRO DURANTE A TENTATIVA DE CONSUMIR A API
			JOptionPane.showMessageDialog(null, "Não foi possível requisitar os valores de cotação, por favor tente novamente.","HTTP ERROR CODE", JOptionPane.ERROR_MESSAGE);
		}
		
		}
	}
	
	
		
		
	
	

