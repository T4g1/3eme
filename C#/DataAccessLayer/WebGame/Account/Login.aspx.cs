using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using WebGame.ServiceReference1;

namespace WebGame.Account
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            RegisterHyperLink.NavigateUrl = "Register.aspx?ReturnUrl=" + HttpUtility.UrlEncode(Request.QueryString["ReturnUrl"]);
        }

        protected void LoginUser_Authenticate(object sender, AuthenticateEventArgs e)
        {
            BLLClient client = new BLLClient();
            //demande d'authentification d'un client 
            e.Authenticated = client.UserConnect(LoginUser.UserName, LoginUser.Password);
            
        }
    }
}
