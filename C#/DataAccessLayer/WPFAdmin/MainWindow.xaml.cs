using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using WPFAdmin.ServiceReference1;

namespace WPFAdmin
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private ServiceReference1.BLLClient client;

        public MainWindow()
        {
            InitializeComponent();
            client = new ServiceReference1.BLLClient();
            if (client != null) {
                SolidColorBrush myBrush = new SolidColorBrush(Colors.Green);
                connectstate.Fill = myBrush;
            }


        }

        private void Table_selected_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //mets les bouton en mode caché
            playerChangeStatus.Visibility = System.Windows.Visibility.Hidden;
            deleteButton.Visibility = System.Windows.Visibility.Hidden;
            changeGameStatusButton.Visibility = System.Windows.Visibility.Hidden;

            switch (GetSelectedTable())
            {
                case "utilisateurs":
                    playerChangeStatus.Visibility = System.Windows.Visibility.Visible;
                    utilisateur[] users = client.GetUsers();
                    dataGrid1.ItemsSource = users;

                    break;
                case "jeux":
                    changeGameStatusButton.Visibility = System.Windows.Visibility.Visible;
                    Jeux[] games = client.GetGames();
                    dataGrid1.ItemsSource = games;
                    break;
                case "categories":
                    deleteButton.Visibility = System.Windows.Visibility.Visible;
                    Category[] cate = client.GetCategory();
                    dataGrid1.ItemsSource = cate;
                    break;
                case "highscores":
                    HighScore[] scores = client.GetHighScores();
                    dataGrid1.ItemsSource = scores;
                    break;
                case "votes":
                    Vote[] votes = client.Getvotes();
                    dataGrid1.ItemsSource = votes;
                    break;
                case "admins":
                    admin[] admins = client.GetAdmins();
                    dataGrid1.ItemsSource = admins;
                    break;
                case "editeurs":
                    editeur[] editeurs = client.GetEditeurs();
                    dataGrid1.ItemsSource = editeurs;
                    break;
                case "joueurs":
                    joueur[] joueurs = client.GetJoueurs();
                    dataGrid1.ItemsSource = joueurs;
                    break;
                case "utilisateurs actif":
                    UsersActif[] actif = client.GetUsersActif();
                    dataGrid1.ItemsSource = actif;
                    break;
                case "utilisateurs non actif":
                    UsersNoActif[] noactif = client.GetUsersNoActif();
                    dataGrid1.ItemsSource = noactif;
                    break;
            }
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            client.Close();
        }

        private void ClickOnAddUser(object sender, RoutedEventArgs e)
        {
            //nothing
        }

        private void ClickOnAddCategory(object sender, RoutedEventArgs e)
        {

            addCatGB.Visibility = System.Windows.Visibility.Visible;
        }

        private void CancelAddCategButton_Click(object sender, RoutedEventArgs e)
        {
            //anuler ajout categorie
            addCatGB.Visibility = System.Windows.Visibility.Hidden;
        }

        private void addCategButton_Click(object sender, RoutedEventArgs e)
        {
            //Ajout de la categorie
            Category newcat = new Category();
            newcat.id = int.Parse(idCatTB.Text);
            newcat.Nom = nomCatTB.Text;
            newcat.Description = descriptionTB.Text;

            client.InsertCategory(newcat);
            client.CommitData();

            idCatTB.Text = "";
            nomCatTB.Text = "";
            descriptionTB.Text = "";
            addCatGB.Visibility = System.Windows.Visibility.Hidden;
                      
        }

        private void deleteButton_Click(object sender, RoutedEventArgs e)
        {
            //Suppression d'une categorie
            if (GetSelectedTable() == "categories")
            {
                //recup le tuple
                Category cate = dataGrid1.SelectedItem as Category;
                client.DeleteFromCategories(cate.id);
                client.CommitData();
            }
        }

        private void dataGrid1_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            if (GetSelectedTable() == "categories")
            {
                modifyElem elem = new modifyElem();
                elem.SetElemToModify("categories");
                //recup les données
                Category cate = dataGrid1.CurrentItem as Category;
                //passage d'option à la fenètre modale
                elem.Nom = cate.Nom;
                elem.Id = cate.id.ToString();
                elem.Description = cate.Description;

                elem.ShowDialog();

                //maj de la catégorie.
                client.UpdateFromCategories(int.Parse(elem.Id), elem.NewDescription);
                client.CommitData();
            }
            else if (GetSelectedTable() == "utilisateurs")
            {
                modifyElem elem = new modifyElem();
                //recup les données
                utilisateur user = dataGrid1.CurrentItem as utilisateur;
                //passage d'option à la fenètre modale
                elem.SetElemToModify("utilisateurs");
                elem.Nom_TB = user.Nom;
                elem.Prenom_TB = user.Prenom;
                elem.Pseudo_TB = user.Pseudo;
                elem.Id_TB = user.id.ToString();
                elem.Password_TB = user.Password;
                elem.Useractivated_CB = user.Status;
                elem.grantUser_CB.SelectedIndex = user.Droit;
                
                elem.ShowDialog();

                user.id = int.Parse(elem.Id_TB);
                user.Nom = elem.Nom_TB;
                user.Prenom = elem.Prenom_TB;
                user.Pseudo = user.Pseudo;
                user.Password = elem.Password_TB;
                user.Status = elem.Useractivated_CB.Value;
                user.Droit = elem.grantUser_CB.SelectedIndex;

                //maj d'un utilisateur.
                client.UpdateFromUsers(user);
                client.CommitData();
            }
        }

        private void playerChangeStatus_Click(object sender, RoutedEventArgs e)
        {
            //changement du status d'un joueur
            if (GetSelectedTable() == "utilisateurs")
            {
                //recup le tuple
                utilisateur cate = dataGrid1.SelectedItem as utilisateur;
                client.UpdateUserStatus(cate.id);
                client.CommitData();
            }

        }

        #region get method
        public string GetSelectedTable() {
            ComboBoxItem cbi = (ComboBoxItem)Table_selected.SelectedItem;
            return cbi.Content.ToString();
        }
        #endregion

        private void changeGameStatusButton_Click(object sender, RoutedEventArgs e)
        {
            if (GetSelectedTable() == "jeux")
            {
                //recup le tuple
                Jeux jeu = dataGrid1.SelectedItem as Jeux;
                client.UpdateGamesStatus(jeu.id);
                client.CommitData();
            }
        }

        

        
    }
}
