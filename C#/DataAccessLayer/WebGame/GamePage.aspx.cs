using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebGame.ServiceReference1;

namespace WebGame
{
    public partial class GamePage : System.Web.UI.Page
    {
        private BLLClient client = new BLLClient();

        /*
         * méthode exécuté lors de l'affichage de la page
         */
        protected void Page_Load(object sender, EventArgs e)
        {
            //si la page est chargé pour la première fois
            if (Page.IsPostBack)
            {
                Jeux[] games = client.GetGames();
                displayGames.DataSource = games;
                displayGames.DataBind();
            }
        }
    }
}