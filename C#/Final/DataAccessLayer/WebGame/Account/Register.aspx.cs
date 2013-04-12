using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using CowGame.ServiceReference1;

namespace CowGame.Account
{
    public partial class Register : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            RegisterUser.ContinueDestinationPageUrl = Request.QueryString["ReturnUrl"];
        }

        /**
         * Ajout d'un nouvel utilisateur à la base de donnée + connexion 
         */
        protected void RegisterUser_CreatedUser(object sender, EventArgs e)
        {
            // Récupération des informations de l'utilisateur
            TextBox firstName = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("FirstName");
            TextBox name = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("Name");
            TextBox userName = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("UserName");
            TextBox passWord = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("Password");
            DropDownList droit = (DropDownList)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("Activity");

            //vérification de l'unicité de l'utilisateur
            //#TODO: faire appel à du linq
            BLLClient client = new BLLClient();
            utilisateur[] users = client.GetUsers();

            // Parcourt des clients existant
            // TODO: pas top comme systéme
            int newId = 0;
            foreach (utilisateur temp in users)
            {
                // Si le pseudo existe
                if (temp.Pseudo.Equals(userName.Text))
                    return;

                newId = temp.id + 1;
            }

            //création du nouvel utilisateur
            utilisateur newUser = new utilisateur();
            newUser.id = newId;
            newUser.Prenom = firstName.Text;
            newUser.Nom = name.Text;
            newUser.Pseudo = userName.Text;
            newUser.Password = passWord.Text;
            newUser.Status = true;

            client.Insertuser(newUser);
            client.CommitData();

            // Role de l'utilisateur
            String role = droit.SelectedValue;
            if (droit.SelectedValue.Equals("Editeur"))
                Roles.AddUserToRole(newUser.Pseudo, "Editeur");

            FormsAuthentication.SetAuthCookie(RegisterUser.UserName, false);

            // Redirection
            string url = RegisterUser.ContinueDestinationPageUrl;
            if (String.IsNullOrEmpty(url))
                url = "~/";

            Response.Redirect(url);
        }
    }
}
