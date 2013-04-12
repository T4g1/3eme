using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CowGame.ServiceReference1;
using System.Data.SqlClient;
using System.Configuration;
using System.Data;
using System.Web.Security;

namespace CowGame.Editeur
{
    public partial class Manage : System.Web.UI.Page
    {
        private Boolean dataSourceEmpty = false;

        protected void Page_Load(object sender, EventArgs e)
        {
            // SelectCommand
            SqlDataSource1.SelectCommand =
                    "SELECT Jeux.Nom AS Nom, Jeux.Description AS Description, Jeux.PEGI AS PEGI, SUM(Votes.count) AS Hits, Jeux.Status AS Status, " +
                    "       ROUND(AVG(Votes.cote), 2, 1) AS Cote, Jeux.id AS id, utilisateurs.Nom AS Editeur, Jeux.Categorie AS CategorieID, Categories.Nom AS Categorie " +
                    "FROM Jeux  " +
                    "LEFT JOIN Categories ON Categories.id = Jeux.Categorie  " +
                    "LEFT JOIN utilisateurs ON utilisateurs.id = Jeux.Editeur  " +
                    "LEFT JOIN Votes ON Votes.idJeu = Jeux.id " +
                    "WHERE Jeux.Editeur = " + SiteMaster.getUserId() + " " +
                    "GROUP BY Jeux.id, Jeux.Description, Jeux.Nom, Jeux.PEGI, utilisateurs.Nom, Categories.Nom, Jeux.Categorie, Jeux.Status";
        }

        protected void tableAddGame_PreRender(object sender, EventArgs e)
        {
            if (dataSourceEmpty) {
                addEmptyLine();
            }
        }

        protected void listeJeux_RowEditing(object sender, GridViewEditEventArgs e)
        {
            listeJeux.EditIndex = e.NewEditIndex;
        }

        /**
         * Ajout d'un nouveau jeu
         */
        public void listeJeux_Insert(object sender, EventArgs e)
        {
            SqlConnection sqlcon = new SqlConnection(SqlDataSource1.ConnectionString);
            string strQuery = "INSERT INTO [Jeux] (Nom, Description, PEGI, Categorie, Editeur, Status) VALUES (@Nom, @Description, @PEGI, @CategorieID, @UserID, 1)";
            SqlCommand sqlcmd = new SqlCommand(strQuery, sqlcon);

            // Ajoute les paramétres
            sqlcmd.Parameters.Add("@Nom", SqlDbType.NVarChar, 50).Value = ((TextBox)TableAddGame.FindControl("AddNom")).Text;
            sqlcmd.Parameters.Add("@Description", SqlDbType.NVarChar, 500).Value = ((TextBox)TableAddGame.FindControl("AddDescription")).Text;
            sqlcmd.Parameters.Add("@PEGI", SqlDbType.NVarChar, 50).Value = ((TextBox)TableAddGame.FindControl("AddPEGI")).Text;
            sqlcmd.Parameters.Add("@CategorieID", SqlDbType.Int).Value = ((DropDownList)TableAddGame.FindControl("AddCategorie")).SelectedIndex;
            sqlcmd.Parameters.Add("@UserID", SqlDbType.Int).Value = SiteMaster.getUserId();

            // Execute la demande
            sqlcon.Open();
            sqlcmd.ExecuteNonQuery();
            sqlcon.Close();

            // Rafraîchit le GridView
            listeJeux.DataBind();
        }

        /**
         * Modification d'un nouveau jeu
         */
        public void listeJeux_Modifier(object sender, EventArgs e)
        {
            SqlConnection sqlcon = new SqlConnection(SqlDataSource1.ConnectionString);
            string strQuery = "UPDATE [Jeux] SET [Nom] = @Nom, [Categorie] = @CategorieID, [PEGI] = @PEGI, [Description] = @Description, [Status] = 1 WHERE (id = @id)";
            SqlCommand sqlcmd = new SqlCommand(strQuery, sqlcon);
            
            // Ajoute les paramétres
            sqlcmd.Parameters.Add("@Nom", SqlDbType.NVarChar, 50).Value = ((TextBox)listeJeux.FindControl("UpdateNom")).Text;
            sqlcmd.Parameters.Add("@Description", SqlDbType.NVarChar, 500).Value = ((TextBox)listeJeux.FindControl("UpdateDescription")).Text;
            sqlcmd.Parameters.Add("@PEGI", SqlDbType.NVarChar, 50).Value = ((TextBox)listeJeux.FindControl("UpdatePEGI")).Text;
            sqlcmd.Parameters.Add("@CategorieID", SqlDbType.Int).Value = ((DropDownList)listeJeux.FindControl("UpdateCategorie")).SelectedIndex;
            sqlcmd.Parameters.Add("@id", SqlDbType.Int).Value = ((TextBox)listeJeux.FindControl("UpdateId")).Text;

            // Execute la demande
            sqlcon.Open();
            sqlcmd.ExecuteNonQuery();
            sqlcon.Close();

            // Rafraîchit le GridView
            listeJeux.DataBind();
        }

        /**
         * Vérifie si le datasource est vide
         */
        protected void SqlDataSource1_Selected(object sender, SqlDataSourceStatusEventArgs e)
        { 
            // Données trouvées
            if (e.AffectedRows >= 1)
                return;

            dataSourceEmpty = true;
        }

        /**
        * Ajoute une ligne vide dans le gridView
        */
        protected void addEmptyLine()
        {
            // Cellule pour afficher l'erreur
            GridViewRow grid = new GridViewRow(0, 0, DataControlRowType.DataRow, DataControlRowState.Normal);
            TableCell cell = new TableCell();
            cell.ColumnSpan = listeJeux.Columns.Count;
            cell.Text = "Aucun jeux trouvé";
            cell.Attributes.Add("style", "text-align:center");
            grid.Cells.Add(cell);
            listeJeux.Controls[0].Controls.AddAt(1, grid);
        }
    }
}