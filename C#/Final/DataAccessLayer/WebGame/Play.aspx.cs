using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Configuration;

namespace CowGame
{
    public partial class Play : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            int idJoueur = SiteMaster.getUserId();
            int idJeu = Convert.ToInt32(Request.QueryString["Id"]);

            // Récupére les informations du jeu
            SqlConnection sqlcon = new SqlConnection(ConfigurationManager.ConnectionStrings["FinalGameDBConnectionString"].ConnectionString);
            sqlcon.Open();

            string strQuery = "SELECT Jeux.Nom FROM Jeux WHERE id = '" + idJeu + "'";
            SqlCommand sqlcmd = new SqlCommand(strQuery, sqlcon);

            // Execute la demande
            SqlDataReader rdr = sqlcmd.ExecuteReader();
            while (rdr.Read())
            {
                Title = (string)rdr["Nom"];
                gameName.Text = (string)rdr["Nom"];
                break;
            }
            rdr.Close();

            if (idJoueur > -1)
            {
                // Séléctionne la cote que le joueur a donnée
                strQuery = "SELECT Votes.cote AS cote FROM Votes " +
                            "INNER JOIN utilisateurs ON utilisateurs.id = Votes.idJoueur " +
                            "INNER JOIN Jeux ON Jeux.id = Votes.idJeu " +
                            "WHERE Votes.idJoueur = " + idJoueur + " AND Votes.idJeu = " + idJeu;
                sqlcmd = new SqlCommand(strQuery, sqlcon);

                // Execute la demande
                rdr = sqlcmd.ExecuteReader();
                while (rdr.Read())
                {
                    String value = rdr["cote"].ToString();
                    DropDownList ddl = (DropDownList)TableVote.FindControl("Score");

                    ddl.SelectedValue = value;

                    break;
                }
                rdr.Close();
            }
            else
                appVote.Visible = false;

            sqlcon.Close();

            // Masque le jeu
            if (idJeu != 8) {
                appSilverlight.Visible = false;
                appVote.Visible = false;
            }
        }

        /**
         * Vote pour un jeu
         */
        protected void voter(object sender, EventArgs e)
        {
            int idJoueur, idJeu, cote;

            try
            {
                idJoueur = SiteMaster.getUserId();
                idJeu = Convert.ToInt32(Request.QueryString["Id"]);
                cote = Convert.ToInt32(((DropDownList)TableVote.FindControl("Score")).SelectedValue);
            }
            catch (FormatException ex)
            {
                return;
            }

            if (idJoueur <= -1)
                return;

            SqlConnection sqlcon = new SqlConnection(ConfigurationManager.ConnectionStrings["FinalGameDBConnectionString"].ConnectionString);
            int id = Convert.ToInt32(Request.QueryString["Id"]);
            string strQuery = "INSERT INTO Votes (idJoueur, idJeu, cote, count) " +
                                    "VALUES ( " + idJoueur + ", " + idJeu + ", " + cote + ", 1)";

            // Execute la demande
            Boolean success = false;
            try
            {
                SqlCommand sqlcmd = new SqlCommand(strQuery, sqlcon);
                sqlcon.Open();
                sqlcmd.ExecuteReader();
                sqlcon.Close();
            }
            catch (SqlException ex)
            {
                sqlcon.Close();
                success = false;
            }

            // Nouvelle entrée ajoutée
            if (success)
                return;

            // On doit mettre a jour
            strQuery = "UPDATE Votes SET cote = " + cote + " " +
                        "WHERE  idjoueur = " + idJoueur + " " +
                               "AND idJeu = " + idJeu;

            try
            {
                SqlCommand sqlcmd = new SqlCommand(strQuery, sqlcon);
                sqlcon.Open();
                sqlcmd.ExecuteReader();
                sqlcon.Close();
            }
            catch (SqlException ex)
            {
                sqlcon.Close();
                Response.Write("Unhandled SQLException lors de la modification du vote !");
                return;
            }
        }
    }
}