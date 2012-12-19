package resource;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resource.Joueur;
import resource.Piece;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-18T23:14:32")
@StaticMetamodel(Echiquier.class)
public class Echiquier_ { 

    public static volatile SingularAttribute<Echiquier, Long> id;
    public static volatile SingularAttribute<Echiquier, Joueur> jnoirId;
    public static volatile SingularAttribute<Echiquier, Boolean> gameover;
    public static volatile CollectionAttribute<Echiquier, Piece> pieceCollection;
    public static volatile SingularAttribute<Echiquier, Joueur> jblancId;
    public static volatile SingularAttribute<Echiquier, Joueur> gagnantId;
    public static volatile SingularAttribute<Echiquier, Integer> focusedplayer;
    public static volatile SingularAttribute<Echiquier, String> nom;

}