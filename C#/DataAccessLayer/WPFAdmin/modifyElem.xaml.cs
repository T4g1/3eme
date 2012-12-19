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
using System.Windows.Shapes;

namespace WPFAdmin
{
    /// <summary>
    /// Interaction logic for modifyElem.xaml
    /// </summary>
    public partial class modifyElem : Window
    {
        private string newDescription = "";


        #region getter and setter
        public string NewDescription
        {
            get { return newDescription; }
            set { newDescription = value; }
        }
        public string Id
        {
            get { return textBox2.Text; }
            set { textBox2.Text = value; }
        }
        public string Nom
        {
            get { return textBox1.Text; }
            set { textBox1.Text = value; }
        }
        public string Description
        {
            get { return descri_TB.Text; }
            set { descri_TB.Text = value; }
        }

        public string Id_TB{
            get { return id_TB.Text; }
            set { id_TB.Text = value; }
        }
        public string Nom_TB{
            get { return nom_TB.Text; }
            set { nom_TB.Text = value; }
        }
        public string Prenom_TB{

            get { return prenom_TB.Text; }
            set { prenom_TB.Text = value; }
        }
        public string Pseudo_TB{
            get { return pseudo_TB.Text; }
            set { pseudo_TB.Text = value; }
        }
        public string Password_TB{
            get { return password_TB.Text; }
            set { password_TB.Text = value; }
        }
        public string Avatar_TB{
            get { return avatar_TB.Text; }
            set { avatar_TB.Text = value; }
        }
        public string GrantUser_CB
        {
            get {
                ComboBoxItem cbi = (ComboBoxItem)grantUser_CB.SelectedValue;
                return cbi.Content.ToString();
            }
            set { grantUser_CB.SelectedValue = value; }
        }
        public bool? Useractivated_CB {
            get { return useractivated_CB.IsChecked.Value; }
            set { useractivated_CB.IsChecked = value; }
        
        }
        #endregion

        public modifyElem()
        {
            InitializeComponent();
        }

        public void SetElemToModify(String table) {
            categGrid.Visibility = System.Windows.Visibility.Hidden;
            UserModified.Visibility = System.Windows.Visibility.Hidden;

            if (table.Equals("utilisateurs")) {
                UserModified.Visibility = System.Windows.Visibility.Visible;
            }
            else if (table.Equals("categories")) {
                categGrid.Visibility = System.Windows.Visibility.Visible;
            }
        }

        private void cancelModifiedButton_Click(object sender, RoutedEventArgs e)
        {
            this.Visibility = System.Windows.Visibility.Hidden;
            newDescription = "-1";
        }
        private void validateModifiedCategory_Click(object sender, RoutedEventArgs e)
        {
            this.Visibility = System.Windows.Visibility.Hidden;
            newDescription = descri_TB.Text;
        }



    }
}
