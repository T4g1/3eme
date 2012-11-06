--------------------------------------------------------
--  Fichier créé - lundi-novembre-05-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Trigger REPPLICATION_CC1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "CB"."REPPLICATION_CC1" 
BEFORE INSERT ON COPIE for each row

DECLARE
film FILMS%ROWTYPE;
errmsg VARCHAR2(200);
nbCopie NUMBER;

BEGIN

    if( :NEW.type = 'physique') THEN
      ----------------------------------------------------------------------------
      --Récup de l'id film
      select * into film
      FROM FILMS
      WHERE idfilm = :NEW.idfilm;
      
      insert into Log@DB_CC1.ORACLE values('--ajout du film : ' || :NEW.idfilm , 'Trigger');
      ----------------------------------------------------------------------------
      --Ajout du producteur dans la replication
      BEGIN
      insert into personnes@DB_CC1.ORACLE SELECT * 
                                          FROM PERSONNES 
                                          WHERE idpersonne = film.producteur
                                          AND personnes.idpersonne NOT IN (SELECT pers.idpersonne
                                                                       FROM personnes@DB_CC1.ORACLE pers);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      
      END;
      ----------------------------------------------------------------------------
      --Ajout de la certification dans la replication
      BEGIN
      insert into certifications@DB_CC1.ORACLE SELECT * 
                                               FROM CERTIFICATIONS 
                                               WHERE idcertif = film.certification
                                               AND certifications.idcertif NOT IN (SELECT cert.idcertif
                                                                                   FROM certifications@DB_CC1.ORACLE cert);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout du film
      BEGIN
      insert into FILMS@DB_CC1.ORACLE SELECT * 
                                      FROM FILMS 
                                      WHERE idfilm = :NEW.idfilm
                                      AND FILMS.idfilm NOT IN (SELECT fi.idfilm
                                                               FROM FILMS@DB_CC1.ORACLE fi);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout des acteurs
      BEGIN
      insert into personnes@DB_CC1.ORACLE SELECT personnes.* 
                                          FROM personnes, jouedans
                                          WHERE personnes.idpersonne = jouedans.idpersonne
                                          and jouedans.idfilm = :NEW.idfilm
                                          AND personnes.idpersonne NOT IN (SELECT pers.idpersonne
                                                                           FROM personnes@DB_CC1.ORACLE pers);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout des lien d'acteurs
      BEGIN
      insert into jouedans@DB_CC1.ORACLE SELECT * 
                                          FROM jouedans
                                          WHERE jouedans.idfilm = :NEW.idfilm;
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout des studios
      BEGIN
      insert into studio@DB_CC1.ORACLE SELECT studio.* 
                                          FROM studio, estproduit
                                          WHERE studio.idstudio = estproduit.idstudio
                                          and estproduit.idfilm = :NEW.idfilm
                                          AND studio.idstudio NOT IN (SELECT stud.idstudio
                                                                       FROM studio@DB_CC1.ORACLE stud);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout des lien de studio
      insert into estproduit@DB_CC1.ORACLE SELECT * 
                                           FROM estproduit
                                           WHERE estproduit.idfilm = :NEW.idfilm;
       ---------------------------------------------------------------------------
      --Ajout des genres
      BEGIN
     
      insert into genres@DB_CC1.ORACLE SELECT genres.* 
                                          FROM genres, estdegenre
                                          WHERE genres.idgenre = estdegenre.idgenre
                                          and estdegenre.idfilm = :NEW.idfilm
                                          AND genres.idgenre NOT IN (SELECT gen.idgenre
                                                                       FROM genres@DB_CC1.ORACLE gen);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;              
      END;
      ----------------------------------------------------------------------------
      --Ajout des estdegenre
      BEGIN
      insert into estdegenre@DB_CC1.ORACLE SELECT * 
                                           FROM estdegenre
                                           WHERE estdegenre.idfilm = :NEW.idfilm;
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout des langages
      BEGIN
      insert into langages@DB_CC1.ORACLE SELECT langages.* 
                                         FROM langages, esten
                                         WHERE langages.idlangue = esten.idlangue
                                         AND esten.idfilm = :NEW.idfilm
                                         AND langages.idlangue NOT IN (SELECT lang.idlangue
                                                                       FROM langages@DB_CC1.ORACLE lang);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN raise_application_error(-20000, 'Erreur: Langages double PK');
        WHEN OTHERS THEN raise_application_error(-20000, 'Erreur: Autres erreur langues') ;
      END;
      ----------------------------------------------------------------------------
      --Ajout des esten
      BEGIN
      insert into esten@DB_CC1.ORACLE SELECT * 
                                      FROM esten
                                      WHERE esten.idfilm = :NEW.idfilm; 
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN raise_application_error(-20000, 'Erreur: lien Langages double PK');
        WHEN OTHERS THEN raise_application_error(-20000, 'Erreur: Autres erreur lien langues') ;
      END;
      ----------------------------------------------------------------------------
      --Ajout des pays
      BEGIN
      insert into pays@DB_CC1.ORACLE SELECT pays.* 
                                     FROM pays, participe
                                     WHERE pays.idpays = participe.idpays
                                     and participe.idfilm = :NEW.idfilm
                                     AND pays.idpays NOT IN (SELECT pay.idpays
                                                             FROM pays@DB_CC1.ORACLE pay);
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      ----------------------------------------------------------------------------
      --Ajout des participe
      BEGIN
      insert into participe@DB_CC1.ORACLE SELECT * 
                                          FROM participe
                                          WHERE participe.idfilm = :NEW.idfilm;
      EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN NULL;
      END;
      
    end if;
    ----------------------------------------------------------------------------
    --Ajout des copies
    nbCopie := :NEW.Quantite;
    select trunc(dbms_random.value(0, (:NEW.Quantite/2))) into nbCopie from dual;
    
    /*MAJ de la quantité*/
    :NEW.Quantite := :NEW.Quantite - nbCopie;
        
    insert into copie@DB_CC1.ORACLE values(:NEW.idfilm, :NEW.Type, nbCopie);
    
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN NULL;
        WHEN OTHERS THEN RAISE;
END;
/
ALTER TRIGGER "CB"."REPPLICATION_CC1" ENABLE;
