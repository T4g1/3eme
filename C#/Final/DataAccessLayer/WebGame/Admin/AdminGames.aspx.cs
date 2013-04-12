using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CowGame.Admin
{
    public partial class AdminGames : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void listeJeux_RowEditing(object sender, GridViewEditEventArgs e)
        {
            listeJeux.EditIndex = e.NewEditIndex;
            Response.Write("alert('DATA UPDATED')");
        }
    }
}