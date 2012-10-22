<header>
    <div id="titre_principal">
        <img src="images/zozor_logo.png" alt="Logo de Zozor" id="logo" />
        <h1>Inpres Hollidays</h1>
        <h2>Vos vacances en ligne !</h2>
    </div>

    <nav>
        <ul>
            <li><a href="controlServlet">Accueil</a></li>
            <%
            if(userinfo.isLogged())
            {
                %>
                <li><a href="controlServlet?action=listing">Reserver</a></li>
                <li><a href="controlServlet?action=caddie">Caddie</a></li>
                <li><a href="controlServlet?action=logoff">Logout</a></li>
                <%
            }
            %>
        </ul>
    </nav>
</header>

<div id="banniere_image">
    <div id="banniere_description">
        Les vacances de rêves...
        <a href="controlServlet?action=listing" class="bouton_rouge">Reserver ! <img src="images/flecheblanchedroite.png" alt="" /></a>
    </div>
</div>