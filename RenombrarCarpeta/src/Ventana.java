
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase Ventana Muestra la estructuta que deberia tener una Ventana en Java con
 * la libreria Swing, contiene una etiqueta, un caja de texto y un boton, que
 * tiene la accion de mostrar el texto en la caja por una ventana de mensaje.
 * 
 * @author Daniel Alvarez (a3dany)
 */

public class Ventana extends JFrame implements ActionListener {

	private JLabel texto01; // etiqueta o texto no editable
	private JTextField caja01; // caja de texto, para insertar datos
	private JButton boton01; // boton con una determinada accion
	private File directorio;

	public Ventana() {
		super(); // usamos el contructor de la clase padre JFrame
		configurarVentana(); // configuramos la ventana
		inicializarComponentes(); // inicializamos los atributos o componentes
		directorio = null;
	}

	private void configurarVentana() {
		this.setTitle("Renombrador de archivos -por Manuel Abarca"); // colocamos titulo a la ventana
		this.setSize(400, 400); // colocamos tamanio a la ventana (ancho, alto)
		this.setLocationRelativeTo(null); // centramos la ventana en la pantalla
		this.setLayout(null); // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
		this.setResizable(false); // hacemos que la ventana no sea redimiensionable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hacemos que cuando se cierre la ventana termina todo
																// proceso
	}

	private void inicializarComponentes() {
		// creamos los componentes
		texto01 = new JLabel();
		caja01 = new JTextField();
		boton01 = new JButton();
		// configuramos los componentes
		texto01.setText("Nombre base para los archivos"); // colocamos un texto a la etiqueta
		texto01.setBounds(50, 50, 300, 25); // colocamos posicion y tamanio al texto (x, y, ancho, alto)
		caja01.setBounds(50, 100, 100, 25); // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
		boton01.setText("Seleccionar y renombrar carpeta"); // colocamos un texto al boton
		boton01.setBounds(50, 150, 300, 30); // colocamos posicion y tamanio al boton (x, y, ancho, alto)
		boton01.addActionListener(this); // hacemos que el boton tenga una accion y esa accion estara en esta clase
		// adicionamos los componentes a la ventana
		this.add(texto01);
		this.add(caja01);
		this.add(boton01);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fc = new JFileChooser();
        //Mostrar la ventana para abrir archivo y recoger la respuesta
        //En el parámetro del showOpenDialog se indica la ventana
        //  al que estará asociado. Con el valor this se asocia a la
        //  ventana que la abre.
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int respuesta = fc.showOpenDialog(this);
        //Comprobar si se ha pulsado Aceptar
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            //Crear un objeto File con el archivo elegido
            directorio = fc.getSelectedFile();
            //Mostrar el nombre del archvivo en un campo de texto
            //txtNombre.setText(archivoElegido.getName());
        }
        
        
      
        if(directorio.isDirectory()) {
        	int contador = 1;
        	File [] archivos = directorio.listFiles();
        	int aux = archivos.length;
        	String ceros = "";
        	while(aux>=10) {
        		aux/=10;
        		ceros+="0";
        	}
        	aux = 10;
        	String nombre;
        	if(caja01.getText()!= null && caja01.getText()!="") {
        		nombre = caja01.getText();
        	}else {
        		nombre = directorio.getName();
        	}
        	String raiz = directorio.getAbsolutePath() + "\\";
        	for(int i = 0; i<archivos.length;i++) {
        		if(archivos[i].isFile()) {
        			File nuevoNombre = new File(raiz + nombre + ceros + contador + archivos[i].getName().substring(archivos[i].getName().lastIndexOf('.')));
        			archivos[i].renameTo(nuevoNombre);
        			//archivos[i].
        			//System.out.println(archivos[i].getAbsolutePath().substring(0, archivos[i].getAbsolutePath().lastIndexOf('\\')+ 1));
        			System.out.println(nuevoNombre);
        			contador++;
        			if(contador == aux) {
        				aux*=10;
        				ceros = ceros.substring(0, ceros.lastIndexOf('0'));
        			}
        		}
        	}
        	
        	JOptionPane.showMessageDialog(this, "Archivos de la carpeta renombrados");
        }else {
        	JOptionPane.showMessageDialog(this, "Error al seleccionar la carpeta"); // mostramos un mensaje (frame, mensaje)
        }
		
	}

	public static void main(String[] args) {
		Ventana V = new Ventana(); // creamos una ventana
		V.setVisible(true); // hacemos visible la ventana creada
	}

}
