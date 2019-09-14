package tp1;

public class Point2d extends AbstractPoint {
    // La plupart des methodes utilisent X et Y au lieu d'une boucle
    // qui itère sur les deux éléments du vector.
    private final Integer X = 0;
    private final Integer Y = 1;

    // TODO creer un point en 2d avec 2 donnees
    public Point2d(Double x, Double y) {
        super(new Double[]{x,y});
        //vector[X] = x;
        //vector[Y] = y;
    }

    // TODO creer un point a partir d'un vecteur de donnees
    public Point2d(Double[] vector) {
        super(new Double[]{vector[0], vector[1]});
        //this.vector = vector;
    }

    public Double X() { return vector[X];}
    public Double Y() { return vector[Y];}

    // TODO prendre un vecteur de donnees et appliquer la translation.
    @Override
    public Point2d translate(Double[] translateVector) {
        var nouveauPoint2d = new Point2d(vector);
        nouveauPoint2d.vector[X] += translateVector[X];
        nouveauPoint2d.vector[Y] += translateVector[Y];
        return nouveauPoint2d;
    }

    // TODO prendre un point et appliquer la translation.
    public Point2d translate(Point2d translateVector) {
        var nouveauPoint2d = new Point2d(vector);
        nouveauPoint2d.vector[X] += translateVector.X();
        nouveauPoint2d.vector[Y] += translateVector.Y();
        return nouveauPoint2d;
    }

    // TODO prendre un vecteur de donnees et appliquer la rotation.
    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        var nouveauPoint2d = new Point2d(vector);
        if (nouveauPoint2d.vector.length == rotationMatrix.length && nouveauPoint2d.vector.length == rotationMatrix[0].length){
            var ancienX = nouveauPoint2d.vector[X];
            var ancienY = nouveauPoint2d.vector[Y];
            nouveauPoint2d.vector[X] = (ancienX * rotationMatrix[0][0]) + (ancienY * rotationMatrix[0][1]);
            nouveauPoint2d.vector[Y] = (ancienX * rotationMatrix[1][0]) + (ancienY * rotationMatrix[1][1]);
        }
        return nouveauPoint2d;
    }

    // TODO prendre un angle de rotation, creer une matrice et appliquer la rotation.
    public Point2d rotate(Double angle) {
        var nouveauPoint2d = new Point2d(vector);
        var ancienX = nouveauPoint2d.vector[X];
        var ancienY = nouveauPoint2d.vector[Y];
        nouveauPoint2d.vector[X] = (ancienX * Math.cos(angle)) - (ancienY * Math.sin(angle));
        nouveauPoint2d.vector[Y] = (ancienX * Math.sin(angle)) + (ancienY * Math.cos(angle));
        return nouveauPoint2d;
    }

    // TODO prendre un facteur de division et l'appliquer.
    @Override
    public Point2d divide(Double divider) {
        var nouveauPoint2d = new Point2d(vector);
        nouveauPoint2d.vector[X] /= divider;
        nouveauPoint2d.vector[Y] /= divider;
        return nouveauPoint2d;
    }

    // TODO prendre un facteur de multiplication et l'appliquer.
    @Override
    public Point2d multiply(Double multiplier) {
        var nouveauPoint2d = new Point2d(vector);
        nouveauPoint2d.vector[X] *= multiplier;
        nouveauPoint2d.vector[Y] *= multiplier;
        return nouveauPoint2d;
    }

    // TODO prendre un facteur d'addition et l'appliquer.
    @Override
    public Point2d add(Double adder) {
        var nouveauPoint2d = new Point2d(vector);
        nouveauPoint2d.vector[X] += adder;
        nouveauPoint2d.vector[Y] += adder;
        return nouveauPoint2d;
    }

    // TODO creer un nouveau point.
    @Override
    public Point2d clone() {
        return new Point2d(vector[X], vector[Y]);
    }
}
