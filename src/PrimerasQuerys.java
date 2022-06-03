import com.db4o.*;
import com.db4o.query.Query;

import java.util.List;


public class PrimerasQuerys extends Funciones {

    final static String nombreArchivo = System.getProperty("user.home") + "/formula1.db4o4";

    public static void main(String[] args) {
        ObjectContainer db=Db4o.openFile(Db4o
                .newConfiguration(), nombreArchivo);
        try {
            guardarPrimerPiloto(db);
            guardarSegundoPiloto(db);
            guardarTercerPiloto(db);
            obtenerPilotoPorNombre(db);
            obtenerPilotoPorPuntos(db);
            pilotosOrdenados(db);
            limpiarBBDD(db);
        }
        finally {
            db.close();
        }
    }

    public static void guardarPrimerPiloto(ObjectContainer db) {
        Piloto piloto1 =new Piloto("Michael Schumacher",100);
        db.store(piloto1);
        System.out.println("Stored "+ piloto1);
    }

    public static void guardarSegundoPiloto(ObjectContainer db) {
        Piloto piloto2 =new Piloto("Rubens Barrichello",99);
        db.store(piloto2);
        System.out.println("Stored "+ piloto2);
    }

    public static void guardarTercerPiloto(ObjectContainer db) {
        Query query=db.query();
        query.constrain(Piloto.class);
        ObjectSet result=query.execute();
        listarResultados((List<?>) result);
    }

    public static void obtenerPilotoPorNombre(ObjectContainer db) {
        Query query=db.query();
        query.constrain(Piloto.class);
        query.descend("name").constrain("Michael Schumacher");
        ObjectSet result=query.execute();
        listarResultados((List<?>) result);
    }

    public static void obtenerPilotoPorPuntos(
            ObjectContainer db) {
        Query query=db.query();
        query.constrain(Piloto.class);
        query.descend("points").constrain(100);
        ObjectSet result=query.execute();
        listarResultados((List<?>) result);
    }


    public static void pilotosOrdenados(ObjectContainer db) {
        Query query=db.query();
        query.constrain(Piloto.class);
        query.descend("name").orderAscending();
        ObjectSet result=query.execute();
        listarResultados((List<?>) result);
        query.descend("name").orderDescending();
        result=query.execute();
        listarResultados((List<?>) result);
    }

    public static void limpiarBBDD(ObjectContainer db) {
        ObjectSet result=db.queryByExample(Piloto.class);
        while(result.hasNext()) {
            db.delete(result.next());
        }
    }
}