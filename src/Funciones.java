import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.List;


public class Funciones {

    public static void listarResultados(List<?> resultados){
        System.out.println(resultados.size());
        for (Object o : resultados) {
            System.out.println(o);
        }
    }

    public static void listarResultadosActualizados(ObjectContainer container, ObjectSet resultado, int depth) {
        System.out.println(resultado.size());
        while(resultado.hasNext()) {
            Object obj = resultado.next();
            container.ext().refresh(obj, depth);
            System.out.println(obj);
        }
    }

    public static void selectAll(ObjectContainer db){
        ObjectSet resultado=db.queryByExample(new Object());
        listarResultados((List<?>) resultado);
    }

    public static void deleteAll(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Object());
        while(result.hasNext()) {
            db.delete(result.next());
        }
    }
}