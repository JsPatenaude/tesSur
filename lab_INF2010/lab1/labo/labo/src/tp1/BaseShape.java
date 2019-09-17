package tp1;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape {
    // Vous aller apprendre plus en details le fonctionnement d'un Set lors
    // du cours sur les fonctions de hashage, vous pouvez considerez ceci comme une liste normale.
    private Set<Point2d> coords;

    // TODO Initialiser les points.
    public BaseShape() {
        coords = new HashSet<>();
    }

    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
        this.coords = new HashSet<>(coords);
    }

    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public void add(Point2d coord) {
        coords.add(coord);
    }
    public void add(BaseShape shape) {
        coords.addAll(shape.coords);
    }
    public void addAll(Collection<Point2d> coords) {
        this.coords.addAll(coords);
    }
    public void remove(Point2d coord) {
        coords.remove(coord);
    }
    public void remove(BaseShape shape) {
        coords.removeAll(shape.coords);
    }
    public void removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
    }

    // TODO retourne les coordonnees de la liste.
    public Set<Point2d> getCoords() {
        return new HashSet<>(coords);
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        var baseShape = new BaseShape(coords);
        baseShape.coords = baseShape.translateAll(point);
        return baseShape;
    }

    // TODO appliquer la translation sur la liste.
    public Set<Point2d> translateAll(Point2d point) {
        var liste = new HashSet<Point2d>();
        for (var coord : coords) {
            liste.add(coord.translate(point));
        }
        return liste;
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
        var baseShape = new BaseShape(coords);
        baseShape.coords = baseShape.rotateAll(angle);
        return baseShape;
    }

    // TODO appliquer la rotation sur la liste.
    public Set<Point2d> rotateAll(Double angle) {
        var liste = new HashSet<Point2d>();
        for (var coord : coords) {
            liste.add(coord.rotate(angle));
        }
        return liste;
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return new BaseShape(coords);
    }
}
