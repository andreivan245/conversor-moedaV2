package br.com.andre.NovoConversorMoeda;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Combinacoes {
	public Vector<String> retornaListaCombinacao() throws ParserConfigurationException, SAXException {
		try {
			Vector<String> listaCombinacao = new Vector<String>();

			File file = new File("available.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();

			for (int temp = 0; temp < nl.getLength(); temp++) {

				listaCombinacao.add(nl.item(temp).getNodeName());

			}
			return listaCombinacao;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}

	}

	public Vector<String> retornaListaTextoOrdenada() throws ParserConfigurationException, SAXException {
		try {

			Vector<String> listaTexto = new Vector<String>();
			File file = new File("available.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();

			for (int temp = 0; temp < nl.getLength(); temp++) {

				listaTexto.add(nl.item(temp).getTextContent().replace("/", " para "));
			}

			Collections.sort(listaTexto);
			return listaTexto;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}

	}

	public Vector<String> retornaListaTextoOriginal() throws ParserConfigurationException, SAXException {
		try {

			Vector<String> listaTexto = new Vector<String>();
			File file = new File("available.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(file);
			Element docEle = dom.getDocumentElement();
			NodeList nl = docEle.getChildNodes();

			for (int temp = 0; temp < nl.getLength(); temp++) {

				listaTexto.add(nl.item(temp).getTextContent().replace("/", " para "));
			}

			return listaTexto;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}

	}
}
