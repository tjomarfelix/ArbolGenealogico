package arbolgenealogico;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author jomar
 */
public class ArbolBinario <T> {
    private NodoArbol<T> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public NodoArbol<T> crearRaiz(T dato) {
        if (raiz != null) {
            throw new IllegalStateException("El arbol ya tiene una raiz.");
        }
        raiz = new NodoArbol<>(dato);
        return raiz;
    }

    public NodoArbol<T> insertarIzquierdo(NodoArbol<T> padre, T dato) {
        if (padre == null) {
            throw new IllegalArgumentException("El padre no puede ser nulo.");
        }
        if (padre.getIzquierdo() != null) {
            throw new IllegalStateException("El nodo ya tiene hijo izquierdo.");
        }
        NodoArbol<T> nuevo = new NodoArbol<>(dato);
        padre.setIzquierdo(nuevo);
        return nuevo;
    }

    public NodoArbol<T> insertarDerecho(NodoArbol<T> padre, T dato) {
        if (padre == null) {
            throw new IllegalArgumentException("El padre no puede ser nulo.");
        }
        if (padre.getDerecho() != null) {
            throw new IllegalStateException("El nodo ya tiene hijo derecho.");
        }
        NodoArbol<T> nuevo = new NodoArbol<>(dato);
        padre.setDerecho(nuevo);
        return nuevo;
    }

    public NodoArbol<T> buscar(Predicate<T> criterio) {
        return buscar(raiz, criterio);
    }

    private NodoArbol<T> buscar(NodoArbol<T> nodo, Predicate<T> criterio) {
        if (nodo == null) return null;
        if (criterio.test(nodo.getDato())) return nodo;

        NodoArbol<T> encontrado = buscar(nodo.getIzquierdo(), criterio);
        if (encontrado != null) return encontrado;

        return buscar(nodo.getDerecho(), criterio);
    }


    public boolean eliminarSubarbol(NodoArbol<T> nodo) {
        if (nodo == null || raiz == null) return false;

        if (nodo == raiz) {
            raiz = null;
            return true;
        }
        return eliminarSubarbol(raiz, nodo);
    }

    private boolean eliminarSubarbol(NodoArbol<T> actual, NodoArbol<T> objetivo) {
        if (actual == null) return false;

        if (actual.getIzquierdo() == objetivo) {
            actual.setIzquierdo(null);
            return true;
        }
        if (actual.getDerecho() == objetivo) {
            actual.setDerecho(null);
            return true;
        }
        return eliminarSubarbol(actual.getIzquierdo(), objetivo)
            || eliminarSubarbol(actual.getDerecho(), objetivo);
    }

    public void vaciar() {
        raiz = null;
    }

    

    public int contar() {
        return contar(raiz);
    }

    private int contar(NodoArbol<T> nodo) {
        if (nodo == null) return 0;
        return 1 + contar(nodo.getIzquierdo()) + contar(nodo.getDerecho());
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoArbol<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    public void preorden(Consumer<T> visitar) {
        preorden(raiz, visitar);
    }

    private void preorden(NodoArbol<T> nodo, Consumer<T> visitar) {
        if (nodo == null) return;
        visitar.accept(nodo.getDato());
        preorden(nodo.getIzquierdo(), visitar);
        preorden(nodo.getDerecho(), visitar);
    }

    public void inorden(Consumer<T> visitar) {
        inorden(raiz, visitar);
    }

    private void inorden(NodoArbol<T> nodo, Consumer<T> visitar) {
        if (nodo == null) return;
        inorden(nodo.getIzquierdo(), visitar);
        visitar.accept(nodo.getDato());
        inorden(nodo.getDerecho(), visitar);
    }

    public void postorden(Consumer<T> visitar) {
        postorden(raiz, visitar);
    }

    private void postorden(NodoArbol<T> nodo, Consumer<T> visitar) {
        if (nodo == null) return;
        postorden(nodo.getIzquierdo(), visitar);
        postorden(nodo.getDerecho(), visitar);
        visitar.accept(nodo.getDato());
    }
}