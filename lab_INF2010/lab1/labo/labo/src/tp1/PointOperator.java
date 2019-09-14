package tp1;

import java.util.*;

public final class PointOperator {
    // TODO appliquer la translation sur le vecteur d'entree.
    public static Double[] translate(Double[] vector, Double[] translateVector) {
        var nouveauVector = new Double[vector.length];
        if (vector.length == translateVector.length){
            for (var dim = 0; dim < vector.length; dim++){
                nouveauVector[dim] = vector[dim];
                nouveauVector[dim] += translateVector[dim];
            }
        }
        return nouveauVector;
    }

    // TODO appliquer la rotation sur le vecteur d'entree.
    public static Double[] rotate(Double[] vector, Double[][] rotationMatrix) {
        var nouveauVector = new Double[vector.length];
        if (vector.length == rotationMatrix.length && vector.length == rotationMatrix[0].length){
            for (var ligne = 0; ligne < rotationMatrix.length; ligne++){
                nouveauVector[ligne] = 0.0;
                for (var colonne = 0; colonne < rotationMatrix[ligne].length; colonne++){
                    nouveauVector[ligne] += rotationMatrix[ligne][colonne] * vector[colonne];
                }
            }
        }
        return nouveauVector;
    }

    // TODO appliquer le facteur de division sur le vecteur d'entree.
    public static Double[] divide(Double[] vector, Double divider) {
        var nouveauVector = new Double[vector.length];
        for (var dim = 0; dim < vector.length; dim++){
            nouveauVector[dim] = vector[dim];
            nouveauVector[dim] /= divider;
        }
        return nouveauVector;
    }

    // TODO appliquer le facteur de multiplication sur le vecteur d'entree.
    public static Double[] multiply(Double[] vector, Double multiplier) {
        var nouveauVector = new Double[vector.length];
        for (var dim = 0; dim < vector.length; dim++){
            nouveauVector[dim] = vector[dim];
            nouveauVector[dim] *= multiplier;
        }
        return nouveauVector;
    }

    // TODO appliquer le facteur d'addition sur le vecteur d'entree.
    public static Double[] add(Double[] vector, Double adder) {
        var nouveauVector = new Double[vector.length];
        for (var dim = 0; dim < vector.length; dim++){
            nouveauVector[dim] = vector[dim];
            nouveauVector[dim] += adder;
        }
        return nouveauVector;
    }

    // TODO retourne la coordonnee avec les plus grandes valeurs en X et en Y.
    public static Point2d getMaxCoord(Collection<Point2d> coords) {
        // en assumant que le point minimal est (-infini, -infini)
        var ptMax = new Point2d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (var coord : coords) {
            for (var i = 0; i < coord.vector.length; i++){
                if (coord.vector[i] > ptMax.vector[i]){
                    ptMax.vector[i] = coord.vector[i];
                }
            }
        }
        return ptMax;
    }

    // TODO retourne la coordonnee avec les plus petites valeurs en X et en Y.
    public static Point2d getMinCoord(Collection<Point2d> coords) {
        // en assumant que le point maximal est (infini, infini)
        var ptMin = new Point2d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        for (var coord : coords) {
            for (var i = 0; i < coord.vector.length; i++){
                if (coord.vector[i] < ptMin.vector[i]){
                    ptMin.vector[i] = coord.vector[i];
                }
            }
        }
        return ptMin;
    }
}
