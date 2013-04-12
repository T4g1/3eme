using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CowGame.ServiceReference1;

namespace CowGame
{
    public partial class GamePage : System.Web.UI.Page
    {
        private BLLClient client = new BLLClient();

        protected void Page_Load(object sender, EventArgs e)
        {
        }
    }
}