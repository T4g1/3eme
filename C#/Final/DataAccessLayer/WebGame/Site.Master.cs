using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Configuration;
using System.Web.Security;

namespace CowGame
{
    public partial class SiteMaster : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        /**
         * Récupére l'id de l'utilisateur
         * 
         * @return      -1 si erreur, id sinon
         */
        public static int getUserId()
        {
            try
            {
                int id = 0;

                SqlConnection sqlcon = new SqlConnection(ConfigurationManager.ConnectionStrings["FinalGameDBConnectionString"].ConnectionString);
                string strQuery = "SELECT utilisateurs.id FROM utilisateurs WHERE Pseudo = '" + Membership.GetUser().UserName + "'";
                SqlCommand sqlcmd = new SqlCommand(strQuery, sqlcon);

                // Execute la demande
                sqlcon.Open();
                SqlDataReader rdr = sqlcmd.ExecuteReader();
                while (rdr.Read())
                {
                    id = rdr.GetInt32(0);
                    break;
                }
                sqlcon.Close();

                return id;
            }
            catch (NullReferenceException ex)
            {
                return -1;
            }
        }
    }
}
