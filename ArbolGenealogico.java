package arbolgenealogico;

/**
 *
 * @author jomar
 */
public class ArbolGenealogico {

    public static void main(String[] args) {
        ArbolBinario<Familiar> arbol = new ArbolBinario<>();
        
        
        NodoArbol<Familiar> Juanito = arbol.crearRaiz(
            new Familiar("Juanito ", 19,"Estudiante", false, "Navojoa, Sonora")
        );
        
        //Padres 
        NodoArbol<Familiar> Alberto = arbol.insertarIzquierdo(Juanito,
            new Familiar ("Alberto ", 45, "Maestro",false, "Navojoa, Sonora")
        );
        NodoArbol <Familiar> Olivia = arbol.insertarDerecho(Juanito,
            new Familiar ("Olivia ", 41, "Ama de casa", false, "Navojoa, Sonora")
        );
        
        //abuelos oaternos
        arbol.insertarIzquierdo(Alberto,
               new Familiar ("Juan ", 90,"Ganadero",true, "Navojoa, Sonora"));
        arbol.insertarDerecho(Alberto,
               new Familiar ("Margarita ", 84, "Retirada",false, "Navojoa, Sonora"));
        
        //abuelos maternos
        arbol.insertarIzquierdo(Olivia,
               new Familiar ("Juan Manuel ", 67,"Retirado",false, "Navojoa, Sonora"));
        arbol.insertarDerecho(Olivia,
               new Familiar ("Luz ", 64, "Retirada",false, "Navojoa, Sonora"));
        
        System.out.println("//Arbol Genealogico//");
        System.out.println("Total de Familiares: " + arbol.contar());
        System.out.println("Generaciones: " + arbol.altura());
        
        System.out.println("");
        System.out.println("--Recorrido en preorden---");
        arbol.preorden (f -> System.out.println(f));
        
        System.out.println();
        System.out.println("--- Recorrido en postorden ---");
        arbol.postorden(f -> System.out.println(f));
        
        System.out.println();
        System.out.println("--- Recorrido en inorden---");
        arbol.inorden(f -> System.out.println(f));
        
        System.out.println();
        NodoArbol<Familiar> buscado = arbol.buscar(f -> f.getNombre().equals("Olivia"));
        System.out.println("Busqueda de 'Olivia': "
                + (buscado != null ? buscado.getDato() : "Encontrada"));
        
        
        
            
    }
    
}
