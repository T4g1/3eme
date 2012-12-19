using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Collections;
using System.Drawing;
using System.IO;

namespace DataAccessLayer
{
    public class mainDal
    {
        /*Membres*/
        private DbGamesDataContext DB;
        private bool autoCommit = false;

        public mainDal(){
            //Initialisation du dataset.
            DB = new DbGamesDataContext();
        }


        //TODO: ici aussi trouver une fucking solutions
        #region insert into
        public void InsertIntoUtilisateurs(int id, string Nom, string prenom, string pseudo, string password, Image avatar, int Droit, bool Status) { 
            MemoryStream ms = new MemoryStream();
            utilisateur user = new utilisateur();

            if( avatar != null)
                avatar.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);
            //avatar.Value.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);
            //création du tuple
            user.id = id;
            user.Nom = Nom;
            user.Prenom = prenom;
            user.Pseudo = pseudo;
            user.Password = password;
            user.Avatar = ms.ToArray();
            user.Droit = Droit;
            user.Status = Status;

            //ajout à la base si un username et password est spécifié
            if(pseudo != null && password != null ){
                DB.utilisateurs.InsertOnSubmit(user);
                if( AutoCommit )
                    DB.SubmitChanges();
            }
        }
        public void InsertIntoJeux(int id, string Nom, string Description, string PEGI, int Categorie, int Editeur) {
            Jeux newGame = new Jeux();
            //création du nouveau tuple
            newGame.id = id;
            newGame.Nom = Nom;
            newGame.Description = Description;
            newGame.PEGI = PEGI;
            newGame.Categorie = Categorie;
            newGame.Editeur = Editeur;

            if(Categorie.Equals("") && Editeur.Equals(""))
                DB.Jeuxes.InsertOnSubmit(newGame);
            if (AutoCommit)
                DB.SubmitChanges();
        }
        public void InsertIntoHighScores(int idJoueur, int idJeux, int Score) {
            HighScore hs = new HighScore();
            hs.idJoueur = idJoueur;
            hs.idJeux = idJeux;
            hs.Score = Score;

            DB.HighScores.InsertOnSubmit(hs);
            if (AutoCommit)
                DB.SubmitChanges();
        }
        public void InsertIntoVotes(int idJoueur, int idJeux, int cote) {
            Vote vote = new Vote();
            vote.idjoueur = idJoueur;
            vote.idjeu = idJeux;
            vote.cote = cote;

            DB.Votes.InsertOnSubmit(vote);
            if (AutoCommit)
                DB.SubmitChanges();
        }
        public void InsertIntoCategories(Category tuple) {
            DB.Categories.InsertOnSubmit(tuple);
            if (AutoCommit)
                DB.SubmitChanges();
        }
        #endregion

        #region get all table
        /*Permet de selectionner l ensemble des tuples d'une table
         * 
         */ 
        public utilisateur[] SelectAllUsers(){
            return DB.utilisateurs.ToArray();
            //return (from users in DB.utilisateurs
             //       select users).ToArray(); 
        }
        public Jeux[] SelectAllGames(){
            return DB.Jeuxes.ToArray();
        }
        public Vote[] SelectAllVotes(){
            return (from votes in DB.Votes
                    select votes).ToArray();
        }
        public Category[] SelectAllCategories(){
            return (from category in DB.Categories
                    select category).ToArray();
        }
        public HighScore[] SelectAllHighScores() {
            return (from scores in DB.HighScores
                    select scores).ToArray();
        }
        public admin[] SelectAllAdmin() {
            return DB.admins.ToArray();
        }
        public editeur[] SelectAllediteurs()
        {
            return DB.editeurs.ToArray();
        }
        public joueur[] SelectAlljoueurs()
        {
            return DB.joueurs.ToArray();
        }
        public UsersActif[] SelectAllusersactif()
        {
            return DB.UsersActifs.ToArray();
        }
        public UsersNoActif[] SelectAllusernoactif()
        {
            return DB.UsersNoActifs.ToArray();
        }
        #endregion

        #region Suppression dans une table

        /*Permet la suppression dans l'une des tables fournies en paramètre
         *column: string indiquant la colonne sur laquelle effectue la recherche
         *value: valeur permettant d'affiner la recherche au tuple que l'on souhaite supprimer
         *cascade: permet d'aller supprimer dans les autres table pour éviter une error sur la FK
         */
        public void DeleteFromUsers(String column, string value) {
            IQueryable<utilisateur> result = null;

            switch (column) { 
                case "id":
                    result = (from users in DB.utilisateurs
                              where users.id == int.Parse(value)
                              select users);
                    break;
                case "Nom":
                    result = (from users in DB.utilisateurs
                              where users.Nom == value
                              select users);
                    break;
                case "Prenom":
                    result = (from users in DB.utilisateurs
                              where users.Prenom == value
                              select users);
                    break;
                case "Pseudo":
                    result = (from users in DB.utilisateurs
                              where users.Pseudo == value
                              select users);
                    break;
                case "Droit":
                    result = (from users in DB.utilisateurs
                              where users.Droit == int.Parse(value)
                              select users);
                    break;
                case "statut":
                    if (value == "true" || value == "False")
                    {
                        result = from users in DB.utilisateurs
                                  where users.Status == bool.Parse(value)
                                  select users;
                    }
                    break;
            }
            if (result != null)
            {
                DB.utilisateurs.DeleteAllOnSubmit<utilisateur>(result);
                if (AutoCommit)
                    DB.SubmitChanges();
            }

        }
        public void DeleteFromGames(String column, string value, bool cascade)
        {
            IQueryable<Jeux> result = null;

            switch (column)
            {
                case "id":
                    result = (from users in DB.Jeuxes
                              where users.id == int.Parse(value)
                              select users);
                    break;
                case "Nom":
                    result = (from users in DB.Jeuxes
                              where users.Nom == value
                              select users);
                    break;
                case "PEGI":
                    result = (from users in DB.Jeuxes
                              where users.PEGI == value
                              select users);
                    break;
                case "Categorie":
                    result = (from users in DB.Jeuxes
                              where users.Categorie == int.Parse(value)
                              select users);
                    break;
                case "Editeur":
                    result = (from users in DB.Jeuxes
                              where users.Editeur == int.Parse(value)
                              select users);
                    break;
            }
            if (result != null)
            {
                DB.Jeuxes.DeleteAllOnSubmit<Jeux>(result);
                if (AutoCommit)
                    DB.SubmitChanges();
            }

        }
        public void DeleteFromVotes(String column, string value)
        {
            IQueryable<Vote> result = null;

            switch (column)
            {
                case "idJoueur":
                    result = (from users in DB.Votes
                              where users.idjoueur == int.Parse(value)
                              select users);
                    break;
                case "idjeu":
                    result = (from users in DB.Votes
                              where users.idjeu == int.Parse(value)
                              select users);
                    break;
                case "cote":
                    result = (from users in DB.Votes
                              where users.cote == int.Parse(value)
                              select users);
                    break;
            }
            if (result != null)
            {
                DB.Votes.DeleteAllOnSubmit<Vote>(result);
                if (AutoCommit)
                    DB.SubmitChanges();
            }

        }
        public void DeleteFromHighScores(String column, string value)
        {
            IQueryable<HighScore> result = null;

            switch (column)
            {
                case "idJoueur":
                    result = (from users in DB.HighScores
                              where users.idJoueur == int.Parse(value)
                              select users);
                    break;
                case "idjeu":
                    result = (from users in DB.HighScores
                              where users.idJeux == int.Parse(value)
                              select users);
                    break;
                case "Score":
                    result = (from users in DB.HighScores
                              where users.Score == int.Parse(value)
                              select users);
                    break;
            }

            if(result != null){
                DB.HighScores.DeleteAllOnSubmit<HighScore>(result);
                if (AutoCommit)
                    DB.SubmitChanges();
            }

        }
        public int DeleteFromCategories(int id)
        {
            IQueryable<Category> result = null;
            //recupère les jeux possédant la categorie à supprimer 
            IQueryable<Jeux> existInGame = from jeux in DB.Jeuxes
                                            where jeux.Categorie == id
                                            select jeux;

            if (existInGame.Count() == 0)
            {
                result = (from category in DB.Categories
                          where category.id.Equals(id)
                          select category);

                if (result != null)
                {
                    DB.Categories.DeleteAllOnSubmit<Category>(result);
                    if (AutoCommit)
                        DB.SubmitChanges();
                }
            }
            else {
                return -1;
            }

            return 0;

        }
        #endregion


        //TODO: refaire tout et trouver des solution à ce truc de merde !!!
        #region update dans une table

        /*Permet la modification dans l'une des tables fournies en paramètre
         *column: string indiquant la colonne sur laquelle effectue la recherche
         *oldValue: valeur permettant d'affiner la recherche au tuple que l'on souhaite supprimer
         *newValue: remplacera l'ancienne valeur pour la colonne indiqué
         */
        public void ChangeUserStatus(int id)
        {
            IQueryable<utilisateur> result = null;

            result = (from users in DB.utilisateurs
                        where users.id.Equals(id)
                        select users);

            foreach (utilisateur tuple in result)
            {
                if (tuple.Status)
                    tuple.Status = false;
                else {
                    tuple.Status = true;
                }
            }       
            if (AutoCommit)
                DB.SubmitChanges();

        }
        public void ChangeGameStatus(int id)
        {
            IQueryable<Jeux> result = null;

            result = (from games in DB.Jeuxes
                      where games.id.Equals(id)
                      select games);

            foreach (Jeux tuple in result)
            {
                if ((bool)tuple.Status)
                    tuple.Status = false;
                else
                {
                    tuple.Status = true;
                }
            }
            if (AutoCommit)
                DB.SubmitChanges();

        }

        public void UpdateFromGames(String column, string oldValue, String newValue)
        {
            IQueryable<Jeux> result = null;

            switch (column)
            {
                case "id":
                    result = (from users in DB.Jeuxes
                              where users.id == int.Parse(oldValue)
                              select users);
                    break;
                case "Nom":
                    result = (from users in DB.Jeuxes
                              where users.Nom == oldValue
                              select users);
                    break;
                case "PEGI":
                    result = (from users in DB.Jeuxes
                              where users.PEGI == oldValue
                              select users);
                    break;
                case "Categorie":
                    result = (from users in DB.Jeuxes
                              where users.Categorie == int.Parse(oldValue)
                              select users);
                    break;
                case "Editeur":
                    result = (from users in DB.Jeuxes
                              where users.Editeur == int.Parse(oldValue)
                              select users);
                    break;
            }

            foreach (Jeux tuple in result)
            {
                switch(column){
                    case "id":
                        tuple.id = int.Parse(newValue);
                        break;
                    case "Nom":
                        tuple.Nom = newValue;
                        break;
                    case "PEGI":
                        tuple.PEGI = newValue;
                        break;
                    case "Categorie":
                        tuple.Categorie = int.Parse(newValue);
                        break;
                    case "Editeur":
                        tuple.Editeur = int.Parse(newValue);
                        break;
                }
            }
            if (AutoCommit)
                DB.SubmitChanges();
        }


        public void UpdateFromUsers(utilisateur membres)
        {
            IQueryable<utilisateur> result = null;

            result = (from users in DB.utilisateurs
                      where users.id.Equals(membres.id)
                      select users);

            foreach (utilisateur tuple in result)
            {
                tuple.id = membres.id;
                tuple.Nom = membres.Nom;
                tuple.Prenom = membres.Prenom;
                tuple.Password = membres.Password;
                tuple.Avatar = membres.Avatar;
                tuple.Droit = membres.Droit;
                tuple.Status = membres.Status;
            }
            if (AutoCommit)
                DB.SubmitChanges();
        }


        public void UpdateFromCategories(int id, string newValue)
        {
            IQueryable<Category> result = null;

            result = (from users in DB.Categories
                        where users.id.Equals(id)
                        select users);

            foreach(Category tuple in result)
            {
                tuple.Description = newValue;
            }       
            if (AutoCommit)
                DB.SubmitChanges();
        }
        #endregion


        public void commit() {
            DB.SubmitChanges();
        }

        public bool AutoCommit
        {
            get { return autoCommit; }
            set { autoCommit = value; }
        }

    }
}
