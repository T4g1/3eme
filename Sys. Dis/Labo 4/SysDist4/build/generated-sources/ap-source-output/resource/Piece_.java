package resource;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resource.Echiquier;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-18T23:14:32")
@StaticMetamodel(Piece.class)
public class Piece_ { 

    public static volatile SingularAttribute<Piece, Long> id;
    public static volatile SingularAttribute<Piece, byte[]> color;
    public static volatile CollectionAttribute<Piece, Echiquier> echiquierCollection;
    public static volatile SingularAttribute<Piece, String> dtype;
    public static volatile SingularAttribute<Piece, Integer> y;
    public static volatile SingularAttribute<Piece, Integer> x;

}