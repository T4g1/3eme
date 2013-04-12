<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="GamePage.aspx.cs" Inherits="WebGame.GamePage" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <p>
        <asp:GridView ID="displayGames" runat="server" Height="145px" Width="590px">
        </asp:GridView>
        <br />
        à quelle jeu voulez-vous jouer ?
    </p>
    
</asp:Content>
