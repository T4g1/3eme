using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace GameSilver1
{
    public partial class MainPage : UserControl
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void button1_MouseEnter(object sender, MouseEventArgs e)
        {
            button1.IsEnabled = false;
            button1.Content = "Non";
            button2.Content = "Oui";
        }

        private void button1_MouseLeave(object sender, MouseEventArgs e)
        {
            button1.IsEnabled = true;
            button1.Content = "Oui";
            button2.Content = "Non";
        }
    }
}
