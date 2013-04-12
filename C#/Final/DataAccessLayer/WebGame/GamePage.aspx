<%@ Page Title="Jeux" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="GamePage.aspx.cs" Inherits="CowGame.GamePage" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
     <h2>
        Nos jeux
    </h2>
    <p>
        <asp:GridView ID="displayGames" runat="server" Width="916px" 
            AllowPaging="True" AllowSorting="True" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource1" ShowHeaderWhenEmpty="True">
            <Columns>
                <asp:BoundField DataField="Nom" HeaderText="Nom" SortExpression="Nom" />
                <asp:BoundField DataField="Description" HeaderText="Description" SortExpression="Description" />
                <asp:BoundField DataField="PEGI" HeaderText="PEGI" SortExpression="PEGI" />
                <asp:BoundField DataField="Categorie" HeaderText="Categorie" SortExpression="Categorie" />
                <asp:BoundField DataField="Editeur" HeaderText="Editeur" SortExpression="Editeur" />
                <asp:BoundField DataField="Hits" HeaderText="Hits" SortExpression="Hits" ReadOnly="True" />
                <asp:BoundField DataField="Cote" HeaderText="Cote" SortExpression="Cote" ReadOnly="True" />
                <asp:TemplateField HeaderText="Action">
                    <ItemTemplate>
                        <asp:HyperLink runat="server" NavigateUrl='<%# "~/Play.aspx?Id=" + Eval("id") %>' Text="Jouer" />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:FinalGameDBConnectionString %>" 
            ProviderName="System.Data.SqlClient" 
            SelectCommand=" SELECT  Jeux.Nom AS Nom, Jeux.Description AS Description, Jeux.PEGI AS PEGI, SUM(Votes.count) AS Hits, Jeux.Status AS Status,
                                    ROUND(AVG(Votes.cote), 2, 1) AS Cote, Jeux.id AS id, utilisateurs.Nom AS Editeur, Categories.Nom AS Categorie
                            FROM Jeux 
                            LEFT JOIN Categories ON Categories.id = Jeux.Categorie 
                            LEFT JOIN utilisateurs ON utilisateurs.id = Jeux.Editeur 
                            LEFT JOIN Votes ON Votes.idJeu = Jeux.id
                            WHERE Jeux.Status = 2
                            GROUP BY Jeux.id, Jeux.Description, Jeux.Nom, Jeux.PEGI, utilisateurs.Nom, Categories.Nom, Jeux.Status">
        </asp:SqlDataSource>
    </p>
</asp:Content>
