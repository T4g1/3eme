using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace ReflexShooter
{
    public static class MouseState
    {
        private static MainPage _parent;

        public static double X
        {
            get;
            set;
        }

        public static double Y
        {
            get;
            set;
        }

        public static FrameworkElement Listener
        {
            get;
            private set;
        }

        static MouseState()
        {
            X = 0;
            Y = 0;
        }

        public static void Listen(FrameworkElement element, MainPage parent)
        {
            _parent = parent;

            Listener = element;
            Listener.MouseLeftButtonDown += new MouseButtonEventHandler(Listener_MouseLeftButtonDown);
            Listener.MouseMove += new MouseEventHandler(Listener_MouseMove);
        }

        public static void Listener_MouseMove(object sender, MouseEventArgs e)
        {
            Point point = e.GetPosition(null);
            X = point.X;
            Y = point.Y;
        }

        public static void Listener_MouseLeftButtonDown(object sender, MouseEventArgs e)
        {
            _parent.tire();
        }
    }
}
