using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using DataAccessLayer;
using System.Drawing;

namespace WCF_BLL
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in both code and config file together.
    public class BLL : IBLL
    {
        private mainDal md = new mainDal();

        public string GetData(String value)
        {
            return string.Format("You entered: {0}", value);
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }


        //Personnal implementations

        #region selection de toute une table
        public utilisateur[] GetUsers()
        {
            utilisateur[] users = md.SelectAllUsers();

            return users;
        }

        public Jeux[] GetGames()
        {
            return md.SelectAllGames();
        }

        public Category[] GetCategory()
        {
            return md.SelectAllCategories();
        }

        public Vote[] Getvotes()
        {
            return md.SelectAllVotes();
        }

        public HighScore[] GetHighScores()
        {
            return md.SelectAllHighScores();
        }

        public admin[] GetAdmins()
        {
            return md.SelectAllAdmin();
        }

        public editeur[] GetEditeurs()
        {
            return md.SelectAllediteurs();
        }

        public joueur[] GetJoueurs()
        {
            return md.SelectAlljoueurs();
        }

        public UsersActif[] GetUsersActif()
        {
            return md.SelectAllusersactif();
        }

        public UsersNoActif[] GetUsersNoActif()
        {
            return md.SelectAllusernoactif();
        }
        #endregion

        #region insert data into a table
        public void Insertuser(int id, string Nom, string prenom, string pseudo, string password, Image avatar, int Droit, bool Status)
        {
            md.InsertIntoUtilisateurs(id, Nom, prenom, pseudo, password, avatar, Droit, Status);
        }
        public void Insertgame(int id, string Nom, string Description, string PEGI, int Categorie, int Editeur)
        {
            md.InsertIntoJeux(id, Nom, Description, PEGI, Categorie, Editeur);
        }
        public void InsertHighScore(int idJoueur, int idJeux, int Score)
        {
            md.InsertIntoHighScores(idJoueur, idJeux, Score);
        }
        public void InsertVotes(int idJoueur, int idJeux, int cote)
        {
            md.InsertIntoVotes(idJoueur, idJeux, cote);
        }
        public void InsertCategory(Category tuple)
        {
            md.InsertIntoCategories(tuple);
        }
        #endregion

        #region update data from table
        public void UpdateUserStatus(int id)
        {
            md.ChangeUserStatus(id);
        }
        public void UpdateGamesStatus(int id)
        {
            md.ChangeGameStatus(id);
        }
        public void UpdateFromUsers(utilisateur membres) {
            md.UpdateFromUsers(membres);
        }
        public void UpdateFromCategories(int id, string description)
        {
            md.UpdateFromCategories(id, description);
        }
        #endregion

        #region delete data from table
        public void DeleteFromUsers(string column, string value)
        {
            md.DeleteFromUsers(column, value);
        }
        public void DeleteFromGames(string column, string value, bool cascade)
        {
            md.DeleteFromGames(column, value, cascade);
        }
        public void DeleteFromVotes(string column, string value)
        {
            md.DeleteFromVotes(column, value);
        }
        public void DeleteFromHighScores(string column, string value)
        {
            md.DeleteFromHighScores(column, value);
        }
        public int DeleteFromCategories(int id)
        {
            return md.DeleteFromCategories(id);
        }
        #endregion

        public void CommitData()
        {
            md.commit();
        }
        public void Autocommit(int status)
        {
            if( status == 1)
                md.AutoCommit = true;
            else
            {
                md.AutoCommit = false;
            }

        }

    }

}
