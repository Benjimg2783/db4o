import java.io.*;
import java.util.List;

import com.db4o.*;


public class PrimerosPasos extends Funciones {
    final static String nombreArchivo = System.getProperty("user.home")
            + "/formula1.db4o";

    public static void main(String[] args) {
        accessDb4o();
        new File(nombreArchivo).delete();
        ObjectContainer db = Db4o.openFile(Db4o
                .newConfiguration(), nombreArchivo);
        try {
            guardarPrimerParticipante(db);
            guardarSegundoParticipante(db);
            consultarTodosParticipantes(db);
            conseguirParticipantePorNombre(db);
            actualizarParticipante(db);
            conseguirParticipantePorPuntos(db);
            borrarPrimerParticipantePorNombre(db);
            borrarSegundoParticipantePorNombre(db);
        } finally {
            db.close();
        }
    }

    public static void accessDb4o() {
        ObjectContainer db = Db4o.openFile(Db4o
                .newConfiguration(), nombreArchivo);
        try {
            // do something with db4o
        } finally {
            db.close();
        }
    }

    public static void guardarPrimerParticipante(ObjectContainer db) {
        Piloto participante1 = new Piloto("Michael Schumacher", 100);
        db.store(participante1);
        System.out.println("Stored " + participante1);
    }

    public static void guardarSegundoParticipante(ObjectContainer db) {
        Piloto participante2 = new Piloto("Rubens Barrichello", 99);
        db.store(participante2);
        System.out.println("Stored " + participante2);
    }

    public static void consultarTodosParticipantesQBE(ObjectContainer db) {
        Piloto proto = new Piloto(null, 0);
        ObjectSet result = db.queryByExample(proto);
        listarResultados((List<?>) result);
    }

    public static void consultarTodosParticipantes(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Piloto.class);
        listarResultados((List<?>) result);
    }

    public static void conseguirParticipantePorNombre(ObjectContainer db) {
        Piloto proto = new Piloto("Michael Schumacher", 0);
        ObjectSet result = db.queryByExample(proto);
        listarResultados((List<?>) result);
    }

    public static void conseguirParticipantePorPuntos(ObjectContainer db) {
        Piloto proto = new Piloto(null, 100);
        ObjectSet result = db.queryByExample(proto);
        listarResultados((List<?>) result);
    }

    public static void actualizarParticipante(ObjectContainer db) {
        ObjectSet result = db
                .queryByExample(new Piloto("Michael Schumacher", 0));
        Piloto found = (Piloto) result.next();
        found.sumPuntos(11);
        db.store(found);
        System.out.println("Added 11 points for " + found);
        consultarTodosParticipantes(db);
    }

    public static void borrarPrimerParticipantePorNombre(ObjectContainer db) {
        ObjectSet result = db
                .queryByExample(new Piloto("Michael Schumacher", 0));
        Piloto found = (Piloto) result.next();
        db.delete(found);
        System.out.println("Deleted " + found);
        consultarTodosParticipantes(db);
    }

    public static void borrarSegundoParticipantePorNombre(ObjectContainer db) {
        ObjectSet result = db
                .queryByExample(new Piloto("Rubens Barrichello", 0));
        Piloto found = (Piloto) result.next();
        db.delete(found);
        System.out.println("Deleted " + found);
        consultarTodosParticipantes(db);
    }
}