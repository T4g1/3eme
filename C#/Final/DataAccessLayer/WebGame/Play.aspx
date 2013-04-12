<%@ Page Title="Jeux" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Play.aspx.cs" Inherits="CowGame.Play" %>

<asp:Content ID="HeaderContent" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
     <h2>
        <asp:Label runat="server" ID="gameName" />
    </h2>
    <p runat="server" id="appSilverlight">
        <object width="300" height="300"
            data="data:application/x-silverlight-2," 
            type="application/x-silverlight-2" >
            <param name="source" value="ClientBin/ReflexShooter.xap"/>

            <!-- Display installation image. -->
            <a href="http://go.microsoft.com/fwlink/?LinkID=149156&v=4.0.60310.0" 
                style="text-decoration: none;">
                <img src="http://go.microsoft.com/fwlink/?LinkId=161376" 
                    alt="Get Microsoft Silverlight" 
                    style="border-style: none"/>
            </a>
        </object>
    </p>

    <p runat="server" id="appVote">
        <asp:Table runat="server" ID="TableVote" AutoGenerateColumns="False" Width="917px">
            <asp:TableHeaderRow id="HeaderRow" runat="server">
                <asp:TableCell Text="Votre avis"></asp:TableCell>
                <asp:TableCell Text="Action"></asp:TableCell>
            </asp:TableHeaderRow>       

            <asp:TableRow>
                <asp:TableCell>
                    <asp:DropDownList ID="Score" runat="server">
                        <asp:ListItem Value="" Text="(Séléctionnez une option)" />
                        <asp:ListItem Value="0" Text="Méprisable" />
                        <asp:ListItem Value="1" Text="Nul" />
                        <asp:ListItem Value="2" Text="Bof" />
                        <asp:ListItem Value="3" Text="Cool" />
                        <asp:ListItem Value="4" Text="Génial" />
                        <asp:ListItem Value="5" Text="Exeptionnel" />
                    </asp:DropDownList>
                </asp:TableCell>
                <asp:TableCell>
                    <asp:Button ID="Button1" runat="server" Text="Voter" OnClick="voter"/>
                </asp:TableCell>
            </asp:TableRow>
        </asp:Table>
    </p>
</asp:Content>