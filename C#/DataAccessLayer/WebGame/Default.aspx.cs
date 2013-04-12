using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebGame
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            //Afficher bonjour à l'utilisateur connecté
            Label1.Text = Page.User.Identity.Name+" ";
            Label1.Text += "type d'authetification: " + Page.User.Identity.AuthenticationType+" ";
            Label1.Text += "connecté: " + Page.User.Identity.IsAuthenticated;
            Page.User.IsInRole("Administrateur");
        }
    }
}
