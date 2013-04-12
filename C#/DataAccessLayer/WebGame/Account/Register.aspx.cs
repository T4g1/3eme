using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebGame.ServiceReference1;

namespace WebGame.Account
{
    public partial class Register : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            RegisterUser.ContinueDestinationPageUrl = Request.QueryString["ReturnUrl"];
        }

        /**
         * ajout d'un nouvel utilisateur à la base de donnée + connexion 
         */
        protected void RegisterUser_CreatedUser(object sender, EventArgs e)
        {
            /*récupération des informations de l'utilisateur*/
            TextBox firstName = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("FirstName");
            TextBox name = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("Name");
            TextBox userName =(TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("UserName");
            TextBox passWord = (TextBox)RegisterUser.CreateUserStep.ContentTemplateContainer.FindControl("Password");
            //création du nouvel utilisateur
            utilisateur user = new utilisateur();
            user.Prenom = firstName.Text;
            user.Nom = name.Text;
            user.Pseudo = userName.Text;
            user.Password = passWord.Text;
            user.Status = true;

            //vérification de l'unicité de l'utilisateur
            //#TODO: faire appel à du linq
            BLLClient client = new BLLClient();
            utilisateur[] users = client.GetUsers();
            int newId = -1;
            //parcour des clients existant
            foreach (utilisateur temp in users) { 
                //si le pseudo existe
                if(temp.Pseudo.Equals(user.Pseudo)){
                    user.id = -1;
                    break;
                }
                newId = temp.id+1;
            }


            if( user.id != -1){
                //Ajout à la bd le nouvel utilisateur
                user.id = newId;
                client.Insertuser(user);
                client.CommitData();
                //création de la session http
                FormsAuthentication.SetAuthCookie(RegisterUser.UserName, false /* createPersistentCookie */);
                string continueUrl = RegisterUser.ContinueDestinationPageUrl;
                if (String.IsNullOrEmpty(continueUrl))
                {
                    continueUrl = "~/";
                }
                Response.Redirect(continueUrl);
            }
        }

    }
}
