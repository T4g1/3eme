using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ClientServiceWeb.ServiceReference1;

namespace ClientServiceWeb
{
    public partial class Form1 : Form
    {
        private SOAPWSClient swc;
        private String login = "admin";
        private String password = "admin";

        /**
         * Constructeur
         */
        public Form1()
        {
            InitializeComponent();

            swc = new SOAPWSClient();

            // Génération des gridView
            displayPlayers.AutoGenerateColumns = true;
            displayHighScore.AutoGenerateColumns = true;
            displayPartieParJoueur.AutoGenerateColumns = true;
            displayLooser.AutoGenerateColumns = true;

            // Init du premier onglet
            refreshPlayer_Click(null, null);
        }

        /**
         * Mise a jour des joueurs
         */
        private void refreshPlayer_Click(object sender, EventArgs e)
        {
            // Récupére les joueurs
            joueur[] players = swc.GetPlayer(
                    login, password,
                    new ServiceReference1.GetPlayer()
            );

            // Affichage dans la datagrid
            displayPlayers.DataSource = players;
        }

        /**
         * Mise a jour des scores
         */
        private void refreshScore_Click(object sender, EventArgs e)
        {
            // Récupére les scores
            highScore[] highScore = swc.getHighScore(
                    login, password,
                    new ServiceReference1.getHighScore()
            );

            // Affichage dans la datagrid
            displayHighScore.DataSource = highScore;
        }

        /**
         * Mise a jour des parties
         */
        private void refreshPartie_Click(object sender, EventArgs e)
        {
            // Récupére les scores
            highScore[] Partie = swc.GetPlayByPlayer(
                    login, password,
                    new ServiceReference1.GetPlayByPlayer()
            );

            // Compte s parties jouées
            long total = 0;
            foreach (highScore jeu in Partie)
            {
                total += jeu.score;
            }

            // Affiche le tout
            partieCountField.Text = "Total de parties jouées: " + total;
            displayPartieParJoueur.DataSource = Partie;
        }


        /**
         * Mise a jour des parties perdues
         */
        private void refreshLoose_Click(object sender, EventArgs e)
        {
            // Récupére les parties perdues
            highScore[] looser = swc.GetLooseByPlayer(
                    login, password,
                    new ServiceReference1.GetLooseByPlayer()
            );

            // Affichage dans la datagrid
            displayLooser.DataSource = looser;
        }

        /**
         * On ajoute un joueur
         */
        private void addPlayer_Click(object sender, EventArgs e)
        {
            // Ajout du joueur
            swc.AddPlayer(
                    login, password,
                    new ServiceReference1.AddPlayer() {
                        pseudo = pseudoField.Text
                    }
            );

            // Mise a jour de la datagrid
            this.refreshPlayer_Click(null, null);
        }

        /**
         * Supprime un joueur
         */
        private void deletePlayer_Click(object sender, EventArgs e)
        {
            // Confirmation
            DialogResult result = MessageBox.Show(
                    "êtes-vous sur de vouloir supprimer " + displayPlayers.SelectedRows[0].Cells[2].Value.ToString(),
                    "Supprimer un joueur ?", MessageBoxButtons.YesNo
            );

            // Si on a repondu oui
            if (result.ToString().Equals("Yes"))
            {
                swc.RemovePlayer(
                        login, password,
                        new ServiceReference1.RemovePlayer() {
                            id = (long)displayPlayers.SelectedRows[0].Cells[0].Value
                        }
                );
            }

            // Mise a jour de la datagrid
            this.refreshPlayer_Click(null, null);
        }

        /**
         * Changement d'onglet
         */
        private void tabControlSelected(object sender, TabControlEventArgs e)
        {
            switch (e.TabPage.Name)
            {
                case "playerTab":
                    refreshPlayer_Click(null, null);
                    break;
                case "scoreTab":
                    refreshScore_Click(null, null);
                    break;
                case "winPartieTab":
                    refreshPartie_Click(null, null);
                    break;
                case "loosePartieTab":
                    refreshLoose_Click(null, null);
                    break;
            }
        }

        /**
         * On selectionne un autre joueur
         */
        private void playerSelectedChanged(object sender, EventArgs e)
        {
            try
            {
                pseudoField.Text = (String)displayPlayers.SelectedRows[0].Cells[2].Value;
            }
            catch (ArgumentOutOfRangeException ex) { }
        }

        /**
         * Modifie le joueur séléctionné
         */
        private void updatePlayer_Click(object sender, EventArgs e)
        {
            // récupére le pseudo
            try
            {
                // Récupére les données
                String newPseudo = pseudoField.Text;
                long playerId = (long)displayPlayers.SelectedRows[0].Cells[0].Value;

                // Modification du joueur
                AlterUserResponse resp = swc.AlterUser(
                        login, password,
                        new ServiceReference1.AlterUser() {
                            id=playerId,
                            pseudo = newPseudo
                        }
                );

                // Mise a jour de la datagrid
                this.refreshPlayer_Click(null, null);
            }
            catch (ArgumentOutOfRangeException ex)
            {
                MessageBox.Show(
                    "Pas de joueur séléctionné",
                    "Erreur", MessageBoxButtons.OK
                );
            }
        }
    }
}
