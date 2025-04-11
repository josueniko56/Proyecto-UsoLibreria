/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tiempo;

/**
 *
 * @author josue
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Fechas {

    private static final Locale ESPAÑOL = new Locale("es", "ES");
//--------------------tipos de formatos--------------------
    
    private static final String[] TIPO_FORMATO = {
        "dd/MM/yyyy", "dd-MM-yyyy", "yyyy-MM-dd", "dd/MM/yy", "d 'de' MMMM 'de' yyyy"
    };

    // Obtener la fecha actual sin hora
    public static Date getDia() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
/*
    //-------------------- Convierte una fecha a texto--------------------
    public static String FechaTexto(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato, ESPAÑOL);
        return sdf.format(fecha);
    }
*/
    //---------------------Nuevo Metodo con validacion de formato----------------------------------
    public static String FechaTexto(Date fecha, String formato) {
    if (fecha == null || formato == null || formato.isEmpty()) {
        throw new IllegalArgumentException("Fecha o formato no válidos.");
    }
    return new SimpleDateFormat(formato, ESPAÑOL).format(fecha);
}

    //-------------------- Convertir un texto a fecha, aceptando varios formatos--------------------
    public static Date TextoFecha(String fechaTexto) {
        for (String formato : TIPO_FORMATO) {
            try {
                return new SimpleDateFormat(formato, ESPAÑOL).parse(fechaTexto);
            } catch (ParseException e) {
                // Intentar siguiente en el arreglo
            }
        }
        return null;
    }

    // --------------------Convertir con un solo formato específico--------------------
    public static Date TextoFecha(String texto, String formato) {
        try {
            return new SimpleDateFormat(formato, ESPAÑOL).parse(texto);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // --------------------Sumar días y mostrar día con texto, ejemplo 7+3="10 jueves"--------------------
    public static String SumarDiasTexto(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        return new SimpleDateFormat("dd EEEE", ESPAÑOL).format(cal.getTime());
    }

    //-------------------- Métodos get para día/mes/año como texto--------------------
    public static String getDia(Date fecha) {
        return new SimpleDateFormat("dd", ESPAÑOL).format(fecha);
    }

    public static String getMes(Date fecha) {
        return new SimpleDateFormat("MMMM", ESPAÑOL).format(fecha);
    }

    public static String getAño(Date fecha) {
        return new SimpleDateFormat("yyyy", ESPAÑOL).format(fecha);
    }

    //-------------------- Métodos get para día/mes/año como número------------------------------------
    public static int getDiaNum(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMesNum(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getAñoNum(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.YEAR);
    }

    //-------------------- Sumar días--------------------
    public static Date SumarDias(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        return cal.getTime();
    }

    // --------------------Diferencia de días--------------------
    public static long DiasDiferencia(Date inicio, Date fin) {
        long dif = fin.getTime() - inicio.getTime();
        return dif / (1000 * 60 * 60 * 24);
    }

    // --------------------Verificar rango de fecha--------------------
    public static boolean DiaRango(Date fecha, Date inicio, Date fin) {
        return !fecha.before(inicio) && !fecha.after(fin);
    }

    // --------------------PRUEBAS DE METODOS--------------------
    public static void main(String[] args) {
        Date hoy = getDia();

        System.out.println("Hoy: " + FechaTexto(hoy, "EEEE dd 'de' MMMM 'de' yyyy"));

        String texto = "07/04/2025";
        Date fechaTexto = TextoFecha(texto);

        System.out.println("Fecha convertida: " + FechaTexto(fechaTexto, "EEEE dd MMM yyyy"));

        Date en3dias = SumarDias(hoy, 3);
        System.out.println("Suma de 3 días: " + FechaTexto(en3dias, "EEEE dd MMM yyyy"));

        System.out.println("¿Qué día cae en 3 días?: " + SumarDiasTexto(hoy, 3));

        System.out.println("Día: " + getDia(hoy));
        System.out.println("Mes: " + getMes(hoy));
        System.out.println("Año: " + getAño(hoy));

        System.out.println("Día (número): " + getDiaNum(hoy));
        System.out.println("Mes (número): " + getMesNum(hoy));
        System.out.println("Año (número): " + getAñoNum(hoy));
    }
}
