/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebalibreria;

/**
 *
 * @author josue
 */
import Tiempo.Fechas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FechasTester extends JFrame {
    private JTextArea resultadoArea;
    private JTextField fechaTextoField;
    private JTextField formatoField;
    private JTextField diasField;
    private JTextField fechaInicioField;
    private JTextField fechaFinField;

    public FechasTester() {
        setTitle("Probador de Clase Fechas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de controles
        JPanel controlPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Campos de entrada
        fechaTextoField = new JTextField();
        formatoField = new JTextField("dd/MM/yyyy");
        diasField = new JTextField("0");
        fechaInicioField = new JTextField();
        fechaFinField = new JTextField();

        // Botones para probar cada método
        controlPanel.add(new JLabel("Fecha (texto):"));
        controlPanel.add(fechaTextoField);
        
        controlPanel.add(new JLabel("Formato:"));
        controlPanel.add(formatoField);
        
        controlPanel.add(new JLabel("Días a sumar/restar:"));
        controlPanel.add(diasField);
        
        controlPanel.add(new JLabel("Fecha inicio (rango):"));
        controlPanel.add(fechaInicioField);
        
        controlPanel.add(new JLabel("Fecha fin (rango):"));
        controlPanel.add(fechaFinField);
        
        controlPanel.add(crearBoton("Obtener fecha actual", e -> probarGetDia()));
        controlPanel.add(crearBoton("Convertir texto a fecha", e -> probarTextoFecha()));
        controlPanel.add(crearBoton("Convertir fecha a texto", e -> probarFechaTexto()));
        controlPanel.add(crearBoton("Sumar días", e -> probarSumarDias()));
        controlPanel.add(crearBoton("Sumar días con texto", e -> probarSumarDiasTexto()));
        controlPanel.add(crearBoton("Obtener día/mes/año", e -> probarGetters()));
        controlPanel.add(crearBoton("Obtener día/mes/año (num)", e -> probarGettersNum()));
        controlPanel.add(crearBoton("Diferencia en días", e -> probarDiasDiferencia()));
        controlPanel.add(crearBoton("Verificar rango", e -> probarDiaRango()));

        // Área de resultados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto, ActionListener listener) {
        JButton button = new JButton(texto);
        button.addActionListener(listener);
        return button;
    }

    private void mostrarResultado(String resultado) {
        resultadoArea.append(resultado + "\n\n");
    }

    // Métodos de prueba
    private void probarGetDia() {
        try {
            Date fechaActual = Fechas.getDia();
            mostrarResultado("Fecha actual (sin hora): " + Fechas.FechaTexto(fechaActual, "dd/MM/yyyy HH:mm:ss"));
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarTextoFecha() {
        try {
            String textoFecha = fechaTextoField.getText();
            if (textoFecha.isEmpty()) {
                mostrarResultado("Error: Ingrese una fecha como texto");
                return;
            }
            
            Date fecha = Fechas.TextoFecha(textoFecha);
            if (fecha != null) {
                mostrarResultado("Fecha convertida: " + Fechas.FechaTexto(fecha, "d 'de' MMMM 'de' yyyy"));
            } else {
                mostrarResultado("Error: No se pudo convertir el texto a fecha con los formatos soportados");
            }
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarFechaTexto() {
        try {
            Date fecha = Fechas.TextoFecha(fechaTextoField.getText());
            if (fecha == null) {
                fecha = new Date(); // Usar fecha actual si no se pudo convertir
            }
            
            String formato = formatoField.getText();
            String resultado = Fechas.FechaTexto(fecha, formato);
            mostrarResultado("Fecha formateada (" + formato + "): " + resultado);
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarSumarDias() {
        try {
            Date fecha = Fechas.TextoFecha(fechaTextoField.getText());
            if (fecha == null) {
                fecha = new Date();
            }
            
            int dias = Integer.parseInt(diasField.getText());
            Date nuevaFecha = Fechas.SumarDias(fecha, dias);
            
            mostrarResultado("Fecha original: " + Fechas.FechaTexto(fecha, "dd/MM/yyyy") + 
                           "\nSumando " + dias + " días: " + 
                           Fechas.FechaTexto(nuevaFecha, "dd/MM/yyyy"));
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarSumarDiasTexto() {
        try {
            Date fecha = Fechas.TextoFecha(fechaTextoField.getText());
            if (fecha == null) {
                fecha = new Date();
            }
            
            int dias = Integer.parseInt(diasField.getText());
            String resultado = Fechas.SumarDiasTexto(fecha, dias);
            
            mostrarResultado("Sumar " + dias + " días con texto:\n" + resultado);
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarGetters() {
        try {
            Date fecha = Fechas.TextoFecha(fechaTextoField.getText());
            if (fecha == null) {
                fecha = new Date();
            }
            
            mostrarResultado("Fecha: " + Fechas.FechaTexto(fecha, "dd/MM/yyyy") +
                           "\nDía: " + Fechas.getDia(fecha) +
                           "\nMes: " + Fechas.getMes(fecha) +
                           "\nAño: " + Fechas.getAño(fecha));
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarGettersNum() {
        try {
            Date fecha = Fechas.TextoFecha(fechaTextoField.getText());
            if (fecha == null) {
                fecha = new Date();
            }
            
            mostrarResultado("Fecha: " + Fechas.FechaTexto(fecha, "dd/MM/yyyy") +
                           "\nDía (num): " + Fechas.getDiaNum(fecha) +
                           "\nMes (num): " + Fechas.getMesNum(fecha) +
                           "\nAño (num): " + Fechas.getAñoNum(fecha));
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarDiasDiferencia() {
        try {
            Date fecha1 = Fechas.TextoFecha(fechaInicioField.getText());
            Date fecha2 = Fechas.TextoFecha(fechaFinField.getText());
            
            if (fecha1 == null || fecha2 == null) {
                mostrarResultado("Error: Ingrese ambas fechas válidas");
                return;
            }
            
            long diferencia = Fechas.DiasDiferencia(fecha1, fecha2);
            mostrarResultado("Diferencia entre " + Fechas.FechaTexto(fecha1, "dd/MM/yyyy") + 
                            " y " + Fechas.FechaTexto(fecha2, "dd/MM/yyyy") + 
                            ": " + diferencia + " días");
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    private void probarDiaRango() {
        try {
            Date fecha = Fechas.TextoFecha(fechaTextoField.getText());
            Date inicio = Fechas.TextoFecha(fechaInicioField.getText());
            Date fin = Fechas.TextoFecha(fechaFinField.getText());
            
            if (fecha == null || inicio == null || fin == null) {
                mostrarResultado("Error: Ingrese todas las fechas válidas");
                return;
            }
            
            boolean enRango = Fechas.DiaRango(fecha, inicio, fin);
            mostrarResultado("Fecha: " + Fechas.FechaTexto(fecha, "dd/MM/yyyy") +
                           "\nRango: " + Fechas.FechaTexto(inicio, "dd/MM/yyyy") + 
                           " a " + Fechas.FechaTexto(fin, "dd/MM/yyyy") +
                           "\n¿Está en rango? " + (enRango ? "Sí" : "No"));
        } catch (Exception e) {
            mostrarResultado("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FechasTester tester = new FechasTester();
            tester.setVisible(true);
        });
    }
}