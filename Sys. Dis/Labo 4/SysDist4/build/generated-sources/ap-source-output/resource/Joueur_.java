package resource;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resource.Echiquier;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-12-18T23:14:32")
@StaticMetamodel(Joueur.class)
public class Joueur_ { 

    public static volatile SingularAttribute<Joueur, Long> id;
    public static volatile CollectionAttribute<Joueur, Echiquier> echiquierCollection2;
    public static volatile CollectionAttribute<Joueur, Echiquier> echiquierCollection1;
    public static volatile CollectionAttribute<Joueur, Echiquier> echiquierCollection;
    public static volatile SingularAttribute<Joueur, String> pseudo;

}