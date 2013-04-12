<%@ Page Title="Mes jeux" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Manage.aspx.cs" Inherits="CowGame.Editeur.Manage" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
     <h2>
        Mes jeux
    </h2>
    <p>
        Administration de vos jeux. Sur cette page vous pouvez modifier vos jeux, en ajouter de nouveau ou en supprimer.
    </p>

    <p>
        <asp:GridView ID="listeJeux" runat="server" AllowPaging="True" 
            DataKeyNames="id" AllowSorting="True" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource1" Width="917px" ShowHeaderWhenEmpty="True"
            OnRowEditing="listeJeux_RowEditing">
            <Columns>
                <asp:BoundField DataField="id" HeaderText="Id" SortExpression="id" ReadOnly="True" Visible="False" />
                <asp:BoundField DataField="Nom" HeaderText="Nom" SortExpression="Nom" />
                <asp:BoundField DataField="Description" HeaderText="Description" SortExpression="Description" />
                <asp:BoundField DataField="PEGI" HeaderText="PEGI" SortExpression="PEGI" />
                
                <%-- Categorie --%>
                <asp:TemplateField HeaderText="Categorie" SortExpression="Categorie" >
                    <EditItemTemplate>
                        <asp:DropDownList ID="UpdateCategorie" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSourceCategorie" DataTextField="Categorie" DataValueField="id" SelectedValue='<%# Bind("CategorieID") %>'>
                        </asp:DropDownList>
                    </EditItemTemplate>
                    <ItemTemplate>
                        <asp:Label ID="Label4" runat="server" Enabled="False" Text='<%# Eval("Categorie") %>' />
                    </ItemTemplate>
                </asp:TemplateField>
                
                <%-- Status --%>
                <asp:TemplateField HeaderText="Status" SortExpression="Status" >
                    <ItemTemplate>
                        <asp:DropDownList ID="DropDownList1" runat="server" AppendDataBoundItems="true" Enabled="False" SelectedValue='<%# Bind("Status") %>'>
                            <asp:ListItem Value="0">Invalide</asp:ListItem>
                            <asp:ListItem Value="1">A valider</asp:ListItem>
                            <asp:ListItem Value="2">Validé</asp:ListItem>
                        </asp:DropDownList>
                    </ItemTemplate>
                </asp:TemplateField>

                <asp:BoundField DataField="Hits" HeaderText="Hits" SortExpression="Hits" ReadOnly="True" />
                <asp:BoundField DataField="Cote" HeaderText="Cote" SortExpression="Cote" ReadOnly="True" />
                
                <%-- Action --%>
                <asp:TemplateField HeaderText="Action">
                    <ItemTemplate>
                        <asp:Button ID="Button1" runat="server" CausesValidation="False" CommandName="Edit" Text="Modifier" />
                        &nbsp;<asp:Button ID="Button2" runat="server" CausesValidation="False" CommandName="Delete" Text="Supprimer" />
                    </ItemTemplate>
                    <EditItemTemplate>
                        <asp:Button ID="Button1" runat="server" CausesValidation="True" CommandName="Update" Text="Mettre à jour" />
                        &nbsp;<asp:Button ID="Button2" runat="server" CausesValidation="False" CommandName="Cancel" Text="Annuler" />
                    </EditItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </p>

    <p>
        <asp:Table runat="server" ID="TableAddGame" AutoGenerateColumns="False" Width="917px" ShowHeaderWhenEmpty="True" ShowFooter="True" OnPreRender="tableAddGame_PreRender">
            <asp:TableHeaderRow id="HeaderRow" runat="server">
                <asp:TableCell Text="Nom"></asp:TableCell>
                <asp:TableCell Text="Description"></asp:TableCell>
                <asp:TableCell Text="PEGI"></asp:TableCell>
                <asp:TableCell Text="Categorie"></asp:TableCell>
                <asp:TableCell Text="Action"></asp:TableCell>
            </asp:TableHeaderRow>       

            <asp:TableRow>
                <asp:TableCell>
                    <asp:TextBox ID="AddNom" runat="server" />
                </asp:TableCell>
                <asp:TableCell>
                    <asp:TextBox ID="AddDescription" runat="server" />
                </asp:TableCell>
                <asp:TableCell>
                    <asp:TextBox ID="AddPEGI" runat="server" />
                </asp:TableCell>
                <asp:TableCell>
                    <asp:DropDownList ID="AddCategorie" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSourceCategorie" DataTextField="Categorie" DataValueField="id">
                    </asp:DropDownList>
                </asp:TableCell>
                <asp:TableCell>
                    <asp:Button ID="Button1" runat="server" Text="Ajouter" OnClick="listeJeux_Insert"/>
                </asp:TableCell>
            </asp:TableRow>
        </asp:Table>
    </p>

    <!-- SelectCommand voire Code Behind -->
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:FinalGameDBConnectionString %>" 
        ProviderName="System.Data.SqlClient" onselected="SqlDataSource1_Selected"
        Updatecommand=" UPDATE [Jeux] SET [Nom] = @Nom, [Categorie] = @CategorieID, [PEGI] = @PEGI, [Description] = @Description, [Status] = 1 WHERE (id = @id)"
        DeleteCommand=" DELETE FROM [Jeux] WHERE (id = @id)">
        <UpdateParameters >
            <asp:Parameter Name="id" />
            <asp:Parameter Name="Nom" />
            <asp:Parameter Name="Description" />
            <asp:Parameter Name="PEGI" />
            <asp:Parameter Name="Categorie" />
        </UpdateParameters>
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSourceCategorie" runat="server" 
        ConnectionString="<%$ ConnectionStrings:FinalGameDBConnectionString %>" 
        ProviderName="System.Data.SqlClient" 
        SelectCommand=" SELECT Categories.Nom AS Categorie, Categories.id AS id
                        FROM Categories">
    </asp:SqlDataSource>
</asp:Content>
