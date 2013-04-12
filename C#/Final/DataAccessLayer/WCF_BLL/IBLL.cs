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
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IBLL
    {
        [OperationContract]
        string GetData(String value);

        [OperationContract]
        CompositeType GetDataUsingDataContract(CompositeType composite);

        // TODO: Add your service operations here
        #region select all data from a table
        [OperationContract]
        utilisateur[] GetUsers();

        [OperationContract]
        Jeux[] GetGames();

        [OperationContract]
        Category[] GetCategory();

        [OperationContract]
        Vote[] Getvotes();

        [OperationContract]
        HighScore[] GetHighScores();

        [OperationContract]
        admin[] GetAdmins();
        [OperationContract]
        editeur[] GetEditeurs();
        [OperationContract]
        joueur[] GetJoueurs();
        [OperationContract]
        UsersActif[] GetUsersActif();
        [OperationContract]
        UsersNoActif[] GetUsersNoActif();
        #endregion

        #region insert data into a 
        [OperationContract]
        void Insertuser(utilisateur user);

        void Insertgame(int id, string Nom, string Description, string PEGI, int Categorie, int Editeur);
        void InsertHighScore(int idJoueur, int idJeux, int Score);
        void InsertVotes(int idJoueur, int idJeux, int cote);
        [OperationContract]
        void InsertCategory(Category tuple);
        #endregion

        #region update data from table
        /*
        void UpdateFromUsers(String column, string oldValue, String newValue);
        void UpdateFromGames(String column, string oldValue, String newValue);
        void UpdateFromVotes(String column, string oldValue, String newValue);
        void UpdateFromHighScores(String column, string oldValue, String newValue);
         */
        [OperationContract]
        void UpdateUserStatus(int id);
        [OperationContract]
        void UpdateGamesStatus(int id);
        [OperationContract]
        void UpdateFromUsers(utilisateur membres);
        [OperationContract]
        void UpdateFromCategories(int id, String description);
        #endregion

        #region delete data from table
        void DeleteFromUsers(String column, string value);
        void DeleteFromGames(String column, string value, bool cascade);
        void DeleteFromVotes(String column, string value);
        void DeleteFromHighScores(String column, string value);
        [OperationContract]
        int DeleteFromCategories(int id);
        #endregion

        [OperationContract]
        bool UserConnect(string username, string password);

        [OperationContract]
        void CommitData();

        [OperationContract]
        void Autocommit(int status);


    }

    // Use a data contract as illustrated in the sample below to add composite types to service operations
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }
    }
}
