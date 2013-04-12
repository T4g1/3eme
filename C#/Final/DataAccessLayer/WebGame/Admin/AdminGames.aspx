<%@ Page Title="Administration" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="AdminGames.aspx.cs" Inherits="CowGame.Admin.AdminGames" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <h2>
        Administration
    </h2>
    <p>
        Administration des jeux. Sur cette page vous pouvez modifier le status d'un jeu.</p>

    <p>
        <asp:GridView ID="listeJeux" runat="server" AllowPaging="True" 
            DataKeyNames="id" AllowSorting="True" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource1" Width="917px"
            onrowediting="listeJeux_RowEditing" ShowHeaderWhenEmpty="True">
            <Columns>
                <asp:BoundField DataField="id" HeaderText="Id" SortExpression="id" ReadOnly="True" Visible="False" />
                <asp:BoundField DataField="Nom" HeaderText="Nom" SortExpression="Nom" ReadOnly="True" />
                <asp:BoundField DataField="Description" HeaderText="Description" SortExpression="Description" ReadOnly="True" />
                <asp:BoundField DataField="PEGI" HeaderText="PEGI" SortExpression="PEGI" ReadOnly="True" />
                <asp:BoundField DataField="Categorie" HeaderText="Categorie" SortExpression="Categorie" ReadOnly="True" />
                <asp:BoundField DataField="Editeur" HeaderText="Editeur" SortExpression="Editeur" ReadOnly="True" />
                <asp:TemplateField HeaderText="Status" SortExpression="Status" >
                    <ItemTemplate>
                        <asp:DropDownList ID="DropDownListStatus" runat="server" DataField="Status" Enabled="False" AppendDataBoundItems="true" SelectedValue='<%# Bind("Status") %>'>
                            <asp:ListItem Value="0">Invalide</asp:ListItem>
                            <asp:ListItem Value="1">A valider</asp:ListItem>
                            <asp:ListItem Value="2">Validé</asp:ListItem>
                        </asp:DropDownList>
                    </ItemTemplate>
                    <EditItemTemplate>
                        <asp:DropDownList ID="DropDownListStatus" runat="server" DataField="Status" Enabled="True" AppendDataBoundItems="true" SelectedValue='<%# Bind("Status") %>'>
                            <asp:ListItem Value="0">Invalide</asp:ListItem>
                            <asp:ListItem Value="1">A valider</asp:ListItem>
                            <asp:ListItem Value="2">Validé</asp:ListItem>
                        </asp:DropDownList>
                    </EditItemTemplate>
                </asp:TemplateField>
                <asp:BoundField DataField="Hits" HeaderText="Hits" SortExpression="Hits" ReadOnly="True" />
                <asp:BoundField DataField="Cote" HeaderText="Cote" SortExpression="Cote" ReadOnly="True" />
                <asp:CommandField ShowEditButton="True" ButtonType="Button" />
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
                            GROUP BY Jeux.id, Jeux.Description, Jeux.Nom, Jeux.PEGI, utilisateurs.Nom, Categories.Nom, Jeux.Status"
            Updatecommand="UPDATE [Jeux] SET [Status] = @Status WHERE (id = @id)">
            <UpdateParameters >
                <asp:Parameter Name="Status" />
                <asp:Parameter Name="id" />
            </UpdateParameters>
        </asp:SqlDataSource>
    </p>
</asp:Content>
